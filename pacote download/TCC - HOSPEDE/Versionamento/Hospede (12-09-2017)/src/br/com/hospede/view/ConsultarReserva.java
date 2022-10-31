package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOHistoricoReserva;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.DAO.ReservaDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloReserva;
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
    private JLabel                 lblPesquisa, lblHistorico;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    private QuartoDAO              quartoDAO;
    private ReservaDAO             reservaDAO;
    private HistoricoDAO             auditarDAO;
    private ClienteDAO             clienteDAO;
    private ModeloReserva          modeloReserva;
    private EditarReserva          editarReserva;
    private MaskFormatter          mascararCPF;
    
    public ConsultarReserva(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Reserva");
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
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HISTÓRICO DE AÇÕES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);

        //Adicionando componentes.
        add(scroll);
        add(lblPesquisa);
        add(txtPesquisa);
        add(btnCadastrar);
        add(btnPesquisar);
        add(btnHistorico);
        add(lblHistorico);
        
        botoes[37] = btnPesquisar;
        botoes[18] = btnCadastrar;
        botoes[73] = btnHistorico;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int linha = tabela.getSelectedRow();
               int coluna = tabela.getSelectedColumn();
              
               
               if(coluna == 9){
                   //Imagem Editar para editar reserva.
                   clienteDAO = ClienteDAO.getInstance();
                   
                   editarReserva = EditarReserva.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
                   editarReserva.receberReserva(modelo.getReserva(linha));
                   DTOCliente cliente = clienteDAO.consultarClientePorId(modelo.getReserva(linha).getId_cliente());
                   editarReserva.receberliente(cliente);
                   editarReserva.setVisible(true);
                   
               } else if(coluna == 10){
                   quartoDAO = QuartoDAO.getInstance();
                   reservaDAO = ReservaDAO.getInstance();
                   
            
               //Alterando o quarto para livre.
               DTOReserva reserva = modelo.getReserva(linha);//Pegando o ID do quarto para ser alterado.
               
               DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
               
               try{
               //Verifica se o quarto foi encontrado. Caso não seja, exibe uma mensagem de alerta sobre o nome fornecido no campo nome, visto que aconterá um erro por causa do campo em branco.
               if(quarto.getNumero().equals("")){
                       
               } else  {
               quarto.setSituacao("LIVRE");
               quartoDAO.editarQuarto(quarto);
               
               boolean deletou = false;
               //Em sequência, deletar a reserva.
               deletou = reservaDAO.deletarReserva(reserva);
               
               if(deletou != false ){
                   //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                    auditarDAO = HistoricoDAO.getInstance();
                    historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    historicoReserva.setLogin(usuarioLogado.getLogin());
                    historicoReserva.setAcao("Deletou");
                    historicoReserva.setTipo_reserva("RESERVAR");
                    
                    //Buscando cliente.
                    clienteDAO = ClienteDAO.getInstance();
                    DTOCliente cliente = clienteDAO.consultarClientePorId(modelo.getReserva(linha).getId_cliente());
                    historicoReserva.setCliente_reservado(cliente.getNome());
                    
                    //Obter o ID da reserva.
                    historicoReserva.setId_reserva(modelo.getReserva(linha).getId_reserva());
                    historicoReserva.setCliente_reservado(cliente.getNome());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterReserva(historicoReserva);
                    
                    modeloReserva = ModeloReserva.getInstance();
                    modeloReserva.remover(linha);
                    modeloReserva.adicionarLista(reservaDAO.listarReservas());
                    
               }
               
               
               }
               }catch(Exception erro){
                  
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
    
    public static ConsultarReserva getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarReserva( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
