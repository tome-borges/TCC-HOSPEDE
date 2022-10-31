package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOPasseio;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloPasseio;
import br.com.hospede.model.modeloTabela.ModeloSelecionarPasseio;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SelecionarPasseioCadastrarPassageiro extends InterfaceFactory{
    
    private JTable                 tabela;
    private JScrollPane            scroll;
    private ModeloSelecionarPasseio           modelo;
    private JLabel                 lblTitulo;
    private JPanel                 painel;
    private JButton                btnSelecionar;
    private ControlarEventos       eventos = null;
    public static SelecionarPasseioCadastrarPassageiro      instance;
    
    public SelecionarPasseioCadastrarPassageiro(JButton botoes[],JMenuItem itensDeMenu[], JTextField[] camposDeTexto, DTOUsuario usuarioLogado){
        setTitle("Cadastrar passageiro em passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 180, 690, 380);
        setLayout(null);
        setResizable(false);
        
        painel = new JPanel();
        painel.setSize(630, 270);
        painel.setLocation(20, 20);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        modelo =  ModeloSelecionarPasseio.getInstance();
        tabela = new JTable(modelo);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(620, 260);
        scroll.setLocation(5, 5);
        
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setSize(100,30);
        btnSelecionar.setLocation(20, 300);
        
        add(painel);
        painel.add(scroll);
        add(btnSelecionar);
        
        botoes[53] = btnSelecionar;
        
        //Associando os botões a classe que controla eventos.
        getRootPane().setDefaultButton(btnSelecionar); //Deixa o botão Hospedar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnSelecionar.addActionListener(eventos);
        
    }
    
    public DTOPasseio getPasseio(){
        
        //Pega a linha selecionada na tabela de passeios.
        int linhaSelecionada = tabela.getSelectedRow();
        return modelo.getPasseio(linhaSelecionada);
    }
    
    public static SelecionarPasseioCadastrarPassageiro getInstance(JButton botoe[],JMenuItem itensDeMenu[],  JTextField[] camposDeTexto, DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new SelecionarPasseioCadastrarPassageiro(botoe, itensDeMenu, camposDeTexto,  usuarioLogado);
        }
        return instance;
    }
}
