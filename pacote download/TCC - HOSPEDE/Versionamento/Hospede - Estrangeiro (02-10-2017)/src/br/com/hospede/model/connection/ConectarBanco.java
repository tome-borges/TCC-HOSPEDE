package br.com.hospede.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class ConectarBanco {
    
    private Connection          conexao;
    private PreparedStatement   statement;
    private ResultSet           resultSet;
    private final String        driver    = "org.postgresql.Driver";
    private final String        caminho   = "jdbc:postgresql://localhost:5432/HOSPEDE";
    private final String        usuario   = "postgres";
    private final String        senha     = "admin";
    public static ConectarBanco instance  = null;
    
    public Connection conectar(){//Conecta o banco de dados.
        
        try{
        System.setProperty("jdbc.Drivers", driver);
        conexao = DriverManager.getConnection(caminho,usuario, senha);
        
        }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao conectar banco:\n"+erro.getMessage());
        }
        return conexao;
    }
    
    //------------------------------------------------------------------------------------------------
    
    public void desconectar(){
        
        try{
        conexao.close();
        }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao desconectar banco:\n"+erro.getMessage());
        }
    }
    
    //------------------------------------------------------------------------------------------------
    
    public PreparedStatement setStatement(String sql){//Seta o statement.
        
        try{
            statement = conexao.prepareStatement(sql);
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao setar statement:\n"+erro.getMessage());
        }
        
        return statement;
    }
    
    //-------------------------------------------------------------------------------------------------
    
    public static ConectarBanco getInstance(){
        if(instance == null){
            instance = new ConectarBanco();
        }
        return instance;
    }

}
