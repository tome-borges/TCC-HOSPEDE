package br.com.hospede.model.DAO;

import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.connection.ConectarBanco;
import br.com.hospede.model.modeloTabela.ModeloEntrandoHoje;
import br.com.hospede.model.modeloTabela.ModeloSaindoHoje;
import br.com.hospede.view.Login;
import br.com.hospede.view.TelaPrincipal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class QuartoDAO {

    private PreparedStatement statement;
    private ResultSet resultSet;
    private ConectarBanco banco;
    public static QuartoDAO instance;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private java.util.Date dataAtual = new java.util.Date(); //Pega a data atual.
    private SimpleDateFormat formatarDataPesquisa = new SimpleDateFormat("YYYY-MM-dd");
    private SimpleDateFormat formatarDataHora = new SimpleDateFormat("YYYY-MM-dd HH:mm");

    public boolean cadastrarQuarto(DTOQuarto quarto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean cadastrou = false;

        try {
            statement = banco.setStatement("INSERT INTO quarto (numero, situacao, ocupantes, diaria, categoria)"
                    + "VALUES (?, ?, ?, ?, ?)");

            statement.setInt(1, Integer.parseInt(quarto.getNumero()));
            statement.setString(2, quarto.getSituacao());
            statement.setInt(3, Integer.parseInt(quarto.getOcupantes()));
            statement.setDouble(4, Double.parseDouble(quarto.getDiaria()));
            statement.setString(5, quarto.getCategoria());

            statement.execute();
            JOptionPane.showMessageDialog(null, "Quarto cadastrado com sucesso!");
            cadastrou = true;// Para salvar a ação feita na tabela "manter_quarto" que controla as ações do usuario nos quartos.

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();
        return cadastrou;

    }

    //--------------------------------------------------------------------------------------------------------------------------
    public DTOQuarto consultarQuarto(int numero) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOQuarto Quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);

        try {
            statement = banco.setStatement("SELECT\n"
                    + "        distinct(q.numero),\n"
                    + "        q.situacao,\n"
                    + "        q.categoria,\n"
                    + "        c.nome,\n"
                    + "        q.diaria,\n"
                    + "        r.entrada,\n"
                    + "        r.saida,\n"
                    + "        q.id,\n"
                    + "        q.ocupantes\n"
                    + "        \n"
                    + "        FROM\n"
                    + "        public.quarto q \n"
                    + "        LEFT JOIN (SELECT * FROM reserva WHERE (to_char(entrada,'YYYY-MM-dd') = '" + formatarDataPesquisa.format(dataAtual) + "' AND status='ABERTA') OR\n"
                    + "                                               (TIPO = 'HOSPEDAR' AND '" + formatarDataPesquisa.format(dataAtual) + "' BETWEEN to_char(entrada,'YYYY-MM-dd') AND to_char(saida,'YYYY-MM-dd') AND status='ABERTA'))r ON q.id = r.id_quarto\n"
                    + "         LEFT JOIN cliente c on r.id_cliente = c.id\n"
                    + "                                \n"
                    + "         WHERE numero = ?       \n"
                    + "         ORDER BY numero");

            statement.setInt(1, numero);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                //Informação do quarto, proveniente do left join nas tabelas.
                Quarto.setNumero(resultSet.getString(1));
                Quarto.setSituacao(resultSet.getString(2));
                Quarto.setCategoria(resultSet.getString(3));
                Quarto.setNome_cliente(resultSet.getString(4));
                Quarto.setDiaria(resultSet.getString(5));
                Quarto.setEntrada(resultSet.getDate(6));
                Quarto.setSaida(resultSet.getDate(7));
                Quarto.setId_quarto(resultSet.getInt(8));
                Quarto.setOcupantes(Integer.toString(resultSet.getInt(9)));

                //Adicionando objeto imagem, conforme a situação do quarto.
                try {

                    if (Quarto.getSituacao().equals("RESERVADO")) {
                        URL resourceReservado = getClass().getResource("/br/com/hospede/view/imagens/Reservado.png");
                        ImageIcon imgReservado = new ImageIcon(resourceReservado);
                        Quarto.setIconeDeSituacao(imgReservado);

                    } else if (Quarto.getSituacao().equals("HOSPEDADO")) {
                        URL resourceHospedado = getClass().getResource("/br/com/hospede/view/imagens/Hospedado.png");
                        ImageIcon imgHospedado = new ImageIcon(resourceHospedado);
                        Quarto.setIconeDeSituacao(imgHospedado);

                    } else if (Quarto.getSituacao().equals("MANUTENCAO")) {
                        URL resourceManutencao = getClass().getResource("/br/com/hospede/view/imagens/Manutencao.png");
                        ImageIcon imgManutencao = new ImageIcon(resourceManutencao);
                        Quarto.setIconeDeSituacao(imgManutencao);

                    } else if (Quarto.getSituacao().equals("ORGANIZANDO")) {
                        URL resourceOrganizando = getClass().getResource("/br/com/hospede/view/imagens/Organizando.png");
                        ImageIcon imgOrganizando = new ImageIcon(resourceOrganizando);
                        Quarto.setIconeDeSituacao(imgOrganizando);

                    } else {
                        URL resourceLivre = getClass().getResource("/br/com/hospede/view/imagens/Livre.png");
                        ImageIcon imgLivre = new ImageIcon(resourceLivre);
                        Quarto.setIconeDeSituacao(imgLivre);
                    }

                } catch (Exception erro) {
                    URL resourceLivre = getClass().getResource("/br/com/hospede/view/imagens/Livre.png");
                    ImageIcon imgLivre = new ImageIcon(resourceLivre);
                    Quarto.setIconeDeSituacao(imgLivre);
                }

            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listas quartos:\n" + erro.getMessage());
        }

        banco.desconectar();
        return Quarto;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    public boolean editarQuarto(DTOQuarto quarto) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean editou = false;

        try {
            statement = banco.setStatement("UPDATE quarto SET numero=?, situacao=?, ocupantes = ?, diaria = ?, categoria=?"
                    + "WHERE id = ?");
            statement.setInt(1, Integer.parseInt(quarto.getNumero()));
            statement.setString(2, quarto.getSituacao());
            statement.setInt(3, Integer.parseInt(quarto.getOcupantes()));
            statement.setDouble(4, Double.parseDouble(quarto.getDiaria()));
            statement.setString(5, quarto.getCategoria());
            statement.setInt(6, quarto.getId_quarto());

            statement.execute();
            editou = true;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();
        return editou;
    }

    //----------------------------------------------------------------------------------------------------------------------------
    public boolean deletarQuarto(String numero) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        boolean deletou = false;

        try {
            statement = banco.setStatement("DELETE FROM quarto WHERE numero = ?");
            statement.setInt(1, Integer.parseInt(numero));

            statement.execute();

            deletou = true;
            JOptionPane.showMessageDialog(null, "Quarto deletado com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();
        return deletou;
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOQuarto> listarQuarto() {
        ArrayList<DTOQuarto> lista = new ArrayList<>();

        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT\n"
                    + "                   \n"
                    + "        distinct(q.numero),\n"
                    + "        q.situacao,\n"
                    + "        q.categoria,\n"
                    + "        c.nome,\n"
                    + "        q.diaria,\n"
                    + "        r.entrada,\n"
                    + "        r.saida,\n"
                    + "        q.id,\n"
                    + "        q.ocupantes\n"
                    + "        \n"
                    + "        FROM\n"
                    + "        public.quarto q \n"
                    + "        LEFT JOIN (SELECT * FROM reserva WHERE (to_char(entrada,'YYYY-MM-dd') = '" + formatarDataPesquisa.format(dataAtual) + "' AND status='ABERTA') OR\n"
                    + "                                               (TIPO = 'HOSPEDAR' AND '" + formatarDataPesquisa.format(dataAtual) + "' BETWEEN to_char(entrada,'YYYY-MM-dd') AND to_char(saida,'YYYY-MM-dd') AND status='ABERTA'))r ON q.id = r.id_quarto\n"
                    + "         LEFT JOIN cliente c on r.id_cliente = c.id\n"
                    + "                                \n"
                    + "         ORDER BY numero");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                DTOQuarto Quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);
                //Informação do quarto, proveniente do left join nas tabelas.
                Quarto.setSituacao(resultSet.getString(2));
                Quarto.setNumero(resultSet.getString(1));
                Quarto.setCategoria(resultSet.getString(3));
                Quarto.setNome_cliente(resultSet.getString(4));
                Quarto.setDiaria(resultSet.getString(5));
                Quarto.setEntrada(resultSet.getTimestamp(6));
                Quarto.setSaida(resultSet.getTimestamp(7));
                Quarto.setId_quarto(resultSet.getInt(8));
                Quarto.setOcupantes(Integer.toString(resultSet.getInt(9)));

                //Adicionando objeto imagem, conforme a situação do quarto.
                try {

                    if (Quarto.getSituacao().equals("LIVRE")) {
                        URL resourceLivre = getClass().getResource("/br/com/hospede/view/imagens/Livre.png");
                        ImageIcon imgLivre = new ImageIcon(resourceLivre);
                        Quarto.setIconeDeSituacao(imgLivre);

                    } else if (Quarto.getSituacao().equals("RESERVADO")) {
                        URL resourceReservado = getClass().getResource("/br/com/hospede/view/imagens/Reservado.png");
                        ImageIcon imgReservado = new ImageIcon(resourceReservado);
                        Quarto.setIconeDeSituacao(imgReservado);

                    } else if (Quarto.getSituacao().equals("HOSPEDADO")) {
                        URL resourceHospedado = getClass().getResource("/br/com/hospede/view/imagens/Hospedado.png");
                        ImageIcon imgHospedado = new ImageIcon(resourceHospedado);
                        Quarto.setIconeDeSituacao(imgHospedado);

                    } else if (Quarto.getSituacao().equals("MANUTENCAO")) {
                        URL resourceManutencao = getClass().getResource("/br/com/hospede/view/imagens/Manutencao.png");
                        ImageIcon imgManutencao = new ImageIcon(resourceManutencao);
                        Quarto.setIconeDeSituacao(imgManutencao);

                    } else {
                        URL resourceOrganizando = getClass().getResource("/br/com/hospede/view/imagens/Organizando.png");
                        ImageIcon imgOrganizando = new ImageIcon(resourceOrganizando);
                        Quarto.setIconeDeSituacao(imgOrganizando);
                    }

                } catch (Exception erro) {

                }

                lista.add(Quarto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listas quartos:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //--------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<DTOQuarto> listarQuartoSelecionar(Date entrada, Date saida) {
        ArrayList<DTOQuarto> lista = new ArrayList<>();

        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT \n"
                    + "distinct(q.numero),\n"
                    + "           q.situacao,\n"
                    + "           q.categoria,\n"
                    + "           CASE WHEN q.situacao not in ('LIVRE','MANUTENCAO','ORGANIZANDO') THEN c.nome ELSE '' END AS nome,\n"
                    + "           q.diaria,\n"
                    + "           CASE WHEN q.situacao not in ('LIVRE','MANUTENCAO','ORGANIZANDO') THEN r.entrada ELSE null END AS entrada,\n"
                    + "           CASE WHEN q.situacao not in ('LIVRE','MANUTENCAO','ORGANIZANDO') THEN r.saida ELSE null END AS saida,\n"
                    + "           q.id,\n"
                    + "           q.ocupantes\n"
                    + "           \n"
                    + "          FROM public.quarto q \n"
                    + "                           LEFT JOIN (SELECT * FROM reserva WHERE status='ABERTA')r ON q.id = r.id_quarto\n"
                    + "                           LEFT JOIN cliente c on r.id_cliente = c.id\n"
                    + "                           WHERE q.numero not in (SELECT\n"
                    + "                       distinct(q.numero)\n"
                    + "                       FROM\n"
                    + "                           public.quarto q \n"
                    + "                           LEFT JOIN (SELECT * FROM reserva WHERE status='ABERTA')r ON q.id = r.id_quarto\n"
                    + "                           LEFT JOIN cliente c on r.id_cliente = c.id\n"
                    + "                           WHERE STATUS <> 'ORGANIZANDO' AND to_char(r.entrada,'YYYY-MM-dd HH:mm')  BETWEEN '" + formatarDataHora.format(entrada) + "' and '" + formatarDataHora.format(saida) + "' OR\n"
                    + "                           STATUS <> 'ORGANIZANDO' AND  to_char(r.saida,'YYYY-MM-dd HH:mm')   BETWEEN   '" + formatarDataHora.format(entrada) + "' and '" + formatarDataHora.format(saida) + "'\n"
                    + "                           ORDER BY numero) AND q.situacao <> 'ORGANIZANDO'\n"
                    + "                           ORDER BY numero");

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                DTOQuarto Quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);
                //Informação do quarto, proveniente do left join nas tabelas.
                Quarto.setSituacao(resultSet.getString(2));
                Quarto.setNumero(resultSet.getString(1));
                Quarto.setCategoria(resultSet.getString(3));
                Quarto.setNome_cliente(resultSet.getString(4));
                Quarto.setDiaria(resultSet.getString(5));
                Quarto.setEntrada(resultSet.getTimestamp(6));
                Quarto.setSaida(resultSet.getTimestamp(7));
                Quarto.setId_quarto(resultSet.getInt(8));
                Quarto.setOcupantes(Integer.toString(resultSet.getInt(9)));

                //Adicionando objeto imagem, conforme a situação do quarto.
                try {

                    if (Quarto.getSituacao().equals("LIVRE")) {
                        URL resourceLivre = getClass().getResource("/br/com/hospede/view/imagens/Livre.png");
                        ImageIcon imgLivre = new ImageIcon(resourceLivre);
                        Quarto.setIconeDeSituacao(imgLivre);

                    } else if (Quarto.getSituacao().equals("RESERVADO")) {
                        URL resourceReservado = getClass().getResource("/br/com/hospede/view/imagens/Reservado.png");
                        ImageIcon imgReservado = new ImageIcon(resourceReservado);
                        Quarto.setIconeDeSituacao(imgReservado);

                    } else if (Quarto.getSituacao().equals("HOSPEDADO")) {
                        URL resourceHospedado = getClass().getResource("/br/com/hospede/view/imagens/Hospedado.png");
                        ImageIcon imgHospedado = new ImageIcon(resourceHospedado);
                        Quarto.setIconeDeSituacao(imgHospedado);

                    } else if (Quarto.getSituacao().equals("MANUTENCAO")) {
                        URL resourceManutencao = getClass().getResource("/br/com/hospede/view/imagens/Manutencao.png");
                        ImageIcon imgManutencao = new ImageIcon(resourceManutencao);
                        Quarto.setIconeDeSituacao(imgManutencao);

                    } else {
                        URL resourceOrganizando = getClass().getResource("/br/com/hospede/view/imagens/Organizando.png");
                        ImageIcon imgOrganizando = new ImageIcon(resourceOrganizando);
                        Quarto.setIconeDeSituacao(imgOrganizando);
                    }

                } catch (Exception erro) {

                }

                lista.add(Quarto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listas quartos para selecionar:\n" + erro.getMessage());
        }

        banco.desconectar();
        return lista;
    }

    //-------------------------------------------------------------------------------------------------------------------------
    public int[] quantidadeServicosQuartos() {//Totaliza a quantidade de quarto em: reserva, hospadagem, livre, manutenção e organização.
        int[] totais = new int[5];
        int somaReserva = 0, somaHospedagens = 0, somaLivres = 0, somaManutencao = 0, somaOrganizando = 0;

        banco = ConectarBanco.getInstance();
        banco.conectar();

        try {
            statement = banco.setStatement("SELECT * FROM quarto");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(3).equals("RESERVADO")) {
                    somaReserva += 1;

                } else if (resultSet.getString(3).equals("HOSPEDADO")) {
                    somaHospedagens += 1;

                } else if (resultSet.getString(3).equals("LIVRE")) {
                    somaLivres += 1;

                } else if (resultSet.getString(3).equals("MANUTENCAO")) {
                    somaManutencao += 1;

                } else if (resultSet.getString(3).equals("ORGANIZANDO")) {
                    somaOrganizando += 1;
                }
            }

            //Armazena os totais gerais no vetor.    
            totais[0] = somaReserva;
            totais[1] = somaHospedagens;
            totais[2] = somaLivres;
            totais[3] = somaManutencao;
            totais[4] = somaOrganizando;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar a quantidade de quartos reservados:\n" + erro.getMessage());
        }

        banco.desconectar();
        return totais;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public DTOQuarto getQuartoPorID(DTOReserva reserva) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOQuarto quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);

        try {
            statement = banco.setStatement("SELECT * FROM quarto WHERE id = ? ");
            statement.setInt(1, reserva.getId_quarto());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);
                quarto.setId_quarto(resultSet.getInt(1));
                quarto.setNumero(Integer.toString(resultSet.getInt(2)));
                quarto.setSituacao(resultSet.getString(3));
                quarto.setOcupantes(resultSet.getString(4));
                quarto.setDiaria(resultSet.getString(5));
                quarto.setCategoria(resultSet.getString(6));
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro consultar quarto de uma reserva:\n" + erro.getMessage());
        }

        banco.desconectar();
        return quarto;
    }

    //-----------------------------------------------------------------------------------------------
    public DTOQuarto consultarQuartoID(int id) {
        banco = ConectarBanco.getInstance();
        banco.conectar();
        DTOQuarto quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);

        try {
            statement = banco.setStatement("SELECT * FROM quarto WHERE id = ? ");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                quarto = new DTOQuarto(0, "", "", "", "", "", "", null, null);
                quarto.setId_quarto(resultSet.getInt(1));
                quarto.setNumero(Integer.toString(resultSet.getInt(2)));
                quarto.setSituacao(resultSet.getString(3));
                quarto.setOcupantes(resultSet.getString(4));
                quarto.setDiaria(resultSet.getString(5));
                quarto.setCategoria(resultSet.getString(6));
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro consultar quarto de uma reserva:\n" + erro.getMessage());
        }

        banco.desconectar();
        return quarto;
    }
    //--------------------------------------------------------------------------------------------------------------------------------
    public void atualizarQuarto() {
        banco = ConectarBanco.getInstance();
        banco.conectar();

        ArrayList<DTOReserva> reservas = new ArrayList<DTOReserva>();
        DTOReserva reserva;
        //pego as reservas para este quarto
        try {

            statement = banco.setStatement("SELECT * FROM RESERVA \n"
                    + "WHERE STATUS = 'ABERTA' AND\n"
                    + "       (TIPO = 'RESERVAR' AND to_char(ENTRADA,'YYYY-MM-dd') = '" + formatarDataPesquisa.format(dataAtual) + "') OR\n"
                    + "       (STATUS = 'ABERTA' AND TIPO = 'HOSPEDAR' AND '" + formatarDataPesquisa.format(dataAtual) + "' between to_char(entrada,'YYYY-MM-dd') and to_char(saida,'YYYY-MM-dd'))");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                reserva = new DTOReserva();
                reserva.setId_reserva(resultSet.getInt(1));
                reserva.setId_quarto(resultSet.getInt(2));
                reserva.setId_cliente(resultSet.getInt(3));
                reserva.setTipo(resultSet.getString(4));
                reserva.setStatus(resultSet.getString(5));
                reserva.setEntrada(resultSet.getDate(7));
                reserva.setSaida(resultSet.getDate(8));

                reservas.add(reserva);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao editar atualizar:\n" + erro.getMessage());
        }

        //Agora só editar o status do quarto conforme rsultado da busca acima.
        int posicao = 0;

        try {

            while (posicao < reservas.size()) {

                statement = banco.setStatement("UPDATE quarto SET situacao = ? WHERE id = '" + reservas.get(posicao).getId_quarto() + "'");
                if (reservas.get(posicao).getTipo().equals("RESERVAR")) {
                    statement.setString(1, "RESERVADO");

                } else if (reservas.get(posicao).getTipo().equals("HOSPEDAR")) {
                    statement.setString(1, "HOSPEDADO");
                }

                statement.execute();
                posicao++;
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar quarto:\n" + erro.getMessage());
        }

        banco.desconectar();

    }

    public boolean checarDisponibilidadeData(DTOReserva reserva, DTOQuarto quarto) {
        boolean disponivel = true;
        banco = ConectarBanco.getInstance();
        banco.conectar();

        ArrayList<DTOReserva> reservas = new ArrayList<DTOReserva>();
        DTOReserva reservaBanco;
        //Agora pego as reservas para este quarto
        try {
            statement = banco.setStatement("SELECT * FROM RESERVA WHERE ID_QUARTO ='" + quarto.getId_quarto() + "'  AND STATUS = 'ABERTA'");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                reservaBanco = new DTOReserva();
                reservaBanco.setId_reserva(resultSet.getInt(1));
                reservaBanco.setId_quarto(resultSet.getInt(2));
                reservaBanco.setId_cliente(resultSet.getInt(3));
                reservaBanco.setTipo(resultSet.getString(4));
                reservaBanco.setStatus(resultSet.getString(5));
                reservaBanco.setEntrada(resultSet.getTimestamp(7));
                reservaBanco.setSaida(resultSet.getTimestamp(8));

                reservas.add(reservaBanco);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao checar disponibilidade:\n" + erro.getMessage());
        }

        //Verifico se há alguma reserva dentro do tempo selecionado para uma reserva ou hospedagem
        try {
            if (reservas.size() == 0) {
                disponivel = true;
            } else {

                for (int posicao = 0; posicao < reservas.size(); posicao++) {

                    if (reserva.getId_reserva() == reservas.get(posicao).getId_reserva()) {
                        if ((reserva.getEntrada().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() < reservas.get(posicao).getSaida().getTime())
                                || (reserva.getSaida().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() < reservas.get(posicao).getSaida().getTime())
                                && reserva.getEntrada().getTime() < reservas.get(posicao).getEntrada().getTime()
                                || (reserva.getSaida().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() > reservas.get(posicao).getSaida().getTime())
                                && reserva.getEntrada().getTime() < reservas.get(posicao).getEntrada().getTime()) {

                            disponivel = false;
                            throw new Exception();

                        } else {
                            disponivel = true;
                        }

                    } else {

                        if ((reserva.getEntrada().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() < reservas.get(posicao).getSaida().getTime())
                                || (reserva.getSaida().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() < reservas.get(posicao).getSaida().getTime())
                                && reserva.getEntrada().getTime() < reservas.get(posicao).getEntrada().getTime()
                                || (reserva.getSaida().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getSaida().getTime() > reservas.get(posicao).getSaida().getTime())
                                && reserva.getEntrada().getTime() < reservas.get(posicao).getEntrada().getTime()
                                || (reserva.getEntrada().getTime() > reservas.get(posicao).getEntrada().getTime() && reserva.getEntrada().getTime() < reservas.get(posicao).getSaida().getTime())
                                && reserva.getSaida().getTime() > reservas.get(posicao).getSaida().getTime()) {

                            disponivel = false;
                            throw new Exception();

                        } else {
                            disponivel = true;
                        }
                    }
                }

            }
        } catch (Exception erro) {
        }

        return disponivel;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void atualizarRelatorio() {
        //Primeiro a tabela com os clientes entrando
        EntrandoOuSaindoDAO entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
        ModeloEntrandoHoje modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
        modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());

        //Depois, as tabelas com os cliente saindo.
        ModeloSaindoHoje modeloSaindoHoje = ModeloSaindoHoje.getInstance();
        modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());

        //Valores para os serviços
        //Busca no banco, os serviços e retorna os totais encontrados.
        TelaPrincipal telaPrincipal = TelaPrincipal.getInstance(Login.usuarioLogado);
        QuartoDAO quartoDAO = QuartoDAO.getInstance();
        int[] totais = new int[5];
        totais = quartoDAO.quantidadeServicosQuartos();
        telaPrincipal.receberQuantias(totais);

        quartoDAO = QuartoDAO.getInstance();
        quartoDAO.atualizarQuarto();

    }

    //-------------------------------------------------------------------//---------------------------------------------------------
    public static QuartoDAO getInstance() {
        if (instance == null) {
            instance = new QuartoDAO();
        }
        return instance;
    }
}
