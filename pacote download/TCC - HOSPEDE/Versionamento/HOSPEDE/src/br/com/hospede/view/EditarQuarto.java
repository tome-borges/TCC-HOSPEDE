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

public class EditarQuarto extends InterfaceFactory {

    //Objetos
    private JPanel                painel;
    private JLabel                lblTitulo, lblNumero, lblSituacao, lblCategoria, lblDiaria, lblOcupantes;
    private JTextField            txtNumero, txtOcupantes;
    private JComboBox             situacao, categoria;
    private JButton               btnSalvar;
    private final String[]        dados = {"LIVRE", "MANUTENCAO"};
    private final String[]        situações = {"LUXO", "SIMPLES"};
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos      eventos;
    public static EditarQuarto    instance = null;
    private DTOQuarto quarto;
    private NumberFormatter formatter;
    private JFormattedTextField  txtDiaria;

    //Construtor.
    public EditarQuarto( JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
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

        lblNumero = new JLabel("*Número:");
        lblNumero.setSize(100, 20);
        lblNumero.setLocation(20, 50);

        lblCategoria = new JLabel("*Categoria:");
        lblCategoria.setSize(100, 20);
        lblCategoria.setLocation(25, 100);

        lblDiaria = new JLabel("*Diária R$:");
        lblDiaria.setSize(210, 20);
        lblDiaria.setLocation(35, 150);

        lblOcupantes = new JLabel("*Ocupantes:");
        lblOcupantes.setSize(210, 60);
        lblOcupantes.setLocation(25, 180);

        lblSituacao = new JLabel("*Situacao:");
        lblSituacao.setSize(210, 20);
        lblSituacao.setLocation(30, 260);

        //Iniciando caixas de textos.
        txtNumero = new JTextField();
        txtNumero.setSize(300, 40);
        txtNumero.setLocation(100, 40);

        categoria = new JComboBox(situações);
        categoria.setSize(300, 40);
        categoria.setLocation(100, 90);

        try {

            DecimalFormat dFormat = new DecimalFormat("#######.00");
            formatter = new NumberFormatter(dFormat);
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

        //Inicializando botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setSize(100, 25);
        btnSalvar.setLocation(100, 320);

        //Adicionando componentes
        add(painel);
        painel.add(lblNumero);
        painel.add(lblCategoria);
        painel.add(lblDiaria);
        painel.add(lblOcupantes);
        painel.add(lblSituacao);
        painel.add(txtNumero);
        painel.add(categoria);
        painel.add(txtDiaria);
        painel.add(txtOcupantes);
        painel.add(situacao);
        painel.add(btnSalvar);

        botoes[17] = btnSalvar;
        getRootPane().setDefaultButton(btnSalvar); //Deixa o botão Cadastrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnSalvar.addActionListener(eventos);
    }

    public DTOQuarto getQuarto() {
        DTOQuarto quarto = new DTOQuarto(0,"","","",null,"","",null,null);
        
        quarto.setNumero(txtNumero.getText());
        quarto.setSituacao(dados[situacao.getSelectedIndex()]);
        quarto.setOcupantes(txtOcupantes.getText());
        quarto.setDiaria(Double.parseDouble(txtDiaria.getText().replace(',', '.')));
        quarto.setCategoria(situações[categoria.getSelectedIndex()]);
        quarto.setId_quarto(this.quarto.getId_quarto());
        return quarto;
    }
    
    public void receberQuarto(DTOQuarto quarto){
        txtNumero.setText(quarto.getNumero());
        txtOcupantes.setText(quarto.getOcupantes());
        try {
            txtDiaria.setText(formatter.valueToString(quarto.getDiaria()));
        } catch (ParseException ex) {
            Logger.getLogger(EditarQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
        categoria.setSelectedItem(quarto.getCategoria());
        situacao.setSelectedItem(quarto.getSituacao());
        this.quarto = quarto;    
    }
    
    public void limparCampos(){
        txtNumero.setText(null);
        txtOcupantes.setText(null);
        txtDiaria.setText(null);
        
    }
    
    public static EditarQuarto getInstance( JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            instance = new EditarQuarto(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
