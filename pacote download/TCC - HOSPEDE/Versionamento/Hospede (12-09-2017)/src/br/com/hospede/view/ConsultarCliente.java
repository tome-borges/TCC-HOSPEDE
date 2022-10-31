package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOHistoricoCliente;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloCliente;
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

public class ConsultarCliente extends InterfaceFactory{
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloCliente          modelo;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private JLabel                 lblPesquisa, lblHistorico;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    private ControlarEventos       eventos = null;
    public static ConsultarCliente instance = null;
    private MaskFormatter          mascararCPF;
    private ModeloCliente          modeloCliente;
    private InterfaceFactory       interfaceEditarCliente;
    private EditarCliente          editarCliente;
    private ClienteDAO             clienteDAO;
    private HistoricoDAO             auditarDAO;
    
    public ConsultarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Cliente");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 508);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.campoDeTexto = camposDeTexto;
        
        //Criando label.
        lblPesquisa = new JLabel("CPF:");
        lblPesquisa.setSize(100,20);
        lblPesquisa.setLocation(20,20);
        
        try{
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtPesquisa = new JFormattedTextField(mascararCPF);
            txtPesquisa.setSize(200, 20);
            txtPesquisa.setLocation(70, 20);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }

        modelo = ModeloCliente.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1270, 350);
        scroll.setLocation(20, 100);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(3).setMaxWidth(60);
        tabela.getColumnModel().getColumn(7).setMaxWidth(70);
        tabela.getColumnModel().getColumn(12).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(12).setMaxWidth(70);
        tabela.getColumnModel().getColumn(11).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(11).setMaxWidth(70);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1250,50);
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(290,10);
        
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
        add(lblHistorico);
        add(btnHistorico);
        
        
        botoes[35] = btnPesquisar;
        botoes[72] = btnHistorico;
        botoes[8] = btnCadastrar;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
     
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                modeloCliente = ModeloCliente.getInstance();
                editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                int EDITAR_CLIENTE     = 9;
                
                if(coluna == 11){
                    
                    if (interfaceEditarCliente == null || interfaceEditarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                        interfaceEditarCliente = InterfaceFactory.getInterface(EDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarCliente.receberCliente(modeloCliente.getCliente(linha));
                        interfaceEditarCliente.setVisible(true);
       
                      } else {
                        editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarCliente.receberCliente(modeloCliente.getCliente(linha));
                        interfaceEditarCliente.setVisible(true);
                     }
                    
                  } else if(coluna == 12){
                    //Deletar usuário do banco.
                    clienteDAO = ClienteDAO.getInstance();
                    modeloCliente = ModeloCliente.getInstance();
                    
                    boolean deletou = false;
                    deletou = clienteDAO.deletarCliente(modeloCliente.getCliente(linha).getCpf());
                    
                    if(deletou != false){
                        
                        DTOHistoricoCliente historicoCliente = new DTOHistoricoCliente();
                        auditarDAO = HistoricoDAO.getInstance();
                        
                        //Pegando o ID do cliente Deletado.
                        historicoCliente.setId_cliente(modeloCliente.getCliente(linha).getId_cliente());
                        historicoCliente.setNome_cliente(modeloCliente.getCliente(linha).getNome());
                        historicoCliente.setId_usuario(usuarioLogado.getId_usuario());
                        historicoCliente.setAcao("Deletou");
                        
                        auditarDAO.salvarManterCliente(historicoCliente);
                        
                    }
                    
                    modeloCliente.remover(linha);
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
    
    public static ConsultarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarCliente(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
