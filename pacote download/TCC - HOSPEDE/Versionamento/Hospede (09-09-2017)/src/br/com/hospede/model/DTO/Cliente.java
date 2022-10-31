package br.com.hospede.model.DTO;

import java.net.URL;
import javax.swing.ImageIcon;

public class Cliente {
    
    private int    id_cliente, cadastrado_por;
    private String nome, cidade, estado, endereco, bairro, complemento, email,
                   cpf, rg, cep, telefone, celular;
    private ImageIcon editar, deletar;

    public Cliente(int id_cliente, int cadastrado_por, String nome, String cidade, String estado, String endereco, String bairro, String complemento, String email, String cpf, String rg, String cep, String telefone, String celular) {
        this.id_cliente = id_cliente;
        this.cadastrado_por = cadastrado_por;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.endereco = endereco;
        this.bairro = bairro;
        this.complemento = complemento;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.cep = cep;
        this.telefone = telefone;
        this.celular = celular;
        
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
    
    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getCadastrado_por() {
        return cadastrado_por;
    }

    public void setCadastrado_por(int cadastrado_por) {
        this.cadastrado_por = cadastrado_por;
    }

    
    
}
