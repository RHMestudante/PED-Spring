package edu.ifsp.ped.spring.Model.Objetos;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import edu.ifsp.ped.spring.Model.ObjetosDAO.AulaDAO;
import edu.ifsp.ped.spring.Model.ObjetosDAO.PlanoAulaDAO;
import edu.ifsp.ped.spring.Model.ObjetosDAO.ProfessorDAO;
import edu.ifsp.ped.spring.Model.ObjetosDAO.SimilaridadeDAO;

public class PlanoDeAula extends Plano{

    String dataI, turma, curso, caminho;
    int codProf, planCod;
    ArrayList<Aula> aulas = AulaDAO.buscarBancoA();

    

    public PlanoDeAula(String dataI, String turma, String curso, String caminho, int codProf, int codPlan) {
        this.dataI = this.getDataI();
        this.turma = turma;
        this.curso = curso;
        this.caminho = this.getCaminho();
        this.codProf = codProf;
        this.planCod = codPlan;
    }

    public String getDataI() {
        return dataI;
    }

    public void setDataI(String dataI) {
        this.dataI = dataI;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public int getPlanCod() {
        return planCod;
    }

    public void setPlanCod(int planCod) {
        this.planCod = planCod;
    }

    @Override
    protected void QuantasAulas(int codPlan) {
        int q = 0;
        for(Aula aula : aulas){
            if(aula.getplanTurma() == turma){
                q = q + 1;
            }
        }
        System.out.println(q);  
    }

    public static PlanoDeAula adiocionarPlano(String caminho1) {

        ArrayList<String> TodasPalavras = new ArrayList<>();
        ArrayList<Integer> nData = new ArrayList<>();
        ArrayList<Integer> nFimDesc = new ArrayList<>();
        ArrayList<String> datas = new ArrayList<>();
        ArrayList<String> conteudos = new ArrayList<>();

        //Dados que já estão no banco
        ArrayList<PlanoDeAula> planos = PlanoAulaDAO.buscarBancoPA();
        ArrayList<Professor> profs = ProfessorDAO.buscarBancoP();
        ArrayList<Aula> aulas = new ArrayList<>();

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

        //Professor

        String nomeP = "";
        for(int c = Iprof+ 1; c < Fprof -1 ; c++){
            nomeP = nomeP + " " + TodasPalavras.get(c);
        }
        boolean temPIgual = false;
        int profCod =804202100;
        int countP = 1;
        for (Professor professor : profs){
            if(nomeP.equals(professor.getNomeC())){
                temPIgual = true;
                profCod = countP;
            }
            countP = countP +1;
        }
        nomeP.trim();
        if(profCod == 804202100){
            profCod = countP;
        }
        Professor prof = new Professor(nomeP);
        if(temPIgual == false){
            profs.add(prof);
            ProfessorDAO.adicionaP(prof);
        }


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
        PlanoDeAula planoAula = new PlanoDeAula(data, turmaL, curso, caminho1, profCod, planos.size()); 
        
        boolean temIgual = false;
        for (PlanoDeAula plano : planos){
            if(turmaL.equals(plano.getTurma())){
                temIgual = true;
            }
        }
        if(temIgual == false){
            planos.add(planoAula);
            PlanoAulaDAO.adicionaPA(planoAula);
        }

        //Aula
        if(temIgual == false){
        int indexP = 910202100;
        int contIndexP = 1;
        for (PlanoDeAula plano : planos){
            if(turmaL.equals(plano.getTurma()) && datas.get(0).equals(plano.getDataI())){
                indexP = plano.getPlanCod();
            }
            contIndexP = contIndexP +1;
        }
        if(indexP == 910202100){
            indexP = contIndexP + 1;
        }

        for(int r = 0; r < datas.size() - 1; r++){

            Aula aula = new Aula(datas.get(r),conteudos.get(r), turmaL);
            aulas.add(aula);
            AulaDAO.adiciona(aula);

        }
    }
        SimilaridadeDAO.apagaBancoSM();
        Aula.calcularSm();
        ArrayList<Similaridade> simis = SimilaridadeDAO.buscarBancoSM();
        System.out.println(profs.size());
        System.out.println(planos.size());
        System.out.println(simis.size());
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

