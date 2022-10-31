package br.com.hospede.view;
import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.AuditarDAO;
import br.com.hospede.model.dao.ClienteDAO;
import br.com.hospede.model.dao.QuartoDAO;
import br.com.hospede.model.dao.ReservaDAO;
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
    private JLabel                 lblPesquisa, lblFecharHospedagem, lblPedidoQuarto;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar, btnPedidoQuarto, btnFecharHospedagem;
    private QuartoDAO              quartoDAO;
    private ReservaDAO             reservaDAO;
    private AuditarDAO             auditarDAO;
    private ClienteDAO             clienteDAO;
    private ModeloHospedagem       modeloHospedagem;
    private EditarHospedagem       editarHospedagem;
    private MaskFormatter          mascararCPF;
    
    public ConsultarHospedagem(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 508);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;  
        
        //Criando label.
        lblPesquisa = new JLabel("CPF:");
        lblPesquisa.setSize(100,20);
        lblPesquisa.setLocation(20,20);
        
         //Criando campos de texto.
        try{
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtPesquisa = new JFormattedTextField(mascararCPF);
            txtPesquisa.setSize(200, 20);
            txtPesquisa.setLocation(70, 20);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
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
        btnPedidoQuarto.setLocation(590,10);
        
        lblPedidoQuarto = new JLabel("PEDIDOS DE QUARTO");
        lblPedidoQuarto.setSize(150,50);
        lblPedidoQuarto.setLocation(560,60);
        
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
        tabela.getColumnModel().getColumn(10).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(10).setMaxWidth(70);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);

        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        

        //Adicionando componentes.
        add(scroll);
        add(lblPesquisa);
        add(txtPesquisa);
        add(btnCadastrar);
        add(btnPesquisar);
        add(btnPedidoQuarto);
        add(btnFecharHospedagem);
        add(lblFecharHospedagem);
        add(lblPedidoQuarto);
        
        botoes[37] = btnPesquisar;
        botoes[25] = btnCadastrar;
        botoes[30] = btnFecharHospedagem;
        botoes[45] = btnPedidoQuarto;
        
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnFecharHospedagem.addActionListener(eventos);
        btnPedidoQuarto.addActionListener(eventos);
        
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
                   
                   Cliente cliente = clienteDAO.consultarClientePorId(modelo.getHospedagem(linha).getId_cliente());
                   Quarto quarto = quartoDAO.getQuartoPorID(modelo.getHospedagem(linha));
                   editarHospedagem.receberHospedagem(modelo.getHospedagem(linha), cliente, quarto);
                   editarHospedagem.setVisible(true);
                   
               } else if(coluna == 10){
                  quartoDAO = QuartoDAO.getInstance();
                  reservaDAO = ReservaDAO.getInstance();
                   
              
               //Alterando o quarto para livre.
               Reserva reserva = modelo.getHospedagem(linha);//Pegando o ID do quarto para ser alterado.
               
               Quarto quarto = quartoDAO.getQuartoPorID(reserva);
               
               try{
               //Verifica se o quarto foi encontrado. Caso não seja, exibe uma mensagem de alerta sobre o nome fornecido no campo nome, visto que aconterá um erro por causa do campo em branco.
               if(quarto.getNumero().equals("")){
                       
               } else  {
               quarto.setSituacao("LIVRE");
               quartoDAO.editarQuarto(quarto);
               
               //Em sequência, deletar a reserva.
               boolean deletou = false;
               deletou = reservaDAO.deletarHospedagem(reserva);
               
               if(deletou != false){
                   
                     //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    br.com.hospede.model.DTO.AuditarHospedagem auditarHospedagem = new br.com.hospede.model.DTO.AuditarHospedagem();
                    auditarDAO = AuditarDAO.getInstance();
                    auditarHospedagem.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    auditarHospedagem.setLogin(usuarioLogado.getLogin());
                    auditarHospedagem.setAcao("Deletou");
                    auditarHospedagem.setTipo_reserva("HOSPEDAR");
                    
                    //Obter o ID da reserva.
                    auditarHospedagem.setId_hospedagem(modelo.getHospedagem(linha).getId_reserva());
                    auditarHospedagem.setCliente_hospedado(modelo.getHospedagem(linha).getNome_cliente());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterHospedagem(auditarHospedagem);
                   
               editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
               editarHospedagem.dispose();
               editarHospedagem.limparCampos();
               modeloHospedagem = ModeloHospedagem.getInstance();
               modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
               }
               }
               }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, "Hospedagem não encontrada. Certifique-se se o cliente está reservado.");
               }
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
    
    public String getPesquisa(){
        return txtPesquisa.getText();
    }
    
    public static ConsultarHospedagem getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
