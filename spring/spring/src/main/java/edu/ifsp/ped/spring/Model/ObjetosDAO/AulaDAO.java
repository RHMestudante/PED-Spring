package edu.ifsp.ped.spring.Model.ObjetosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.Controle;
import edu.ifsp.ped.spring.Model.Objetos.Aula;

public class AulaDAO {

    static Controle controle =  new Controle();
        
    static Connection conexao = controle.getConexao();

        public static void adiciona(Aula aula){  

            String sql = "INSERT INTO aula(aul_data,aul_descricao,plan_Turma) VALUES(?,?,?)";  
            
            try {  
                PreparedStatement stmt = conexao.prepareStatement(sql);  

                stmt.setString(1, aula.getData());  
                stmt.setString(2, aula.getDescricao());  
                stmt.setString(3, aula.getplanTurma());   

                stmt.execute();  
                stmt.close();  

            } catch (SQLException u) {  
                throw new RuntimeException(u);  
        }
    }

    public static ArrayList<Aula> buscarBancoA(){

        ResultSet resultSet = null;
        
        try {
            String consulta = "SELECT * FROM aula";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar uma lista para armazenar os dados
        ArrayList<Aula> aulas = new ArrayList<>();

        // Processar os resultados
        try {
            while (resultSet.next()) {
                
                // Ler os dados do resultado
                String data = resultSet.getString("aul_data");
                String descricao = resultSet.getString("aul_descricao");
                String planTurma = resultSet.getString("plan_Turma");
            
                
                Aula aula = new Aula(data, descricao, planTurma);


                // Adicionar o valor à lista
                aulas.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aulas;
    }

    public static void editaAula(int aulaID, String nova){
        try {
            // Preparar a declaração SQL
            String sql = "UPDATE aula SET aul_descricao = ? WHERE id = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            // Definir os valores dos parâmetros
            preparedStatement.setString(1, nova);
            preparedStatement.setInt(2, aulaID);

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

    public static void apagaBancoAula(int ID){

        try {
            // Preparar a declaração SQL
            String sql = "DELETE FROM aula WHERE aul_cod = ?";
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