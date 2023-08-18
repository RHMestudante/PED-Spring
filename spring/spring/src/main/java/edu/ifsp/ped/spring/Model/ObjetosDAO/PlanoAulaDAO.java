package edu.ifsp.ped.spring.Model.ObjetosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.Controle;
import edu.ifsp.ped.spring.Model.Objetos.PlanoDeAula;

public class PlanoAulaDAO {

    static Controle controle =  new Controle();
    static Connection conexao = controle.getConexao();
    
    public static void adicionaPA(PlanoDeAula plano){
        String sql = "INSERT INTO PlanoDeAula(plan_dataI,plan_nomeTurma,plan_nomeCurso,plan_caminho, prof_cod) VALUES(?,?,?,?,?)";  
        try {  
            PreparedStatement stmt = conexao.prepareStatement(sql);  
            stmt.setString(1, plano.getDataI());  
            stmt.setString(2, plano.getTurma());  
            stmt.setString(3, plano.getCurso());  
            stmt.setString(4, plano.getCaminho());
            stmt.setInt(5, plano.getCodProf());  
            stmt.execute();  
            stmt.close();  
        } catch (SQLException u) {  
            throw new RuntimeException(u);  
        }  
        System.out.println("Colocado no banco");
    }

    public static ArrayList<PlanoDeAula> buscarBancoPA(){

        ResultSet resultSet = null;
        
        try {
            String consulta = "SELECT * FROM planodeaula";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar uma lista para armazenar os dados
        ArrayList<PlanoDeAula> planos = new ArrayList<>();

        // Processar os resultados
        try {
            int c = 0;
            while (resultSet.next()) {
                
                String dataI = resultSet.getString("plan_dataI");
                String turma = resultSet.getString("plan_nomeTurma");
                String curso = resultSet.getString("plan_nomeCurso");
                String caminho = resultSet.getString("plan_caminho");
                int codProf = resultSet.getInt("prof_cod");

                PlanoDeAula plano = new PlanoDeAula(dataI, turma, curso, caminho, codProf, c);
                planos.add(plano);
                c = c + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planos;
    }

    public static void apagaBancoPA(String turmaPlan) {
        try {
            // Preparar a declaração SQL
            String sql = "DELETE FROM planodeaula WHERE plan_nomeTurma = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Definir o valor do parâmetro
            preparedStatement.setString(1, turmaPlan);

            // Executar a instrução SQL
            int linhasAfetadas = preparedStatement.executeUpdate();

            String sql2 = "DELETE FROM aula WHERE plan_Turma = ?";
            PreparedStatement preparedStatement2 = conexao.prepareStatement(sql2);
            // Definir o valor do parâmetro
            preparedStatement2.setString(1, turmaPlan);

            // Executar a instrução SQL
            int linhasAfetadas2 = preparedStatement2.executeUpdate();

            // Verificar se a exclusão foi bem-sucedida
            if (linhasAfetadas > 0 && linhasAfetadas2 > 0) {
                System.out.println("Item deletado com sucesso.");
            } else {
                System.out.println("Nenhum item foi deletado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editaCPA(int ID, String nova){
        try {
            // Preparar a declaração SQL
            String sql = "UPDATE planodeaula SET plan_caminho = ? WHERE id = ?";
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
}
