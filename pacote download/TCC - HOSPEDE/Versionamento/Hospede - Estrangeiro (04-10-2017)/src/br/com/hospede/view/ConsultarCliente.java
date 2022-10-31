package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOHistoricoCliente;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloCliente;
import br.com.hospede.model.modeloTabela.ModeloQuarto;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
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
    private JLabel                 lblCpf, lblHistorico, lblNome;
    private JTextField             txtNome;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    private ControlarEventos       eventos = null;
    public static ConsultarCliente instance = null;
    private MaskFormatter          mascararCPF;
    private ModeloCliente          modeloCliente;
    private InterfaceFactory       interfaceEditarCliente;
    private EditarCliente          editarCliente;
    private ClienteDAO             clienteDAO;
    private HistoricoDAO           auditarDAO;
    private JFormattedTextField    txtCpf;
    
    public ConsultarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Cliente");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 530);
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
        tabela.getColumnModel().getColumn(13).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(13).setMaxWidth(70);
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
        add(lblCpf);
        add(txtCpf);
        add(lblNome);
        add(txtNome);
        add(btnCadastrar);
        add(btnPesquisar);
        add(lblHistorico);
        add(btnHistorico);
        
        
        botoes[35] = btnPesquisar;
        botoes[72] = btnHistorico;
        botoes[8] = btnCadastrar;
        camposDeTexto[21] = txtCpf;
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        txtCpf.addFocusListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
     
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                modeloCliente = ModeloCliente.getInstance();
                editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                int EDITAR_CLIENTE     = 9;
                
                if(coluna == 12){
                    
                    if (interfaceEditarCliente == null || interfaceEditarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                        interfaceEditarCliente = InterfaceFactory.getInterface(EDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarCliente.receberCliente(modeloCliente.getCliente(linha));
                        interfaceEditarCliente.setVisible(true);
       
                      } else {
                        editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarCliente.receberCliente(modeloCliente.getCliente(linha));
                        interfaceEditarCliente.setVisible(true);
                     }
                    
                  } else if(coluna == 13){
                    //Deletar usuário do banco.
                    InterfaceFactory confirmar = InterfaceFactory.getInterface(51, botoes, itensDeMenu, campoDeTexto, usuarioLogado);
                    confirmar.setVisible(true);
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
        return new DTOCliente(0, 0, txtNome.getText(), "", "", "", "", "", "", txtCpf.getText(), "", "", "", "");
    }
    
    public void limparMascaraCpf() throws ParseException{
           mascararCPF.setCommitsOnValidEdit(false);
           txtCpf.setText("");
    }
    
    public DTOCliente getCliente(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloCliente = ModeloCliente.getInstance();
        return modeloCliente.getCliente(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    public static ConsultarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarCliente(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
