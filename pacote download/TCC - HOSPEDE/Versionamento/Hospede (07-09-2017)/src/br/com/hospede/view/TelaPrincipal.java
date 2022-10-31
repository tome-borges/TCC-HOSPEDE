package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import java.awt.Color;
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
import javax.swing.JTextField;

public class TelaPrincipal extends InterfaceFactory{
    
    //Objetos.
    private JPanel                painelAcima;
    private JPanel                painelArea;
    private JMenuBar              barra;
    private JMenu                 cadastrar, editar, excluir, consultar, auditar, ajuda;
    private JMenuItem             itemCadastrarCliente, itemCadastrarReservas, itemCadastrarHospedagens,
                                  itemCadastrarUsuarios, itemCadastrarQuarto, itemEditarClientes, itemEditarUsuarios, itemEditarReservas, itemEditarHospedagens,
                                  itemEditarQuarto, itemConsultarClientes, itemConsultarUsuarios, itemConsultarReservas, itemConsultarHospedagens,
                                  itemDeletarCliente, itemDeletarUsuarios, itemDeletarReservas, itemDeletarHospedagens, itemDeletarQuarto, itemAjuda,
                                  itemAuditarQuarto, itemAuditarCliente, itemAuditarReserva, itemAuditarHospedagem, itemAuditarUsuario, itemConsultarQuarto,
                                  itemCadastrarPasseios, itemEditarPasseios, itemDeletarPasseios, itemConsultarPasseios, itemCadastrarProduto, itemEditarProduto,
                                  itemConsultarProduto, itemDeletarProduto, itemConsultarPedidosQuarto;
    
    private JLabel                lblLogo, lblUsuario, lblQuarto, lblCliente, lblReserva, lblHospedagem, lblProdutos, lblPasseio, lblSair;
    private JButton               btnReserva, btnHospedagem, btnCliente, btnUsuario, btnQuarto,
                                  btnProdutos, btnPasseio, btnSair;
                                  
    
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos      eventos;
    private Usuario               usuarioLogado;
    public static TelaPrincipal   instance      = null;
        
