package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.PassageiroDAO;
import br.com.hospede.model.dao.PasseioDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import br.com.hospede.model.modeloTabela.ModeloPasseio;
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

public class ConsultarPasseio extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloPasseio           modelo;
    private JLabel                 lblPesquisa, lblPassageiros;
    private JButton                btnCadastrar, btnPesquisar, btnPassageiro;
    private JTextField             txtPesquisa;
    private ControlarEventos       eventos = null;
    public static ConsultarPasseio      instance;
    private ModeloPasseio           modeloPasseio;
    private EditarPasseio           editarPasseio;
    private PasseioDAO              passeioDAO;
    private InterfaceFactory        interfaceEditarPasseio;
    private ModeloPassageiros        modeloPassageiro;
    private PassageiroDAO           passageiroDAO;
    
    public ConsultarPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, Usuario usuarioLogado){
        setTitle("Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(16, 187, 1325, 508);
        setLayout(null);
        setResizable(false);
       
        lblPesquisa = new JLabel("Nome:");
        lblPesquisa.setSize(200,20);
        lblPesquisa.setLocation(30,20);
        
         //Criando campos de texto.
        txtPesquisa = new JTextField(200);
        txtPesquisa.setSize(200,20);
        txtPesquisa.setLocation(90,20);
        
        modelo =  ModeloPasseio.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1055, 330);
        scroll.setLocation(130, 100);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(3).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        
        Icon imPesquisar = new ImageIcon(getClass().getResource("imagens/Lupa.gif"));
        Icon imCadastrar = new ImageIcon(getClass().getResource("imagens/Adicionar.png"));
        
        btnPesquisar = new JButton(imPesquisar);
        btnPesquisar.setSize(80,50);
        btnPesquisar.setLocation(310,10);
        
        //Criando botão.
        btnCadastrar = new JButton(imCadastrar);
        btnCadastrar.setSize(40,40);
        btnCadastrar.setLocation(1150,50);
        
        Icon imPassageiro = new ImageIcon(getClass().getResource("imagens/Passageiros.png"));
        
        btnPassageiro = new JButton(imPassageiro);
        btnPassageiro.setSize(80,50);
        btnPassageiro.setLocation(740,10);
        
        lblPassageiros = new JLabel("ADICIONAR PASSAGEIRO");
        lblPassageiros.setSize(150,50);
        lblPassageiros.setLocation(710,60);
        
        add(btnCadastrar);
        add(btnPesquisar);
        add(lblPesquisa);
        add(txtPesquisa);
        add(lblPassageiros);
        add(btnPassageiro);
        add(scroll);
        
        botoes[67] = btnCadastrar;
        botoes[68] = btnPesquisar;
        botoes[69] = btnPassageiro;
        
        
        //Associando os botões a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnPesquisar.addActionListener(eventos);
        btnPassageiro.addActionListener(eventos);
        
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
     
                int linha = tabela.getSelectedRow();
                int coluna = tabela.getSelectedColumn();
                modeloPasseio = ModeloPasseio.getInstance();
                editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                int EDITAR_PASSEIOS = 33;
                
                if(coluna == 3){
                    
                    if (interfaceEditarPasseio == null || interfaceEditarPasseio.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                        interfaceEditarPasseio = InterfaceFactory.getInterface(EDITAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarPasseio.receberPasseio(modeloPasseio.getPasseio(linha));
                        interfaceEditarPasseio.setVisible(true);
                        
                        //Obtendo passageiros do passeio selecionado
                        modeloPassageiro = ModeloPassageiros.getInstance();
                        passageiroDAO = PassageiroDAO.getInstance();
                        
                        modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(modeloPasseio.getPasseio(linha)));
       
                      } else {
                        editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        editarPasseio.receberPasseio(modeloPasseio.getPasseio(linha));
                        modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(modeloPasseio.getPasseio(linha)));
                        interfaceEditarPasseio.setVisible(true);
                     }
                    
                  } else if(coluna == 4){
                    
                    passeioDAO = PasseioDAO.getInstance();
                    modeloPasseio = ModeloPasseio.getInstance();
                    passeioDAO.deletarPasseio(modeloPasseio.getPasseio(linha));
                    modeloPasseio.remover(linha);
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
    
    public static ConsultarPasseio getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, Usuario usuarioLogado){
        if(instance == null){
            instance = new ConsultarPasseio(botoe, itensDeMenu, camposDeTexto,  usuarioLogado);
        }
        return instance;
    }
}
