package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOUsuario;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;


public class EditarProduto extends InterfaceFactory{
     private JTextField    txtNome, txtQuantidade;
    private JFormattedTextField txtPreco;
    private JDateChooser  dataEntrada;
    private JLabel        lblNome, lblQuantidade, lblPreco;
    private JButton       btnSalvar;
    private JPanel        painel;
    private static JMenuItem[]     itensDeMenu = new JMenuItem[ControlarEventos.QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    private JButton[]              botoes        = new JButton[ControlarEventos.QUANTIDADE_POSICOES_VETOR_BOTOES];
    private JTextField[]           campoDeTexto = new JTextField[ControlarEventos.QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static EditarProduto instance = null;
    private ControlarEventos       eventos;
    private NumberFormatter formatter;
    
    public EditarProduto(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Editar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(375, 110, 515, 435);
        setLayout(null);
        setResizable(false);
        
         //Inicializando objetos
        painel = new JPanel();
        painel.setSize(500, 400);
        painel.setLocation(5, 5);
        painel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        painel.setLayout(null);
        
        lblNome = new JLabel("Nome");
        lblNome.setSize(50, 20);
        lblNome.setLocation(60, 60);
        
        txtNome = new JTextField();
        txtNome.setSize(250, 30);
        txtNome.setLocation(100, 60);
        txtNome.setEditable(false);
        
        lblQuantidade = new JLabel("Quantidade");
        lblQuantidade.setSize(70, 20);
        lblQuantidade.setLocation(25, 110);
        
        txtQuantidade = new JTextField();
        txtQuantidade.setSize(60, 30);
        txtQuantidade.setLocation(100, 105);
        
        lblPreco = new JLabel("Preço R$");
        lblPreco.setSize(100, 20);
        lblPreco.setLocation(40, 160);
        
         try {

            DecimalFormat dFormat = new DecimalFormat("#######.00");
            formatter = new NumberFormatter(dFormat);
            formatter.setFormat(dFormat);
            formatter.setAllowsInvalid(false);

            txtPreco = new JFormattedTextField(formatter);
            txtPreco.setFormatterFactory(new DefaultFormatterFactory(formatter));
            txtPreco.setSize(100, 30);
            txtPreco.setLocation(100, 160);

        } catch (Exception erro) {

        }
       
        
        
        btnSalvar = new JButton("Salvar");
        btnSalvar.setSize(100, 30);
        btnSalvar.setLocation(50, 350);
        
        //Adicionando componentes no painel.
        add(painel);
        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblQuantidade);
        painel.add(txtQuantidade);
        painel.add(lblPreco);
        painel.add(txtPreco);
        painel.add(btnSalvar);
        
        
        
        botoes[48] = btnSalvar;
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        
        btnSalvar.addActionListener(eventos);
    }
    
    public DTOProduto getProduto(){
        DTOProduto produto = new DTOProduto();
        
        produto.setNome(txtNome.getText());
        produto.setPreco(Double.parseDouble(txtPreco.getText().replace(',', '.')));
        produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        try{
            
        }catch(Exception erro){
            
        }
        
        return produto;
    }
    
    public void receberProduto(DTOProduto produto){
        txtNome.setText(produto.getNome());
        
        try{
              txtPreco.setText(formatter.valueToString(produto.getPreco()));
              txtQuantidade.setText(Integer.toString(produto.getQuantidade()));
        }catch(Exception erro){
           
        }
       
        
    }
    public void limparCampos(){
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        
    }
    
    public static EditarProduto getInstance(JButton botoes[],JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        if(instance == null){
            instance = new EditarProduto(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
