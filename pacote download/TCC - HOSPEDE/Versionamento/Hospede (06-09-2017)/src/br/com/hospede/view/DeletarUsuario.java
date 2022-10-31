package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeletarUsuario extends InterfaceFactory{
     //Objetos.
    private JPanel               painelPessoal = null;
    private JLabel               lblTitulo, lblLogin, lblEmail, lblSenha, lblTelefone, lblFuncao, lblInstrucao;
    private JButton              btnDeletar;
    private JTextField           txtLogin, txtEmail, txtTelefone, txtFuncao, pswSenha;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos     eventos;

    public static DeletarUsuario instance = null;
    
    //Construtor.
    public DeletarUsuario(JButton botoe[],JMenuItem itensDeMenu[], Usuario usuarioLogado, JTextField[] camosDeTexto){
        setTitle("Deletar Usuário");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(215, 110, 1127, 610);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        this.botoes=botoe;
        this.campoDeTexto = campoDeTexto;
        this.itensDeMenu = itensDeMenu;
        

        //Inicializando painel.
        painelPessoal = new JPanel();
        painelPessoal.setSize(1050, 300);
        painelPessoal.setLocation(30, 30);
        painelPessoal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painelPessoal.setLayout(null);

        //Inicializando Labels.
        lblTitulo = new JLabel("Preencha o campo abaixo: ");
        lblTitulo.setSize(200, 20);
        lblTitulo.setLocation(10, 10);
        
        lblInstrucao = new JLabel("Digite o nome do usuário no campo 'Login'.");
        lblInstrucao.setSize(300, 20);
        lblInstrucao.setLocation(10, 40);

        lblLogin = new JLabel("Login:");
        lblLogin.setSize(90, 20);
        lblLogin.setLocation(20, 90);

        lblEmail = new JLabel("Email:");
        lblEmail.setSize(90, 20);
        lblEmail.setLocation(20, 120);

        lblSenha = new JLabel("Senha: ");
        lblSenha.setSize(200, 20);
        lblSenha.setLocation(15, 150);

        lblTelefone = new JLabel("Telefone: ");
        lblTelefone.setSize(200, 20);
        lblTelefone.setLocation(5, 180);

        lblFuncao = new JLabel("Função: ");
        lblFuncao.setSize(200, 20);
        lblFuncao.setLocation(10, 210);

        //Iniciando caixas de textos.
        txtLogin = new JTextField();
        txtLogin.setSize(300, 20);
        txtLogin.setLocation(60, 90);
       

        txtEmail = new JTextField();
        txtEmail.setSize(300, 20);
        txtEmail.setLocation(60, 120);
        txtEmail.setEditable(false);

        pswSenha = new JTextField();
        pswSenha.setSize(300, 20);
        pswSenha.setLocation(60, 150);
        pswSenha.setEditable(false);

        txtTelefone = new JTextField();
        txtTelefone.setSize(300, 20);
        txtTelefone.setLocation(60, 180);
        txtTelefone.setEditable(false);

        //Inicializando jcombobox.
        txtFuncao = new JTextField();
        txtFuncao.setSize(300, 20);
        txtFuncao.setLocation(60, 210);
        txtFuncao.setEditable(false);

        //Inicializando botões
        btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(100, 20);
        btnDeletar.setLocation(10, 270);

        //Adicionando componentes.
        add(painelPessoal);
        painelPessoal.add(lblTitulo);
        painelPessoal.add(lblInstrucao);
        painelPessoal.add(lblLogin);
        painelPessoal.add(txtLogin);
        painelPessoal.add(lblEmail);
        painelPessoal.add(txtEmail);
        painelPessoal.add(lblSenha);
        painelPessoal.add(pswSenha);
        painelPessoal.add(lblTelefone);
        painelPessoal.add(txtTelefone);
        painelPessoal.add(lblFuncao);
        painelPessoal.add(txtFuncao);
        painelPessoal.add(btnDeletar);
        
        //Adicionando botão e campo de texto aos respectivos vetores.
        botoes[6]      = btnDeletar;
        campoDeTexto[0]= txtLogin;
        
        getRootPane().setDefaultButton(btnDeletar); //Deixa o botão Deletar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes,itensDeMenu, campoDeTexto, usuarioLogado);
        btnDeletar.addActionListener(eventos);
        txtLogin.addFocusListener(eventos);
    }
    
    public Usuario getUsuario(){
        
        Usuario usuario = new Usuario(0,0,"","","","","");
        
        usuario.setLogin(txtLogin.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setSenha(pswSenha.getText());
        usuario.setFuncao(txtFuncao.getText());
        return usuario;
    }
    
    public void recebeUsuario(Usuario usuario){//Recebe usuário após consulta no banco para exibição dos dados do usuário que se deseja excluir.
        txtLogin.setText(usuario.getLogin());
        txtEmail.setText(usuario.getEmail());
        pswSenha.setText(usuario.getSenha());
        txtTelefone.setText(usuario.getTelefone());
        txtFuncao.setText(usuario.getFuncao());
        
        
    }
    
    public void limparCampos(){
        txtLogin.setText(null);
        txtEmail.setText(null);
        pswSenha.setText(null);
        txtTelefone.setText(null);
        txtFuncao.setText(null);
    }
    
    public static DeletarUsuario getInstance(JButton botoes[],JMenuItem intensDeMenu[], Usuario usuarioLogado, JTextField[] camposDeTexto){
        if(instance == null){
            return instance = new DeletarUsuario(botoes,intensDeMenu, usuarioLogado, camposDeTexto);
        }
        return instance;
    }
}
