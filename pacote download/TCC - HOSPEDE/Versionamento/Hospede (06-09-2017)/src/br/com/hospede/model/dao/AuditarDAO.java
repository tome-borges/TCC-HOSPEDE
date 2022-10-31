package br.com.hospede.model.dao;

import br.com.hospede.model.DTO.AuditarCliente;
import br.com.hospede.model.DTO.AuditarHospedagem;
import br.com.hospede.model.DTO.AuditarQuarto;
import br.com.hospede.model.DTO.ManterReserva;
import br.com.hospede.model.DTO.AuditarUsuario;
import br.com.hospede.model.DTO.ManterCliente;
import br.com.hospede.model.DTO.ManterQuarto;
import br.com.hospede.model.DTO.ManterUsuario;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class AuditarDAO {

    private PreparedStatement  statement;
    private ResultSet          resultSet;
    private ConectarBanco      banco;
    public static AuditarDAO   instance;
    private java.sql.Timestamp dt_acaoSQL;
    

    public void salvarManterQuarto(ManterQuarto manterQuarto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_quarto (id_usuario, id_quarto, acao, numero_quarto, dt_acao) VALUES (?,?,?,?,?)");

            statement.setInt(1, manterQuarto.getId_usuario());
            statement.setInt(2, manterQuarto.getId_quarto());
            statement.setString(3, manterQuarto.getAcao());
            statement.setInt(4, manterQuarto.getNumero_quarto());
            statement.setTimestamp(5, dt_acaoSQL = new java.sql.Timestamp( new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita no quarto:\n" + erro.getMessage());
        }

        banco.desconectar();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterCliente(ManterCliente manterCliente) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_cliente (id_usuario, id_cliente, acao, nome_cliente, dt_acao) VALUES (?,?,?,?,?)");

            statement.setInt(1, manterCliente.getId_usuario());
            statement.setInt(2, manterCliente.getId_cliente());
            statement.setString(3, manterCliente.getAcao());
            statement.setString(4, manterCliente.getNome_cliente());
            statement.setTimestamp(5, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita no cliente:\n" + erro.getMessage());
        }

        banco.desconectar();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterUsuario(ManterUsuario manterUsuario) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_usuario (id_usuario_responsavel, id_usuario_alterado, acao, login_usuario_alterado, dt_acao) VALUES  (?,?,?,?,?)");

            statement.setInt(1, manterUsuario.getId_usuario_reponsavel());
            statement.setInt(2, manterUsuario.getId_suario_alterado());
            statement.setString(3, manterUsuario.getAcao());
            statement.setString(4, manterUsuario.getUsuario_alterado());
            statement.setTimestamp(5, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita no usuário:\n" + erro.getMessage());
        }
        banco.desconectar();

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterReserva(ManterReserva manterReserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_reserva (id_usuario, login, acao, tipo_reserva, id_reserva, cliente_reservado, dt_acao ) VALUES  (?,?,?,?,?,?,?)");

            statement.setInt(1, manterReserva.getId_usuario_responsavel());
            statement.setString(2, manterReserva.getLogin());
            statement.setString(3, manterReserva.getAcao());
            statement.setString(4, manterReserva.getTipo_reserva());
            statement.setInt(5, manterReserva.getId_reserva());
            statement.setString(6, manterReserva.getCliente_reservado());
            statement.setTimestamp(7, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na reserva:\n" + erro.getMessage());
        }
        banco.desconectar();
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterHospedagem(AuditarHospedagem auditarHospedagem) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_reserva (id_usuario, login, acao, dt_acao, tipo_reserva, id_reserva, cliente_reservado ) VALUES  (?,?,?,?,?,?,?)");

            statement.setInt(1, auditarHospedagem.getId_usuario_responsavel());
            statement.setString(2, auditarHospedagem.getLogin());
            statement.setString(3, auditarHospedagem.getAcao());
            statement.setTimestamp(4, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));
            statement.setString(5, auditarHospedagem.getTipo_reserva());
            statement.setInt(6, auditarHospedagem.getId_hospedagem());
            statement.setString(7, auditarHospedagem.getCliente_hospedado());

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na hospedagem:\n" + erro.getMessage());
        }
        banco.desconectar();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<AuditarQuarto> auditarQuarto() {
        ArrayList<AuditarQuarto> lista = new ArrayList<>();
        AuditarQuarto auditarQuarto;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "u.id,\n"
                    + "u.login,\n"
                    + "hq.acao,\n"
                    + "hq.numero_quarto,\n"
                    + "hq.dt_acao\n"
                    + "\n"
                    + "FROM\n"
                    + "public.usuario u\n"
                    + "LEFT JOIN public.historico_quarto hq ON u.id = hq.id_usuario\n"
                    + "LEFT JOIN public.quarto q ON hq.id_quarto = q.id\n"
                    + "WHERE hq.dt_acao IS NOT null\n"
                    + "ORDER BY hq.dt_acao ");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                auditarQuarto = new AuditarQuarto();
                auditarQuarto.setId_usuario(resultSet.getInt(1));
                auditarQuarto.setLogin(resultSet.getString(2));
                auditarQuarto.setAcao(resultSet.getString(3));
                auditarQuarto.setNumero(resultSet.getInt(4));
                auditarQuarto.setDt_acao(resultSet.getTimestamp(5));

                lista.add(auditarQuarto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<AuditarCliente> auditarCliente() {
        ArrayList<AuditarCliente> lista = new ArrayList<>();
        AuditarCliente auditarCliente;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "u.id,\n"
                    + "u.login,\n"
                    + "hc.acao,\n"
                    + "hc.nome_cliente,\n"
                    + "hc.dt_acao\n"
                    + "\n"
                    + "FROM\n"
                    + "public.usuario u\n"
                    + "LEFT JOIN public.historico_cliente hc ON u.id = hc.id_usuario\n"
                    + "LEFT JOIN public.cliente c ON hc.id_cliente = c.id\n"
                    + "WHERE hc.dt_acao IS NOT null\n"
                    + "ORDER BY hc.dt_acao");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                auditarCliente = new AuditarCliente();
                auditarCliente.setId_usuario(resultSet.getInt(1));
                auditarCliente.setLogin(resultSet.getString(2));
                auditarCliente.setAcao(resultSet.getString(3));
                auditarCliente.setNome_cliente(resultSet.getString(4));
                auditarCliente.setDt_acao(resultSet.getTimestamp(5));

                lista.add(auditarCliente);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar cliente:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<AuditarUsuario> auditarUsuario() {
        ArrayList<AuditarUsuario> lista = new ArrayList<>();
        AuditarUsuario auditarUsuario;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT\n"
                    + "ur.id,\n"
                    + "ur.login,\n"
                    + "hu.acao,\n"
                    + "hu.login_usuario_alterado,\n"
                    + "hu.dt_acao\n"
                    + "\n"
                    + "FROM \n"
                    + "\n"
                    + "public.usuario ur  --Usuário responsável por alterar.\n"
                    + "\n"
                    + "LEFT JOIN public.historico_usuario hu ON ur.id = hu.id_usuario_responsavel\n"
                    + "LEFT JOIN public.usuario ul ON hu.id_usuario_alterado = ul.id --Usuário alterado por outro usuário\n"
                    + "WHERE hu.dt_acao is not null\n"
                    + "ORDER BY hu.dt_acao");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                auditarUsuario = new AuditarUsuario();
                auditarUsuario.setId_usuario_responsavel(resultSet.getInt(1));
                auditarUsuario.setLogin_usuario_responsavel(resultSet.getString(2));
                auditarUsuario.setAcao(resultSet.getString(3));
                auditarUsuario.setLogin_usuario_alterado(resultSet.getString(4));
                auditarUsuario.setDt_acao(resultSet.getTimestamp(5));

                lista.add(auditarUsuario);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar usuário:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    public ArrayList<ManterReserva> auditarReserva() {
        ArrayList<ManterReserva> lista = new ArrayList<>();
        ManterReserva manterReserva;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "u.id,\n"
                    + "u.login,\n"
                    + "hr.acao,\n"
                    + "hr.id_reserva,\n"
                    + "hr.dt_acao,\n"
                    + "hr.cliente_reservado\n"
                    + "\n"
                    + "FROM\n"
                    + "public.usuario u\n"
                    + "LEFT JOIN public.historico_reserva hr ON u.id = hr.id_usuario\n"
                    + "WHERE hr.dt_acao IS NOT null AND hr.tipo_reserva = 'RESERVAR'");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manterReserva = new ManterReserva();
                manterReserva.setId_usuario_responsavel(resultSet.getInt(1));
                manterReserva.setLogin(resultSet.getString(2));
                manterReserva.setAcao(resultSet.getString(3));
                manterReserva.setId_reserva(resultSet.getInt(4));
                manterReserva.setDt_acao(resultSet.getTimestamp(5));
                manterReserva.setCliente_reservado(resultSet.getString(6));

                lista.add(manterReserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar reserva:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<AuditarHospedagem> auditarHospedagem() {
        ArrayList<AuditarHospedagem> lista = new ArrayList<>();
        AuditarHospedagem auditarHospedagem;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "u.id,\n"
                    + "u.login,\n"
                    + "hr.acao,\n"
                    + "hr.id_reserva,\n"
                    + "hr.dt_acao,\n"
                    + "hr.cliente_reservado"
                    + "\n"
                    + "FROM\n"
                    + "public.usuario u\n"
                    + "LEFT JOIN public.historico_reserva hr ON u.id = hr.id_usuario\n"
                    + "WHERE hr.dt_acao IS NOT null AND hr.tipo_reserva = 'HOSPEDAR'");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                auditarHospedagem = new AuditarHospedagem();
                auditarHospedagem.setId_usuario_responsavel(resultSet.getInt(1));
                auditarHospedagem.setLogin(resultSet.getString(2));
                auditarHospedagem.setAcao(resultSet.getString(3));
                auditarHospedagem.setId_hospedagem(resultSet.getInt(4));
                auditarHospedagem.setDt_acao(resultSet.getTimestamp(5));
                auditarHospedagem.setCliente_hospedado(resultSet.getString(6));

                lista.add(auditarHospedagem);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    public static AuditarDAO getInstance() {
        if (instance == null) {
            instance = new AuditarDAO();
        }
        return instance;
    }
}
