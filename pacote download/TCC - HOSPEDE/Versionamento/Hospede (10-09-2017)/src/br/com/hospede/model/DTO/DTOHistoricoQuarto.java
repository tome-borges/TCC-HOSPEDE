package br.com.hospede.model.DTO;

import java.util.Date;

public class DTOHistoricoQuarto {
    
    private DTOHistoricoQuarto     id_manter;
    private int                 id_usuario, id_quarto, numero_quarto;
    private String              acao, login;
    private Date                dt_acao;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    

    public DTOHistoricoQuarto getId_manter() {
        return id_manter;
    }

    public void setId_manter(DTOHistoricoQuarto id_manter) {
        this.id_manter = id_manter;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getNumero_quarto() {
        return numero_quarto;
    }

    public void setNumero_quarto(int numero_quarto) {
        this.numero_quarto = numero_quarto;
    }

    public Date getDt_acao() {
        return dt_acao;
    }

    public void setDt_acao(Date dt_acao) {
        this.dt_acao = dt_acao;
    }
 
    
}
