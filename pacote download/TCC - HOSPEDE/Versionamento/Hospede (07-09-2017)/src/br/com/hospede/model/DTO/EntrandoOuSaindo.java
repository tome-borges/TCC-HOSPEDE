package br.com.hospede.model.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

public class EntrandoOuSaindo {
    
    private String           nomeDoCliente, data,numeroDoQuarto, celular;
    private ImageIcon        imgSituacao;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private Date             dataAtual = new Date();
    private Date             entrada, saida;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ImageIcon getImgSituacao() {
        return imgSituacao;
    }

    public void setImgSituacao(ImageIcon imgSituacao) {
        this.imgSituacao = imgSituacao;
    }


    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public String getNumeroDoQuarto() {
        return numeroDoQuarto;
    }

    public void setNumeroDoQuarto(String numeroDoQuarto) {
        this.numeroDoQuarto = numeroDoQuarto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
