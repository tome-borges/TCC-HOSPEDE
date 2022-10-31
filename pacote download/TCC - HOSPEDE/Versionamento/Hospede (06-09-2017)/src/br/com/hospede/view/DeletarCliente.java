package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.ClienteDAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeletarCliente extends InterfaceFactory{
    private JPanel               painelPessoal, painelContato;
    private JLabel               lblTituloPessoal, lblTituloContato, lblNome, lblCPF, lblRG,lblEstado, lblCidade, lblEndereco,
                                 lblBairro, lblComplemento, lblCEP, lblTelefone, lblCelular, lblEmail, lblInstrucao;
    private JTextField           txtNome,txtCPF, txtRG, txtCidade, txtEstado, txtCEP, txtEndereco, txtBairro, txtComplemento,
                                 txtTelefone, txtEmail, txtCelular;
    private JButton              bntDeletar;
    public static DeletarCliente instance = null;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos     eventos;
    private ClienteDAO           clienteDAO;
    
    public DeletarCliente(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuariLogado){
        setTitle("Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
         this.itensDeMenu = itensDeMenu;
         this.botoes=botoes;
         this.campoDeTexto = camposDeTexto;
        
        //Inicializando paineis.
        painelPessoal = new JPanel();
        painelPessoal.setSize(1050,250);
        painelPessoal.setLocation(30,30);
        painelPessoal.setBorder(BorderFactory.createLineBorder(Color.black));
        painelPessoal.setLayout(null);
        
        painelContato = new JPanel();
        painelContato.setSize(1050,250);
        painelContato.setLocation(30,295);
        painelContato.setBorder(BorderFactory.createLineBorder(Color.black));
        painelContato.setLayout(null);
        
        //Inicializando labels.
        lblTituloPessoal = new JLabel("Dados pessoais");
        lblTituloPessoal.setSize(100,20);
        lblTituloPessoal.setLocation(10,10);
        
        lblInstrucao = new JLabel("Digite o nome do cliente no campo nome.");
        lblInstrucao.setSize(320, 20);
        lblInstrucao.setLocation(10, 40);
        
        lblNome = new JLabel("Nome: ");
        lblNome.setSize(100,20);
        lblNome.setLocation(20,70);
        
        lblCPF = new JLabel("CPF:");
        lblCPF.setSize(100,20);
        lblCPF.setLocation(30,100);
        
        lblRG = new JLabel("RG:");
        lblRG.setSize(100,20);
        lblRG.setLocation(430,100);
        
        lblEstado = new JLabel("Estado: ");
        lblEstado.setSize(100,20);
        lblEstado.setLocation(15,130);
        
        lblCidade = new JLabel("Cidade: ");
        lblCidade.setSize(100,20);
        lblCidade.setLocation(408,130);
        
        lblEndereco = new JLabel("Endereço: ");
        lblEndereco.setSize(100,20);
        lblEndereco.setLocation(5,170);
        
        lblBairro = new JLabel("Bairro: ");
        lblBairro.setSize(100,20);
        lblBairro.setLocation(15,190);
        
        lblComplemento = new JLabel("Complemento: ");
        lblComplemento.setSize(100,20);
        lblComplemento.setLocation(375,190);
        
        lblCEP = new JLabel("CEP:");
        lblCEP.setSize(100,20);
        lblCEP.setLocation(25,220);
        
        lblTituloContato= new JLabel("Dados para contato");
        lblTituloContato.setSize(130,20);
        lblTituloContato.setLocation(10,10);
        
        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(100,20);
        lblTelefone.setLocation(20,50);
        
        lblCelular = new JLabel("Celular: ");
        lblCelular.setSize(100,20);
        lblCelular.setLocation(408,50);
        
        lblEmail = new JLabel("Email: ");
        lblEmail.setSize(100,20);
        lblEmail.setLocation(20,80);
        
        //Inicializando caixas de textos.
        txtNome = new JTextField();
        txtNome.setSize(700,20);
        txtNome.setLocation(70,70);
 
        
        txtCPF = new JTextField();
        txtCPF.setSize(300,20);
        txtCPF.setLocation(70,100);
        txtCPF.setEditable(false);
        
        txtRG = new JTextField();
        txtRG.setSize(300,20);
        txtRG.setLocation(470,100);
        txtRG.setEditable(false);
        
        txtEstado = new JTextField();
        txtEstado.setSize(300,20);
        txtEstado.setLocation(70,130);
        txtEstado.setEditable(false);
        
        txtCidade = new JTextField();
        txtCidade.setSize(300,20);
        txtCidade.setLocation(470,130);
        txtCidade.setEditable(false);
        
        txtEndereco = new JTextField();
        txtEndereco.setSize(700,20);
        txtEndereco.setLocation(70,160);
        txtEndereco.setEditable(false);
        
        txtBairro = new JTextField();
        txtBairro.setSize(300,20);
        txtBairro.setLocation(70,190);
        txtBairro.setEditable(false);
        
        txtComplemento = new JTextField();
        txtComplemento.setSize(300,20);
        txtComplemento.setLocation(470,190);
        txtComplemento.setEditable(false);
        
        txtCEP = new JTextField();
        txtCEP.setSize(300,20);
        txtCEP.setLocation(70,220);
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
        
        //Associando botões
        bntDeletar = new JButton("Excluir");
        bntDeletar.setSize(100,20);
        bntDeletar.setLocation(80,200);
        
        //Adicionando componentes.
        add(painelPessoal);
        painelPessoal.add(lblTituloPessoal);
        painelPessoal.add(lblInstrucao);
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
        painelContato.add(bntDeletar);
        
        campoDeTexto[4] = txtNome;
        botoes[12]      = bntDeletar;
        
        getRootPane().setDefaultButton(bntDeletar); //Deixa o botão Deletar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuariLogado);
        bntDeletar.addActionListener(eventos);
        txtNome.addFocusListener(eventos);
            
    }
    
    public Cliente getCliente(){
        Cliente cliente = new Cliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");
        cliente.setNome(txtNome.getText());
        cliente.setCpf(txtCPF.getText());
        cliente.setRg(txtRG.getText());
        cliente.setCidade(txtCidade.getText());
        cliente.setEndereco(txtEndereco.getText());
        cliente.setBairro(txtBairro.getText());
        cliente.setComplemento(txtComplemento.getText());
        cliente.setCep(txtCEP.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setCelular(txtCelular.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setEstado(txtEstado.getText());
        return cliente;
        
    }
    
    public void receberCliente(Cliente cliente){
        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtRG.setText(cliente.getRg());
        txtCidade.setText(cliente.getCidade());
        txtEndereco.setText(cliente.getEndereco());
        txtBairro.setText(cliente.getBairro());
        txtComplemento.setText(cliente.getComplemento());
        txtCEP.setText(cliente.getCep());
        txtTelefone.setText(cliente.getTelefone());
        txtCelular.setText(cliente.getCelular());
        txtEmail.setText(cliente.getEmail());
        txtEstado.setText(cliente.getEstado());
    }
    
    public void limparCampos(){
        //Limpando os campos.
        txtNome.setText(null);
        txtCPF.setText(null);
        txtRG.setText(null);
        txtCidade.setText(null);
        txtEndereco.setText(null);
        txtBairro.setText(null);
        txtComplemento.setText(null);
        txtCEP.setText(null);
        txtTelefone.setText(null);
        txtCelular.setText(null);
        txtEmail.setText(null);
        txtEstado.setText(null);
        
    }
    
    public static DeletarCliente getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuariLogado){
        if(instance == null){
            return instance = new DeletarCliente(botoes, itensDeMenu, camposDeTexto, usuariLogado);
        }
        return instance;
    }
    
}
