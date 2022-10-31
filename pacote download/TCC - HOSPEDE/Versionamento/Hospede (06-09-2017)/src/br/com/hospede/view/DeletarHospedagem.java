package br.com.hospede.view;
import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeletarHospedagem extends InterfaceFactory{
    private JPanel painelPessoal, painelContato, painelReserva;
    private JLabel lblTituloPessoal, lblTituloContato, lblTituloReserva, lblNome, lblCPF, lblRG, lblEstado, lblCidade, lblEndereco,
            lblBairro, lblComplemento, lblCEP, lblTelefone, lblCelular, lblEmail, lblCrianca, lblAdulto,
            lblEntrada, lblSaida, lblDiaria, lblPagamento;
    private JTextField txtNome, txtCPF, txtRG, txtCidade, txtEstado, txtCEP, txtEndereco, txtBairro, txtComplemento,
            txtTelefone, txtEmail, txtCelular, txtDiaria, txtEntrada, txtSaida, txtPagamento, txtCrianca, txtAdulto, txtQuarto;
    private JButton btnExcluir;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static DeletarHospedagem instance = null;
    private int id_cliente, id_quarto, id_reserva;
    private SimpleDateFormat    formatarData = new SimpleDateFormat("dd/MM/yyyy");
    
    public DeletarHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        setTitle("Deletar Hospedagem");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu=itensDeMenu;
        this.botoes=botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painelPessoal = new JPanel();
        painelPessoal.setSize(1050, 250);
        painelPessoal.setLocation(30, 30);
        painelPessoal.setBorder(BorderFactory.createLineBorder(Color.black));
        painelPessoal.setLayout(null);

        painelContato = new JPanel();
        painelContato.setSize(1050, 120);
        painelContato.setLocation(30, 295);
        painelContato.setBorder(BorderFactory.createLineBorder(Color.black));
        painelContato.setLayout(null);

        painelReserva = new JPanel();
        painelReserva.setSize(1050, 120);
        painelReserva.setLocation(30, 430);
        painelReserva.setBorder(BorderFactory.createLineBorder(Color.black));
        painelReserva.setLayout(null);

        //Inicializando labels.
        lblTituloPessoal = new JLabel("Dados pessoais");
        lblTituloPessoal.setSize(100, 20);
        lblTituloPessoal.setLocation(10, 10);

        lblNome = new JLabel("Nome: ");
        lblNome.setSize(100, 20);
        lblNome.setLocation(20, 50);

        lblCPF = new JLabel("CPF:");
        lblCPF.setSize(100, 20);
        lblCPF.setLocation(30, 80);

        lblRG = new JLabel("RG:");
        lblRG.setSize(100, 20);
        lblRG.setLocation(430, 80);

        lblEstado = new JLabel("Estado: ");
        lblEstado.setSize(100, 20);
        lblEstado.setLocation(15, 110);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(100, 20);
        lblCidade.setLocation(408, 110);

        lblEndereco = new JLabel("Endereço: ");
        lblEndereco.setSize(100, 20);
        lblEndereco.setLocation(5, 140);

        lblBairro = new JLabel("Bairro: ");
        lblBairro.setSize(100, 20);
        lblBairro.setLocation(15, 170);

        lblComplemento = new JLabel("Complemento: ");
        lblComplemento.setSize(100, 20);
        lblComplemento.setLocation(375, 170);

        lblCEP = new JLabel("CEP:");
        lblCEP.setSize(100, 20);
        lblCEP.setLocation(25, 200);

        lblTituloContato = new JLabel("Dados para contato");
        lblTituloContato.setSize(130, 20);
        lblTituloContato.setLocation(10, 10);

        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(100, 20);
        lblTelefone.setLocation(20, 50);

        lblCelular = new JLabel("Celular: ");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(408, 50);

        lblEmail = new JLabel("Email: ");
        lblEmail.setSize(100, 20);
        lblEmail.setLocation(20, 80);

        lblCrianca = new JLabel("Crianças: ");
        lblCrianca.setSize(100, 20);
        lblCrianca.setLocation(20, 50);

        lblTituloReserva = new JLabel("Dados para reserva");
        lblTituloReserva.setSize(150, 20);
        lblTituloReserva.setLocation(10, 10);

        lblAdulto = new JLabel("Adultos: ");
        lblAdulto.setSize(100, 20);
        lblAdulto.setLocation(160, 50);

        lblEntrada = new JLabel("Entrada: ");
        lblEntrada.setSize(100, 20);
        lblEntrada.setLocation(280, 50);

        lblSaida = new JLabel("Saída: ");
        lblSaida.setSize(100, 20);
        lblSaida.setLocation(480, 50);

        lblDiaria = new JLabel("Diaria: ");
        lblDiaria.setSize(100, 20);
        lblDiaria.setLocation(680, 50);

        lblPagamento = new JLabel("Pagamento: ");
        lblPagamento.setSize(100, 20);
        lblPagamento.setLocation(830, 50);

        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(700,20);
        txtNome.setLocation(70,50);
        
        txtCPF = new JTextField();
        txtCPF.setSize(300,20);
        txtCPF.setLocation(70,80);
        txtCPF.setEditable(false);
    
        
        txtRG = new JTextField();
        txtRG.setSize(300,20);
        txtRG.setLocation(470,80);
        txtRG.setEditable(false);
   
        
        txtEstado = new JTextField();
        txtEstado.setSize(300,20);
        txtEstado.setLocation(70,110);
        txtEstado.setEditable(false);
    
        
        txtCidade = new JTextField();
        txtCidade.setSize(300,20);
        txtCidade.setLocation(470,110);
        txtCidade.setEditable(false);
     
        
        txtEndereco = new JTextField();
        txtEndereco.setSize(700,20);
        txtEndereco.setLocation(70,140);
        txtEndereco.setEditable(false);
        
        txtBairro = new JTextField();
        txtBairro.setSize(300,20);
        txtBairro.setLocation(70,170);
        txtBairro.setEditable(false);
        
        txtComplemento = new JTextField();
        txtComplemento.setSize(300,20);
        txtComplemento.setLocation(470,170);
        txtComplemento.setEditable(false);
        
        txtCEP = new JTextField();
        txtCEP.setSize(300,20);
        txtCEP.setLocation(70,200);
        txtCEP.setEditable(false);
        
        txtTelefone = new JTextField();
        txtTelefone.setSize(300,20);
        txtTelefone.setLocation(80,50);
        txtTelefone.setEditable(false);
        
        txtCelular= new JTextField();
        txtCelular.setSize(300,20);
        txtCelular.setLocation(468,50);
        txtCelular.setEditable(false);
        
        txtEmail= new JTextField();
        txtEmail.setSize(300,20);
        txtEmail.setLocation(80,80);
        txtEmail.setEditable(false);
        
        txtCrianca = new JTextField();
        txtCrianca.setSize(50,20);
        txtCrianca.setLocation(80,50);
        txtCrianca.setEditable(false);
        
        txtAdulto = new JTextField();
        txtAdulto.setSize(50,20);
        txtAdulto.setLocation(210,50);
        txtAdulto.setEditable(false);
        
        txtEntrada = new JTextField();
        txtEntrada.setSize(130,20);
        txtEntrada.setLocation(330,50);
        txtEntrada.setEditable(false);
        
        txtSaida = new JTextField();
        txtSaida.setSize(130,20);
        txtSaida.setLocation(520,50);
        txtSaida.setEditable(false);
        
        txtDiaria = new JTextField();
        txtDiaria.setSize(100,20);
        txtDiaria.setLocation(720,50);
        txtDiaria.setEditable(false);
        
        txtPagamento = new JTextField();
        txtPagamento.setSize(100,20);
        txtPagamento.setLocation(900,50);
        txtPagamento.setEditable(false);
        
        txtQuarto = new JTextField();
       

        //Associando botões
        btnExcluir = new JButton("Excluir");
        btnExcluir.setSize(100, 20);
        btnExcluir.setLocation(80, 90);

        //Adicionando componentes.
        add(painelPessoal);
        painelPessoal.add(lblTituloPessoal);
        painelPessoal.add(lblNome);
        painelPessoal.add(txtNome);
        painelPessoal.add(lblCPF);
        painelPessoal.add(txtCPF);
        painelPessoal.add(lblRG);
        painelPessoal.add(txtRG);
        painelPessoal.add(lblEstado);
        painelPessoal.add(txtEstado);
        painelPessoal.add(lblCidade);
        painelPessoal.add(txtCidade);
        painelPessoal.add(lblEndereco);
        painelPessoal.add(txtEndereco);
        painelPessoal.add(lblBairro);
        painelPessoal.add(txtBairro);
        painelPessoal.add(lblComplemento);
        painelPessoal.add(txtComplemento);
        painelPessoal.add(lblCEP);
        painelPessoal.add(txtCEP);
        add(painelContato);
        painelContato.add(lblTituloContato);
        painelContato.add(lblTelefone);
        painelContato.add(txtTelefone);
        painelContato.add(lblCelular);
        painelContato.add(txtCelular);
        painelContato.add(lblEmail);
        painelContato.add(txtEmail);
        add(painelReserva);
        painelReserva.add(lblTituloReserva);
        painelReserva.add(lblCrianca);
        painelReserva.add(txtCrianca);
        painelReserva.add(lblAdulto);
        painelReserva.add(txtAdulto);
        painelReserva.add(lblEntrada);
        painelReserva.add(txtEntrada);
        painelReserva.add(lblSaida);
        painelReserva.add(txtSaida);
        painelReserva.add(lblDiaria);
        painelReserva.add(txtDiaria);
        painelReserva.add(lblPagamento);
        painelReserva.add(txtPagamento);
        painelReserva.add(btnExcluir);
        
        campoDeTexto[12] = txtNome;
        botoes[28] = btnExcluir;
        
        getRootPane().setDefaultButton(btnExcluir); //Deixa o botão Excluir em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        txtNome.addFocusListener(eventos);
        btnExcluir.addActionListener(eventos);
    }
    
    public void receberHospedagem(Reserva reserva, Cliente cliente, Quarto quarto){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtRG.setText(cliente.getRg());
        txtEstado.setText(cliente.getEstado());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtComplemento.setText(cliente.getComplemento());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
        txtCrianca.setText(reserva.getQuantidade_de_criancas());
        txtAdulto.setText(reserva.getQuantidade_de_adultos());
        try{
        txtEntrada.setText(formatarData.format(reserva.getEntrada()));
        txtSaida.setText(formatarData.format(reserva.getSaida()));
        txtPagamento.setText(reserva.getFormaPagamento());
        }catch(Exception erro){
            
        }
        id_quarto = quarto.getId_quarto();
        id_reserva = reserva.getId_reserva();
    }
    
    public Reserva getHospedagem(){
        Reserva reserva = new Reserva();
        
        reserva.setQuantidade_de_criancas(txtCrianca.getText());
        reserva.setQuantidade_de_adultos(txtAdulto.getText());
        reserva.setFormaPagamento(txtPagamento.getText());
        reserva.setId_cliente(id_cliente);
        reserva.setId_quarto(id_quarto);
        reserva.setId_reserva(id_reserva);
        
        return reserva;
    }
    
    public Cliente getCliente(){
        Cliente cliente = new Cliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setRg(txtRG.getText());
        cliente.setEstado(txtEstado.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setBairro(txtBairro.getText());
        cliente.setCep(txtCEP.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setEmail(txtEmail.getText());
        
        return cliente;
    } 
    
    public void limparCampos(){
        txtNome.setText(null);
        txtCPF.setText(null);
        txtRG.setText(null);
        txtEstado.setText(null);
        txtCidade.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtComplemento.setText(null);
        txtCEP.setText(null);
        txtTelefone.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
        txtCrianca.setText(null);
        txtAdulto.setText(null);
        txtEntrada.setText(null);
        txtSaida.setText(null);
        txtDiaria.setText(null);
        txtPagamento.setText(null);
    }
    
    public static DeletarHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new DeletarHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
