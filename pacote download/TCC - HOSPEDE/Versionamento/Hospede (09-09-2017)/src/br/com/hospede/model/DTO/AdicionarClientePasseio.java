package br.com.hospede.model.DTO;

import java.net.URL;
import javax.swing.ImageIcon;


public class AdicionarClientePasseio {
    
    private int id, id_Passeio, id_Hospedagem;
    private String nome, cpf, responsavel, quarto, tituloPasseio;
    private ImageIcon editar, deletar;
    private Double ingresso;

    public AdicionarClientePasseio(){
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
    
    
    public int getId_Hospedagem() {
        return id_Hospedagem;
    }

    public void setId_Hospedagem(int id_Hospedagem) {
        this.id_Hospedagem = id_Hospedagem;
    }

    public int getId_Passeio() {
        return id_Passeio;
    }

    public void setId_Passeio(int id_Passeio) {
        this.id_Passeio = id_Passeio;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getQuarto() {
        return quarto;
    }

    public void setQuarto(String quarto) {
        this.quarto = quarto;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTituloPasseio() {
        return tituloPasseio;
    }

    public void setTituloPasseio(String tituloPasseio) {
        this.tituloPasseio = tituloPasseio;
    }

    public Double getIngresso() {
        return ingresso;
    }

    public void setIngresso(Double ingresso) {
        this.ingresso = ingresso;
    }
    
    
}
