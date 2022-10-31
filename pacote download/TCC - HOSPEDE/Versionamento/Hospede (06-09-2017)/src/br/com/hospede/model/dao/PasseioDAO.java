package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.DTOPasseio;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class PasseioDAO {
    
    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static PasseioDAO  instance= null;
    private java.sql.Timestamp dt_SQL;
    
    
    public void cadastrarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("INSERT INTO passeio (titulo, ingresso, data, descricao) VALUES"
                                           + "(?,?,?,?)");
            statement.setString(1,passeio.getTitulo());
            statement.setDouble(2,passeio.getIngresso() );
            statement.setString(4,passeio.getDescricao());
            statement.setTimestamp(3,dt_SQL = new java.sql.Timestamp(passeio.getData().getTime()));
                       
            statement.execute();
            JOptionPane.showMessageDialog(null, "Passeio cadastrado com sucesso!");
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar passeio:\n"+erro.getMessage());
        }
        
        banco.desconectar();
    }
    
    public void deletarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("DELETE FROM passeio WHERE titulo = ?");
            statement.setString(1, passeio.getTitulo());

            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Passeio deletado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar passeio:\n" + erro.getMessage());
        }
        banco.desconectar();
        
    }
    
    public DTOPasseio consultarPasseio(String nome){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        DTOPasseio passeio = new DTOPasseio();
        
          try{
            statement = banco.setStatement("SELECT * FROM passeio where titulo = ?");
            
            statement.setString(1,nome);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                passeio.setId(resultSet.getInt(1));
                passeio.setTitulo(resultSet.getString(2));
                passeio.setIngresso(resultSet.getDouble(3));
                passeio.setData(resultSet.getDate(4));
                passeio.setDescricao(resultSet.getString(5));
            }
            
            
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar passeio:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return passeio;
    }
    
    public void editarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE passeio SET ingresso=?, data=?, descricao=? WHERE titulo = ?");
            
            statement.setDouble(1, passeio.getIngresso());
            statement.setTimestamp(2, dt_SQL = new java.sql.Timestamp(passeio.getData().getTime()));
            statement.setString(3, passeio.getDescricao());
            statement.setString(4, passeio.getTitulo());
            

            statement.execute();
            JOptionPane.showMessageDialog(null, "Passeio editado com sucesso!");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar passeio:\n" + erro.getMessage());
        }

        banco.desconectar();
    }
    
    public ArrayList<DTOPasseio> listarPasseio(){
        DTOPasseio passeio;
        ArrayList<DTOPasseio> listaPasseio = new ArrayList<DTOPasseio>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM passeio");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                passeio = new DTOPasseio();
                
                passeio.setId(resultSet.getInt(1));
                passeio.setTitulo(resultSet.getString(2));
                passeio.setIngresso(resultSet.getDouble(3));
                passeio.setData(resultSet.getDate(4));
                passeio.setDescricao(resultSet.getString(5));
               
                
                listaPasseio.add(passeio);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar passeio:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaPasseio;
    }
    
    public static PasseioDAO getInstance(){
        if(instance == null){
            instance = new PasseioDAO();
        }
        return instance;
    }
}
