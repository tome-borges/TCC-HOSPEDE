package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOProduto;
import br.com.hospede.model.DTO.Usuario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DeletarProduto extends InterfaceFactory{
    
    private JTextField    txtNome, txtQuantidade, txtPreco, txtDataEntrada;
    private JTextField    dataEntrada;
    private JLabel        lblNome, lblQuantidade, lblPreco, lblDataEntrada;
    private JButton       btnDeletar;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static DeletarProduto instance = null;
    private ControlarEventos       eventos;
    private SimpleDateFormat    formatarData = new SimpleDateFormat("dd/MM/yyyy");
    
    public DeletarProduto(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Deletar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setLayout(null);
        setResizable(false);
        
         //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 400);
        painel.setLocation(30, 30);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        lblNome = new JLabel("Nome");
        lblNome.setSize(50, 20);
        lblNome.setLocation(50, 50);
        
        txtNome = new JTextField();
        txtNome.setSize(250, 20);
        txtNome.setLocation(90, 50);
        txtNome.setEnabled(true);
        
        lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setSize(70, 20);
        lblQuantidade.setLocation(15, 140);
        
        txtQuantidade = new JTextField();
        txtQuantidade.setSize(50, 20);
        txtQuantidade.setLocation(90, 140);
        txtQuantidade.setEnabled(true);
        
        lblDataEntrada = new JLabel("Data entrada");
        lblDataEntrada.setSize(100, 20);
        lblDataEntrada.setLocation(10, 110);
        
        dataEntrada = new JTextField();
        dataEntrada.setSize(130, 20);
        dataEntrada.setLocation(90, 110);
        dataEntrada.setEnabled(true);
        
        lblPreco = new JLabel("Preço R$");
        lblPreco.setSize(100, 20);
        lblPreco.setLocation(30, 80);
        
        txtPreco = new JTextField();
        txtPreco.setSize(100, 20);
        txtPreco.setLocation(90, 80);
        txtPreco.setEnabled(true);
       
        
        
        btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(100, 20);
        btnDeletar.setLocation(50, 350);
        
        //Adicionando componentes no painel.
        add(painel);
        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblQuantidade);
        painel.add(txtQuantidade);
        painel.add(lblDataEntrada);
        painel.add(dataEntrada);
        painel.add(lblPreco);
        painel.add(txtPreco);
        painel.add(btnDeletar);
        
        campoDeTexto[16] = txtNome;
        botoes[49] = btnDeletar;

        getRootPane().setDefaultButton(btnDeletar); //Deixa o botão Editar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnDeletar.addActionListener(eventos);
        txtNome.addFocusListener(eventos);
    }
    
    public DTOProduto getProduto(){
        DTOProduto produto = new DTOProduto();
        produto.setNome(txtNome.getText());
        try{
        produto.setPreco(Double.parseDouble(txtPreco.getText()));
        produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        }catch(Exception erro){
            
        }
        
        
        return produto;
        
    }
    
    public void receberProduto(DTOProduto produto){
        txtNome.setText(produto.getNome());
        
        try{
            txtPreco.setText(Double.toString(produto.getPreco()));
             dataEntrada.setText(formatarData.format(produto.getEntrega()));
              txtQuantidade.setText(Integer.toString(produto.getQuantidade()));
        }catch(Exception erro){
           
        }
       
        
    }
    
    public void limparCampos(){
        txtNome.setText("");
        txtQuantidade.setText("");
        dataEntrada.setText("");
        txtPreco.setText("");
    }
    
    public static DeletarProduto getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            instance = new DeletarProduto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
