package edu.ifsp.ped.spring.Model.Objetos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Professor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String nomeC, usuario, senha;

    int atual;

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

    public Long getId() {
        return null;
    }

}
