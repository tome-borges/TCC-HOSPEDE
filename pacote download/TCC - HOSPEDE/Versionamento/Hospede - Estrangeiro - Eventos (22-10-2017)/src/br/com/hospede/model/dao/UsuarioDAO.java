package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private ConectarBanco     banco;
    private PreparedStatement statement;
    private ResultSet         resultSet;
    public static UsuarioDAO  instance= null;

    
    public boolean cadastrarUsuario(DTOUsuario usuario){
        banco = ConectarBanco.getInstance();
        banco.conectar();
       boolean cadastrou = false;
        try{
           statement = banco.setStatement("INSERT INTO usuario( login, email, telefone, funcao, senha) VALUES ( ?, ?, ?, ?, ?)");
           statement.setString(1, usuario.getLogin());
           statement.setString(2, usuario.getEmail());
           statement.setString(3, usuario.getTelefone());
           statement.setString(4, usuario.getFuncao());
           statement.setString(5, usuario.getSenha());
           
           statement.execute();
           JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
           cadastrou = true;
        }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário:\n" + erro.getMessage());
        }
        banco.desconectar();
        return cadastrou;
    }
    
    //---------------------------------------------------------------------------------------------------
    
    public DTOUsuario consultarUsuario(String login) {//Pesquisa um usuÃ¡rio no banco de dados.
        DTOUsuario usuario= new DTOUsuario(0,0,"","","","","");
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT * FROM usuario WHERE login = ?");
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                usuario = new DTOUsuario(0,0,"","","","","");
                
                usuario.setLogin(login);
                usuario.setEmail(resultSet.getString(3));
                usuario.setFuncao(resultSet.getString(5));
                usuario.setSenha(resultSet.getString(4));
                usuario.setTelefone(resultSet.getString(6));
                usuario.setId_usuario(resultSet.getInt(1));
                
                
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuário:\n" + erro.getMessage());
        }
        banco.desconectar();
        return usuario;
    }
    
    
    public boolean editarUsuario(DTOUsuario usuario){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try{
            statement = banco.setStatement("UPDATE usuario SET login=?, email=?, telefone=?,"
                    + " senha=?, funcao = ? WHERE id= ?");
            
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getEmail());
            statement.setString(3,usuario.getTelefone());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getFuncao());
            statement.setInt(6, usuario.getId_usuario());
            
            statement.execute();
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
            editou = true;
        }catch(Exception erro){
               JOptionPane.showMessageDialog(null, "Erro ao editar usuário:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return editou;
    }
    
    //----------------------------------------------------------------------------------------------------------------
    
    public boolean deletarUsuario(String login){
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try{
            statement = banco.setStatement("DELETE FROM usuario WHERE login = ?");
            statement.setString(1, login);
            
            statement.execute();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
            deletou = true;
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao deletar usuário:\n" + erro.getMessage());
        }
                
        banco.desconectar();
        return deletou;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    
    public ArrayList<DTOUsuario> listarUsuario(){
        
        DTOUsuario usuario;
        ArrayList<DTOUsuario> listaUsuario = new ArrayList<DTOUsuario>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT * FROM usuario");
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                usuario = new DTOUsuario(0,0,"","","","","");
                
                usuario.setLogin(resultSet.getString(2));
                usuario.setEmail(resultSet.getString(3));
                usuario.setFuncao(resultSet.getString(5));
                usuario.setSenha(resultSet.getString(4));
                usuario.setTelefone(resultSet.getString(6));
                usuario.setId_usuario(resultSet.getInt(1));
                
                listaUsuario.add(usuario);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários:\n" + erro.getMessage());
        }
        
        banco.desconectar();
        return listaUsuario;
    }
    
    //----------------------------------------------------------------------------------------------------------------
    
    public static UsuarioDAO getInstance(){
        if(instance == null){
            instance = new UsuarioDAO();
        }
        return instance;
    }
}
