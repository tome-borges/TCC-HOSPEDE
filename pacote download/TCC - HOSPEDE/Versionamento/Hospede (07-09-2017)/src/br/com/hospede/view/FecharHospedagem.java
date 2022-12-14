package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
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
    private JLabel lblTituloProdutos, lblTituloPasseios, lblNome, lblCPF, lblDiaria, lblDiasHospedados, lblTotalPagar, lblQuarto;
    private JTextField txtNome, txtCPF, txtDiaria, txtQuarto, txtDiasHospedados, txtTotalPagar;
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
    private Cliente                cliente;
    
    public FecharHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Fechar Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(125, 20, 1147, 680);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com bot??es, para adicionar o bot??o desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(1130, 640);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);
        
        modeloPasseios =  ModeloPasseiosRealizados.getInstance();
        tabelaPasseios = new JTable(modeloPasseios);
        tabelaPasseios.getColumnModel().getColumn(1).setMaxWidth(60);
        scrollPasseios = new JScrollPane(tabelaPasseios);
        scrollPasseios.setSize(1020, 160);
        scrollPasseios.setLocation(50, 420);
        scrollPasseios.setBorder(BorderFactory.createLineBorder(Color.black));
        
        modeloProdutos = ModeloProdutosConsumidos.getInstance();
        tabelaProdutos = new JTable(modeloProdutos);
        scrollProdutos = new JScrollPane(tabelaProdutos);
        scrollProdutos.setSize(1020, 160);
        scrollProdutos.setLocation(50, 220);
        scrollProdutos.setBorder(BorderFactory.createLineBorder(Color.black));
        
         //Criando m??scara no campo.
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
        
        lblTituloProdutos = new JLabel("PRODUTOS CONSUMIDOS");
        lblTituloProdutos.setSize(200, 20);
        lblTituloProdutos.setLocation(55, 200);
        
        lblTituloPasseios = new JLabel("PASSEIOS REALIZADOS");
        lblTituloPasseios.setSize(200, 20);
        lblTituloPasseios.setLocation(55, 400);
        
        txtQuarto = new JTextField();
        txtQuarto.setSize(90, 30);
        txtQuarto.setLocation(570, 150);
        
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
        btnFechar.setLocation(350, 590);
        
        lblTotalPagar = new JLabel("Total R$");
        lblTotalPagar.setSize(100, 30);
        lblTotalPagar.setLocation(50, 590);
        
        txtTotalPagar = new JTextField();
        txtTotalPagar.setSize(100, 30);
        txtTotalPagar.setLocation(110, 590);
        
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
        
        botoes[41] = btnFechar;
        camposDeTexto[13] = txtCPF;
        
        getRootPane().setDefaultButton(btnFechar); //Deixa o bot??o Fechar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnFechar.addActionListener(eventos);
        txtCPF.addFocusListener(eventos);
    }
    
    public void receberHospedagem(Reserva reserva, Cliente cliente, Quarto quarto){
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
    
    public Reserva getHospedagem(){
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
    }
    
    public static FecharHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new FecharHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
