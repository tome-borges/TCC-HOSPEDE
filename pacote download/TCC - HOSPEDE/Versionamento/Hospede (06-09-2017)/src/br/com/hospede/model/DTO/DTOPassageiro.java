package br.com.hospede.model.DTO;

import java.net.URL;
import javax.swing.ImageIcon;

public class DTOPassageiro {
    
    private int id, id_passeio, id_hospedagem_responsavel;
    private String nome_passageiro, cpf;
    private ImageIcon editar, deletar;

    public DTOPassageiro (){
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
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_passeio() {
        return id_passeio;
    }

    public void setId_passeio(int id_passeio) {
        this.id_passeio = id_passeio;
    }

    public int getId_hospedagem_responsavel() {
        return id_hospedagem_responsavel;
    }

    public void setId_hospedagem_responsavel(int id_hospedagem_responsavel) {
        this.id_hospedagem_responsavel = id_hospedagem_responsavel;
    }

    public String getNome_passageiro() {
        return nome_passageiro;
    }

    public void setNome_passageiro(String nome_passageiro) {
        this.nome_passageiro = nome_passageiro;
    }
    
    
}