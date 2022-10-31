package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOUsuario;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class EditarCliente extends InterfaceFactory{
     private JPanel painel;
     private int id_cliente;
    private JLabel lblNome, lblCPF, lblRG, lblEstado, lblCidade, lblEndereco, lblCpf, lblPassaporte,
            lblBairro, lblCEP, lblTelefone, lblCelular, lblEmail;
    private JTextField txtNome, txtCidade, txtEndereco, txtBairro, txtEmail, txtPassaporte;
    //Sigla dos Estados brasileiros.
    private String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MG", "PA", "PB", "PR",
        "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO","Estrangeiro"};
    private JComboBox estado;
    private JButton btnSalvar;
    public static JMenuItem[] itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[] botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    public static JTextField[] camposDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    private DTOUsuario usuarioLogado;
    public static EditarCliente instance = null;
    private MaskFormatter mascararCPF, mascararRG, mascararCEP, mascararTelefone, mascararCelular, mascararPassaporte;
    private JFormattedTextField txtCPF, txtRG, txtCEP, txtTelefone, txtCelular;
    private JRadioButton rdbCPF, rdbPassaporte;

    public EditarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        setTitle("Editar Cliente");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(325, 80, 760, 590);
        setLayout(null);
        setResizable(false);

        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.camposDeTexto = camposDeTexto;
        this.usuarioLogado = usuarioLogado;

        //Inicializando paineis.
        
        try{
         mascararCPF = new MaskFormatter("###.###.###-##");
        }catch(Exception erro){
            
        }
        painel = new JPanel();
        painel.setSize(745, 550);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);

        lblNome = new JLabel("*Nome: ");
        lblNome.setSize(90, 30);
        lblNome.setLocation(80, 50);

        lblCPF = new JLabel("*CPF/PASSAPORTE:");
        lblCPF.setSize(150, 20);
        lblCPF.setLocation(5, 110);

        lblRG = new JLabel("RG:");
        lblRG.setSize(200, 20);
        lblRG.setLocation(460, 110);

        lblEstado = new JLabel("*Estado: ");
        lblEstado.setSize(200, 20);
        lblEstado.setLocation(75, 150);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(200, 20);
        lblCidade.setLocation(75, 200);

        lblEndereco = new JLabel("*Endereço: ");
        lblEndereco.setSize(180, 20);
        lblEndereco.setLocation(60, 250);

        lblBairro = new JLabel("Bairro: ");
        lblBairro.setSize(100, 20);
        lblBairro.setLocation(80, 300);

        lblCEP = new JLabel("CEP:");
        lblCEP.setSize(100, 20);
        lblCEP.setLocation(90, 350);

        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(100, 20);
        lblTelefone.setLocation(70, 400);

        lblCelular = new JLabel("*Celular: ");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(438, 400);

        lblEmail = new JLabel("Email: ");
        lblEmail.setSize(100, 20);
        lblEmail.setLocation(80, 450);

        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(560, 30);
        txtNome.setLocation(130, 50);

        //Criando máscara no campo.
        lblCpf = new JLabel("CPF ");
        lblCpf.setSize(300, 40);
        lblCpf.setLocation(160, 100);

        rdbCPF = new JRadioButton();
        rdbCPF.setSize(300, 40);
        rdbCPF.setLocation(130, 100);

        lblPassaporte = new JLabel("PASSAPORTE ");
        lblPassaporte.setSize(300, 40);
        lblPassaporte.setLocation(260, 100);

        rdbPassaporte = new JRadioButton();
        rdbPassaporte.setSize(300, 40);
        rdbPassaporte.setLocation(230, 100);

        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rdbCPF);
        grupoBotoes.add(rdbPassaporte);


        try {
            mascararRG = new MaskFormatter("##.###.###-##");
            txtRG = new JFormattedTextField(mascararRG);
            txtRG.setSize(200, 30);
            txtRG.setLocation(490, 100);
            txtRG.setEditable(false);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mascarar RG \n:" + erro.getMessage());
        }

                txtCPF = new JFormattedTextField(mascararCPF);
                txtCPF.setFocusLostBehavior(JFormattedTextField.COMMIT);
                txtCPF.setEditable(false);
                
                
                txtPassaporte = new JTextField();
                txtPassaporte.setEditable(false);
                
                
                

           
        
        estado = new JComboBox(estados);
        estado.setSize(560, 30);
        estado.setLocation(130, 150);

        txtCidade = new JTextField();
        txtCidade.setSize(560, 30);
        txtCidade.setLocation(130, 200);

        txtEndereco = new JTextField();
        txtEndereco.setSize(560, 30);
        txtEndereco.setLocation(130, 250);

        txtBairro = new JTextField();
        txtBairro.setSize(300, 30);
        txtBairro.setLocation(130, 300);

        try{
        mascararCEP = new MaskFormatter("#####-###");
        txtCEP = new JFormattedTextField(mascararCEP);
        txtCEP.setSize(300, 30);
        txtCEP.setLocation(130, 350);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CEP \n:"+erro.getMessage());
         }
       
        //Criando máscara no campo.
        try {
            mascararTelefone = new MaskFormatter("(##)####-####");
            txtTelefone = new JFormattedTextField(mascararTelefone);
            txtTelefone.setSize(300, 30);
            txtTelefone.setLocation(130, 400);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mascarar telefone \n:" + erro.getMessage());
        }

        //Criando máscara no campo.
        try {
            mascararCelular = new MaskFormatter("(##)#####-####");
            txtCelular = new JFormattedTextField(mascararCelular);
            txtCelular.setSize(190, 30);
            txtCelular.setLocation(498, 400);

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mascarar celular \n:" + erro.getMessage());
        }

        txtEmail = new JTextField();
        txtEmail.setSize(300, 30);
        txtEmail.setLocation(130, 450);

        //Associando botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setSize(100, 25);
        btnSalvar.setLocation(110, 500);

        //Adicionando componentes.
        add(painel);
        painel.add(lblNome);
        painel.add(lblCPF);
        painel.add(lblRG);
        painel.add(lblEstado);
        painel.add(txtNome);
        painel.add(txtRG);
        painel.add(estado);
        painel.add(lblCidade);
        painel.add(lblEndereco);
        painel.add(txtCidade);
        painel.add(txtEndereco);
        painel.add(lblBairro);
        painel.add(lblCEP);
        painel.add(lblTelefone);
        painel.add(lblCelular);
        painel.add(txtBairro);
        painel.add(txtCEP);
        painel.add(txtTelefone);
        painel.add(txtCelular);
        painel.add(txtEmail);
        painel.add(lblEmail);
        painel.add(btnSalvar);
        

        botoes[13] = btnSalvar;

        getRootPane().setDefaultButton(btnSalvar); //Deixa o botão Cadastrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        btnSalvar.addActionListener(eventos);
    }

    public DTOCliente getCliente() {
        DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        cliente.setNome(txtNome.getText());
        cliente.setRg(txtRG.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setBairro(txtBairro.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEstado(estados[estado.getSelectedIndex()]);
        cliente.setCep(txtCEP.getText());
        cliente.setId_cliente(id_cliente);
        cliente.setPassaport(txtPassaporte.getText());
        
       
        return cliente;
    }
    
    public void receberCliente(DTOCliente cliente){
        txtNome.setText(cliente.getNome());
        
        
        
        if(cliente.getCpf().equals("   .   .   -  ")){
                txtPassaporte.setSize(300, 30);
                txtPassaporte.setLocation(130, 103);
                
                painel.remove(txtCPF);
                painel.add(txtPassaporte); 
                txtPassaporte.setText(cliente.getPassaport());
        } else {
                txtCPF.setSize(300, 30);
                txtCPF.setLocation(130, 103);
                
                painel.remove(txtPassaporte);
                painel.add(txtCPF);
                txtCPF.setText(cliente.getCpf());
        }
        txtRG.setText(cliente.getRg());
        estado.setSelectedItem(cliente.getEstado());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
        id_cliente = cliente.getId_cliente();
    }

    public void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtPassaporte.setText("");
        txtRG.setText("");
        estado.setSelectedIndex(WIDTH);
        txtCidade.setText("");
        txtEndereco.setText("");
        txtBairro.setText("");
        txtCEP.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtEmail.setText("");
    }
    
    public static EditarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        if (instance == null) {
            return instance = new EditarCliente(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
