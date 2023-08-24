package edu.ifsp.ped.spring.Model.ObjetosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ifsp.ped.spring.Model.Controle;
import edu.ifsp.ped.spring.Model.Objetos.Similaridade;

public class SimilaridadeDAO {
    static Controle controle =  new Controle();
        
    static Connection conexao = controle.getConnection();

        public static void adiciona(Similaridade sm){  

            String sql = "INSERT INTO similaridade(aul_cod,rela_cod,rel_valor) VALUES(?,?,?)";  
            
            try {  
                PreparedStatement stmt = conexao.prepareStatement(sql);  

                stmt.setInt(1,sm.getA1());  
                stmt.setInt(2,sm.getA2());  
                stmt.setDouble(3,sm.getValor());   
                stmt.execute();  
                stmt.close();  

            } catch (SQLException u) {  
                throw new RuntimeException(u);  
        }
    }

    public static ArrayList<Similaridade> buscarBancoSM(){

        ResultSet resultSet = null;
        
        try {
            String consulta = "SELECT * FROM similaridade";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar uma lista para armazenar os dados
        ArrayList<Similaridade> simi = new ArrayList<>();

        // Processar os resultados
        try {
            if (resultSet != null && resultSet.next()) {
                while (resultSet.next()) {

                    // Ler os dados do resultado
                    int a1 = resultSet.getInt("aul_cod");
                    int a2 = resultSet.getInt("rela_cod");
                    Double valor = resultSet.getDouble("rel_valor");
                
                    
                    Similaridade sm = new Similaridade(a1, a2, valor);

                    // Adicionar o valor à lista
                    simi.add(sm);
                }
            } else {
                System.out.println("Sem similaridades");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return simi;
    }


    public static void apagaBancoSM(){

        try {
            // Preparar a declaração SQL
            String sql = "drop table similaridade;";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.executeUpdate();

            String sql2 = "CREATE TABLE Similaridade (aul_cod INTEGER,rela_cod INTEGER,rel_valor DECIMAL(10));";
            PreparedStatement preparedStatement2 = conexao.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
