package br.com.hospede.model.DTO;

import java.util.Date;

public class AuditarHospedagem {
    private int    id_usuario_responsavel, id_manter_reserva,id_hospedagem;
    private String login, acao,cliente_hospedado, tipo_reserva;
    private Date   dt_acao;

    public int getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(int id_usuario_responsavel) {
        this.id_usuario_responsavel = id_usuario_responsavel;
    }

    public int getId_manter_reserva() {
        return id_manter_reserva;
    }

    public void setId_manter_reserva(int id_manter_reserva) {
        this.id_manter_reserva = id_manter_reserva;
    }

    public int getId_hospedagem() {
        return id_hospedagem;
    }

    public void setId_hospedagem(int id_hospedagem) {
        this.id_hospedagem = id_hospedagem;
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

    public String getCliente_hospedado() {
        return cliente_hospedado;
    }

    public void setCliente_hospedado(String cliente_hospedado) {
        this.cliente_hospedado = cliente_hospedado;
    }

    public String getTipo_reserva() {
        return tipo_reserva;
    }

    public void setTipo_reserva(String tipo_reserva) {
        this.tipo_reserva = tipo_reserva;
    }

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }
    
    
}
