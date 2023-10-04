package edu.ifsp.ped.spring.Model.Objetos;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String data, descricao;
    String planTurma;
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getplanTurma() {
        return planTurma;
    }

    public void setplanTurma(String planTurma) {
        this.planTurma = planTurma;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
