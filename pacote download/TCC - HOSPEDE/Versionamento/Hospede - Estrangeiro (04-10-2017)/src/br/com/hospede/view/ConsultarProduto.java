package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.ProdutoDAO;
import br.com.hospede.model.dto.DTOHistoricoProduto;
import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloProduto;
import br.com.hospede.model.modeloTabela.ModeloQuarto;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ConsultarProduto extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloProduto          modelo;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private JLabel                 lblPesquisa, lblHistorico;
    private JTextField             txtPesquisa;
    private JButton                btnCadastrar, btnPesquisar, btnHistorico;
    private ControlarEventos       eventos = null;
    public static ConsultarProduto instance = null;
    private ModeloProduto          modeloProduto;
    private EditarProduto          editarProduto;
    private InterfaceFactory       interfaceEditarProduto;
    private ProdutoDAO             produtoDAO;
    private HistoricoDAO           historicoDAO;
    
    public ConsultarProduto(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Produto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 530);setBounds(16, 187, 1325, 508);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.campoDeTexto = camposDeTexto;
        
         //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,20);
        txtPesquisa.setLocation(90,20);

        modelo = ModeloProduto.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1055, 330);
        scroll.setLocation(130, 100);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(5).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(6).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        lblPesquisa = new JLabel("Nome:");
        lblPesquisa.setSize(200,20);
        lblPesquisa.setLocation(30,20);
        
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(100,20);
        txtPesquisa.setLocation(100,20);
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(220,10);
        
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1150,50);
        
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
        
        botoes[65] = btnCadastrar;
        botoes[66] = btnPesquisar;
        botoes[75] = btnHistorico;
   
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnHistorico.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
     
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                modeloProduto = ModeloProduto.getInstance();
                editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                int EDITAR_PRODUTO = 37;
                
                if(coluna == 5){
                    
                    if (interfaceEditarProduto == null || interfaceEditarProduto.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                        interfaceEditarProduto = InterfaceFactory.getInterface(EDITAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarProduto.receberProduto(modeloProduto.getProduto(linha));
                        interfaceEditarProduto.setVisible(true);
       
                      } else {
                        editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarProduto.receberProduto(modeloProduto.getProduto(linha));
                        interfaceEditarProduto.setVisible(true);
                     }
                    
                  } else if(coluna == 6){
                    //Deletar usuário do banco.
                    InterfaceFactory confirmar = InterfaceFactory.getInterface(53, botoes, itensDeMenu, campoDeTexto, usuarioLogado);
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
    
    public String getPesquisa(){
        return txtPesquisa.getText();
    }
    
     public DTOProduto getProduto(){
        int linha = tabela.getSelectedRow();
        int coluna = tabela.getSelectedColumn();
        modeloProduto = ModeloProduto.getInstance();
        return modeloProduto.getProduto(linha);
    }
    
    public int getLinhaSelecionada(){
       int linha = tabela.getSelectedRow();
       return linha;
    }
    
    
    public static ConsultarProduto getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new ConsultarProduto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
