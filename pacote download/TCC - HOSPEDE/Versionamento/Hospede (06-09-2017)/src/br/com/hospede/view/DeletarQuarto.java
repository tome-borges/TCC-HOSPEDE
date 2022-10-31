package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.QuartoDAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DeletarQuarto extends InterfaceFactory{
    private JPanel painel;
    private JLabel lblTitulo, lblNumero, lblSituacao, lblCategoria, lblDiaria, lblOcupantes;
    private JTextField txtNumero, txtCategoria, txtDiaria, txtOcupantes;
    private JButton btnExcluir;
    private final String[]        dados = {"LIVRE", "RESERVADO", "HOSPEDADO", "MANUTENCAO", "ORGANIZAÇÃO"};
    private JComboBox             situacao;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static DeletarQuarto instance = null;
    private ControlarEventos eventos;
    private QuartoDAO quartoDAO;
    
    public DeletarQuarto(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Deletar Quarto");
         setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.itensDeMenu = itensDeMenu;
        this.botoes=botoes;
        
         //Iniciando painel.
        painel = new JPanel();
        painel.setSize(1050,300);
        painel.setLocation(30,30);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        //Inicializando Labels.
        lblTitulo = new JLabel("Preencha os campos abaixo: ");
        lblTitulo.setSize(200, 20);
        lblTitulo.setLocation(10, 10);
        
        lblNumero = new JLabel("Número do quarto:");
        lblNumero.setSize(220, 20);
        lblNumero.setLocation(20, 50);

        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setSize(90, 20);
        lblCategoria.setLocation(65, 80);

        lblDiaria = new JLabel("Diária R$:");
        lblDiaria.setSize(90, 20);
        lblDiaria.setLocation(75, 110);

        lblOcupantes = new JLabel("Ocupantes:");
        lblOcupantes.setSize(90, 20);
        lblOcupantes.setLocation(60, 140);

        lblSituacao = new JLabel("Situacao:");
        lblSituacao.setSize(90, 20);
        lblSituacao.setLocation(70, 170);

        
        //Iniciando caixas de textos.
        txtNumero = new JTextField();
        txtNumero.setSize(50, 20);
        txtNumero.setLocation(135, 50);
        
        txtCategoria = new JTextField();
        txtCategoria.setSize(200, 20);
        txtCategoria.setLocation(135, 80);
        txtCategoria.setEditable(false);

        txtDiaria = new JTextField();
        txtDiaria.setSize(150, 20);
        txtDiaria.setLocation(135, 110);
        txtDiaria.setEditable(false);

        txtOcupantes = new JTextField();
        txtOcupantes.setSize(50, 20);
        txtOcupantes.setLocation(135, 140);
        txtOcupantes.setEditable(false);

        //Iniciando combo box
        situacao = new JComboBox(dados);
        situacao.setSize(300, 20);
        situacao.setLocation(135, 170);
        situacao.setEditable(false);
        
       
        
        //Inicializando botões
        btnExcluir = new JButton("Excluir");
        btnExcluir.setSize(100, 20);
        btnExcluir.setLocation(10, 250);

        
        //Adicionando componentes
        add(painel);
         painel.add(lblTitulo);
        painel.add(lblNumero);
        painel.add(lblCategoria);
        painel.add(lblDiaria);
        painel.add(lblOcupantes);
        painel.add(lblSituacao);
        painel.add(txtNumero);
        painel.add(txtCategoria);
        painel.add(txtDiaria);
        painel.add(txtOcupantes);
        painel.add(situacao);
        painel.add(btnExcluir);
        
        botoes[16]       = btnExcluir;
        camposDeTexto[5] = txtNumero;
        
        getRootPane().setDefaultButton(btnExcluir); //Deixa o botão Excluir em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnExcluir.addActionListener(eventos);
        txtNumero.addFocusListener(eventos);
    }
    
    public Quarto getQuarto(){
        
        Quarto quarto = new Quarto(0,"","","","","","",null,null);
        quarto.setNumero(txtNumero.getText());
        quarto.setCategoria(txtCategoria.getText());
        quarto.setSituacao(dados[situacao.getSelectedIndex()]);
        quarto.setOcupantes(txtOcupantes.getText());
        quarto.setDiaria(txtDiaria.getText());
        
        return quarto;
    }
    
    public void receberQuarto(Quarto quarto){
        txtNumero.setText(quarto.getNumero());
        txtDiaria.setText(quarto.getDiaria());
        txtOcupantes.setText(quarto.getOcupantes());
        txtCategoria.setText(quarto.getCategoria());
        situacao.setSelectedItem(quarto.getSituacao());
    }
    
    public void limparCampos(){
        txtNumero.setText(null);
        txtDiaria.setText(null);
        txtOcupantes.setText(null);
        txtCategoria.setText(null);
    }
    
    public static DeletarQuarto getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new DeletarQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
