package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOPedidosDeQuarto;
import br.com.hospede.model.dto.DTOReserva;
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
    
    public boolean cadastrarProduto(DTOProduto produto){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean cadastrou = false;
        try{
            statement = banco.setStatement("INSERT INTO produto (nome, preco, quantidade, dt_entrada) VALUES"
                                           + "(?,?,?,?)");
            statement.setString(1,produto.getNome());
            statement.setDouble(2,produto.getPreco());
            statement.setInt(3,produto.getQuantidade());
            statement.setTimestamp(4,dt_acaoSQL = new java.sql.Timestamp( new Date().getTime()));
                       
            statement.execute();
            cadastrou = true;
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return cadastrou;
    }
    
    public boolean deletarProduto(DTOProduto produto){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try {
            statement = banco.setStatement("DELETE FROM produto WHERE nome = ?");
            statement.setString(1, produto.getNome());

            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
            deletou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar produto:\n" + erro.getMessage());
        }
        banco.desconectar();
        return deletou;
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
                produto.setEntrega(resultSet.getTimestamp(4));
                produto.setQuantidade(resultSet.getInt(5));
            }
            
            
        } catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar produto:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return produto;
    }
    
    public boolean editarProduto(DTOProduto produto){
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
            editou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar produto:\n" + erro.getMessage());
        }

        banco.desconectar();
        return editou;
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
                produto.setEntrega(resultSet.getTimestamp(4));
                produto.setQuantidade(resultSet.getInt(5));
               
                
                listaProduto.add(produto);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaProduto;
    }
    
    public ArrayList<DTOPedidosDeQuarto> listarProdutoPedido(){
        DTOPedidosDeQuarto produto;
        ArrayList<DTOPedidosDeQuarto> listaProduto = new ArrayList<DTOPedidosDeQuarto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM produtos_consumidos");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new DTOPedidosDeQuarto();
                
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
    
    public void cadastrarProdutoPedido(DTOReserva reserva, DTOProduto produto){
        
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
    
    public ArrayList<DTOProduto> listarPedidosPorHospedagem(DTOReserva reserva){
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
            
    
    
    public ArrayList<DTOProduto> listarProdutoFecharConta(DTOReserva reserva){
        DTOProduto produto;
        ArrayList<DTOProduto> listaProduto = new ArrayList<DTOProduto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT \n" +
                      "T1.NOME,\n" +
                      "T1.QUANTIDADE,\n" + 
                      "T4.PRECO,\n" +
                      "T4.id\n"+
                       "\n" +
                      "FROM PRODUTOS_CONSUMIDOS T1\n" +
                      "INNER JOIN RESERVA T2   ON T1.ID_RESERVA = T2.ID\n" +
                      "INNER JOIN CLIENTE T3   ON T2.ID_CLIENTE = T3.ID\n" +
                      "INNER JOIN PRODUTO T4   ON T1.NOME = T4.NOME\n" +
                       "\n" +
                      "WHERE T2.ID = ?\n" +
                      "\n" +
                      "ORDER BY T1.NOME");
            statement.setInt(1, reserva.getId_reserva());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new DTOProduto();
                
                produto.setNome(resultSet.getString(1));
                produto.setQuantidade(resultSet.getInt(2));
                produto.setPreco(resultSet.getDouble(3));
                produto.setId(resultSet.getInt(4));
             
                listaProduto.add(produto);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos pedidos:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaProduto;
    }
    
    public ArrayList<DTOProduto> listarProdutoReceita(int id){
        DTOProduto produto;
        ArrayList<DTOProduto> listaProduto = new ArrayList<DTOProduto>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT \n" +
                      "T1.NOME,\n" +
                      "T1.QUANTIDADE,\n" + 
                      "T4.PRECO,\n" +
                      "T4.id\n"+
                       "\n" +
                      "FROM PRODUTOS_CONSUMIDOS T1\n" +
                      "INNER JOIN RESERVA T2   ON T1.ID_RESERVA = T2.ID\n" +
                      "INNER JOIN CLIENTE T3   ON T2.ID_CLIENTE = T3.ID\n" +
                      "INNER JOIN PRODUTO T4   ON T1.NOME = T4.NOME\n" +
                       "\n" +
                      "WHERE T2.ID = ?\n" +
                      "\n" +
                      "ORDER BY T1.NOME");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                produto = new DTOProduto();
                
                produto.setNome(resultSet.getString(1));
                produto.setQuantidade(resultSet.getInt(2));
                produto.setPreco(resultSet.getDouble(3));
                produto.setId(resultSet.getInt(4));
             
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
