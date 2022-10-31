package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.DTOCliente;
import br.com.hospede.model.DTO.DTOUsuario;
import br.com.hospede.model.DAO.ClienteDAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class EditarCliente extends InterfaceFactory{
     private JPanel                 painel;
    private JLabel                 lblNome, lblCPF, lblRG, lblEstado, lblCidade, lblEndereco,
                                   lblBairro, lblCEP, lblTelefone, lblCelular, lblEmail;
    private JTextField             txtNome,txtCidade, txtEndereco, txtBairro,txtEmail;
    //Sigla dos Estados brasileiros.
    private String[]               estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MG", "PA", "PB", "PR",
                                              "PE", "PI", "RJ", "RN", "RS","RO", "RR", "SC", "SP","SE","TO"};
    private JComboBox              estado;
    private JButton                btnEditar;
    public static JMenuItem[]      itensDeMenu   = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    public static JTextField[]     camposDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos       eventos;
    private DTOUsuario                usuarioLogado;
    public static EditarCliente instance      = null;
    private MaskFormatter          mascararCPF, mascararRG, mascararCEP, mascararTelefone, mascararCelular;
    private JFormattedTextField    txtCPF, txtRG, txtCEP, txtTelefone, txtCelular;

    public EditarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        setTitle("Editar Cliente");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(295, 80, 730, 590);
        setLayout(null);
        setResizable(false);

        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.camposDeTexto = camposDeTexto;
        this.usuarioLogado = usuarioLogado;

        //Inicializando paineis.
        painel = new JPanel();
        painel.setSize(715, 550);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.black));
        painel.setLayout(null);

        lblNome = new JLabel("*Nome: ");
        lblNome.setSize(90, 20);
        lblNome.setLocation(50, 50);

        lblCPF = new JLabel("*CPF:");
        lblCPF.setSize(90, 20);
        lblCPF.setLocation(60, 100);

        lblRG = new JLabel("RG:");
        lblRG.setSize(200, 20);
        lblRG.setLocation(430, 100);

        lblEstado = new JLabel("*Estado: ");
        lblEstado.setSize(200, 20);
        lblEstado.setLocation(45, 150);

        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(200, 20);
        lblCidade.setLocation(45, 200);

        lblEndereco = new JLabel("*Endereço: ");
        lblEndereco.setSize(180, 20);
        lblEndereco.setLocation(30, 250);

        lblBairro = new JLabel("Bairro: ");
        lblBairro.setSize(100, 20);
        lblBairro.setLocation(50, 300);

        lblCEP = new JLabel("CEP:");
        lblCEP.setSize(100, 20);
        lblCEP.setLocation(60, 350);

        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(100, 20);
        lblTelefone.setLocation(40, 400);

        lblCelular = new JLabel("*Celular: ");
        lblCelular.setSize(100, 20);
        lblCelular.setLocation(408, 400);

        lblEmail = new JLabel("Email: ");
        lblEmail.setSize(100, 20);
        lblEmail.setLocation(50, 450);

        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(560, 40);
        txtNome.setLocation(100, 40);

        //Criando máscara no campo.
        try{
        mascararCPF = new MaskFormatter("###.###.###-##");
        txtCPF = new JFormattedTextField(mascararCPF);
        txtCPF.setSize(300, 40);
        txtCPF.setLocation(100, 90);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CPF \n:"+erro.getMessage());
         }
        
        //Criando máscara no campo.
        try{
        mascararRG = new MaskFormatter("##.###.###-##");
        txtRG = new JFormattedTextField(mascararRG);
        txtRG.setSize(200, 40);
        txtRG.setLocation(460, 90);
         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar RG \n:"+erro.getMessage());
         }
        

        estado = new JComboBox(estados);
        estado.setSize(560, 30);
        estado.setLocation(100, 150);

        txtCidade = new JTextField();
        txtCidade.setSize(560, 30);
        txtCidade.setLocation(100, 200);

        txtEndereco = new JTextField();
        txtEndereco.setSize(560, 30);
        txtEndereco.setLocation(100, 250);

        txtBairro = new JTextField();
        txtBairro.setSize(300, 30);
        txtBairro.setLocation(100, 300);

        //Criando máscara no campo.
         try{
        mascararCEP = new MaskFormatter("#####-###");
        txtCEP = new JFormattedTextField(mascararCEP);
        txtCEP.setSize(300, 30);
        txtCEP.setLocation(100, 350);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar CEP \n:"+erro.getMessage());
         }
        
        //Criando máscara no campo.
         try{
        mascararTelefone = new MaskFormatter("(##)####-####");
        txtTelefone = new JFormattedTextField(mascararTelefone);
        txtTelefone.setSize(300, 30);
        txtTelefone.setLocation(100, 400);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar telefone \n:"+erro.getMessage());
         }
        
         //Criando máscara no campo.
         try{
        mascararCelular = new MaskFormatter("(##)#####-####");
        txtCelular = new JFormattedTextField(mascararCelular);
        txtCelular.setSize(190, 30);
        txtCelular.setLocation(468, 400);

         }catch(Exception erro){
               JOptionPane.showMessageDialog(null,"Erro ao mascarar celular \n:"+erro.getMessage());
         }


        txtEmail = new JTextField();
        txtEmail.setSize(300, 30);
        txtEmail.setLocation(100, 450);

        //Associando botões
        btnEditar = new JButton("Editar");
        btnEditar.setSize(100, 25);
        btnEditar.setLocation(80, 500);

        //Adicionando componentes.
        add(painel);
        painel.add(lblNome);
        painel.add(lblCPF);
        painel.add(lblRG);
        painel.add(lblEstado);
        painel.add(txtNome);
        painel.add(txtCPF);
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
        painel.add(btnEditar);
        
        

        botoes[13] = btnEditar;

        getRootPane().setDefaultButton(btnEditar); //Deixa o botão Cadastrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        btnEditar.addActionListener(eventos);
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
        
       
        return cliente;
    }
    
    public void receberCliente(DTOCliente cliente){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtRG.setText(cliente.getRg());
        estado.setSelectedItem(cliente.getEstado());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
    }

    public void limparCampos() {
        txtNome.setText(null);
        txtCPF.setText(null);
        txtRG.setText(null);
        estado.setSelectedIndex(WIDTH);
        txtCidade.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtCEP.setText(null);
        txtTelefone.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
    }

    public static EditarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado) {
        if (instance == null) {
            return instance = new EditarCliente(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
