package edu.ifsp.ped.spring.Model.Objetos;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;


@Entity
public class Professor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String nomeC, usuario, senha;

    int atual;

    @OneToOne(mappedBy = "professor")
    private PlanoDeAula planoDeAula;

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
