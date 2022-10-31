package br.com.hospede.view;
import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.DAO.ReservaDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloHospedagem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ConsultarHospedagem extends InterfaceFactory{
    private JTable                 tabela;
    private JScrollPane            scroll;
    private final String[] coluna = {"Nome", "Cidade", "Muncipio", "Email","Telefone","Celular","Quarto","Crianças", "Adultos", "Entrada","Saída"};
    private String[][]             dado = { };
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ModeloHospedagem          modelo;
    public static ConsultarHospedagem instance = null;
    private ControlarEventos       eventos;
    private JLabel                 lblCpf, lblNome, lblFecharHospedagem, lblPedidoQuarto, lblHistorico, lblPassaporte;
    private JTextField             txtNome, txtPassaporte;
    private JButton                btnCadastrar, btnPesquisar, btnPedidoQuarto, btnFecharHospedagem, btnHistorico;
    private QuartoDAO              quartoDAO;
    private ReservaDAO             reservaDAO;
    private HistoricoDAO           auditarDAO;
    private ClienteDAO             clienteDAO;
    private ModeloHospedagem       modeloHospedagem;
    private EditarHospedagem       editarHospedagem;
    private MaskFormatter          mascararCPF;
    private JFormattedTextField    txtCpf;
    
    public ConsultarHospedagem(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 530);
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
        
            txtNome = new JTextField();
            txtNome.setSize(200, 30);
            txtNome.setLocation(70, 60);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        Icon imPedidoQuarto = new ImageIcon(getClass().getResource("imagens/PedidosQuarto.png"));
        Icon imFecharHospedagem = new ImageIcon(getClass().getResource("imagens/FecharHospedagem.png"));
        
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1250,50);
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(310,10);
        
        btnPedidoQuarto = new JButton(imPedidoQuarto);
        btnPedidoQuarto.setSize(80,50);
        btnPedidoQuarto.setLocation(740,10);
        
        lblPedidoQuarto = new JLabel("PEDIDOS DE QUARTO");
        lblPedidoQuarto.setSize(150,50);
        lblPedidoQuarto.setLocation(710,60);
        
        btnFecharHospedagem = new JButton(imFecharHospedagem);
        btnFecharHospedagem.setSize(80,50);
        btnFecharHospedagem.setLocation(890,10);
        
        lblFecharHospedagem = new JLabel("FECHAR HOSPEDAGEM");
        lblFecharHospedagem.setSize(150,50);
        lblFecharHospedagem.setLocation(860,60);
        
      
        modelo =  ModeloHospedagem.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1270, 350);
        scroll.setLocation(20, 110);
        
         JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(9).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(9).setMaxWidth(70);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);

        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
        

        //Adicionando componentes.
        add(scroll);
        add(lblCpf);
        add(txtCpf);
        add(btnCadastrar);
        add(btnPesquisar);
        add(btnPedidoQuarto);
        add(btnFecharHospedagem);
        add(lblFecharHospedagem);
        add(lblPedidoQuarto);
        add(lblHistorico);
        add(btnHistorico);
        
        botoes[39] = btnPesquisar;
        botoes[25] = btnCadastrar;
        botoes[30] = btnFecharHospedagem;
        botoes[45] = btnPedidoQuarto;
        botoes[74] = btnHistorico;
        add(txtPassaporte);
        add(lblPassaporte);
        
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnFecharHospedagem.addActionListener(eventos);
        btnPedidoQuarto.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int linha = tabela.getSelectedRow();
               int coluna = tabela.getSelectedColumn();
              
               
               if(coluna == 9){
                   //Imagem Editar para editar reserva.
                   clienteDAO = ClienteDAO.getInstance();
                   quartoDAO = QuartoDAO.getInstance();
                   
                   editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
                   
                   DTOCliente cliente = clienteDAO.consultarClientePorId(modelo.getHospedagem(linha).getId_cliente());
                   DTOQuarto quarto = quartoDAO.getQuartoPorID(modelo.getHospedagem(linha));
                   editarHospedagem.receberHospedagem(modelo.getHospedagem(linha), cliente, quarto);
                   editarHospedagem.setVisible(true);
                   
               } 
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
   public DTOCliente getPesquisa(){
        DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", txtCpf.getText(), "", "", "", "");
       cliente.setPassaport(txtPassaporte.getText());
      return cliente;
    }
    
    public static ConsultarHospedagem getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
