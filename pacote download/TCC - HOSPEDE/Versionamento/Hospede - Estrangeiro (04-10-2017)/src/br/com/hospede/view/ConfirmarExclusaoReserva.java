package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOUsuario;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class ConfirmarExclusaoReserva extends InterfaceFactory{
    
    private JButton btnCancelar, btnConfirmar;
    private JLabel lblAviso;
    public static ConfirmarExclusaoReserva instance;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    private ControlarEventos eventos;
    
    public ConfirmarExclusaoReserva(JButton botoes[], JMenuItem itensDeMenu[], JTextField campoDeTexto[], DTOUsuario usuarioLogado){
        
        setTitle("Confirmar Exclusão");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(470, 350, 360, 140);
        setLayout(null);
        setResizable(false);

        this.itensDeMenu = itensDeMenu;
        this.botoes = botoes;
        this.campoDeTexto = campoDeTexto;
        
        lblAviso = new JLabel("Tem certeza que deseja deletar a reserva?");
        lblAviso.setSize(500, 30);
        lblAviso.setLocation(60, 20);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setSize(100, 30);
        btnCancelar.setLocation(40, 60);
        
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setSize(100, 30);
        btnConfirmar.setLocation(200, 60);
        
        add(lblAviso);
        add(btnCancelar);
        add(btnConfirmar);
        
        botoes[84] = btnConfirmar;
        botoes[85] = btnCancelar;
        getRootPane().setDefaultButton(btnConfirmar); //Deixa o botão Reservar em foco para o KeyListener.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, campoDeTexto, usuarioLogado);
        btnConfirmar.addActionListener(eventos);
        btnCancelar.addActionListener(eventos);
    }
    
    public static ConfirmarExclusaoReserva getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField campoDeTexto[], DTOUsuario usuarioLogado){
        if(instance == null){
            instance = new ConfirmarExclusaoReserva(botoes,itensDeMenu, campoDeTexto, usuarioLogado);
        }
        return instance;
    }
}
