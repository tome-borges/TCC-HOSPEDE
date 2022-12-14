package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloPasseiosRealizados;
import br.com.hospede.model.modeloTabela.ModeloProdutosConsumidos;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FecharHospedagem extends InterfaceFactory {

    private JPanel painel;
    private JLabel lblTituloProdutos, lblTituloPasseios, lblNome, lblCPF, lblDiaria, lblDiasHospedados,
            lblTotalPagar, lblQuarto, lblTotalProduto, lblTotalHospedagem, lblTotalPasseio, lblCpf, lblPassaporte;
    private JTextField txtNome, txtDiaria, txtQuarto, txtDiasHospedados, txtTotalPagar,
            txtTotalProduto, txtTotalHospedagem, txtTotalPasseio, txtPassaporte;
    private JButton btnFechar;
    private static JMenuItem[] itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[] botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[] campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static FecharHospedagem instance = null;
    private int id_cliente, id_quarto, id_reserva;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private JTable tabelaPasseios, tabelaProdutos;
    private JScrollPane scrollPasseios, scrollProdutos;
    private ModeloPasseiosRealizados modeloPasseios;
    private ModeloProdutosConsumidos modeloProdutos;
    private MaskFormatter mascararCPF, mascararPassaporte;
    private DTOCliente cliente;
    private JFormattedTextField txtCPF;

    public FecharHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        setTitle("Fechar Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(125, 20, 1147, 700);
        setLayout(null);
        setResizable(false);

        //Recebendo vetores com bot??es, para adicionar o bot??o desta classe neles.
        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(1130, 660);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);

        modeloPasseios = ModeloPasseiosRealizados.getInstance();
        tabelaPasseios = new JTable(modeloPasseios);
        tabelaPasseios.getColumnModel().getColumn(1).setMaxWidth(60);
        scrollPasseios = new JScrollPane(tabelaPasseios);
        scrollPasseios.setSize(1020, 160);
        scrollPasseios.setLocation(50, 440);
        scrollPasseios.setBorder(BorderFactory.createLineBorder(Color.black));

        lblTotalPasseio = new JLabel("Total passeios R$:");
        lblTotalPasseio.setSize(150, 30);
        lblTotalPasseio.setLocation(850, 610);
        lblTotalPasseio.setForeground(Color.red);

        txtTotalPasseio = new JTextField();
        txtTotalPasseio.setSize(90, 30);
        txtTotalPasseio.setLocation(980, 610);
        txtTotalPasseio.setEditable(false);

        modeloProdutos = ModeloProdutosConsumidos.getInstance();
        tabelaProdutos = new JTable(modeloProdutos);
        scrollProdutos = new JScrollPane(tabelaProdutos);
        scrollProdutos.setSize(1020, 160);
        scrollProdutos.setLocation(50, 220);
        scrollProdutos.setBorder(BorderFactory.createLineBorder(Color.black));

        lblTotalProduto = new JLabel("Total produtos R$:");
        lblTotalProduto.setSize(150, 30);
        lblTotalProduto.setLocation(850, 390);
        lblTotalProduto.setForeground(Color.red);

        txtTotalProduto = new JTextField();
        txtTotalProduto.setSize(90, 30);
        txtTotalProduto.setLocation(980, 390);
        txtTotalProduto.setEditable(false);
        
        lblPassaporte = new JLabel("PASSAPORTE ");
        lblPassaporte.setSize(300, 40);
        lblPassaporte.setLocation(460, 15);
        lblPassaporte.setForeground(Color.red);

        //Criando m??scara no campo.
        try{
        mascararCPF = new MaskFormatter("###.###.###-##");
        txtCPF = new JFormattedTextField(mascararCPF);
        txtCPF.setFocusLostBehavior(JFormattedTextField.COMMIT);
        txtCPF.setSize(200, 30);
        txtCPF.setLocation(100, 20);
        }catch(Exception erro){
            
        }
        
        txtPassaporte = new JTextField();
        txtPassaporte.setSize(200, 30);
        txtPassaporte.setLocation(550, 20);

        lblCPF = new JLabel("CPF");
        lblCPF.setSize(150, 20);
        lblCPF.setLocation(50, 20);
        lblCPF.setForeground(Color.red);

        lblNome = new JLabel("Nome: ");
        lblNome.setSize(90, 20);
        lblNome.setLocation(50, 100);

        lblDiaria = new JLabel("Di??ria R$");
        lblDiaria.setSize(90, 20);
        lblDiaria.setLocation(35, 150);

        lblDiasHospedados = new JLabel("Dias Hospedados");
        lblDiasHospedados.setSize(150, 20);
        lblDiasHospedados.setLocation(235, 150);

        lblQuarto = new JLabel("Quarto");
        lblQuarto.setSize(150, 20);
        lblQuarto.setLocation(520, 150);

        lblTotalHospedagem = new JLabel("Total hospedagem R$:");
        lblTotalHospedagem.setSize(150, 20);
        lblTotalHospedagem.setLocation(830, 150);
        lblTotalHospedagem.setForeground(Color.red);

        lblTituloProdutos = new JLabel("PRODUTOS CONSUMIDOS");
        lblTituloProdutos.setSize(200, 20);
        lblTituloProdutos.setLocation(55, 200);

        lblTituloPasseios = new JLabel("PASSEIOS REALIZADOS");
        lblTituloPasseios.setSize(200, 20);
        lblTituloPasseios.setLocation(55, 420);

        txtQuarto = new JTextField();
        txtQuarto.setSize(90, 30);
        txtQuarto.setLocation(570, 150);
        txtQuarto.setEditable(false);

        txtTotalHospedagem = new JTextField();
        txtTotalHospedagem.setSize(90, 30);
        txtTotalHospedagem.setLocation(980, 150);
        txtTotalHospedagem.setEditable(false);

        txtDiasHospedados = new JTextField();
        txtDiasHospedados.setSize(100, 30);
        txtDiasHospedados.setLocation(350, 150);
        txtDiasHospedados.setEditable(false);

        txtDiaria = new JTextField();
        txtDiaria.setSize(90, 30);
        txtDiaria.setLocation(100, 150);
        txtDiaria.setEditable(false);

        txtNome = new JTextField();
        txtNome.setSize(560, 30);
        txtNome.setLocation(100, 90);
        txtNome.setEditable(false);

        btnFechar = new JButton("Fechar");
        btnFechar.setSize(100, 30);
        btnFechar.setLocation(350, 620);

        lblTotalPagar = new JLabel("Total a pagar R$");
        lblTotalPagar.setForeground(Color.red);
        lblTotalPagar.setSize(100, 30);
        lblTotalPagar.setLocation(50, 620);

        txtTotalPagar = new JTextField();
        txtTotalPagar.setSize(100, 30);
        txtTotalPagar.setLocation(150, 620);
        txtTotalPagar.setEditable(false);

        add(painel);
        painel.add(scrollPasseios);
        painel.add(scrollProdutos);
        painel.add(btnFechar);
        painel.add(lblCPF);
        painel.add(txtCPF);
        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblDiaria);
        painel.add(txtDiaria);
        painel.add(lblDiasHospedados);
        painel.add(txtDiasHospedados);
        painel.add(lblQuarto);
        painel.add(txtQuarto);
        painel.add(lblTituloProdutos);
        painel.add(lblTituloPasseios);
        painel.add(lblTotalPagar);
        painel.add(txtTotalPagar);
        painel.add(lblTotalHospedagem);
        painel.add(txtTotalHospedagem);
        painel.add(lblTotalProduto);
        painel.add(txtTotalProduto);
        painel.add(lblTotalPasseio);
        painel.add(txtTotalPasseio);
        painel.add(lblPassaporte);
        painel.add(txtCPF);
        painel.add(txtPassaporte);

        botoes[41] = btnFechar;
        camposDeTexto[13] = txtCPF;
        camposDeTexto[23] = txtPassaporte;

        getRootPane().setDefaultButton(btnFechar); //Deixa o bot??o Fechar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnFechar.addActionListener(eventos);
        txtCPF.addFocusListener(eventos);
        txtPassaporte.addFocusListener(eventos);
    }

    public void receberHospedagem(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto) {
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtQuarto.setText(quarto.getNumero());
        txtDiaria.setText(quarto.getDiaria());
        this.cliente = cliente;

        //Calcula o total a ser pago pela hospedagem.
        try {
            reserva.totalPagar(quarto);
            txtDiasHospedados.setText(Integer.toString(reserva.getDias_hospedados()));
        } catch (Exception erro) {

        }

    }

    public DTOReserva getHospedagem() {
        return null;
    }

    public DTOCliente getCliente() {
        DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        
        if(txtCPF.getText().equals("   .   .   -  ") && txtPassaporte.getText().equals("")){
           //Os dois campos est?? em branco
           cliente = cliente;
            
        } else {
            if (txtCPF.getText().equals("   .   .   -  ")) {
            //Ser?? pesquisado por passaporte.
            cliente.setPassaport(txtPassaporte.getText());

        } else if (txtPassaporte.getText().equals("")) {
            cliente.setCpf(txtCPF.getText());

        }
        }
        return cliente;

    }

    public void limparCampos() {
        txtNome.setText("");
        txtDiaria.setText("");
        txtQuarto.setText("");
        txtDiasHospedados.setText("");
        txtTotalHospedagem.setText("");
        txtTotalPasseio.setText("");
        txtTotalProduto.setText("");
        txtTotalPagar.setText("");
        txtPassaporte.setText("");
    }

    public void receberTotaisServicos(double totalHospedagem, double totalProdutos, double totalPasseio) {
        try {
            txtTotalHospedagem.setText(Double.toString(totalHospedagem));
            txtTotalPasseio.setText(Double.toString(totalPasseio));
            txtTotalProduto.setText(Double.toString(totalProdutos));

            txtTotalPagar.setText(Double.toString(totalHospedagem + totalPasseio + totalProdutos));
        } catch (Exception erro) {
        }
    }


    public static FecharHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        if (instance == null) {
            return instance = new FecharHospedagem(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
