package edu.ifsp.ped.spring.Model.ObjetosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.Controle;
import edu.ifsp.ped.spring.Model.Objetos.Professor;

public class ProfessorDAO {
    
    static Controle controle =  new Controle();
    static Connection conexao = controle.getConnection();
    
    public static void adicionaP(Professor prof){

        String sql = "INSERT INTO professor(prof_nomeC,prof_senha,prof_usuario) VALUES(?,?,?)";  
        try {  
            PreparedStatement stmt = conexao.prepareStatement(sql);  
            stmt.setString(1, prof.getNomeC());  
            stmt.setString(2, prof.getSenha());  
            stmt.setString(3, prof.getUsuario());  
             
            stmt.execute();  
            stmt.close();  
        } catch (SQLException u) {  
            throw new RuntimeException(u);  
        }  
        System.out.println("Colocado no banco");
    }

    public static ArrayList<Professor> buscarBancoP(){

        ResultSet resultSet = null;
        
        try {
            String consulta = "SELECT * FROM professor";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar uma lista para armazenar os dados
        ArrayList<Professor> profs = new ArrayList<>();

        // Processar os resultados
        try {
            while (resultSet.next()) {
                
                String nomeC = resultSet.getString("prof_nomeC");
                String usuario = resultSet.getString("prof_usuario");
                String senha = resultSet.getString("prof_senha");

                Professor prof = new Professor(nomeC);
                prof.setUsuario(usuario);
                prof.setSenha(senha);
                profs.add(prof);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profs;
    }

    public static void editaSenha(int ID, String nova){
        try {
            // Preparar a declaração SQL
            String sql = "UPDATE professor SET prof_senha = ? WHERE prof_cod = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros
            preparedStatement.setString(1, nova);
            preparedStatement.setInt(2, ID);

            // Executar a instrução SQL
            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verificar se a edição foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Item editado com sucesso.");
            } else {
                System.out.println("Nenhum item foi editado.");
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public static void editaUsu(int ID, String nova){
        try {
            // Preparar a declaração SQL
            String sql = "UPDATE professor SET prof_usuario = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros
            preparedStatement.setString(1, nova);
            preparedStatement.setInt(2, ID);

            // Executar a instrução SQL
            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verificar se a edição foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Item editado com sucesso.");
            } else {
                System.out.println("Nenhum item foi editado.");
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public static void apagaBancoProf(int ID){

        try {
            // Preparar a declaração SQL
            String sql = "DELETE FROM professor WHERE prof_cod = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Definir o valor do parâmetro
            preparedStatement.setInt(1, ID);

            // Executar a instrução SQL
            int linhasAfetadas = preparedStatement.executeUpdate();

            // Verificar se a exclusão foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Item deletado com sucesso.");
            } else {
                System.out.println("Nenhum item foi deletado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
