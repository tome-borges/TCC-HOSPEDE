package br.com.hospede.model.dto;

import java.util.Date;

public class DTOHistoricoProduto {
    
    private int id, id_produto, id_usuario_responsavel, id_cliente;
    private String acao, usuarioResponsavel, produto;
    private Date data_Acao;



    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(int id_usuario_responsavel) {
        this.id_usuario_responsavel = id_usuario_responsavel;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getData_Acao() {
        return data_Acao;
    }

    public void setData_Acao(Date data_Acao) {
        this.data_Acao = data_Acao;
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
    
}
