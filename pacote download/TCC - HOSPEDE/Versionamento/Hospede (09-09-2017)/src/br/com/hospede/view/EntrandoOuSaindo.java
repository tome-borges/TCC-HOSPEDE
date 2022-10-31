package br.com.hospede.view;

import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.EntrandoOuSaindoDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloEntrandoHoje;
import br.com.hospede.model.modeloTabela.ModeloSaindoHoje;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class EntrandoOuSaindo extends InterfaceFactory{
    
    private JTable                 tabelaEntrando, tabelaSaindo;
    private JLabel                 pesquisa, lblEntrando, lblSaindo, imagemEntrando, imagemSaindo;
    private JTextField             txtPesquisa;
    private JButton                btnPesquisar;
    private JScrollPane            scroolEntrando, scroolSaindo;
    public static EntrandoOuSaindo instance = null;
    private ModeloEntrandoHoje     modeloEntrando;
    private ModeloSaindoHoje       modeloSaindo;
    private EntrandoOuSaindoDAO    entrandoSaindoDAO;
 
    
    public EntrandoOuSaindo(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Entrando e Saindo Hoje");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        
        //Iniciando imagens.
        Icon imgSaindo = new ImageIcon(getClass().getResource("imagens/SaindoHoje.png"));
        Icon imgEntrando = new ImageIcon(getClass().getResource("imagens/EntrandoHoje.png"));
       
         //Inicializando labels.
        pesquisa = new JLabel("Pesquisar:");
        pesquisa.setSize(100, 20);
        pesquisa.setLocation(25, 45);
        
        lblEntrando = new JLabel("Entrando:");
        lblEntrando.setSize(100, 20);
        lblEntrando.setLocation(625, 45);
        
        imagemEntrando = new JLabel(imgEntrando);
        imagemEntrando.setSize(100, 30);
        imagemEntrando.setLocation(670, 45);
        
        lblSaindo = new JLabel("Saindo:");
        lblSaindo.setSize(100, 20);
        lblSaindo.setLocation(825, 45);
        
        imagemSaindo = new JLabel(imgSaindo);
        imagemSaindo.setSize(100, 30);
        imagemSaindo.setLocation(870, 45);

        //Inicializando caixa de texto.
        txtPesquisa = new JTextField();
        txtPesquisa.setSize(300, 20);
        txtPesquisa.setLocation(90, 45);

        //Inicializando botões
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setSize(100, 20);
        btnPesquisar.setLocation(450, 45);
        
        
        //Construindo tabela.
        //Tabelas com clientes entrando hoje.
        modeloEntrando =  ModeloEntrandoHoje.getInstance();
        tabelaEntrando = new JTable(modeloEntrando);
        scroolEntrando = new JScrollPane(tabelaEntrando);
        scroolEntrando.setSize(1055,200);
        scroolEntrando.setLocation(20,100);
        
        //Tabelas com clientes saindo hoje.
        modeloSaindo = ModeloSaindoHoje.getInstance();
        tabelaSaindo = new JTable(modeloSaindo);
        scroolSaindo = new JScrollPane(tabelaSaindo);
        scroolSaindo.setSize(1055,200);
        scroolSaindo.setLocation(20,350);
        
        //Configurando atributos das tabelas.
        JTableRenderer renderer = new JTableRenderer();
        tabelaEntrando.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaEntrando.getColumnModel().getColumn(0).setMaxWidth(60);
        
        tabelaSaindo.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tabelaSaindo.getColumnModel().getColumn(0).setMaxWidth(60);


        tabelaEntrando.setRowHeight(23);
        tabelaSaindo.setRowHeight(23);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        //Adicionando componentes.
        add(pesquisa);
        add(txtPesquisa);
        add(lblEntrando);
        add(lblSaindo);
        add(imagemEntrando);
        add(imagemSaindo);
        add(btnPesquisar);
        add(scroolEntrando);
        add(scroolSaindo);
        
    }
    
    public static EntrandoOuSaindo getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new EntrandoOuSaindo(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
