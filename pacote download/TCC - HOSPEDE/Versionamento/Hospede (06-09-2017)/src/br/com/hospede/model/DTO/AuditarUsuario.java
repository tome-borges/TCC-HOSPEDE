package br.com.hospede.model.DTO;

import java.util.Date;

public class AuditarUsuario {
    
    private int    id_usuario_responsavel;
    private String login_usuario_responsavel, acao, data_acao, login_usuario_alterado;
    private Date   dt_acao;

    public int getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(int id_usuario_responsavel) {
        this.id_usuario_responsavel = id_usuario_responsavel;
    }

    public String getLogin_usuario_responsavel() {
        return login_usuario_responsavel;
    }

    public void setLogin_usuario_responsavel(String login_usuario_responsavel) {
        this.login_usuario_responsavel = login_usuario_responsavel;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getData_acao() {
        return data_acao;
    }

    public void setData_acao(String data_acao) {
        this.data_acao = data_acao;
    }

    public String getLogin_usuario_alterado() {
        return login_usuario_alterado;
    }

    public void setLogin_usuario_alterado(String login_usuario_alterado) {
        this.login_usuario_alterado = login_usuario_alterado;
    }

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }
    
    
}
