package br.com.hospede.model.DAO;

import br.com.hospede.model.DTO.DTOReceitaDiaria;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReservaDAO {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private ConectarBanco banco;
    public static ReservaDAO instance;
    private java.sql.Timestamp data_entradaSQL, data_saidaSQL, data_entrada_hospedagemSQL;

    //Ações para reservas do tipo 'RESERVAR"
    public boolean cadastrarReserva(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto) {
        boolean reservou = false;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO reserva (id_quarto, id_cliente, tipo, entrada, saida, status) VALUES (?,?,?,?,?,?)");

            statement.setInt(1, quarto.getId_quarto());
            statement.setInt(2, reserva.getId_cliente());
            statement.setString(3, reserva.getTipo());
            statement.setTimestamp(4, data_entradaSQL = new Timestamp(reserva.getEntrada().getTime()));
            statement.setTimestamp(5, data_saidaSQL = new Timestamp(reserva.getSaida().getTime()));
            statement.setString(6, "ABERTA");

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
    public DTOReserva consultarReserva(int id_cliente) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOReserva reserva = new DTOReserva();

        try {
            statement = banco.setStatement("SELECT * FROM reserva WHERE id_cliente = ? AND tipo = 'RESERVAR' AND status = 'ABERTA'");
            statement.setInt(1, id_cliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setStatus(resultSet.getString(5));
                reserva.setEntrada(resultSet.getTimestamp(6));
                reserva.setSaida(resultSet.getTimestamp(7));
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar reservar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reserva;
    }

    //----------------------------------------------------------------------------------------------------------------
    public boolean deletarReserva(DTOReserva reserva) {
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
    public boolean editarReserva(DTOReserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE reserva SET entrada=?, saida=?, tipo = ?, status = ? WHERE id = ? AND tipo = 'RESERVAR'");

            statement.setTimestamp(1, data_entradaSQL = new Timestamp(reserva.getEntrada().getTime()));
            statement.setTimestamp(2, data_saidaSQL = new Timestamp(reserva.getSaida().getTime()));
            statement.setString(3, reserva.getTipo());
            statement.setString(4, reserva.getStatus());
            statement.setInt(5, reserva.getId_reserva());

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
    public ArrayList<DTOReserva> listarReservas() {
        ArrayList<DTOReserva> reservaLista = new ArrayList<DTOReserva>();
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
                    + "WHERE r.tipo = 'RESERVAR' AND r.status = 'ABERTA' ORDER BY q.numero");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DTOReserva reserva = new DTOReserva();
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
                reserva.setEntrada(resultSet.getTimestamp(11));
                reserva.setSaida(resultSet.getTimestamp(12));

                reservaLista.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar reservas:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservaLista;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------
    public DTOReserva pesquisarReserva(DTOCliente cliente) {
        DTOReserva reserva = new DTOReserva();
        banco.conectar();
        try {
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
                reserva.setEntrada(resultSet.getTimestamp(8));
                reserva.setSaida(resultSet.getTimestamp(9));
            }

        } catch (Exception erro) {

        }

        banco.desconectar();
        return reserva;
    }

    //---------------------------------------------------------//------------------------------------------------------//------------------------------------------------------------
    //Ações para reserva do tipo "HOSPEDAR"
    public boolean cadastrarHospedagem(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto) {
        boolean reservou = false;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO reserva (id_quarto, id_cliente, tipo, entrada, saida, status) VALUES (?,?,?,?,?,?)");

            statement.setInt(1, quarto.getId_quarto());
            statement.setInt(2, reserva.getId_cliente());
            statement.setString(3, reserva.getTipo());
            statement.setTimestamp(4, data_entrada_hospedagemSQL = new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.setTimestamp(5, data_saidaSQL = new Timestamp(reserva.getSaida().getTime()));
            statement.setString(6, "ABERTA");

            statement.execute();
            JOptionPane.showMessageDialog(null, "Hospedagem cadastrada com sucesso!");
            reservou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao hospedar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservou;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    public boolean cadastrarHospedagemReservada(DTOReserva reserva, DTOCliente cliente, DTOQuarto quarto) {
        boolean reservou = false;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO reserva (id_quarto, id_cliente, tipo, entrada, saida, status) VALUES (?,?,?,?,?,?)");

            statement.setInt(1, quarto.getId_quarto());
            statement.setInt(2, reserva.getId_cliente());
            statement.setString(3, reserva.getTipo());
            statement.setTimestamp(4, data_entrada_hospedagemSQL = new java.sql.Timestamp(new java.util.Date().getTime()));
            statement.setTimestamp(5, data_saidaSQL = new Timestamp(reserva.getSaida().getTime()));
            statement.setString(6, "ABERTA");

            statement.execute();
            reservou = true;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao hospedar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservou;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------
    public DTOReserva consultarHospedagem(int id_cliente) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOReserva reserva = new DTOReserva();

        try {
            statement = banco.setStatement("SELECT * FROM reserva WHERE id_cliente = ? AND tipo = 'HOSPEDAR' AND status = 'ABERTA'");
            statement.setInt(1, id_cliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setStatus(resultSet.getString(5));
                reserva.setEntrada(resultSet.getTimestamp(7));
                reserva.setSaida(resultSet.getTimestamp(8));
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reserva;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------
    public DTOReserva consultarHospedagemIdQuarto(int id_quarto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOReserva reserva = new DTOReserva();

        try {
            statement = banco.setStatement("SELECT * FROM reserva WHERE id_quarto = ? AND tipo = 'HOSPEDAR' AND status = 'ABERTA'");
            statement.setInt(1, id_quarto);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setStatus(resultSet.getString(5));
                reserva.setEntrada(resultSet.getTimestamp(6));
                reserva.setSaida(resultSet.getTimestamp(7));
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reserva;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean deletarHospedagem(DTOReserva reserva) {
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
    public boolean editarHospedagem(DTOReserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;
        try {
            statement = banco.setStatement("UPDATE reserva SET"
                    + " entrada=?, saida=?, status = ?, saldo = ? WHERE id = ? AND tipo = 'HOSPEDAR'");

            statement.setTimestamp(1, data_entradaSQL = new Timestamp(reserva.getEntrada().getTime()));
            statement.setTimestamp(2, data_saidaSQL = new Timestamp(reserva.getSaida().getTime()));
            statement.setString(3, reserva.getStatus());
            statement.setDouble(4, reserva.getSaldo());
            statement.setInt(5, reserva.getId_reserva());

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
    public ArrayList<DTOReserva> listarHospedagem() {
        ArrayList<DTOReserva> reservaLista = new ArrayList<DTOReserva>();
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
                    + "WHERE r.tipo = 'HOSPEDAR' AND r.status = 'ABERTA' ORDER BY q.numero");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DTOReserva reserva = new DTOReserva();
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
                reserva.setEntrada(resultSet.getTimestamp(11));
                reserva.setSaida(resultSet.getTimestamp(12));
                

                reservaLista.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar reservas:\n" + erro.getMessage());
        }

        banco.desconectar();
        return reservaLista;
    }

    //--------------------------------------------------------------------------------------------------------------------
    public DTOReserva pesquisarHospedagem(DTOCliente cliente) {
        DTOReserva reserva = new DTOReserva();
        banco.conectar();
        try {
            statement = banco.setStatement("SELECT\n"
                    + "c.nome,\n"
                    + "c.estado,\n"
                    + "c.telefone,\n"
                    + "c.celular,\n"
                    + "c.email,\n"
                    + "q.numero,\n"
                    + "r.tipo,\n"
                    + "r.entrada,\n"
                    + "r.saida,\n"
                    + "r.id\n"
                    + "FROM\n"
                    + "public.quarto q\n"
                    + "LEFT JOIN public.reserva r ON q.id = r.id_quarto\n"
                    + "LEFT JOIN public.cliente c ON c.id = r.id_cliente\n"
                    + "\n"
                    + "WHERE r.tipo = 'HOSPEDAR' AND r.id_cliente = ? AND r.status = 'ABERTA'");
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
                reserva.setEntrada(resultSet.getTimestamp(8));
                reserva.setSaida(resultSet.getTimestamp(9));
                reserva.setId_reserva(resultSet.getInt(10));
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());

        }

        banco.desconectar();
        return reserva;
    }

    //-----------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOReceitaDiaria> getReceitaDiaria() {
        ArrayList<DTOReceitaDiaria> reservas = new ArrayList<DTOReceitaDiaria>();

        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "T1.ID,\n"
                    + "T3.NUMERO,\n"
                    + "T2.NOME,\n"
                    + "T1.ENTRADA,\n"
                    + "T1.SAIDA,\n"
                    + "T1.STATUS,\n"
                    + "T1.SALDO\n"
                    + "\n"
                    + "FROM reserva T1\n"
                    + "INNER JOIN cliente T2 ON T1.ID_CLIENTE = T2.ID\n"
                    + "INNER JOIN quarto T3  ON T1.ID_QUARTO = T3.ID\n"
                    + "WHERE T1.STATUS IN ('ABERTA','FECHADA') AND TIPO ='HOSPEDAR' ORDER BY T1.ENTRADA");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DTOReceitaDiaria reserva = new DTOReceitaDiaria();
                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setNumeroQuarto(resultSet.getString(2));
                reserva.setCliente(resultSet.getString(3));
                reserva.setEntrada(resultSet.getTimestamp(4));
                reserva.setSaida(resultSet.getTimestamp(5));
                reserva.setStatus(resultSet.getString(6));
                reserva.setSaldo(resultSet.getDouble(7));

                reservas.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar reservas:\n" + erro.getMessage());
        }

        banco.desconectar();

        return reservas;
    }

    //-----------------------------------------------------------------------------------------------------------------
    public static ReservaDAO getInstance() {
        if (instance == null) {
            instance = new ReservaDAO();
        }
        return instance;
    }
}
