package br.com.hospede.control;

import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.EntrandoOuSaindoDAO;
import br.com.hospede.model.DAO.PassageiroDAO;
import br.com.hospede.model.DAO.PasseioDAO;
import br.com.hospede.model.DAO.ProdutoDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.DAO.ReservaDAO;
import br.com.hospede.model.DAO.UsuarioDAO;
import br.com.hospede.model.DTO.DTOReceitaDiaria;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOHistoricoCliente;
import br.com.hospede.model.dto.DTOHistoricoPasseio;
import br.com.hospede.model.dto.DTOHistoricoProduto;
import br.com.hospede.model.dto.DTOHistoricoQuarto;
import br.com.hospede.model.dto.DTOHistoricoReserva;
import br.com.hospede.model.dto.DTOHistoricoUsuario;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.modeloTabela.ModeloAuditarCliente;
import br.com.hospede.model.modeloTabela.ModeloAuditarHospedagem;
import br.com.hospede.model.modeloTabela.ModeloAuditarPasseio;
import br.com.hospede.model.modeloTabela.ModeloAuditarProduto;
import br.com.hospede.model.modeloTabela.ModeloAuditarQuarto;
import br.com.hospede.model.modeloTabela.ModeloAuditarReserva;
import br.com.hospede.model.modeloTabela.ModeloAuditarUsuario;
import br.com.hospede.model.modeloTabela.ModeloCliente;
import br.com.hospede.model.modeloTabela.ModeloEntrandoHoje;
import br.com.hospede.model.modeloTabela.ModeloHospedagem;
import br.com.hospede.model.modeloTabela.ModeloPasseio;
import br.com.hospede.model.modeloTabela.ModeloProduto;
import br.com.hospede.model.modeloTabela.ModeloQuarto;
import br.com.hospede.model.modeloTabela.ModeloReserva;
import br.com.hospede.model.modeloTabela.ModeloSaindoHoje;
import br.com.hospede.model.modeloTabela.ModeloUsuario;
import br.com.hospede.model.modeloTabela.ModeloPassageiros;
import br.com.hospede.model.modeloTabela.ModeloPasseiosRealizados;
import br.com.hospede.model.modeloTabela.ModeloPedidosDeQuarto;
import br.com.hospede.model.modeloTabela.ModeloProdutoPedido;
import br.com.hospede.model.modeloTabela.ModeloProdutosConsumidos;
import br.com.hospede.model.modeloTabela.ModeloReceitaDiaria;
import br.com.hospede.model.modeloTabela.ModeloSelecionarCliente;
import br.com.hospede.model.modeloTabela.ModeloSelecionarPasseio;
import br.com.hospede.model.modeloTabela.ModeloSelecionarQuarto;
import br.com.hospede.view.AdicionarPassageiroPasseio;
import br.com.hospede.view.AuditarCliente;
import br.com.hospede.view.AuditarHospedagem;
import br.com.hospede.view.AuditarPasseio;
import br.com.hospede.view.AuditarProduto;
import br.com.hospede.view.AuditarQuarto;
import br.com.hospede.view.AuditarReserva;
import br.com.hospede.view.AuditarUsuario;
import br.com.hospede.view.CadastrarUsuario;
import br.com.hospede.view.ConsultarUsuario;
import br.com.hospede.view.EditarUsuario;
import br.com.hospede.view.Login;
import br.com.hospede.view.SelecionarQuarto;
import br.com.hospede.view.CadastrarCliente;
import br.com.hospede.view.ConsultarCliente;
import br.com.hospede.view.EditarCliente;
import br.com.hospede.view.CadastrarHospedagem;
import br.com.hospede.view.CadastrarPasseios;
import br.com.hospede.view.CadastrarPedidoQuarto;
import br.com.hospede.view.CadastrarProduto;
import br.com.hospede.view.ConsultarHospedagem;
import br.com.hospede.view.EditarHospedagem;
import br.com.hospede.view.FecharHospedagem;
import br.com.hospede.view.CadastrarQuarto;
import br.com.hospede.view.EditarQuarto;
import br.com.hospede.view.CadastrarReserva;
import br.com.hospede.view.ConsultarProduto;
import br.com.hospede.view.ConsultarQuarto;
import br.com.hospede.view.ConsultarPasseio;
import br.com.hospede.view.ConsultarPedidoQuarto;
import br.com.hospede.view.ConsultarReserva;
import br.com.hospede.view.EditarPasseio;
import br.com.hospede.view.EditarProduto;
import br.com.hospede.view.EditarReserva;
import br.com.hospede.view.ProdutoPedido;
import br.com.hospede.view.ReceitaDiaria;
import br.com.hospede.view.SelecionarCliente;
import br.com.hospede.view.SelecionarPasseioCadastrarPassageiro;
import br.com.hospede.view.TelaPrincipal;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControlarEventos implements ActionListener, FocusListener, MouseListener {

    public static final int QUANTIDADE_POSICOES_VETOR_BOTOES = 85;
    public static final int QUANTIDADE_POSICOES_VETOR_ITENS_MENU = 50;
    public static final int QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO = 50;
    public static final int QUANTIDADE_MODELOS = 10;

    //Vetores para comportar os objetos de identifica????o dentro da classe "ControlarEventos".
    public static JButton[] botoes = new JButton[QUANTIDADE_POSICOES_VETOR_BOTOES];
    public static JMenuItem[] itensDeMenu = new JMenuItem[QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    public static JTextField[] camposDeTexto = new JTextField[QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];
    public static Object[] modeloTabelas = new Object[QUANTIDADE_MODELOS];

    //C??digos para criar interfaces. Exemplo: TELAPRINCIPAL indica para a classe "InterfaceFactory" criar a interface Tela principal.
    private final int TELA_PRINCIPAL = 1, CADASTRAR_USUARIO = 2, DELETAR_USUARIO = 3, CONSULTAR_USUARIO = 4, EDITAR_USUARIO = 5,
            CADASTRAR_CLIENTE = 6, DELETAR_CLIENTE = 7, CONSULTAR_CLIENTE = 8, EDITAR_CLIENTE = 9,
            CADASTRAR_QUARTO = 10, DELETAR_QUARTO = 13, CONSULTAR_QUARTO = 11, EDITAR_QUARTO = 12,
            CADASTRAR_RESERVA = 14, SELECIONAR_QUARTO = 15, CONSULTAR_RESERVA = 16, DELETAR_RESERVA = 17, EDITAR_RESERVA = 18,
            RELATORIO_SERVICOS = 19, AUDITAR_QUARTO = 20, AUDITAR_CLIENTE = 25, AUDITAR_USUARIO = 26, AUDITAR_RESERVA = 27,
            CADASTRAR_HOSPEDAGEM = 21, CONSULTAR_HOSPEDAGEM = 22, DELETAR_HOSPEDAGEM = 23, EDITAR_HOSPEDAGEM = 24,
            FECHAR_HOSPEDAGEM = 28, ENTRANDO_OU_SAINDO = 29, AUDITAR_HOSPEDAGEM = 30, CADASTRAR_PASSEIOS = 31, SELECIONAR_PASSEIOS_CONSULTA = 32,
            EDITAR_PASSEIOS = 33, DELETAR_PASSEIOS = 34, PASSEIOS = 35, CADASTRAR_PRODUTO = 36, EDITAR_PRODUTO = 37,
            DELETAR_PRODUTO = 38, CONSULTAR_PRODUTO = 39, PEDIDO_QUARTO = 40, CONSULTAR_PASSEIO = 41, SELECIONAR_PASSEIO_CADASTRAR_PASSAGEIRO = 42,
            ADICIONAR_PASSAGEIRO_PASSEIO = 43, PRODUTO_PEDIDO = 44, CONSULTAR_PEDIDO_QUARTO = 45, AUDITAR_PRODUTO = 46, AUDITAR_PASSEIO = 47,
            SELECIONAR_CLIENTE = 48;

    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat validarData = new SimpleDateFormat("dd/MM/yyyy");

    //Objetos de usu??rio.
    Login login;
    private DTOUsuario usuarioLogado;
    private InterfaceFactory interfaceCadastrarUsuario = null;
    private InterfaceFactory interfaceConsultarUsuario = null;
    private InterfaceFactory interfaceEditarUsuario = null;
    private CadastrarUsuario cadastrarUsuario;
    private ConsultarUsuario consultarUsuario;
    private EditarUsuario editarUsuario;
    private UsuarioDAO usuarioDAO;
    private ModeloUsuario modeloUsuario;

    //Objetos de cliente.
    private InterfaceFactory interfaceCadastrarCliente = null;
    private InterfaceFactory interfaceConsultarCliente = null;
    private InterfaceFactory interfaceEditarCliente = null;
    private ClienteDAO clienteDAO;
    private CadastrarCliente cadastrarCliente;
    private EditarCliente editarCliente;
    private ConsultarCliente consultarCliente;
    private ModeloCliente modeloCliente;
    private ModeloSelecionarCliente modeloSelecionarCliente;
    private SelecionarCliente selecionarCliente;

    //Objetos de quarto.
    private InterfaceFactory interfaceCadastrarQuarto = null;
    private InterfaceFactory interfaceConsultarQuarto = null;
    private InterfaceFactory interfaceEditarQuarto = null;
    private CadastrarQuarto cadastrarQuarto;
    private EditarQuarto editarQuarto;
    private ModeloQuarto modeloQuarto;
    private QuartoDAO quartoDAO;
    private ConsultarQuarto consultarQuarto;
    private ModeloSelecionarQuarto modeloSelecionarQuarto;

    //Objetos reserva.
    private InterfaceFactory interfaceCadastrarReserva = null;
    private InterfaceFactory interfaceSelecionarQuarto = null;
    private InterfaceFactory interfaceConsultarReserva = null;
    private InterfaceFactory interfaceEditarReserva = null;
    private InterfaceFactory interfaceFecharHospedagem = null;
    private InterfaceFactory interfaceSelecionarCliente = null;
    private SelecionarQuarto selecionarQuarto;
    private CadastrarReserva cadastrarReserva;
    private EditarReserva editarReserva;
    private ConsultarReserva consultarReserva;
    private FecharHospedagem fecharHospedagem;
    private ReservaDAO reservaDAO;
    private ModeloReserva modeloReserva;

    //Objetos hospedagem.
    private InterfaceFactory interfaceCadastrarHospedagem = null;
    private InterfaceFactory interfaceConsultarHospedagem = null;
    private InterfaceFactory interfaceEditarHospedagem = null;
    private CadastrarHospedagem cadastrarHospedagem;
    private EditarHospedagem editarHospedagem;
    private ModeloHospedagem modeloHospedagem;
    private ConsultarHospedagem consultarHospedagem;

    //Objetos auditar.
    private InterfaceFactory interfaceAuditarQuarto = null;
    private InterfaceFactory interfaceAuditarCliente = null;
    private InterfaceFactory interfaceAuditarUsuario = null;
    private InterfaceFactory interfaceAuditarReserva = null;
    private InterfaceFactory interfaceAuditarHospedagem = null;
    private InterfaceFactory interfaceAuditarProduto;
    private InterfaceFactory interfaceAuditarPasseio;
    private ModeloAuditarQuarto modeloAuditarQuarto;
    private ModeloAuditarCliente modeloAuditarCliente;
    private ModeloAuditarUsuario modeloAuditarUsuario;
    private ModeloAuditarReserva modeloAuditarReserva;
    private ModeloAuditarHospedagem modeloAuditarHospedagem;
    private ModeloAuditarProduto modeloAuditarProduto;
    private ModeloAuditarPasseio modeloAuditarPasseio;
    private DTOHistoricoReserva historicoReserva;
    private HistoricoDAO historicoDAO;

    //Objeto Tela Principal.
    private InterfaceFactory interfaceTelaPrincipal = null;
    private InterfaceFactory interfaceEntrandoOuSaindo;
    private ModeloEntrandoHoje modeloEntrandoHoje;
    private ModeloSaindoHoje modeloSaindoHoje;
    private EntrandoOuSaindoDAO entrandoOuSaindoDAO;

    //Objetos passeios
    private InterfaceFactory interfaceCadastrarPasseios;
    private InterfaceFactory interfaceEditarPasseios;
    private InterfaceFactory interfaceDeletarPasseios;
    private InterfaceFactory interfaceSelecionarPasseioConsulta;
    private InterfaceFactory interfaceEditarPasseio;
    private InterfaceFactory interfacePasseios;
    private InterfaceFactory interfaceConsultarPesseio;
    private InterfaceFactory interfaceSelecionarPasseioCadastrarPassageiro;
    private InterfaceFactory interfacePassageiroPassaio;
    private PasseioDAO passeioDAO;
    private CadastrarPasseios cadastrarPasseio;
    private EditarPasseio editarPasseio;
    private ModeloPasseio modeloPasseio;
    private ConsultarPasseio consultarPasseio;
    private SelecionarPasseioCadastrarPassageiro selecionarPasseioCadastrarPassageiro;
    private ModeloPassageiros modeloPassageiro;
    private AdicionarPassageiroPasseio adicionarPassageiroPasseio;
    private ModeloSelecionarPasseio modeloSelecionarPasseio;
    private ModeloPasseiosRealizados modeloPasseiosRealizados;

    //Objetos produtos
    private InterfaceFactory interfaceCadastrarProdutos;
    private InterfaceFactory interfaceConsultarProduto;
    private InterfaceFactory interfaceEditarProduto;
    private InterfaceFactory interfaceDeletarProduto;
    private InterfaceFactory interfaceCadastraPedidoQuarto;
    private InterfaceFactory interfaceProdutoPedido;
    private InterfaceFactory interfaceConsultarPedidoQuarto;
    private CadastrarProduto cadastrarProduto;
    private ProdutoDAO produtoDAO;
    private ModeloProduto modeloProduto;
    private EditarProduto editarProduto;
    private CadastrarPedidoQuarto cadastrarPedidoQuarto;
    private ProdutoPedido produtoPedido;
    private ModeloProdutoPedido modeloProdutoPedido;
    private ModeloPedidosDeQuarto modeloPedidosDeQuarto;
    private ConsultarPedidoQuarto consultarPedidoQuarto;
    private ConsultarProduto consultarProduto;
    private ModeloProdutosConsumidos modeloProdutosConumidos;

    //Objetos passageiros
    private PassageiroDAO passageiroDAO;
    private ModeloPassageiros modeloPassageiros;

    public static ControlarEventos instance = null;
    private Date dataAtual = new Date();

    public static Login instanceLogin = null;
    private TelaPrincipal telaPrincipal;
    
    private InterfaceFactory interfaceReceitaDiaria;

    //Construtor principal.
    public ControlarEventos(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.camposDeTexto = camposDeTexto;
        this.usuarioLogado = usuarioLogado;
        dataAtual.getTime();

    }

    //Eventos de clique.
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botoes[0]) {//Evento acionado ap??s clicar no bot??o "Entrar" da classe "Login".

            //Verifica o acesso do usu??rio.
            usuarioDAO = UsuarioDAO.getInstance();
            login = Login.getInstance();
            usuarioLogado = usuarioDAO.consultarUsuario(login.getUsuario().getLogin());

            //Verifica se h?? campos em branco.
            try {
                if (login.getUsuario().getLogin().equals("") && login.getUsuario().getSenha().equals("")) {
                    throw new Exception();

                } else if (usuarioLogado.getSenha().equals(login.getUsuario().getSenha())) {
                    if (interfaceTelaPrincipal == null || interfaceTelaPrincipal.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                        interfaceTelaPrincipal = InterfaceFactory.getInterface(TELA_PRINCIPAL, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        interfaceTelaPrincipal.setVisible(true);
                        login.dispose();

                        //Primeiro a tabela com os clientes entrando
                        entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
                        modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
                        modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());

                        //Depois, as tabelas com os cliente saindo.
                        modeloSaindoHoje = ModeloSaindoHoje.getInstance();
                        modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());

                        //Valores para os servi??os
                        //Busca no banco, os servi??os e retorna os totais encontrados.
                        telaPrincipal = TelaPrincipal.getInstance(usuarioLogado);
                        quartoDAO = QuartoDAO.getInstance();
                        int[] totais = new int[5];
                        totais = quartoDAO.quantidadeServicosQuartos();
                        telaPrincipal.receberQuantias(totais);

                    } else {//Caso a interface j?? tenha sido aberto uma vez.
                        interfaceTelaPrincipal.setVisible(true);
                    }

                } else { //Caso o usu??rio n??os seja encontrado no banco de dados e a consulta retorne null.
                    JOptionPane.showMessageDialog(null, "Usu??rio e senha n??o correspondem");
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "H?? campos em branco.");
            }

            //---------------------------------------------------------------------------------------------------------------   
        } else if (evento.getSource() == botoes[1]) {//Evento acionado ap??s clicar no bot??o "Sair" da classe "TelaPrincipal".

            System.exit(0);
            //----------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[2]) {//Evento disparado ap??s clicar no bot??o DTOUsuario na classe "TelaPrincipal".
            if (interfaceConsultarUsuario == null || interfaceConsultarUsuario.isValid()) {
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                usuarioDAO = UsuarioDAO.getInstance();
                modeloUsuario = ModeloUsuario.getInstance();

                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);

            } else {

                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[3]) {
            //Evento disparado ap??s clicar no bot??o Pesquisar na classe "AuditarUsuario".

            AuditarUsuario auditarUsuario = AuditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarUsuario = ModeloAuditarUsuario.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();
            //Encontrando o usu??rio
            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarUsuario.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuario());
                
            } else {
            modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuarioLogin(usuario.getId_usuario()));
            }

            

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[4]) {//Evento disparado ap??s cliqe no bot??o "Cadastrar" da classe "CadastarUsuario".
            usuarioDAO = UsuarioDAO.getInstance();
            DTOUsuario usuarioCadastrar;
            modeloUsuario = ModeloUsuario.getInstance();
            cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o usu??rio j?? existe no banco.
            usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());

            if (cadastrarUsuario.getUsuario().getLogin().equals("")) {//Caso aperte cadastrar com o campo login da classe "CadastrarUsuario" esteja em branco.
                JOptionPane.showMessageDialog(null, "Digite informa????es obrigat??rias de usu??rio para cadastro.");

            } else //Caso login n??o est?? em branco.
            if (usuarioCadastrar.getSenha() == "") {//Caso n??o esteja cadastrado no banco.

                if ("".equals(cadastrarUsuario.getUsuario().getLogin()) || "".equals(cadastrarUsuario.getUsuario().getTelefone())
                        || "".equals(cadastrarUsuario.getUsuario().getSenha())) {//Verifica se existem campos obrigat??rios em branco.
                    JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco.");

                } else {//Caso n??o seja cadastrado e nem haja campos em branco, pode cadastrar.

                    boolean cadastrou = false;//Verifica se o cadastro fou realizado com sucesso.
                    cadastrou = usuarioDAO.cadastrarUsuario(cadastrarUsuario.getUsuario());

                    if (cadastrou != false) {
                        //Pega o ID do usuario cadastrado.
                        DTOHistoricoUsuario historicoUsuario = new DTOHistoricoUsuario();
                        usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());
                        historicoUsuario.setId_suario_alterado(usuarioCadastrar.getId_usuario());
                        historicoUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        historicoUsuario.setUsuario_alterado(usuarioCadastrar.getLogin());
                        historicoUsuario.setAcao("Cadastrou");

                        //Salva na tabale para audita????o.
                        historicoDAO = HistoricoDAO.getInstance();
                        historicoDAO.salvarManterUsuario(historicoUsuario);

                        cadastrarUsuario.dispose();
                        cadastrarUsuario.limparCampos();

                        modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                    }

                }

            } else {//Significa que o usu??rio j?? existe no banco de dados.
                JOptionPane.showMessageDialog(null, "Este usu??rio j?? existe.");
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[5]) {//Acionao ap??s clique no bot??o "Usuarios" da clase "TelaPrincipal;
            if (interfaceConsultarUsuario == null || interfaceConsultarUsuario.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                usuarioDAO = UsuarioDAO.getInstance();
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Alimenta a tabela com uma lista vindo do m??todo "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario = ModeloUsuario.getInstance();
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);

            } else {
                //Alimenta a tabela com uma lista vindo do m??todo "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[6]) {
            //Pesquisar da classe "AuditarQuarto"

            AuditarQuarto auditarQuarto = AuditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarQuarto = ModeloAuditarQuarto.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();

            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarQuarto.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuarto());
                
            } else {
                 modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuartoUsuario(usuario.getId_usuario()));
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[7]) {//Acionado ap??s clique no bot??o "Editar" da classe "EditarUsuario"
            usuarioDAO = UsuarioDAO.getInstance();
            DTOUsuario usuarioEditar;
            editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o usu??rio existe no banco.
            usuarioEditar = usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin());

            if (usuarioEditar.getSenha() == null) {
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado!");

            } else//Caso usu??rio seja encontrado no banco.
            if (editarUsuario.getUsuario().getTelefone().equals("") || editarUsuario.getUsuario().getSenha().equals("")
                    || editarUsuario.getUsuario().getLogin().equals("") || editarUsuario.getUsuario().getEmail().equals("")) {
                //Verifica se h?? campos obrigat??rios em branco.
                JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco");

            } else {

                boolean editou = false;//Verifica se a edi????o foi bem sucedida.
                editou = usuarioDAO.editarUsuario(editarUsuario.getUsuario());

                if (editou != false) {
                    //Pega o ID do usuario cadastrado.
                    DTOHistoricoUsuario historicoUsuario = new DTOHistoricoUsuario();
                    historicoUsuario.setId_suario_alterado(usuarioEditar.getId_usuario());
                    historicoUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                    historicoUsuario.setUsuario_alterado(usuarioEditar.getLogin());
                    historicoUsuario.setAcao("Editou");

                    //Salva na tabela de auditoria.
                    historicoDAO = HistoricoDAO.getInstance();
                    historicoDAO.salvarManterUsuario(historicoUsuario);

                    editarUsuario.limparCampos();
                    editarUsuario.dispose();
                    editarUsuario = null;

                    usuarioDAO = UsuarioDAO.getInstance();
                    modeloUsuario = ModeloUsuario.getInstance();

                    modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());

                }

            }

            //---------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[8] || evento.getSource() == botoes[79]) {
            //Acionado ap??s clique no bot??o "Clientes" da classe "Tela Principal".
            if (interfaceCadastrarCliente == null || interfaceCadastrarCliente.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceCadastrarCliente = InterfaceFactory.getInterface(CADASTRAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceCadastrarCliente.setVisible(true);

            } else {
                cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarCliente.limparCampos();
                interfaceCadastrarCliente.setVisible(true);
            }

            //---------------------------------------------------------------------------------------------------------------------- 
        } else if (evento.getSource() == botoes[9]) {
            //Acionado pelo clique no bot??o "Cadastrar"da classe "CadastrarCliente".
            clienteDAO = ClienteDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            DTOCliente clienteCadastrar;
            cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o cliente j?? existe no banco.
            clienteCadastrar = clienteDAO.consultarCliente(cadastrarCliente.getCliente());

            if (cadastrarCliente.getCliente().getNome().equals("")) {//Caso aperte cadastrar com o campo nome da classe "CadastrarCliete" esteja em branco.
                JOptionPane.showMessageDialog(null, "Digite informa????es obrigat??rias de cliente para cadastro.");

            } else { //Caso campo nome n??o esteja em branco.

                if (clienteCadastrar.getCpf().equals("") || clienteCadastrar.getPassaport().equals("")) { //caso n??o esteja cadastrado no banco.

                    if (cadastrarCliente.getCliente().getCelular().equals("") || cadastrarCliente.getCliente().getCpf().equals("") && cadastrarCliente.getCliente().getPassaport().equals("")
                            || cadastrarCliente.getCliente().getEndereco().equals("") || cadastrarCliente.getCliente().getEstado().equals("")
                            || cadastrarCliente.getCliente().getNome().equals("")) { //Verifica se h?? campos obrigat??rios em branco.
                        JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco.");

                    } else {//Caso n??o seja cadastrado e nem haja campos em branco, pode cadastrar.

                        boolean cadastrou = false; //Verifica se o cadastro foi bem sucedido.
                        cadastrou = clienteDAO.cadastrarCliente(cadastrarCliente.getCliente());

                        if (cadastrou != false) {

                            //Pega o ID do cliente que foi cadastrado.
                            DTOHistoricoCliente historicoCliente = new DTOHistoricoCliente();
                            clienteCadastrar = clienteDAO.consultarCliente(cadastrarCliente.getCliente());
                            historicoCliente.setId_cliente(clienteCadastrar.getId_cliente());
                            historicoCliente.setNome_cliente(clienteCadastrar.getNome());
                            historicoCliente.setId_usuario(usuarioLogado.getId_usuario());
                            historicoCliente.setAcao("Cadastrou");

                            //Sala na tabela
                            historicoDAO.salvarManterCliente(historicoCliente);

                            cadastrarCliente.dispose();
                            cadastrarCliente.limparCampos();

                            modeloCliente = ModeloCliente.getInstance();
                            modeloSelecionarCliente = ModeloSelecionarCliente.getInstance();

                            modeloCliente.adicionarLista(clienteDAO.listarCliente());
                            modeloSelecionarCliente.adicionarLista(clienteDAO.listarCliente());
                        }

                    }

                } else {//Caso j?? exista um cliente no banco.
                    JOptionPane.showMessageDialog(null, "Este CPF ou Passaporte encontra-se cadastrado.");
                }
            }

            //---------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[10]) {//Acionado ap??s clique no bot??o "Clientes" da clase "TelaPrincipal.
            if (interfaceConsultarCliente == null || interfaceConsultarCliente.isValid()) {
                interfaceConsultarCliente = InterfaceFactory.getInterface(CONSULTAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Alimenta a tabela com uma lista vindo do m??todo "listarCliente" da classe "clienteDao".
                clienteDAO = ClienteDAO.getInstance();
                modeloCliente = ModeloCliente.getInstance();
                modeloCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceConsultarCliente.setVisible(true);
            } else {
                //Alimenta a tabela com uma lista vindo do m??todo "listarCliente" da classe "clienteDao".
                modeloCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceConsultarCliente.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------------- 
        } else if (evento.getSource() == botoes[11]) {//Acionado pelo bot??o "Hospedar cliente reservado" da classe "CadastrarHospdagem".
            quartoDAO = QuartoDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            if (cadastrarHospedagem.getHospedagem().getTipo().equals("")) {
                JOptionPane.showMessageDialog(null, "   O cliente n??o esta reservado");

            } else {
                //Altera o quarto para "HOSPEDADO"
                DTOQuarto quartoEditar = quartoDAO.getQuartoPorID(cadastrarHospedagem.getReservaParaHospedar());
                quartoEditar.setSituacao("HOSPEDADO"); //Passa o quarto de reservado para hospedado.
                quartoDAO.editarQuarto(quartoEditar);

                //Depois altera a reserva para hospedagem.
                DTOReserva reservaEditar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                reservaEditar.setStatus("HOSPEDADA");
                boolean editou = false;
                editou = reservaDAO.editarReserva(reservaEditar);

                //Crio uma hospedagem
                DTOReserva reservaHospedar = reservaEditar;
                reservaHospedar.setTipo("HOSPEDAR");
                reservaDAO.cadastrarHospedagemReservada(reservaHospedar, cadastrarHospedagem.getCliente(), quartoEditar);

                //Salva a hospedagem feita na tabela que audita as hospedagens.
                if (editou != false) {

                    //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                    DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                    historicoDAO = HistoricoDAO.getInstance();
                    historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    historicoReserva.setLogin(usuarioLogado.getLogin());
                    historicoReserva.setAcao("Hospedou");
                    historicoReserva.setTipo_reserva("HOSPEDAR");

                    //Obter o ID da reserva.
                    reservaEditar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                    historicoReserva.setId_reserva(reservaEditar.getId_reserva());
                    historicoReserva.setCliente_reservado(cadastrarHospedagem.getCliente().getNome());

                    //Salva na tabela.
                    historicoDAO.salvarManterHospedagem(historicoReserva);

                    cadastrarHospedagem.limparCampos();
                    cadastrarHospedagem.dispose();
                    modeloHospedagem = ModeloHospedagem.getInstance();
                    modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                }
            }

            quartoDAO.atualizarQuarto();
            //------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[16]) {//Pesquisar AuditarCliente

            AuditarCliente auditarCliente = AuditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarCliente = ModeloAuditarCliente.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();
            
             //Encontrando o usu??rio
            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarCliente.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarCliente.adicionarLista(historicoDAO.auditarCliente());
                
            } else {
                 modeloAuditarCliente.adicionarLista(historicoDAO.auditarClienteUsuario(usuario.getId_usuario()));
            }

          

            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[12]) {//Pesquisar AuditarReserva

            AuditarReserva auditarReserva = AuditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarReserva = ModeloAuditarReserva.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();

            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarReserva.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarReserva.adicionarLista(historicoDAO.auditarReserva());
                
            } else {
                 modeloAuditarReserva.adicionarLista(historicoDAO.auditarReservaUsuario(usuario.getId_usuario()));
            }

            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[13]) {//Acionado pelo bot??o "Editar" da classe "EditarCliente".
            clienteDAO = ClienteDAO.getInstance();
            DTOCliente clienteEditar;
            editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o cliente existe no banco.
            if(editarCliente.getCliente().getCpf().equals("   .   .   -  ")){
                clienteEditar = clienteDAO.consultarClientePassaporte(editarCliente.getCliente());
            } else {
                clienteEditar = clienteDAO.consultarCliente(editarCliente.getCliente());
            }
            

            if (clienteEditar.getCelular() == null) {
                JOptionPane.showMessageDialog(null, "Cliente n??o encontrado!");

            } else {//Caso o usu??rio seja encontrado no banco

                if (editarCliente.getCliente().getCelular().equals("") || editarCliente.getCliente().getCpf().equals("")
                        || editarCliente.getCliente().getEndereco().equals("") || editarCliente.getCliente().getNome().equals("")
                        || editarCliente.getCliente().getEstado().equals("")) {//Verifica se h?? campos obrigat??rios em branco.
                    JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco");

                } else {

                    boolean editou = false;
                    editou = clienteDAO.editarCliente(editarCliente.getCliente());

                    if (editou != false) {

                        DTOHistoricoCliente historicoCliente = new DTOHistoricoCliente();
                        historicoDAO = HistoricoDAO.getInstance();
                        historicoCliente.setId_cliente(clienteEditar.getId_cliente());

                        //Pegando o ID do cliente editado.
                        historicoCliente.setId_cliente(clienteEditar.getId_cliente());
                        historicoCliente.setNome_cliente(clienteEditar.getNome());
                        historicoCliente.setId_usuario(usuarioLogado.getId_usuario());
                        historicoCliente.setAcao("Editou");

                        historicoDAO.salvarManterCliente(historicoCliente);

                        editarCliente.limparCampos();
                        editarCliente.dispose();
                        editarCliente = null;

                        modeloCliente = ModeloCliente.getInstance();
                        modeloCliente.adicionarLista(clienteDAO.listarCliente());
                    }
                }
            }

            //-------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[14]) {
            //Acionado ap??s clique no bot??o "Cadastrar" da classe "CadastrarQuarto".
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quartoCadastrar;
            cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                //Verifica se o quarto j?? est?? cadastrado.
                quartoCadastrar = quartoDAO.consultarQuarto(Integer.parseInt(cadastrarQuarto.getQuarto().getNumero()));

                if (cadastrarQuarto.getQuarto().getNumero().equals("")) {//Caso o campo "n??mero" da classe "CadastrarQuarto" esteja em branco.
                    JOptionPane.showMessageDialog(null, "Digite informa????es obrigat??rias do quarto para cadastro.");

                } else {

                    if (quartoCadastrar.getNumero().equals("")) {//Caso o quarto n??o esteja cadastrado.
                        if (cadastrarQuarto.getQuarto().getNumero().equals("") || cadastrarQuarto.getQuarto().getSituacao().equals("")
                                || cadastrarQuarto.getQuarto().getOcupantes().equals("") || cadastrarQuarto.getQuarto().getDiaria().equals("")
                                || cadastrarQuarto.getQuarto().getCategoria().equals("")) {
                            JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco.");

                        } else {

                            boolean cadastrou = false; //Repons??vel por controlar se o cadastro foi bem sucedido.
                            cadastrou = quartoDAO.cadastrarQuarto(cadastrarQuarto.getQuarto());

                            if (cadastrou != false) {//Se o cadastro foi bem sucedido, este m??todo registra na tabela "manter_quarto" quem cadastrou e qual quarto foi cadastrado.
                                HistoricoDAO auditarDAO = new HistoricoDAO();
                                DTOHistoricoQuarto historicoQuarto = new DTOHistoricoQuarto();
                                HistoricoDAO manterQuartoDAO = HistoricoDAO.getInstance();

                                //Obtem o ID do quarto cadastrado.
                                quartoCadastrar = quartoDAO.consultarQuarto(Integer.parseInt(cadastrarQuarto.getQuarto().getNumero()));
                                historicoQuarto.setId_quarto(quartoCadastrar.getId_quarto());
                                historicoQuarto.setNumero_quarto(Integer.parseInt(quartoCadastrar.getNumero()));
                                historicoQuarto.setId_usuario(usuarioLogado.getId_usuario());
                                historicoQuarto.setAcao("Cadastrou");

                                //Salva na tabela.
                                auditarDAO.salvarManterQuarto(historicoQuarto);

                                cadastrarQuarto.dispose();
                                cadastrarQuarto.limparCampos();
                                cadastrarQuarto = null;

                                modeloQuarto = ModeloQuarto.getInstance();
                                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Quarto j?? existe no banco.");
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Digite informa????es obrigat??rias do quarto para cadastro.");
            }

            //-----------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[15]) {//Acionado ap??s clique no bot??o "Quartos" da classe "TelaPrincipal".
            if (interfaceConsultarQuarto == null || interfaceConsultarQuarto.isValid()) {
                interfaceConsultarQuarto = InterfaceFactory.getInterface(CONSULTAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceConsultarQuarto.setVisible(true);

                //Aciona o m??todo para listar todos os quartos.
                quartoDAO = QuartoDAO.getInstance();
                modeloQuarto = ModeloQuarto.getInstance();
                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();

            } else {

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();

                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);
            }

            //------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[17]) {//Acionado pelo bot??o 'Editar" da classe "EditarQuarto"
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quartoEditar;
            editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                //Verifica se o usu??rio existe no banco.
                quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(editarQuarto.getQuarto().getNumero()));
                if (editarQuarto.getQuarto().getNumero().equals("")) {//Verifica se o campo "Numero" da classe "EditarQuarto" est?? em branco.
                    JOptionPane.showMessageDialog(null, "Digite informa????es obrigat??rias do quarto para cadastro.");

                } else {

                    if (quartoEditar == null) {//Caso o quarto n??o esteja cadastrado.
                        JOptionPane.showMessageDialog(null, "Cliente n??o encontrado.");

                    } else if (editarQuarto.getQuarto().getNumero().equals("") || editarQuarto.getQuarto().getSituacao().equals("")
                            || editarQuarto.getQuarto().getOcupantes().equals("") || editarQuarto.getQuarto().getDiaria().equals("")
                            || editarQuarto.getQuarto().getCategoria().equals("") || editarQuarto.getQuarto().getSituacao().equals("")) {//Verifica se h?? campos obrigat??rios em branco.
                        JOptionPane.showMessageDialog(null, "H?? campos obrigat??rios em branco.");

                    } else {
                        boolean editou = false;//Respons??vel por controlar se a edi????o foi bem sucedida.
                        editou = quartoDAO.editarQuarto(editarQuarto.getQuarto());
                        editarQuarto.dispose();
                        editarQuarto.limparCampos();

                        if (editou != false) {//Se a edi????o foi bem sucedida, este m??todo registra na tabela "manter_quarto" quem editou e qual quarto foi editado.

                            DTOHistoricoQuarto historicoQuarto = new DTOHistoricoQuarto();
                            HistoricoDAO manterQuartoDAO = HistoricoDAO.getInstance();

                            //Obtem o ID do quarto cadastrado.
                            historicoQuarto.setId_quarto(quartoEditar.getId_quarto());
                            historicoQuarto.setNumero_quarto(Integer.parseInt(quartoEditar.getNumero()));
                            historicoQuarto.setId_usuario(usuarioLogado.getId_usuario());
                            historicoQuarto.setAcao("Editou");

                            //Salva na tabela.
                            manterQuartoDAO.salvarManterQuarto(historicoQuarto);
                            JOptionPane.showMessageDialog(null, "Quarto editado com sucesso!");
                            editarQuarto.dispose();
                            editarQuarto.limparCampos();
                            editarQuarto = null;

                            modeloQuarto = ModeloQuarto.getInstance();
                            modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                        }
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Quarto n??o encontrado.");
            }
            quartoDAO.atualizarRelatorio();

            //--------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[18]) {
            if (interfaceSelecionarCliente == null || interfaceSelecionarCliente.isValid()) {
                interfaceSelecionarCliente = InterfaceFactory.getInterface(SELECIONAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                clienteDAO = ClienteDAO.getInstance();
                selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                selecionarCliente.setTipoReserva("Reservar");

                modeloSelecionarCliente = ModeloSelecionarCliente.getInstance();
                modeloSelecionarCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceSelecionarCliente.setVisible(true);

            } else {
                selecionarCliente.setTipoReserva("Reservar");
                modeloSelecionarCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceSelecionarCliente.setVisible(true);
            }
            //---------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[19]) { 
          //Acionado a??s clique no bot??o "Reservar" da classe "CadastrarReserva".

            //Verifica se o cliente est?? cadastrado
            clienteDAO = ClienteDAO.getInstance();
            cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            DTOCliente cliente = clienteDAO.consultarCliente(cadastrarReserva.getCliente());

            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente n??o encontrado.");

            } else {

                try {
                    if (cadastrarReserva.getReserva().getEntrada() == null || cadastrarReserva.getReserva().getSaida() == null) {//Verifica se h?? campos obrigat??rios em branco.
                        JOptionPane.showMessageDialog(null, "H?? campos em branco.");

                    } else {

                        //Valida das datas inseridas
                        if (    validarData.parse(validarData.format(cadastrarReserva.getReserva().getEntrada())).before(validarData.parse(validarData.format(dataAtual)))
                                || cadastrarReserva.getReserva().getSaida().before(dataAtual)
                                || cadastrarReserva.getReserva().getEntrada().after(cadastrarReserva.getReserva().getSaida())
                                || validarData.format(cadastrarReserva.getReserva().getEntrada()).equals(validarData.format(cadastrarReserva.getReserva().getSaida()))) {
                            JOptionPane.showMessageDialog(null, "Data para reserva invalida.");

                        } else {

                            if (interfaceSelecionarQuarto == null || interfaceSelecionarQuarto.isValid()) {
                                interfaceSelecionarQuarto = InterfaceFactory.getInterface(SELECIONAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                                //Aciona o m??todo para listar todos os quartos.
                                quartoDAO = QuartoDAO.getInstance();
                                modeloSelecionarQuarto = ModeloSelecionarQuarto.getInstance();
                                DTOReserva reserva = cadastrarReserva.getReserva();
                                modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuartoSelecionar(reserva.getEntrada(), reserva.getSaida()));

                                //Informa o tipo de reserva.
                                selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                                selecionarQuarto.setTipoReserva("RESERVAR");

                                interfaceSelecionarQuarto.setVisible(true);
                            } else {

                                //Informa o tipo de reserva.
                                selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                                selecionarQuarto.setTipoReserva("RESERVAR");
                                DTOReserva reserva = cadastrarReserva.getReserva();
                                modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuartoSelecionar(reserva.getEntrada(), reserva.getSaida()));
                                interfaceSelecionarQuarto.setVisible(true);
                            }
                        }
                    }

                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
                }
            }
            quartoDAO.atualizarQuarto();

            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[20]) {//Acionado pelo bot??o Selecionar da classe "SelecionarQuato".
            //1.Primeiro altera o quarto.

            quartoDAO = QuartoDAO.getInstance();
            selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            DTOQuarto quartoEditar;
            quartoEditar = selecionarQuarto.getQuarto();

            if (selecionarQuarto.getTipoReserva().equals("RESERVAR")) {//Significa que ?? uma reserva do tipo "reservar".

                try {
                    if (selecionarQuarto.getQuarto().getNumero().equals("")) {//Caso o usu??rio n??o selecione nehum quarto e aperte "Selecionar" da classe "SelecionarQuarto".
                        JOptionPane.showMessageDialog(null, "?? neces??rio escolher um quarto.");

                    } else {

                        if (!quartoDAO.checarDisponibilidadeData(cadastrarReserva.getReserva(), quartoEditar)) {//Verifica se o quarto j?? est?? sendo utilizado e n??o permite reservar.
                            JOptionPane.showMessageDialog(null, "Este quarto n??o est?? dispon??vel para este per??odo.");

                        } else {

                            //pegando o ID do quarto para associar a reserva.
                            quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(quartoEditar.getNumero()));

                            //2.depois, cadastra a reserva.
                            reservaDAO = ReservaDAO.getInstance();
                            cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                            DTOReserva reservaCadastrar;
                            reservaCadastrar = cadastrarReserva.getReserva();
                            reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());

                            boolean reservou = false;//Respons??vel por verificar se conseguiu reservar.
                            DTOCliente cliente;
                            cliente = cadastrarReserva.getCliente();
                            reservou = reservaDAO.cadastrarReserva(reservaCadastrar, cliente, quartoEditar);

                            if (reservou != false) {

                                //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                                DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                                historicoDAO = HistoricoDAO.getInstance();
                                historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                                historicoReserva.setLogin(usuarioLogado.getLogin());
                                historicoReserva.setAcao("Reservou");
                                historicoReserva.setTipo_reserva("RESERVAR");
                                historicoReserva.setCliente_reservado(cadastrarReserva.getCliente().getNome());

                                //Obter o ID da reserva.
                                reservaCadastrar = reservaDAO.consultarReserva(cadastrarReserva.getCliente().getId_cliente());
                                historicoReserva.setId_reserva(reservaCadastrar.getId_reserva());

                                //Salva na tabela.
                                historicoDAO.salvarManterReserva(historicoReserva);

                                selecionarQuarto.dispose();
                                cadastrarReserva.dispose();
                                cadastrarReserva.limparCampos();

                                modeloReserva = ModeloReserva.getInstance();
                                modeloReserva.adicionarLista(reservaDAO.listarReservas());
                            }

                            QuartoDAO quartoDAO = QuartoDAO.getInstance();
                            quartoDAO.atualizarQuarto();
                        }

                    }
                } catch (Exception erro) {

                }

            } else { //Significa que ?? uma reserva do tipo "hospedar"

                try {

                    if (!quartoDAO.checarDisponibilidadeData(cadastrarHospedagem.getHospedagem(), quartoEditar)) {//Verifica se o quarto j?? est?? sendo utilizado e n??o permite reservar.
                        JOptionPane.showMessageDialog(null, "Este quarto n??o est?? dispon??vel para este per??odo.");

                    } else {

                        //pegando o ID do quarto para associar a reserva.
                        quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(quartoEditar.getNumero()));

                        //2.depois, cadastra a reserva.
                        reservaDAO = ReservaDAO.getInstance();
                        cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        DTOReserva reservaCadastrar;
                        reservaCadastrar = cadastrarHospedagem.getHospedagem();
                        reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());
                        reservaCadastrar.setTipo("HOSPEDAR");

                        DTOCliente cliente;
                        cliente = cadastrarHospedagem.getCliente();

                        boolean hospedou = false;
                        hospedou = reservaDAO.cadastrarHospedagem(reservaCadastrar, cliente, quartoEditar);

                        if (hospedou != false) {

                            //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                            DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                            historicoDAO = HistoricoDAO.getInstance();
                            historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                            historicoReserva.setLogin(usuarioLogado.getLogin());
                            historicoReserva.setAcao("Hospedou");
                            historicoReserva.setTipo_reserva("HOSPEDAR");

                            //Obter o ID da reserva.
                            reservaCadastrar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                            historicoReserva.setId_reserva(reservaCadastrar.getId_reserva());
                            historicoReserva.setCliente_reservado(cadastrarHospedagem.getCliente().getNome());

                            //Salva na tabela.
                            historicoDAO.salvarManterHospedagem(historicoReserva);

                            selecionarQuarto.dispose();
                            cadastrarHospedagem.dispose();
                            cadastrarHospedagem.limparCampos();

                            modeloHospedagem = ModeloHospedagem.getInstance();
                            modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                        }

                        QuartoDAO quartoDAO = QuartoDAO.getInstance();
                        quartoDAO.atualizarQuarto();
                    }

                } catch (HeadlessException | NumberFormatException erro) {

                }

            }
            quartoDAO.atualizarQuarto();
            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[21]) {
            //Acionado pelo bot??o "Reservas" da classe "TelaPrincipal".
            if (interfaceConsultarReserva == null || interfaceConsultarReserva.isValid()) {
                interfaceConsultarReserva = InterfaceFactory.getInterface(CONSULTAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Aciona o m??todo para listas todas as reservas.
                reservaDAO = ReservaDAO.getInstance();
                modeloReserva = ModeloReserva.getInstance();
                modeloReserva.adicionarLista(reservaDAO.listarReservas());
                interfaceConsultarReserva.setVisible(true);

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();

            } else {

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();
                modeloReserva.adicionarLista(reservaDAO.listarReservas());
                interfaceConsultarReserva.setVisible(true);
            }

            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[22]) {//Pesquisar AuditarHospedagem
            AuditarHospedagem auditarHospedagem = AuditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarHospedagem = ModeloAuditarHospedagem.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();
            
            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarHospedagem.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarHospedagem.adicionarLista(historicoDAO.auditarHospedagem());
                
            } else {
                 modeloAuditarHospedagem.adicionarLista(historicoDAO.auditarHospedagemUsuario(usuario.getId_usuario()));
            }

            //--------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[23]) {//Acionado pelo bot??o Editar da classe "EditarReserva".
            reservaDAO.getInstance();
            editarReserva = EditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                if (editarReserva.getReserva().getEntrada() == null
                        || editarReserva.getReserva().getSaida() == null) {
                    JOptionPane.showMessageDialog(null, "Reserva n??o encontrada. Certifique-se se o cliente est?? reservado.");

                } else {

                    //Verifica se as datas s??o v??lidas dentro da regra de neg??cio.
                    if (validarData.parse(validarData.format(editarReserva.getReserva().getEntrada())).before(validarData.parse(validarData.format(dataAtual))) || validarData.parse(validarData.format(editarReserva.getReserva().getSaida())).before(validarData.parse(validarData.format(dataAtual)))
                            || editarReserva.getReserva().getEntrada().after(editarReserva.getReserva().getSaida())
                            || validarData.format(editarReserva.getReserva().getEntrada()).equals(validarData.format(editarReserva.getReserva().getSaida()))) {

                        JOptionPane.showMessageDialog(editarReserva, "Data para reserva inv??lida");
                    } else {

                        //Verifica se a reserva n??o esbarra em outra reserva.
                        DTOQuarto quartoVerificar = quartoDAO.getQuartoPorID(editarReserva.getReserva());
                        if (quartoDAO.checarDisponibilidadeData(editarReserva.getReserva(), quartoVerificar) != true){
                            
                            JOptionPane.showMessageDialog(null, "Este quarto n??o est?? dispon??vel para este per??odo.");
                            
                        } else {

                            if(validarData.parse(validarData.format(editarReserva.getReserva().getEntrada())).getTime()>validarData.parse(validarData.format(dataAtual)).getTime()){
                                quartoVerificar.setSituacao("LIVRE");
                                quartoDAO.editarQuarto(quartoVerificar);
                            }
                            
                            boolean editou = false;
                            editou = reservaDAO.editarReserva(editarReserva.getReserva());

                            if (editou != false) {
                                //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                                DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                                historicoDAO = HistoricoDAO.getInstance();
                                historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                                historicoReserva.setLogin(usuarioLogado.getLogin());
                                historicoReserva.setAcao("Editou");
                                historicoReserva.setTipo_reserva("RESERVAR");
                                historicoReserva.setCliente_reservado(editarReserva.getCliente().getNome());

                                //Obter o ID da reserva.
                                historicoReserva.setId_reserva(editarReserva.getReserva().getId_reserva());
                                historicoReserva.setCliente_reservado(editarReserva.getCliente().getNome());

                                //Salva na tabela.
                                historicoDAO.salvarManterReserva(historicoReserva);

                                editarReserva.dispose();
                                editarReserva.limparCampos();

                                modeloReserva = ModeloReserva.getInstance();
                                modeloReserva.adicionarLista(reservaDAO.listarReservas());
                            }
                        }
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Reserva n??o encontrada.");
            }

            quartoDAO.atualizarQuarto();
            //--------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[24]) {
            //Acionado pelo bot??o "Atualizar Dados" da classe "TelaPrincipal".
           
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[25]) {//Acionado pelo bot??o Hospedar da classe "Telaprincipal".
            if (interfaceSelecionarCliente == null || interfaceSelecionarCliente.isValid()) {
                interfaceSelecionarCliente = InterfaceFactory.getInterface(SELECIONAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                clienteDAO = ClienteDAO.getInstance();
                selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                selecionarCliente.setTipoReserva("Hospedar");

                modeloSelecionarCliente = ModeloSelecionarCliente.getInstance();
                modeloSelecionarCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceSelecionarCliente.setVisible(true);

            } else {
                selecionarCliente.setTipoReserva("Hospedar");
                modeloSelecionarCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceSelecionarCliente.setVisible(true);
            }

            //------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[26]) {//Acionado pelo bot??o Hospedagem da classe "TelaPrincipal".
            if (interfaceConsultarHospedagem == null || interfaceConsultarHospedagem.isValid()) {
                interfaceConsultarHospedagem = InterfaceFactory.getInterface(CONSULTAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Aciona o m??todo para listas todas as hospedagens.
                reservaDAO = ReservaDAO.getInstance();
                modeloHospedagem = ModeloHospedagem.getInstance();
                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();

            } else {
                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);

                QuartoDAO quartoDAO = QuartoDAO.getInstance();
                quartoDAO.atualizarQuarto();
            }

            //-----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[27]) {//Acionado pelo bot??o editar da classe "EditarHospedagem".
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();

            editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Caso o campo, forma de pagamento estiver em braqnco, significa que n??o h?? reserva naquele nome de cliente.
            try {
                if (editarHospedagem.getHospedagem().getSaida() == null) {
                    JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada. Certifique-se se o cliente est?? hospedado.");

                } else {

                    //Verifica se as datas s??o v??lidas dentro da regra de neg??cio.
                    if (validarData.parse(validarData.format(editarHospedagem.getHospedagem().getSaida())).before(validarData.parse(validarData.format(dataAtual)))
                            || editarHospedagem.getHospedagem().getEntrada().after(editarHospedagem.getHospedagem().getSaida())
                            || validarData.format(editarHospedagem.getHospedagem().getEntrada()).equals(validarData.format(editarHospedagem.getHospedagem().getSaida()))) {
                        JOptionPane.showMessageDialog(editarHospedagem, "Data para reserva inv??lida");
                    } else {

                        //Verifica se a reserva n??o esbarra em outra reserva.
                        DTOQuarto quartoVerificar = quartoDAO.getQuartoPorID(editarHospedagem.getHospedagem());
                        if (!quartoDAO.checarDisponibilidadeData(editarHospedagem.getHospedagem(), quartoVerificar)) {
                            JOptionPane.showMessageDialog(null, "Este quarto n??o est?? dispon??vel para este per??odo.");
                        } else {
                            boolean editou = false;
                            editou = reservaDAO.editarHospedagem(editarHospedagem.getHospedagem());

                            if (editou != false) {
                                //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                                DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                                historicoDAO = HistoricoDAO.getInstance();
                                historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                                historicoReserva.setLogin(usuarioLogado.getLogin());
                                historicoReserva.setAcao("Editou");
                                historicoReserva.setTipo_reserva("HOSPEDAR");

                                //Obter o ID da reserva.
                                historicoReserva.setId_reserva(editarHospedagem.getHospedagem().getId_reserva());
                                historicoReserva.setCliente_reservado(editarHospedagem.getCliente().getNome());

                                //Salva na tabela.
                                historicoDAO.salvarManterHospedagem(historicoReserva);

                                editarHospedagem.dispose();
                                editarHospedagem.limparCampos();
                                modeloHospedagem = ModeloHospedagem.getInstance();
                                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                            }
                        }
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada.");
            }

            quartoDAO.atualizarQuarto();
            //----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[28]) {//Pesquisar AuditarProduto
            AuditarProduto auditarProduto = AuditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarProduto = ModeloAuditarProduto.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();
            
            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarProduto.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarProduto.adicionarLista(historicoDAO.auditarProduto());
                
            } else {
                 modeloAuditarProduto.adicionarLista(historicoDAO.auditarProdutoUsuario(usuario.getId_usuario()));
            }


            //----------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[29]) {//Acionado pelo bot??o "Hospedar" da classe "CadastrarHospedagem".

            clienteDAO = ClienteDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");

            //Saber se procura por CPF ou Passaporte
            if (cadastrarHospedagem.getCliente().getCpf().equals("   .   .   -  ")) {
                //Procurar por passaporte.
                cliente = clienteDAO.consultarClientePassaporte(cadastrarHospedagem.getCliente());
            } else {
                cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente());
            }

            //Verifica se o cliente est?? cadastrado
            if (cliente == null) {
                JOptionPane.showMessageDialog(null, "Cliente n??o encontrado.");

            } else {

                try {//Verifica se h?? campos em branco antes de hospedar.
                    if (cadastrarHospedagem.getHospedagem().getSaida() == null) {//Verifica se h?? campos obrigat??rios em branco.
                        JOptionPane.showMessageDialog(null, "H?? campos em branco.");

                    } else {

                        //Verifica se a data esta dentro do per??odo estipulado na regra de neg??cio
                        if (cadastrarHospedagem.getHospedagem().getEntrada().before(dataAtual) || cadastrarHospedagem.getHospedagem().getSaida().before(dataAtual)
                                || cadastrarHospedagem.getHospedagem().getEntrada().after(cadastrarHospedagem.getHospedagem().getSaida())
                                || validarData.format(cadastrarHospedagem.getHospedagem().getEntrada()).equals(validarData.format(cadastrarHospedagem.getHospedagem().getSaida()))) {
                            JOptionPane.showMessageDialog(null, "Data para hospedagem inv??lida.");

                        } else {

                            if (interfaceSelecionarQuarto == null || interfaceSelecionarQuarto.isValid()) {//Exibe a classe para selecionar o quarto.
                                interfaceSelecionarQuarto = InterfaceFactory.getInterface(SELECIONAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                                //Aciona o m??todo para listar todos os quartos.
                                quartoDAO = QuartoDAO.getInstance();
                                modeloSelecionarQuarto = ModeloSelecionarQuarto.getInstance();
                                DTOReserva reserva = cadastrarHospedagem.getHospedagem();
                                modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuartoSelecionar(reserva.getEntrada(), reserva.getSaida()));

                                //Informa o tipo de reserva.
                                selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                                selecionarQuarto.setTipoReserva("HOSPEDAR");

                                interfaceSelecionarQuarto.setVisible(true);
                            } else {
                                //Informa o tipo de reserva.
                                selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                                selecionarQuarto.setTipoReserva("HOSPEDAR");
                                DTOReserva reserva = cadastrarHospedagem.getHospedagem();
                                modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuartoSelecionar(reserva.getEntrada(), reserva.getSaida()));
                                interfaceSelecionarQuarto.setVisible(true);
                            }
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
                }
            }

            quartoDAO.atualizarQuarto();
            //-----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[30]) {//Acinado pelo bot??o "Fechar Hospedagem" da classe "TelaPrincipal"
            if (interfaceFecharHospedagem == null || interfaceFecharHospedagem.isValid()) {
                interfaceFecharHospedagem = InterfaceFactory.getInterface(FECHAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceFecharHospedagem.setVisible(true);
                modeloPasseiosRealizados = ModeloPasseiosRealizados.getInstance();
                modeloProdutosConumidos = ModeloProdutosConsumidos.getInstance();

                modeloProdutosConumidos.limparDados();
                modeloPasseiosRealizados.limparDados();
            } else {
                fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                fecharHospedagem.limparCampos();
                modeloPasseiosRealizados.limparDados();
                modeloProdutosConumidos.limparDados();
                interfaceFecharHospedagem.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[31]) {//Acionado pelo bot??es entrando ou saindo da classe "TelaPrincipal.
            if (interfaceEntrandoOuSaindo == null || interfaceEntrandoOuSaindo.isValid()) {
                interfaceEntrandoOuSaindo = InterfaceFactory.getInterface(ENTRANDO_OU_SAINDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Preenche a tabela da classe "EntrandoOuSaindo".
                //Primeiro a tabela com os clientes entrando.
                entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
                modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
                modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());

                //Depois, as tabelas com os cliente saindo.
                modeloSaindoHoje = ModeloSaindoHoje.getInstance();
                modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                interfaceEntrandoOuSaindo.setVisible(true);

            } else {
                modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());
                modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                interfaceEntrandoOuSaindo.setVisible(true);
            }

            //---------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[32]) {//Acionado pelo bot??es saindo ou saindo da classe "TelaPrincipal.
            if (interfaceEntrandoOuSaindo == null || interfaceEntrandoOuSaindo.isValid()) {
                interfaceEntrandoOuSaindo = InterfaceFactory.getInterface(ENTRANDO_OU_SAINDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Preenche a tabela da classe "EntrandoOuSaindo".
                //Primeiro a tabela com os clientes entrando.
                entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
                modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
                modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());

                //Depois, as tabelas com os cliente saindo.
                modeloSaindoHoje = ModeloSaindoHoje.getInstance();
                modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                interfaceEntrandoOuSaindo.setVisible(true);

            } else {
                modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());
                modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                interfaceEntrandoOuSaindo.setVisible(true);
            }
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[33]) {
            //Acionado pelo bot??o "Pesquisar" da classe "ConsultarUsuario".
            usuarioDAO = UsuarioDAO.getInstance();
            consultarUsuario = ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloUsuario = ModeloUsuario.getInstance();

            try {
                if (consultarUsuario.getPesquisa().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");

                } else {
                    //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                    DTOUsuario usuario;
                    usuario = usuarioDAO.consultarUsuario(consultarUsuario.getPesquisa());
                    
                    

                    //Se o usu??rio n??o for encontrado.
                    if(usuario.getLogin().equals("")){
                        JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                        usuario = null;
                    } else {
                    //Atualizando janela.
                    modeloUsuario.adicionar(usuarioDAO.consultarUsuario(consultarUsuario.getPesquisa()));
                    interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    interfaceConsultarUsuario.setVisible(true);
                    usuario = null;
                    }
                }
            } catch (Exception erro) {

            }

            //-------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[34]) {//Bot??o Adicionar da clase "ConsultaUsuario".
            if (interfaceCadastrarUsuario == null || interfaceCadastrarUsuario.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceCadastrarUsuario = InterfaceFactory.getInterface(CADASTRAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceCadastrarUsuario.setVisible(true);

            } else {
                cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarUsuario.limparCampos();
                interfaceCadastrarUsuario.setVisible(true);
            }

            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[35]) {//Bot??o pesquisar da classe "ConsultarCliente".
            clienteDAO = ClienteDAO.getInstance();
            consultarCliente = ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloCliente = ModeloCliente.getInstance();

            try {
                if (consultarCliente.getPesquisa().getCpf().equals("   .   .   -  ")
                        && consultarCliente.getPesquisa().getNome().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");

                } else {

                    if (!consultarCliente.getPesquisa().getCpf().equals("   .   .   -  ") && consultarCliente.getPesquisa().getNome().equals("")) {
                        
                        DTOCliente cliente;
                        cliente = clienteDAO.consultarCliente(consultarCliente.getPesquisa());
                        
                        if(cliente.getNome().equals("")){
                            JOptionPane.showMessageDialog(null, "Cliente n??o encontrado.");
                            cliente = null;
                            
                        } else {
                            //Significa que procurar?? por CPF
                            modeloCliente.adicionar(clienteDAO.consultarCliente(consultarCliente.getPesquisa()));
                            cliente = null;
                        }
                        

                    } else { //Procurar?? por nome.
                        ArrayList<DTOCliente> clientes;
                        clientes = clienteDAO.ConsultarClienteNome(consultarCliente.getPesquisa());
                        
                        if(clientes.size() <=0){
                            JOptionPane.showMessageDialog(null, "Cliente n??o encontrado.");
                            clientes = null;
                        } else {
                            //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                            modeloCliente.adicionarLista(clienteDAO.ConsultarClienteNome(consultarCliente.getPesquisa()));
                            clientes = null;
                        }
                        
                    }
                    //Atualizando janela.
                    interfaceConsultarCliente = InterfaceFactory.getInterface(CONSULTAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    interfaceConsultarCliente.setVisible(true);
                }
            } catch (Exception erro) {

            }

            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[36]) {//Bot??o listar da classe "ConsultarCliente".
            //Alimenta a tabela com uma lista vindo do m??todo "ListarUsuario" da classe "usuarioDAO".
            modeloCliente.adicionarLista(clienteDAO.listarCliente());
            interfaceConsultarCliente.setVisible(true);

            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[37]) {//Bot??o pesquisar da classe consultarReserva.
            try {
                consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                if (consultarReserva.getPesquisa().getCpf().equals("   .   .   -  ")
                        && consultarReserva.getPesquisa().getPassaport().equals("")) {
                    new Exception();

                } else {
                    reservaDAO = ReservaDAO.getInstance();
                    consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    modeloReserva = ModeloReserva.getInstance();
                    //Obtendo cliente.
                    clienteDAO = ClienteDAO.getInstance();
                    DTOCliente cliente = null;

                    if (!consultarReserva.getPesquisa().getCpf().equals("   .   .   -  ") && consultarReserva.getPesquisa().getPassaport().equals("")) {
                        
                        //Significa que procurar?? por CPF
                        cliente = clienteDAO.consultarCliente(consultarReserva.getPesquisa());

                    } else { //Procurar?? por nome.
                        //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                        cliente = clienteDAO.consultarClientePassaporte(consultarReserva.getPesquisa());
                    }
                    
                    DTOReserva reserva;
                    reserva = reservaDAO.consultarReserva(cliente.getId_cliente());
                    
                    if(reserva.getId_reserva() == 0){
                        JOptionPane.showMessageDialog(null, "Reserva n??o encontrada.");
                        reserva = null;
                    } else {
                        modeloReserva.adicionar(reservaDAO.consultarReserva(cliente.getId_cliente()));

                    //Adicionando o quarto pesquisado a tabela que exibe os quartos.
                    modeloReserva = ModeloReserva.getInstance();
                    modeloReserva.adicionar(reservaDAO.pesquisarReserva(cliente));
                        reserva = null;
                    }

                    

                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");
            }

        } else if (evento.getSource() == botoes[38]) {//Bot??o Listar da classe consultrReserva.
            //Alimenta a tabela com uma lista vindo do m??todo "ListarUsuario" da classe "usuarioDAO".
            modeloReserva.adicionarLista(reservaDAO.listarReservas());
            interfaceConsultarReserva.setVisible(true);

        } else if (evento.getSource() == botoes[39]) {//Bot??o pesquisar da classe consultarHospedagem.
            //Primeiro pega o cliente.
            clienteDAO = ClienteDAO.getInstance();
            consultarHospedagem = ConsultarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            DTOCliente cliente = null;

            if (!consultarHospedagem.getPesquisa().getCpf().equals("   .   .   -  ") && consultarHospedagem.getPesquisa().getPassaport().equals("")) {
                //Significa que procurar?? por CPF
                cliente = clienteDAO.consultarCliente(consultarHospedagem.getPesquisa());

            } else { //Procurar?? por nome.
                //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                cliente = clienteDAO.consultarClientePassaporte(consultarHospedagem.getPesquisa());
            }

            DTOReserva reserva;
            reserva = reservaDAO.pesquisarHospedagem(cliente);
            
            if(reserva.getId_reserva() == 0){
                JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada.");
                reserva = null;
                
            } else {
                //Agora, procura a reserva pelo cliente.
            reservaDAO = ReservaDAO.getInstance();
            modeloHospedagem.adicionar(reservaDAO.pesquisarHospedagem(cliente));

            interfaceConsultarHospedagem = InterfaceFactory.getInterface(CONSULTAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            interfaceConsultarHospedagem.setVisible(true);
            reserva = null;
            }
            

            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[40]) {//Bot??o listar da classe consultarHospedagem.
            //Alimenta a tabela com uma lista vindo do m??todo "ListarUsuario" da classe "usuarioDAO".
            modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
            interfaceConsultarHospedagem.setVisible(true);

            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[41]) {//Acionado pelo bot??o Fechar da classe Fechar Hospedagem.
            fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();

            Document comanda = new Document();

            try {
                PdfWriter.getInstance(comanda, new FileOutputStream("Comanda.pdf"));
                PdfPTable tabelaProdutos = new PdfPTable(4);
                PdfPTable tabelaPasseios = new PdfPTable(4);

                comanda.open();
                comanda.add(new Paragraph("Sistema Hospede"));
                comanda.add(new Paragraph("Comanda"));
                comanda.add(new Paragraph("Data: " + formatarData.format(new Date())));
                comanda.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

                if (!fecharHospedagem.getCliente().getCpf().equals("")) {
                    comanda.add(new Paragraph("CPF / Passaporte: " + fecharHospedagem.getCliente().getCpf()));
                } else {
                    comanda.add(new Paragraph("CPF / Passaporte: " + fecharHospedagem.getCliente().getPassaport()));
                }

                comanda.add(new Paragraph("Cliente: " + fecharHospedagem.getCliente().getNome()));
                comanda.add(new Paragraph("Di??ria :" + fecharHospedagem.getTxtDiaria()));
                comanda.add(new Paragraph("Dias Hospedados: " + fecharHospedagem.getTxtDiasHospedados()));
                comanda.add(new Paragraph("Quarto: " + fecharHospedagem.getTxtQuarto()));
                comanda.add(new Paragraph("Total Hospedagem R$:" + fecharHospedagem.getTxtTotalHospedagem()));
                comanda.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));
                comanda.add(new Paragraph("                 PRODUTOS CONSUMIDOS"));
                tabelaProdutos.addCell("C??DIGO");
                tabelaProdutos.addCell("NOME");
                tabelaProdutos.addCell("QUANTIDADE");
                tabelaProdutos.addCell("PRE??O");
                for (int posicao = 0; posicao < fecharHospedagem.getModeloProdutos().getProdutos().size(); posicao++) {
                    tabelaProdutos.addCell(Integer.toString(fecharHospedagem.getModeloProdutos().getProduto(posicao).getId()));
                    tabelaProdutos.addCell(fecharHospedagem.getModeloProdutos().getProduto(posicao).getNome());
                    tabelaProdutos.addCell(Integer.toString(fecharHospedagem.getModeloProdutos().getProduto(posicao).getQuantidade()));
                    tabelaProdutos.addCell(Double.toString(fecharHospedagem.getModeloProdutos().getProduto(posicao).getPreco()));
                   
                }
                comanda.add(tabelaProdutos);
                comanda.add(new Paragraph(""));
                comanda.add(new Paragraph("Total produtos R$: " + fecharHospedagem.getTxtTotalProduto()));
                comanda.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));
                comanda.add(new Paragraph("                PASSEIOS REALIZADOS"));
                tabelaPasseios.addCell("PASSEIO");
                tabelaPasseios.addCell("NOME");
                tabelaPasseios.addCell("CPF/PASSA.");
                tabelaPasseios.addCell("INGRESSO");
                
                for (int posicao = 0; posicao < fecharHospedagem.getModeloPasseios().getPassageiros().size(); posicao++) {
                    tabelaPasseios.addCell(fecharHospedagem.getModeloPasseios().getPassageiro(posicao).getTituloPasseio());
                    tabelaPasseios.addCell(fecharHospedagem.getModeloPasseios().getPassageiro(posicao).getNome_passageiro());
                    tabelaPasseios.addCell(fecharHospedagem.getModeloPasseios().getPassageiro(posicao).getCpf());
                    tabelaPasseios.addCell(Double.toString(fecharHospedagem.getModeloPasseios().getPassageiro(posicao).getIngresso()));
        
                }
                comanda.add(tabelaPasseios);
                comanda.add(new Paragraph(""));
                comanda.add(new Paragraph("Total passeios R$: " + fecharHospedagem.getTxtTotalPasseio()));
                comanda.add(new Paragraph("Total a pagar R$: " + fecharHospedagem.getTxtTotalPagar()));

            } catch (Exception erro) {
                JOptionPane.showConfirmDialog(null, erro.getMessage());
            } finally {
                comanda.close();
            }

            try {
                reservaDAO.editarHospedagemFechamento(fecharHospedagem.getHospedagem());
                quartoDAO.editarQuarto(fecharHospedagem.getQuarto());

                Desktop.getDesktop().open(new File("Comanda.pdf"));

                fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                fecharHospedagem.dispose();

                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);

                //---------------------------------------------------------------------------------------------------------------------------------------------------------
            } catch (IOException ex) {
                Logger.getLogger(ControlarEventos.class.getName()).log(Level.SEVERE, null, ex);
            }

            quartoDAO.atualizarQuarto();
        } else if (evento.getSource() == botoes[42]) {//Acionado pelo bot??o Passeios da clase Tela principal
            if (interfaceSelecionarPasseioConsulta == null || interfaceSelecionarPasseioConsulta.isValid()) {
                interfaceSelecionarPasseioConsulta = InterfaceFactory.getInterface(CONSULTAR_PASSEIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                modeloPasseio = ModeloPasseio.getInstance();
                passeioDAO = PasseioDAO.getInstance();

                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);

            } else {
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            }

            //---------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[43]) {//Acionado pelo bot??o Consultar Passeios da classe Tela Principal.
            if (interfaceSelecionarPasseioConsulta == null || interfaceSelecionarPasseioConsulta.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceSelecionarPasseioConsulta = InterfaceFactory.getInterface(SELECIONAR_PASSEIOS_CONSULTA, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                passeioDAO = PasseioDAO.getInstance();

                modeloPasseio = ModeloPasseio.getInstance();
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            } else {
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            }
            //---------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[44]) {
            //Acionado pelo menu Consultar Produto da classe Tela Principal.
            if (interfaceConsultarProduto == null || interfaceConsultarProduto.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                produtoDAO = ProdutoDAO.getInstance();
                interfaceConsultarProduto = InterfaceFactory.getInterface(CONSULTAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Adiciono a lista de produtos do banco a tabela da classe ModeloProduto.
                modeloProduto = ModeloProduto.getInstance();
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);

            } else {
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            }
            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[45]) {
            //Acionado pelo menu Pedido de DTOQuarto da classe Tela Principal.
            if (interfaceCadastraPedidoQuarto == null || interfaceCadastraPedidoQuarto.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceCadastraPedidoQuarto = InterfaceFactory.getInterface(PEDIDO_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
                modeloPedidosDeQuarto.limparDados();
                cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarPedidoQuarto.limparCampos();
                interfaceCadastraPedidoQuarto.setVisible(true);
                cadastrarPedidoQuarto.setCampos();
            } else {
                cadastrarPedidoQuarto.limparCampos();
                modeloPedidosDeQuarto.limparDados();
                cadastrarPedidoQuarto.setCampos();
                interfaceCadastraPedidoQuarto.setVisible(true);
            }
            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[47]) {
            //Acionado pelo bot??o cadastrar da classe CadastrarProduto
            cadastrarProduto = CadastrarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            DTOProduto produto;
            produto = cadastrarProduto.getProduto();
            produtoDAO = ProdutoDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();

            //Verifica se o produto j?? existe
            if (produtoDAO.consultarProduto(cadastrarProduto.getProduto().getNome()).getEntrega() != null) {
                JOptionPane.showMessageDialog(null, "Este produto j?? existe.");
            } else {
                boolean cadastrou = false;
                cadastrou = produtoDAO.cadastrarProduto(produto);

                try {

                    if (cadastrou != false) {
                        DTOHistoricoProduto historicoProduto = new DTOHistoricoProduto();
                        DTOProduto produtoCadastrar = produtoDAO.consultarProduto(cadastrarProduto.getProduto().getNome());
                        historicoProduto.setId_produto(produtoCadastrar.getId());
                        historicoProduto.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                        historicoProduto.setAcao("Cadastrou");
                        historicoProduto.setProduto(produtoCadastrar.getNome());

                        historicoDAO.salvarManterProduto(historicoProduto);
                    }
                } catch (Exception erro) {

                }
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                cadastrarProduto.limparCampos();
                cadastrarProduto.dispose();
                modeloProduto = ModeloProduto.getInstance();
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[46]) {//Pesquisar AuditarPasseio
            AuditarPasseio auditarPasseio = AuditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            historicoDAO = HistoricoDAO.getInstance();
            modeloAuditarPasseio = ModeloAuditarPasseio.getInstance();
            usuarioDAO = UsuarioDAO.getInstance();
            
            DTOUsuario usuario = usuarioDAO.consultarUsuario(auditarPasseio.getUsuario());
            
            if(usuario.getId_usuario() == 0){
                JOptionPane.showMessageDialog(null, "Usu??rio n??o encontrado.");
                modeloAuditarPasseio.adicionarLista(historicoDAO.auditarPasseio());
                
            } else {
                 modeloAuditarPasseio.adicionarLista(historicoDAO.auditarPasseioUsuario(usuario.getId_usuario()));
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[48]) {
            //Acionado pelo bot??o Editar da classe EditarProduto
            editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            produtoDAO = ProdutoDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();

            boolean editou = false;
            editou = produtoDAO.editarProduto(editarProduto.getProduto());

            try {
                if (editou != false) {
                    DTOHistoricoProduto historicoProduto = new DTOHistoricoProduto();
                    DTOProduto produtoEditar = produtoDAO.consultarProduto(editarProduto.getProduto().getNome());
                    historicoProduto.setId_produto(produtoEditar.getId());
                    historicoProduto.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    historicoProduto.setAcao("Editou");
                    historicoProduto.setProduto(produtoEditar.getNome());

                    historicoDAO.salvarManterProduto(historicoProduto);
                }
            } catch (Exception erro) {

            }

            editarProduto.limparCampos();
            editarProduto.dispose();
            modeloProduto = ModeloProduto.getInstance();
            modeloProduto.adicionarLista(produtoDAO.listarProduto());

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[49]) {

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[50]) {
            //Acionado pelo bot??o Cadastrar da classe CadastrarPasseio
            cadastrarPasseio = CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            passeioDAO = PasseioDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();

            //Verifica se o passeio j?? existe.
            if (passeioDAO.consultarPasseio(cadastrarPasseio.getPasseio().getTitulo()).getData() != null) {
                JOptionPane.showMessageDialog(null, "Este passeio j?? existe.");
            } else {
                boolean cadastrou = false;
                cadastrou = passeioDAO.cadastrarPasseio(cadastrarPasseio.getPasseio());

                try {
                    if (cadastrou != false) {
                        DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                        DTOPasseio passeioCadastrar = passeioDAO.consultarPasseio(cadastrarPasseio.getPasseio().getTitulo());
                        historicoPasseio.setId_passeio(passeioCadastrar.getId());
                        historicoPasseio.setPasseio(passeioCadastrar.getTitulo());
                        historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                        historicoPasseio.setAcao("Cadastrou");

                        historicoDAO.salvarManterPasseio(historicoPasseio);
                    }
                } catch (Exception erro) {

                }

                cadastrarPasseio.limparCampos();
                cadastrarPasseio.dispose();

                modeloPasseio = ModeloPasseio.getInstance();
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[51]) {
            //Acionado pelo bot??o Editar da classe EditarPasseio
            editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            passeioDAO = PasseioDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();

            boolean editou = false;
            editou = passeioDAO.editarPasseio(editarPasseio.getPasseio());

            try {
                if (editou != false) {
                    DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                    DTOPasseio passeioEditar = passeioDAO.consultarPasseio(editarPasseio.getPasseio().getTitulo());
                    historicoPasseio.setId_passeio(passeioEditar.getId());
                    historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    historicoPasseio.setPasseio(passeioEditar.getTitulo());
                    historicoPasseio.setAcao("Editou");

                    historicoDAO.salvarManterPasseio(historicoPasseio);
                }
            } catch (Exception erro) {

            }

            editarPasseio.limparCampos();
            editarPasseio.dispose();

            modeloPasseio = ModeloPasseio.getInstance();
            modeloPasseio.adicionarLista(passeioDAO.listarPasseio());

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[52]) {

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[53]) {
            //Acionado pelo bot??o Selecionar da classe SelecionarPasseioCadastrarPasseio
            selecionarPasseioCadastrarPassageiro = SelecionarPasseioCadastrarPassageiro.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Enviando o passeio escolido para a classe que adiciona passeio.
            adicionarPassageiroPasseio.receberPasseio(selecionarPasseioCadastrarPassageiro.getPasseio());
            adicionarPassageiroPasseio.setCampos();

            selecionarPasseioCadastrarPassageiro.dispose();
            adicionarPassageiroPasseio.setVisible(true);
            modeloPassageiros = ModeloPassageiros.getInstance();
            modeloPassageiros.limparDados();

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[54]) {
            //Acionado pelo bot??o Adicionar da classe AdicionarPassageiroPasseio
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloPassageiro = ModeloPassageiros.getInstance();
            passageiroDAO = PassageiroDAO.getInstance();
            passeioDAO = PasseioDAO.getInstance();

            //Verifica se CPF j?? existe.
            if (verificarCPF_Passaporte()) {
                modeloPassageiro.adicionar(adicionarPassageiroPasseio.getPassageiro());
                adicionarPassageiroPasseio.limparPassageiros();
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[55]) {
            //Acionado pelo bot??o Remover da classe AdicionarPassageiroPasseio
            //Obtendo linha para remover
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloPassageiro = ModeloPassageiros.getInstance();
            modeloPassageiro.remover(adicionarPassageiroPasseio.getPassageiroRevomer());

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[56]) {
            //Acionado pelo bot??o Cadastrar da classe AdicionarPassageiroPasseio
            passageiroDAO = PassageiroDAO.getInstance();
            modeloPassageiro = ModeloPassageiros.getInstance();

            for (int posicao = 0; posicao < modeloPassageiro.getPassageiros().size(); posicao++) {
                passageiroDAO.cadastrarPassageiro(modeloPassageiro.getPassageiro(posicao));
            }

            adicionarPassageiroPasseio.dispose();
            adicionarPassageiroPasseio.limparDados();
            modeloPassageiro.limparDados();

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[57]) {
            //Acionado pelo Remover da classe Passeios

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[58]) {
            //Acionado pelo Adicionar da classe CadastrarPedidoQuarto
            if (interfaceProdutoPedido == null || interfaceProdutoPedido.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceProdutoPedido = InterfaceFactory.getInterface(PRODUTO_PEDIDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                produtoPedido = ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                produtoPedido.limparDados();
                produtoDAO = ProdutoDAO.getInstance();
                modeloProdutoPedido = ModeloProdutoPedido.getInstance();
                modeloProdutoPedido.adicionarLista(produtoDAO.listarProduto());
                interfaceProdutoPedido.setVisible(true);

            } else {
                 produtoPedido.limparDados();
                modeloProdutoPedido.adicionarLista(produtoDAO.listarProduto());
                interfaceProdutoPedido.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[59]) {
            //Acionado pelo Selecionar da classe CadastrarPedidoQuarto
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
            produtoPedido = ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloProdutoPedido = ModeloProdutoPedido.getInstance();
            DTOProduto produtoOriginal, produtoAdicionar;

            produtoOriginal = modeloProdutoPedido.getProduto(produtoPedido.getLinhaSelecionada());
            int QuantidadePedida = produtoPedido.getQuantidade();
            
            if ((produtoOriginal.getQuantidade() - QuantidadePedida) <= 0) {
                JOptionPane.showMessageDialog(null, "Produto com saldo negativo.");

            } else {

                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja confirmar este consumo?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {

                    //Subtrai do banco.
                    try {
                        produtoDAO = ProdutoDAO.getInstance();
                        modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
                        cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        DTOProduto produtoParaSubtrair, produtoPedidoHospedagem;

                        

                        //Subtraindo a quantidade do produto pedido pela quantidade que existe no banco de dados.
                        produtoParaSubtrair = produtoDAO.consultarProduto(modeloProdutoPedido.getProduto(produtoPedido.getLinhaSelecionada()).getNome());
                        produtoPedidoHospedagem = modeloProdutoPedido.getProduto(produtoPedido.getLinhaSelecionada());

                        //Subtraindo a quantidade.
                        produtoParaSubtrair.setQuantidade(produtoParaSubtrair.getQuantidade() - QuantidadePedida);
                        produtoDAO.editarProduto(produtoParaSubtrair);

                        produtoOriginal.setQuantidade(produtoOriginal.getQuantidade() - QuantidadePedida);

                        //Alterando o valor do produto para joga-lo na tabela de produtos pedidos.
                        produtoOriginal.setQuantidade(QuantidadePedida);
                        modeloPedidosDeQuarto.adicionar(produtoOriginal);

                        modeloProdutoPedido.fireTableDataChanged();
                        produtoPedido.dispose();
                        cadastrarPedidoQuarto.setVisible(true);
                        
                        produtoDAO.cadastrarProdutoPedido(cadastrarPedidoQuarto.getReserva(), produtoOriginal);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao gravar consumo no banco: " + erro.getMessage());
                    }

                }

            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[60]) {
            //Acionado pelo Remover da classe ConsultarPedidoQuarto
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
            consultarPedidoQuarto = ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            produtoDAO = ProdutoDAO.getInstance();

            produtoDAO.deletarProduto(modeloPedidosDeQuarto.getProduto(consultarPedidoQuarto.getIndiceRemover()));
            modeloPedidosDeQuarto.remover(consultarPedidoQuarto.getIndiceRemover());

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[61]) {
            //Acionado pelo Fechar da classe CadastrarPedidoQuarto
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            try{
                FileOutputStream arquivo = new FileOutputStream("pedido.txt");
                PrintWriter escrever = new PrintWriter(arquivo);
                
                escrever.println("Sistema Hospede");
                escrever.println("Pedido");
                escrever.println("Data: "+ formatarData.format(new Date()));
                escrever.println("");
                escrever.println("-----------------------------------------");
                escrever.println("");
                escrever.println("Quarto: "+cadastrarPedidoQuarto.getReserva().getNumero_quarto());
                escrever.println("Nome: "+cadastrarPedidoQuarto.getHospede().getNome());
                escrever.println("Celular: "+cadastrarPedidoQuarto.getHospede().getCelular());
                escrever.println("");
                escrever.println("-----------------------------------------");
                escrever.println("");
                escrever.println("ITENS");
                
                ModeloPedidosDeQuarto modeloPedidoDeQuarto = ModeloPedidosDeQuarto.getInstance();
                
                for(int posicao = 0; posicao<modeloPedidoDeQuarto.getPedidosDeQuarto().size();posicao++){
                escrever.print("Produto: "+modeloPedidoDeQuarto.getProduto(posicao).getNome()+"  "); 
                escrever.print("Quantidade: "+modeloPedidoDeQuarto.getProduto(posicao).getQuantidade()+" ");
                escrever.print("Pre??o: "+modeloPedidoDeQuarto.getProduto(posicao).getPreco()+" "); 
                escrever.println(""); 
                }
                
                escrever.close();
                arquivo.close();
                
                Desktop.getDesktop().open(new File("pedido.txt"));
                
            }catch(Exception erro){
                
            }
            cadastrarPedidoQuarto.dispose();
            cadastrarPedidoQuarto.limparCampos();
            modeloPedidosDeQuarto.limparDados();

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[62]) {
            //Acionado pelo Pesquiar da classe ConsultarQuarto

            try {
                consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                if (consultarQuarto.getPesquisa() == 0) {
                    new Exception();

                } else {
                    quartoDAO = QuartoDAO.getInstance();
                    consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    modeloQuarto = ModeloQuarto.getInstance();

                    DTOQuarto quarto;
                    quarto = quartoDAO.consultarQuarto(consultarQuarto.getPesquisa());
                    
                    if(quarto.getNumero().equals("")){
                        JOptionPane.showMessageDialog(null, "Quarto n??o encontrado.");
                    } else {
                    //Adicionando o quarto pesquisado a tabela que exibe os quartos.
                    modeloQuarto.adicionar(quartoDAO.consultarQuarto(consultarQuarto.getPesquisa()));

                    //Atualizando janela.
                    interfaceConsultarQuarto = InterfaceFactory.getInterface(CONSULTAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    interfaceConsultarQuarto.setVisible(true);
                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");
            }

            //--------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[63]) {
            //Evento acionado pelo Cadastrar da classe "ConsultarQuarto".
            if (interfaceCadastrarQuarto == null || interfaceCadastrarQuarto.isValid()) {
                interfaceCadastrarQuarto = InterfaceFactory.getInterface(CADASTRAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceCadastrarQuarto.setVisible(true);

            } else {
                cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarQuarto.limparCampos();
                interfaceCadastrarQuarto.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[64]) {
            //Evento acionado pelo Produto da classe "TelaPrincipal".
            if (interfaceConsultarProduto == null || interfaceConsultarProduto.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceConsultarProduto = InterfaceFactory.getInterface(CONSULTAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                modeloProduto = ModeloProduto.getInstance();
                produtoDAO = ProdutoDAO.getInstance();

                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            } else {
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[65]) {
            //Evento acionado pelo Cadastrar da classe "ConsultarProduto".
            if (interfaceCadastrarProdutos == null) {
                interfaceCadastrarProdutos = InterfaceFactory.getInterface(CADASTRAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceCadastrarProdutos.setVisible(true);
            } else {
                interfaceCadastrarProdutos.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[66]) {
            //Evento acionado pelo Pesquisar da classe "ConsultarProduto".
            produtoDAO = ProdutoDAO.getInstance();
            consultarProduto = ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloProduto = ModeloProduto.getInstance();

            try {
                if (consultarProduto.getPesquisa().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");

                } else {
                    DTOProduto produto;
                    produto = produtoDAO.consultarProduto(consultarProduto.getPesquisa());
                    
                    if(produto.getId() == 0){
                        JOptionPane.showMessageDialog(null, "Produto n??o encontrado.");
                        produto = null;
                    } else {
                        //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                    modeloProduto.adicionar(produtoDAO.consultarProduto(consultarProduto.getPesquisa()));
                    produto = null;
                    }
                    

                }
            } catch (Exception erro) {

            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[67]) {
            //Evento acionado pelo cADASTRAR da classe "ConsultarPasseio".
            if (interfaceCadastrarPasseios == null || interfaceCadastrarPasseios.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                interfaceCadastrarPasseios = InterfaceFactory.getInterface(CADASTRAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceCadastrarPasseios.setVisible(true);
            } else {
                interfaceCadastrarPasseios.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[68]) {
            //Evento acionado pelo Pesquisar da classe "ConsultarPasseio".
            passeioDAO = PasseioDAO.getInstance();
            modeloPasseio = ModeloPasseio.getInstance();
            consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                if (consultarPasseio.getPesquisa().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");
                } else {
                    
                    DTOPasseio passeio;
                    passeio = passeioDAO.consultarPasseio(consultarPasseio.getPesquisa());
                    
                    if(passeio.getId() == 0){
                        JOptionPane.showMessageDialog(null, "Passeio n??o encontrado.");
                        passeio = null;
                    } else {
                    modeloPasseio.adicionar(passeioDAO.consultarPasseio(consultarPasseio.getPesquisa()));
                    passeio = null;
                    }
                }
            } catch (Exception erro) {

            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[68]) {
            //Evento acionado pelo Pesquisar da classe "ConsultarPasseio".
            passeioDAO = PasseioDAO.getInstance();
            modeloPasseio = ModeloPasseio.getInstance();
            consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                if (consultarPasseio.getPesquisa().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");
                } else {
                    modeloPasseio.adicionar(passeioDAO.consultarPasseio(consultarPasseio.getPesquisa()));
                }
            } catch (Exception erro) {

            }

            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[69]) {
            //Evento acionado pelo iten de menu "ADICIONAR PASSAGEIRO" da classe "ConsultarHospedagem"
            if (interfaceSelecionarPasseioCadastrarPassageiro == null || interfaceSelecionarPasseioCadastrarPassageiro.isValid()) {
                interfaceSelecionarPasseioCadastrarPassageiro = InterfaceFactory.getInterface(SELECIONAR_PASSEIO_CADASTRAR_PASSAGEIRO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceSelecionarPasseioCadastrarPassageiro.setVisible(true);
                passeioDAO = PasseioDAO.getInstance();
                modeloSelecionarPasseio = ModeloSelecionarPasseio.getInstance();
                modeloSelecionarPasseio.adicionarLista(passeioDAO.listarPasseio());

            } else {

                modeloSelecionarPasseio = ModeloSelecionarPasseio.getInstance();
                modeloSelecionarPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioCadastrarPassageiro.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[70]) {
            //Acionado pelo "HISTORICO DE AC??O" de usu??rios.
            if (interfaceAuditarUsuario == null || interfaceAuditarUsuario.isValid()) {
                interfaceAuditarUsuario = InterfaceFactory.getInterface(AUDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Lista a tabela com as a????es feita nos usu??rios.
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarUsuario = ModeloAuditarUsuario.getInstance();
                modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuario());
                interfaceAuditarUsuario.setVisible(true);
            } else {
                modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuario());
                interfaceAuditarUsuario.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[71]) {
            //Acionado pelo "HISTORICO DE AC??O" de quartos.
            if (interfaceAuditarQuarto == null || interfaceAuditarQuarto.isValid()) {
                interfaceAuditarQuarto = InterfaceFactory.getInterface(AUDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Lista as a????es realizadas por usu??rios sobre os quartos.
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarQuarto = ModeloAuditarQuarto.getInstance();
                modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuarto());
                interfaceAuditarQuarto.setVisible(true);

            } else {
                //Lista as a????es realizadas por usu??rios sobre os quartos.
                modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuarto());
                interfaceAuditarQuarto.setVisible(true);
                interfaceAuditarQuarto.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[72]) {
            //Acionado pelo "HISTORICO DE AC??O" de clientes.
            if (interfaceAuditarCliente == null || interfaceAuditarCliente.isValid()) {
                interfaceAuditarCliente = InterfaceFactory.getInterface(AUDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Acionando m??todo que preenche a tabela.
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarCliente = ModeloAuditarCliente.getInstance();
                modeloAuditarCliente.adicionarLista(historicoDAO.auditarCliente());
                interfaceAuditarCliente.setVisible(true);

            } else {
                modeloAuditarCliente.adicionarLista(historicoDAO.auditarCliente());
                interfaceAuditarCliente.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[73]) {
            //Acionado pelo "HISTORICO DE AC??O" de reservas.
            if (interfaceAuditarReserva == null || interfaceAuditarReserva.isValid()) {
                interfaceAuditarReserva = InterfaceFactory.getInterface(AUDITAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                //Preenche a tabela da classe "Auditar DTOReserva".
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarReserva = ModeloAuditarReserva.getInstance();
                modeloAuditarReserva.adicionarLista(historicoDAO.auditarReserva());
                interfaceAuditarReserva.setVisible(true);

            } else {
                modeloAuditarReserva.adicionarLista(historicoDAO.auditarReserva());
                interfaceAuditarReserva.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[74]) {
            //Acionado pelo "HISTORICO DE AC??O" de hospedagem.
            if (interfaceAuditarHospedagem == null || interfaceAuditarHospedagem.isValid()) {
                interfaceAuditarHospedagem = InterfaceFactory.getInterface(AUDITAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceAuditarHospedagem.setVisible(true);

                //Preenche a tabela da classe "Auditar Hospedagem".
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarHospedagem = ModeloAuditarHospedagem.getInstance();
                modeloAuditarHospedagem.adicionarLista(historicoDAO.auditarHospedagem());
            } else {
                modeloAuditarHospedagem.adicionarLista(historicoDAO.auditarHospedagem());
                interfaceAuditarHospedagem.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[75]) {
            //Acionado pelo "HISTORICO DE AC??O" de produto.
            if (interfaceAuditarProduto == null || interfaceAuditarProduto.isValid()) {
                interfaceAuditarProduto = InterfaceFactory.getInterface(AUDITAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceAuditarProduto.setVisible(true);

                //Preenche a tabela da classe "Auditar Hospedagem".
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarProduto = ModeloAuditarProduto.getInstance();
                modeloAuditarProduto.adicionarLista(historicoDAO.auditarProduto());
            } else {
                modeloAuditarProduto.adicionarLista(historicoDAO.auditarProduto());
                interfaceAuditarProduto.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[76]) {
            //Acionado pelo "HISTORICO DE AC??O" de passeio.
            if (interfaceAuditarPasseio == null || interfaceAuditarPasseio.isValid()) {
                interfaceAuditarPasseio = InterfaceFactory.getInterface(AUDITAR_PASSEIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                interfaceAuditarPasseio.setVisible(true);

                //Preenche a tabela da classe "Auditar Hospedagem".
                historicoDAO = HistoricoDAO.getInstance();
                modeloAuditarPasseio = ModeloAuditarPasseio.getInstance();
                modeloAuditarPasseio.adicionarLista(historicoDAO.auditarPasseio());
            } else {
                modeloAuditarPasseio.adicionarLista(historicoDAO.auditarPasseio());
                interfaceAuditarPasseio.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[77]) {
            //Acionado pelo "Selecionar" de SelecionarCliente.
            selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Significa que ?? uma reserva do tipo reserva.
            if (selecionarCliente.getTipoReserva().equals("Reservar")) {
                cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarReserva.receberliente(selecionarCliente.getCliente());
                cadastrarReserva.setVisible(true);
                selecionarCliente.dispose();

            } else {
                cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                cadastrarHospedagem.limparCampos();
                cadastrarHospedagem.receberliente(selecionarCliente.getCliente());
                cadastrarHospedagem.btnHospedar.setEnabled(true);
                selecionarCliente.dispose();
                cadastrarHospedagem.setVisible(true);

            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[78]) {
            clienteDAO = ClienteDAO.getInstance();
            selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloSelecionarCliente = ModeloSelecionarCliente.getInstance();

            try {
                if (selecionarCliente.getPesquisa().getCpf().equals("   .   .   -  ")
                        && selecionarCliente.getPesquisa().getNome().equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite um valor v??lido para pesquisa.");

                } else {

                    if (!selecionarCliente.getPesquisa().getCpf().equals("   .   .   -  ") && selecionarCliente.getPesquisa().getNome().equals("")) {
                        //Significa que procurar?? por CPF
                        modeloSelecionarCliente.adicionar(clienteDAO.consultarCliente(selecionarCliente.getPesquisa()));

                    } else { //Procurar?? por nome.
                        //Adicionando o usu??rio pesquisado a tabela que exibe os usu??rios.
                        modeloSelecionarCliente.adicionarLista(clienteDAO.ConsultarClienteNome(selecionarCliente.getPesquisa()));
                    }

                }
            } catch (Exception erro) {

            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[80]){
            //Acionado pelo pesquisar da classe "ReceitaDiaria"
            ReceitaDiaria receitaDiaria = ReceitaDiaria.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            reservaDAO = ReservaDAO.getInstance();
            ModeloReceitaDiaria modeloReceitaDiaria = ModeloReceitaDiaria.getInstance();
            
            if(receitaDiaria.getDia() == null){
                JOptionPane.showMessageDialog(null, "Digite uma data v??lida.");
            } else {
                
                ArrayList<DTOReceitaDiaria> receitas = reservaDAO.pesquisarReceitaDiaria(receitaDiaria.getDia());
                
                if(receitas.size() ==0){
                    JOptionPane.showMessageDialog(null, "N??o h?? receitas para este dia.");
                    modeloReceitaDiaria.adicionarLista(reservaDAO.getReceitaDiaria());
                    
                } else {
                    modeloReceitaDiaria.adicionarLista(receitas);
                }
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == itensDeMenu[0]) {//Acionado pelo item de menu "Manual do Usu??rio" da classe "TelaPrincipal".
            //Abre um PDF com instru????es de uso.
            try {
                
                Desktop.getDesktop().open(new File("C:\\Users\\Fabricio\\Documents\\Hospede\\src\\br\\com\\hospede\\document\\Manual do Usu??rio - Sistema Hospede.pdf"));
                
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao exibir manual de usurio:\n" + erro.getMessage());
            }
            
        } else if (evento.getSource() == itensDeMenu[1]) {//Acionado pelo item de menu "Receita Di??ria" da classe "TelaPrincipal".
            
           if (interfaceReceitaDiaria == null || interfaceReceitaDiaria.isValid()) {
                interfaceReceitaDiaria = InterfaceFactory.getInterface(49, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                reservaDAO = ReservaDAO.getInstance();
                ReceitaDiaria receitaDiaria = ReceitaDiaria.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                ModeloReceitaDiaria modeloReceitaDiaria = ModeloReceitaDiaria.getInstance();
         
            
                  modeloReceitaDiaria.adicionarLista(reservaDAO.getReceitaDiaria());
                interfaceReceitaDiaria.setVisible(true);

            } else {
                ModeloReceitaDiaria modeloReceitaDiaria = ModeloReceitaDiaria.getInstance();
                modeloReceitaDiaria.adicionarLista(reservaDAO.getReceitaDiaria());
                interfaceReceitaDiaria.setVisible(true);
            }
        } 
    }

    //-----------------------------------------------------//------------------------------------------------------------------
    //Eventos de focu em objetos.
    @Override
    public void focusGained(FocusEvent evento) {
        
    }

    @Override
    public void focusLost(FocusEvent evento
    ) {
        if (evento.getSource() == camposDeTexto[0]) {
            //Acionado quando o campo "Nome" da classe "CadastrarProduto" perde o foco.
            DTOProduto produtoCadastrar;
            produtoDAO = ProdutoDAO.getInstance();
            cadastrarProduto = CadastrarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o produto j?? existe.
            if (produtoDAO.consultarProduto(cadastrarProduto.getProduto().getNome()).getEntrega() != null) {
                JOptionPane.showMessageDialog(null, "Este produto j?? existe");
            }
            //----------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[1]) {
            //Acionado quando o campo "Login" da classe "CadastrarUsuario" perde o foco.
            DTOUsuario usuarioCadastrar;
            cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            usuarioDAO = UsuarioDAO.getInstance();
            usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());

            //Verifica se o usu??rio j?? existe no banco.
            if (!usuarioCadastrar.getFuncao().equals("")) {
                JOptionPane.showMessageDialog(cadastrarCliente, "Este usu??rio j?? existe.");
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[6]) {
            //Acionado quando o campo "Numero" da classe "CadastrarQuarto" perde o foco.
            quartoDAO = QuartoDAO.getInstance();
            cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o quarto j?? existe.
            try {
                if (!quartoDAO.consultarQuarto(Integer.parseInt(cadastrarQuarto.getQuarto().getNumero())).getSituacao().equals("")) {
                    JOptionPane.showMessageDialog(cadastrarQuarto, "Este quarto j?? existe");
                }
            } catch (Exception erro) {

            }

            //----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[7] || evento.getSource() == camposDeTexto[21]) {
            //Acionado quando o campo "CPF/PASSAPORTE" da classe "CadastrarCliente" perde o foco.
            clienteDAO = ClienteDAO.getInstance();
            cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);    

            try {
                //Verifica se o cliente j?? existe.
                if (cadastrarCliente.getCliente().getCpf().equals("   .   .   -  ") || cadastrarCliente.getCliente().getCpf().equals("")) {
                    //Verifica o cliente pelo passaporte.
                    if (!clienteDAO.consultarClientePassaporte(cadastrarCliente.getCliente()).getNome().equals("")) {
                        JOptionPane.showMessageDialog(null, "Este passaporte j?? existe.");

                    }
                } else if (!cadastrarCliente.getCliente().getCpf().equals("   .   .   -  ")) {
                    if (!clienteDAO.consultarCliente(cadastrarCliente.getCliente()).getNome().equals("")) {
                        JOptionPane.showMessageDialog(null, "Este CPF j?? existe.");

                    }
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getCause());
            }

            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[10] || evento.getSource() == camposDeTexto[22]) {//Acionado quando o campo nome da classe "CadastrarHospedagem" perder foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o cliente est?? reservado antes de hospeda-lo.
            DTOReserva reserva;
            DTOCliente cliente; //Pegando o ID do cliente.

            //Encontrando o clienre por CPF ou Passaporte
            if (cadastrarHospedagem.getCliente().getCpf().equals("   .   .   -  ") || cadastrarHospedagem.getCliente().getCpf().equals("")) {
                //Procura por Passaporte.
                cliente = clienteDAO.consultarClientePassaporte(cadastrarHospedagem.getCliente());
            } else {
                cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente());
            }

            reserva = reservaDAO.consultarReserva(cliente.getId_cliente());

            try {
                if (reserva.getEntrada() == null) {//Significa que n??o h?? reserva no nome daquele cliente hoje.

                    //Este if, investiga se o cliente j?? est?? hospedado. Se tiver, n??o permite que ele se hospede novamente.
                    if (cliente.getId_cliente() == reservaDAO.consultarHospedagem(cliente.getId_cliente()).getId_cliente() && !cliente.getCpf().equals("")) {//Significa que o cliente est?? hospedado.
                        JOptionPane.showMessageDialog(null, "Cliente encontra-se hospedado.");

                    } else {

                        //Encontrando o clienre por CPF ou Passaporte
                        if (cadastrarHospedagem.getCliente().getCpf().equals("   .   .   -  ") || cadastrarHospedagem.getCliente().getCpf().equals("")) {
                            //Procura por Passaporte.
                            cadastrarHospedagem.receberliente(clienteDAO.consultarClientePassaporte(cadastrarHospedagem.getCliente()));
                        } else {
                            cadastrarHospedagem.receberliente(clienteDAO.consultarCliente(cadastrarHospedagem.getCliente()));
                        }

                        cadastrarHospedagem.receberHospedagem(reserva);
                        cadastrarHospedagem.btnHospedar.setEnabled(true);
                    }

                } else {//Cliente est?? reservado. Logo, envia a reserva para a tela.
                    JOptionPane.showMessageDialog(null, "Cliente encontra-se reservado.");

                    //Encontrando o clienre por CPF ou Passaporte
                    if (cadastrarHospedagem.getCliente().getCpf().equals("   .   .   -  ") || cadastrarHospedagem.getCliente().getCpf().equals("")) {
                        //Procura por Passaporte.
                        cadastrarHospedagem.receberliente(clienteDAO.consultarClientePassaporte(cadastrarHospedagem.getCliente()));
                    } else {
                        cadastrarHospedagem.receberliente(clienteDAO.consultarCliente(cadastrarHospedagem.getCliente()));
                    }
                    cadastrarHospedagem.receberHospedagem(reserva);
                    cadastrarHospedagem.btnHospedar.setEnabled(false);

                }
            } catch (Exception erro) {

            }

            //--------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[11]) {//Acionado ap??s o campo nome , da classe "EditarHospedagem", perder o foco.
            reservaDAO = ReservaDAO.getInstance();
            clienteDAO = ClienteDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                //Primeiro busca o cliente.
                DTOCliente cliente = clienteDAO.consultarCliente(editarHospedagem.getCliente());
                //Segundo, hospedagem.
                DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
                //E por ??ltimo, o querto.
                DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);

                editarHospedagem.receberHospedagem(reserva, cliente, quarto);;
            } catch (Exception erro) {

            }
            //----------------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[13] || evento.getSource() == camposDeTexto[23]) {
            //Acionado pelo campo nome da classe "FecharHsopedagem".
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            passeioDAO = PasseioDAO.getInstance();
            produtoDAO = ProdutoDAO.getInstance();
            fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            DTOCliente cliente = new DTOCliente(0, 0, "", "", "", "", "", "", "", "", "", "", "", "");

            try {
                //Primeiro busca o cliente.
                if (!fecharHospedagem.getCliente().getCpf().equals("   .   .   -  ") && !fecharHospedagem.getCliente().getCpf().equals("")) {
                    //Procura o cliente pelo CPF.
                    cliente = clienteDAO.consultarCliente(fecharHospedagem.getCliente());
                } else if (!fecharHospedagem.getCliente().getPassaport().equals("")) {
                    cliente = clienteDAO.consultarClientePassaporte(fecharHospedagem.getCliente());
                }

                //Segundo, reserva.
                DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());

                if (reserva.getId_reserva() != 0) {
                    //Terceiro, quarto.
                    DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);

                    fecharHospedagem.receberHospedagem(reserva, cliente, quarto);
                    modeloPasseiosRealizados = ModeloPasseiosRealizados.getInstance();
                    modeloProdutosConumidos = ModeloProdutosConsumidos.getInstance();
                    modeloPasseiosRealizados.limparDados();
                    modeloProdutosConumidos.limparDados();
                    modeloPasseiosRealizados.adicionarLista(passeioDAO.listarPasseiosFecharConta(reserva));
                    modeloProdutosConumidos.adicionarLista(produtoDAO.listarProdutoFecharConta(reserva));

                    //Calculando os totais e o total a pagar pelos consumos e reservas.
                    //Primeiro calcula tota de hospedagem
                    reserva.totalPagarFechar(quarto);

                    //Calculando produtos consumidos.
                    DTOProduto produto = new DTOProduto();
                    //Calculando os passeios realiados.
                    DTOPasseio passeio = new DTOPasseio();

                    fecharHospedagem.receberTotaisServicos(reserva.getTotalPagar(), produto.totalPagar(), passeio.totalPagar());
                } else {
                    JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada.");
                }
            } catch (Exception erro) {

            }

        } else if (evento.getSource() == camposDeTexto[14]) {//Acionado pelo campo nome do produto classe EditarProduto
            editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            produtoDAO = ProdutoDAO.getInstance();

            editarProduto.receberProduto(produtoDAO.consultarProduto(editarProduto.getProduto().getNome()));

        } else if (evento.getSource() == camposDeTexto[16]) {
            //Acionado pelo campo titulo do passeio classe CadastrarPasseio
            passeioDAO = PasseioDAO.getInstance();
            cadastrarPasseio = CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            //Verifica se o passeio j?? existe.
            if (passeioDAO.consultarPasseio(cadastrarPasseio.getPasseio().getTitulo()).getData() != null) {
                JOptionPane.showMessageDialog(null, "Este passeio j?? existe.");
            }

        } else if (evento.getSource() == camposDeTexto[18] || evento.getSource() == camposDeTexto[24]) {
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            clienteDAO = ClienteDAO.getInstance();
            DTOCliente cliente = null;
            try {
                //Primeiro busca o cliente.
                if (!adicionarPassageiroPasseio.getCliente().getCpf().equals("   .   .   -  ") && !adicionarPassageiroPasseio.getCliente().getCpf().equals("")) {
                    //Procura o cliente pelo CPF.
                    cliente = clienteDAO.consultarCliente(adicionarPassageiroPasseio.getCliente());
                } else if (!adicionarPassageiroPasseio.getCliente().getPassaport().equals("")) {
                    cliente = clienteDAO.consultarClientePassaporte(adicionarPassageiroPasseio.getCliente());
                }

                //Buscando a hospedagem.
                reservaDAO = ReservaDAO.getInstance();
                DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());

                if (reserva.getId_reserva() != 0) {
                    //Buscando quarto.
                    quartoDAO = QuartoDAO.getInstance();
                    DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);

                    //Enviando as informa????es para a tela de cadastro de passageiro.
                    adicionarPassageiroPasseio.receberCliente(cliente, quarto, reserva);
                    adicionarPassageiroPasseio.liberarCampos();
                } else {
                    JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada");
                }
            } catch (Exception erro) {

            }

        } else if (evento.getSource() == camposDeTexto[19]) {
            //Acionado pelo campo nome do Quarto classe CadastrarPedidoQuarto
            //Obtendo os objetos.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            try {
                //Buscando quarto.
                quartoDAO = QuartoDAO.getInstance();
                DTOQuarto quarto = quartoDAO.consultarQuarto(Integer.parseInt(cadastrarPedidoQuarto.getQuarto().getNumero()));

                //Buscando a hospedagem.
                reservaDAO = ReservaDAO.getInstance();
                DTOReserva reserva = reservaDAO.consultarHospedagemIdQuarto(quarto.getId_quarto());

                if (reserva.getId_reserva() == 0) {
                    JOptionPane.showMessageDialog(null, "Hospedagem n??o encontrada");
                    cadastrarPedidoQuarto.limparCampos();

                } else {
                    //Buscando o cliente no banco.
                    clienteDAO = ClienteDAO.getInstance();
                    DTOCliente cliente = clienteDAO.consultarClienteID(reserva.getId_cliente());
                    cadastrarPedidoQuarto.liberarCampos();
                    cadastrarPedidoQuarto.receberHospede(reserva, cliente, quarto);
                }

            } catch (Exception erro) {

            }

        } else if (evento.getSource() == camposDeTexto[20]) {//Acionado pelo campo nome do cliente classe ConsultarPedidoQuarto
            //Obtendo os objetos.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            produtoDAO = ProdutoDAO.getInstance();

            consultarPedidoQuarto = ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();

            //Buscando o cliente no banco.
            DTOCliente cliente = clienteDAO.consultarCliente(consultarPedidoQuarto.getHospede());

            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());

            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);

            consultarPedidoQuarto.receberHospede(reserva, cliente, quarto);

            modeloPedidosDeQuarto.adicionarLista(produtoDAO.listarPedidosPorHospedagem(reserva));

        } else if (evento.getSource() == modeloTabelas[7]) {//Acionado pelo campo nome do Dia classe ReceitaDiaria
            

        }
    }
    //----------------------------------------------------//-------------------------------------------------------------------

    public boolean verificarCPF_Passaporte() {
        boolean permissao = false;

        try {

            //Primeiro no banco.
            if (modeloPassageiro.getPassageiros().isEmpty()) {
                for (int posicaoBanco = 0; posicaoBanco < passageiroDAO.consultarPassageiros(adicionarPassageiroPasseio.getPasseio()).size(); posicaoBanco++) {

                    if (adicionarPassageiroPasseio.getPassageiro().getCpf().equals(passageiroDAO.consultarPassageiros(adicionarPassageiroPasseio.getPasseio()).get(posicaoBanco).getCpf())) {
                        JOptionPane.showMessageDialog(null, "CPF/Passaporte j?? existe na lista de passageiros.");
                        permissao = false;
                        throw new Exception();

                    } else {
                        permissao = true;
                    }

                }

            } else {

                //Primeiro no banco.
                for (int posicaoBanco = 0; posicaoBanco < passageiroDAO.consultarPassageiros(adicionarPassageiroPasseio.getPasseio()).size(); posicaoBanco++) {

                    if (adicionarPassageiroPasseio.getPassageiro().getCpf().equals(passageiroDAO.consultarPassageiros(adicionarPassageiroPasseio.getPasseio()).get(posicaoBanco).getCpf())) {
                        JOptionPane.showMessageDialog(null, "CPF/Passaporte j?? existe na lista de passageiros.");
                        permissao = false;
                        throw new Exception();

                    } else {
                        permissao = true;
                    }

                }

                //Depois verifica na pr??pria lista
                for (int posicao = 0; posicao < modeloPassageiros.getPassageiros().size(); posicao++) {

                    if (adicionarPassageiroPasseio.getPassageiro().getCpf().equals(modeloPassageiro.getPassageiro(posicao).getCpf())) {
                        JOptionPane.showMessageDialog(null, "CPF/Passaporte j?? existe na lista de passageiros.");
                        permissao = false;
                        throw new Exception();
                    } else {
                        permissao = true;
                    }
                }
            }

        } catch (Exception erro) {

        }

        return permissao;
    }

    public static ControlarEventos getInstance(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, DTOUsuario usuarioLogado, Object[] modelosTabelas) {
        //Garantir , caso j?? exista uma instancia da classe ControlarEventos, quee os vetores n??o ficar??o faltando objetos.
        ControlarEventos.botoes = botoes;
        ControlarEventos.itensDeMenu = itensDeMenu;
        ControlarEventos.camposDeTexto = camposDeTexto;

        if (instance == null) {
            instance = new ControlarEventos(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modelosTabelas);
        }
        return instance;
    }

    //================================================================================================================================================================================
    //Eventos do mause nas tabelas.
    @Override
    public void mouseClicked(MouseEvent evento) {

        if (evento.getSource() == modeloTabelas[0]) {
            //Clique na tabela de "ConsultarUsu??rios"
            consultarUsuario = ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarUsuario.getTabela().getSelectedRow();
            int coluna = consultarUsuario.getTabela().getSelectedColumn();
            InterfaceFactory interfaceConfirmarExclusao;

            if (coluna == 5) {
                //Exibir a classe "EditarUsuario"
                int EDITAR_USUARIO = 5;
                if (interfaceEditarUsuario == null || interfaceEditarUsuario.isValid()) {
                    interfaceEditarUsuario = InterfaceFactory.getInterface(EDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    modeloUsuario = ModeloUsuario.getInstance();

                    //Envia o usu??rio para a tela de edi????o.
                    editarUsuario.recebeUsuario(modeloUsuario.getUsuario(linha));
                    interfaceEditarUsuario.setVisible(true);
                } else {
                    //Envia o usu??rio para a tela de edi????o.
                    editarUsuario.recebeUsuario(modeloUsuario.getUsuario(linha));
                    interfaceEditarUsuario.setVisible(true);
                }

            } else if (coluna == 6) {
                //Deletar usu??rio do banco.
                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usu??rio?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {
                    usuarioDAO = UsuarioDAO.getInstance();
                    modeloUsuario = ModeloUsuario.getInstance();
                    historicoDAO = HistoricoDAO.getInstance();
                    consultarUsuario = ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    boolean deletou = false; //Verifica se o a exclus??o foi conclu??da.
                    deletou = usuarioDAO.deletarUsuario(consultarUsuario.getUsuario().getLogin());

                    if (deletou != false) {
                        //Pega o ID do usuario cadastrado.
                        DTOHistoricoUsuario manterUsuario = new DTOHistoricoUsuario();
                        modeloUsuario = ModeloUsuario.getInstance();
                        manterUsuario.setId_suario_alterado(consultarUsuario.getUsuario().getId_usuario());
                        manterUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        manterUsuario.setUsuario_alterado(consultarUsuario.getUsuario().getLogin());
                        manterUsuario.setAcao("Deletou");

                        //Salva na tabela de auditoria.
                        historicoDAO = HistoricoDAO.getInstance();
                        historicoDAO.salvarManterUsuario(manterUsuario);

                    }

                    modeloUsuario.remover(consultarUsuario.getLinhaSelecionada());

                }
            }

        } else if (evento.getSource() == modeloTabelas[1]) {
            consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarQuarto.getTabela().getSelectedRow();
            int coluna = consultarQuarto.getTabela().getSelectedColumn();
            modeloQuarto = ModeloQuarto.getInstance();
            int EDITAR_QUARTO = 12;

            if (coluna == 7) {
                //Exibir a classe "EditarQuarto"
                if (modeloQuarto.getQuarto(linha).getSituacao().equals("RESERVADO") || modeloQuarto.getQuarto(linha).getSituacao().equals("HOSPEDADO")) {

                    JOptionPane.showMessageDialog(null, "O quarto selecionado n??o pode ser editado. O mesmo est?? em uso.");
                } else {
                    modeloQuarto = ModeloQuarto.getInstance();

                    if (interfaceEditarQuarto == null || interfaceEditarQuarto.isValid()) {
                        interfaceEditarQuarto = InterfaceFactory.getInterface(EDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        interfaceEditarQuarto.setVisible(true);
                        editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        editarQuarto.receberQuarto(modeloQuarto.getQuarto(linha));

                    } else {

                        editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        editarQuarto.receberQuarto(modeloQuarto.getQuarto(linha));
                        editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                        interfaceEditarQuarto.setVisible(true);
                    }

                }

            } else if (coluna == 8) {

                if (modeloQuarto.getQuarto(linha).getSituacao().equals("RESERVADO") || modeloQuarto.getQuarto(linha).getSituacao().equals("HOSPEDADO")
                        || modeloQuarto.getQuarto(linha).getSituacao().equals("ORGANIZANDO")) {

                    JOptionPane.showMessageDialog(null, "O quarto selecionado n??o pode ser deletado. O mesmo est?? em uso.");
                } else {
                    //Deletar usu??rio do banco.
                    int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este quarto?");

                    if (confirmarAcao == JOptionPane.YES_OPTION) {
                        quartoDAO = QuartoDAO.getInstance();
                        modeloQuarto = ModeloQuarto.getInstance();
                        consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                        boolean deletou = false;//Controla se a exclus??o foi bem sucedida.
                        deletou = quartoDAO.deletarQuarto(consultarQuarto.getQuarto().getNumero());

                        if (deletou != false) {//Se a exclus??o foi bem sucedida, este m??todo registra na tabela "manter_quarto" quem excluiu e qual quarto foi excluido.

                            DTOHistoricoQuarto HistoricoQuarto = new DTOHistoricoQuarto();
                            HistoricoDAO manterQuartoDAO = HistoricoDAO.getInstance();

                            //Obtem o ID do quarto cadastrado.
                            HistoricoQuarto.setId_quarto(consultarQuarto.getQuarto().getId_quarto());
                            HistoricoQuarto.setId_usuario(usuarioLogado.getId_usuario());
                            HistoricoQuarto.setNumero_quarto(Integer.parseInt(consultarQuarto.getQuarto().getNumero()));
                            HistoricoQuarto.setAcao("Deletou");

                            //Salva na tabela.
                            manterQuartoDAO.salvarManterQuarto(HistoricoQuarto);

                        }

                        modeloQuarto.remover(consultarQuarto.getLinhaSelecionada());

                    }
                }
            }
        } else if (evento.getSource() == modeloTabelas[2]) {
            //Tabela clientes.
            consultarCliente = ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarCliente.getTabela().getSelectedRow();
            int coluna = consultarCliente.getTabela().getSelectedColumn();
            modeloCliente = ModeloCliente.getInstance();
            editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int EDITAR_CLIENTE = 9;

            if (coluna == 12) {

                if (interfaceEditarCliente == null || interfaceEditarCliente.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                    interfaceEditarCliente = InterfaceFactory.getInterface(EDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarCliente.receberCliente(modeloCliente.getCliente(linha));
                    interfaceEditarCliente.setVisible(true);

                } else {
                    editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarCliente.receberCliente(modeloCliente.getCliente(linha));
                    interfaceEditarCliente.setVisible(true);
                }

            } else if (coluna == 13 && usuarioLogado.getFuncao().equals("Gerente")) {
                //Deletar usu??rio do banco.
                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {
                    clienteDAO = ClienteDAO.getInstance();
                    modeloCliente = ModeloCliente.getInstance();
                    consultarCliente = ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    boolean deletou = false;

                    //Verifica se ?? por CPF ou Passaporte
                    if (!consultarCliente.getCliente().getCpf().equals("   .   .   -  ")) {

                        deletou = clienteDAO.deletarCliente(consultarCliente.getCliente().getCpf());
                    } else {
                        deletou = clienteDAO.deletarClientePassaporte(consultarCliente.getCliente().getPassaport());
                    }
                    if (deletou != false) {

                        DTOHistoricoCliente historicoCliente = new DTOHistoricoCliente();
                        historicoDAO = HistoricoDAO.getInstance();

                        //Pegando o ID do cliente Deletado.
                        historicoCliente.setId_cliente(consultarCliente.getCliente().getId_cliente());
                        historicoCliente.setNome_cliente(consultarCliente.getCliente().getNome());
                        historicoCliente.setId_usuario(usuarioLogado.getId_usuario());
                        historicoCliente.setAcao("Deletou");

                        historicoDAO.salvarManterCliente(historicoCliente);

                    }

                    modeloCliente.remover(consultarCliente.getLinhaSelecionada());

                }
            }

        } else if (evento.getSource() == modeloTabelas[3]) {
            //Tabela reservas.
            consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarReserva.getTabela().getSelectedRow();
            int coluna = consultarReserva.getTabela().getSelectedColumn();
            modeloReserva = ModeloReserva.getInstance();

            if (coluna == 9) {
                //Imagem Editar para editar reserva.
                clienteDAO = ClienteDAO.getInstance();

                editarReserva = EditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                editarReserva.receberReserva(modeloReserva.getReserva(linha));
                DTOCliente cliente = clienteDAO.consultarClientePorId(modeloReserva.getReserva(linha).getId_cliente());
                editarReserva.receberliente(cliente);
                editarReserva.setVisible(true);

            } else if (coluna == 10) {
                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta reserva?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {
                    quartoDAO = QuartoDAO.getInstance();
                    reservaDAO = ReservaDAO.getInstance();
                    consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                    //Alterando o quarto para livre.
                    DTOReserva reserva = consultarReserva.getReserva();//Pegando o ID do quarto para ser alterado.

                    DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);

                    try {
                        //Verifica se o quarto foi encontrado. Caso n??o seja, exibe uma mensagem de alerta sobre o nome fornecido no campo nome, visto que aconter?? um erro por causa do campo em branco.
                        if (quarto.getNumero().equals("")) {

                        } else {
                            quarto.setSituacao("LIVRE");
                            quartoDAO.editarQuarto(quarto);

                            boolean deletou = false;
                            //Em sequ??ncia, deletar a reserva.
                            //deletou = reservaDAO.deletarReserva(reserva);
                            reserva.setStatus("CANCELADA");
                            deletou = reservaDAO.editarReserva(reserva);

                            if (deletou != false) {
                                //Salva a a????o feita na tabela manter_Reserva, para posterior audita????o.
                                DTOHistoricoReserva historicoReserva = new DTOHistoricoReserva();
                                historicoDAO = HistoricoDAO.getInstance();
                                historicoReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                                historicoReserva.setLogin(usuarioLogado.getLogin());
                                historicoReserva.setAcao("Deletou");
                                historicoReserva.setTipo_reserva("RESERVAR");

                                //Buscando cliente.
                                clienteDAO = ClienteDAO.getInstance();
                                DTOCliente cliente = clienteDAO.consultarClientePorId(consultarReserva.getReserva().getId_cliente());
                                historicoReserva.setCliente_reservado(cliente.getNome());

                                //Obter o ID da reserva.
                                historicoReserva.setId_reserva(consultarReserva.getReserva().getId_reserva());
                                historicoReserva.setCliente_reservado(cliente.getNome());

                                //Salva na tabela.
                                historicoDAO.salvarManterReserva(historicoReserva);

                                modeloReserva = ModeloReserva.getInstance();
                                modeloReserva.remover(consultarReserva.getLinhaSelecionada());
                                modeloReserva.adicionarLista(reservaDAO.listarReservas());

                            }

                        }
                        
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }

                }
                       quartoDAO.atualizarQuarto();
            }

        } else if (evento.getSource() == modeloTabelas[4]) {
            //Tabela hospedagens.
            consultarHospedagem = ConsultarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarHospedagem.getTabela().getSelectedRow();
            int coluna = consultarHospedagem.getTabela().getSelectedColumn();
            modeloHospedagem = ModeloHospedagem.getInstance();

            if (coluna == 9) {
                //Imagem Editar para editar reserva.
                clienteDAO = ClienteDAO.getInstance();
                quartoDAO = QuartoDAO.getInstance();

                editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                DTOCliente cliente = clienteDAO.consultarClientePorId(modeloHospedagem.getHospedagem(linha).getId_cliente());
                DTOQuarto quarto = quartoDAO.getQuartoPorID(modeloHospedagem.getHospedagem(linha));
                editarHospedagem.receberHospedagem(modeloHospedagem.getHospedagem(linha), cliente, quarto);
                editarHospedagem.setVisible(true);

            }

        } else if (evento.getSource() == modeloTabelas[5]) {
            //Tabela produtos.
            consultarProduto = ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarProduto.getTabela().getSelectedRow();
            int coluna = consultarProduto.getTabela().getSelectedColumn();
            modeloProduto = ModeloProduto.getInstance();
            editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int EDITAR_PRODUTO = 37;

            if (coluna == 5) {

                if (interfaceEditarProduto == null || interfaceEditarProduto.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                    interfaceEditarProduto = InterfaceFactory.getInterface(EDITAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarProduto.receberProduto(modeloProduto.getProduto(linha));
                    interfaceEditarProduto.setVisible(true);

                } else {
                    editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarProduto.receberProduto(modeloProduto.getProduto(linha));
                    interfaceEditarProduto.setVisible(true);
                }

            } else if (coluna == 6) {
                //Deletar usu??rio do banco.
                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este produto?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {
                    produtoDAO = ProdutoDAO.getInstance();
                    modeloProduto = ModeloProduto.getInstance();
                    historicoDAO = HistoricoDAO.getInstance();
                    consultarProduto = ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                    boolean deletou = false;
                    deletou = produtoDAO.deletarProduto(consultarProduto.getProduto());

                    try {
                        if (deletou != false) {
                            DTOHistoricoProduto historicoProduto = new DTOHistoricoProduto();
                            historicoProduto.setId_produto(consultarProduto.getProduto().getId());
                            historicoProduto.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                            historicoProduto.setAcao("Deletou");
                            historicoProduto.setProduto(consultarProduto.getProduto().getNome());

                            historicoDAO.salvarManterProduto(historicoProduto);
                        }
                    } catch (Exception erro) {

                    }

                    modeloProduto.remover(consultarProduto.getLinhaSelecionada());
                }

            }

        } else if (evento.getSource() == modeloTabelas[6]) {
            //Tabela passeios.
            consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = consultarPasseio.getTabela().getSelectedRow();
            int coluna = consultarPasseio.getTabela().getSelectedColumn();
            modeloPasseio = ModeloPasseio.getInstance();
            editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int EDITAR_PASSEIOS = 33;

            if (coluna == 3) {

                if (interfaceEditarPasseio == null || interfaceEditarPasseio.isValid()) {//Controla a quantidade de interface abertas. S?? permite abrir uma.
                    interfaceEditarPasseio = InterfaceFactory.getInterface(EDITAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarPasseio.receberPasseio(modeloPasseio.getPasseio(linha));
                    interfaceEditarPasseio.setVisible(true);

                    //Obtendo passageiros do passeio selecionado
                    modeloPassageiro = ModeloPassageiros.getInstance();
                    passageiroDAO = PassageiroDAO.getInstance();

                    modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(modeloPasseio.getPasseio(linha)));

                } else {
                    editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                    editarPasseio.receberPasseio(modeloPasseio.getPasseio(linha));
                    modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(modeloPasseio.getPasseio(linha)));
                    interfaceEditarPasseio.setVisible(true);
                }

            } else if (coluna == 4 && usuarioLogado.getFuncao().equals("Gerente")) {
                int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este passeio?");

                if (confirmarAcao == JOptionPane.YES_OPTION) {
                    passeioDAO = PasseioDAO.getInstance();
                    modeloPasseio = ModeloPasseio.getInstance();
                    historicoDAO = new HistoricoDAO();
                    consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

                    boolean deletou = false;
                    DTOPasseio passeioEditar = consultarPasseio.getPasseio();
                    passeioEditar.setStatus("FECHADO");
                    deletou = passeioDAO.editarPasseio(passeioEditar);

                    try {
                        if (deletou != false) {
                            DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                            DTOPasseio passeioDeletar = consultarPasseio.getPasseio();
                            historicoPasseio.setId_passeio(passeioDeletar.getId());
                            historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                            historicoPasseio.setPasseio(passeioDeletar.getTitulo());
                            historicoPasseio.setAcao("Deletou");

                            historicoDAO.salvarManterPasseio(historicoPasseio);
                        }
                    } catch (Exception erro) {

                    }

                    modeloPasseio.remover(consultarPasseio.getLinhaSelecionada());
                }

            }

        } else if (evento.getSource() == modeloTabelas[7]) {
            //Tabela editarPasseio
            editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            int linha = editarPasseio.getTabela().getSelectedRow();
            int coluna = editarPasseio.getTabela().getSelectedColumn();
            modeloPassageiros = ModeloPassageiros.getInstance();

           
            if (coluna == 4) {
                 int confirmarAcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar este passageiro?");
                
                if(confirmarAcao == JOptionPane.YES_OPTION){
                    modeloPassageiro = ModeloPassageiros.getInstance();
                    passageiroDAO = PassageiroDAO.getInstance();

                   passageiroDAO.deletarPassegeiro(modeloPassageiros.getPassageiro(linha));
                    modeloPassageiro.remover(linha);
                }
                
            }

        } else if (evento.getSource() == modeloTabelas[8]) {
            //Tabela adicionarPassageiros

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
