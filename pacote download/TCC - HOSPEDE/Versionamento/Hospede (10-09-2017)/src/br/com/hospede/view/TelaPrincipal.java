package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.DAO.EntrandoOuSaindoDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloEntrandoHoje;
import br.com.hospede.model.modeloTabela.ModeloSaindoHoje;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TelaPrincipal extends InterfaceFactory{
    
    //Objetos.
    private JPanel                painelAcima, painelUnidade, painelEntrandoSaindo;
    private JPanel                painelArea;
    private JLabel                lblLogo, lblUsuario, lblQuarto, lblCliente, lblReserva, lblReservaServicos, lblHospedagemServicos,lblHospedagem, lblProdutos,
                                  lblCabecalho, lblPasseio, lblSair, imgReserva, imgHospedagem, imgLivre, imgManutencao,
                                  imgOrganizando, lblLivre, lblManutencao,lblOrganizando,lblTotalQuarto, lblEntrando, lblSaindo, imagemEntrando, imagemSaindo;
    private JButton               btnReserva, btnHospedagem, btnCliente, btnUsuario, btnQuarto,
                                  btnProdutos, btnPasseio, btnSair, btnAtualizar;
    private JTextField            txtReserva, txtHospedagem, txtLivre, txtManutencao, txtOrganizando, txtTotalQuarto;                              
    
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos      eventos;
    private DTOUsuario               usuarioLogado;
    public static TelaPrincipal   instance      = null;
    private ModeloEntrandoHoje     modeloEntrando;
    private ModeloSaindoHoje       modeloSaindo;
    private EntrandoOuSaindoDAO    entrandoSaindoDAO;
    private JTable                 tabelaEntrando, tabelaSaindo;
    private JScrollPane            scroolEntrando, scroolSaindo;
        
    //Construtor.
    public TelaPrincipal(DTOUsuario usuarioLogado){
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1360, 735);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        
        //Recebendo usuário que logou.
        this.usuarioLogado = usuarioLogado;    

        //Inicializando paineis.
        painelArea = new JPanel();
        painelArea.setSize(1325, 535);
        painelArea.setLocation(10, 160);
        painelArea.setLayout(null);
        painelArea.setBorder(BorderFactory.createLineBorder(Color.black));
        
        painelEntrandoSaindo = new JPanel();
        painelEntrandoSaindo.setLayout(null);
        painelEntrandoSaindo.setSize(955, 490);
        painelEntrandoSaindo.setLocation(360, 5);
        painelEntrandoSaindo.setBorder(BorderFactory.createLineBorder(Color.black));
       
        painelUnidade = new JPanel();
        painelUnidade.setLayout(null);
        painelUnidade.setSize(350, 490);
        painelUnidade.setLocation(5, 5);
        painelUnidade.setBorder(BorderFactory.createLineBorder(Color.black));
      
        
        painelAcima = new JPanel();
        painelAcima.setSize(1325, 150);
        painelAcima.setLocation(10, 5);
        painelAcima.setLayout(null);
        painelAcima.setBorder(BorderFactory.createLineBorder(Color.black));
  

         //Inicializando imagens.
        Icon imReserva = new ImageIcon(getClass().getResource("imagens/Reserva.png"));
        Icon imUsuario = new ImageIcon(getClass().getResource("imagens/Usuario.png"));
        Icon imCliente = new ImageIcon(getClass().getResource("imagens/Cliente.png"));
        Icon imQuarto = new ImageIcon(getClass().getResource("imagens/Quarto.png"));
        Icon imHospedagem = new ImageIcon(getClass().getResource("imagens/Hospedagem.png"));
        Icon imPasseio = new ImageIcon(getClass().getResource("imagens/Passeio.png"));
        Icon imProdutos = new ImageIcon(getClass().getResource("imagens/Produtos.png"));
        Icon imSair = new ImageIcon(getClass().getResource("imagens/Sair.png"));
        
        //Inicializando imagem.
        URL resourceLogo = getClass().getResource("/br/com/hospede/view/imagens/Logo.jpg");
        ImageIcon imgLogo = new ImageIcon(resourceLogo);
        

        //Iniciando labels.
        lblLogo = new JLabel(imgLogo);
        lblLogo.setSize(200, 80);
        lblLogo.setLocation(150, 605);
        
        lblCabecalho = new JLabel("ESTATÍSTICA POR SERVIÇOS: ");
        lblCabecalho.setSize(500, 50);
        lblCabecalho.setLocation(50, 5);
        
        Icon reserva = new ImageIcon(getClass().getResource("imagens/Reservado.png"));
        Icon hospedagem = new ImageIcon(getClass().getResource("imagens/Hospedado.png"));
        Icon manutencao = new ImageIcon(getClass().getResource("imagens/Manutencao.png"));
        Icon livre = new ImageIcon(getClass().getResource("imagens/Livre.png"));
        Icon organizando = new ImageIcon(getClass().getResource("imagens/Organizando.png"));
        Icon imgSaindo = new ImageIcon(getClass().getResource("imagens/SaindoHoje.png"));
        Icon imgEntrando = new ImageIcon(getClass().getResource("imagens/EntrandoHoje.png"));
        
        imgReserva = new JLabel(reserva);
        imgReserva.setSize(90, 40);
        imgReserva.setLocation(10, 60);

        lblReservaServicos = new JLabel(" reserva(s) realizada(s).");
        lblReservaServicos.setSize(200, 40);
        lblReservaServicos.setLocation(150, 60);

        imgHospedagem = new JLabel(hospedagem);
        imgHospedagem.setSize(90, 40);
        imgHospedagem.setLocation(10, 120);

        lblHospedagemServicos = new JLabel(" hospedagem(s) efetuada(s).");
        lblHospedagemServicos.setSize(200, 40);
        lblHospedagemServicos.setLocation(150, 120);

        imgManutencao = new JLabel(manutencao);
        imgManutencao.setSize(90, 40);
        imgManutencao.setLocation(10, 180);

        lblManutencao = new JLabel(" quarto(s) em manutenção.");
        lblManutencao.setSize(200, 40);
        lblManutencao.setLocation(150, 180);

        imgLivre = new JLabel(livre);
        imgLivre.setSize(90, 40);
        imgLivre.setLocation(10, 240);

        lblLivre = new JLabel(" quarto(s) livre(s).");
        lblLivre.setSize(200, 40);
        lblLivre.setLocation(150, 240);
        
        imgOrganizando = new JLabel(organizando);
        imgOrganizando.setSize(90, 40);
        imgOrganizando.setLocation(10, 300);
        
        lblOrganizando = new JLabel("Quarto(s) em organização.");
        lblOrganizando.setSize(200, 40);
        lblOrganizando.setLocation(150, 300);
        
        //Inicaindo caixas de textos.
        txtReserva = new JTextField();
        txtReserva.setSize(50, 20);
        txtReserva.setLocation(90, 72);
        txtReserva.setEditable(false);

        txtHospedagem = new JTextField();
        txtHospedagem.setSize(50, 20);
        txtHospedagem.setLocation(90, 130);
        txtHospedagem.setEditable(false);

        txtManutencao = new JTextField();
        txtManutencao.setSize(50, 20);
        txtManutencao.setLocation(90, 190);
        txtManutencao.setEditable(false);

        txtLivre = new JTextField();
        txtLivre.setSize(50, 20);
        txtLivre.setLocation(90, 250);
        txtLivre.setEditable(false);
        
        txtOrganizando = new JTextField();
        txtOrganizando.setSize(50, 20);
        txtOrganizando.setLocation(90, 310);
        txtOrganizando.setEditable(false);
        
        lblTotalQuarto = new JLabel("Total:");
        lblTotalQuarto.setSize(110, 20);
        lblTotalQuarto.setLocation(40, 360);
        lblTotalQuarto.setForeground(Color.red);
                
        txtTotalQuarto = new JTextField();
        txtTotalQuarto.setSize(50, 20);
        txtTotalQuarto.setLocation(90, 360);
        txtTotalQuarto.setEditable(false);
        
        btnAtualizar = new JButton("Atualizar ");
        
        //Inicializando botões.
        btnReserva = new JButton(imReserva);
        btnReserva.setSize(120, 80);
        btnReserva.setLocation(510, 20);
        btnReserva.setToolTipText("Funções para reservas");
        
        lblReserva = new JLabel("RESERVAS");
        lblReserva.setSize(130, 90);
        lblReserva.setLocation(540, 80);

        btnHospedagem = new JButton(imHospedagem);
        btnHospedagem.setSize(120, 80);
        btnHospedagem.setLocation(660, 20);
        btnHospedagem.setToolTipText("Funções para hospedagens");
        
        lblHospedagem = new JLabel("HOSPEDAGENS");
        lblHospedagem.setSize(130, 90);
        lblHospedagem.setLocation(680, 80);
        
        btnProdutos = new JButton(imProdutos);
        btnProdutos.setSize(120, 80);
        btnProdutos.setLocation(840, 20);
        btnProdutos.setToolTipText("Funçõe em cima de produtos");
        
        lblProdutos = new JLabel("PRODUTOS");
        lblProdutos.setSize(130, 90);
        lblProdutos.setLocation(870, 80);
        
        btnPasseio = new JButton(imPasseio);
        btnPasseio.setSize(120, 80);
        btnPasseio.setLocation(990, 20);
        btnPasseio.setToolTipText("Funções de passeio");
        
        lblPasseio = new JLabel("PASSEIOS");
        lblPasseio.setSize(130, 90);
        lblPasseio.setLocation(1030, 80);
        
        btnSair = new JButton(imSair);
        btnSair.setSize(120, 80);
        btnSair.setLocation(1140, 20);
        btnSair.setToolTipText("Sair do sistema");
        
        lblSair = new JLabel("SAIR");
        lblSair.setSize(130, 90);
        lblSair.setLocation(1190, 80);
        
        btnCliente = new JButton(imCliente);
        btnCliente.setSize(120, 80);
        btnCliente.setLocation(330, 20);
        btnCliente.setToolTipText("Funções de cliente");
        
        lblCliente = new JLabel("CLIENTES");
        lblCliente.setSize(130, 90);
        lblCliente.setLocation(360, 80);
        
        btnUsuario = new JButton(imUsuario);
        btnUsuario.setSize(120, 80);
        btnUsuario.setLocation(30, 20);
        btnUsuario.setToolTipText("Funções em usuario");
        
        lblUsuario = new JLabel("USUÁRIOS");
        lblUsuario.setSize(130, 90);
        lblUsuario.setLocation(60, 80);

        btnQuarto = new JButton(imQuarto);
        btnQuarto.setSize(120, 80);
        btnQuarto.setLocation(180, 20);
        btnQuarto.setToolTipText("Funções de quartos");
        
        lblQuarto = new JLabel("QUARTOS");
        lblQuarto.setSize(120, 80);
        lblQuarto.setLocation(210, 80);
        
        //Iniciando imagens.
        Icon Saindo = new ImageIcon(getClass().getResource("imagens/SaindoHoje.png"));
        Icon Entrando = new ImageIcon(getClass().getResource("imagens/EntrandoHoje.png"));
       
         //Inicializando labels.
        lblEntrando = new JLabel("HÓSPEDES ENTRANDO HOJE:");
        lblEntrando.setSize(200, 20);
        lblEntrando.setLocation(100, 30);
        
        imagemEntrando = new JLabel(Entrando);
        imagemEntrando.setSize(100, 30);
        imagemEntrando.setLocation(285, 23);
        
        lblSaindo = new JLabel("HÓSPEDES SAINDO HOJE:");
        lblSaindo.setSize(200, 20);
        lblSaindo.setLocation(525, 30);
        
        imagemSaindo = new JLabel(Saindo);
        imagemSaindo.setSize(100, 30);
        imagemSaindo.setLocation(670, 23);
        
        //Construindo tabela.
        //Tabelas com clientes entrando hoje.
        modeloEntrando =  ModeloEntrandoHoje.getInstance();
        tabelaEntrando = new JTable(modeloEntrando);
        scroolEntrando = new JScrollPane(tabelaEntrando);
        scroolEntrando.setSize(940,200);
        scroolEntrando.setLocation(5,72);
        
     
        
      
        tabelaEntrando.getColumnModel().getColumn(0).setMaxWidth(70);
        tabelaEntrando.getColumnModel().getColumn(1).setMaxWidth(300);
        tabelaEntrando.getColumnModel().getColumn(2).setMaxWidth(70);
        tabelaEntrando.getColumnModel().getColumn(3).setMaxWidth(200);
        tabelaEntrando.getColumnModel().getColumn(4).setMaxWidth(200);
        tabelaEntrando.getColumnModel().getColumn(5).setMaxWidth(100);
        scroolEntrando.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Tabelas com clientes saindo hoje.
        modeloSaindo = ModeloSaindoHoje.getInstance();
        tabelaSaindo = new JTable(modeloSaindo);
        scroolSaindo = new JScrollPane(tabelaSaindo);
        scroolSaindo.setSize(940,200);
        scroolSaindo.setLocation(5,282);
        
        tabelaSaindo.getColumnModel().getColumn(0).setMaxWidth(70);
        tabelaSaindo.getColumnModel().getColumn(1).setMaxWidth(300);
        tabelaSaindo.getColumnModel().getColumn(2).setMaxWidth(70);
        tabelaSaindo.getColumnModel().getColumn(3).setMaxWidth(200);
        tabelaSaindo.getColumnModel().getColumn(4).setMaxWidth(200);
        tabelaSaindo.getColumnModel().getColumn(5).setMaxWidth(100);
        scroolSaindo.setBorder(BorderFactory.createLineBorder(Color.black));
        
            //Configurando atributos das tabelas.
        JTableRenderer renderer = new JTableRenderer();
        tabelaEntrando.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaEntrando.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaEntrando.setRowHeight(30);
        tabelaEntrando.setRowHeight(30);
        
        tabelaSaindo.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaSaindo.getColumnModel().getColumn(0).setMaxWidth(60);
        tabelaSaindo.setRowHeight(30);
        tabelaSaindo.setRowHeight(30);
        
        btnAtualizar = new JButton("ATUALIZAR DADOS");
        btnAtualizar.setSize(1310,30);
        btnAtualizar.setLocation(5,500);
        
        //Adicionando componentes.
        add(painelAcima);
        painelAcima.add(btnUsuario);
        painelAcima.add(btnQuarto);
        painelAcima.add(btnCliente);
        painelAcima.add(btnReserva);
        painelAcima.add(btnHospedagem);
        painelAcima.add(btnProdutos);
        painelAcima.add(btnPasseio);
        painelAcima.add(btnSair);
        painelAcima.add(lblReserva);
        painelAcima.add(lblHospedagem);
        painelAcima.add(lblQuarto);
        painelAcima.add(lblCliente);
        painelAcima.add(lblPasseio);
        painelAcima.add(lblProdutos);
        painelAcima.add(lblSair);
        painelAcima.add(lblUsuario);
        add(painelArea);
        painelArea.add(btnAtualizar);
        painelArea.add(lblLogo);
        painelArea.add(painelUnidade);
        painelArea.add(painelEntrandoSaindo);
        painelUnidade.add(lblCabecalho);
        painelUnidade.add(lblCabecalho);
        painelUnidade.add(imgReserva);
        painelUnidade.add(imgHospedagem);
        painelUnidade.add(imgManutencao);
        painelUnidade.add(imgLivre);
        painelUnidade.add(lblReservaServicos);
        painelUnidade.add(lblHospedagemServicos);
        painelUnidade.add(lblManutencao);
        painelUnidade.add(lblLivre);
        painelUnidade.add(lblOrganizando);
        painelUnidade.add(imgOrganizando);
        painelUnidade.add(txtReserva);
        painelUnidade.add(txtHospedagem);
        painelUnidade.add(txtManutencao);
        painelUnidade.add(txtLivre);
        painelUnidade.add(txtOrganizando);
        painelUnidade.add(lblTotalQuarto);
        painelUnidade.add(txtTotalQuarto);
        painelEntrandoSaindo.add(scroolEntrando);
        painelEntrandoSaindo.add(scroolSaindo);
        painelEntrandoSaindo.add(lblEntrando);
        painelEntrandoSaindo.add(lblSaindo);
        painelEntrandoSaindo.add(imagemEntrando);
        painelEntrandoSaindo.add(imagemSaindo);
        
        //Adicionando botões e itens de menu, em seus respectivos vetores, para enviar a classe que controla os eventos.
        
        botoes[1] = btnSair;
        botoes[2] = btnUsuario;
        botoes[15] = btnQuarto;
        botoes[10] = btnCliente;
        botoes[21] = btnReserva;
        botoes[24] = btnAtualizar;
        botoes[26] = btnHospedagem;
        botoes[42] = btnPasseio;
        botoes[64] = btnProdutos;
        
        
        //Associando os botões a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnUsuario.addActionListener(eventos);
        btnProdutos.addActionListener(eventos);
        btnSair.addActionListener(eventos);
        btnQuarto.addActionListener(eventos);
        btnCliente.addActionListener(eventos);
        btnReserva.addActionListener(eventos);
        btnHospedagem.addActionListener(eventos);
        btnPasseio.addActionListener(eventos);
        btnAtualizar.addActionListener(eventos);
        
    }
    
    public void receberQuantias(int[] totais){//Recebe a quantia de cada serviço, após uma busca no banco de dados.
        
        txtReserva.setText(Integer.toString(totais[0]));
        txtHospedagem.setText(Integer.toString(totais[1]));
        txtLivre.setText(Integer.toString(totais[2]));
        txtManutencao.setText(Integer.toString(totais[3]));
        txtOrganizando.setText(Integer.toString(totais[4]));
        
        //Soma todos os quartos.
        int totalQuarto = totais[0] + totais[1] +totais[2] + totais[3] +totais[4];
        txtTotalQuarto.setText(Integer.toString(totalQuarto));
    }
 
    public static TelaPrincipal getInstance(DTOUsuario usuario){
        if(instance == null){
            instance = new TelaPrincipal(usuario);
        }
        return instance;
    }
    
}
