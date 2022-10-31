package br.com.hospede.model.DTO;


import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class Reserva {
    
    private int    id_reserva, id_cliente, id_quarto, id_pagamento, dias_hospedados = 0;
    private String quantidade_de_criancas, quantidade_de_adultos, formaPagamento,tipo, nome_cliente, estado_cliente, cidade_cliente,
                   telefone_cliente, celular_cliente, email_cliente, numero_quarto;
    private Double totalPagar;
    private Date   entrada, saida;
    private ImageIcon editar, deletar;

    public Reserva() {
        
                    URL resourceEditar = getClass().getResource("/br/com/hospede/view/imagens/Editar.png");
                    editar =new ImageIcon(resourceEditar);
                    
                    
                    URL resourceDeletar = getClass().getResource("/br/com/hospede/view/imagens/Deletar.png");
                    deletar = new ImageIcon(resourceDeletar);
    }
    
    

    public ImageIcon getEditar() {
        return editar;
    }

    public void setEditar(ImageIcon editar) {
        this.editar = editar;
    }

    public ImageIcon getDeletar() {
        return deletar;
    }

    public void setDeletar(ImageIcon deletar) {
        this.deletar = deletar;
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
    

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getQuantidade_de_criancas() {
        return quantidade_de_criancas;
    }

    public void setQuantidade_de_criancas(String quantidade_de_criancas) {
        this.quantidade_de_criancas = quantidade_de_criancas;
    }

    public String getQuantidade_de_adultos() {
        return quantidade_de_adultos;
    }

    public void setQuantidade_de_adultos(String quantidade_de_adultos) {
        this.quantidade_de_adultos = quantidade_de_adultos;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(String estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public String getCidade_cliente() {
        return cidade_cliente;
    }

    public void setCidade_cliente(String cidade_cliente) {
        this.cidade_cliente = cidade_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getCelular_cliente() {
        return celular_cliente;
    }

    public void setCelular_cliente(String celular_cliente) {
        this.celular_cliente = celular_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getNumero_quarto() {
        return numero_quarto;
    }

    public void setNumero_quarto(String numero_quarto) {
        this.numero_quarto = numero_quarto;
    }

    public int getDias_hospedados() {
        return dias_hospedados;
    }

    public void setDias_hospedados(int dias_hospedados) {
        this.dias_hospedados = dias_hospedados;
    }
    
    

    public void totalPagar(Quarto quarto){
        //Quantidade de dias hospedados.
        DateTime entrada = new DateTime(getEntrada());
        DateTime saida = new DateTime(getSaida());
        dias_hospedados = Days.daysBetween(entrada, saida).getDays();
        
        //Multiplica os dias hospedados com o preço da diário do quarto, passado por parâmetro.
         try{
        totalPagar = Double.parseDouble(quarto.getDiaria()) * dias_hospedados;
         }catch(Exception erro){
             
         }
    }
}
