package edu.ifsp.ped.spring.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifsp.ped.spring.Model.Objetos.Aula;
import edu.ifsp.ped.spring.Model.Objetos.PlanoDeAula;
import edu.ifsp.ped.spring.Model.Objetos.Professor;
import edu.ifsp.ped.spring.repository.PlanoDeAulaRepository;
import edu.ifsp.ped.spring.repository.ProfessorRepository;

@Service
public class PlanoDeAulaService {

    @Autowired
    static PlanoDeAulaRepository PlanoRepo; 
    @Autowired
    static ProfessorRepository ProfRepo; 

    public static PlanoDeAula adiocionarPlano(String caminho1, long profCod) {

        ArrayList<String> TodasPalavras = new ArrayList<>();
        ArrayList<Integer> nData = new ArrayList<>();
        ArrayList<Integer> nFimDesc = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();
        ArrayList<String> conteudos = new ArrayList<>();
        ArrayList<Aula> aulas = new ArrayList<>();

        //Dados que já estão no banco
        ArrayList<PlanoDeAula> planos = (ArrayList<PlanoDeAula>) PlanoRepo.findAll();
        ArrayList<Professor> profs = (ArrayList<Professor>) ProfRepo.findAll();


        int Icurso=0, Fcurso=0, Iturma=0, Fturma=0, Iprof=0, Fprof =0; 

        // Carrega o arquivo PDF
        try{
            File file = new File(caminho1);
            PDDocument documento = Loader.loadPDF(file);
            // Extrai o texto do PDF
            PDFTextStripper stripper = new PDFTextStripper();
            String texto = stripper.getText(documento);
            String[] linhas = texto.split("\n");
            int contadorP = 0;
            for(String linha : linhas){
                String[] palavras = linha.split(" ");
                for(String palavra : palavras){
                    String palavraT = palavra.trim();
                    if(palavraT.equals("Curso:")){
                        Icurso = contadorP;
                    }
                    if(palavraT.equals("Período/Ano")){
                        Fcurso = contadorP;
                    }
                    if(palavraT.equals("Curricular:")){
                        Iturma = contadorP;
                    }
                    if(palavraT.equals("Carga")){
                        Fturma = contadorP;
                    }
                    if(palavraT.equals("INSTRUMENTOS")){
                        Fprof = contadorP -8;
                    }
                    if(!palavraT.equals("") && !palavraT.equals("AULAS")){
                        TodasPalavras.add(palavraT);
                    }
                    if(isDateValid(palavra)){
                        nData.add(TodasPalavras.size() - 1);
                    }
                    contadorP = contadorP +1;
                }
            }
            Iprof= Fturma; 
        }catch(Exception e){}
        //Salva as datas
        for(int iDada : nData){
            datas.add(TodasPalavras.get(iDada));
        }
        int Start = nData.get(0);
        //Salva o fim das descrições
        for(int r = Start; r < TodasPalavras.size(); r++){
            String palavra = TodasPalavras.get(r);
            palavra = palavra.toLowerCase();
            if((!TodasPalavras.get(r-1).equals("de")) && (!isDateValid(TodasPalavras.get(r-1))) && (palavra.equals("aula") || palavra.equals("aulas") || palavra.equals("atividade") || palavra.equals("seminário"))){   
                nFimDesc.add(r);
            }
        }
        //Salva os conteudos
        for(int t = 0; t < nFimDesc.size(); t++){
            String tex = "";
            for(int u = nData.get(t) + 1; u <= nFimDesc.get(t) - 1; u++){   
                tex += TodasPalavras.get(u) + " ";
            }
            conteudos.add(tex);
        }


        //Salvar Objetos

        //PlanoDeAula
        String curso = "";
        for(int c = Icurso+ 1; c < Fcurso -1 ; c++){
            curso = curso + " " + TodasPalavras.get(c);
        }
        String turmaL = "";
        for(int c = Iturma + 1; c < Fturma; c++){
            turmaL = turmaL + " " + TodasPalavras.get(c);
        }
        String data =datas.get(0);

        //Aula
        for(int r = 0; r < datas.size() - 1; r++){

            Aula aula = new Aula(datas.get(r),conteudos.get(r));
            aulas.add(aula);
        }

        long id = planos.size();
        PlanoDeAula planoAula = new PlanoDeAula(id, data, turmaL, curso, caminho1, profCod, aulas); 

        return planoAula;
    }
        
    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofPattern(dateFormat)
        .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            System.out.println(date);
            return true;
        } catch (DateTimeParseException e) {
        return false;
        } 
    }





    
    
}
