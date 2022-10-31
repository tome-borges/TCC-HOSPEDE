package br.com.hospede.view;
import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class EditarHospedagem extends InterfaceFactory{
     public static EditarHospedagem instance;
    private final JPanel           painel;
    private final JLabel           lblNome, lblCPF, lblRG, lblEstado, lblCidade, lblEndereco,
                                   lblBairro, lblCEP, lblTelefone, lblCelular, lblEmail,
                                   lblEntrada, lblSaida, lblDiaria, lblPagamento;
    private JTextField             txtNome, txtCidade, txtEstado, txtEndereco, txtBairro, txtEmail, txtDiaria;
    private JComboBox              txtPagamento;
    private final String[]         formas = {"Cheque", "Dinheiro", "Crédito", "Débito"};
    private JButton                btnReservar;
    private JDateChooser           entrada, saida;
    private String                 stringData;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos       eventos = null;
    private int                    id_cliente, id_reserva;
    private MaskFormatter          mascararCPF, mascararRG, mascararCEP, mascararTelefone, mascararCelular;
    private JFormattedTextField    txtCPF, txtRG, txtCEP, txtTelefone, txtCelular;

    public EditarHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado) {
        setTitle("Editar Reserva");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(230, 70, 940, 610);
        setLayout(null);
        setResizable(false);

        //Recebendo vetores com botões, para adicionar o botão desta classe neles.
        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = camposDeTexto;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(923, 570);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);

        //Inicializando labels.

        lblNome = new JLabel("Nome: ");
        lblNome.setSize(100, 20);
        lblNome.setLocation(50, 30);

        lblCPF = new JLabel("CPF:");
        lblCPF.setSize(100, 20);
        lblCPF.setLocation(50, 80);

        lblRG = new JLabel("RG:");
        lblRG.setSize(100, 20);
        lblRG.setLocation(430, 80);

        lblEstado = new JLabel("Estado: ");
        lblEstado.setSize(100, 20);
        lblEstado.setLocation(45, 130);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(100, 20);
        lblCidade.setLocation(408, 130);

        lblEndereco = new JLabel("Endereço: ");
        lblEndereco.setSize(100, 20);
        lblEndereco.setLocation(30, 170);

        lblBairro = new JLabel("Bairro: ");
        lblBairro.setSize(100, 20);
        lblBairro.setLocation(45, 225);

        lblCEP = new JLabel("CEP:");
        lblCEP.setSize(100, 20);
        lblCEP.setLocation(418, 224);

        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(100, 20);
        lblTelefone.setLocation(45, 275);

        lblCelular = new JLabel("Celular: ");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(408, 265);

        lblEmail = new JLabel("Email: ");
        lblEmail.setSize(100, 20);
        lblEmail.setLocation(50, 320);

        lblEntrada = new JLabel("Entrada: ");
        lblEntrada.setSize(100, 20);
        lblEntrada.setLocation(45, 370);

        lblSaida = new JLabel("Saída: ");
        lblSaida.setSize(100, 20);
        lblSaida.setLocation(45, 410);

        lblDiaria = new JLabel("Diaria: ");
        lblDiaria.setSize(100, 20);
        lblDiaria.setLocation(680, 50);

        lblPagamento = new JLabel("Pagamento: ");
        lblPagamento.setSize(100, 20);
        lblPagamento.setLocation(680, 50);

        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(700, 30);
        txtNome.setLocation(100, 25);

        //Criando máscara no campo.
        try{
        mascararCPF = new MaskFormatter("###.###.###-##");
        txtCPF = new JFormattedTextField(mascararCPF);
        txtCPF.setSize(300, 30);
        txtCPF.setLocation(100, 76);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        //Criando máscara no campo.
        try{
        mascararRG = new MaskFormatter("##.###.###-##");
        txtRG = new JFormattedTextField(mascararRG);
        txtRG.setSize(330, 30);
        txtRG.setLocation(470, 76);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar RG \n:"+erro.getMessage());
         }

        txtEstado = new JTextField();
        txtEstado.setSize(300, 30);
        txtEstado.setLocation(100, 120);

        txtCidade = new JTextField();
        txtCidade.setSize(330, 30);
        txtCidade.setLocation(470, 120);

        txtEndereco = new JTextField();
        txtEndereco.setSize(700, 30);
        txtEndereco.setLocation(100, 170);

        txtBairro = new JTextField();
        txtBairro.setSize(300, 30);
        txtBairro.setLocation(100, 220);

       //Criando máscara no campo.
         try{
        mascararCEP = new MaskFormatter("#####-###");
        txtCEP = new JFormattedTextField(mascararCEP);
        txtCEP.setSize(330, 30);
        txtCEP.setLocation(470, 220);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CEP \n:"+erro.getMessage());
         }
        
        //Criando máscara no campo.
         try{
        mascararTelefone = new MaskFormatter("(##)####-####");
        txtTelefone = new JFormattedTextField(mascararTelefone);
        txtTelefone.setSize(300, 30);
        txtTelefone.setLocation(100, 265);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar telefone \n:"+erro.getMessage());
         }
        
         //Criando máscara no campo.
         try{
        mascararCelular = new MaskFormatter("(##)#####-####");
        txtCelular = new JFormattedTextField(mascararCelular);
        txtCelular.setSize(330, 30);
        txtCelular.setLocation(470, 261);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar celular \n:"+erro.getMessage());
         }

        txtEmail = new JTextField();
        txtEmail.setSize(300, 30);
        txtEmail.setLocation(100, 315);

        entrada = new JDateChooser();
        entrada.setSize(130, 30);
        entrada.setLocation(100, 365);

        saida = new JDateChooser();
        saida.setSize(130, 30);
        saida.setLocation(100, 415);

        //Associando botões
        btnReservar = new JButton("Editar");
        btnReservar.setSize(100, 25);
        btnReservar.setLocation(100, 465);

        //Adicionando componentes.
        add(painel);
        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblCPF);
        painel.add(txtCPF);
        painel.add(lblRG);
        painel.add(txtRG);
        painel.add(txtEstado);
        painel.add(lblEstado);
        painel.add(lblCidade);
        painel.add(txtCidade);
        painel.add(lblEndereco);
        painel.add(txtEndereco);
        painel.add(lblBairro);
        painel.add(txtBairro);
        painel.add(lblCEP);
        painel.add(txtCEP);
        painel.add(lblTelefone);
        painel.add(txtTelefone);
        painel.add(lblCelular);
        painel.add(txtCelular);
        painel.add(lblEmail);
        painel.add(txtEmail);
        painel.add(lblEntrada);
        painel.add(entrada);
        painel.add(lblSaida);
        painel.add(saida);
        painel.add(btnReservar);
      
        //Adicionando botão ao vetor.
        botoes[27]       = btnReservar;
       

        //Associando os botões a classe que controla eventos.
        getRootPane().setDefaultButton(btnReservar); //Deixa o botão Reservar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnReservar.addActionListener(eventos);
        txtCPF.addFocusListener(eventos);
    }
    
    public void receberHospedagem(Reserva reserva, Cliente cliente, Quarto quarto){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtRG.setText(cliente.getRg());
        txtEstado.setText(cliente.getEstado());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
        try{
            entrada.setDate(reserva.getEntrada());
            saida.setDate(reserva.getSaida());
            
        }catch(Exception erro){
        }
        id_reserva = reserva.getId_reserva();
    }
    
    public Reserva getHospedagem(){
        Reserva reserva = new Reserva();
        
        try{
        reserva.setEntrada(entrada.getDate());
        reserva.setSaida(saida.getDate());
        }catch( Exception erro){ 
        }
        reserva.setId_cliente(id_cliente);
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
        try{
        txtNome.setText(null);
        txtCPF.setText(null);
        txtRG.setText(null);
        txtEstado.setText(null);
        txtCidade.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtCEP.setText(null);
        txtTelefone.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
        txtDiaria.setText(null);
        }catch(Exception erro){
                
                }

    }
    
    public static EditarHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado){
        if(instance == null){
            return instance = new EditarHospedagem( botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
