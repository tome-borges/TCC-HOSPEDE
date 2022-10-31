package br.com.hospede.view;

import br.com.hospede.control.ControlarEventos;
import br.com.hospede.control.InterfaceFactory;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloReceitaDiaria;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ReceitaDiaria extends InterfaceFactory{
    
    public static ReceitaDiaria instance;
    private ModeloReceitaDiaria modeloReceita;
    private JTable tabela;
    private JScrollPane scroll;
    private JDateChooser  data;
    private JLabel        lblData;
    private ControlarEventos eventos;
    
    public ReceitaDiaria(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas){
        setTitle("Receita Diaria");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(125, 20, 1147, 700);
        setLayout(null);
        setResizable(false);
        
        //Tabela.
        modeloReceita = ModeloReceitaDiaria.getInstance();
        tabela = new JTable(modeloReceita);
        tabela.getColumnModel().getColumn(1).setMaxWidth(60);
        scroll = new JScrollPane(tabela);
        scroll.setSize(1080, 500);
        scroll.setLocation(30, 120);
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));
        tabela.setRowHeight(30);
        
        data = new JDateChooser();
        data.setSize(130, 30);
        data.setLocation(30, 50);
        
        
        lblData = new JLabel("Selecione um dia:");
        lblData.setSize(200, 20);
        lblData.setLocation(30, 30);
        
        add(scroll);
        add(data);
        add(lblData);
        
        modelosTabelas[7] = data;
        //Associando eventos.
        eventos = ControlarEventos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        data.addFocusListener(eventos);
        
    }
    
    public String getDia(){
        return data.getDateFormatString();
    }
    
    
    public static ReceitaDiaria getInstance(JButton botoes[], JMenuItem itensDeMenu[], JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        if (instance == null) {
            return instance = new ReceitaDiaria(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }
}
