package br.com.hospede.model.dto;

import br.com.hospede.model.modeloTabela.ModeloPedidosDeQuarto;
import br.com.hospede.model.modeloTabela.ModeloProdutosConsumidos;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;

public class DTOProduto {
    
    private int                      id, quantidade;
    private String                   nome;
    private Date                     entrega;
    private Double                   preco;
    private ImageIcon                editar, deletar;
    private ModeloProdutosConsumidos modeloProdutosConsumidos;

    public DTOProduto(){
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
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
    public double totalPagar(){
        Double totalPagar = 0.0;
         modeloProdutosConsumidos = ModeloProdutosConsumidos.getInstance();
        
        for (int posicao = 0; posicao<modeloProdutosConsumidos.getProdutos().size() ;posicao++){
            totalPagar += modeloProdutosConsumidos.getProduto(posicao).getPreco() * modeloProdutosConsumidos.getProduto(posicao).getQuantidade();
        }
        
        return totalPagar;
    }
    
}
