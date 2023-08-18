package edu.ifsp.ped.spring.Model.Objetos;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ifsp.ped.spring.Model.ObjetosDAO.AulaDAO;
import edu.ifsp.ped.spring.Model.ObjetosDAO.PlanoAulaDAO;

public class Professor implements Academico{

    String nomeC, usuario, senha;

    int usandoAgora;


    public Professor(String nomeC) {
        this.nomeC = nomeC;
        String nome = nomeC.trim();
        String[] palavras = nome.split(" ");
        this.usuario = palavras[0];
        this.senha = senhaP;
    }

    public String getNomeC() {
        return nomeC;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAtual() {
        return usandoAgora;
    }

    public void setAtual(int usuA) {
        this.usandoAgora = usuA;
    }

    

    @Override
    public void removerPlano(int codPlan) {
        ArrayList<PlanoDeAula> planos = PlanoAulaDAO.buscarBancoPA();
        PlanoAulaDAO.apagaBancoPA(planos.get(codPlan).getTurma());
    }

    @Override
    public void editaAula(String planTurma) {
        Scanner ler = new Scanner(System.in);
        ArrayList<Aula> aulas = AulaDAO.buscarBancoA();
        System.out.println("Qual aula deseja editar?");
        for(int r = 0; r < aulas.size(); r++){
            if(aulas.get(r).getplanTurma() == planTurma){
                System.out.println(r + aulas.get(r).getDescricao());
            }
        }
        System.out.println("Qual indice?");
        int in = ler.nextInt();
        System.out.println("Novo texto:");
        String n = ler.next();
        AulaDAO.editaAula(in, n);
        ler.close();
    }
      
}
