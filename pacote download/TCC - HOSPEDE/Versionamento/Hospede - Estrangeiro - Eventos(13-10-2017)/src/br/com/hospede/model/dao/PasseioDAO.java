package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOPassageiro;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class PasseioDAO {
    
    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static PasseioDAO  instance= null;
    private java.sql.Timestamp dt_SQL;
    
    
    public boolean cadastrarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean cadastrou = false;
        
        try{
            statement = banco.setStatement("INSERT INTO passeio (titulo, ingresso, data, descricao, status) VALUES"
                                           + "(?,?,?,?,?)");
            statement.setString(1,passeio.getTitulo());
            statement.setDouble(2,passeio.getIngresso() );
            statement.setString(4,passeio.getDescricao());
            statement.setTimestamp(3,dt_SQL = new java.sql.Timestamp(passeio.getData().getTime()));
            statement.setString(5,"ABERTO");
                       
            statement.execute();
            JOptionPane.showMessageDialog(null, "Passeio cadastrado com sucesso!");
            cadastrou = true;
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar passeio:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return cadastrou;
    }
    
    public boolean deletarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try {
            statement = banco.setStatement("DELETE FROM passeio WHERE titulo = ? and status = 'ABERTO'");
            statement.setString(1, passeio.getTitulo());

            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Passeio deletado com sucesso!");
             deletou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar passeio:\n" + erro.getMessage());
        }
        banco.desconectar();
        return deletou;
    }
    
    public DTOPasseio consultarPasseio(String nome){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        DTOPasseio passeio = new DTOPasseio();
        
          try{
            statement = banco.setStatement("SELECT * FROM passeio where titulo = ? and status = 'ABERTO'");
            
            statement.setString(1,nome);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                passeio.setId(resultSet.getInt(1));
                passeio.setTitulo(resultSet.getString(2));
                passeio.setIngresso(resultSet.getDouble(3));
                passeio.setData(resultSet.getDate(4));
                passeio.setDescricao(resultSet.getString(5));
                passeio.setStatus(resultSet.getString(6));
            }
            
            
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar passeio:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return passeio;
    }
    
    public boolean editarPasseio(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE passeio SET ingresso=?, data=?, descricao=?, status=? WHERE titulo = ? and status = 'ABERTO'");
            
            statement.setDouble(1, passeio.getIngresso());
            statement.setTimestamp(2, dt_SQL = new java.sql.Timestamp(passeio.getData().getTime()));
            statement.setString(3, passeio.getDescricao());
            statement.setString(4, passeio.getStatus());
            statement.setString(5, passeio.getTitulo());
            
            

            statement.execute();
            JOptionPane.showMessageDialog(null, "Passeio editado com sucesso!");
            editou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar passeio:\n" + erro.getMessage());
        }

        banco.desconectar();
        return editou;
    }
    
    public ArrayList<DTOPasseio> listarPasseio(){
        DTOPasseio passeio;
        ArrayList<DTOPasseio> listaPasseio = new ArrayList<DTOPasseio>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM passeio WHERE status = 'ABERTO'");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                passeio = new DTOPasseio();
                
                passeio.setId(resultSet.getInt(1));
                passeio.setTitulo(resultSet.getString(2));
                passeio.setIngresso(resultSet.getDouble(3));
                passeio.setData(resultSet.getDate(4));
                passeio.setDescricao(resultSet.getString(5));
                passeio.setStatus(resultSet.getString(6));
               
                
                listaPasseio.add(passeio);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar passeio:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaPasseio;
    }
    
    public ArrayList<DTOPassageiro> listarPasseiosFecharConta(DTOReserva reserva){
        DTOPassageiro passeio;
        ArrayList<DTOPassageiro> listaPasseio = new ArrayList<DTOPassageiro>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT\n" +
                          "T2.TITULO,\n" +
                          "T2.INGRESSO,\n" +
                          "T2.DATA,\n" +
                          "T2.STATUS,\n"+
                          "T1.NOME_PASSAGEIRO,\n" +
                          "T1.CPF_PASSAGEIRO,\n" +
                          "T2.INGRESSO,\n" +
                          "T1.ID_HOSPEDAGEM_RESPONSAVEL,\n" +
                          "T4.NOME as \"Responsável\"\n" +
                          "\n" +
                          "FROM PASSAGEIROS T1\n" +
                          "INNER JOIN PASSEIO T2 ON T1.ID_PASSEIO= T2.ID\n" +
                          "INNER JOIN RESERVA T3 ON T1.ID_HOSPEDAGEM_RESPONSAVEL = T3.ID\n" +
                          "INNER JOIN CLIENTE T4 ON T3.ID_CLIENTE = T4.ID\n" +
                          "\n" +
                          "WHERE T3.ID = ? AND T2.STATUS = 'ABERTO'\n" +
                          "\n" +
                          "ORDER BY T2.TITULO");
             statement.setInt(1, reserva.getId_reserva());
             resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                passeio = new DTOPassageiro();
                passeio.setTituloPasseio(resultSet.getString(1));
                passeio.setNome_passageiro(resultSet.getString(5));
                passeio.setCpf(resultSet.getString(6));
                passeio.setIngresso(resultSet.getDouble(2));
                passeio.setResponsavel(resultSet.getString(9));
                
               
               
                
                listaPasseio.add(passeio);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar passeios para fechar:\n" + erro.getMessage());
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
