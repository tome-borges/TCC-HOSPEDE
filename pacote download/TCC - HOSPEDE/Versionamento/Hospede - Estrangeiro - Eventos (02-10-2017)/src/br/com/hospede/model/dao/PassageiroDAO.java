package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOPassageiro;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PassageiroDAO {
    
    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static PassageiroDAO  instance= null;
    private java.sql.Timestamp dt_SQL;
    
    public void cadastrarPassageiro(DTOPassageiro passageiro){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("INSERT INTO passageiros (id_passeio, id_hospedagem_responsavel, nome_passageiro,"
                    + " quarto_hospedagem, cpf_passageiro)"
                    + "VALUES (?,?,?,?,?)");
            
            statement.setInt(1, passageiro.getId_passeio());
            statement.setInt(2, passageiro.getId_hospedagem_responsavel());
            statement.setString(3, passageiro.getNome_passageiro());
            statement.setString(4, passageiro.getQuarto());
            statement.setString(5, passageiro.getCpf());
            
            statement.execute();
        
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar passageiro: \n"+ erro.getMessage());
       }
        banco.desconectar();
    }
    
    public ArrayList<DTOPassageiro> listarPassageiro(){
        DTOPassageiro adicionarClientePasseio;
        ArrayList<DTOPassageiro> listaPassageiro = new ArrayList<DTOPassageiro>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM passeio");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                adicionarClientePasseio = new DTOPassageiro();
                
                adicionarClientePasseio.setId(resultSet.getInt(1));
                adicionarClientePasseio.setId_passeio(resultSet.getInt(2));
                adicionarClientePasseio.setId_hospedagem_responsavel(resultSet.getInt(3));
                adicionarClientePasseio.setNome_passageiro(resultSet.getString(4));
                adicionarClientePasseio.setQuarto(resultSet.getString(5));
                adicionarClientePasseio.setCpf(resultSet.getString(6));
                
               
                
                listaPassageiro.add(adicionarClientePasseio);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar passageiro:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaPassageiro;
    }
    
    public ArrayList<DTOPassageiro> consultarPassageiros(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        ArrayList<DTOPassageiro> listaPassageiros = new ArrayList<DTOPassageiro>();
        
          try{
            statement = banco.setStatement("SELECT * FROM public.passageiros\n" +
                                           "WHERE ID_PASSEIO = ?;");
            
            statement.setInt(1,passeio.getId());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                DTOPassageiro passageiro = new DTOPassageiro();
                passageiro.setId(resultSet.getInt(1));
                passageiro.setId_passeio(resultSet.getInt(2));
                passageiro.setId_hospedagem_responsavel(resultSet.getInt(3));
                passageiro.setNome_passageiro(resultSet.getString(4));
                passageiro.setQuarto(resultSet.getString(5));
                passageiro.setCpf(resultSet.getString(6));
                
                listaPassageiros.add(passageiro);
            }
            
            
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar passageiro:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return listaPassageiros;
    }
    
    public void deletarPassegeiro(DTOPassageiro passageiro){
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("DELETE FROM passageiros WHERE id = ?");
            statement.setInt(1, passageiro.getId());

            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Passageiro deletado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar passageiro:\n" + erro.getMessage());
        }
        banco.desconectar();
        
    }
    
    public static PassageiroDAO getInstance(){
        if(instance == null){
            instance = new PassageiroDAO();
        }
        return instance;
    }
}
