/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospede.model.dto;

import br.com.hospede.model.modeloTabela.ModeloPasseiosRealizados;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Tomé
 */
public class DTOPasseio {
    
    private int                      id;
    private String                   titulo, descricao, status;
    private Date                     Data;
    private Double                   ingresso;
    private ImageIcon                editar, deletar, cancelar;
    private ModeloPasseiosRealizados modeloPasseiosRealizados;
    
    public DTOPasseio(){
        URL resourceEditar = getClass().getResource("/br/com/hospede/view/imagens/Editar.png");
                    editar =new ImageIcon(resourceEditar);
                    
                    
                    URL resourceDeletar = getClass().getResource("/br/com/hospede/view/imagens/FecharPasseio.png");
                    deletar = new ImageIcon(resourceDeletar);
                    
                    URL resourceCancelar = getClass().getResource("/br/com/hospede/view/imagens/Close.png");
                    cancelar = new ImageIcon(resourceCancelar);
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

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public Double getIngresso() {
        return ingresso;
    }

    public void setIngresso(Double ingresso) {
        this.ingresso = ingresso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean verificarCPF_Passaporte(String cpf){
                return false;
    }

    public ImageIcon getCancelar() {
        return cancelar;
    }

    public void setCancelar(ImageIcon cancelar) {
        this.cancelar = cancelar;
    }
    
    
    
    public double totalPagar(){
        Double totalPagar = 0.0;
        modeloPasseiosRealizados = ModeloPasseiosRealizados.getInstance();
        
        for (int posicao = 0; posicao<modeloPasseiosRealizados.getPassageiros().size() ;posicao++){
            totalPagar += modeloPasseiosRealizados.getPassageiro(posicao).getIngresso();
        }
        
        return totalPagar;
    }
}
