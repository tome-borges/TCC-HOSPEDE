package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.DAO.PassageiroDAO;
import br.com.hospede.model.DAO.PasseioDAO;
import br.com.hospede.model.DTO.DTOHistoricoPasseio;
import br.com.hospede.model.DTO.DTOPasseio;
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
    private JLabel                 lblPesquisa, lblPassageiros, lblHistorico;
    private JButton                btnCadastrar, btnPesquisar, btnPassageiro, btnHistorico;
    private JTextField             txtPesquisa;
    private ControlarEventos       eventos = null;
    public static ConsultarPasseio      instance;
    private ModeloPasseio           modeloPasseio;
    private EditarPasseio           editarPasseio;
    private PasseioDAO              passeioDAO;
    private InterfaceFactory        interfaceEditarPasseio;
    private ModeloPassageiros        modeloPassageiro;
    private PassageiroDAO           passageiroDAO;
    private HistoricoDAO            historicoDAO;
    
    public ConsultarPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, DTOUsuario usuarioLogado){
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
        
        //Criando bot??o.
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
        
        Icon imHistorico = new ImageIcon(getClass().getResource("imagens/Historico.png"));
        btnHistorico = new JButton(imHistorico);
        btnHistorico.setSize(80,50);
        btnHistorico.setLocation(590,10);
        
        lblHistorico = new JLabel("HIST??RICO DE A????ES");
        lblHistorico.setSize(150,50);
        lblHistorico.setLocation(560,60);
        
        add(btnCadastrar);
        add(btnPesquisar);
        add(lblPesquisa);
        add(txtPesquisa);
        add(lblPassageiros);
        add(btnPassageiro);
        add(scroll);
        add(btnHistorico);
        add(lblHistorico);
        
        botoes[67] = btnCadastrar;
        botoes[68] = btnPesquisar;
        botoes[69] = btnPassageiro;
        
        
        //Associando os bot??es a classe que controla eventos.
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
                    
                    if (interfaceEditarPasseio == null || interfaceEditarPasseio.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
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
                    historicoDAO = new HistoricoDAO();
                    
                    boolean deletou = false;
                    deletou =  passeioDAO.deletarPasseio(modeloPasseio.getPasseio(linha));
            
            try{
                if(deletou != false){
                DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                DTOPasseio passeioEditar = modeloPasseio.getPasseio(linha);
                historicoPasseio.setId_passeio(passeioEditar.getId());
                historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                historicoPasseio.setAcao("Deletou");
                
                historicoDAO.salvarManterPasseio(historicoPasseio);
                }
            }catch(Exception erro){
                
            }
                   
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
    
    public static ConsultarPasseio getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new ConsultarPasseio(botoe, itensDeMenu, camposDeTexto,  usuarioLogado);
        }
        return instance;
    }
}