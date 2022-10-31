package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloUsuario;
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

public class ConsultarUsuario extends InterfaceFactory{
    //Objetos.
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloUsuario          modelo;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static JTextField[]     camposDeTexto;
    private ControlarEventos       eventos;
    private JLabel                 lblPesquisa, lblHistorico;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    public static ConsultarUsuario instance=null;
    private ModeloUsuario    modeloUsuario;
    
    //Construtor.
    public ConsultarUsuario(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas
){
        setTitle("Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 205, 1325, 530);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        
        //Criando label.
        lblPesquisa = new JLabel("Login:");
        lblPesquisa.setSize(100,20);
        lblPesquisa.setLocation(20,20);
        
        //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,30);
        txtPesquisa.setLocation(90,20);

        //Construindo tabela.
        modelo = ModeloUsuario.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1105, 350);
        scroll.setLocation(90, 100);
        
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(5).setMaxWidth(70);
        tabela.getColumnModel().getColumn(5).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(6).setMaxWidth(70);
        tabela.getColumnModel().getColumn(6).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        tabela.getTableHeader().setReorderingAllowed(false);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(312,10);
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
       
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1150,50);
        btnCadastrar.setToolTipText("Cadastrar Usuário.");
      

        //Adicionando componentes.
        add(scroll);
        add(lblPesquisa);
        add(txtPesquisa);
        add(btnCadastrar);
        add(btnPesquisar);
        add(btnHistorico);
        add(lblHistorico);
        
        camposDeTexto[2] = txtPesquisa;
        botoes[70] = btnHistorico;
        botoes[33]       = btnPesquisar;
        botoes[34]       = btnCadastrar;
        modelosTabelas[0] = tabela;
        
        
        //Associando eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        txtPesquisa.addActionListener(eventos);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        tabela.addMouseListener(eventos);
    }   
    
    public String getPesquisa(){
      return txtPesquisa.getText();
    }
    
    public DTOUsuario getUsuario(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloUsuario = ModeloUsuario.getInstance();
        return modeloUsuario.getUsuario(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    public JTable getTabela(){
        return tabela;
    }
     
    
    public static ConsultarUsuario getInstance(JButton botoes[], JMenuItem itensDeMenu[],  JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas
){
        if(instance == null){
            return instance = new ConsultarUsuario(botoes, itensDeMenu,  camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }

}
