package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.JTableRenderer;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FecharHospedagem extends InterfaceFactory{
    private JPanel painel;
    private JLabel lblTituloProdutos, lblTituloPasseios, lblNome, lblCPF, lblDiaria, lblDiasHospedados,
                   lblTotalPagar, lblQuarto, lblTotalProduto, lblTotalHospedagem, lblTotalPasseio;
    private JTextField txtNome, txtCPF, txtDiaria, txtQuarto, txtDiasHospedados, txtTotalPagar,
                       txtTotalProduto, txtTotalHospedagem, txtTotalPasseio;
    private JButton btnFechar;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static FecharHospedagem instance = null;
    private int id_cliente, id_quarto, id_reserva;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private JTable                 tabelaPasseios, tabelaProdutos;
    private JScrollPane            scrollPasseios, scrollProdutos;
    private ModeloPasseiosRealizados           modeloPasseios;
    private ModeloProdutosConsumidos modeloProdutos;
    private MaskFormatter          mascararCPF;
    private DTOCliente                cliente;
    
    public FecharHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        setTitle("Fechar Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(125, 20, 1147, 700);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(1130, 660);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);
        
        
        
        modeloPasseios =  ModeloPasseiosRealizados.getInstance();
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
        
         //Criando máscara no campo.
        try{
        mascararCPF = new MaskFormatter("###.###.###-##");
        txtCPF = new JFormattedTextField(mascararCPF);
        txtCPF.setSize(300, 30);
        txtCPF.setLocation(100, 40);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        lblCPF = new JLabel("*CPF:");
        lblCPF.setSize(90, 20);
        lblCPF.setLocation(60, 40);
        lblCPF.setForeground(Color.red);
        
        lblNome = new JLabel("Nome: ");
        lblNome.setSize(90, 20);
        lblNome.setLocation(50, 100);
        
        lblDiaria = new JLabel("Diária R$");
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
        
        txtTotalHospedagem = new JTextField();
        txtTotalHospedagem.setSize(90, 30);
        txtTotalHospedagem.setLocation(980, 150);
        
        txtDiasHospedados = new JTextField();
        txtDiasHospedados.setSize(100, 30);
        txtDiasHospedados.setLocation(350, 150);
        
        txtDiaria = new JTextField();
        txtDiaria.setSize(90, 30);
        txtDiaria.setLocation(100, 150);
        
        txtNome = new JTextField();
        txtNome.setSize(560, 30);
        txtNome.setLocation(100, 90);
        
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
        
        botoes[41] = btnFechar;
        camposDeTexto[13] = txtCPF;
       
        getRootPane().setDefaultButton(btnFechar); //Deixa o botão Fechar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnFechar.addActionListener(eventos);
        txtCPF.addFocusListener(eventos);
    }
    
    public void receberHospedagem(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtQuarto.setText(quarto.getNumero());
        txtDiaria.setText(quarto.getDiaria());
        this.cliente = cliente;
        
        //Calcula o total a ser pago pela hospedagem.
        try{
        reserva.totalPagar(quarto);
        txtDiasHospedados.setText(Integer.toString(reserva.getDias_hospedados()));
        }catch(Exception erro){
                
        }
 
    }
    
    public DTOReserva getHospedagem(){
      return null;
    }
    
    public String getCpfCliente(){
     return txtCPF.getText();  
    }
    public void limparCampos(){
        txtNome.setText("");
        txtCPF.setText("");
        txtDiaria.setText("");
        txtQuarto.setText("");
        txtDiasHospedados.setText("");
        txtTotalHospedagem.setText("");
        txtTotalPasseio.setText("");
        txtTotalProduto.setText("");
        txtTotalPagar.setText("");
    }
    
    public void receberTotaisServicos(double totalHospedagem, double totalProdutos, double totalPasseio){
        try{
        txtTotalHospedagem.setText(Double.toString(totalHospedagem));
        txtTotalPasseio.setText(Double.toString(totalPasseio));
        txtTotalProduto.setText(Double.toString(totalProdutos));
        
        txtTotalPagar.setText(Double.toString(totalHospedagem + totalPasseio + totalProdutos));
        }catch(Exception erro){
        }   
    }
    
    public static FecharHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            return instance = new FecharHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