    //Construtor.
    public TelaPrincipal(Usuario usuarioLogado){
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
        painelArea.setSize(1325, 510);
        painelArea.setLocation(10, 160);
        painelArea.setBorder(BorderFactory.createLineBorder(Color.black));
        
        painelAcima = new JPanel();
        painelAcima.setSize(1325, 150);
        painelAcima.setLocation(10, 5);
        painelAcima.setLayout(null);
        painelAcima.setBorder(BorderFactory.createLineBorder(Color.black));

        //Inicializando menus.
        barra = new JMenuBar();
        cadastrar = new JMenu("Cadastrar");
        editar = new JMenu("Editar");
        excluir = new JMenu("Deletar");
        consultar = new JMenu("Consultar");
        auditar = new JMenu("Auditar");
        ajuda = new JMenu("Ajuda");

        itemCadastrarCliente = new JMenuItem("Cadastrar cliente");
        itemCadastrarUsuarios = new JMenuItem("Cadastrar usuário");
        itemCadastrarReservas = new JMenuItem("Cadastrar reserva");
        itemCadastrarHospedagens = new JMenuItem("Cadastrar hospedagem");
        itemCadastrarQuarto = new JMenuItem("Cadastrar quartos");
        itemEditarClientes = new JMenuItem("Editar cliente");
        itemEditarUsuarios = new JMenuItem("Editar usuário");
        itemEditarReservas = new JMenuItem("Editar reserva");
        itemEditarHospedagens = new JMenuItem("Editar hospedagem");
        itemEditarQuarto = new JMenuItem("Editar quartos");
        itemDeletarCliente = new JMenuItem("Deletar cliente");
        itemDeletarUsuarios = new JMenuItem("Deletar usuário");
        itemDeletarReservas = new JMenuItem("Deletar reserva");
        itemDeletarHospedagens = new JMenuItem("Deletar hospedagem");
        itemDeletarQuarto = new JMenuItem("Deletar quartos");
        itemConsultarClientes = new JMenuItem("Consultar cliente");
        itemConsultarUsuarios = new JMenuItem("Consultar usuário");
        itemConsultarReservas = new JMenuItem("Consultar reserva");
        itemConsultarHospedagens = new JMenuItem("Consultar hospedagem");
        itemAjuda = new JMenuItem("Manual do usuário");
        itemAuditarQuarto = new JMenuItem("Auditar quarto");
        itemAuditarCliente = new JMenuItem("Auditar cliente");
        itemAuditarReserva = new JMenuItem("Auditar Reserva");
        itemAuditarHospedagem = new JMenuItem("Auditar hospedagem");
        itemAuditarUsuario = new JMenuItem("Auditar usuário");
        itemConsultarQuarto= new JMenuItem("Consultar Quarto");
        itemCadastrarPasseios= new JMenuItem("Cadastrar Passeios");
        itemEditarPasseios= new JMenuItem("Editar Passeios");
        itemDeletarPasseios= new JMenuItem("Deletar Passeios");
        itemConsultarPasseios= new JMenuItem("Consultar Passeios");
        itemCadastrarProduto= new JMenuItem("Cadastrar Produto");
        itemEditarProduto= new JMenuItem("Editar Produto");
        itemDeletarProduto= new JMenuItem("Deletar Produto");
        itemConsultarProduto= new JMenuItem("Consultar Produto");
        itemConsultarPedidosQuarto = new JMenuItem("Consultar Pedidos de Quarto");

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
        painelArea.add(lblLogo);
        
        //Adicionando botões e itens de menu, em seus respectivos vetores, para enviar a classe que controla os eventos.
        
        botoes[1] = btnSair;
        botoes[2] = btnUsuario;
        botoes[15] = btnQuarto;
        botoes[10] = btnCliente;
        botoes[21] = btnReserva;
        botoes[26] = btnHospedagem;
        botoes[42] = btnPasseio;
        botoes[64] = btnProdutos;
             
        //Itens de menu.
        itensDeMenu[0] = itemDeletarUsuarios;
        itensDeMenu[1] = itemEditarUsuarios;
        itensDeMenu[2] = itemConsultarUsuarios;
        itensDeMenu[3] = itemCadastrarUsuarios;
        itensDeMenu[4] = itemDeletarCliente;
        itensDeMenu[5] = itemEditarClientes;
        itensDeMenu[6] = itemCadastrarQuarto;
        itensDeMenu[7] = itemDeletarQuarto;
        itensDeMenu[8] = itemEditarQuarto;
        itensDeMenu[9] = itemCadastrarCliente;
        itensDeMenu[10] = itemConsultarClientes;
        itensDeMenu[11] = itemDeletarReservas;
        itensDeMenu[12] = itemEditarReservas;
        itensDeMenu[13] = itemCadastrarReservas;
        itensDeMenu[14] = itemConsultarReservas;
        itensDeMenu[15] = itemConsultarQuarto;
        itensDeMenu[16] = itemAuditarQuarto;
        itensDeMenu[17] = itemCadastrarHospedagens;
        itensDeMenu[18] = itemEditarHospedagens;
        itensDeMenu[19] = itemDeletarHospedagens;
        itensDeMenu[20] = itemConsultarHospedagens;
        itensDeMenu[21] = itemAuditarCliente;
        itensDeMenu[22] = itemAuditarUsuario;
        itensDeMenu[23] = itemAuditarReserva;
        itensDeMenu[24] = itemAuditarHospedagem;
        itensDeMenu[25] = itemAjuda;
        itensDeMenu[26] = itemCadastrarPasseios;
        itensDeMenu[27] = itemEditarPasseios;
        itensDeMenu[28] = itemDeletarPasseios;
        itensDeMenu[29] = itemConsultarPasseios;
        itensDeMenu[30] = itemCadastrarProduto;
        itensDeMenu[31] = itemEditarProduto;
        itensDeMenu[32] = itemDeletarProduto;
        itensDeMenu[34] = itemConsultarPedidosQuarto;
        
        
        //Associando os botões a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnUsuario.addActionListener(eventos);
        itemEditarUsuarios.addActionListener(eventos);
        itemDeletarUsuarios.addActionListener(eventos);
        itemCadastrarUsuarios.addActionListener(eventos);
        itemConsultarUsuarios.addActionListener(eventos);
        itemCadastrarCliente.addActionListener(eventos);
        itemEditarClientes.addActionListener(eventos);
        itemDeletarCliente.addActionListener(eventos);
        itemConsultarClientes.addActionListener(eventos);
        itemCadastrarQuarto.addActionListener(eventos);
        itemEditarQuarto.addActionListener(eventos);
        itemDeletarQuarto.addActionListener(eventos);
        itemCadastrarReservas.addActionListener(eventos);
        itemEditarReservas.addActionListener(eventos);
        itemDeletarReservas.addActionListener(eventos);
        itemConsultarReservas.addActionListener(eventos);
        itemConsultarQuarto.addActionListener(eventos);
        itemCadastrarHospedagens.addActionListener(eventos);
        itemEditarHospedagens.addActionListener(eventos);
        itemDeletarHospedagens.addActionListener(eventos);
        itemConsultarHospedagens.addActionListener(eventos);
        itemAjuda.addActionListener(eventos);
        itemAuditarQuarto.addActionListener(eventos);
        itemAuditarCliente.addActionListener(eventos);
        itemAuditarUsuario.addActionListener(eventos);
        itemAuditarReserva.addActionListener(eventos);
        itemAuditarHospedagem.addActionListener(eventos);
        itemCadastrarPasseios.addActionListener(eventos);
        itemEditarPasseios.addActionListener(eventos);
        itemDeletarPasseios.addActionListener(eventos);
        itemConsultarProduto.addActionListener(eventos);
        itemCadastrarProduto.addActionListener(eventos);
        itemEditarProduto.addActionListener(eventos);
        itemDeletarProduto.addActionListener(eventos);
        itemConsultarProduto.addActionListener(eventos);
        itemConsultarPasseios.addActionListener(eventos);
        itemConsultarPedidosQuarto.addActionListener(eventos);
        btnProdutos.addActionListener(eventos);
        btnSair.addActionListener(eventos);
        btnQuarto.addActionListener(eventos);
        btnCliente.addActionListener(eventos);
        btnReserva.addActionListener(eventos);
        btnHospedagem.addActionListener(eventos);
        btnPasseio.addActionListener(eventos);
        
        
        //Restrições de acesso dependendo da função do usuário.
        if (usuarioLogado.getFuncao().equals("Atendente")) {

       
            itemConsultarClientes.setEnabled(false);
            itemConsultarUsuarios.setEnabled(false);
            itemCadastrarUsuarios.setEnabled(false);
            itemEditarClientes.setEnabled(false);
            itemEditarUsuarios.setEnabled(false);
            itemDeletarCliente.setEnabled(false);
            itemDeletarUsuarios.setEnabled(false);
            itemDeletarQuarto.setEnabled(false);
            itemEditarQuarto.setEnabled(false);
            itemCadastrarQuarto.setEnabled(false);
            auditar.setEnabled(false);

        }
    }
 
    public static TelaPrincipal getInstance(Usuario usuario){
        if(instance == null){
            instance = new TelaPrincipal(usuario);
        }
        return instance;
    }
    
}
