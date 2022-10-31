package br.com.hospede.view;

import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.connection.ConectarBanco;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RelatorioServicos extends InterfaceFactory{
    private JPanel                  painelUnidade;
    private JLabel                  imgReserva, imgHospedagem, imgLivre, imgManutencao, imgOrganizando, lblReserva, lblHospedagem, lblLivre, lblManutencao,
                                    lblOrganizando,lblTotalQuartos, lblCabecalho, lblTotalQuarto;
    private JTextField              txtReserva, txtHospedagem, txtLivre, txtManutencao, txtOrganizando, txtTotalQuarto;
    public static RelatorioServicos instance = null;

    public RelatorioServicos(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado) {
        setTitle("Relatório de Serviços");
        setBounds(350, 100, 630, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        //Iniciando paineis.
        painelUnidade = new JPanel();
        painelUnidade.setBorder(BorderFactory.createLineBorder(Color.black));
        painelUnidade.setLayout(null);
        painelUnidade.setSize(550, 410);
        painelUnidade.setLocation(30, 30);

        //Iniciando imagens.
        Icon reserva = new ImageIcon(getClass().getResource("imagens/Reservado.png"));
        Icon hospedagem = new ImageIcon(getClass().getResource("imagens/Hospedado.png"));
        Icon manutencao = new ImageIcon(getClass().getResource("imagens/Manutencao.png"));
        Icon livre = new ImageIcon(getClass().getResource("imagens/Livre.png"));
        Icon organizando = new ImageIcon(getClass().getResource("imagens/Organizando.png"));

        //Iniciando labels
        lblCabecalho = new JLabel("Estatística por serviços: ");
        lblCabecalho.setSize(500, 50);
        lblCabecalho.setLocation(250, 5);

        imgReserva = new JLabel(reserva);
        imgReserva.setSize(90, 40);
        imgReserva.setLocation(10, 60);

        lblReserva = new JLabel(" reserva(s) realizada(s).");
        lblReserva.setSize(200, 40);
        lblReserva.setLocation(150, 60);

        imgHospedagem = new JLabel(hospedagem);
        imgHospedagem.setSize(90, 40);
        imgHospedagem.setLocation(10, 120);

        lblHospedagem = new JLabel(" hospedagem(s) efetuada(s).");
        lblHospedagem.setSize(200, 40);
        lblHospedagem.setLocation(150, 120);

        imgManutencao = new JLabel(manutencao);
        imgManutencao.setSize(90, 40);
        imgManutencao.setLocation(10, 180);

        lblManutencao = new JLabel(" quarto(s) em manutenção.");
        lblManutencao.setSize(200, 40);
        lblManutencao.setLocation(150, 180);

        imgLivre = new JLabel(livre);
        imgLivre.setSize(90, 40);
        imgLivre.setLocation(10, 240);

        lblLivre = new JLabel(" quarto(s) livre(s).");
        lblLivre.setSize(200, 40);
        lblLivre.setLocation(150, 240);
        
        imgOrganizando = new JLabel(organizando);
        imgOrganizando.setSize(90, 40);
        imgOrganizando.setLocation(10, 310);
        
        lblOrganizando = new JLabel("Quarto(s) em organização.");
        lblOrganizando.setSize(200, 40);
        lblOrganizando.setLocation(150, 310);
        
        //Inicaindo caixas de textos.
        txtReserva = new JTextField();
        txtReserva.setSize(50, 20);
        txtReserva.setLocation(90, 72);
        txtReserva.setEditable(false);

        txtHospedagem = new JTextField();
        txtHospedagem.setSize(50, 20);
        txtHospedagem.setLocation(90, 130);
        txtHospedagem.setEditable(false);

        txtManutencao = new JTextField();
        txtManutencao.setSize(50, 20);
        txtManutencao.setLocation(90, 190);
        txtManutencao.setEditable(false);

        txtLivre = new JTextField();
        txtLivre.setSize(50, 20);
        txtLivre.setLocation(90, 250);
        txtLivre.setEditable(false);
        
        txtOrganizando = new JTextField();
        txtOrganizando.setSize(50, 20);
        txtOrganizando.setLocation(90, 320);
        txtOrganizando.setEditable(false);
        
        lblTotalQuarto = new JLabel("Total de quartos:");
        lblTotalQuarto.setSize(110, 20);
        lblTotalQuarto.setLocation(40, 360);
        
        txtTotalQuarto = new JTextField();
        txtTotalQuarto.setSize(50, 20);
        txtTotalQuarto.setLocation(150, 360);
        txtTotalQuarto.setEditable(false);
        
        //Adicioando componentes.
        add(painelUnidade);
        painelUnidade.add(lblCabecalho);
        painelUnidade.add(imgReserva);
        painelUnidade.add(imgHospedagem);
        painelUnidade.add(imgManutencao);
        painelUnidade.add(imgLivre);
        painelUnidade.add(lblReserva);
        painelUnidade.add(lblHospedagem);
        painelUnidade.add(lblManutencao);
        painelUnidade.add(lblLivre);
        painelUnidade.add(lblOrganizando);
        painelUnidade.add(imgOrganizando);
        painelUnidade.add(txtReserva);
        painelUnidade.add(txtHospedagem);
        painelUnidade.add(txtManutencao);
        painelUnidade.add(txtLivre);
        painelUnidade.add(txtOrganizando);
        painelUnidade.add(lblTotalQuarto);
        painelUnidade.add(txtTotalQuarto);
        

    }

    public void receberQuantias(int[] totais){//Recebe a quantia de cada serviço, após uma busca no banco de dados.
        
        txtReserva.setText(Integer.toString(totais[0]));
        txtHospedagem.setText(Integer.toString(totais[1]));
        txtLivre.setText(Integer.toString(totais[2]));
        txtManutencao.setText(Integer.toString(totais[3]));
        txtOrganizando.setText(Integer.toString(totais[4]));
        
        //Soma todos os quartos.
        int totalQuarto = totais[0] + totais[1] +totais[2] + totais[3] +totais[4];
        txtTotalQuarto.setText(Integer.toString(totalQuarto));
    }
   
    public static RelatorioServicos getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], Usuario usuarioLogado) {
        if (instance == null) {
            return instance = new RelatorioServicos(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }
}
