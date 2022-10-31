package br.com.hospede.view;
import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloQuarto;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ConsultarQuarto extends InterfaceFactory{
    private ModeloQuarto          modelo;
    private JTable                tabela;
    private JScrollPane           scroll;
    private boolean               busca = true;
    private int                   posicao = 1;
    private JLabel                lblLivre, lblReservado, lblHospedado, lblManutencao, livre, hospedado, reservado, manutencao, lblOrganizando, organizando, lblHistorico;
    public static ConsultarQuarto instance = null;
    private QuartoDAO             quartoDAO;
    private JTextField            txtPesquisa;
    private JLabel                blPesquisa;
    private JButton               btnPesquisar, btnCadastrar, btnHistorico;
    private ControlarEventos       eventos;
    private ModeloQuarto          modeloQuarto;
    private InterfaceFactory interfaceEditarQuarto    = null;
    private EditarQuarto     editarQuarto;
    private HistoricoDAO       auditarDAO;
    

    public ConsultarQuarto(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas
) {
        setTitle("Quarto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 205, 1325, 530);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        try{
            //Criando caminho das imagens.
        URL resourceLivre       = getClass().getResource("/br/com/hospede/view/imagens/Livre.png");
        URL resourceHospedado   = getClass().getResource("/br/com/hospede/view/imagens/Hospedado.png");
        URL resourceReservado   = getClass().getResource("/br/com/hospede/view/imagens/Reservado.png");
        URL resourceManutencao  = getClass().getResource("/br/com/hospede/view/imagens/Manutencao.png");
        URL resourceOrganizando = getClass().getResource("/br/com/hospede/view/imagens/Organizando.png");
        
        Icon imgLivre       = new ImageIcon(resourceLivre);
        Icon imgReservado   = new ImageIcon(resourceReservado);
        Icon imgHospedado   = new ImageIcon(resourceHospedado);
        Icon imgManutencao  = new ImageIcon(resourceManutencao);
        Icon imgOrganizacao = new ImageIcon(resourceOrganizando);
        
        //Iniciando labels
        lblLivre = new JLabel(imgLivre);
        lblLivre.setSize(100, 100); 
        lblLivre.setLocation(130, 390);
        
        lblReservado = new JLabel(imgReservado);
        lblReservado.setSize(100, 100);
        lblReservado.setLocation(330, 390);

        lblHospedado = new JLabel(imgHospedado);
        lblHospedado.setSize(100, 100);
        lblHospedado.setLocation(530, 390);

        lblManutencao = new JLabel(imgManutencao);
        lblManutencao.setSize(100, 100);
        lblManutencao.setLocation(730, 390);
        
        lblOrganizando = new JLabel(imgOrganizacao);
        lblOrganizando.setSize(100, 100);
        lblOrganizando.setLocation(930, 390);
        
        }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "Erro ao buscar imagens.");
        }
       
        livre = new JLabel("Livre");
        livre.setSize(100, 100);
        livre.setLocation(230, 390);

        reservado = new JLabel("Reservado");
        reservado.setSize(100, 100);
        reservado.setLocation(430, 390);

        hospedado = new JLabel("Hospedado");
        hospedado.setSize(100, 100);
        hospedado.setLocation(630, 390);

        manutencao = new JLabel("Manutenção");
        manutencao.setSize(100, 100);
        manutencao.setLocation(830, 390);
        
        organizando = new JLabel("Organizando");
        organizando.setSize(100, 100);
        organizando.setLocation(1030, 390);
        
        blPesquisa = new JLabel("Número do Quarto:");
        blPesquisa.setSize(200,20);
        blPesquisa.setLocation(20,20);
        
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(100,20);
        txtPesquisa.setLocation(150,20);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(270,10);
       
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1230,50);

        //Criando tabela.
        modelo = ModeloQuarto.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1235, 300);
        scroll.setLocation(40, 100);
        
        tabela.getTableHeader().setReorderingAllowed(false);

        //Configurando as celulas da tabela
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(90);
        tabela.getColumnModel().getColumn(1).setMaxWidth(75);
        tabela.getColumnModel().getColumn(2).setMaxWidth(300);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(5).setMaxWidth(150);
        tabela.getColumnModel().getColumn(6).setMaxWidth(150);
        tabela.getColumnModel().getColumn(7).setMaxWidth(70);
        tabela.getColumnModel().getColumn(7).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(8).setMaxWidth(70);
        tabela.getColumnModel().getColumn(8).setCellRenderer(renderer);

        tabela.setRowHeight(30);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
        
        if(!usuarioLogado.getFuncao().equals("Gerente")){
            btnCadastrar.setEnabled(false);
            btnHistorico.setEnabled(false);
            tabela.setEnabled(false);
            
        }

        //Adicionando componentes.
        add(scroll);
        add(lblLivre);
        add(lblReservado);
        add(lblHospedado);
        add(lblManutencao);
        add(lblOrganizando);
        add(livre);
        add(reservado);
        add(hospedado);
        add(manutencao);
        add(organizando);
        add(blPesquisa);
        add(txtPesquisa);
        add(btnPesquisar);
        add(btnCadastrar);
        add(btnHistorico);
        add(lblHistorico);
        
        botoes[62]= btnPesquisar;
        botoes[63] = btnCadastrar;
        botoes[71] = btnHistorico;
        modelosTabelas[1] = tabela;
        
        //Associando eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        
        quartoDAO = new QuartoDAO();
        quartoDAO.listarQuarto();
       
        tabela.addMouseListener(eventos);
    
   }
    public int getPesquisa(){
      return Integer.parseInt(txtPesquisa.getText());
    }
    
    public DTOQuarto getQuarto(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloQuarto = ModeloQuarto.getInstance();
        return modeloQuarto.getQuarto(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    public JTable getTabela(){
        return tabela;
    }
    
    public static ConsultarQuarto getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas
) {
        if (instance == null) {
            return instance = new ConsultarQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
