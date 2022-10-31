package br.com.hospede.model.DTO;

import java.net.URL;
import javax.swing.ImageIcon;


public class DTOUsuario {
    private int    id_usuario, alterado_por;
    private String login;
    private String senha;
    private String email;
    private String telefone;
    private String funcao;
    private ImageIcon editar, deletar;
    
   
    public DTOUsuario(int id_usuario, int alterado_por, String login, String senha, String email, String telefone, String funcao) { 
    
        this.id_usuario = id_usuario;
        this.alterado_por = alterado_por;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;
        this.funcao = funcao;
    
    
                    URL resourceEditar = getClass().getResource("/br/com/hospede/view/imagens/Editar.png");
                    editar =new ImageIcon(resourceEditar);
                    
                    
                    URL resourceDeletar = getClass().getResource("/br/com/hospede/view/imagens/Deletar.png");
                    deletar = new ImageIcon(resourceDeletar);
                    
    }
    
    public ImageIcon getEditar(){
        return editar;
    }
    
    public ImageIcon getDeletar(){
        return deletar;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getAlterado_por() {
        return alterado_por;
    }

    public void setAlterado_por(int alterado_por) {
        this.alterado_por = alterado_por;
    }

    
}
