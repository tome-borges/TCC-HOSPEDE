package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReservaDAO {

    private PreparedStatement statement;
    private ResultSet         resultSet;
    private ConectarBanco     banco;
    private Date              data_entradaSQL, data_saidaSQL;
    public static ReservaDAO  instance;

    //Ações para reservas do tipo 'RESERVAR"
    public boolean cadastrarReserva(Reserva reserva, Cliente cliente, Quarto quarto) {
        boolean reservou = false;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO reserva (id_quarto, id_cliente, tipo, entrada, saida) VALUES (?,?,?,?,?)");

            
            statement.setInt(1, quarto.getId_quarto());
            statement.setInt(2, reserva.getId_cliente());
            statement.setString(3, reserva.getTipo());
            statement.setDate(4,  data_entradaSQL = new Date(reserva.getEntrada().getTime()));
            statement.setDate(5, data_saidaSQL = new Date(reserva.getSaida().getTime()));

            statement.execute();
            JOptionPane.showMessageDialog(null, "Reserva cadastrada com sucesso!");
            reservou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao reservar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservou;
    }

    //----------------------------------------------------------------------------------------------------------------
    public Reserva consultarReserva(int id_cliente) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        Reserva reserva = new Reserva();

        try {
            statement = banco.setStatement("SELECT * FROM reserva WHERE id_cliente = ? AND tipo = 'RESERVAR'");
            statement.setInt(1, id_cliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setEntrada(resultSet.getDate(5));
                reserva.setSaida(resultSet.getDate(6));
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar reservar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reserva;
    }

    //----------------------------------------------------------------------------------------------------------------
    public boolean deletarReserva(Reserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try {
            statement = banco.setStatement("DELETE FROM reserva WHERE id = ? ");
            statement.setInt(1, reserva.getId_reserva());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Reserva deletada com sucesso!");
            deletou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar reservar:\n" + erro.getMessage());
        }

        banco.desconectar();
       return true;
    }

    //----------------------------------------------------------------------------------------------------------------
    public boolean editarReserva(Reserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE reserva SET entrada=?, saida=?, tipo = ? WHERE id = ? AND tipo = 'RESERVAR'");

            
            statement.setDate(1, data_entradaSQL = new Date(reserva.getEntrada().getTime()));
            statement.setDate(2, data_saidaSQL = new Date(reserva.getSaida().getTime()));
            statement.setString(3, reserva.getTipo());
            statement.setInt(4, reserva.getId_reserva());
            

            statement.execute();
            JOptionPane.showMessageDialog(null, "Reserva editada com sucesso!");
            editou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar reservar:\n" + erro.getMessage());
        }

        banco.desconectar();
         return editou;
    }

    //---------------------------------------------------------------------------------------------------------------
    public ArrayList<Reserva> listarReservas() {
        ArrayList<Reserva> reservaLista = new ArrayList<Reserva>();
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT\n"
                    + "c.nome,\n"
                    + "c.estado,\n"
                    + "c.telefone,\n"
                    + "c.celular,\n"
                    + "c.email,\n"
                    + "q.numero,\n"
                    + "q.id,\n"
                    + "r.id,\n"
                    + "c.id,\n"
                    + "r.tipo,\n"
                    + "r.entrada,\n"
                    + "r.saida\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON c.id = r.id_cliente\n"
                    + "\n"
                    + "WHERE r.tipo = 'RESERVAR' ORDER BY q.numero");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setNome_cliente(resultSet.getString(1));
                reserva.setEstado_cliente(resultSet.getString(2));
                reserva.setTelefone_cliente(resultSet.getString(3));
                reserva.setCelular_cliente(resultSet.getString(4));
                reserva.setEmail_cliente(resultSet.getString(5));
                reserva.setNumero_quarto(resultSet.getString(6));
                reserva.setId_quarto(resultSet.getInt(7));
                reserva.setId_reserva(resultSet.getInt(8));
                reserva.setId_cliente(resultSet.getInt(9));
                reserva.setTipo(resultSet.getString(10));
                reserva.setEntrada(resultSet.getDate(11));
                reserva.setSaida(resultSet.getDate(12));

                reservaLista.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar reservas:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservaLista;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------
    public Reserva pesquisarReserva(Cliente cliente){
        Reserva reserva = new Reserva();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT\n"
                    + "c.nome,\n"
                    + "c.estado,\n"
                    + "c.telefone,\n"
                    + "c.celular,\n"
                    + "c.email,\n"
                    + "q.numero,\n"
                    + "r.tipo,\n"
                    + "r.entrada,\n"
                    + "r.saida\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON c.id = r.id_cliente\n"
                    + "\n"
                    + "WHERE r.tipo = 'RESERVAR' AND r.id_cliente = ?");
            statement.setInt(1, cliente.getId_cliente());
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                reserva.setNome_cliente(resultSet.getString(1));
                reserva.setEstado_cliente(resultSet.getString(2));
                reserva.setTelefone_cliente(resultSet.getString(3));
                reserva.setCelular_cliente(resultSet.getString(4));
                reserva.setEmail_cliente(resultSet.getString(5));
                reserva.setNumero_quarto(resultSet.getString(6));
                reserva.setTipo(resultSet.getString(7));
                reserva.setEntrada(resultSet.getDate(8));
                reserva.setSaida(resultSet.getDate(9));
            }
            
        }catch(Exception erro){
        
    }
        
        banco.desconectar();
        return reserva;
    }
    
    //---------------------------------------------------------//------------------------------------------------------//------------------------------------------------------------
    //Ações para reserva do tipo "HOSPEDAR"
    public boolean cadastrarHospedagem(Reserva reserva, Cliente cliente, Quarto quarto) {
        boolean reservou = false;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO reserva (id_quarto, id_cliente, tipo, entrada, saida) VALUES (?,?,?,?,?)");

            
            statement.setInt(1, quarto.getId_quarto());
            statement.setInt(2, reserva.getId_cliente());
            statement.setString(3, reserva.getTipo());
            statement.setDate(4, data_entradaSQL = new Date(reserva.getEntrada().getTime()));
            statement.setDate(5, data_saidaSQL = new Date(reserva.getSaida().getTime()));

            statement.execute();
            JOptionPane.showMessageDialog(null, "Hospedagem cadastrada com sucesso!");
            reservou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao hospedar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservou;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    public Reserva consultarHospedagem(int id_cliente) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        Reserva reserva = new Reserva();

        try {
            statement = banco.setStatement("SELECT * FROM reserva WHERE id_cliente = ? AND tipo = 'HOSPEDAR'");
            statement.setInt(1, id_cliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setEntrada(resultSet.getDate(5));
                reserva.setSaida(resultSet.getDate(6));
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reserva;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean deletarHospedagem(Reserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;
        try {
            statement = banco.setStatement("DELETE FROM reserva WHERE id = ? ");
            statement.setInt(1, reserva.getId_reserva());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Hospedagem deletada com sucesso!");
            deletou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return deletou;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean editarHospedagem(Reserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE reserva SET"
                    + " entrada=?, saida=? WHERE id = ? AND tipo = 'HOSPEDAR'");

            statement.setDate(1, data_entradaSQL = new Date(reserva.getEntrada().getTime()));
            statement.setDate(2, data_saidaSQL = new Date(reserva.getSaida().getTime()));
            statement.setInt(3, reserva.getId_reserva());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Hospedagem editada com sucesso!");
            editou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return editou;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<Reserva> listarHospedagem() {
        ArrayList<Reserva> reservaLista = new ArrayList<Reserva>();
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT\n"
                    + "c.nome,\n"
                    + "c.estado,\n"
                    + "c.telefone,\n"
                    + "c.celular,\n"
                    + "c.email,\n"
                    + "q.numero,\n"
                    + "q.id,\n"
                    + "c.id,\n"
                    + "r.id,\n"
                    + "r.tipo,\n"
                    + "r.entrada,\n"
                    + "r.saida\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON c.id = r.id_cliente\n"
                    + "\n"
                    + "WHERE r.tipo = 'HOSPEDAR' ORDER BY q.numero");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setNome_cliente(resultSet.getString(1));
                reserva.setEstado_cliente(resultSet.getString(2));
                reserva.setTelefone_cliente(resultSet.getString(3));
                reserva.setCelular_cliente(resultSet.getString(4));
                reserva.setEmail_cliente(resultSet.getString(5));
                reserva.setNumero_quarto(resultSet.getString(6));
                reserva.setId_quarto(resultSet.getInt(7));
                reserva.setId_cliente(resultSet.getInt(8));
                reserva.setId_reserva(resultSet.getInt(9));
                reserva.setTipo(resultSet.getString(10));
                reserva.setEntrada(resultSet.getDate(11));
                reserva.setSaida(resultSet.getDate(12));

                reservaLista.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar reservas:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservaLista;
    }
    //--------------------------------------------------------------------------------------------------------------------
    public Reserva pesquisarHospedagem(Cliente cliente){
        Reserva reserva = new Reserva();
        banco.conectar();
        try{
            statement = banco.setStatement("SELECT\n"
                    + "c.nome,\n"
                    + "c.estado,\n"
                    + "c.telefone,\n"
                    + "c.celular,\n"
                    + "c.email,\n"
                    + "q.numero,\n"
                    + "r.tipo,\n"
                    + "r.entrada,\n"
                    + "r.saida\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id_quarto = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON c.id_cliente = r.id_cliente\n"
                    + "\n"
                    + "WHERE r.tipo = 'HOSPEDAR' AND r.id_cliente = ?");
            statement.setInt(1, cliente.getId_cliente());
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                reserva.setNome_cliente(resultSet.getString(1));
                reserva.setEstado_cliente(resultSet.getString(2));
                reserva.setTelefone_cliente(resultSet.getString(3));
                reserva.setCelular_cliente(resultSet.getString(4));
                reserva.setEmail_cliente(resultSet.getString(5));
                reserva.setNumero_quarto(resultSet.getString(6));
                reserva.setTipo(resultSet.getString(7));
                reserva.setEntrada(resultSet.getDate(8));
                reserva.setSaida(resultSet.getDate(9));
            }
            
        }catch(Exception erro){
        
    }
        
        banco.desconectar();
        return reserva;
    }
    
    //-----------------------------------------------------------------------------------------------------------------

    public static ReservaDAO getInstance() {
        if (instance == null) {
            instance = new ReservaDAO();
        }
        return instance;
    }
}
