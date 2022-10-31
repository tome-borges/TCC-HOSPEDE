package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloPedidosDeQuarto;
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
import javax.swing.text.MaskFormatter;

public class CadastrarPedidoQuarto extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloPedidosDeQuarto  modelo;
    private JButton                btnCadastrar, btnAdicionar, btnRemover;
    private JLabel                 lblNome, lblQuarto, lblCelular;
    private JTextField             txtNome, txtQuarto, txtCelular;
    private JPanel                 painel;
    public static CadastrarPedidoQuarto instance = null;
    private ControlarEventos       eventos;
    private DTOReserva reserva;
    private MaskFormatter          mascararCPF;
    
    public CadastrarPedidoQuarto(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Pedidos de Quarto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(265, 70, 795, 610);
        setLayout(null);
        setResizable(false);
        
        painel = new JPanel();
        painel.setSize(780, 570);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        modelo =  ModeloPedidosDeQuarto.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(700, 220);
        scroll.setLocation(30, 220);
        tabela.getColumnModel().getColumn(0).setMaxWidth(350);
        tabela.getColumnModel().getColumn(1).setMaxWidth(250);
        tabela.getColumnModel().getColumn(2).setMaxWidth(100);
        
        lblQuarto = new JLabel("*Quarto:");
        lblQuarto.setSize(90, 20);
        lblQuarto.setLocation(15, 50);
        lblQuarto.setForeground(Color.red);
        
        txtQuarto = new JTextField();
        txtQuarto.setSize(70, 30);
        txtQuarto.setLocation(60, 50);
     
        
        lblNome = new JLabel("Nome: ");
        lblNome.setSize(100, 20);
        lblNome.setLocation(20, 100);
        
        txtNome = new JTextField();
        txtNome.setSize(300, 30);
        txtNome.setLocation(60, 100); 
        txtNome.setEditable(false);
                
        lblCelular = new JLabel("Celular:");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(390, 100);
        
        txtCelular = new JTextField();
        txtCelular.setSize(150, 30);
        txtCelular.setLocation(450, 100);
        txtCelular.setEditable(false);
                
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 30);
        btnCadastrar.setLocation(30, 530);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setSize(100, 30);
        btnAdicionar.setLocation(30, 180);
        
        btnRemover = new JButton("Remover");
        btnRemover.setSize(100, 20);
        btnRemover.setLocation(180, 240);
        
        add(painel);
        painel.add(lblNome);
        painel.add(lblQuarto);
        painel.add(lblCelular);
        painel.add(txtNome);
        painel.add(txtQuarto);
        painel.add(txtCelular);
        painel.add(btnAdicionar);
        painel.add(btnCadastrar);
        painel.add(scroll);

       botoes[58] = btnAdicionar;
       botoes[61] = btnCadastrar;
       camposDeTexto[19]= txtQuarto;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        txtQuarto.addFocusListener(eventos);
        btnAdicionar.addActionListener(eventos);
        btnCadastrar.addActionListener(eventos);
        btnRemover.addActionListener(eventos);
    }
    
   public DTOQuarto getQuarto(){
     return new DTOQuarto(0, "", "", "", "", txtQuarto.getText(), "", null, null);
   }
    
    public void receberHospede(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto){
        txtNome.setText(cliente.getNome());
        txtQuarto.setText(quarto.getNumero());
        txtCelular.setText(cliente.getCelular());
        this.reserva = reserva;
    }
    
    public void limparCampos(){
        txtNome.setText("");
        txtQuarto.setText("");
        txtCelular.setText("");
    }
    
    public DTOReserva getReserva(){
        return reserva;
    }
    
    public static CadastrarPedidoQuarto getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new CadastrarPedidoQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
