/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.AdicionarClientePasseio;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.DTOPasseio;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Tomé
 */
public class AdicionarPassageiroPasseio extends InterfaceFactory{
    
    private ModeloPassageiros modelo;
    private JTable               tabela;
    private JScrollPane          scroll;
    private JTextField    txtTitulo, txtIngresso, txtCpfCliente, txtCPF, txtQuarto, txtCelular, txtNomePassageiro,
            txtCpfPassageiro, txtData;
    private JTextArea     txtDescricao;
    private JLabel        lblTitulo, lblDescricao, lblIngresso, lblData, lblNome,lblQuarto, lblCelular,
                          lblNomePassageiro, lblCpfPassageiro, lblAviso;
    private JButton       btnCadastrar, btnAdicionar;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static AdicionarPassageiroPasseio instance = null;
    private ControlarEventos eventos;
    private int id_Passeio;
    private int id_hospedagem;
    private SimpleDateFormat    formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private MaskFormatter          mascararCPF;
    private String nomeCliente;
    
    public AdicionarPassageiroPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Adicionar Passageiro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 80, 1070, 610);
        setLayout(null);
        setResizable(false);
        
        
        //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 570);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        modelo = ModeloPassageiros.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(950, 150);
        scroll.setLocation(50, 310);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(5).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        lblTitulo = new JLabel("Título");
        lblTitulo.setSize(50, 20);
        lblTitulo.setLocation(50, 20);
        
        txtTitulo = new JTextField();
        txtTitulo.setSize(250, 30);
        txtTitulo.setLocation(90, 20);
        
        lblIngresso = new JLabel("Ingresso R$");
        lblIngresso.setSize(70, 20);
        lblIngresso.setLocation(390, 20);
        
        txtIngresso = new JTextField();
        txtIngresso.setSize(50, 30);
        txtIngresso.setLocation(470, 20);
        
        lblData = new JLabel("Data");
        lblData.setSize(70, 30);
        lblData.setLocation(560, 20);
        
        txtData = new JTextField();
        txtData.setSize(130, 30);
        txtData.setLocation(600, 20);
        
        lblDescricao = new JLabel("Descrição");
        lblDescricao.setSize(100, 20);
        lblDescricao.setLocation(50, 60);
        
        txtDescricao = new JTextArea();
        txtDescricao.setSize(950, 150);
        txtDescricao.setLocation(50, 80);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        lblNome = new JLabel("Nome");
        lblNome.setSize(100, 20);
        lblNome.setLocation(50, 270);
        
        lblAviso= new JLabel("Insira o responsável pela dispesa do passeio");
        lblAviso.setSize(300, 20);
        lblAviso.setLocation(50, 240);
        
        lblQuarto = new JLabel("Quarto");
        lblQuarto.setSize(100, 20);
        lblQuarto.setLocation(280, 270);
        
        txtQuarto = new JTextField();
        txtQuarto.setSize(50, 30);
        txtQuarto.setLocation(335, 270);
        
        lblCelular = new JLabel("Celular");
        lblCelular.setSize(50, 20);
        lblCelular.setLocation(400, 270);
        
        txtCelular = new JTextField();
        txtCelular.setSize(200, 30);
        txtCelular.setLocation(450, 270);
        
        lblNomePassageiro = new JLabel("Passageiro");
        lblNomePassageiro.setSize(100, 20);
        lblNomePassageiro.setLocation(50, 490);
           
        txtNomePassageiro = new JTextField();
        txtNomePassageiro.setSize(200, 30);
        txtNomePassageiro.setLocation(130, 490);
        
        try{
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtCpfCliente = new JFormattedTextField(mascararCPF);
            txtCpfCliente.setSize(180, 30);
            txtCpfCliente.setLocation(90, 270);
            
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        lblCpfPassageiro = new JLabel("CPF");
        lblCpfPassageiro.setSize(100, 20);
        lblCpfPassageiro.setLocation(350, 490);
        
        try{
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtCpfPassageiro = new JFormattedTextField(mascararCPF);
            txtCpfPassageiro.setSize(200, 30);
        txtCpfPassageiro.setLocation(400, 490);
            
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 30);
        btnCadastrar.setLocation(50, 530);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setSize(100, 30);
        btnAdicionar.setLocation(750, 490);
        
        //Adicionando componentes no painel.
        add(painel);
        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblIngresso);
        painel.add(txtIngresso);
        painel.add(lblData);
        painel.add(txtData);
        painel.add(lblDescricao);
        painel.add(txtDescricao);
        painel.add(scroll);
        painel.add(btnAdicionar);
        painel.add(btnCadastrar);
        painel.add(lblNome);
        painel.add(txtCpfCliente);
        painel.add(lblQuarto);
        painel.add(lblCelular);
        painel.add(txtCelular);
        painel.add(txtQuarto);
        painel.add(txtNomePassageiro);
        painel.add(lblNomePassageiro);
        painel.add(lblCpfPassageiro);
        painel.add(txtCpfPassageiro);
        painel.add(lblAviso);
        
       camposDeTexto[18] = txtCpfCliente;
       botoes[54] = btnAdicionar;
       botoes[56] = btnCadastrar;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnAdicionar.addActionListener(eventos);
        txtCpfCliente.addFocusListener(eventos);
        
        
    }
    
    public void receberPasseio(DTOPasseio passeio){
        txtTitulo.setText(passeio.getTitulo());
        txtIngresso.setText(Double.toString(passeio.getIngresso()));
        try{
            
        txtData.setText(formatarData.format(passeio.getData()));
        txtDescricao.setText(passeio.getDescricao());
        id_Passeio = passeio.getId();
        }catch(Exception erro){
            
        }
        
        
    }
    
    public void receberCliente(Cliente cliente, Quarto quarto, Reserva hospedagem){
        txtQuarto.setText(quarto.getNumero());
        txtCelular.setText(cliente.getCelular());
        id_hospedagem = hospedagem.getId_reserva();
        nomeCliente = cliente.getNome();
    }
        
    public Cliente getCliente(){
        Cliente cliente = new Cliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        
        cliente.setCpf(txtCpfCliente.getText());
        cliente.setCelular(txtCelular.getText());
        return cliente;   
    }
    
    public AdicionarClientePasseio getPassageiro(){
        AdicionarClientePasseio clienteAdiconar = new AdicionarClientePasseio();
        
        clienteAdiconar.setNome(txtNomePassageiro.getText());
        clienteAdiconar.setCpf(txtCpfPassageiro.getText());
        clienteAdiconar.setQuarto(txtQuarto.getText());
        clienteAdiconar.setResponsavel(nomeCliente);
        clienteAdiconar.setId_Passeio(id_Passeio);
        clienteAdiconar.setId_Hospedagem(id_hospedagem);
        
        return clienteAdiconar;
    }
    
    public int getPassageiroRevomer(){
        int linhaSelecionada;
        return linhaSelecionada = tabela.getSelectedRow();
    }
    
   public void limparDados(){

        txtQuarto.setText("");
        txtCelular.setText("");
        txtCpfCliente.setText("");
        
   }
   
   public void limparPassageiros(){
       txtNomePassageiro.setText("");
        txtCpfPassageiro.setText("");
   }
    
    public static AdicionarPassageiroPasseio getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            instance = new AdicionarPassageiroPasseio(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
