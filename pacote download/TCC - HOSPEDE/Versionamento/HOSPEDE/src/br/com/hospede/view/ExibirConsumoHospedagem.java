package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloPasseiosRealizados;
import br.com.hospede.model.modeloTabela.ModeloProdutosConsumidos;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ExibirConsumoHospedagem extends InterfaceFactory{
    private JPanel painel;
    private JLabel lblTituloProdutos, lblTituloPasseios, lblTotalProduto, lblTotalPasseio;
    private JTextField txtTotalProduto, txtTotalPasseio;
    private static JMenuItem[] itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[] botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[] campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static ExibirConsumoHospedagem instance = null;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private JTable tabelaPasseios, tabelaProdutos;
    private JScrollPane scrollPasseios, scrollProdutos;
    private ModeloPasseiosRealizados modeloPasseios;
    private ModeloProdutosConsumidos modeloProdutos;
    private DTOReserva reserva;
    private DTOQuarto quarto;

    public ExibirConsumoHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        setTitle("Consumos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(125, 60, 1147, 550);
        setLayout(null);
        setResizable(false);

        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(1130, 510);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);

        modeloPasseios = ModeloPasseiosRealizados.getInstance();
        tabelaPasseios = new JTable(modeloPasseios);
        tabelaPasseios.getColumnModel().getColumn(1).setMaxWidth(60);
        scrollPasseios = new JScrollPane(tabelaPasseios);
        scrollPasseios.setSize(1020, 160);
        scrollPasseios.setLocation(50, 280);
        scrollPasseios.setBorder(BorderFactory.createLineBorder(Color.black));

        lblTotalPasseio = new JLabel("Total passeios R$:");
        lblTotalPasseio.setSize(150, 30);
        lblTotalPasseio.setLocation(850, 450);
        lblTotalPasseio.setForeground(Color.red);

        txtTotalPasseio = new JTextField();
        txtTotalPasseio.setSize(90, 30);
        txtTotalPasseio.setLocation(980, 450);
        txtTotalPasseio.setEditable(false);

        modeloProdutos = ModeloProdutosConsumidos.getInstance();
        tabelaProdutos = new JTable(modeloProdutos);
        scrollProdutos = new JScrollPane(tabelaProdutos);
        scrollProdutos.setSize(1020, 160);
        scrollProdutos.setLocation(50, 60);
        scrollProdutos.setBorder(BorderFactory.createLineBorder(Color.black));

        lblTotalProduto = new JLabel("Total produtos R$:");
        lblTotalProduto.setSize(150, 30);
        lblTotalProduto.setLocation(850, 230);
        lblTotalProduto.setForeground(Color.red);

        txtTotalProduto = new JTextField();
        txtTotalProduto.setSize(90, 30);
        txtTotalProduto.setLocation(980, 230);
        txtTotalProduto.setEditable(false);

        lblTituloProdutos = new JLabel("PRODUTOS CONSUMIDOS");
        lblTituloProdutos.setSize(200, 20);
        lblTituloProdutos.setLocation(55, 40);

        lblTituloPasseios = new JLabel("PASSEIOS REALIZADOS");
        lblTituloPasseios.setSize(200, 20);
        lblTituloPasseios.setLocation(55, 260);

        add(painel);
        painel.add(scrollPasseios);
        painel.add(scrollProdutos);
        painel.add(lblTituloProdutos);
        painel.add(lblTituloPasseios);
        painel.add(lblTotalProduto);
        painel.add(txtTotalProduto);
        painel.add(lblTotalPasseio);
        painel.add(txtTotalPasseio);
        
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado, modelosTabelas);
    }


    public DTOReserva getHospedagem() {
        reserva.setStatus("FECHADA");
        return reserva;
    }
    
    public DTOQuarto getQuarto(){
        quarto.setSituacao("ORGANIZANDO");
        quarto.setNome_cliente("");
        quarto.setEntrada(null);
        quarto.setSaida(null);
       
        return quarto;
    }


    public void receberTotaisServicos(double totalHospedagem, double totalProdutos, double totalPasseio) {
        try {
     
            txtTotalPasseio.setText(Double.toString(totalPasseio));
            txtTotalProduto.setText(Double.toString(totalProdutos));

        } catch (Exception erro) {
        }
    }

 

    public String getTxtTotalProduto() {
        return txtTotalProduto.getText();
    }


    public String getTxtTotalPasseio() {
        return txtTotalPasseio.getText();
    }


    public ModeloPasseiosRealizados getModeloPasseios() {
        return modeloPasseios;
    }

    public ModeloProdutosConsumidos getModeloProdutos() {
        return modeloProdutos;
    }

     public void receberTotaisServicos(double totalProdutos, double totalPasseio) {
        try {
            txtTotalPasseio.setText(Double.toString(totalPasseio));
            txtTotalProduto.setText(Double.toString(totalProdutos));

        } catch (Exception erro) {
        }
    }
    
    

    public static ExibirConsumoHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        if (instance == null) {
            return instance = new ExibirConsumoHospedagem(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
