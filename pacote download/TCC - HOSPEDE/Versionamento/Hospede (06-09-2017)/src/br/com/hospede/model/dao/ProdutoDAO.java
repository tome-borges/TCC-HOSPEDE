package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.DTOProduto;
import br.com.hospede.model.DTO.PedidosDeQuarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static ProdutoDAO  instance = null;
    private java.sql.Timestamp dt_acaoSQL;
    
    public void cadastrarProduto(DTOProduto produto){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("INSERT INTO produto (nome, preco, quantidade, dt_entrada) VALUES"
                                           + "(?,?,?,?)");
            statement.setString(1,produto.getNome());
            statement.setDouble(2,produto.getPreco() );
            statement.setInt(3,produto.getQuantidade());
            statement.setTimestamp(4,dt_acaoSQL = new java.sql.Timestamp( new Date().getTime()));
                       
            statement.execute();
            
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto:\n"+erro.getMessage());
        }
        
        banco.desconectar();
    }
    
    public void deletarProduto(DTOProduto produto){
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("DELETE FROM produto WHERE nome = ?");
            statement.setString(1, produto.getNome());

            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar produto:\n" + erro.getMessage());
        }
        banco.desconectar();
        
    }
    
    public DTOProduto consultarProduto(String nome){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        DTOProduto produto = new DTOProduto();
        
          try{
            statement = banco.setStatement("SELECT * FROM public.produto where nome = ?");
            
            statement.setString(1,nome);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setPreco(resultSet.getDouble(3));
                produto.setEntrega(resultSet.getDate(4));
                produto.setQuantidade(resultSet.getInt(5));
            }
            
            
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return produto;
    }
    
    public void editarProduto(DTOProduto produto){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE produto SET preco=?, dt_entrada=?, quantidade=? WHERE nome = ?");
            
            statement.setDouble(1, produto.getPreco());
            statement.setTimestamp(2, dt_acaoSQL = new java.sql.Timestamp( new Date().getTime()));
            statement.setInt(3, produto.getQuantidade());
            statement.setString(4, produto.getNome());
            

            statement.execute();
            JOptionPane.showMessageDialog(null, "Produto editada com sucesso!");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar produto:\n" + erro.getMessage());
        }

        banco.desconectar();
    }
    
    public ArrayList<DTOProduto> listarProduto(){
        DTOProduto produto;
        ArrayList<DTOProduto> listaProduto = new ArrayList<DTOProduto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM produto");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new DTOProduto();
                
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setPreco(resultSet.getDouble(3));
                produto.setEntrega(resultSet.getDate(4));
                produto.setQuantidade(resultSet.getInt(5));
               
                
                listaProduto.add(produto);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaProduto;
    }
    
    public ArrayList<PedidosDeQuarto> listarProdutoPedido(){
        PedidosDeQuarto produto;
        ArrayList<PedidosDeQuarto> listaProduto = new ArrayList<PedidosDeQuarto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM produtos_consumidos");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new PedidosDeQuarto();
                
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setPreco(resultSet.getDouble(3));
               
                produto.setQuantidade(resultSet.getInt(5));
               
                
                listaProduto.add(produto);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos pedidos:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaProduto;
    }
    
    public void cadastrarProdutoPedido(Reserva reserva, DTOProduto produto){
        
         banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("INSERT INTO produtos_consumidos (id_reserva, nome, quantidade) VALUES"
                                           + "(?,?,?)");
            statement.setInt(1,reserva.getId_reserva());
            statement.setString(2,produto.getNome());
            statement.setInt(3,produto.getQuantidade());
            
                       
            statement.execute();
            
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto consumido:\n"+erro.getMessage());
        }
        
        banco.desconectar();
    }
    
    public ArrayList<DTOProduto> listarPedidosPorHospedagem(Reserva reserva){
        DTOProduto produto;
        ArrayList<DTOProduto> listaProduto = new ArrayList<DTOProduto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT id, id_reserva, nome, quantidade, total_pagar "
                    + "FROM public.produtos_consumidos where id_reserva = ?");
            statement.setInt(1, reserva.getId_reserva());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new DTOProduto();
                
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(3));
               
                
                listaProduto.add(produto);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos pedidos:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaProduto;
    }
            
    
    public static ProdutoDAO getInstance(){
        if(instance == null){
            instance = new ProdutoDAO();
        }
        return instance;
    }
}
