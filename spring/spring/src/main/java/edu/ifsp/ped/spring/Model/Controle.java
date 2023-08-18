package edu.ifsp.ped.spring.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Controle {

    private static final String URL = "jdbc:mysql://localhost:3306/ped";
    private static final String USUARIO = "root";
    private static final String SENHA = "Henrique0712$";

    Connection conexao = null;

    public Controle() {
        try {
            // Registrar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão bem-sucedida!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Falha na conexão com o banco de dados!");
            e.printStackTrace();
        }
    }

    public void fecharConecao(){

        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão!");
                e.printStackTrace();
            }
        }
    }

    public Connection getConexao() {
        return conexao;
    }


}
