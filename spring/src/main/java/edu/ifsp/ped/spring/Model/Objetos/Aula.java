package edu.ifsp.ped.spring.Model.Objetos;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String data, descricao;

    @ManyToOne
    PlanoDeAula planTurma;
    
    public Aula() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanoDeAula getPlanTurma() {
        return planTurma;
    }

    public void setPlanTurma(PlanoDeAula planTurma) {
        this.planTurma = planTurma;
    }

    public Aula(String data, String descricao) {
        this.data = data;
        this.descricao = descricao;
    }

}
