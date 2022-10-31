package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.DAO.ReservaDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloReserva;
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

public class ConsultarReserva extends InterfaceFactory{
    private JTable                 tabela;
    private JScrollPane            scroll;
    private final String[] coluna = {"Nome", "Cidade", "Muncipio", "Email","Telefone","Celular","Quarto","Crianças", "Adultos", "Entrada","Saída"};
    private String[][]             dado = { };
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ModeloReserva          modelo;
    public static ConsultarReserva instance = null;
    private ControlarEventos       eventos;
    private JLabel                 lblCpf, lblNome, lblHistorico, lblPassaporte;
    private JFormattedTextField             txtCpf, txtNome;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    private QuartoDAO              quartoDAO;
    private ReservaDAO             reservaDAO;
    private HistoricoDAO           auditarDAO;
    private ClienteDAO             clienteDAO;
    private ModeloReserva          modeloReserva;
    private EditarReserva          editarReserva;
    private MaskFormatter          mascararCPF;
    private JTextField             txtPassaporte;
    
    
    public ConsultarReserva(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Reserva");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 205, 1325, 530);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;  
        
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
            
            txtPassaporte = new JTextField();
            txtPassaporte.setSize(200, 30);
            txtPassaporte.setLocation(70, 60);
            
            lblPassaporte = new JLabel("Passaporte");
            lblPassaporte.setSize(200, 30);
            lblPassaporte.setLocation(0, 60);
            
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
            txtNome = new JFormattedTextField();
            txtNome.setSize(200, 30);
            txtNome.setLocation(70, 60);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1250,50);
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(310,10);
      
        modelo =  ModeloReserva.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1270, 350);
        scroll.setLocation(20, 100);
        
         JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(9).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(9).setMaxWidth(70);
        tabela.getColumnModel().getColumn(10).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(10).setMaxWidth(70);

        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        tabela.getTableHeader().setReorderingAllowed(false);
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
        
        if(!usuarioLogado.getFuncao().equals("Gerente")){
            btnHistorico.setEnabled(false);
            
            
        }

        //Adicionando componentes.
        add(scroll);
        add(lblCpf);
        add(txtCpf);
        add(btnCadastrar);
        add(btnPesquisar);
        add(btnHistorico);
        add(lblHistorico);
        add(txtPassaporte);
        add(lblPassaporte);
        
        botoes[37] = btnPesquisar;
        botoes[18] = btnCadastrar;
        botoes[73] = btnHistorico;
        modelosTabelas[3] = tabela;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);   
        tabela.addMouseListener(eventos);
        
    }
    
    public DTOCliente getPesquisa(){
       DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", txtCpf.getText(), "", "", "", "");
       cliente.setPassaport(txtPassaporte.getText());
      return cliente;
        
    }
    
    public DTOReserva getReserva(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloReserva = ModeloReserva.getInstance();
        return modeloReserva.getReserva(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    public JTable getTabela(){
        return tabela;
    }
    
    public static ConsultarReserva getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            return instance = new ConsultarReserva( botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
