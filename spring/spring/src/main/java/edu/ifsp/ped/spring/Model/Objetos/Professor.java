package edu.ifsp.ped.spring.Model.Objetos;

public class Professor{

    String nomeC, usuario, senha;

    int atual;


    public Professor(String nomeC, String usuario, String senha) {
        this.nomeC = nomeC;
        this.usuario = usuario;
        this.senha = senha;
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
        return atual;
    }

    public void setAtual(int usuA) {
        this.atual = usuA;
    }

}
