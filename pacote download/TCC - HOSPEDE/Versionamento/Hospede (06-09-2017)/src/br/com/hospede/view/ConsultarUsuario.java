package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.UsuarioDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloUsuario;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ConsultarUsuario extends InterfaceFactory{
    //Objetos.
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloUsuario          modelo;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static JTextField[]     camposDeTexto;
    private ControlarEventos       eventos;
    private JLabel                 lblPesquisa;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar;
    public static ConsultarUsuario instance=null;
    
    private InterfaceFactory interfaceEditarUsuario;
    private EditarUsuario    editarUsuario;
    private UsuarioDAO       usuarioDAO;
    private ModeloUsuario    modeloUsuario;
    
    //Construtor.
    public ConsultarUsuario(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 508);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        
        //Criando label.
        lblPesquisa = new JLabel("Login:");
        lblPesquisa.setSize(100,20);
        lblPesquisa.setLocation(20,20);
        
        //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,20);
        txtPesquisa.setLocation(90,20);

        //Construindo tabela.
        modelo = ModeloUsuario.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1105, 350);
        scroll.setLocation(90, 100);
        
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(5).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(6).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(312,10);
       
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1150,50);
      

        //Adicionando componentes.
        add(scroll);
        add(lblPesquisa);
        add(txtPesquisa);
        add(btnCadastrar);
        add(btnPesquisar);
        
        camposDeTexto[2] = txtPesquisa;
        botoes[33]       = btnPesquisar;
        botoes[34]       = btnCadastrar;
        
        
        //Associando eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        txtPesquisa.addActionListener(eventos);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                
                if(coluna == 5){
                    //Exibir a classe "EditarUsuario"
                    int EDITAR_USUARIO     = 5;
                    if(interfaceEditarUsuario == null || interfaceEditarUsuario.isValid()){
                       interfaceEditarUsuario = InterfaceFactory.getInterface(EDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                       editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
                       modeloUsuario = ModeloUsuario.getInstance();
                       
                       //Envia o usuário para a tela de edição.
                       editarUsuario.recebeUsuario(modeloUsuario.getUsuario(linha));
                       interfaceEditarUsuario.setVisible(true);
        
                    } else {
                        //Envia o usuário para a tela de edição.
                        editarUsuario.recebeUsuario(modeloUsuario.getUsuario(linha));
                        interfaceEditarUsuario.setVisible(true);
                    }
                    
                }else if(coluna == 6){
                    //Deletar usuário do banco.
                    usuarioDAO = UsuarioDAO.getInstance();
                    modeloUsuario = ModeloUsuario.getInstance();
                    usuarioDAO.deletarUsuario(modeloUsuario.getUsuario(linha).getLogin());
                    modeloUsuario.remover(linha);
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
    
    
     
    
    public static ConsultarUsuario getInstance(JButton botoes[], JMenuItem itensDeMenu[],  JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new ConsultarUsuario(botoes, itensDeMenu,  camposDeTexto, usuarioLogado);
        }
        return instance;
    }

}
