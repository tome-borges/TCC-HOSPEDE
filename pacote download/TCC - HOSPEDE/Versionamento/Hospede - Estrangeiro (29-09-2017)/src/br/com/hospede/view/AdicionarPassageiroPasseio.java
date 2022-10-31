package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOPassageiro;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.JTableRenderer;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class AdicionarPassageiroPasseio extends InterfaceFactory {

    private ModeloPassageiros modelo;
    private JTable tabela;
    private JScrollPane scroll;
    private JTextField txtTitulo, txtIngresso, txtQuarto, txtCelular, txtNomePassageiro,
            txtData, txtPassaporteCliente, txtPassaportePassageiro;
    private JTextArea txtDescricao;
    private JLabel lblTitulo, lblDescricao, lblIngresso, lblData, lblNome, lblQuarto, lblCelular,
            lblNomePassageiro, lblCpfPassageiro, lblAviso, lblPassaporte, lblCpf, lblPassaportePassageiro;
    private JButton btnCadastrar, btnAdicionar;
    private JPanel painel;
    private static JMenuItem[] itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[] botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[] campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static AdicionarPassageiroPasseio instance = null;
    private ControlarEventos eventos;
    private int id_Passeio;
    private int id_hospedagem;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private MaskFormatter mascararCPF;
    private String nomeCliente;
    private JRadioButton rdbCPF, rdbPassaporte;
    private JFormattedTextField txtcfpCliente, txtCpfPassageiro,txtCpfCliente;
    private DTOUsuario usuarioLogado;

    public AdicionarPassageiroPasseio(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        setTitle("Adicionar Passageiro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(150, 80, 1070, 610);
        setLayout(null);
        setResizable(false);
        
        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = camposDeTexto;
        this.usuarioLogado = usuarioLogado;

        //Inicializando objetos
        painel = new JPanel();
        painel.setSize(1050, 570);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);

        modelo = ModeloPassageiros.getInstance();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        scroll.setSize(950, 150);
        scroll.setLocation(50, 310);

        JTableRenderer renderer = new JTableRenderer();
        tabela.getColumnModel().getColumn(4).setCellRenderer(renderer);
        tabela.getColumnModel().getColumn(5).setCellRenderer(renderer);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(30);

        lblTitulo = new JLabel("Título");
        lblTitulo.setSize(50, 20);
        lblTitulo.setLocation(50, 20);

        txtTitulo = new JTextField();
        txtTitulo.setSize(250, 30);
        txtTitulo.setLocation(90, 20);
        txtTitulo.setEditable(false);

        lblIngresso = new JLabel("Ingresso R$");
        lblIngresso.setSize(70, 20);
        lblIngresso.setLocation(390, 20);

        txtIngresso = new JTextField();
        txtIngresso.setSize(50, 30);
        txtIngresso.setLocation(470, 20);
        txtIngresso.setEditable(false);

        lblData = new JLabel("Data");
        lblData.setSize(70, 30);
        lblData.setLocation(560, 20);

        txtData = new JTextField();
        txtData.setSize(130, 30);
        txtData.setLocation(600, 20);
        txtData.setEditable(false);

        lblDescricao = new JLabel("Descrição");
        lblDescricao.setSize(100, 20);
        lblDescricao.setLocation(50, 60);

        txtDescricao = new JTextArea();
        txtDescricao.setSize(950, 150);
        txtDescricao.setLocation(50, 80);
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtDescricao.setEditable(false);

        lblNome = new JLabel("CPF");
        lblNome.setSize(100, 20);
        lblNome.setLocation(50, 270);
        lblNome.setForeground(Color.red);

        lblPassaporte = new JLabel("PASSAPORTE");
        lblPassaporte.setSize(100, 20);
        lblPassaporte.setLocation(280, 270);
        lblPassaporte.setForeground(Color.red);

        lblAviso = new JLabel("Insira o responsável pela despesa do passeio");
        lblAviso.setSize(300, 20);
        lblAviso.setLocation(50, 240);

        lblQuarto = new JLabel("Quarto");
        lblQuarto.setSize(100, 20);
        lblQuarto.setLocation(580, 270);

        txtQuarto = new JTextField();
        txtQuarto.setSize(50, 30);
        txtQuarto.setLocation(635, 270);
        txtQuarto.setEditable(false);

        lblCelular = new JLabel("Celular");
        lblCelular.setSize(50, 20);
        lblCelular.setLocation(700, 270);

        txtCelular = new JTextField();
        txtCelular.setSize(200, 30);
        txtCelular.setLocation(750, 270);
        txtCelular.setEditable(false);

        lblNomePassageiro = new JLabel("Passageiro");
        lblNomePassageiro.setSize(100, 20);
        lblNomePassageiro.setLocation(50, 490);

        txtNomePassageiro = new JTextField();
        txtNomePassageiro.setSize(200, 30);
        txtNomePassageiro.setLocation(130, 490);

        try {
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtCpfCliente = new JFormattedTextField(mascararCPF);
            txtCpfCliente.setFocusLostBehavior(JFormattedTextField.COMMIT);
            txtCpfCliente.setSize(180, 30);
            txtCpfCliente.setLocation(90, 270);

            txtPassaporteCliente = new JTextField();
            txtPassaporteCliente.setSize(180, 30);
            txtPassaporteCliente.setLocation(375, 270);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mascarar CPF \n:" + erro.getMessage());
        }

        lblCpfPassageiro = new JLabel("CPF/PASSAPORTE");
        lblCpfPassageiro.setSize(150, 20);
        lblCpfPassageiro.setLocation(350, 490);

        try {
            mascararCPF = new MaskFormatter("###.###.###-##");
            txtCpfPassageiro = new JFormattedTextField(mascararCPF);
            txtCpfPassageiro.setFocusLostBehavior(JFormattedTextField.COMMIT);
            txtCpfPassageiro.setSize(200, 30);
            txtCpfPassageiro.setLocation(500, 490);

            txtPassaportePassageiro = new JTextField();
            txtPassaportePassageiro.setSize(200, 30);
            txtPassaportePassageiro.setLocation(500, 490);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mascarar CPF \n:" + erro.getMessage());
        }

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 30);
        btnCadastrar.setLocation(50, 530);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setSize(100, 30);
        btnAdicionar.setLocation(750, 490);

        lblCpf = new JLabel("CPF ");
        lblCpf.setSize(300, 40);
        lblCpf.setLocation(530, 520);

        rdbCPF = new JRadioButton();
        rdbCPF.setSize(300, 40);
        rdbCPF.setLocation(500, 520);

        lblPassaportePassageiro = new JLabel("PASSAPORTE ");
        lblPassaportePassageiro.setSize(300, 40);
        lblPassaportePassageiro.setLocation(630, 520);

        rdbPassaporte = new JRadioButton();
        rdbPassaporte.setSize(300, 40);
        rdbPassaporte.setLocation(600, 520);

        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rdbCPF);
        grupoBotoes.add(rdbPassaporte);

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
        painel.add(scroll);
        painel.add(btnAdicionar);
        painel.add(btnCadastrar);
        painel.add(lblNome);
        painel.add(lblQuarto);
        painel.add(lblCelular);
        painel.add(txtCelular);
        painel.add(txtQuarto);
        painel.add(txtNomePassageiro);
        painel.add(lblNomePassageiro);
        painel.add(lblCpfPassageiro);
        painel.add(txtCpfPassageiro);
        painel.add(txtPassaportePassageiro);
        painel.add(txtCpfCliente);
        painel.add(lblAviso);
        painel.add(lblPassaporte);
        painel.add(rdbPassaporte);
        painel.add(rdbCPF);
        painel.add(txtPassaporteCliente);
        painel.add(lblCpf);
        painel.add(lblPassaportePassageiro);

        camposDeTexto[18] = txtCpfCliente;
        camposDeTexto[24] = txtPassaporteCliente;
        botoes[54] = btnAdicionar;
        botoes[56] = btnCadastrar;

        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
        btnAdicionar.addActionListener(eventos);
        txtCpfCliente.addFocusListener(eventos);
        txtPassaporteCliente.addFocusListener(eventos);
        rdbCPF.addItemListener(this);
        rdbPassaporte.addItemListener(this);

    }

    public void receberPasseio(DTOPasseio passeio) {
        
        try {
            txtTitulo.setText(passeio.getTitulo());
            txtIngresso.setText(Double.toString(passeio.getIngresso()));
            txtData.setText(formatarData.format(passeio.getData()));
            txtDescricao.setText(passeio.getDescricao());
            id_Passeio = passeio.getId();
        } catch (Exception erro) {

        }

    }

    public void receberCliente(DTOCliente cliente, DTOQuarto quarto, DTOReserva hospedagem) {
        txtQuarto.setText(quarto.getNumero());
        txtCelular.setText(cliente.getCelular());
        id_hospedagem = hospedagem.getId_reserva();
        nomeCliente = cliente.getNome();
    }

    public DTOCliente getCliente() {
        DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        
        if(txtCpfCliente.getText().equals("   .   .   -  ") && txtPassaporteCliente.getText().equals("")){
           //Os dois campos está em branco
           cliente = cliente;
            
        } else {
            if (txtCpfCliente.getText().equals("   .   .   -  ")) {
            //Será pesquisado por passaporte.
            cliente.setPassaport(txtPassaporteCliente.getText());

        } else if (txtPassaporteCliente.getText().equals("")) {
            cliente.setCpf(txtCpfCliente.getText());

        }
        }
        return cliente;
    }

    public DTOPassageiro getPassageiro() {
        DTOPassageiro clienteAdiconar = new DTOPassageiro();

        clienteAdiconar.setNome_passageiro(txtNomePassageiro.getText());
        if(txtCpfPassageiro.getText().equals("   .   .   -  ")){
            //Significa que não pe pra cadastrar CPF e sim passaporte
            txtCpfPassageiro.setText("");
            clienteAdiconar.setCpf(txtPassaportePassageiro.getText());
            
        } else if(txtPassaportePassageiro.getText().equals("")){
            txtPassaporteCliente.setText("");
            clienteAdiconar.setCpf(txtCpfPassageiro.getText());
        }
        clienteAdiconar.setQuarto(txtQuarto.getText());
        clienteAdiconar.setResponsavel(nomeCliente);
        clienteAdiconar.setId_passeio(id_Passeio);
        clienteAdiconar.setId_hospedagem_responsavel(id_hospedagem);

        return clienteAdiconar;
    }

    public int getPassageiroRevomer() {
        int linhaSelecionada;
        return linhaSelecionada = tabela.getSelectedRow();
    }

    public void limparDados() {

        txtQuarto.setText("");
        txtCelular.setText("");
        txtCpfCliente.setText("");

    }

    public void limparPassageiros() {
        txtNomePassageiro.setText("");
        txtCpfPassageiro.setText("");
    }

    @Override
    public void itemStateChanged(ItemEvent evento) {
        if (rdbCPF.isSelected()) {

            try {
                painel.remove(txtPassaportePassageiro);
                painel.add(txtCpfPassageiro);
            } catch (Exception erro) {

            }

        } else if (rdbPassaporte.isSelected()) {

            try {

                painel.remove(txtCpfPassageiro);
                painel.add(txtPassaportePassageiro);
                txtPassaportePassageiro.setText("TESTE");
                txtPassaportePassageiro.setText("");
                
                AdicionarPassageiroPasseio tela = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
                tela.setVisible(true);
                
            } catch (Exception erro) {

            }

        }
    }
    
    public void setCampos(){
        txtNomePassageiro.setEditable(false);
        txtCpfPassageiro.setEditable(false);
        txtPassaportePassageiro.setEditable(false);
        btnAdicionar.setEnabled(false);
        btnCadastrar.setEnabled(false);
    }
    
    public void liberarCampos(){
        txtNomePassageiro.setEditable(true);
        txtCpfPassageiro.setEditable(true);
        txtPassaportePassageiro.setEditable(true);
        btnAdicionar.setEnabled(true);
        btnCadastrar.setEnabled(true);
    }

    public static AdicionarPassageiroPasseio getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        if (instance == null) {
            instance = new AdicionarPassageiroPasseio(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
