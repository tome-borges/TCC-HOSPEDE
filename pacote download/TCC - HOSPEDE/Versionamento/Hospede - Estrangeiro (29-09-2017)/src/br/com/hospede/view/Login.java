package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.model.dto.DTOUsuario;
import com.birosoft.liquid.util.Colors;
import java.awt.Color;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Login extends JFrame{
    
    //Declarando objetos.
    private JPanel           painelBorda;
    private JLabel           lblLogo, lblLogin, lblSenha, lblImagem;
    protected JTextField     txtLogin;
    private JPasswordField   pswSenha;
    private JButton          btnEntrar;
    private DTOUsuario          usuario;
    private JButton[]        botoes;
    private JMenuItem[]      itensDeMenu   = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JTextField[]     camposDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    public static  Login     instance = null;
    
     public static void main(String[] args){
                      try {
                      
                  try {
                      UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
                  } catch (InstantiationException ex) {
                      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IllegalAccessException ex) {
                      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (UnsupportedLookAndFeelException ex) {
                      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                  }
           

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    
 
        instance  = Login.getInstance();
        instance.setVisible(true);
        

    }
    
    //Construtor.
    public Login() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 595, 395);
        setLayout(null);

        //Iniciando painel com borda.
        painelBorda = new JPanel();
        painelBorda.setSize(565, 340);
        painelBorda.setLocation(10, 10);
        painelBorda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painelBorda.setLayout(null);
        setResizable(false);
        SwingUtilities.updateComponentTreeUI(this); 
        setBackground(Colors.dark(Color.red, ERROR));

        //Inicializando imagem.
        URL resourceLogo = getClass().getResource("/br/com/hospede/view/imagens/Logo.jpg");
        ImageIcon imgLogo = new ImageIcon(resourceLogo);
        

        //Iniciando labels.
        lblLogo = new JLabel(imgLogo);
        lblLogo.setSize(200, 80);
        lblLogo.setLocation(200, 5);
        
        ImageIcon imgLogin = new ImageIcon(getClass().getResource("imagens/Login.jpg"));
        lblImagem = new JLabel(imgLogin);
        lblImagem.setSize(150, 150);
        lblImagem.setLocation(20, 100);
        
        lblLogin = new JLabel("Login:");
        lblLogin.setSize(100, 30);
        lblLogin.setLocation(180, 105);
        
        lblSenha = new JLabel("Senha:");
        lblSenha.setSize(100, 30);
        lblSenha.setLocation(173, 155);

        //Iniciando caixas de textos.
        txtLogin = new JTextField();
        txtLogin.setSize(250, 30);
        txtLogin.setLocation(250, 105);
        

        pswSenha = new JPasswordField();
        pswSenha.setSize(250, 30);
        pswSenha.setLocation(250, 155);


        //Iniciando botões.
        
        ImageIcon imgLivre = new ImageIcon(getClass().getResource("imagens/Next.png"));
        
        btnEntrar = new JButton(imgLivre);
        btnEntrar.setSize(100, 70);
        btnEntrar.setLocation(400, 200);

        //Adicionando componentes.
        add(painelBorda);
        painelBorda.add(lblLogo);
        painelBorda.add(lblImagem);
        painelBorda.add(lblLogin);
        painelBorda.add(lblSenha);
        painelBorda.add(txtLogin);
        painelBorda.add(pswSenha);
        painelBorda.add(btnEntrar);
      
        
        //Adicionando botões, em um vetor, para enviar a classe que controla os eventos.
        botoes = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
        botoes[0] = btnEntrar;
        
        
        getRootPane().setDefaultButton(btnEntrar); //Deixa o botão Entrar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuario);
        btnEntrar.addActionListener(eventos);

        
    }
    
    public DTOUsuario getUsuario(){
        usuario = new DTOUsuario(0, 0,"Fabricio", "Fabr1cio","", "", "");
        return usuario;
        
    }

    public static Login getInstance(){
        if(instance == null){
            instance = new Login();
        }
        return instance;
    }

}
