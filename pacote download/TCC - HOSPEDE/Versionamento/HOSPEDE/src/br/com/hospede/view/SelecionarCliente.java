package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloSelecionarCliente;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;


public class SelecionarCliente extends InterfaceFactory{
     private JTable                 tabela;
    private JScrollPane            scroll;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private JLabel                 lblCpf, lblNome;
    private JTextField             txtNome;
    private JButton                btnSelecionar, btnPesquisar, btnCadastrar;
    private ControlarEventos       eventos = null;
    public static SelecionarCliente instance = null;
    private MaskFormatter          mascararCPF;
    private ModeloSelecionarCliente          modelo;
    private InterfaceFactory       interfaceEditarCliente;
    private EditarCliente          editarCliente;
    private ClienteDAO             clienteDAO;
    private HistoricoDAO           auditarDAO;
    private String                 tipoReserva;
    private JFormattedTextField    txtCpf;
    
    public SelecionarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Selecionar Cliente");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 100, 1325, 608);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.campoDeTexto = camposDeTexto;
        
        //Criando label.
        lblCpf = new JLabel("CPF:");
        lblCpf.setSize(100,20);
        lblCpf.setLocation(20,20);
        
        lblNome = new JLabel("Nome:");
        lblNome.setSize(100,20);
        lblNome.setLocation(20,60);
        
        try{
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtCpf = new JFormattedTextField(mascararCPF);
            txtCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
            txtCpf.setSize(200, 30);
            txtCpf.setLocation(70, 20);
            
            
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
            txtNome = new JTextField();
            txtNome.setSize(200, 30);
            txtNome.setLocation(70, 60);

        modelo = ModeloSelecionarCliente.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1270, 350);
        scroll.setLocation(20, 100);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(3).setMaxWidth(60);
        tabela.getColumnModel().getColumn(7).setMaxWidth(70);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(290,10);
        
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setSize(100, 30);
        btnSelecionar.setLocation(15, 510);
        
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1250,50);
        btnCadastrar.setToolTipText("Cadastrar Cliente.");
        

        //Adicionando componentes.
        add(scroll);
        add(lblCpf);
        add(txtCpf);
        add(lblNome);
        add(txtNome);
        add(btnPesquisar);
        add(btnPesquisar);
        add(btnSelecionar);
        add(btnCadastrar);
        
        //botoes[35] = btnPesquisar;
        botoes[77] = btnSelecionar;
        botoes[78] = btnPesquisar;
        botoes[79] = btnCadastrar;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnPesquisar.addActionListener(eventos);
        btnSelecionar.addActionListener(eventos);
        btnCadastrar.addActionListener(eventos);
        
    }
    
    public DTOCliente getPesquisa(){
        return new DTOCliente(0, 0, txtNome.getText(), "", "", "", "", "", "", txtCpf.getText(), "", "", "", "");
    }
    
    public void setTipoReserva(String tipoReserva){
        this.tipoReserva = tipoReserva; 
    }
    
    public String getTipoReserva(){
        return tipoReserva;
    }
    
    public DTOCliente getCliente(){
        
         DTOCliente cliente = modelo.getCliente(tabela.getSelectedRow());
        return cliente;
    }
    
    public static SelecionarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            return instance = new SelecionarCliente(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
