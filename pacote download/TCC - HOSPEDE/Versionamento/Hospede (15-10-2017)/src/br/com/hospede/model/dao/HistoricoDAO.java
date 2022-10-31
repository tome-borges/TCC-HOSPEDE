package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOHistoricoCliente;
import br.com.hospede.model.dto.DTOHistoricoPasseio;
import br.com.hospede.model.dto.DTOHistoricoProduto;
import br.com.hospede.model.dto.DTOHistoricoUsuario;
import br.com.hospede.model.dto.DTOHistoricoQuarto;
import br.com.hospede.model.dto.DTOHistoricoReserva;
import br.com.hospede.model.connection.ConectarBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class HistoricoDAO {

    private PreparedStatement  statement;
    private ResultSet          resultSet;
    private ConectarBanco      banco;
    public static HistoricoDAO instance;
    private java.sql.Timestamp dt_acaoSQL;

    public void salvarManterQuarto(DTOHistoricoQuarto manterQuarto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_quarto (id_usuario, id_quarto, acao, numero_quarto, dt_acao) VALUES (?,?,?,?,?)");

            statement.setInt(1, manterQuarto.getId_usuario());
            statement.setInt(2, manterQuarto.getId_quarto());
            statement.setString(3, manterQuarto.getAcao());
            statement.setInt(4, manterQuarto.getNumero_quarto());
            statement.setTimestamp(5, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita no quarto:\n" + erro.getMessage());
        }

        banco.desconectar();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterCliente(DTOHistoricoCliente manterCliente) {
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
    public void salvarManterUsuario(DTOHistoricoUsuario manterUsuario) {
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
    public void salvarManterReserva(DTOHistoricoReserva historicoReserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_reserva (id_usuario, login, acao, tipo_reserva, id_reserva, cliente_reservado, dt_acao ) VALUES  (?,?,?,?,?,?,?)");

            statement.setInt(1, historicoReserva.getId_usuario_responsavel());
            statement.setString(2, historicoReserva.getLogin());
            statement.setString(3, historicoReserva.getAcao());
            statement.setString(4, historicoReserva.getTipo_reserva());
            statement.setInt(5, historicoReserva.getId_reserva());
            statement.setString(6, historicoReserva.getCliente_reservado());
            statement.setTimestamp(7, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na reserva:\n" + erro.getMessage());
        }
        banco.desconectar();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void salvarManterHospedagem(DTOHistoricoReserva HistoricoReserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_reserva (id_usuario, login, acao, dt_acao, tipo_reserva, id_reserva, cliente_reservado ) VALUES  (?,?,?,?,?,?,?)");

            statement.setInt(1, HistoricoReserva.getId_usuario_responsavel());
            statement.setString(2, HistoricoReserva.getLogin());
            statement.setString(3, HistoricoReserva.getAcao());
            statement.setTimestamp(4, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));
            statement.setString(5, HistoricoReserva.getTipo_reserva());
            statement.setInt(6, HistoricoReserva.getId_reserva());
            statement.setString(7, HistoricoReserva.getCliente_reservado());

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na hospedagem:\n" + erro.getMessage());
        }
        banco.desconectar();
    }

    //----------------------------------------------------------------------------------------------------------------
    public void salvarManterPasseio(DTOHistoricoPasseio historicoPasseio) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_passeio (id_passeio, id_cliente,id_usuario_responsavel_por_registrar, dt_acao, acao, passeio ) VALUES  (?,?,?,?,?,?)");

            statement.setInt(1, historicoPasseio.getId_passeio());
            statement.setInt(2, historicoPasseio.getId_cliente());
            statement.setInt(3, historicoPasseio.getId_usuario_responsavel());
            statement.setTimestamp(4, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));
            statement.setString(5, historicoPasseio.getAcao());
            statement.setString(6, historicoPasseio.getPasseio());

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na passeio:\n" + erro.getMessage());
        }
        banco.desconectar();
    }

    //----------------------------------------------------------------------------------------------------------------------
    public void salvarManterProduto(DTOHistoricoProduto historicoProduto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("INSERT INTO historico_produto (id_produto,id_usuario_responsavel, dt_acao, acao, produto ) VALUES  (?,?,?,?,?)");

            statement.setInt(1, historicoProduto.getId_produto());
            statement.setInt(2, historicoProduto.getId_usuario_responsavel());
            statement.setTimestamp(3, dt_acaoSQL = new java.sql.Timestamp(new Date().getTime()));
            statement.setString(4, historicoProduto.getAcao());
            statement.setString(5, historicoProduto.getProduto());

            statement.execute();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar ação feita na produto:\n" + erro.getMessage());
        }
        banco.desconectar();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoQuarto> auditarQuarto() {
        ArrayList<DTOHistoricoQuarto> lista = new ArrayList<>();
        DTOHistoricoQuarto HistoricoQuarto;
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
                HistoricoQuarto = new DTOHistoricoQuarto();
                HistoricoQuarto.setId_usuario(resultSet.getInt(1));
                HistoricoQuarto.setLogin(resultSet.getString(2));
                HistoricoQuarto.setAcao(resultSet.getString(3));
                HistoricoQuarto.setNumero_quarto(resultSet.getInt(4));
                HistoricoQuarto.setDt_acao(resultSet.getTimestamp(5));

                lista.add(HistoricoQuarto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }
    //------------------------------------------------------------------------------------------------------------------------------
     public ArrayList<DTOHistoricoQuarto> auditarQuartoUsuario(int id_usuario) {
        ArrayList<DTOHistoricoQuarto> lista = new ArrayList<>();
        DTOHistoricoQuarto HistoricoQuarto;
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
                    + "WHERE hq.dt_acao IS NOT null and u.id = ?\n"
                    + "ORDER BY hq.dt_acao ");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HistoricoQuarto = new DTOHistoricoQuarto();
                HistoricoQuarto.setId_usuario(resultSet.getInt(1));
                HistoricoQuarto.setLogin(resultSet.getString(2));
                HistoricoQuarto.setAcao(resultSet.getString(3));
                HistoricoQuarto.setNumero_quarto(resultSet.getInt(4));
                HistoricoQuarto.setDt_acao(resultSet.getTimestamp(5));

                lista.add(HistoricoQuarto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoCliente> auditarCliente() {
        ArrayList<DTOHistoricoCliente> lista = new ArrayList<>();
        DTOHistoricoCliente historicoCliente;
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
                historicoCliente = new DTOHistoricoCliente();
                historicoCliente.setId_usuario(resultSet.getInt(1));
                historicoCliente.setLogin(resultSet.getString(2));
                historicoCliente.setAcao(resultSet.getString(3));
                historicoCliente.setNome_cliente(resultSet.getString(4));
                historicoCliente.setDt_acao(resultSet.getTimestamp(5));

                lista.add(historicoCliente);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar cliente:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    //---------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoCliente> auditarClienteUsuario(int id_usuario) {
        ArrayList<DTOHistoricoCliente> lista = new ArrayList<>();
        DTOHistoricoCliente historicoCliente;
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
                    + "WHERE hc.dt_acao IS NOT null and u.id = ?\n"
                    + "ORDER BY hc.dt_acao");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historicoCliente = new DTOHistoricoCliente();
                historicoCliente.setId_usuario(resultSet.getInt(1));
                historicoCliente.setLogin(resultSet.getString(2));
                historicoCliente.setAcao(resultSet.getString(3));
                historicoCliente.setNome_cliente(resultSet.getString(4));
                historicoCliente.setDt_acao(resultSet.getTimestamp(5));

                lista.add(historicoCliente);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar cliente:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoUsuario> auditarUsuario() {
        ArrayList<DTOHistoricoUsuario> lista = new ArrayList<>();
        DTOHistoricoUsuario historicoUsuario;
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
                historicoUsuario = new DTOHistoricoUsuario();
                historicoUsuario.setId_usuario_reponsavel(resultSet.getInt(1));
                historicoUsuario.setLogin_usuario_responsavel(resultSet.getString(2));
                historicoUsuario.setAcao(resultSet.getString(3));
                historicoUsuario.setLogin_usuario_alterado(resultSet.getString(4));
                historicoUsuario.setDt_acao(resultSet.getTimestamp(5));

                lista.add(historicoUsuario);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar usuário:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    //---------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoUsuario> auditarUsuarioLogin(int  id_usuario) {
        ArrayList<DTOHistoricoUsuario> lista = new ArrayList<>();
        DTOHistoricoUsuario historicoUsuario;
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
                    + "WHERE hu.dt_acao is not null and ur.id = ?\n"
                    + "ORDER BY hu.dt_acao");

           
            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historicoUsuario = new DTOHistoricoUsuario();
                historicoUsuario.setId_usuario_reponsavel(resultSet.getInt(1));
                historicoUsuario.setLogin_usuario_responsavel(resultSet.getString(2));
                historicoUsuario.setAcao(resultSet.getString(3));
                historicoUsuario.setLogin_usuario_alterado(resultSet.getString(4));
                historicoUsuario.setDt_acao(resultSet.getTimestamp(5));

                lista.add(historicoUsuario);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar usuário por data:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoReserva> auditarReserva() {
        ArrayList<DTOHistoricoReserva> lista = new ArrayList<>();
        DTOHistoricoReserva historicoReserva;
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
                historicoReserva = new DTOHistoricoReserva();
                historicoReserva.setId_usuario_responsavel(resultSet.getInt(1));
                historicoReserva.setLogin(resultSet.getString(2));
                historicoReserva.setAcao(resultSet.getString(3));
                historicoReserva.setId_reserva(resultSet.getInt(4));
                historicoReserva.setDt_acao(resultSet.getTimestamp(5));
                historicoReserva.setCliente_reservado(resultSet.getString(6));

                lista.add(historicoReserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar reserva:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoReserva> auditarReservaUsuario(int id_usuario) {
        ArrayList<DTOHistoricoReserva> lista = new ArrayList<>();
        DTOHistoricoReserva historicoReserva;
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
                    + "WHERE hr.dt_acao IS NOT null AND hr.tipo_reserva = 'RESERVAR' and u.id = ?");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historicoReserva = new DTOHistoricoReserva();
                historicoReserva.setId_usuario_responsavel(resultSet.getInt(1));
                historicoReserva.setLogin(resultSet.getString(2));
                historicoReserva.setAcao(resultSet.getString(3));
                historicoReserva.setId_reserva(resultSet.getInt(4));
                historicoReserva.setDt_acao(resultSet.getTimestamp(5));
                historicoReserva.setCliente_reservado(resultSet.getString(6));

                lista.add(historicoReserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar reserva:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoReserva> auditarHospedagem() {
        ArrayList<DTOHistoricoReserva> lista = new ArrayList<>();
        DTOHistoricoReserva historicoReserva;
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
                historicoReserva = new DTOHistoricoReserva();
                historicoReserva.setId_usuario_responsavel(resultSet.getInt(1));
                historicoReserva.setLogin(resultSet.getString(2));
                historicoReserva.setAcao(resultSet.getString(3));
                historicoReserva.setId_reserva(resultSet.getInt(4));
                historicoReserva.setDt_acao(resultSet.getTimestamp(5));
                historicoReserva.setCliente_reservado(resultSet.getString(6));

                lista.add(historicoReserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
    //------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoReserva> auditarHospedagemUsuario(int id_usuario) {
        ArrayList<DTOHistoricoReserva> lista = new ArrayList<>();
        DTOHistoricoReserva historicoReserva;
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
                    + "WHERE hr.dt_acao IS NOT null AND hr.tipo_reserva = 'HOSPEDAR' AND u.id = ?");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                historicoReserva = new DTOHistoricoReserva();
                historicoReserva.setId_usuario_responsavel(resultSet.getInt(1));
                historicoReserva.setLogin(resultSet.getString(2));
                historicoReserva.setAcao(resultSet.getString(3));
                historicoReserva.setId_reserva(resultSet.getInt(4));
                historicoReserva.setDt_acao(resultSet.getTimestamp(5));
                historicoReserva.setCliente_reservado(resultSet.getString(6));

                lista.add(historicoReserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar hospedagem:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }
   //---------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoPasseio> auditarPasseio() {
        ArrayList<DTOHistoricoPasseio> lista = new ArrayList<>();
        DTOHistoricoPasseio HistoricoPasseio;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "T2.login,\n"
                    + "T1.acao,\n"
                    + "T1.passeio,\n"
                    + "T1.dt_acao\n"
                    + "\n"
                    + "FROM historico_passeio T1\n"
                    + "INNER JOIN usuario T2 on t1.id_usuario_responsavel_por_registrar = t2.id\n"
                    + "\n"
                    + "ORDER BY t1.dt_acao");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HistoricoPasseio = new DTOHistoricoPasseio();
                HistoricoPasseio.setUsuarioResponsavel(resultSet.getString(1));
                HistoricoPasseio.setAcao(resultSet.getString(2));
                HistoricoPasseio.setPasseio(resultSet.getString(3));
                HistoricoPasseio.setData_registro(resultSet.getTimestamp(4));
                

                lista.add(HistoricoPasseio);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar passeio:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }
    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoPasseio> auditarPasseioUsuario(int id_usuario) {
        ArrayList<DTOHistoricoPasseio> lista = new ArrayList<>();
        DTOHistoricoPasseio HistoricoPasseio;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "T2.login,\n"
                    + "T1.acao,\n"
                    + "T1.passeio,\n"
                    + "T1.dt_acao\n"
                    + "\n"
                    + "FROM historico_passeio T1\n"
                    + "INNER JOIN usuario T2 on t1.id_usuario_responsavel_por_registrar = t2.id\n"
                    + "WHERE t2.id = ?\n"
                    + "ORDER BY t1.dt_acao");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HistoricoPasseio = new DTOHistoricoPasseio();
                HistoricoPasseio.setUsuarioResponsavel(resultSet.getString(1));
                HistoricoPasseio.setAcao(resultSet.getString(2));
                HistoricoPasseio.setPasseio(resultSet.getString(3));
                HistoricoPasseio.setData_registro(resultSet.getTimestamp(4));
                

                lista.add(HistoricoPasseio);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar passeio:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }
     //-------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoProduto> auditarProduto() {
        ArrayList<DTOHistoricoProduto> lista = new ArrayList<>();
        DTOHistoricoProduto historicoProduto;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "T2.login,\n"
                    + "T1.acao,\n"
                    + "T1.produto,\n"
                    + "T1.dt_acao\n"
                    + "\n"
                    + "FROM historico_produto T1\n"
                    + "INNER JOIN usuario T2 on t1.id_usuario_responsavel = t2.id\n"
                    +"\n"
                    + "ORDER BY t1.dt_acao");

            
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                historicoProduto = new DTOHistoricoProduto();
                historicoProduto.setUsuarioResponsavel(resultSet.getString(1));
                historicoProduto.setAcao(resultSet.getString(2));
                historicoProduto.setProduto(resultSet.getString(3));
                historicoProduto.setData_Acao(resultSet.getTimestamp(4));

                lista.add(historicoProduto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar produto:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }
    //-------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOHistoricoProduto> auditarProdutoUsuario(int id_usuario) {
        ArrayList<DTOHistoricoProduto> lista = new ArrayList<>();
        DTOHistoricoProduto historicoProduto;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "T2.login,\n"
                    + "T1.acao,\n"
                    + "T1.produto,\n"
                    + "t1.data_Acao\n"
                    + "\n"
                    + "FROM historico_produto T1\n"
                    + "INNER JOIN usuario T2 on t1.id_usuario_responsavel = t2.id\n"
                    + "WHERE t2.id = ?\n"
                    + "ORDER BY t1.data_Acao");

            statement.setInt(1, id_usuario);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                historicoProduto = new DTOHistoricoProduto();
                historicoProduto.setUsuarioResponsavel(resultSet.getString(1));
                historicoProduto.setAcao(resultSet.getString(2));
                historicoProduto.setProduto(resultSet.getString(3));
                historicoProduto.setData_Acao(resultSet.getTimestamp(4));

                lista.add(historicoProduto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao auditar produto:\n" + erro.getMessage());
        }

        banco.desconectar();

        return lista;
    }

    public static HistoricoDAO getInstance() {
        if (instance == null) {
            instance = new HistoricoDAO();
        }
        return instance;
    }
}
