package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.UsuarioDAO;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CadastrarUsuario extends InterfaceFactory {

     //Objetos.
    private JPanel              painelPessoal = null;
    private JLabel              lblLogin, lblEmail, lblSenha, lblTelefone,lblFuncao;
    private JButton             btnCadastrar;
    private JTextField          txtLogin, txtEmail, pswSenha;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos    eventos;
    private final String[]      funcoes = {"Atendente", "Gerente"};
    public static CadastrarUsuario instance = null;
    private UsuarioDAO          usuarioDAO = null;
    private MaskFormatter       mascararTelefone;
    private JComboBox           funcao;
    private JFormattedTextField txtTelefone;
    

    //Construtor..
    public CadastrarUsuario(JButton botoes[], JMenuItem itensDeMenu[], JTextField campoDeTexto[], Usuario usuarioLogado) {
        setTitle("Cadastrar Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(370, 150, 530, 410);
        setLayout(null);
        setResizable(false);

        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = campoDeTexto;

        //Inicializando painel.
        painelPessoal = new JPanel();
        painelPessoal.setSize(512, 372);
        painelPessoal.setLocation(5, 5);
        painelPessoal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painelPessoal.setLayout(null);

        lblLogin = new JLabel("*Login:");
        lblLogin.setSize(90, 20);
        lblLogin.setLocation(50, 50);

        lblEmail = new JLabel("Email:");
        lblEmail.setSize(90, 20);
        lblEmail.setLocation(60, 100);

        lblSenha = new JLabel("*Senha: ");
        lblSenha.setSize(200, 20);
        lblSenha.setLocation(45, 150);

        lblTelefone = new JLabel("*Telefone: ");
        lblTelefone.setSize(200, 60);
        lblTelefone.setLocation(35, 180);
        
        lblFuncao = new JLabel("*Função: ");
        lblFuncao.setSize(200, 20);
        lblFuncao.setLocation(40, 260);

        //Iniciando caixas de textos.
        txtLogin = new JTextField();
        txtLogin.setSize(300, 40);
        txtLogin.setLocation(100, 40);

        txtEmail = new JTextField();
        txtEmail.setSize(300, 40);
        txtEmail.setLocation(100, 90);

        pswSenha = new JTextField();
        pswSenha.setSize(300, 40);
        pswSenha.setLocation(100, 140);
        
        //Inicializando jcombobox.
        funcao = new JComboBox(funcoes);
        funcao.setSize(300, 30);
        funcao.setLocation(100, 250);

        //Criando máscaro para o campo Telefone.
        try{
        mascararTelefone = new MaskFormatter("(##) #####-####");
        txtTelefone = new JFormattedTextField(mascararTelefone);
        txtTelefone.setSize(200, 40);
        txtTelefone.setLocation(100, 190);
        }catch(Exception erro){
                JOptionPane.showMessageDialog(null,"Erro ao mascarar o campo telefone\n"+erro.getMessage());
        }

        //Inicializando botões
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setSize(100, 25);
        btnCadastrar.setLocation(100, 320);

        //Adicionando componentes.
        add(painelPessoal);
        painelPessoal.add(lblLogin);
        painelPessoal.add(txtLogin);
        painelPessoal.add(lblEmail);
        painelPessoal.add(txtEmail);
        painelPessoal.add(lblSenha);
        painelPessoal.add(pswSenha);
        painelPessoal.add(txtTelefone);
        painelPessoal.add(lblTelefone);
        painelPessoal.add(lblFuncao);
        painelPessoal.add(funcao);
        painelPessoal.add(btnCadastrar);

        botoes[4] = btnCadastrar;
        campoDeTexto[1] = txtLogin;

        getRootPane().setDefaultButton(btnCadastrar); //Deixa o botão Editar em foco para o KeyListener.
        eventos = new ControlarEventos( botoes,itensDeMenu,campoDeTexto, usuarioLogado);
        btnCadastrar.addActionListener(eventos);
    }

    public Usuario getUsuario() {
        Usuario usuario = new Usuario(0,0,txtLogin.getText(),pswSenha.getText(),txtEmail.getText()
                                      ,txtTelefone.getText(),funcoes[funcao.getSelectedIndex()]);
        return usuario;

    }
        
    public void recebeUsuario(Usuario usuario) {
        txtLogin.setText(usuario.getLogin());
        txtEmail.setText(usuario.getEmail());
        pswSenha.setText(usuario.getSenha());
        txtTelefone.setText(usuario.getTelefone());
        funcao.setSelectedItem(usuario.getFuncao());
        
        
    }
    
    public void limparCampos(){
        txtLogin.setText(null);
        txtEmail.setText(null);
        pswSenha.setText(null);
        txtTelefone.setText(null);
        
        
        
    }

    public static CadastrarUsuario getInstance( JButton botoes[],JMenuItem itensDeMenu[], JTextField campoDeTexto[], Usuario usuarioLogado) {
        if (instance == null) {
            return instance = new CadastrarUsuario( botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        }
        return instance;
    }

}
