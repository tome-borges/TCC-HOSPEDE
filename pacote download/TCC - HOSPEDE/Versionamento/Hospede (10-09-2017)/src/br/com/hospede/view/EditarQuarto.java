package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOQuarto;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.DAO.QuartoDAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditarQuarto extends InterfaceFactory {

    //Objetos
    private JPanel                painel;
    private JLabel                lblTitulo, lblNumero, lblSituacao, lblCategoria, lblDiaria, lblOcupantes;
    private JTextField            txtNumero, txtCategoria, txtDiaria, txtOcupantes;
    private JComboBox             situacao;
    private JButton               btnEditar;
    private final String[]        dados = {"LIVRE", "MANUTENCAO"};
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos      eventos;
    public static EditarQuarto    instance = null;

    //Construtor.
    public EditarQuarto( JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        setTitle("Editar Quarto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(370, 150, 530, 410);
        setLayout(null);
        setResizable(false);

        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        
        //Iniciando painel.
        //Inicializando painel.
        painel = new JPanel();
        painel.setSize(512, 372);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);

        lblNumero = new JLabel("Número:");
        lblNumero.setSize(90, 20);
        lblNumero.setLocation(50, 50);

        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setSize(90, 20);
        lblCategoria.setLocation(35, 100);

        lblDiaria = new JLabel("Diária R$:");
        lblDiaria.setSize(200, 20);
        lblDiaria.setLocation(45, 150);

        lblOcupantes = new JLabel("Ocupantes:");
        lblOcupantes.setSize(200, 60);
        lblOcupantes.setLocation(35, 180);

        lblSituacao = new JLabel("Situacao:");
        lblSituacao.setSize(200, 20);
        lblSituacao.setLocation(40, 260);

        //Iniciando caixas de textos.
        txtNumero = new JTextField();
        txtNumero.setSize(300, 40);
        txtNumero.setLocation(100, 40);

        txtCategoria = new JTextField();
        txtCategoria.setSize(300, 40);
        txtCategoria.setLocation(100, 90);

        txtDiaria = new JTextField();
        txtDiaria.setSize(300, 40);
        txtDiaria.setLocation(100, 140);

        txtOcupantes = new JTextField();
        txtOcupantes.setSize(200, 40);
        txtOcupantes.setLocation(100, 190);

        //Iniciando combo box
        situacao = new JComboBox(dados);
        situacao.setSize(300, 30);
        situacao.setLocation(100, 250);

        //Inicializando botões
        btnEditar = new JButton("Editar");
        btnEditar.setSize(100, 25);
        btnEditar.setLocation(100, 320);

        //Adicionando componentes
        add(painel);
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
        painel.add(btnEditar);

        botoes[17] = btnEditar;
        getRootPane().setDefaultButton(btnEditar); //Deixa o botão Cadastrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnEditar.addActionListener(eventos);
    }

    public DTOQuarto getQuarto() {
        DTOQuarto quarto = new DTOQuarto(0,"","","","","","",null,null);
        
        quarto.setNumero(txtNumero.getText());
        quarto.setSituacao(dados[situacao.getSelectedIndex()]);
        quarto.setOcupantes(txtOcupantes.getText());
        quarto.setDiaria(txtDiaria.getText());
        quarto.setCategoria(txtCategoria.getText());
        
        return quarto;
    }
    
    public void receberQuarto(DTOQuarto quarto){
        txtNumero.setText(quarto.getNumero());
        txtOcupantes.setText(quarto.getOcupantes());
        txtDiaria.setText(quarto.getDiaria());
        txtCategoria.setText(quarto.getCategoria());
        situacao.setSelectedItem(quarto.getSituacao());
    }
    
    public void limparCampos(){
        txtNumero.setText(null);
        txtOcupantes.setText(null);
        txtDiaria.setText(null);
        txtCategoria.setText(null);
    }
    
    public static EditarQuarto getInstance( JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new EditarQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
