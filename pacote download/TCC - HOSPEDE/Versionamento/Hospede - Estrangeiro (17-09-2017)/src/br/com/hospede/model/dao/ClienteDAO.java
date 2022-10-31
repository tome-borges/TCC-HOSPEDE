package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static ClienteDAO  instance= null;
    
    public boolean cadastrarCliente(DTOCliente cliente){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean cadastrou = false;
        try{
            statement = banco.setStatement("INSERT INTO cliente (nome, rg, endereco, bairro, cidade, celular, email,"
                    +                      "telefone, cpf, estado, cep, passaporte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getRg());
            statement.setString(3, cliente.getEndereco());
            statement.setString(4, cliente.getBairro());
            statement.setString(5, cliente.getCidade());
            statement.setString(6, cliente.getCelular());
            statement.setString(7, cliente.getEmail());
            statement.setString(8, cliente.getTelefone());
            statement.setString(9, cliente.getCpf());
            statement.setString(10, cliente.getEstado());
            statement.setString(11, cliente.getCep());
            statement.setString(12, cliente.getPassaport());
            
            statement.execute();
            cadastrou = true; //Para salvar a ação feita na tabela "manter_cliente" que controla as ações do usuario nos clientes.
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return cadastrou;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------
    
    public DTOCliente consultarCliente(DTOCliente cliente){
        DTOCliente clienteRetornar = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT *FROM cliente WHERE cpf = ?");
            
            statement.setString(1, cliente.getCpf());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                
                clienteRetornar.setId_cliente(resultSet.getInt(1));
                clienteRetornar.setNome(resultSet.getString(2));
                clienteRetornar.setRg(resultSet.getString(3));
                clienteRetornar.setEndereco(resultSet.getString(4));
                clienteRetornar.setBairro(resultSet.getString(5));
                clienteRetornar.setCidade(resultSet.getString(6));
                clienteRetornar.setCelular(resultSet.getString(7));
                clienteRetornar.setEmail(resultSet.getString(8));
                clienteRetornar.setTelefone(resultSet.getString(9));
                clienteRetornar.setCpf(resultSet.getString(10));
                clienteRetornar.setEstado(resultSet.getString(11));
                clienteRetornar.setCep(resultSet.getString(12));
                clienteRetornar.setPassaport(resultSet.getString(13));
                   
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return clienteRetornar;
    }
    //----------------------------------------------------------------------------------------------------------------------
    public DTOCliente consultarClientePassaporte(DTOCliente cliente){
        DTOCliente clienteRetornar = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT *FROM cliente WHERE passaporte = ?");
            
            statement.setString(1, cliente.getPassaport());
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                
                clienteRetornar.setId_cliente(resultSet.getInt(1));
                clienteRetornar.setNome(resultSet.getString(2));
                clienteRetornar.setRg(resultSet.getString(3));
                clienteRetornar.setEndereco(resultSet.getString(4));
                clienteRetornar.setBairro(resultSet.getString(5));
                clienteRetornar.setCidade(resultSet.getString(6));
                clienteRetornar.setCelular(resultSet.getString(7));
                clienteRetornar.setEmail(resultSet.getString(8));
                clienteRetornar.setTelefone(resultSet.getString(9));
                clienteRetornar.setCpf(resultSet.getString(10));
                clienteRetornar.setEstado(resultSet.getString(11));
                clienteRetornar.setCep(resultSet.getString(12));
                clienteRetornar.setPassaport(resultSet.getString(13));
                   
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return clienteRetornar;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------
     public DTOCliente consultarClienteID(int id_cliente){
        DTOCliente clienteRetornar = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT *FROM cliente WHERE id = ?");
            
            statement.setInt(1, id_cliente);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                
                clienteRetornar.setId_cliente(resultSet.getInt(1));
                clienteRetornar.setNome(resultSet.getString(2));
                clienteRetornar.setRg(resultSet.getString(3));
                clienteRetornar.setEndereco(resultSet.getString(4));
                clienteRetornar.setBairro(resultSet.getString(5));
                clienteRetornar.setCidade(resultSet.getString(6));
                clienteRetornar.setCelular(resultSet.getString(7));
                clienteRetornar.setEmail(resultSet.getString(8));
                clienteRetornar.setTelefone(resultSet.getString(9));
                clienteRetornar.setCpf(resultSet.getString(10));
                clienteRetornar.setEstado(resultSet.getString(11));
                clienteRetornar.setCep(resultSet.getString(12));
                clienteRetornar.setPassaport(resultSet.getString(13));
                   
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return clienteRetornar;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------
    
    public ArrayList<DTOCliente> listarCliente(){
        ArrayList<DTOCliente> clienteLista = new ArrayList<DTOCliente>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT * FROM cliente");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setNome(resultSet.getString(2));
                cliente.setRg(resultSet.getString(3));
                cliente.setEndereco(resultSet.getString(4));
                cliente.setBairro(resultSet.getString(5));
                cliente.setCidade(resultSet.getString(6));
                cliente.setCelular(resultSet.getString(7));
                cliente.setEmail(resultSet.getString(8));
                cliente.setTelefone(resultSet.getString(9));
                cliente.setCpf(resultSet.getString(10));
                cliente.setEstado(resultSet.getString(11));
                cliente.setCep(resultSet.getString(12));
                cliente.setPassaport(resultSet.getString(13));
                
                clienteLista.add(cliente);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return clienteLista;
    }
    
    public ArrayList<DTOCliente> ConsultarClienteNome(DTOCliente clientePesquisar){
        ArrayList<DTOCliente> clienteLista = new ArrayList<DTOCliente>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT * FROM cliente WHERE nome like ?");
            statement.setString(1, clientePesquisar.getNome()+"%");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setNome(resultSet.getString(2));
                cliente.setRg(resultSet.getString(3));
                cliente.setEndereco(resultSet.getString(4));
                cliente.setBairro(resultSet.getString(5));
                cliente.setCidade(resultSet.getString(6));
                cliente.setCelular(resultSet.getString(7));
                cliente.setEmail(resultSet.getString(8));
                cliente.setTelefone(resultSet.getString(9));
                cliente.setCpf(resultSet.getString(10));
                cliente.setEstado(resultSet.getString(11));
                cliente.setCep(resultSet.getString(12));
                cliente.setPassaport(resultSet.getString(13));
                
                clienteLista.add(cliente);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return clienteLista;
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------------
    public boolean editarCliente(DTOCliente cliente){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou =false;
        try{
            statement = banco.setStatement("UPDATE cliente SET nome = ?, rg = ?, endereco = ?, bairro = ?,"
                    + "cidade = ?, celular = ?, email = ?, telefone = ?, cpf = ?,"
                    + "cep = ?, passaporte = ?, estado = ? WHERE cpf = ?");
            
             
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getRg());
            statement.setString(3, cliente.getEndereco());
            statement.setString(4, cliente.getBairro());
            statement.setString(5, cliente.getCidade());
            statement.setString(6, cliente.getCelular());
            statement.setString(7, cliente.getEmail());
            statement.setString(8, cliente.getTelefone());
            statement.setString(9, cliente.getCpf());
            statement.setString(10, cliente.getCep());
            statement.setString(11, cliente.getPassaport());
            statement.setString(12, cliente.getEstado());
            statement.setString(13, cliente.getCpf());
            
            
            
            statement.execute();
            JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
            editou = true; //Para salvar a ação feita na tabela "manter_cliente" que controla as ações do usuario nos clientes.
        }catch(Exception erro){
          JOptionPane.showMessageDialog(null, "Erro ao editar clientes:\n"+erro.getMessage());  
        } 
        
        banco.desconectar();
        return editou;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------
    public boolean deletarCliente(String cpf){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try{
            statement = banco.setStatement("DELETE FROM cliente WHERE cpf = ?");
            statement.setString(1, cpf);
            
            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
            deletou = true; //Para salvar a ação feita na tabela "manter_cliente" que controla as ações do usuario nos clientes.
        }catch(Exception erro){
           JOptionPane.showMessageDialog(null, "Erro ao deletar clientes:\n"+erro.getMessage());   
        }
                
        banco.desconectar();
        return deletou;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------
    public DTOCliente consultarClientePorId(int id){
        DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        banco = ConectarBanco.getInstance();
        banco.conectar();
        
        try{
            statement = banco.setStatement("SELECT *FROM cliente WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                
                cliente.setId_cliente(resultSet.getInt(1));
                cliente.setNome(resultSet.getString(2));
                cliente.setRg(resultSet.getString(3));
                cliente.setEndereco(resultSet.getString(4));
                cliente.setBairro(resultSet.getString(5));
                cliente.setCidade(resultSet.getString(6));
                cliente.setCelular(resultSet.getString(7));
                cliente.setEmail(resultSet.getString(8));
                cliente.setTelefone(resultSet.getString(9));
                cliente.setCpf(resultSet.getString(10));
                cliente.setEstado(resultSet.getString(11));
                cliente.setCep(resultSet.getString(12));
                cliente.setPassaport(resultSet.getString(13));
                   
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao consultar cliente por ID:\n"+erro.getMessage());
        }
        
        banco.desconectar();
        return cliente;
    }
    
    //-------------------------------------------------------//----------------------------------------------------------------------------------
    
    public static ClienteDAO getInstance(){
        if(instance == null){
            instance = new ClienteDAO();
        }
        return instance;
    }
}
