package br.com.hospede.model.DTO;

import java.util.Date;

public class AuditarCliente {
    
    private int    id_usuario;
    private String login, acao, nome_cliente;
    private Date   dt_acao;

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

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }
    
    
}
