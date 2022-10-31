package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloSelecionarQuarto;
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

public class SelecionarQuarto extends InterfaceFactory{
    
    private JButton                btnSelecionar;
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloSelecionarQuarto           modelo;
    private boolean                busca = true;
    private int                    posicao = 1;
    private JLabel                 lblLivre, lblReservado, lblHospedado, lblManutencao, lblOrganizando, livre, hospedado, reservado, manutencao, organizando;
    private JMenuItem[]            itensDeMenu = new JMenuItem[30];
    private JButton[]              botoes        = new JButton[43];;
    private ControlarEventos       eventos;
    private String                 controle, acao;
    private CadastrarReserva       cadastrarReserva;
    public static SelecionarQuarto instance = null;
    private QuartoDAO              quartoDAO = new QuartoDAO();
    private String                 tipoReserva;
    
    
    public SelecionarQuarto(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Selecionar Quarto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 70, 1115, 605);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com botÃµes, para adicionar o botÃ£o desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;
        this.acao = controle;

        //Criando tabelas
        modelo =  ModeloSelecionarQuarto.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1075, 450);
        scroll.setLocation(10, 20);
        
        //Configurando as celulas da tabela
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(90);
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(45);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(5).setMaxWidth(100);
        tabela.getColumnModel().getColumn(6).setMaxWidth(100);
        tabela.setRowHeight(30);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setSize(100, 30);
        btnSelecionar.setLocation(10, 510);

        //Iniciando labels
        ImageIcon imgLivre = new ImageIcon(getClass().getResource("imagens/Livre.png"));
        Icon imgReservado = new ImageIcon(getClass().getResource("imagens/Reservado.png"));
        Icon imgHospedado = new ImageIcon(getClass().getResource("imagens/Hospedado.png"));
        Icon imgManutencao = new ImageIcon(getClass().getResource("imagens/Manutencao.png"));
        Icon imgOrganizacao = new ImageIcon(getClass().getResource("imagens/Organizando.png"));
        
        lblLivre = new JLabel(imgLivre);
        lblLivre.setSize(100, 100);
        lblLivre.setLocation(100, 470);

        lblReservado = new JLabel(imgReservado);
        lblReservado.setSize(100, 100);
        lblReservado.setLocation(250, 470);

        lblHospedado = new JLabel(imgHospedado);
        lblHospedado.setSize(100, 100);
        lblHospedado.setLocation(450, 470);

        lblManutencao = new JLabel(imgManutencao);
        lblManutencao.setSize(100, 100);
        lblManutencao.setLocation(650, 470);
        
        lblOrganizando = new JLabel(imgOrganizacao);
        lblOrganizando.setSize(100, 100);
        lblOrganizando.setLocation(850, 470);

        livre = new JLabel("Livre");
        livre.setSize(100, 100);
        livre.setLocation(200, 470);

        reservado = new JLabel("Reservado");
        reservado.setSize(100, 100);
        reservado.setLocation(350, 470);

        hospedado = new JLabel("Hospedado");
        hospedado.setSize(100, 100);
        hospedado.setLocation(550, 470);

        manutencao = new JLabel("ManutenÃ§Ã£o");
        manutencao.setSize(100, 100);
        manutencao.setLocation(750, 470);
        
        organizando = new JLabel("Organizando");
        organizando.setSize(100, 100);
        organizando.setLocation(950, 470);

        //Adicionando componentes.
        add(scroll);
        add(btnSelecionar);
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
        
        //Adicionando botÃ£o ao vetor.
        botoes[20] = btnSelecionar;
        
        //Associando os botÃµes a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnSelecionar.addActionListener(eventos);
        
        DTOQuarto quarto = new DTOQuarto(0,"","","","","","",null,null);
        quartoDAO.listarQuarto();
    }
    
    public DTOQuarto getQuarto(){
        DTOQuarto Quarto = new DTOQuarto(0,"","","","","","",null,null) ;
        
        //Pega o indice selecionado na tebalela de quartos.
        int linhaSelecionada = tabela.getSelectedRow();
        Quarto = modelo.getQuarto(linhaSelecionada);
        return Quarto;
    }
    
    public void setTipoReserva(String tipoReserva){
        this.tipoReserva = tipoReserva;
        
    }
    
    
    public String getTipoReserva(){//Retorna o tipo de reserva, classifica-se em hospedagem e reserva.
        return tipoReserva;
    }
    
    public static SelecionarQuarto getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new SelecionarQuarto(botoes,itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
