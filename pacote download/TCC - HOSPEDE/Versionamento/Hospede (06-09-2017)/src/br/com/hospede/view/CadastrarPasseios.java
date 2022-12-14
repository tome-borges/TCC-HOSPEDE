package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOPasseio;
import br.com.hospede.model.DTO.Usuario;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CadastrarPasseios extends InterfaceFactory{
    
    private JTextField    txtTitulo, txtIngresso;
    private JLabel        lblPassageiros;
    private JTextArea     txtDescricao;
    private JDateChooser  data;
    private JLabel        lblTitulo, lblDescricao, lblIngresso, lblData;
    private JButton       btnCadastrar, btnPassageiro;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static CadastrarPasseios instance = null;
    private ControlarEventos eventos;
    
    public CadastrarPasseios(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Cadastrar Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1065, 440);
        setLayout(null);
        setResizable(false);
        
        
        //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 400);
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
        
        
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 30);
        btnCadastrar.setLocation(50, 350);
        
        
        
        
        
        
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
        painel.add(btnCadastrar);
        
        botoes[50] = btnCadastrar;
        botoes[69] = btnPassageiro;
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        
        btnCadastrar.addActionListener(eventos);
        btnPassageiro.addActionListener(eventos);
        
        
    }
    
    public DTOPasseio getPasseio(){
       DTOPasseio passeio = new DTOPasseio();
        
       passeio.setTitulo(txtTitulo.getText());
       passeio.setIngresso(Double.parseDouble(txtIngresso.getText()));
       passeio.setDescricao(txtDescricao.getText());
       try{
           passeio.setData(data.getDate());
           
       }catch(Exception erro){
           
       }
       return passeio; 
    }
    
    public void limparCampos(){
        txtTitulo.setText("");
        txtIngresso.setText("");
        txtDescricao.setText("");
    }
    
    public static CadastrarPasseios getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            instance = new CadastrarPasseios(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
