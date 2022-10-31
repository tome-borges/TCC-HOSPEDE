package br.com.hospede.model.dto;

import java.util.Date;

public class DTOHistoricoPasseio {
    
    private int id_usuario_responsavel, id_passeio, id_cliente;
    private Date data_registro;
    private String acao, usuarioResponsavel, passeio;

   

    public int getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(int id_usuario_responsavel) {
        this.id_usuario_responsavel = id_usuario_responsavel;
    }

    public int getId_passeio() {
        return id_passeio;
    }

    public void setId_passeio(int id_passeio) {
        this.id_passeio = id_passeio;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public String getPasseio() {
        return passeio;
    }

    public void setPasseio(String passeio) {
        this.passeio = passeio;
    }
    
    
    
}
