package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.PassageiroDAO;
import br.com.hospede.model.DAO.PasseioDAO;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import br.com.hospede.model.modeloTabela.ModeloPasseio;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ConsultarPasseio extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloPasseio           modelo;
    private JLabel                 lblPesquisa, lblPassageiros, lblHistorico;
    private JButton                btnCadastrar, btnPesquisar, btnPassageiro, btnHistorico;
    private JTextField             txtPesquisa;
    private ControlarEventos       eventos = null;
    public static ConsultarPasseio      instance;
    private ModeloPasseio           modeloPasseio;
    private EditarPasseio           editarPasseio;
    private PasseioDAO              passeioDAO;
    private InterfaceFactory        interfaceEditarPasseio;
    private ModeloPassageiros        modeloPassageiro;
    private PassageiroDAO           passageiroDAO;
    private HistoricoDAO            historicoDAO;
    
    public ConsultarPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 530);
        setLayout(null);
        setResizable(false);
       
        lblPesquisa = new JLabel("Nome:");
        lblPesquisa.setSize(200,20);
        lblPesquisa.setLocation(30,20);
        
         //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,20);
        txtPesquisa.setLocation(90,20);
        
        modelo =  ModeloPasseio.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1055, 330);
        scroll.setLocation(130, 100);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(3).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(310,10);
        
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1150,50);
        
        Icon imPassageiro = new ImageIcon(getClass().getResource("imagens/Passageiros.png"));
        
        btnPassageiro = new JButton(imPassageiro);
        btnPassageiro.setSize(80,50);
        btnPassageiro.setLocation(740,10);
        
        lblPassageiros = new JLabel("ADICIONAR PASSAGEIRO");
        lblPassageiros.setSize(150,50);
        lblPassageiros.setLocation(710,60);
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
        
        add(btnCadastrar);
        add(btnPesquisar);
        add(lblPesquisa);
        add(txtPesquisa);
        add(lblPassageiros);
        add(btnPassageiro);
        add(scroll);
        add(btnHistorico);
        add(lblHistorico);
        
        botoes[67] = btnCadastrar;
        botoes[68] = btnPesquisar;
        botoes[69] = btnPassageiro;
        botoes[76] = btnHistorico;
        modelosTabelas[6] = tabela;
        
        
        //Associando os botões a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnPassageiro.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        tabela.addMouseListener(eventos);
    }
    
    public String getPesquisa(){
        return txtPesquisa.getText();
    }
    
    public DTOPasseio getPasseio(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloPasseio = ModeloPasseio.getInstance();
        return modeloPasseio.getPasseio(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    public JTable getTabela(){
        return tabela;
    }
    
    
    public static ConsultarPasseio getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            instance = new ConsultarPasseio(botoe, itensDeMenu, camposDeTexto,  usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}