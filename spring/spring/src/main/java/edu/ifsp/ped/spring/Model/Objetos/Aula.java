package edu.ifsp.ped.spring.Model.Objetos;

import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.ObjetosDAO.AulaDAO;
import edu.ifsp.ped.spring.Model.ObjetosDAO.SimilaridadeDAO;

public class Aula {
    String data, descricao;
    String planTurma;
    int cod;

    public Aula(String data, String descricao, String planTurma) {
        this.data = data;
        this.descricao = descricao;
        this.planTurma = planTurma;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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

    public String getplanTurma() {
        return planTurma;
    }

    public void setplanTurma(String planTurma) {
        this.planTurma = planTurma;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static void calcularSm() {
        ArrayList<Aula> aulas = AulaDAO.buscarBancoA();

        for (int r = 0; r < aulas.size(); r++) {
            for (int prox = r + 1; prox < aulas.size(); prox++) {
                Aula aulaAtual = aulas.get(r);
                Aula aulaProxima = aulas.get(prox);

                if (!aulaAtual.getplanTurma().equals(aulaProxima.getplanTurma()) &&
                        Similaridade.calcularSimilaridade(aulaAtual.getDescricao(), aulaProxima.getDescricao()) >= 0.4) {

                    Similaridade simi = new Similaridade(r+1, prox+1, Similaridade.calcularSimilaridade(aulaAtual.getDescricao(), aulaProxima.getDescricao()));
                    SimilaridadeDAO.adiciona(simi);
                }
            }
        }
    }
    
}
