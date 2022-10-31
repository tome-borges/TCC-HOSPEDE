package br.com.hospede.model.dto;

import java.util.Date;

public class DTOHistoricoUsuario {
    
    private int    id_manter_usuario, id_usuario_reponsavel, id_suario_alterado;
    private String acao, usuario_alterado, data_acao, login_usuario_responsavel, login_usuario_alterado;;
    private Date   dt_acao;

    public int getId_manter_usuario() {
        return id_manter_usuario;
    }

    public void setId_manter_usuario(int id_manter_usuario) {
        this.id_manter_usuario = id_manter_usuario;
    }

    public int getId_usuario_reponsavel() {
        return id_usuario_reponsavel;
    }

    public void setId_usuario_reponsavel(int id_usuario_reponsavel) {
        this.id_usuario_reponsavel = id_usuario_reponsavel;
    }

    public int getId_suario_alterado() {
        return id_suario_alterado;
    }

    public void setId_suario_alterado(int id_suario_alterado) {
        this.id_suario_alterado = id_suario_alterado;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getUsuario_alterado() {
        return usuario_alterado;
    }

    public void setUsuario_alterado(String usuario_alterado) {
        this.usuario_alterado = usuario_alterado;
    }

    public String getData_acao() {
        return data_acao;
    }

    public void setData_acao(String data_acao) {
        this.data_acao = data_acao;
    }

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }

    public String getLogin_usuario_responsavel() {
        return login_usuario_responsavel;
    }

    public void setLogin_usuario_responsavel(String login_usuario_responsavel) {
        this.login_usuario_responsavel = login_usuario_responsavel;
    }

    public String getLogin_usuario_alterado() {
        return login_usuario_alterado;
    }

    public void setLogin_usuario_alterado(String login_usuario_alterado) {
        this.login_usuario_alterado = login_usuario_alterado;
    }
    
    
}
