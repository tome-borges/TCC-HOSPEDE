package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloAuditarUsuario;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AuditarUsuario extends InterfaceFactory{
    
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];;
    public JTextField[]          camposDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public JMenuItem[]           itensDeMenu   = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JLabel               lblPesquisa;
    private JTextField           txtPesquisa;
    private ModeloAuditarUsuario modelo;
    private JTable               tabela;
    private JScrollPane          scroll;
    private JButton              btnListar; 
    public static AuditarUsuario instance = null;
    
    public AuditarUsuario(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Auditar Quarto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(145, 60, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.camposDeTexto = camposDeTexto;
        
        //Criando label.
        lblPesquisa = new JLabel("Pesquisar:");
        lblPesquisa.setSize(100,20);
        lblPesquisa.setLocation(20,20);
        
         //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,20);
        txtPesquisa.setLocation(90,20);

        modelo = ModeloAuditarUsuario.getInstance();
        tabela = new JTable(modelo);
        tabela.setEnabled(false);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1055, 450);
        scroll.setLocation(20, 100);
        
        tabela.getColumnModel().getColumn(0).setMaxWidth(80);
        tabela.getColumnModel().getColumn(1).setMaxWidth(80);
        tabela.getColumnModel().getColumn(2).setMaxWidth(80);
        tabela.setRowHeight(30);
        
        btnListar = new JButton("Listar clientes");
        btnListar.setSize(150,20);
        btnListar.setLocation(920,60);
        btnListar.setToolTipText("Lista todos os clientes cadastrados no sistema.");

        //Adicionando componentes.
        add(scroll);
        add(lblPesquisa);
        add(txtPesquisa);
    }
    
    public static AuditarUsuario getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new AuditarUsuario(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        
        return instance;
    }
}
