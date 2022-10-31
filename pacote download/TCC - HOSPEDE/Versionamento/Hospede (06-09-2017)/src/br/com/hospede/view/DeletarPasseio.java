package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOPasseio;
import br.com.hospede.model.DTO.Usuario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DeletarPasseio extends InterfaceFactory{
    
    private JTextField    txtTitulo, txtIngresso, txtData;
    private JTextArea     txtDescricao;
    private JLabel        lblTitulo, lblDescricao, lblIngresso, lblData;
    private JButton       btnDeletar;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static DeletarPasseio instance = null;
    private SimpleDateFormat    formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private ControlarEventos    eventos;
    
    public DeletarPasseio(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Deletar Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setLayout(null);
        setResizable(false);
        
        
        //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 400);
        painel.setLocation(30, 30);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        lblTitulo = new JLabel("Título");
        lblTitulo.setSize(50, 20);
        lblTitulo.setLocation(50, 50);
        
        txtTitulo = new JTextField();
        txtTitulo.setSize(250, 20);
        txtTitulo.setLocation(90, 50);
        
        lblIngresso = new JLabel("Ingresso R$");
        lblIngresso.setSize(70, 20);
        lblIngresso.setLocation(390, 50);
        
        
        txtIngresso = new JTextField();
        txtIngresso.setSize(50, 20);
        txtIngresso.setLocation(470, 50);
        txtIngresso.setEditable(false);
        
        lblData = new JLabel("Data");
        lblData.setSize(70, 20);
        lblData.setLocation(560, 50);
        
        txtData = new JTextField();
        txtData.setSize(130, 20);
        txtData.setLocation(600, 50);
        txtData.setEditable(false);
        
        lblDescricao = new JLabel("Descrição");
        lblDescricao.setSize(100, 20);
        lblDescricao.setLocation(50, 90);
        
        txtDescricao = new JTextArea();
        txtDescricao.setSize(950, 200);
        txtDescricao.setLocation(50, 110);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtDescricao.setEnabled(false);
        
        
        btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(100, 20);
        btnDeletar.setLocation(50, 350);
        
        //Adicionando componentes no painel.
        add(painel);
        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblIngresso);
        painel.add(txtIngresso);
        painel.add(lblData);
        painel.add(txtData);
        painel.add(lblDescricao);
        painel.add(txtDescricao);
        painel.add(btnDeletar);
        
        campoDeTexto[17] = txtTitulo;
        botoes[52] = btnDeletar;

        getRootPane().setDefaultButton(btnDeletar); //Deixa o botão Editar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnDeletar.addActionListener(eventos);
        txtTitulo.addFocusListener(eventos);
        
    }
    
    public void receberPasseio(DTOPasseio passeio){
        txtTitulo.setText(passeio.getTitulo());
        txtDescricao.setText(passeio.getDescricao());
         try{
           txtData.setText(formatarData.format(passeio.getData()));
           txtIngresso.setText(Double.toString(passeio.getIngresso()));
           
       }catch(Exception erro){
           
       }
        
    }
    
    public DTOPasseio getPasseio(){
       DTOPasseio passeio = new DTOPasseio();
        
       passeio.setTitulo(txtTitulo.getText());
       
       passeio.setDescricao(txtDescricao.getText());
       try{
           passeio.setIngresso(Double.parseDouble(txtIngresso.getText()));
           
       }catch(Exception erro){
           
       }
       return passeio; 
    }
    
    public void limparCampos(){
        txtTitulo.setText("");
        txtIngresso.setText("");
        txtDescricao.setText("");
    }
    
    public static DeletarPasseio getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            instance = new DeletarPasseio(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
