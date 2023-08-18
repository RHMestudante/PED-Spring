package edu.ifsp.ped.spring.Model.Objetos;

import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.ObjetosDAO.AulaDAO;

public class Similaridade {

    int a1, a2;
    double valor;

    public Similaridade(int a1, int a2, double valor) {
        this.a1 = a1;
        this.a2 = a2;
        this.valor = valor;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static double calcularSimilaridade(String descricao1, String descricao2) {
        int[][] dp = new int[descricao1.length() + 1][descricao2.length() + 1];

        for (int i = 0; i <= descricao1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= descricao2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= descricao1.length(); i++) {
            for (int j = 1; j <= descricao2.length(); j++) {
                if (descricao1.charAt(i - 1) == descricao2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insertion = dp[i][j - 1] + 1;
                    int deletion = dp[i - 1][j] + 1;
                    int substitution = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(insertion, deletion), substitution);
                }
            }
        }

        int maxLen = Math.max(descricao1.length(), descricao2.length());
        double normalizedDistance = (double) dp[descricao1.length()][descricao2.length()] / maxLen;
        return 1 - normalizedDistance;
    }


    public static int calcularSM(Aula a1) {
        ArrayList<Aula> aulas = AulaDAO.buscarBancoA();
        int iMaior = -1; // Valor inicializado como -1 para indicar que não há aulas com o mesmo objetivo

        for (int r = 0; r < aulas.size(); r++) {
            double similaridade = calcularSimilaridade(a1.getDescricao(), aulas.get(r).getDescricao());
            if (similaridade <= 0.99 && similaridade > iMaior) {
                iMaior = r;
            }
        }

        return iMaior;
    }
    
}
