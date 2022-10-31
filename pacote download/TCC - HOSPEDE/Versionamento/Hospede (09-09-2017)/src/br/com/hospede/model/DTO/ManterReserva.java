package br.com.hospede.model.DTO;

import java.util.Date;

public class ManterReserva {
    
    private int    id_usuario_responsavel, id_manter_reserva,id_reserva;
    private String login, acao,cliente_reservado, tipo_reserva;
    private Date   dt_acao;

    public int getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(int id_usuario) {
        this.id_usuario_responsavel = id_usuario;
    }

    public int getId_manter_reserva() {
        return id_manter_reserva;
    }

    public void setId_manter_reserva(int id_manter_reserva) {
        this.id_manter_reserva = id_manter_reserva;
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

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getCliente_reservado() {
        return cliente_reservado;
    }

    public void setCliente_reservado(String cliente_reservado) {
        this.cliente_reservado = cliente_reservado;
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
