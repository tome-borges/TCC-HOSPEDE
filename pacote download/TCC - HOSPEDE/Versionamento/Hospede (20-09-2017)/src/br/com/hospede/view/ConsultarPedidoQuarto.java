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


public class ConsultarPedidoQuarto extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloPedidosDeQuarto  modelo;
    private JButton                btnCadastrar, btnAdicionar, btnRemover;
    private JLabel                 lblNome, lblQuarto, lblCelular;
    private JTextField             txtNome, txtQuarto, txtCelular;
    private JPanel                 painel;
    public static ConsultarPedidoQuarto instance = null;
    private ControlarEventos       eventos;
    private DTOReserva reserva;
    
    public ConsultarPedidoQuarto(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Serviço de Quarto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setLayout(null);
        setResizable(false);
        
        painel = new JPanel();
        painel.setSize(1050, 140);
        painel.setLocation(30, 30);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        modelo =  ModeloPedidosDeQuarto.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1050, 220);
        scroll.setLocation(30, 270);
        
        lblNome = new JLabel("Nome: ");
        lblNome.setSize(100, 20);
        lblNome.setLocation(20, 50);
        
        txtNome = new JTextField();
        txtNome.setSize(300, 20);
        txtNome.setLocation(60, 50);
        
        lblQuarto = new JLabel("Quarto:");
        lblQuarto.setSize(100, 20);
        lblQuarto.setLocation(570, 50);
        
        txtQuarto = new JTextField();
        txtQuarto.setSize(70, 20);
        txtQuarto.setLocation(620, 50);

        lblCelular = new JLabel("Celular:");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(780, 50);
        
        txtCelular = new JTextField();
        txtCelular.setSize(150, 20);
        txtCelular.setLocation(830, 50);
        
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 20);
        btnCadastrar.setLocation(30, 550);
        
        btnRemover = new JButton("Remover");
        btnRemover.setSize(100, 20);
        btnRemover.setLocation(30, 240);
        
        
        
        
        
        add(scroll);
        add(btnRemover);
        add(painel);
        painel.add(lblNome);
        painel.add(lblQuarto);
        painel.add(lblCelular);
        painel.add(txtNome);
        painel.add(txtQuarto);
        painel.add(txtCelular);
        
        botoes[60] = btnRemover;
        camposDeTexto[20]= txtNome;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        txtNome.addFocusListener(eventos);
        btnRemover.addActionListener(eventos);
    }
    
    public DTOCliente getHospede(){
     return new DTOCliente(0, 0, txtNome.getText(), "", "", "", "", "", "", "", "", "", "", "");
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
    
    public int getIndiceRemover(){
       
        
        return  tabela.getSelectedRow();
    
    }
    
    public static ConsultarPedidoQuarto getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new ConsultarPedidoQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
