package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.model.DTO.DTOCliente;
import br.com.hospede.model.DTO.DTOReserva;
import br.com.hospede.model.DTO.DTOUsuario;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.hospede.control.InterfaceFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class CadastrarHospedagem extends InterfaceFactory{
    
    public static CadastrarHospedagem instance;
    private final JPanel           painel;
    private final JLabel           lblNome, lblCPF, lblRG, lblEstado, lblCidade, lblEndereco,
                                   lblBairro, lblCEP, lblTelefone, lblCelular, lblEmail,
                                   lblEntrada, lblSaida, lblDiaria, lblPagamento;
    private JTextField             txtNome, txtCidade, txtEstado, txtEndereco, txtBairro, txtEmail, txtDiaria;
    public JButton                 btnHospedar;
    public JButton                 btnHospedarReserva;
    private JDateChooser           entrada, saida;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos       eventos = null;
    private int                    id_cliente, id_quarto, id_reserva;
    private MaskFormatter          mascararCPF, mascararRG, mascararCEP, mascararTelefone, mascararCelular;
    private JFormattedTextField    txtCPF, txtRG, txtCEP, txtTelefone, txtCelular;

    public CadastrarHospedagem(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        setTitle("Hospedar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(230, 70, 940, 610);
        setLayout(null);
        setResizable(false);

        //Recebendo vetores com bot??es, para adicionar o bot??o desta classe neles.
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
        lblNome.setLocation(50, 80);

        lblCPF = new JLabel("CPF:");
        lblCPF.setSize(100, 20);
        lblCPF.setLocation(50, 30);
        lblCPF.setForeground(Color.red);

        lblRG = new JLabel("RG:");
        lblRG.setSize(100, 20);
        lblRG.setLocation(430, 30);

        lblEstado = new JLabel("Estado: ");
        lblEstado.setSize(100, 20);
        lblEstado.setLocation(45, 130);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(100, 20);
        lblCidade.setLocation(408, 130);

        lblEndereco = new JLabel("Endere??o: ");
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
        
        
        
        
       
        entrada = new JDateChooser();
        entrada.setSize(130, 30);
        entrada.setLocation(100, 365);


        lblDiaria = new JLabel("Diaria: ");
        lblDiaria.setSize(100, 20);
        lblDiaria.setLocation(680, 50);

        lblPagamento = new JLabel("Pagamento: ");
        lblPagamento.setSize(100, 20);
        lblPagamento.setLocation(680, 50);

        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(700, 30);
        txtNome.setLocation(100, 75);

        //Criando m??scara no campo.
         try{
        mascararCPF = new MaskFormatter("###.###.###-##");
        txtCPF = new JFormattedTextField(mascararCPF);
        txtCPF.setSize(300, 30);
        txtCPF.setLocation(100, 26);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        //Criando m??scara no campo.
        try{
        mascararRG = new MaskFormatter("##.###.###-##");
        txtRG = new JFormattedTextField(mascararRG);
        txtRG.setSize(330, 30);
        txtRG.setLocation(470, 26);
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

       //Criando m??scara no campo.
         try{
        mascararCEP = new MaskFormatter("#####-###");
        txtCEP = new JFormattedTextField(mascararCEP);
        txtCEP.setSize(330, 30);
        txtCEP.setLocation(470, 220);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CEP \n:"+erro.getMessage());
         }
        
        //Criando m??scara no campo.
         try{
        mascararTelefone = new MaskFormatter("(##)####-####");
        txtTelefone = new JFormattedTextField(mascararTelefone);
        txtTelefone.setSize(300, 30);
        txtTelefone.setLocation(100, 265);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar telefone \n:"+erro.getMessage());
         }
        
         //Criando m??scara no campo.
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

        //Associando bot??es
        btnHospedar = new JButton("Hospedar");
        btnHospedar.setSize(100, 25);
        btnHospedar.setLocation(100, 465);
        
        btnHospedarReserva = new JButton("Hospedar cliente reservado");
        btnHospedarReserva.setSize(300, 25);
        btnHospedarReserva.setLocation(250, 465);
        
        lblSaida = new JLabel("Sa??da: ");
        lblSaida.setSize(100, 20);
        lblSaida.setLocation(45, 410);
        
        saida = new JDateChooser();
        saida.setSize(130, 30);
        saida.setLocation(100, 415);
       
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
        painel.add(btnHospedar);
        painel.add(btnHospedarReserva);
        
        
        

        //Adicionando bot??o ao vetor.
        botoes[29]       = btnHospedar;
        botoes[11]       = btnHospedarReserva;
        camposDeTexto[10] = txtCPF;

        //Associando os bot??es a classe que controla eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnHospedarReserva.addActionListener(eventos);
        btnHospedar.addActionListener(eventos);
        txtCPF.addFocusListener(eventos);
    }
    
    public DTOReserva getHospedagem(){
        DTOReserva reserva = new DTOReserva();
        reserva.setId_cliente(id_cliente);
        reserva.setTipo("RESERVAR");
        try{
        reserva.setEntrada(entrada.getDate());
        reserva.setSaida( saida.getDate());
        }catch(Exception erro){
            
        }
        
        return reserva;
    }
    
    public void receberHospedagem(DTOReserva reserva){
        id_reserva = reserva.getId_reserva();
        id_quarto = reserva.getId_quarto();
        entrada.setDate(reserva.getEntrada());
        saida.setDate(reserva.getSaida());
        
    }
    
    public DTOCliente getCliente(){
        DTOCliente cliente  = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        cliente.setNome(txtNome.getText());
        cliente.setRg(txtRG.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setBairro(txtBairro.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEstado(txtEstado.getText());
        cliente.setCep(txtCEP.getText());
        cliente.setId_cliente(id_cliente);
        return cliente;
    }
    
    public void receberliente(DTOCliente cliente){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtRG.setText(cliente.getRg());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtEstado.setText(cliente.getEstado());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
        id_cliente = cliente.getId_cliente();
        
    }
    
    public DTOReserva getReservaParaHospedar(){
        DTOReserva reserva = new DTOReserva();
        
        try{
        reserva.setEntrada(entrada.getDate());
        reserva.setSaida(saida.getDate());
        reserva.setId_reserva(id_reserva);
        }catch(Exception erro){
            
        }
        reserva.setId_cliente(id_cliente);
        reserva.setTipo("Hospedar reserva");
        reserva.setId_quarto(id_quarto);
        
        return reserva;
    }
    
    public void limparCampos(){
        //Limpando os campos.
        txtNome.setText(null);
        txtCPF.setText(null);
        txtRG.setText(null);
        txtCidade.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtCEP.setText(null);
        txtTelefone.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
        txtEstado.setText(null);
    }

    public static CadastrarHospedagem getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        if (instance == null) {
            instance = new CadastrarHospedagem(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
