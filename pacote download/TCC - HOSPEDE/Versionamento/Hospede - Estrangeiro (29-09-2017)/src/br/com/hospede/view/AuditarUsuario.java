package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloAuditarUsuario;
import com.toedter.calendar.JDateChooser;
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
    private JLabel               lblUsuario, lblDia;
    private JTextField           txtUsuario;
    private ModeloAuditarUsuario modelo;
    private JTable               tabela;
    private JScrollPane          scroll;
    private JButton              btnListar; 
    private JDateChooser        dia;
    public static AuditarUsuario instance = null;
    
    public AuditarUsuario(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Auditar Usuário");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(145, 60, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.camposDeTexto = camposDeTexto;
        
        //Criando label.
        //Criando label.
        lblUsuario = new JLabel("Usuário");
        lblUsuario.setSize(100,20);
        lblUsuario.setLocation(20,20);
        
        lblDia = new JLabel("Dia:");
        lblDia.setSize(100,20);
        lblDia.setLocation(20,60);
         //Criando campos de texto.
        txtUsuario = new JTextField(200);
        txtUsuario.setSize(200,30);
        txtUsuario.setLocation(70,20);
        
        dia = new JDateChooser();
        dia.setSize(200, 30);
        dia.setLocation(70, 60);

        modelo = ModeloAuditarUsuario.getInstance();
        tabela = new JTable(modelo);
        tabela.setEnabled(false);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1055, 450);
        scroll.setLocation(20, 100);
        
        tabela.getColumnModel().getColumn(0).setMaxWidth(250);
        tabela.getColumnModel().getColumn(1).setMaxWidth(250);
        tabela.getColumnModel().getColumn(2).setMaxWidth(250);
        tabela.getColumnModel().getColumn(3).setMaxWidth(370);
        tabela.setRowHeight(30);
        
        btnListar = new JButton("Listar clientes");
        btnListar.setSize(150,20);
        btnListar.setLocation(920,60);
        btnListar.setToolTipText("Lista todos os clientes cadastrados no sistema.");

        //Adicionando componentes.
        add(scroll);
        add(lblUsuario);
        add(txtUsuario);
    }
    
    public static AuditarUsuario getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new AuditarUsuario(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        
        return instance;
    }
}
