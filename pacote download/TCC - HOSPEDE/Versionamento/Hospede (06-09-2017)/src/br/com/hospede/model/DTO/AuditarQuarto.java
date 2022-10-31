package br.com.hospede.model.DTO;

import java.util.Date;

public class AuditarQuarto {
    
    private int    id_usuario, numero;
    private String login, acao;
    private Date dt_acao;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_quarto) {
        this.id_usuario = id_quarto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }

    
}
