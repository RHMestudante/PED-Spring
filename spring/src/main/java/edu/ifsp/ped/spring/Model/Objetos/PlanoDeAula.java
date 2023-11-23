package edu.ifsp.ped.spring.Model.Objetos;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PlanoDeAula{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String dataI, turma, curso, caminho;
    long codProf;
    @OneToMany(mappedBy = "planTurma")
    List<Aula> aula;

    public PlanoDeAula() {
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

    public long getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public PlanoDeAula(long id, String dataI, String turma, String curso, String caminho, long codProf,
            List<Aula> aula) {
        this.id = id;
        this.dataI = dataI;
        this.turma = turma;
        this.curso = curso;
        this.caminho = caminho;
        this.codProf = codProf;
        this.aula = aula;
    }
 
}

