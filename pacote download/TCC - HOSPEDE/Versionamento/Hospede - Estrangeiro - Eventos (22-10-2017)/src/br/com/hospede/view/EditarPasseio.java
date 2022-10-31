package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.PassageiroDAO;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditarPasseio extends InterfaceFactory{
    
    private JTextField    txtTitulo, txtIngresso;
    private JTextArea     txtDescricao;
    private JDateChooser  data;
    private ModeloPassageiros modelo;
    private JTable            tabela;
    private JScrollPane       scroll;
    private JLabel        lblTitulo, lblDescricao, lblIngresso, lblData, lblPassageiros;
    private JButton       btnSalvar;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static EditarPasseio instance = null;
    private ControlarEventos eventos;
    private ModeloPassageiros modeloPassageiro;
    private PassageiroDAO passageiroDAO;
    private DTOPasseio passeio;
    
    public EditarPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Editar Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 50, 1065, 650);
        setLayout(null);
        setResizable(false);
        
        
        //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 610);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        lblTitulo = new JLabel("Título");
        lblTitulo.setSize(50, 20);
        lblTitulo.setLocation(90, 50);
        
        txtTitulo = new JTextField();
        txtTitulo.setSize(250, 30);
        txtTitulo.setLocation(130, 50);
        
        lblIngresso = new JLabel("Ingresso R$");
        lblIngresso.setSize(70, 20);
        lblIngresso.setLocation(430, 50);
        
        txtIngresso = new JTextField();
        txtIngresso.setSize(70, 30);
        txtIngresso.setLocation(520, 50);
        
        lblData = new JLabel("Data");
        lblData.setSize(70, 20);
        lblData.setLocation(600, 50);
        
        data = new JDateChooser();
        data.setSize(130, 30);
        data.setLocation(640, 50);
        
        lblDescricao = new JLabel("Descrição");
        lblDescricao.setSize(100, 20);
        lblDescricao.setLocation(50, 90);
        
        txtDescricao = new JTextArea();
        txtDescricao.setSize(950, 200);
        txtDescricao.setLocation(50, 110);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setSize(100, 30);
        btnSalvar.setLocation(50, 570);
        
        lblPassageiros = new JLabel("Passageiros");
        lblPassageiros.setSize(100, 30);
        lblPassageiros.setLocation(50, 320);
        
        modelo = ModeloPassageiros.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(950, 150);
        scroll.setLocation(50, 350);
        
        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);
        tabela.getTableHeader().setReorderingAllowed(false);
//        
        
        //Adicionando componentes no painel.
        add(painel);
        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblIngresso);
        painel.add(txtIngresso);
        painel.add(lblData);
        painel.add(data);
        painel.add(lblDescricao);
        painel.add(txtDescricao);
        painel.add(btnSalvar);
        painel.add(scroll);
        painel.add(lblPassageiros);
        
        botoes[51] = btnSalvar;
        modelosTabelas[7] = tabela;
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        
        btnSalvar.addActionListener(eventos);
        
        tabela.addMouseListener(eventos);
        
    }
    
    public void receberPasseio(DTOPasseio passeio){
        txtTitulo.setText(passeio.getTitulo());
        txtIngresso.setText(Double.toString(passeio.getIngresso()));
        try{
            
        data.setDate(passeio.getData());
        txtDescricao.setText(passeio.getDescricao());
        }catch(Exception erro){
            
        }
        
        this.passeio = passeio;
    }
    
     public JTable getTabela(){
        return tabela;
    }
    
    
    public DTOPasseio getPasseio(){
       DTOPasseio passeio = new DTOPasseio();
        
       passeio.setTitulo(txtTitulo.getText());
       
       passeio.setDescricao(txtDescricao.getText());
       passeio.setId(this.passeio.getId());
       try{
           passeio.setData(data.getDate());
           passeio.setIngresso(Double.parseDouble(txtIngresso.getText()));
           passeio.setStatus(this.passeio.getStatus());
           
       }catch(Exception erro){
           
       }
       return passeio; 
    }
    
    
    public void limparCampos(){
        txtTitulo.setText("");
        txtIngresso.setText("");
        txtDescricao.setText("");
    }
    
    public static EditarPasseio getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            instance = new EditarPasseio(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
