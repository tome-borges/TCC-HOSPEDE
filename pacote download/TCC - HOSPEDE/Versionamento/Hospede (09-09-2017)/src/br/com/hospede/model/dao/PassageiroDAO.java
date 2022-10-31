package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.AdicionarClientePasseio;
import br.com.hospede.model.DTO.DTOPasseio;
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
    
    public void cadastrarPassageiro(AdicionarClientePasseio passageiro){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("INSERT INTO passageiros (id_passeio, id_hospedagem_responsavel, nome_passageiro,"
                    + " quarto_hospedagem, cpf_passageiro)"
                    + "VALUES (?,?,?,?,?)");
            
            statement.setInt(1, passageiro.getId_Passeio());
            statement.setInt(2, passageiro.getId_Hospedagem());
            statement.setString(3, passageiro.getNome());
            statement.setString(4, passageiro.getQuarto());
            statement.setString(5, passageiro.getCpf());
            
            statement.execute();
        
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar passageiro: \n"+ erro.getMessage());
       }
        banco.desconectar();
    }
    
    public ArrayList<AdicionarClientePasseio> listarPassageiro(){
        AdicionarClientePasseio adicionarClientePasseio;
        ArrayList<AdicionarClientePasseio> listaPassageiro = new ArrayList<AdicionarClientePasseio>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM passeio");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                adicionarClientePasseio = new AdicionarClientePasseio();
                
                adicionarClientePasseio.setId(resultSet.getInt(1));
                adicionarClientePasseio.setId_Passeio(resultSet.getInt(2));
                adicionarClientePasseio.setId_Hospedagem(resultSet.getInt(3));
                adicionarClientePasseio.setNome(resultSet.getString(4));
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
    
    public ArrayList<AdicionarClientePasseio> consultarPassageiros(DTOPasseio passeio){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        ArrayList<AdicionarClientePasseio> listaPassageiros = new ArrayList<AdicionarClientePasseio>();
        
          try{
            statement = banco.setStatement("SELECT * FROM public.passageiros\n" +
                                           "WHERE ID_PASSEIO = ?;");
            
            statement.setInt(1,passeio.getId());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                AdicionarClientePasseio passageiro = new AdicionarClientePasseio();
                passageiro.setId(resultSet.getInt(1));
                passageiro.setId_Passeio(resultSet.getInt(2));
                passageiro.setId_Hospedagem(resultSet.getInt(3));
                passageiro.setNome(resultSet.getString(4));
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
    
    public void deletarPassegeiro(AdicionarClientePasseio passageiro){
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
