
package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOProduto;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.modeloTabela.ModeloProdutoPedido;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ProdutoPedido extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloProdutoPedido          modelo;
    private JLabel                 lblTitulo;
    private JPanel                 painel;
    private JButton                btnSelecionar;
    private ControlarEventos       eventos = null;
    public static ProdutoPedido      instance;
    
    public ProdutoPedido(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, Usuario usuarioLogado){
        setTitle("Selecionar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(480, 200, 690, 380);
        setLayout(null);
        setResizable(false);
        
        painel = new JPanel();
        painel.setSize(630, 270);
        painel.setLocation(20, 20);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        modelo =  ModeloProdutoPedido.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(620, 260);
        scroll.setLocation(5, 5);
        
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setSize(100,20);
        btnSelecionar.setLocation(20, 300);
        
        add(painel);
        painel.add(scroll);
        add(btnSelecionar);
        
        botoes[59] = btnSelecionar;
        
        //Associando os botões a classe que controla eventos.
        getRootPane().setDefaultButton(btnSelecionar); //Deixa o botão Hospedar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnSelecionar.addActionListener(eventos);
        
    }
    
   
    public DTOProduto getProduto(){
        DTOProduto produto = new DTOProduto();
        int linhaSelecionada = tabela.getSelectedRow();
        produto = modelo.getProduto(linhaSelecionada);
        return  produto;
    }
    
    public static ProdutoPedido getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, Usuario usuarioLogado){
        if(instance == null){
            instance = new ProdutoPedido(botoe, itensDeMenu, camposDeTexto,  usuarioLogado);
        }
        return instance;
    }
}
