package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOUsuario;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class CadastrarQuarto extends InterfaceFactory {

    //Objetos
    private JPanel painel;
    private JLabel lblTitulo, lblNumero, lblSituacao, lblCategoria, lblDiaria, lblOcupantes;
    private JTextField txtNumero, txtOcupantes;
    private JComboBox situacao, tipo;
    private JButton btnCadastrar;
    private final String[] dados = {"LIVRE", "MANUTENCAO"};
    private final String[] tipos = {"SIMPLES", "LUXO"};
    private static JMenuItem[] itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[] botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[] campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static CadastrarQuarto instance = null;
    private NumberFormatter formatter;
    private JFormattedTextField txtDiaria;

    //Construtor.
    public CadastrarQuarto(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        setTitle("Cadastrar Quarto");
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

        lblNumero = new JLabel("*Número:");
        lblNumero.setSize(90, 20);
        lblNumero.setLocation(45, 50);

        lblCategoria = new JLabel("*Categoria:");
        lblCategoria.setSize(90, 20);
        lblCategoria.setLocation(30, 100);

        lblDiaria = new JLabel("*Diária R$:");
        lblDiaria.setSize(200, 20);
        lblDiaria.setLocation(40, 150);

        lblOcupantes = new JLabel("*Ocupantes:");
        lblOcupantes.setSize(200, 60);
        lblOcupantes.setLocation(30, 180);

        lblSituacao = new JLabel("*Situacao:");
        lblSituacao.setSize(200, 20);
        lblSituacao.setLocation(35, 260);

        //Iniciando caixas de textos.
        txtNumero = new JTextField();
        txtNumero.setSize(300, 40);
        txtNumero.setLocation(100, 40);
        
        try {

            DecimalFormat dFormat = new DecimalFormat("#######.00");
            NumberFormatter formatter = new NumberFormatter(dFormat);
            formatter.setFormat(dFormat);
            formatter.setAllowsInvalid(false);

            txtDiaria = new JFormattedTextField(formatter);
            txtDiaria.setFormatterFactory(new DefaultFormatterFactory(formatter));
            txtDiaria.setSize(300, 40);
            txtDiaria.setLocation(100, 140);

        } catch (Exception erro) {

        }

        txtOcupantes = new JTextField();
        txtOcupantes.setSize(200, 40);
        txtOcupantes.setLocation(100, 190);

        //Iniciando combo box
        situacao = new JComboBox(dados);
        situacao.setSize(300, 30);
        situacao.setLocation(100, 250);

        tipo = new JComboBox(tipos);
        tipo.setSize(300, 30);
        tipo.setLocation(100, 90);

        //Inicializando botões
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 25);
        btnCadastrar.setLocation(100, 320);

        //Adicionando componentes
        add(painel);
        painel.add(lblNumero);
        painel.add(lblCategoria);
        painel.add(lblDiaria);
        painel.add(lblOcupantes);
        painel.add(lblSituacao);
        painel.add(txtNumero);
        painel.add(tipo);
        painel.add(txtDiaria);
        painel.add(txtOcupantes);
        painel.add(situacao);
        painel.add(btnCadastrar);

        botoes[14] = btnCadastrar;
        camposDeTexto[6] = txtNumero;

        getRootPane().setDefaultButton(btnCadastrar); //Deixa o botão Cadastrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnCadastrar.addActionListener(eventos);
        txtNumero.addFocusListener(eventos);
    }

    public DTOQuarto getQuarto() {
        DTOQuarto quarto = new DTOQuarto(0, "", "", "", null, "", "", null, null);

        quarto.setNumero(txtNumero.getText());
        quarto.setSituacao(dados[situacao.getSelectedIndex()]);
        quarto.setOcupantes(txtOcupantes.getText());
        quarto.setDiaria(Double.parseDouble(txtDiaria.getText().replace(',', '.')));
        quarto.setCategoria(tipos[tipo.getSelectedIndex()]);

        return quarto;
    }

    public void receberQuarto(DTOQuarto quarto) {
        txtNumero.setText(quarto.getNumero());
        txtOcupantes.setText(quarto.getOcupantes());
        try {
            txtDiaria.setText(formatter.valueToString(quarto.getDiaria()));
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
        tipo.setSelectedItem(quarto.getSituacao());
    }

    public void limparCampos() {
        txtNumero.setText(null);
        txtOcupantes.setText(null);
        txtDiaria.setText(null);

    }

    public static CadastrarQuarto getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        if (instance == null) {
            instance = new CadastrarQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
