package edu.ifsp.ped.spring.Model;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Controle {

    private static final String DB_FILE_NAME = "db.sql";
    private static final String DB_PATH = Paths.get("").toAbsolutePath().resolve(DB_FILE_NAME).toString();

    private Connection conexao = null;

    public Controle() {
        if (verificarBancoExistente()) {
            conectarAoBanco();
        } else {
            criarBanco();
            conectarAoBanco();
            criarTabelas();
        }
    }

    private void conectarAoBanco() {
        try {
            // Carregar o driver JDBC do SQLite
            Class.forName("org.sqlite.JDBC");

            // Estabelecer a conexão com o banco de dados SQLite
            conexao = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            System.out.println("Conexão bem-sucedida!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do SQLite não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Falha na conexão com o banco de dados!");
            e.printStackTrace();
        }
    }

    private void criarBanco() {
        try {
            // Criar o diretório do banco de dados se não existir
            File diretorioBanco = new File(DB_PATH).getParentFile();
            if (!diretorioBanco.exists()) {
                diretorioBanco.mkdirs();
            }

            // Criar o arquivo do banco de dados se não existir
            File arquivoBanco = new File(DB_PATH);
            if (arquivoBanco.createNewFile()) {
                System.out.println("Banco de dados criado: " + DB_PATH);
            } else {
                System.out.println("O banco de dados já existe: " + DB_PATH);
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar o banco de dados!");
            e.printStackTrace();
        }
    }

    private void criarTabelas() {
        try (Statement statement = conexao.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS professor (" +
                    "prof_cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "prof_nomeC VARCHAR(500)," +
                    "prof_senha VARCHAR(30)," +
                    "prof_usuario VARCHAR(60)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS coordenador (" +
                    "coo_cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "coo_nomeC VARCHAR(500)," +
                    "coo_senha VARCHAR(30)," +
                    "coo_usuario VARCHAR(60)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS planoDeAula (" +
                    "plan_cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "plan_dataI VARCHAR(10)," +
                    "plan_nomeTurma VARCHAR(300)," +
                    "plan_nomeCurso VARCHAR(100)," +
                    "plan_caminho VARCHAR(300)," +
                    "prof_cod INTEGER," +
                    "FOREIGN KEY (prof_cod) REFERENCES professor (prof_cod)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS aula (" +
                    "aul_cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aul_data VARCHAR(10)," +
                    "aul_descricao VARCHAR(200)," +
                    "plan_Turma VARCHAR(200)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS planoDeEnsino (" +
                    "pla_cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pla_nome VARCHAR(20)," +
                    "pla_caminho VARCHAR(10)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS similaridade (" +
                    "aul_cod INTEGER," +
                    "rela_cod INTEGER," +
                    "rel_valor DECIMAL(10)," +
                    "FOREIGN KEY (aul_cod) REFERENCES aula (aul_cod)," +
                    "FOREIGN KEY (rela_cod) REFERENCES aula (aul_cod)" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS leciona (" +
                    "pla_cod INTEGER," +
                    "coo_cod INTEGER," +
                    "FOREIGN KEY (pla_cod) REFERENCES planoDeEnsino (pla_cod)," +
                    "FOREIGN KEY (coo_cod) REFERENCES coordenador (coo_cod)" +
                    ");";

            statement.executeUpdate(sql);
            System.out.println("Tabelas criadas com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar as tabelas!");
            e.printStackTrace();
        }
    }

    public void fecharConexao() {
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

    public Connection getConnection() {
        return conexao;
    }

    private boolean verificarBancoExistente() {
        File arquivoBanco = new File(DB_PATH);
        return arquivoBanco.exists();
    }
}
