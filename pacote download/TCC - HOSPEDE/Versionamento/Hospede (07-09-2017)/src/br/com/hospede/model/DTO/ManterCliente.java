package br.com.hospede.model.DTO;

import java.util.Date;

public class ManterCliente {
    
    private ManterQuarto        id_manter;
    private int                 id_usuario, id_cliente;
    private String              acao, nome_cliente;
    private Date                dt_acao;

    public ManterQuarto getId_manter() {
        return id_manter;
    }

    public void setId_manter(ManterQuarto id_manter) {
        this.id_manter = id_manter;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
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
