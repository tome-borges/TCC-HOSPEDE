package br.com.hospede.model.dto;

import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;


public class DTOQuarto {

   
    private int       id_quarto;
    private String    situacao,ocupantes, categoria, diaria, numero, nome_cliente;
    private ImageIcon iconeDeSituacao;
    private Date    entrada, saida;
    private ImageIcon editar, deletar;

    public DTOQuarto(int id_quarto, String situacao, String ocupantes, String categoria, String diaria, String numero, String nome_cliente, ImageIcon iconeDeSituacao, Date entrada) {
        this.id_quarto = id_quarto;
        this.situacao = situacao;
        this.ocupantes = ocupantes;
        this.categoria = categoria;
        this.diaria = diaria;
        this.numero = numero;
        this.nome_cliente = nome_cliente;
        this.iconeDeSituacao = iconeDeSituacao;
        this.entrada = entrada;
        
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
    
    
    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(String ocupantes) {
        this.ocupantes = ocupantes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDiaria() {
        return diaria;
    }

    public void setDiaria(String diaria) {
        this.diaria = diaria;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ImageIcon getIconeDeSituacao() {
        return iconeDeSituacao;
    }

    public void setIconeDeSituacao(ImageIcon iconeDeSituacao) {
        this.iconeDeSituacao = iconeDeSituacao;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
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
