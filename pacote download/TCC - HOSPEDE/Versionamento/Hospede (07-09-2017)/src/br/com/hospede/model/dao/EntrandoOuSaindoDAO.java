package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.EntrandoOuSaindo;
import br.com.hospede.model.connection.ConectarBanco;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EntrandoOuSaindoDAO {

    private ConectarBanco             banco;
    private PreparedStatement         statement;
    private ResultSet                 resultSet;
    private Date                      dataAtual = new Date(); //Pega a data atual.
    private String                    data;
    private SimpleDateFormat          formatarData = new SimpleDateFormat("dd/MM/yyyy");
    public static EntrandoOuSaindoDAO instance = null;

    public ArrayList<EntrandoOuSaindo> exibirClienteEntrandoHoje() {
        ArrayList<EntrandoOuSaindo> lista = new ArrayList<EntrandoOuSaindo>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try {

            //Transformando a data atual em string.
            data = formatarData.format(dataAtual);

            statement = banco.setStatement("SELECT \n"
                    + "c.nome,\n"
                    + "q.numero,\n"
                    + "r.entrada,\n"
                    + "r.saida,\n"
                    + "c.celular\n"
                    + "\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON r.id_cliente = c.id\n"
                    + "WHERE r.tipo = 'RESERVAR' AND r.entrada = '"+data+"'");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EntrandoOuSaindo entrandoOuSaindo = new EntrandoOuSaindo();
                entrandoOuSaindo.setNomeDoCliente(resultSet.getString(1));
                entrandoOuSaindo.setNumeroDoQuarto(resultSet.getString(2));
                entrandoOuSaindo.setEntrada(resultSet.getDate(3));
                entrandoOuSaindo.setSaida(resultSet.getDate(4));
                entrandoOuSaindo.setCelular(resultSet.getString(5));

                //Adicionando imgagem referente as reservas entrando hoje.
                try{
                URL resourceEntrando = getClass().getResource("/br/com/hospede/view/imagens/EntrandoHoje.png");
                ImageIcon imgEntrando = new ImageIcon(resourceEntrando);
                entrandoOuSaindo.setImgSituacao(imgEntrando);
                
                }catch(Exception errp){
                    
                }

                lista.add(entrandoOuSaindo);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes entrando hoje:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public ArrayList<EntrandoOuSaindo> exibirClienteSaindoHoje() {
        ArrayList<EntrandoOuSaindo> lista = new ArrayList<EntrandoOuSaindo>();
        banco = ConectarBanco.getInstance();
        banco.conectar();
        try {

            //Transformando a data atual em string.
            data = formatarData.format(dataAtual);

            statement = banco.setStatement("SELECT \n"
                    + "c.nome,\n"
                    + "q.numero,\n"
                    + "r.entrada,\n"
                    + "r.saida,\n"
                    + "c.celular\n"
                    + "\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON r.id_cliente = c.id\n"
                    + "WHERE r.tipo = 'HOSPEDAR' AND r.saida = '"+data+"'");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EntrandoOuSaindo entrandoOuSaindo = new EntrandoOuSaindo();
                entrandoOuSaindo.setNomeDoCliente(resultSet.getString(1));
                entrandoOuSaindo.setNumeroDoQuarto(resultSet.getString(2));
                entrandoOuSaindo.setEntrada(resultSet.getDate(3));
                entrandoOuSaindo.setSaida(resultSet.getDate(4));
                entrandoOuSaindo.setCelular(resultSet.getString(5));

                //Adicionando imgagem referente as reservas saindo hoje.
                try{
                URL resourceSaindo = getClass().getResource("/br/com/hospede/view/imagens/SaindoHoje.png");
                ImageIcon imgSaindo = new ImageIcon(resourceSaindo);
                entrandoOuSaindo.setImgSituacao(imgSaindo);
                    
                }catch(Exception erro){
                    
                }
                

                lista.add(entrandoOuSaindo);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes saindo hoje:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    
   //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static EntrandoOuSaindoDAO getInstance() {
        if (instance == null) {
            instance = new EntrandoOuSaindoDAO();
        }
        return instance;
    }
}
