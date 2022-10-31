
package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloProdutoPedido;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private ModeloProdutoPedido    modelo;
    private JLabel                 lblTitulo, lblProduto, lblQuantidade;
    private JPanel                 painel;
    private JButton                btnSelecionar;
    private ControlarEventos       eventos = null;
    public static ProdutoPedido    instance;
    private JTextField             txtProduto, txtQuantidade;
    
    public ProdutoPedido(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Selecionar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(300, 200, 690, 420);
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
        
        lblProduto = new JLabel("Produto");
        lblProduto.setSize(100, 30);
        lblProduto.setLocation(30, 305);
        
        lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setSize(100, 30);
        lblQuantidade.setLocation(300, 305);
        
        txtProduto = new JTextField();
        txtProduto.setSize(150, 30);
        txtProduto.setLocation(80, 305);
        
        txtQuantidade = new JTextField();
        txtQuantidade.setSize(100, 30);
        txtQuantidade.setLocation(380, 305);
        
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setSize(100,30);
        btnSelecionar.setLocation(20, 350);
        tabela.getTableHeader().setReorderingAllowed(false);
        
        add(painel);
        painel.add(scroll);
        add(lblProduto);
        add(lblQuantidade);
        add(txtProduto);
        add(txtQuantidade);
        add(btnSelecionar);
        
        botoes[59] = btnSelecionar;
        
        //Associando os botões a classe que controla eventos.
        getRootPane().setDefaultButton(btnSelecionar); //Deixa o botão Hospedar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnSelecionar.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
     
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                
                txtProduto.setText(modelo.getProduto(linha).getNome());
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
    }
    
   
    public DTOProduto getProduto(){
        DTOProduto produto = new DTOProduto();
        int linhaSelecionada = tabela.getSelectedRow();
        produto = modelo.getProduto(linhaSelecionada);
        return  produto;
    }
    
    public void limparDados(){
        txtProduto.setText("");
        txtQuantidade.setText("");
    }
    
    public int getQuantidade(){
        return Integer.parseInt(txtQuantidade.getText());
    }
    
    public int getLinhaSelecionada(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        return linha;
    }
    
    
    
    public static ProdutoPedido getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            instance = new ProdutoPedido(botoe, itensDeMenu, camposDeTexto,  usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
