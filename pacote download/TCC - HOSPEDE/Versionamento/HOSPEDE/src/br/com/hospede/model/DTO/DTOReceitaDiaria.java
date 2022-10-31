package br.com.hospede.model.dto;

import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;

public class DTOReceitaDiaria {
    
    private int id, id_reserva;
    private String status, cliente, numeroQuarto;
    private Date entrada, saida, dt_echamento;
    private double saldo;
    private ImageIcon consumo;
    
    public DTOReceitaDiaria(){
        URL resourceDeletar = getClass().getResource("/br/com/hospede/view/imagens/List.png");
                   consumo = new ImageIcon(resourceDeletar);
     }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(String numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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

    public Date getDt_fechamento() {
        return dt_echamento;
    }

    public void setDt_fechamento(Date dt_echamento) {
        this.dt_echamento = dt_echamento;
    }

    public ImageIcon getConsumo() {
        return consumo;
    }

    public void setConsumo(ImageIcon consumo) {
        this.consumo = consumo;
    }
    
    
}