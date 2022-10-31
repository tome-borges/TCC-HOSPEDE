package br.com.hospede.control;

import br.com.hospede.model.dto.DTOHistoricoReserva;
import br.com.hospede.model.dto.DTOCliente;
import br.com.hospede.model.dto.DTOPasseio;
import br.com.hospede.model.dto.DTOProduto;
import br.com.hospede.model.dto.DTOHistoricoCliente;
import br.com.hospede.model.dto.DTOHistoricoQuarto;
import br.com.hospede.model.dto.DTOHistoricoUsuario;
import br.com.hospede.model.dto.DTOQuarto;
import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.model.DAO.ClienteDAO;
import br.com.hospede.model.DAO.HistoricoDAO;
import br.com.hospede.model.DAO.EntrandoOuSaindoDAO;
import br.com.hospede.model.DAO.PassageiroDAO;
import br.com.hospede.model.DAO.PasseioDAO;
import br.com.hospede.model.DAO.ProdutoDAO;
import br.com.hospede.model.DAO.QuartoDAO;
import br.com.hospede.model.DAO.ReservaDAO;
import br.com.hospede.model.DAO.UsuarioDAO;
import br.com.hospede.model.dto.DTOHistoricoPasseio;
import br.com.hospede.model.dto.DTOHistoricoProduto;
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
import br.com.hospede.model.modeloTabela.ModeloSelecionarCliente;
import br.com.hospede.model.modeloTabela.ModeloSelecionarPasseio;
import br.com.hospede.model.modeloTabela.ModeloSelecionarQuarto;
import br.com.hospede.view.AdicionarPassageiroPasseio;
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
import br.com.hospede.view.ConsultarPedidoQuarto;
import br.com.hospede.view.ConsultarProduto;
import br.com.hospede.view.ConsultarQuarto;
import br.com.hospede.view.ConsultarPasseio;
import br.com.hospede.view.ConsultarReserva;
import br.com.hospede.view.EditarPasseio;
import br.com.hospede.view.EditarProduto;
import br.com.hospede.view.EditarReserva;
import br.com.hospede.view.ProdutoPedido;
import br.com.hospede.view.SelecionarCliente;
import br.com.hospede.view.SelecionarPasseioCadastrarPassageiro;
import br.com.hospede.view.TelaPrincipal;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControlarEventos implements ActionListener, FocusListener {
    
    public static final int QUANTIDADE_POSICOES_VETOR_BOTOES = 80;  
    public static final int QUANTIDADE_POSICOES_VETOR_ITENS_MENU = 50; 
    public static final int QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO = 50; 

    //Vetores para comportar os objetos de identificação dentro da classe "ControlarEventos".
    public static JButton[]    botoes        = new JButton[QUANTIDADE_POSICOES_VETOR_BOTOES];
    public static JMenuItem[]  itensDeMenu   = new JMenuItem[QUANTIDADE_POSICOES_VETOR_ITENS_MENU];
    public static JTextField[] camposDeTexto = new JTextField[QUANTIDADE_POSICOES_VETOR_CAIXAS_TEXTO];

    //Códigos para criar interfaces. Exemplo: TELAPRINCIPAL indica para a classe "InterfaceFactory" criar a interface Tela principal.
    private final int TELA_PRINCIPAL = 1, CADASTRAR_USUARIO    = 2,  DELETAR_USUARIO      = 3, CONSULTAR_USUARIO   = 4, EDITAR_USUARIO     = 5,
                                          CADASTRAR_CLIENTE    = 6,  DELETAR_CLIENTE      = 7, CONSULTAR_CLIENTE   = 8, EDITAR_CLIENTE     = 9,
                                          CADASTRAR_QUARTO     = 10, DELETAR_QUARTO       = 13, CONSULTAR_QUARTO   = 11, EDITAR_QUARTO     = 12,
                                          CADASTRAR_RESERVA    = 14, SELECIONAR_QUARTO    = 15, CONSULTAR_RESERVA  = 16, DELETAR_RESERVA   = 17, EDITAR_RESERVA = 18,
                                          RELATORIO_SERVICOS   = 19, AUDITAR_QUARTO       = 20, AUDITAR_CLIENTE    = 25, AUDITAR_USUARIO   = 26, AUDITAR_RESERVA = 27,
                                          CADASTRAR_HOSPEDAGEM = 21, CONSULTAR_HOSPEDAGEM = 22, DELETAR_HOSPEDAGEM = 23, EDITAR_HOSPEDAGEM = 24,
                                          FECHAR_HOSPEDAGEM    = 28, ENTRANDO_OU_SAINDO   = 29, AUDITAR_HOSPEDAGEM = 30, CADASTRAR_PASSEIOS= 31, SELECIONAR_PASSEIOS_CONSULTA = 32,
                                          EDITAR_PASSEIOS = 33     , DELETAR_PASSEIOS = 34,     PASSEIOS = 35,           CADASTRAR_PRODUTO = 36, EDITAR_PRODUTO = 37,
                                          DELETAR_PRODUTO = 38     , CONSULTAR_PRODUTO = 39,    PEDIDO_QUARTO = 40,      CONSULTAR_PASSEIO = 41, SELECIONAR_PASSEIO_CADASTRAR_PASSAGEIRO = 42,
                                          ADICIONAR_PASSAGEIRO_PASSEIO = 43, PRODUTO_PEDIDO = 44, CONSULTAR_PEDIDO_QUARTO = 45, AUDITAR_PRODUTO = 46, AUDITAR_PASSEIO = 47,
                                          SELECIONAR_CLIENTE = 48;

    //Objetos de usuário.
    Login                    login;
    private DTOUsuario          usuarioLogado;
    private InterfaceFactory interfaceCadastrarUsuario = null;
    private InterfaceFactory interfaceConsultarUsuario = null;
    private InterfaceFactory interfaceEditarUsuario    = null;
    private CadastrarUsuario cadastrarUsuario;
    private ConsultarUsuario consultarUsuario;
    private EditarUsuario    editarUsuario;
    private UsuarioDAO       usuarioDAO;
    private ModeloUsuario    modeloUsuario;

    //Objetos de cliente.
    private InterfaceFactory interfaceCadastrarCliente = null;
    private InterfaceFactory interfaceConsultarCliente = null;
    private ClienteDAO       clienteDAO;
    private CadastrarCliente cadastrarCliente;
    private EditarCliente    editarCliente;
    private ConsultarCliente consultarCliente;
    private ModeloCliente    modeloCliente;
    private ModeloSelecionarCliente modeloSelecionarCliente;
    private SelecionarCliente selecionarCliente;
    
    //Objetos de quarto.
    private InterfaceFactory interfaceCadastrarQuarto = null;
    private InterfaceFactory interfaceConsultarQuarto = null;
    private InterfaceFactory interfaceEditarQuarto    = null;
    private CadastrarQuarto  cadastrarQuarto;
    private EditarQuarto     editarQuarto;
    private ModeloQuarto     modeloQuarto;
    private QuartoDAO        quartoDAO;
    private ConsultarQuarto  consultarQuarto;
    private ModeloSelecionarQuarto modeloSelecionarQuarto;
    
    //Objetos reserva.
    private InterfaceFactory interfaceCadastrarReserva = null;
    private InterfaceFactory interfaceSelecionarQuarto = null;
    private InterfaceFactory interfaceConsultarReserva = null;
    private InterfaceFactory interfaceEditarReserva    = null;
    private InterfaceFactory interfaceFecharHospedagem = null;
    private InterfaceFactory interfaceSelecionarCliente = null;
    private SelecionarQuarto selecionarQuarto;
    private CadastrarReserva cadastrarReserva;
    private EditarReserva    editarReserva;
    private ConsultarReserva consultarReserva;
    private FecharHospedagem fecharHospedagem;
    private ReservaDAO       reservaDAO;
    private ModeloReserva    modeloReserva;
    
    //Objetos hospedagem.
    private InterfaceFactory    interfaceCadastrarHospedagem = null;
    private InterfaceFactory    interfaceConsultarHospedagem = null;
    private InterfaceFactory    interfaceEditarHospedagem    = null;
    private CadastrarHospedagem cadastrarHospedagem;
    private EditarHospedagem    editarHospedagem;
    private ModeloHospedagem    modeloHospedagem;
    private ConsultarHospedagem consultarHospedagem;

    //Objetos auditar.
    private InterfaceFactory        interfaceAuditarQuarto     = null;
    private InterfaceFactory        interfaceAuditarCliente    = null;
    private InterfaceFactory        interfaceAuditarUsuario    = null;
    private InterfaceFactory        interfaceAuditarReserva    = null;
    private InterfaceFactory        interfaceAuditarHospedagem = null;
    private InterfaceFactory        interfaceAuditarProduto;
    private InterfaceFactory        interfaceAuditarPasseio;
    private ModeloAuditarQuarto     modeloAuditarQuarto;
    private ModeloAuditarCliente    modeloAuditarCliente;
    private ModeloAuditarUsuario    modeloAuditarUsuario;
    private ModeloAuditarReserva    modeloAuditarReserva;
    private ModeloAuditarHospedagem modeloAuditarHospedagem;
    private ModeloAuditarProduto    modeloAuditarProduto;
    private ModeloAuditarPasseio    modeloAuditarPasseio;
    private DTOHistoricoReserva     historicoReserva;
    private HistoricoDAO            historicoDAO;

    //Objeto Tela Principal.
    private InterfaceFactory    interfaceTelaPrincipal     = null;
    private InterfaceFactory    interfaceEntrandoOuSaindo;
    private ModeloEntrandoHoje  modeloEntrandoHoje;
    private ModeloSaindoHoje    modeloSaindoHoje;
    private EntrandoOuSaindoDAO entrandoOuSaindoDAO;
    
    //Objetos passeios
    private InterfaceFactory    interfaceCadastrarPasseios;
    private InterfaceFactory    interfaceEditarPasseios;
    private InterfaceFactory    interfaceDeletarPasseios;
    private InterfaceFactory    interfaceSelecionarPasseioConsulta;
    private InterfaceFactory    interfacePasseios;
    private InterfaceFactory    interfaceConsultarPesseio;
    private InterfaceFactory    interfaceSelecionarPasseioCadastrarPassageiro;
    private InterfaceFactory    interfacePassageiroPassaio;
    private PasseioDAO          passeioDAO;
    private CadastrarPasseios   cadastrarPasseio;
    private EditarPasseio       editarPasseio;
    private ModeloPasseio       modeloPasseio;  
    private ConsultarPasseio    consultarPasseio;
    private SelecionarPasseioCadastrarPassageiro selecionarPasseioCadastrarPassageiro; 
    private ModeloPassageiros  modeloPassageiro;
    private AdicionarPassageiroPasseio adicionarPassageiroPasseio;
    private ModeloSelecionarPasseio modeloSelecionarPasseio;
    private ModeloPasseiosRealizados modeloPasseiosRealizados;
    
            
    //Objetos produtos
    private InterfaceFactory    interfaceCadastrarProdutos;
    private InterfaceFactory    interfaceConsultarProduto;
    private InterfaceFactory    interfaceEditarProduto;
    private InterfaceFactory    interfaceDeletarProduto;
    private InterfaceFactory    interfaceSelecionarProduto;
    private InterfaceFactory    interfaceCadastraPedidoQuarto;
    private InterfaceFactory    interfaceProdutoPedido;
    private InterfaceFactory    interfaceConsultarPedidoQuarto;
    private CadastrarProduto    cadastrarProduto; 
    private ProdutoDAO          produtoDAO;
    private ModeloProduto       modeloProduto;
    private EditarProduto       editarProduto;
    private CadastrarPedidoQuarto cadastrarPedidoQuarto;
    private ProdutoPedido       produtoPedido;
    private ModeloProdutoPedido modeloProdutoPedido;
    private ModeloPedidosDeQuarto modeloPedidosDeQuarto;
    private ConsultarPedidoQuarto consultarPedidoQuarto;
    private ConsultarProduto      consultarProduto;
    private ModeloProdutosConsumidos modeloProdutosConumidos;
    
    //Objetos passageiros
    private PassageiroDAO passageiroDAO;
    private ModeloPassageiros modeloPassageiros;
    
    public static ControlarEventos instance = null;
    private Date dataAtual = new Date();
    
    public static  Login     instanceLogin = null;
    private TelaPrincipal    telaPrincipal;
    

    //Construtor principal.
    public ControlarEventos(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, DTOUsuario usuarioLogado) {
        this.botoes = botoes;
        this.itensDeMenu = itensDeMenu;
        this.camposDeTexto = camposDeTexto;
        this.usuarioLogado = usuarioLogado;
        dataAtual.getTime();
        
    }

    //Eventos de clique.
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == botoes[0]) {//Evento acionado após clicar no botão "Entrar" da classe "Login".

            //Verifica o acesso do usuário.
            usuarioDAO = UsuarioDAO.getInstance();
            login = Login.getInstance();
            usuarioLogado = usuarioDAO.consultarUsuario(login.getUsuario().getLogin());

                //Verifica se há campos em branco.
                try{
                   if(login.getUsuario().getLogin().equals("") && login.getUsuario().getSenha().equals("")){
                    throw new Exception();
                    
                   } else if (usuarioLogado.getSenha().equals(login.getUsuario().getSenha())) {
                    if (interfaceTelaPrincipal == null || interfaceTelaPrincipal.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                        interfaceTelaPrincipal = InterfaceFactory.getInterface(TELA_PRINCIPAL, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                        interfaceTelaPrincipal.setVisible(true);
                        login.dispose();
                        
             
                
                         //Primeiro a tabela com os clientes entrando
                        entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
                        modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
                        modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());
                
                        //Depois, as tabelas com os cliente saindo.
                       modeloSaindoHoje = ModeloSaindoHoje.getInstance();
                       modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                       
                       
                       
                       //Valores para os serviços
                        //Busca no banco, os serviços e retorna os totais encontrados.
                       telaPrincipal = TelaPrincipal.getInstance(usuarioLogado);
                       quartoDAO = QuartoDAO.getInstance();
                       int[] totais  = new int[5];
                       totais = quartoDAO.quantidadeServicosQuartos();
                       telaPrincipal.receberQuantias(totais);
                      
                

                        

                      } else {//Caso a interface já tenha sido aberto uma vez.
                        interfaceTelaPrincipal.setVisible(true);
                         }

                      } else { //Caso o usuário nãos seja encontrado no banco de dados e a consulta retorne null.
                         JOptionPane.showMessageDialog(null, "Usuário e senha não correspondem");
                        }
                 }catch(Exception erro){
                    JOptionPane.showMessageDialog(null,"Há campos em branco.");
                }

            //---------------------------------------------------------------------------------------------------------------   
        } else if (evento.getSource() == botoes[1]) {//Evento acionado após clicar no botão "Sair" da classe "TelaPrincipal".
            
                           System.exit(0);
            //----------------------------------------------------------------------------------------------------------------    
        } else if (evento.getSource() == botoes[2]) {//Evento disparado após clicar no botão DTOUsuario na classe "TelaPrincipal".
            if (interfaceConsultarUsuario == null || interfaceConsultarUsuario.isValid()) {
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                usuarioDAO = UsuarioDAO.getInstance();
                modeloUsuario = ModeloUsuario.getInstance();
                
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);

            } else {
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[4]) {//Evento disparado após cliqe no botão "Cadastrar" da classe "CadastarUsuario".
            usuarioDAO = UsuarioDAO.getInstance();
            DTOUsuario usuarioCadastrar;
            modeloUsuario = ModeloUsuario.getInstance();
            cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu,  camposDeTexto, usuarioLogado);

            //Verifica se o usuário já existe no banco.
            usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());

            if (cadastrarUsuario.getUsuario().getLogin().equals("")) {//Caso aperte cadastrar com o campo login da classe "CadastrarUsuario" esteja em branco.
                JOptionPane.showMessageDialog(null, "Digite informações obrigatórias de usuário para cadastro.");

            } else //Caso login não está em branco.
            if (usuarioCadastrar.getSenha() == "") {//Caso não esteja cadastrado no banco.

                if ("".equals(cadastrarUsuario.getUsuario().getLogin()) || "".equals(cadastrarUsuario.getUsuario().getTelefone())
                        || "".equals(cadastrarUsuario.getUsuario().getSenha())) {//Verifica se existem campos obrigatórios em branco.
                    JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco.");

                } else {//Caso não seja cadastrado e nem haja campos em branco, pode cadastrar.
                    
                    boolean cadastrou = false;//Verifica se o cadastro fou realizado com sucesso.
                    cadastrou = usuarioDAO.cadastrarUsuario(cadastrarUsuario.getUsuario());
                    
                    if(cadastrou != false){
                        //Pega o ID do usuario cadastrado.
                        DTOHistoricoUsuario historicoUsuario = new DTOHistoricoUsuario();
                        usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());
                        historicoUsuario.setId_suario_alterado(usuarioCadastrar.getId_usuario());
                        historicoUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        historicoUsuario.setUsuario_alterado(usuarioCadastrar.getLogin());
                        historicoUsuario.setAcao("Cadastrou");
                        
                        //Salva na tabale para auditação.
                        historicoDAO = HistoricoDAO.getInstance();
                        historicoDAO.salvarManterUsuario(historicoUsuario);
                        
                        cadastrarUsuario.dispose();
                        cadastrarUsuario.limparCampos();
                        
                        modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                    }
                    
                }

            } else {//Significa que o usuário já existe no banco de dados.
                JOptionPane.showMessageDialog(null, "Este usuário já existe.");
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[5]) {//Acionao após clique no botão "Usuarios" da clase "TelaPrincipal;
            if (interfaceConsultarUsuario == null || interfaceConsultarUsuario.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                usuarioDAO = UsuarioDAO.getInstance();
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);

                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario = ModeloUsuario.getInstance();
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);

            } else {
                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);
            }

            //-------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[6]) {
           

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[7]) {//Acionado após clique no botão "Editar" da classe "EditarUsuario"
            usuarioDAO = UsuarioDAO.getInstance();
            DTOUsuario usuarioEditar;
            editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);

            //Verifica se o usuário existe no banco.
            usuarioEditar = usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin());

            if (usuarioEditar.getSenha() == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");

            } else//Caso usuário seja encontrado no banco.
                 if (editarUsuario.getUsuario().getTelefone().equals("") || editarUsuario.getUsuario().getSenha().equals("")||
                     editarUsuario.getUsuario().getLogin().equals("") || editarUsuario.getUsuario().getEmail().equals("")) {
                 //Verifica se há campos obrigatórios em branco.
                JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco");

                  } else {
                     
                     boolean editou = false;//Verifica se a edição foi bem sucedida.
                     editou = usuarioDAO.editarUsuario(editarUsuario.getUsuario());
                     
                if(editou != false){
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
        } else if (evento.getSource() == botoes[8]) {//Acionado após clique no botão "Clientes" da classe "Tela Principal".
            if (interfaceCadastrarCliente == null || interfaceCadastrarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarCliente = InterfaceFactory.getInterface(CADASTRAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarCliente.setVisible(true);

            } else {
                cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarCliente.limparCampos();;
                interfaceCadastrarCliente.setVisible(true);
            }

            //---------------------------------------------------------------------------------------------------------------------- 
        } else if (evento.getSource() == botoes[9]) {//Acionado pelo clique no botão "Cadastrar"da classe "CadastrarCliente".
            clienteDAO = ClienteDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            DTOCliente clienteCadastrar;
            cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);

            //Verifica se o cliente já existe no banco.
            clienteCadastrar = clienteDAO.consultarCliente(cadastrarCliente.getCliente());
            
            if(cadastrarCliente.getCliente().getNome().equals("")){//Caso aperte cadastrar com o campo nome da classe "CadastrarCliete" esteja em branco.
                JOptionPane.showMessageDialog(null, "Digite informações obrigatórias de cliente para cadastro.");
                
            } else { //Caso campo nome não esteja em branco.
                
                if(clienteCadastrar.getCpf().equals("")){ //caso não esteja cadastrado no banco.
                    
                      if(cadastrarCliente.getCliente().getCelular().equals("") || cadastrarCliente.getCliente().getCpf().equals("") ||
                         cadastrarCliente.getCliente().getEndereco().equals("")|| cadastrarCliente.getCliente().getEstado().equals("") ||
                         cadastrarCliente.getCliente().getNome().equals("")){ //Verifica se há campos obrigatórios em branco.
                        JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco.");
                        
                      } else{//Caso não seja cadastrado e nem haja campos em branco, pode cadastrar.
                          
                          boolean cadastrou = false; //Verifica se o cadastro foi bem sucedido.
                          cadastrou = clienteDAO.cadastrarCliente(cadastrarCliente.getCliente());
                          
                          if(cadastrou != false){
                              
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
                              modeloCliente.adicionarLista(clienteDAO.listarCliente());
                          }
                          
                      }
                      
                } else{//Caso já exista um cliente no banco.
                    JOptionPane.showMessageDialog(null, "Este CPF encontra-se cadastrado.");
                }
            }

            //---------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[10]) {//Acionado após clique no botão "Clientes" da clase "TelaPrincipal.
             if(interfaceConsultarCliente == null || interfaceConsultarCliente.isValid()){
                interfaceConsultarCliente = InterfaceFactory.getInterface(CONSULTAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
                //Alimenta a tabela com uma lista vindo do método "listarCliente" da classe "clienteDao".
                clienteDAO = ClienteDAO.getInstance();
                modeloCliente = ModeloCliente.getInstance();
                modeloCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceConsultarCliente.setVisible(true);
             } else {
                 //Alimenta a tabela com uma lista vindo do método "listarCliente" da classe "clienteDao".
                 modeloCliente.adicionarLista(clienteDAO.listarCliente());
                 interfaceConsultarCliente.setVisible(true);
             }

            //--------------------------------------------------------------------------------------------------------------------------- 
        } else if (evento.getSource() == botoes[11]){//Acionado pelo botão "Hospedar cliente reservado" da classe "CadastrarHospdagem".
            quartoDAO = QuartoDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            if(cadastrarHospedagem.getHospedagem().getTipo().equals("")){
                JOptionPane.showMessageDialog(null, "   O cliente não esta reservado");
                
            } else {
                //Altera o quarto para "HOSPEDADO"
                         DTOQuarto quartoEditar = quartoDAO.getQuartoPorID(cadastrarHospedagem.getReservaParaHospedar());
                         quartoEditar.setSituacao("HOSPEDADO"); //Passa o quarto de reservado para hospedado.
                         quartoDAO.editarQuarto(quartoEditar);
                         
                         //Depois altera a reserva para hospedagem.
                         DTOReserva reservaEditar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                         reservaEditar.setTipo("HOSPEDAR");
                         boolean editou = false;
                         editou = reservaDAO.editarReserva(reservaEditar);
                         
                         //Salva a hospedagem feita na tabela que audita as hospedagens.
                         if(editou != false){
                             
                             //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
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
           
            
            
            //------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[12]){//Acionado pelo botão "Deletar" da classe "DeletarCliente".
                         
            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[13]){//Acionado pelo botão "Editar" da classe "EditarCliente".
               clienteDAO = ClienteDAO.getInstance();
               DTOCliente clienteEditar;
               editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Verifica se o cliente existe no banco.
               clienteEditar = clienteDAO.consultarCliente(editarCliente.getCliente());
               
               if(clienteEditar.getCelular() == null){
                   JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                   
               } else {//Caso o usuário seja encontrado no banco
                   
                   if(editarCliente.getCliente().getCelular().equals("") || editarCliente.getCliente().getCpf().equals("") ||
                         editarCliente.getCliente().getEndereco().equals("") || editarCliente.getCliente().getNome().equals("") ||
                          editarCliente.getCliente().getEstado().equals("")){//Verifica se há campos obrigatórios em branco.
                         JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco");
                         
                   } else {
                       
                       boolean editou = false;
                       editou = clienteDAO.editarCliente(editarCliente.getCliente());
                       
                       if(editou != false){
                           
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
        } else if(evento.getSource() == botoes[14]){
         //Acionado após clique no botão "Cadastrar" da classe "CadastrarQuarto".
                 quartoDAO = QuartoDAO.getInstance();
                 DTOQuarto quartoCadastrar;
                 cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                 
                 try{
                 //Verifica se o quarto já está cadastrado.
                 quartoCadastrar = quartoDAO.consultarQuarto(Integer.parseInt(cadastrarQuarto.getQuarto().getNumero()));
                 
                 if(cadastrarQuarto.getQuarto().getNumero().equals("")){//Caso o campo "número" da classe "CadastrarQuarto" esteja em branco.
                      JOptionPane.showMessageDialog(null, "Digite informações obrigatórias do quarto para cadastro.");
                      
                 } else{
                     
                     if(quartoCadastrar.getNumero().equals("")){//Caso o quarto não esteja cadastrado.
                         if(cadastrarQuarto.getQuarto().getNumero().equals("") || cadastrarQuarto.getQuarto().getSituacao().equals("") || 
                            cadastrarQuarto.getQuarto().getOcupantes().equals("") || cadastrarQuarto.getQuarto().getDiaria().equals("") ||
                            cadastrarQuarto.getQuarto().getCategoria().equals("")){
                             JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco.");
                             
                         } else {
                             
                             boolean cadastrou = false; //Reponsável por controlar se o cadastro foi bem sucedido.
                             cadastrou = quartoDAO.cadastrarQuarto(cadastrarQuarto.getQuarto());
                            
                             
                             if(cadastrou != false){//Se o cadastro foi bem sucedido, este método registra na tabela "manter_quarto" quem cadastrou e qual quarto foi cadastrado.
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
                         JOptionPane.showMessageDialog(null, "Quarto já existe no banco.");
                     }
                 }
                 }catch(Exception erro){
                     JOptionPane.showMessageDialog(null, "Digite informações obrigatórias do quarto para cadastro.");
                 }
            
            
            //-----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[15]){//Acionado após clique no botão "Quartos" da classe "TelaPrincipal".
            if(interfaceConsultarQuarto == null || interfaceConsultarQuarto.isValid()){
                interfaceConsultarQuarto = InterfaceFactory.getInterface(CONSULTAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarQuarto.setVisible(true);
                
                
                
                //Aciona o método para listar todos os quartos.
                quartoDAO = QuartoDAO.getInstance();
                modeloQuarto = ModeloQuarto.getInstance();
                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);
                
            } else{
                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);
            }
            
            //------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[16]){//Acionado pelo botão Deletar da classe "DeletarQuarto".
            
            
            //--------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[17]){//Acionado pelo botão 'Editar" da classe "EditarQuarto"
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quartoEditar;
            editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            //Verifica se o usuário existe no banco.
            quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(editarQuarto.getQuarto().getNumero()));
            if(editarQuarto.getQuarto().getNumero().equals("")){//Verifica se o campo "Numero" da classe "EditarQuarto" está em branco.
                JOptionPane.showMessageDialog(null, "Digite informações obrigatórias do quarto para cadastro.");
                
            } else {
                
                
                if(quartoEditar == null){//Caso o quarto não esteja cadastrado.
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                    
                } else if(editarQuarto.getQuarto().getNumero().equals("")   || editarQuarto.getQuarto().getSituacao().equals("") ||
                   editarQuarto.getQuarto().getOcupantes().equals("") || editarQuarto.getQuarto().getDiaria().equals("") ||
                   editarQuarto.getQuarto().getCategoria().equals("") || editarQuarto.getQuarto().getSituacao().equals("")){//Verifica se há campos obrigatórios em branco.
                    JOptionPane.showMessageDialog(null, "Há campos obrigatórios em branco.");
                    
                } else {
                    boolean editou = false;//Responsável por controlar se a edição foi bem sucedida.
                    editou = quartoDAO.editarQuarto(editarQuarto.getQuarto());
                    editarQuarto.dispose();
                    editarQuarto.limparCampos();
                    
                    if(editou != false){//Se a edição foi bem sucedida, este método registra na tabela "manter_quarto" quem editou e qual quarto foi editado.
                        
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
            } catch(Exception erro){
                    JOptionPane.showMessageDialog(null, "Quarto não encontrado.");
              }
            
            //--------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[18]){
               if(interfaceSelecionarCliente == null || interfaceSelecionarCliente.isValid()){
                  interfaceSelecionarCliente = InterfaceFactory.getInterface(SELECIONAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  clienteDAO = ClienteDAO.getInstance();
                  selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
        } else if(evento.getSource() == botoes[19]){ //Acionado aós clique no botão "Reservar" da classe "CadastrarReserva".
               
                  
                  
                  //Verifica se o cliente está cadastrado
                  clienteDAO = ClienteDAO.getInstance();
                  cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  DTOCliente cliente = clienteDAO.consultarCliente(cadastrarReserva.getCliente());
                  
                  if(cliente == null){
                      JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                      
                  } else {
                      
                      try{
                      if(cadastrarReserva.getReserva().getEntrada() == null || cadastrarReserva.getReserva().getSaida()== null) {//Verifica se há campos obrigatórios em branco.
                          JOptionPane.showMessageDialog(null, "Há campos em branco.");
                          
                        
                      } else {
                          
                                  if(interfaceSelecionarQuarto == null || interfaceSelecionarQuarto.isValid()){
                                     interfaceSelecionarQuarto = InterfaceFactory.getInterface(SELECIONAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                
                                      //Aciona o método para listar todos os quartos.
                                      quartoDAO = QuartoDAO.getInstance();
                                      modeloSelecionarQuarto = ModeloSelecionarQuarto.getInstance();
                                      modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuarto());
                             
                                      //Informa o tipo de reserva.
                                      selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                      selecionarQuarto.setTipoReserva("RESERVAR");
                             
                                      interfaceSelecionarQuarto.setVisible(true);
                                    } else {                              
                              
                                            //Informa o tipo de reserva.
                                           selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                           selecionarQuarto.setTipoReserva("RESERVAR");
                                           modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuarto());
                                           interfaceSelecionarQuarto.setVisible(true);
                                        }
                                }
                       
                       }catch(Exception erro){
                              JOptionPane.showMessageDialog(null, "Erro: "+erro.getMessage());
                              }
                  }
                  
                  
              
            
            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[20]){//Acionado pelo botão Selecionar da classe "SelecionarQuato".
            //1.Primeiro altera o quarto.
            
            quartoDAO = QuartoDAO.getInstance();
            selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            DTOQuarto quartoEditar;
            quartoEditar = selecionarQuarto.getQuarto();
            
            if(selecionarQuarto.getTipoReserva().equals("RESERVAR")){//Significa que é uma reserva do tipo "reservar".
            
            try{
            if(selecionarQuarto.getQuarto().getNumero().equals("")){//Caso o usuário não selecione nehum quarto e aperte "Selecionar" da classe "SelecionarQuarto".
                JOptionPane.showMessageDialog(null, "É necesário escolher um quarto.");
                
            } else {
                
                if(quartoEditar.getSituacao().equals("RESERVADO") || quartoEditar.getSituacao().equals("HOSPEDADO") || 
                   quartoEditar.getSituacao().equals("MANUTENCAO") || quartoEditar.getSituacao().equals("ORGANIZANDO")){//Verifica se o quarto já está sendo utilizado e não permite reservar.
                           JOptionPane.showMessageDialog(null, "Este quarto não pode ser escolhido.");
                           
                } else{
                   
                quartoEditar.setSituacao("RESERVADO");
                quartoDAO.editarQuarto(quartoEditar);
                //pegando o ID do quarto para associar a reserva.
                quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(quartoEditar.getNumero()));
            
                //2.depois, cadastra a reserva.
                reservaDAO = ReservaDAO.getInstance();
                cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                DTOReserva reservaCadastrar;
                reservaCadastrar = cadastrarReserva.getReserva();
                reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());
           
                boolean reservou = false;//Responsável por verificar se conseguiu reservar.
                DTOCliente cliente;
                cliente = cadastrarReserva.getCliente();
                reservou = reservaDAO.cadastrarReserva(reservaCadastrar, cliente, quartoEditar);
                
                if(reservou != false){
                    
                    //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
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
                }
                             
            }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "É necesário escolher um quarto.");
                  }
            
            } else { //Significa que é uma reserva do tipo "hospedar"
                
                try{

                
                if(quartoEditar.getSituacao().equals("RESERVADO") || quartoEditar.getSituacao().equals("HOSPEDADO") || 
                   quartoEditar.getSituacao().equals("MANUTENCAO") || quartoEditar.getSituacao().equals("ORGANIZANDO")){//Verifica se o quarto já está sendo utilizado e não permite reservar.
                           JOptionPane.showMessageDialog(null, "Este quarto não pode ser escolhido.");
                           
                } else {
                    
                quartoEditar.setSituacao("HOSPEDADO");
                quartoDAO.editarQuarto(quartoEditar);
                //pegando o ID do quarto para associar a reserva.
                quartoEditar = quartoDAO.consultarQuarto(Integer.parseInt(quartoEditar.getNumero()));
            
                //2.depois, cadastra a reserva.
                reservaDAO = ReservaDAO.getInstance();
                cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                DTOReserva reservaCadastrar;
                reservaCadastrar = cadastrarHospedagem.getHospedagem();
                reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());
                reservaCadastrar.setTipo("HOSPEDAR");
            
                DTOCliente cliente;
                cliente = cadastrarHospedagem.getCliente();
                
                boolean hospedou = false;
                hospedou = reservaDAO.cadastrarHospedagem(reservaCadastrar, cliente, quartoEditar); 
                
                if(hospedou != false){
                    
                    //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
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
                }
                             
            
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "É necesário escolher um quarto.\n"+erro.getMessage());
                  }
                 
        }
            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[21]){
               //Acionado pelo botão "Reservas" da classe "TelaPrincipal".
               if(interfaceConsultarReserva == null || interfaceConsultarReserva.isValid()){
                  interfaceConsultarReserva = InterfaceFactory.getInterface(CONSULTAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  
                  //Aciona o método para listas todas as reservas.
                  reservaDAO = ReservaDAO.getInstance();
                  modeloReserva = ModeloReserva.getInstance();
                  modeloReserva.adicionarLista(reservaDAO.listarReservas());
                  interfaceConsultarReserva.setVisible(true);

               } else {
                   modeloReserva.adicionarLista(reservaDAO.listarReservas());
                   interfaceConsultarReserva.setVisible(true);
               }
            
            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[22]){//Acionado pelo botão "Deletar" da classe "DeletarReserva".
            
        
              
            //--------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[23]){//Acionado pelo botão Editar da classe "EditarReserva".
            reservaDAO.getInstance();
            editarReserva = EditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            if(editarReserva.getReserva().getEntrada()==null ||
               editarReserva.getReserva().getSaida()==null){
                JOptionPane.showMessageDialog(null, "Reserva não encontrada. Certifique-se se o cliente está reservado.");
                
            } else {
                
                //Verifica se as datas são válidas dentro da regra de negócio.
                if(editarReserva.getReserva().getEntrada().getDay() < dataAtual.getDay() || 
                   editarReserva.getReserva().getSaida().before(editarReserva.getReserva().getEntrada())){
                   JOptionPane.showMessageDialog(editarReserva, "Data para reserva inválida");
                   } else {
                
                        boolean editou = false;
                        editou = reservaDAO.editarReserva(editarReserva.getReserva());
                
                        if(editou != false){
                           //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
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
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Reserva não encontrada.");
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[24]){
            //Acionado pelo botão "Atualizar Dados" da classe "TelaPrincipal".
             //Primeiro a tabela com os clientes entrando
                        entrandoOuSaindoDAO = EntrandoOuSaindoDAO.getInstance();
                        modeloEntrandoHoje = ModeloEntrandoHoje.getInstance();
                        modeloEntrandoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteEntrandoHoje());
                
                        //Depois, as tabelas com os cliente saindo.
                       modeloSaindoHoje = ModeloSaindoHoje.getInstance();
                       modeloSaindoHoje.adicionarLista(entrandoOuSaindoDAO.exibirClienteSaindoHoje());
                       
                       
                       
                       //Valores para os serviços
                        //Busca no banco, os serviços e retorna os totais encontrados.
                       telaPrincipal = TelaPrincipal.getInstance(usuarioLogado);
                       quartoDAO = QuartoDAO.getInstance();
                       int[] totais  = new int[5];
                       totais = quartoDAO.quantidadeServicosQuartos();
                       telaPrincipal.receberQuantias(totais);
           
            
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[25]){//Acionado pelo botão Hospedar da classe "Telaprincipal".
                 if(interfaceSelecionarCliente == null || interfaceSelecionarCliente.isValid()){
                  interfaceSelecionarCliente = InterfaceFactory.getInterface(SELECIONAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  clienteDAO = ClienteDAO.getInstance();
                  selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
        } else if(evento.getSource() == botoes[26]){//Acionado pelo botão Hospedagem da classe "TelaPrincipal".
            if(interfaceConsultarHospedagem == null || interfaceConsultarHospedagem.isValid()){
               interfaceConsultarHospedagem = InterfaceFactory.getInterface(CONSULTAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
                 //Aciona o método para listas todas as hospedagens.
                  reservaDAO = ReservaDAO.getInstance();
                  modeloHospedagem = ModeloHospedagem.getInstance();
                  modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                  interfaceConsultarHospedagem.setVisible(true);
                  
               
            } else {
                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);
            }
            
            //-----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[27]){//Acionado pelo botão editar da classe "EditarHospedagem".
            reservaDAO = ReservaDAO.getInstance();
            editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Caso o campo, forma de pagamento estiver em braqnco, significa que não há reserva naquele nome de cliente.
            try{
            if(editarHospedagem.getHospedagem().getSaida() == null|| editarHospedagem.getHospedagem().getEntrada() == null){
                JOptionPane.showMessageDialog(null, "Hospedagem não encontrada. Certifique-se se o cliente está hospedado.");
                
            } else {
                
                //Verifica se as datas são válidas dentro da regra de negócio.
                if(editarHospedagem.getHospedagem().getEntrada().getDay() < dataAtual.getDay() || 
                   editarHospedagem.getHospedagem().getSaida().before(editarHospedagem.getHospedagem().getEntrada())){
                   JOptionPane.showMessageDialog(editarHospedagem, "Data para reserva inválida");
                   } else {
                
                           boolean editou = false;
                          editou = reservaDAO.editarHospedagem(editarHospedagem.getHospedagem());
                
                       if(editou != false){
                        //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
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
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Hospedagem não encontrada.");
            }
            
            //----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[28]){//Acionado pelo botão Deletar da classe "DeletarHospedagem".
             /* quartoDAO = QuartoDAO.getInstance();
              reservaDAO = ReservaDAO.getInstance();
              deletarHospedagem= DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
              
               //Alterando o quarto para livre.
               DTOReserva reserva = deletarHospedagem.getHospedagem();//Pegando o ID do quarto para ser alterado.
               
               DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
               
               try{
               //Verifica se o quarto foi encontrado. Caso não seja, exibe uma mensagem de alerta sobre o nome fornecido no campo nome, visto que aconterá um erro por causa do campo em branco.
               if(quarto.getNumero().equals("")){
                       
               } else  {
               quarto.setSituacao("LIVRE");
               quartoDAO.editarQuarto(quarto);
               
               //Em sequência, deletar a reserva.
               boolean deletou = false;
               deletou = reservaDAO.deletarHospedagem(reserva);
               
               if(deletou != false){
                   
                     //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    AuditarHospedagem auditarHospedagem = new AuditarHospedagem();
                    auditarDAO = HistoricoDAO.getInstance();
                    auditarHospedagem.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    auditarHospedagem.setLogin(usuarioLogado.getLogin());
                    auditarHospedagem.setAcao("Deletou");
                    auditarHospedagem.setTipo_reserva("HOSPEDAR");
                    
                    //Obter o ID da reserva.
                    auditarHospedagem.setId_hospedagem(deletarHospedagem.getHospedagem().getId_reserva());
                    auditarHospedagem.setCliente_hospedado(deletarHospedagem.getCliente().getNome());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterHospedagem(auditarHospedagem);
                   
               deletarHospedagem.dispose();
               deletarHospedagem.limparCampos();
               }
               }
               }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, "Hospedagem não encontrada. Certifique-se se o cliente está reservado.");
               }
              
            */
            //----------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[29]){//Acionado pelo botão "Hospedar" da classe "CadastrarHospedagem".
            
                  clienteDAO = ClienteDAO.getInstance();
                  cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  DTOCliente cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente());
                 
                  //Verifica se o cliente está cadastrado
                  if(cliente == null){
                      JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                      
                  } else {
                      
                      try{//Verifica se há campos em branco antes de hospedar.
                      if(cadastrarHospedagem.getHospedagem().getSaida()== null || cadastrarHospedagem.getHospedagem().getEntrada()== null){//Verifica se há campos obrigatórios em branco.
                          JOptionPane.showMessageDialog(null, "Há campos em branco.");
                          
                      } else {
                          
                          //Verifica se a data esta dentro do período estipulado na regra de negócio
                           if(cadastrarHospedagem.getHospedagem().getEntrada().getDay() < dataAtual.getDay() || 
                              cadastrarHospedagem.getHospedagem().getSaida().before(cadastrarHospedagem.getHospedagem().getEntrada())){
                               JOptionPane.showMessageDialog(null, "Dara para hospedagem inválida.");
                        
                            } else {
                          
                                  if(interfaceSelecionarQuarto == null || interfaceSelecionarQuarto.isValid()){//Exibe a classe para selecionar o quarto.
                                  interfaceSelecionarQuarto = InterfaceFactory.getInterface(SELECIONAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                
                                  //Aciona o método para listar todos os quartos.
                                  quartoDAO = QuartoDAO.getInstance();
                                  modeloSelecionarQuarto = ModeloSelecionarQuarto.getInstance();
                                  modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuarto());
                             
                                  //Informa o tipo de reserva.
                                  selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                  selecionarQuarto.setTipoReserva("HOSPEDAR");
                             
                                  interfaceSelecionarQuarto.setVisible(true);
                                } else {
                                     //Informa o tipo de reserva.
                                     selecionarQuarto = SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                                     selecionarQuarto.setTipoReserva("HOSPEDAR");
                                     modeloSelecionarQuarto.adicionarLista(quartoDAO.listarQuarto());
                                     interfaceSelecionarQuarto.setVisible(true);
                                   }
                           }
                      }
                      }catch(Exception erro){
                              JOptionPane.showMessageDialog(null, "Erro: "+erro.getMessage());
                              }
                  }
                  
            //-----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[30]){//Acinado pelo botão "Fechar Hospedagem" da classe "TelaPrincipal"
            if(interfaceFecharHospedagem == null || interfaceFecharHospedagem.isValid()){
               interfaceFecharHospedagem = InterfaceFactory.getInterface(FECHAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceFecharHospedagem.setVisible(true);
               modeloPasseiosRealizados = ModeloPasseiosRealizados.getInstance();
               modeloProdutosConumidos = ModeloProdutosConsumidos.getInstance();
               
               modeloProdutosConumidos.limparDados();
               modeloPasseiosRealizados.limparDados();
            } else{
                fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                fecharHospedagem.limparCampos();
                 modeloPasseiosRealizados.limparDados();
                 modeloProdutosConumidos.limparDados();
                 interfaceFecharHospedagem.setVisible(true);
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[31]){//Acionado pelo botões entrando ou saindo da classe "TelaPrincipal.
            if(interfaceEntrandoOuSaindo == null || interfaceEntrandoOuSaindo.isValid()){
                interfaceEntrandoOuSaindo = InterfaceFactory.getInterface(ENTRANDO_OU_SAINDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
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
        } else if(evento.getSource() == botoes[32]){//Acionado pelo botões saindo ou saindo da classe "TelaPrincipal.
            if(interfaceEntrandoOuSaindo == null || interfaceEntrandoOuSaindo.isValid()){
                interfaceEntrandoOuSaindo = InterfaceFactory.getInterface(ENTRANDO_OU_SAINDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
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
        }  else if(evento.getSource() == botoes[33]){
                //Acionado pelo botão "Pesquisar" da classe "ConsultarUsuario".
                usuarioDAO = UsuarioDAO.getInstance();
                consultarUsuario = ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloUsuario = ModeloUsuario.getInstance();
                
                try{
                if(consultarUsuario.getPesquisa().equals("")){
                    JOptionPane.showMessageDialog(null,"Digite um valor válido para pesquisa.");
                    
                } else {
                    //Adicionando o usuário pesquisado a tabela que exibe os usuários.
                modeloUsuario.adicionar(usuarioDAO.consultarUsuario(consultarUsuario.getPesquisa()));
                
                //Atualizando janela.
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarUsuario.setVisible(true);
                }
                }catch(Exception erro){
                    
                }
                
                
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[34]){//Botão Adicionar da clase "ConsultaUsuario".
                if (interfaceCadastrarUsuario == null || interfaceCadastrarUsuario.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarUsuario = InterfaceFactory.getInterface(CADASTRAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarUsuario.setVisible(true);

            } else {
                cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarUsuario.limparCampos();
                interfaceCadastrarUsuario.setVisible(true);
            }
                
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[35]){//Botão pesquisar da classe "ConsultarCliente".
                clienteDAO = ClienteDAO.getInstance();
                consultarCliente = ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloCliente = ModeloCliente.getInstance();
                
                try{
                    if(consultarCliente.getPesquisa().getCpf().equals("   .   .   -  ") &&
                       consultarCliente.getPesquisa().getNome().equals("")){
                        JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
                        
                    } else {
                        
                if(!consultarCliente.getPesquisa().getCpf().equals("   .   .   -  ") && consultarCliente.getPesquisa().getNome().equals("")){
                    //Significa que procurará por CPF
                    modeloCliente.adicionar(clienteDAO.consultarCliente(consultarCliente.getPesquisa()));
                    
                } else { //Procurará por nome.
                //Adicionando o usuário pesquisado a tabela que exibe os usuários.
                modeloCliente.adicionarLista(clienteDAO.ConsultarClienteNome(consultarCliente.getPesquisa()));
                }
                //Atualizando janela.
                interfaceConsultarCliente = InterfaceFactory.getInterface(CONSULTAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarCliente.setVisible(true);
                    }
                }catch(Exception erro){
                    
                }
                
            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[36]){//Botão listar da classe "ConsultarCliente".
                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceConsultarCliente.setVisible(true);
                
             //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[37]){//Botão pesquisar da classe consultarReserva.
                try{
                consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                if(consultarReserva.getPesquisa().getCpf().equals("   .   .   -  ") &&
                   consultarReserva.getPesquisa().getNome().equals("")){
                    new Exception();
                    
                } else {
                reservaDAO = ReservaDAO.getInstance();
                consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloReserva = ModeloReserva.getInstance();
                //Obtendo cliente.
                clienteDAO = ClienteDAO.getInstance();
                DTOCliente cliente = clienteDAO.consultarCliente(consultarReserva.getPesquisa());
                
                if(!consultarReserva.getPesquisa().getCpf().equals("   .   .   -  ") && consultarCliente.getPesquisa().getNome().equals("")){
                    //Significa que procurará por CPF
                    modeloReserva.adicionar(reservaDAO.consultarReserva(consultarReserva.getPesquisa().getId_cliente()));
                    
                } else {
                    modeloReserva.adicionarLista(reservaDAO.consultarReservaNome(cliente));
                }
                //Adicionando o quarto pesquisado a tabela que exibe os quartos.
                modeloReserva = ModeloReserva.getInstance();
                modeloReserva.adicionar(reservaDAO.pesquisarReserva(cliente));
                
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
            }
                
                
        } else if(evento.getSource() == botoes[38]){//Botão Listar da classe consultrReserva.
                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloReserva.adicionarLista(reservaDAO.listarReservas());
                interfaceConsultarReserva.setVisible(true);
               
        } else if(evento.getSource() == botoes[39]){//Botão pesquisar da classe consultarHospedagem.
            //Primeiro pega o cliente.
                clienteDAO = ClienteDAO.getInstance();
                consultarHospedagem = ConsultarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                DTOCliente cliente = clienteDAO.consultarCliente(consultarHospedagem.getPesquisa());
                
                //Agora, procura a reserva pelo cliente.
                reservaDAO = ReservaDAO.getInstance();
                modeloHospedagem.adicionar(reservaDAO.pesquisarHospedagem(cliente));
                
                interfaceConsultarHospedagem = InterfaceFactory.getInterface(CONSULTAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarHospedagem.setVisible(true);
                
            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[40]){//Botão listar da classe consultarHospedagem.
            //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);
                
            //--------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[41]){//Acionado pelo botão Fechar da classe Fechar Hospedagem.
            JOptionPane.showMessageDialog(null, "Ainda não há uma funcionalidade planejada");
            
            //---------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[42]){//Acionado pelo botão Passeios da clase Tela principal
           if(interfaceSelecionarPasseioConsulta == null || interfaceSelecionarPasseioConsulta.isValid()){
               interfaceSelecionarPasseioConsulta = InterfaceFactory.getInterface(CONSULTAR_PASSEIO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
               modeloPasseio = ModeloPasseio.getInstance();
               passeioDAO = PasseioDAO.getInstance();
               
               modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
               interfaceSelecionarPasseioConsulta.setVisible(true);
               
            } else {
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            }
            
            //---------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[43]){//Acionado pelo botão Consultar Passeios da classe Tela Principal.
            if(interfaceSelecionarPasseioConsulta == null || interfaceSelecionarPasseioConsulta.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceSelecionarPasseioConsulta = InterfaceFactory.getInterface(SELECIONAR_PASSEIOS_CONSULTA, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                passeioDAO = PasseioDAO.getInstance();
                
                modeloPasseio = ModeloPasseio.getInstance();
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            } else {
                modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
                interfaceSelecionarPasseioConsulta.setVisible(true);
            }
            //---------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[44]){
            //Acionado pelo menu Consultar Produto da classe Tela Principal.
            if(interfaceConsultarProduto == null || interfaceConsultarProduto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                produtoDAO = ProdutoDAO.getInstance();
                interfaceConsultarProduto = InterfaceFactory.getInterface(CONSULTAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                
                //Adiciono a lista de produtos do banco a tabela da classe ModeloProduto.
                modeloProduto = ModeloProduto.getInstance();
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
                
                
            } else {
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            }
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[45]){
            //Acionado pelo menu Pedido de DTOQuarto da classe Tela Principal.
            if(interfaceCadastraPedidoQuarto == null || interfaceCadastraPedidoQuarto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastraPedidoQuarto = InterfaceFactory.getInterface(PEDIDO_QUARTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
                modeloPedidosDeQuarto.limparDados();
                cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarPedidoQuarto.limparCampos();
                interfaceCadastraPedidoQuarto.setVisible(true);
            } else {
                cadastrarPedidoQuarto.limparCampos();
                modeloPedidosDeQuarto.limparDados();
                interfaceCadastraPedidoQuarto.setVisible(true);
            }
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[47]){
            //Acionado pelo botão cadastrar da classe CadastrarProduto
            cadastrarProduto = CadastrarProduto.getInstance(botoes, itensDeMenu, camposDeTexto,usuarioLogado);
            DTOProduto produto;
            produto = cadastrarProduto.getProduto();
            produtoDAO = ProdutoDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            
            
            boolean cadastrou = false;
            cadastrou = produtoDAO.cadastrarProduto(produto);
            
            try{
                
                if(cadastrou != false ){
                    DTOHistoricoProduto historicoProduto = new DTOHistoricoProduto();
                    DTOProduto produtoCadastrar = produtoDAO.consultarProduto(cadastrarProduto.getProduto().getNome());
                    historicoProduto.setId_produto(produtoCadastrar.getId());
                    historicoProduto.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    historicoProduto.setAcao("Cadastrou");
                    historicoProduto.setProduto(produtoCadastrar.getNome());
                    
                    historicoDAO.salvarManterProduto(historicoProduto);
                }
            }catch(Exception erro){
                
            }
           
            
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
            cadastrarProduto.limparCampos();
            cadastrarProduto.dispose();
            modeloProduto = ModeloProduto.getInstance();
            modeloProduto.adicionarLista(produtoDAO.listarProduto());
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[46]){
            //Acionado pelo botão Selecionar da classe SelecionarPasseioConsult
            /*if(interfacePasseios == null || interfacePasseios.isValid()){
                interfacePasseios = InterfaceFactory.getInterface(CONSULTAR_PASSEIO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                passeioDAO.getInstance();
                passageiroDAO = PassageiroDAO.getInstance();
                modeloPasseio = ModeloPasseio.getInstance();
                modeloPassageiro = ModeloPassageiros.getInstance();
                consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                selecionarPasseioConsulta = SelecionarPasseioConsulta.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
                //Primeiro recupero o passeio selecionado. Após, envio o passeio para a classe Passeio.
                consultarPasseio.receberPasseio(passeioDAO.consultarPasseio(selecionarPasseioConsulta.getPasseio().getTitulo()));
                modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(selecionarPasseioConsulta.getPasseio()));
                
                
                interfaceSelecionarPasseioConsulta.dispose();
                interfacePasseios.setVisible(true);
                
            } else {
                consultarPasseio.receberPasseio(passeioDAO.consultarPasseio(selecionarPasseioConsulta.getPasseio().getTitulo()));
                modeloPassageiro.adicionarLista(passageiroDAO.consultarPassageiros(selecionarPasseioConsulta.getPasseio()));
                interfaceSelecionarPasseioConsulta.dispose();
                interfacePasseios.setVisible(true);
            }*/
            
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[48]){
            //Acionado pelo botão Editar da classe EditarProduto
           editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
           produtoDAO = ProdutoDAO.getInstance();
           historicoDAO = HistoricoDAO.getInstance();
           
           boolean editou = false;
           editou = produtoDAO.editarProduto(editarProduto.getProduto());
            
            try{
                if(editou != false){
                DTOHistoricoProduto historicoProduto = new DTOHistoricoProduto();
                DTOProduto produtoEditar = produtoDAO.consultarProduto(editarProduto.getProduto().getNome());
                historicoProduto.setId_produto(produtoEditar.getId());
                historicoProduto.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                historicoProduto.setAcao("Editou");
                historicoProduto.setProduto(produtoEditar.getNome());
                
                historicoDAO.salvarManterProduto(historicoProduto);
                }
            }catch(Exception erro){
                
            }
           
           
           editarProduto.limparCampos();
           editarProduto.dispose();
           modeloProduto = ModeloProduto.getInstance();
           modeloProduto.adicionarLista(produtoDAO.listarProduto());
            
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[49]){
           /* //Acionado pelo botão Deletar da classe DeletarProduto
            deletarProduto = DeletarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            produtoDAO.deletarProduto(deletarProduto.getProduto());
            deletarProduto.limparCampos();
            deletarProduto.dispose();
            
               */
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[50]){
            //Acionado pelo botão Cadastrar da classe CadastrarPasseio
            cadastrarPasseio = CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            
            boolean cadastrou = false;
            cadastrou = passeioDAO.cadastrarPasseio(cadastrarPasseio.getPasseio());
            
            try{
                if(cadastrou != false){
                DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                DTOPasseio passeioCadastrar = passeioDAO.consultarPasseio(cadastrarPasseio.getPasseio().getTitulo());
                historicoPasseio.setId_passeio(passeioCadastrar.getId());
                historicoPasseio.setPasseio(passeioCadastrar.getTitulo());
                historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                historicoPasseio.setAcao("Cadastrou");
                
                historicoDAO.salvarManterPasseio(historicoPasseio);
                }
            }catch(Exception erro){
                
            }
             
            
            cadastrarPasseio.limparCampos();
            cadastrarPasseio.dispose();
            
            modeloPasseio = ModeloPasseio.getInstance();
            modeloPasseio.adicionarLista(passeioDAO.listarPasseio());

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[51]){
            //Acionado pelo botão Editar da classe EditarPasseio
            editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            historicoDAO = HistoricoDAO.getInstance();
            
            
            
             boolean editou = false;
             editou = passeioDAO.editarPasseio(editarPasseio.getPasseio());
            
            try{
                if(editou != false){
                DTOHistoricoPasseio historicoPasseio = new DTOHistoricoPasseio();
                DTOPasseio passeioEditar = passeioDAO.consultarPasseio(editarPasseio.getPasseio().getTitulo());
                historicoPasseio.setId_passeio(passeioEditar.getId());
                historicoPasseio.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                historicoPasseio.setPasseio(passeioEditar.getTitulo());
                historicoPasseio.setAcao("Editou");
                
                historicoDAO.salvarManterPasseio(historicoPasseio);
                }
            }catch(Exception erro){
                
            }
            
            editarPasseio.limparCampos();
            editarPasseio.dispose();
            
            modeloPasseio = ModeloPasseio.getInstance();
            modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[52]){
            //Acionado pelo botão Deletar da classe DeletarPasseio
            /*deletarPasseio = DeletarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            passeioDAO.deletarPasseio(deletarPasseio.getPasseio());
            
            deletarPasseio.limparCampos();
            deletarPasseio.dispose();*/
    

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[53]){
            //Acionado pelo botão Selecionar da classe SelecionarPasseioCadastrarPasseio
            selecionarPasseioCadastrarPassageiro = SelecionarPasseioCadastrarPassageiro.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
    
            //Enviando o passeio escolido para a classe que adiciona passeio.
            adicionarPassageiroPasseio.receberPasseio(selecionarPasseioCadastrarPassageiro.getPasseio());
            
            selecionarPasseioCadastrarPassageiro.dispose();
            adicionarPassageiroPasseio.setVisible(true);
            modeloPassageiros = ModeloPassageiros.getInstance();
            modeloPassageiros.limparDados();

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[54]){
            //Acionado pelo botão Adicionar da classe AdicionarPassageiroPasseio
            //Pegando o passageiro para adicionar na lista.
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            modeloPassageiro = ModeloPassageiros.getInstance();
            modeloPassageiro.adicionar(adicionarPassageiroPasseio.getPassageiro());
            adicionarPassageiroPasseio.limparPassageiros();

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[55]){
            //Acionado pelo botão Remover da classe AdicionarPassageiroPasseio
            //Obtendo linha para remover
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            modeloPassageiro = ModeloPassageiros.getInstance();
            modeloPassageiro.remover(adicionarPassageiroPasseio.getPassageiroRevomer());
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[56]){
            //Acionado pelo botão Cadastrar da classe AdicionarPassageiroPasseio
            passageiroDAO = PassageiroDAO.getInstance();
            modeloPassageiro = ModeloPassageiros.getInstance();
            
            for(int posicao = 0; posicao<modeloPassageiro.getPassageiros().size(); posicao ++){
                passageiroDAO.cadastrarPassageiro(modeloPassageiro.getPassageiro(posicao));
            }
            
            adicionarPassageiroPasseio.dispose();
            adicionarPassageiroPasseio.limparDados();
            modeloPassageiro.limparDados();
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[57]){
            //Acionado pelo Remover da classe Passeios
            
            
            
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[58]){
            //Acionado pelo Adicionar da classe CadastrarPedidoQuarto
           if (interfaceProdutoPedido == null || interfaceProdutoPedido.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceProdutoPedido = InterfaceFactory.getInterface(PRODUTO_PEDIDO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                produtoPedido = ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
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
       } else if(evento.getSource() == botoes[59]){
            //Acionado pelo Selecionar da classe CadastrarPedidoQuarto
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
            produtoPedido = ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            modeloPedidosDeQuarto.adicionar(produtoPedido.getProduto());
            cadastrarPedidoQuarto.setVisible(true);
            
            produtoPedido.dispose();
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[60]){
            //Acionado pelo Remover da classe ConsultarPedidoQuarto
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
            consultarPedidoQuarto = ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            produtoDAO.deletarProduto(modeloPedidosDeQuarto.getProduto(consultarPedidoQuarto.getIndiceRemover()));
            modeloPedidosDeQuarto.remover(consultarPedidoQuarto.getIndiceRemover());
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[61]){
            //Acionado pelo Cadastrar da classe CadastrarPedidoQuarto
            produtoDAO = ProdutoDAO.getInstance();
            modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            DTOProduto produtoParaSubtrair, produtoPedidoHospedagem;
            
             for(int posicao = 0; posicao<modeloPedidosDeQuarto.getPedidosDeQuarto().size(); posicao ++){
                produtoDAO.cadastrarProdutoPedido(cadastrarPedidoQuarto.getReserva(),modeloPedidosDeQuarto.getProduto(posicao) );
                
                //Subtraindo a quantidade do produto pedido pela quantidade que existe no banco de dados.
                produtoParaSubtrair = produtoDAO.consultarProduto(modeloPedidosDeQuarto.getProduto(posicao).getNome());
                produtoPedidoHospedagem = modeloPedidosDeQuarto.getProduto(posicao);
                
                //Subtraindo a quantidade.
                produtoParaSubtrair.setQuantidade(produtoParaSubtrair.getQuantidade() - produtoPedidoHospedagem.getQuantidade());
                
                produtoDAO.editarProduto(produtoParaSubtrair);
            }
            
            cadastrarPedidoQuarto.dispose();
            cadastrarPedidoQuarto.limparCampos();
            modeloPedidosDeQuarto.limparDados();
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[62]){
            //Acionado pelo Pesquiar da classe ConsultarQuarto
            
            try{
                consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                if(consultarQuarto.getPesquisa() == 0){
                    new Exception();
                    
                } else {
                quartoDAO = QuartoDAO.getInstance();
                consultarQuarto = ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloQuarto = ModeloQuarto.getInstance();
                
                //Adicionando o quarto pesquisado a tabela que exibe os quartos.
                modeloQuarto.adicionar(quartoDAO.consultarQuarto(consultarQuarto.getPesquisa()));
                
                //Atualizando janela.
                interfaceConsultarQuarto = InterfaceFactory.getInterface(CONSULTAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarQuarto.setVisible(true);
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[63]){
           //Evento acionado pelo Cadastrar da classe "ConsultarQuarto".
            if(interfaceCadastrarQuarto == null || interfaceCadastrarQuarto.isValid()){
                interfaceCadastrarQuarto = InterfaceFactory.getInterface(CADASTRAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarQuarto.setVisible(true);
                
            } else{
                cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarQuarto.limparCampos();
                interfaceCadastrarQuarto.setVisible(true);
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[64]){
           //Evento acionado pelo Produto da classe "TelaPrincipal".
            if(interfaceConsultarProduto == null || interfaceConsultarProduto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceConsultarProduto = InterfaceFactory.getInterface(CONSULTAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                modeloProduto = ModeloProduto.getInstance();
                produtoDAO = ProdutoDAO.getInstance();
                
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            } else {
                modeloProduto.adicionarLista(produtoDAO.listarProduto());
                interfaceConsultarProduto.setVisible(true);
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[65]){
           //Evento acionado pelo Cadastrar da classe "ConsultarProduto".
             if(interfaceCadastrarProdutos == null ){
              interfaceCadastrarProdutos = InterfaceFactory.getInterface(CADASTRAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
              interfaceCadastrarProdutos.setVisible(true);
           } else {
               interfaceCadastrarProdutos.setVisible(true);
           }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[66]){
           //Evento acionado pelo Pesquisar da classe "ConsultarProduto".
                produtoDAO = ProdutoDAO.getInstance();
                consultarProduto = ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloProduto = ModeloProduto.getInstance();
                
                try{
                if(consultarProduto.getPesquisa().equals("")){
                    JOptionPane.showMessageDialog(null,"Digite um valor válido para pesquisa.");
                    
                } else {
                    //Adicionando o usuário pesquisado a tabela que exibe os usuários.
                modeloProduto.adicionar(produtoDAO.consultarProduto(consultarProduto.getPesquisa()));
                
                }
                }catch(Exception erro){
                    
                }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[67]){
           //Evento acionado pelo cADASTRAR da classe "ConsultarPasseio".
               if(interfaceCadastrarPasseios == null || interfaceCadastrarPasseios.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarPasseios = InterfaceFactory.getInterface(CADASTRAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceCadastrarPasseios.setVisible(true);
            } else {
                interfaceCadastrarPasseios.setVisible(true);
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[68]){
           //Evento acionado pelo Pesquisar da classe "ConsultarPasseio".
           passeioDAO = PasseioDAO.getInstance();
           modeloPasseio = ModeloPasseio.getInstance();
           consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
           try{
               if(consultarPasseio.getPesquisa().equals("")){
                   JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
               } else{
                   modeloPasseio.adicionar(passeioDAO.consultarPasseio(consultarPasseio.getPesquisa()));
               }
              }catch(Exception erro){
                
                 }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[68]){
           //Evento acionado pelo Pesquisar da classe "ConsultarPasseio".
           passeioDAO = PasseioDAO.getInstance();
           modeloPasseio = ModeloPasseio.getInstance();
           consultarPasseio = ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
           try{
               if(consultarPasseio.getPesquisa().equals("")){
                   JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
               } else{
                   modeloPasseio.adicionar(passeioDAO.consultarPasseio(consultarPasseio.getPesquisa()));
               }
              }catch(Exception erro){
                
                 }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == botoes[69]) {
           //Evento acionado pelo iten de menu "ADICIONAR PASSAGEIRO" da classe "ConsultarHospedagem"
           if(interfaceSelecionarPasseioCadastrarPassageiro == null || interfaceSelecionarPasseioCadastrarPassageiro.isValid()){
               interfaceSelecionarPasseioCadastrarPassageiro = InterfaceFactory.getInterface(SELECIONAR_PASSEIO_CADASTRAR_PASSAGEIRO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceSelecionarPasseioCadastrarPassageiro.setVisible(true);
               passeioDAO = PasseioDAO.getInstance();
               modeloSelecionarPasseio = ModeloSelecionarPasseio.getInstance();
               modeloSelecionarPasseio.adicionarLista(passeioDAO.listarPasseio());
               
           } else{
               
               modeloSelecionarPasseio = ModeloSelecionarPasseio.getInstance();
               modeloSelecionarPasseio.adicionarLista(passeioDAO.listarPasseio());
               interfaceSelecionarPasseioCadastrarPassageiro.setVisible(true);
           }

            //--------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[70]) {
           //Acionado pelo "HISTORICO DE ACÃO" de usuários.
            if(interfaceAuditarUsuario == null || interfaceAuditarUsuario.isValid()){
               interfaceAuditarUsuario= InterfaceFactory.getInterface(AUDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Lista a tabela com as ações feita nos usuários.
               historicoDAO = HistoricoDAO.getInstance();
               modeloAuditarUsuario = ModeloAuditarUsuario.getInstance();
               modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuario());
               interfaceAuditarUsuario.setVisible(true);
            } else {
                modeloAuditarUsuario.adicionarLista(historicoDAO.auditarUsuario());
               interfaceAuditarUsuario.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[71]) {
           //Acionado pelo "HISTORICO DE ACÃO" de quartos.
            if(interfaceAuditarQuarto == null || interfaceAuditarQuarto.isValid()){
               interfaceAuditarQuarto = InterfaceFactory.getInterface(AUDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Lista as ações realizadas por usuários sobre os quartos.
               historicoDAO = HistoricoDAO.getInstance();
               modeloAuditarQuarto = ModeloAuditarQuarto.getInstance();
               modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuarto());
               interfaceAuditarQuarto.setVisible(true);
               
            } else {
                //Lista as ações realizadas por usuários sobre os quartos.
                modeloAuditarQuarto.adicionarLista(historicoDAO.auditarQuarto());
                interfaceAuditarQuarto.setVisible(true);
                interfaceAuditarQuarto.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[72]) {
           //Acionado pelo "HISTORICO DE ACÃO" de clientes.
           if(interfaceAuditarCliente == null || interfaceAuditarCliente.isValid()){
               interfaceAuditarCliente = InterfaceFactory.getInterface(AUDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Acionando método que preenche a tabela.
               historicoDAO = HistoricoDAO.getInstance();
               modeloAuditarCliente = ModeloAuditarCliente.getInstance();
               modeloAuditarCliente.adicionarLista(historicoDAO.auditarCliente());
               interfaceAuditarCliente.setVisible(true);
               
            } else {
                modeloAuditarCliente.adicionarLista(historicoDAO.auditarCliente());
               interfaceAuditarCliente.setVisible(true);
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[73]) {
           //Acionado pelo "HISTORICO DE ACÃO" de reservas.
           if(interfaceAuditarReserva == null || interfaceAuditarReserva.isValid()){
               interfaceAuditarReserva = InterfaceFactory.getInterface(AUDITAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               
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
        } else if(evento.getSource() == botoes[74]) {
           //Acionado pelo "HISTORICO DE ACÃO" de hospedagem.
            if(interfaceAuditarHospedagem == null || interfaceAuditarHospedagem.isValid()){
               interfaceAuditarHospedagem = InterfaceFactory.getInterface(AUDITAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
        } else if(evento.getSource() == botoes[75]) {
           //Acionado pelo "HISTORICO DE ACÃO" de produto.
            if(interfaceAuditarProduto == null || interfaceAuditarProduto.isValid()){
               interfaceAuditarProduto = InterfaceFactory.getInterface(AUDITAR_PRODUTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
        } else if(evento.getSource() == botoes[76]) {
           //Acionado pelo "HISTORICO DE ACÃO" de passeio.
            if(interfaceAuditarPasseio == null || interfaceAuditarPasseio.isValid()){
               interfaceAuditarPasseio = InterfaceFactory.getInterface(AUDITAR_PASSEIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
        } else if(evento.getSource() == botoes[77]) {
           //Acionado pelo "Selecionar" de SelecionarPasseio.
           selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
           
           //Significa que é uma reserva do tipo reserva.
           if(selecionarCliente.getTipoReserva().equals("Reservar")){
               cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               cadastrarReserva.receberliente(selecionarCliente.getCliente());
               cadastrarReserva.setVisible(true);
               selecionarCliente.dispose();
               
           } else {
               cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               cadastrarHospedagem.receberliente(selecionarCliente.getCliente());
               cadastrarHospedagem.setVisible(true);
               selecionarCliente.dispose();
           }
            

            //--------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[78]) {
                clienteDAO = ClienteDAO.getInstance();
                selecionarCliente = SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloSelecionarCliente = ModeloSelecionarCliente.getInstance();
                
                try{
                    if(selecionarCliente.getPesquisa().getCpf().equals("   .   .   -  ") &&
                       selecionarCliente.getPesquisa().getNome().equals("")){
                        JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
                        
                    } else {
                        
                if(!selecionarCliente.getPesquisa().getCpf().equals("   .   .   -  ") && selecionarCliente.getPesquisa().getNome().equals("")){
                    //Significa que procurará por CPF
                    modeloSelecionarCliente.adicionar(clienteDAO.consultarCliente(selecionarCliente.getPesquisa()));
                    
                } else { //Procurará por nome.
                //Adicionando o usuário pesquisado a tabela que exibe os usuários.
                modeloSelecionarCliente.adicionarLista(clienteDAO.ConsultarClienteNome(selecionarCliente.getPesquisa()));
                }
                
                    }
                }catch(Exception erro){
                    
                }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == itensDeMenu[1]) {//Evento acionado pelo iten de menu "Editar DTOUsuario" da classe "TelaPrincipal".
            if (interfaceEditarUsuario == null || interfaceEditarUsuario.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceEditarUsuario = InterfaceFactory.getInterface(EDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceEditarUsuario.setVisible(true);

            } else {
                editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                editarUsuario.limparCampos();
                interfaceEditarUsuario.setVisible(true);
            }

            //-----------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == itensDeMenu[2]) {//Evento acionado pelo iten de menu "Consultar Usuário" da classe "TelaPrincipal".
            if (interfaceConsultarUsuario == null || interfaceConsultarUsuario.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                usuarioDAO = UsuarioDAO.getInstance();
                interfaceConsultarUsuario = InterfaceFactory.getInterface(CONSULTAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);

                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario = ModeloUsuario.getInstance();
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);

            } else {
                //Alimenta a tabela com uma lista vindo do método "ListarUsuario" da classe "usuarioDAO".
                modeloUsuario.adicionarLista(usuarioDAO.listarUsuario());
                interfaceConsultarUsuario.setVisible(true);
            }

            //------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == itensDeMenu[3]) {//Evento acionado pelo iten de menu "Cadastrar Usuário" da classe "TelaPrincipal".
            if (interfaceCadastrarUsuario == null || interfaceCadastrarUsuario.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarUsuario = InterfaceFactory.getInterface(CADASTRAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarUsuario.setVisible(true);

            } else {
                cadastrarUsuario = CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarUsuario.limparCampos();
                interfaceCadastrarUsuario.setVisible(true);
            }

            //----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == itensDeMenu[4]) {//Evento acionado pelo iten de menu "Deletar cliente" da classe "TelaPrincipal".
            /*if (interfaceDeletarCliente == null || interfaceDeletarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceDeletarCliente = InterfaceFactory.getInterface(DELETAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceDeletarCliente.setVisible(true);

            } else {
                deletarCliente = DeletarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarCliente.limparCampos();
                interfaceDeletarCliente.setVisible(true);
            }*/

            //--------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[6]){
           //Evento acionado pelo Cadastrar da classe "ConsultarQuarto".
            if(interfaceCadastrarQuarto == null || interfaceCadastrarQuarto.isValid()){
                interfaceCadastrarQuarto = InterfaceFactory.getInterface(CADASTRAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarQuarto.setVisible(true);
                
            } else{
                cadastrarQuarto = CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarQuarto.limparCampos();
                interfaceCadastrarQuarto.setVisible(true);
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------    
        } else if(evento.getSource() == itensDeMenu[7]){//Acionado pela item de manu "Deletar DTOQuarto" da classe "TelaPrincipal".
           /* if(interfaceDeletarQuarto == null || interfaceDeletarQuarto.isValid()){
               interfaceDeletarQuarto = InterfaceFactory.getInterface(DELETAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceDeletarQuarto.setVisible(true);
               
            } else {
                deletarQuarto = DeletarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarQuarto.limparCampos();
                interfaceDeletarQuarto.setVisible(true);
            }*/
            
            //------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[8]){//Acionado pela item de manu "Editar DTOQuarto" da classe "TelaPrincipal".
            if(interfaceEditarQuarto == null || interfaceEditarQuarto.isValid()){
               interfaceEditarQuarto = InterfaceFactory.getInterface(EDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceEditarQuarto.setVisible(true);
               
            } else {
                editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                editarQuarto.limparCampos();
                interfaceEditarQuarto.setVisible(true);
            }
            
            //---------------------------------------------------------------------------------------------------------------------------------------   
        }else if(evento.getSource() == itensDeMenu[9]){//Acionado pela item de manu "Cadastrar DTOCliente" da classe "TelaPrincipal".
            if (interfaceCadastrarCliente == null || interfaceCadastrarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarCliente = InterfaceFactory.getInterface(CADASTRAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarCliente.setVisible(true);

            } else {
                cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarCliente.limparCampos();
                interfaceCadastrarCliente.setVisible(true);
            }
            //---------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[10]){//Acionado pelo iten e menu "Consultar DTOCliente" da classe "TelaPrincipal".
            if(interfaceConsultarCliente == null || interfaceConsultarCliente.isValid()){
                interfaceConsultarCliente = InterfaceFactory.getInterface(CONSULTAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
                //Alimenta a tabela com uma lista vindo do método "listarCliente" da classe "clienteDao".
                clienteDAO = ClienteDAO.getInstance();
                modeloCliente = ModeloCliente.getInstance();
                modeloCliente.adicionarLista(clienteDAO.listarCliente());
                interfaceConsultarCliente.setVisible(true);
             } else {
                 //Alimenta a tabela com uma lista vindo do método "listarCliente" da classe "clienteDao".
                 modeloCliente.adicionarLista(clienteDAO.listarCliente());
                 interfaceConsultarCliente.setVisible(true);
             }
            //---------------------------------------------------------------------------------------------------------------------------------------
            
        } else if(evento.getSource() == itensDeMenu[11]){//Evento acionado pelo item de menu "DeletarReserva" da classe "TelaPricipal".
           /* if(interfaceDeletarReserva == null || interfaceDeletarReserva.isValid()){
               interfaceDeletarReserva = InterfaceFactory.getInterface(DELETAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceDeletarReserva.setVisible(true);
               
            } else {
                deletarReserva = DeletarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarReserva.limparCampos();
                interfaceDeletarReserva.setVisible(true);
            }
            */
            //----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[12]){//Acionado pelo item de menu "EditarReserva" da classe "TelaPrincipal".
            if(interfaceEditarReserva == null || interfaceEditarReserva.isValid()){
               interfaceEditarReserva = InterfaceFactory.getInterface(EDITAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceEditarReserva.setVisible(true);
                
            } else {
                editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                editarCliente.limparCampos();
                interfaceEditarReserva.setVisible(true);
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[13]){//Acionado pelo item de menu "Cadastrar DTOReserva" da classe "TelaPrincipal".
            if(interfaceCadastrarReserva == null || interfaceCadastrarReserva.isValid()){
                  interfaceCadastrarReserva = InterfaceFactory.getInterface(CADASTRAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  interfaceCadastrarReserva.setVisible(true);
                  
               } else {
                cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarReserva.limparCampos();
                   interfaceCadastrarReserva.setVisible(true);
               }
            
            //----------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[14]){//Acionado pelo item de Menu "Consultar DTOReserva" da classe "TelaPrincipal".
            if(interfaceConsultarReserva == null || interfaceConsultarReserva.isValid()){
                  interfaceConsultarReserva = InterfaceFactory.getInterface(CONSULTAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  
                  //Aciona o método para listas todas as reservas.
                  reservaDAO = ReservaDAO.getInstance();
                  modeloReserva = ModeloReserva.getInstance();
                  modeloReserva.adicionarLista(reservaDAO.listarReservas());
                  interfaceConsultarReserva.setVisible(true);

               } else {
                   modeloReserva.adicionarLista(reservaDAO.listarReservas());
                   interfaceConsultarReserva.setVisible(true);
               }
         
            //------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[15]){//Acionado pelo item de Menu "Consultar quarto" da classe "TelaPrincipal".
            if(interfaceConsultarQuarto == null || interfaceConsultarQuarto.isValid()){
                interfaceConsultarQuarto = InterfaceFactory.getInterface(CONSULTAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceConsultarQuarto.setVisible(true);
                
                
                
                //Aciona o método para listar todos os quartos.
                quartoDAO = QuartoDAO.getInstance();
                modeloQuarto = ModeloQuarto.getInstance();
                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);
                
            } else{
                modeloQuarto.adicionarLista(quartoDAO.listarQuarto());
                interfaceConsultarQuarto.setVisible(true);
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[16]){//Acionaod pelo item de menu "Auditar DTOQuarto" da classe "TelaPrincipal".
            
            
            //------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[17]){//Acionado pelo item de menu "Cadastrar Hospedagem" da classe "TelaPrincipal".
            if(interfaceCadastrarHospedagem == null || interfaceCadastrarHospedagem.isValid()){
                   interfaceCadastrarHospedagem = InterfaceFactory.getInterface(CADASTRAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   interfaceCadastrarHospedagem.setVisible(true);
                   cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    cadastrarHospedagem.btnHospedar.setEnabled(true);
                   
                } else {
                    cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    cadastrarHospedagem.limparCampos();
                    interfaceCadastrarHospedagem.setVisible(true);
                    cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    cadastrarHospedagem.btnHospedar.setEnabled(true);
                }
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[18]){//Acionado pelo item de menu "Editar Hospdagem" da classe "TelaPrincipal".
            if(interfaceEditarHospedagem == null || interfaceEditarHospedagem.isValid()){
                   interfaceEditarHospedagem = InterfaceFactory.getInterface(EDITAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   interfaceEditarHospedagem.setVisible(true);
                   
                } else {
                    editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    editarHospedagem.limparCampos();
                    interfaceEditarHospedagem.setVisible(true);
                }
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[19]){//Acionado pelo item de menu "Deletar Hospdagem" da classe "TelaPrincipal".
           /* if(interfaceDeletarHospedagem == null || interfaceDeletarHospedagem.isValid()){
                   interfaceDeletarHospedagem = InterfaceFactory.getInterface(DELETAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   interfaceDeletarHospedagem.setVisible(true);
                   
                } else {
                    deletarHospedagem = DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    deletarHospedagem.limparCampos();
                    interfaceDeletarHospedagem.setVisible(true);
                }*/
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        }else if(evento.getSource() == itensDeMenu[20]){//Acionado pelo item de menu "Consultar Hospdagem" da classe "TelaPrincipal".
            if(interfaceConsultarHospedagem == null || interfaceConsultarHospedagem.isValid()){
               interfaceConsultarHospedagem = InterfaceFactory.getInterface(CONSULTAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceConsultarHospedagem.setVisible(true);
               
                  //Aciona o método para listas todas as hospedagens.
                  reservaDAO = ReservaDAO.getInstance();
                  modeloHospedagem = ModeloHospedagem.getInstance();
                  modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
               
            } else {
                modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                interfaceConsultarHospedagem.setVisible(true);
            }
            
            //----------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[21]){//Acionado pelo item de menu "Auditar DTOCliente".
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[22]){
            
            
            //---------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[23]){//Acionado pelo item de menu "Auditar DTOReserva" da classe "TelaPrincipal".
            
        //----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[24]){//Acionado pelo item de menu "Auditar Hsopedagem" da classe "TelaPrincipal".
           
        } else if(evento.getSource() == itensDeMenu[25]){//Acionado pelo item de menu "Ajuda" da classe "TelaPrincipal".
            //Abre um PDF com instruções de uso.
            try {
                
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File("C:\\Users\\Tomé\\Desktop\\Hospede\\Documentação\\Manual do Usuário - Sistema Hospede.pdf"));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao exibir manual de usurio:\n" + erro.getMessage());
            }

        } else if(evento.getSource() == itensDeMenu[26]){//Acionado pelo menu Cadastrar Passeios da classe Tela Principal.
            
        } else if(evento.getSource() == itensDeMenu[27]){//Acionado pelo menu Editar Passeios da classe Tela Principal.
            if(interfaceEditarPasseios == null || interfaceEditarPasseios.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceEditarPasseios = InterfaceFactory.getInterface(EDITAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceEditarPasseios.setVisible(true);
            } else {
                interfaceEditarPasseios.setVisible(true);
            }
        } else if(evento.getSource() == itensDeMenu[28]){//Acionado pelo menu Deletar Passeios da classe Tela Principal.
            if(interfaceDeletarPasseios == null || interfaceDeletarPasseios.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceDeletarPasseios = InterfaceFactory.getInterface(DELETAR_PASSEIOS, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceDeletarPasseios.setVisible(true);
            } else {
                interfaceDeletarPasseios.setVisible(true);
            }
        } else if(evento.getSource() == itensDeMenu[29]){//Acionado pelo menu Consultar Passeios da classe Tela Principal.
            if(interfaceSelecionarPasseioConsulta == null || interfaceSelecionarPasseioConsulta.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceSelecionarPasseioConsulta = InterfaceFactory.getInterface(SELECIONAR_PASSEIOS_CONSULTA, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceSelecionarPasseioConsulta.setVisible(true);
            } else {
                interfaceSelecionarPasseioConsulta.setVisible(true);
            }
        } else if(evento.getSource() == itensDeMenu[30]){
           //Acionado pelo menu Cadastrar Produto da classe Tela Principal.
          
            
        } else if(evento.getSource() == itensDeMenu[31]){//Acionado pelo menu Editar Produto da classe Tela Principal.
            if(interfaceEditarProduto == null || interfaceEditarProduto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceEditarProduto = InterfaceFactory.getInterface(EDITAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceEditarProduto.setVisible(true);
            } else {
                interfaceEditarProduto.setVisible(true);
            }
            
        } else if(evento.getSource() == itensDeMenu[32]){//Acionado pelo menu Editar Produto da classe Tela Principal.
            if(interfaceDeletarProduto == null || interfaceDeletarProduto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceDeletarProduto = InterfaceFactory.getInterface(DELETAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceDeletarProduto.setVisible(true);
            } else {
                interfaceDeletarProduto.setVisible(true);
            }
        } else if(evento.getSource() == itensDeMenu[33]){//Acionado pelo menu Consultar Produto da classe Tela Principal.
            if(interfaceConsultarProduto == null || interfaceConsultarProduto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceConsultarProduto = InterfaceFactory.getInterface(CONSULTAR_PRODUTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                interfaceConsultarProduto.setVisible(true);
            } else {
                interfaceConsultarProduto.setVisible(true);
            }
        } else if(evento.getSource() == itensDeMenu[34]){//Acionado pelo menu Consultar Pedidos de DTOQuarto da classe Tela Principal.
            if(interfaceConsultarPedidoQuarto == null || interfaceConsultarPedidoQuarto.isValid()){//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceConsultarPedidoQuarto = InterfaceFactory.getInterface(CONSULTAR_PEDIDO_QUARTO, botoes, itensDeMenu, camposDeTexto,usuarioLogado);
                modeloPedidosDeQuarto = ModeloPedidosDeQuarto.getInstance();
               
                consultarPedidoQuarto = ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                consultarPedidoQuarto.limparCampos();
                
                modeloPedidosDeQuarto.limparDados();
                
                interfaceConsultarPedidoQuarto.setVisible(true);
            } else {
             
                consultarPedidoQuarto.limparCampos();
                modeloPedidosDeQuarto.limparDados();
                interfaceConsultarPedidoQuarto.setVisible(true);
            }
        }
    }

    //-----------------------------------------------------//------------------------------------------------------------------
    //Eventos de focu em objetos.
    @Override
    public void focusGained(FocusEvent evento) {
         if(evento.getSource() == camposDeTexto[21]){
         //Limpa os campos de máscara de CPF
         try{
         consultarCliente = ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
         consultarCliente.limparMascaraCpf();
         }catch(Exception erro){
             
         }   
            
        } 
    }

    @Override
    public void focusLost(FocusEvent evento
    ) {
        if (evento.getSource() == camposDeTexto[0]) {//Acionado quando o campo "Login" da classe "DeletarUsuario" perde o foco.
           /* deletarUsuario = DeletarUsuario.getInstance(botoes, itensDeMenu, usuarioLogado, camposDeTexto);
            usuarioDAO = UsuarioDAO.getInstance();

            //Busca o usuário informado no banco de dados antes de excluir e retorna para a classe "DeletarUsuario".
            deletarUsuario.recebeUsuario(usuarioDAO.consultarUsuario(deletarUsuario.getUsuario().getLogin()));
              */
            //----------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[1]) {//Acionado quando o campo "Login" da classe "EditarUsuario" perde o foco.
            DTOUsuario usuarioLocal;
            editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            usuarioDAO = UsuarioDAO.getInstance();
            usuarioLocal = usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin());

            //Busca o usuário informado no banco de dados antes de editar e retorna para a classe "EditarUsuario".
            editarUsuario.recebeUsuario(usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin()));
            
            //-------------------------------------------------------------------------------------------------------------------
        }  else if(evento.getSource() == camposDeTexto[4]){//Acionado quando o campo "Nome" da classe "DeletarCliente" perde o foco.
           /* clienteDAO = ClienteDAO.getInstance();
            deletarCliente = DeletarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Busca o cliente informado no banco de dados antes de excluir e retorna para a classe "DeletarCliente".
            deletarCliente.receberCliente(clienteDAO.consultarCliente(deletarCliente.getCliente().getNome()));
            */
           //---------------------------------------------------------------------------------------------------------------------------
        }  else if(evento.getSource() == camposDeTexto[5]){//Acionado quando o campo "Numero" da classe "DeletarQuarto" perde o foco.
            /*quartoDAO = QuartoDAO.getInstance();
            deletarQuarto = DeletarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            //Busca o quarto informado no banco de dados antes de excluir e retorna para a classe "DeletarCliente".
            deletarQuarto.receberQuarto(quartoDAO.consultarQuarto(Integer.parseInt(deletarQuarto.getQuarto().getNumero())));
            }catch(Exception erro){
                deletarQuarto.limparCampos();
            }*/
            //-------------------------------------------------------------------------------------------------------------------------
            
        }  else if(evento.getSource() == camposDeTexto[6]){//Acionado quando o campo "Numero" da classe "EditarQuarto" perde o foco.
            quartoDAO = QuartoDAO.getInstance();
            editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            //Busca o quarto informado no banco de dados antes de edtar e retorna para a classe "EditarQuarto".
            editarQuarto.receberQuarto(quartoDAO.consultarQuarto(Integer.parseInt(editarQuarto.getQuarto().getNumero())));
            }catch(Exception erro){
                editarQuarto.limparCampos();
            }
            
            //----------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[7]){//Acionado quando o campo "CPF" da classe "CadastrarReserva" perde o foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            
            
                //Este if, investiga se o cliente já está reservado. Se tiver, não permite que ele se reserve novamente.
             try{
             DTOCliente cliente = clienteDAO.consultarCliente(cadastrarReserva.getCliente());
             if(cliente.getId_cliente() == reservaDAO.consultarReserva(cliente.getId_cliente()).getId_cliente() && !cliente.getCpf().equals("")){
                 JOptionPane.showMessageDialog(null, "Cliente encontra-se reservado.");
                 
             } else {
                 cadastrarReserva.receberliente(clienteDAO.consultarCliente(cadastrarReserva.getCliente()));
             }
                
             }catch(Exception erro){
                 
            }
            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[8]){//Acionado quando o campo Nome da classe DeletarReserva perde o foco.
           /* clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            deletarReserva = DeletarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            DTOCliente cliente = clienteDAO.consultarCliente(deletarReserva.getCliente().getNome());
            //Segundo, reserva.
            DTOReserva reserva = reservaDAO.consultarReserva(cliente.getId_cliente());
            //Terceiro, quarto.
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            deletarReserva.receberReserva(reserva, cliente, quarto);
            
            */
        } else if(evento.getSource() == camposDeTexto[10]){//Acionado quando o campo nome da classe "CadastrarHospedagem" perder foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            if(cadastrarHospedagem.getCliente().getCpf().equals("")){//Se o campo "nome" estiver em branco, ele nem procura o cliente no banco.
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                cadastrarHospedagem.btnHospedar.setEnabled(true);
                
            } else {
                
                   //Verifica se o cliente está reservado antes de hospeda-lo.
                   DTOReserva reserva;
                   DTOCliente cliente; //Pegando o ID do cliente.
                   cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente());
                   reserva = reservaDAO.consultarReserva(cliente.getId_cliente());
                   
                   
                   try{
                   if(reserva.getEntrada() == null){//Significa que não há reserva no nome daquele cliente.
                       
                       //Este if, investiga se o cliente já está hospedado. Se tiver, não permite que ele se hospede novamente.
                       if(cliente.getId_cliente() == reservaDAO.consultarHospedagem(cliente.getId_cliente()).getId_cliente() && !cliente.getCpf().equals("")){//Significa que o cliente está hospedado.
                           JOptionPane.showMessageDialog(null, "Cliente encontra-se hospedado.");
                          
                       } else {
                            cadastrarHospedagem.receberliente(clienteDAO.consultarCliente(cadastrarHospedagem.getCliente())); 
                            cadastrarHospedagem.receberHospedagem(reserva);
                            cadastrarHospedagem.btnHospedar.setEnabled(true);
                       }
                       
                       
                   } else {//Cliente está reservado. Logo, envia a reserva para a tela.
                       JOptionPane.showMessageDialog(null, "Cliente encontra-se reservado.");
                       cadastrarHospedagem.receberliente(cliente);//Primeiro o cliente, porque este método limpa alguns campos.
                       cadastrarHospedagem.receberHospedagem(reserva);
                       cadastrarHospedagem.btnHospedar.setEnabled(false);
                       
                   }
                   }catch(Exception erro){
                      
                   }
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[11]){//Acionado após o campo nome , da classe "EditarHospedagem", perder o foco.
            reservaDAO = ReservaDAO.getInstance();
            clienteDAO = ClienteDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            editarHospedagem = EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
                //Primeiro busca o cliente.
            DTOCliente cliente = clienteDAO.consultarCliente(editarHospedagem.getCliente());
            //Segundo, hospedagem.
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            //E por último, o querto.
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
                
            editarHospedagem.receberHospedagem(reserva, cliente, quarto);;
            }catch(Exception erro){
                
            }
            //----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[12]){//Acionado quando o campo nome da classe "DeletarHospedagem" perder foco.
           /* clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            deletarHospedagem = DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            DTOCliente cliente = clienteDAO.consultarCliente(deletarHospedagem.getCliente().getNome());
            //Segundo, reserva.
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            //Terceiro, quarto.
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            deletarHospedagem.receberHospedagem(reserva, cliente, quarto);
            */
        } else if(evento.getSource() == camposDeTexto[13]){
            //Acionado pelo campo nome da classe "FecharHsopedagem".
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            passeioDAO = PasseioDAO.getInstance();
            produtoDAO = ProdutoDAO.getInstance();
            fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            DTOCliente cliente = clienteDAO.consultarCliente(fecharHospedagem.getCpfCliente());
            //Segundo, reserva.
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
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
            reserva.totalPagar(quarto);
            
            //Calculando produtos consumidos.
            DTOProduto produto = new DTOProduto();
            //Calculando os passeios realiados.
            DTOPasseio passeio = new DTOPasseio();
            
            try{
            fecharHospedagem.receberTotaisServicos(reserva.getTotalPagar(),produto.totalPagar(),passeio.totalPagar());
            }catch(Exception erro){
                
            }
            
        } else if(evento.getSource() == camposDeTexto[14]){//Acionado pelo campo nome do produto classe EditarProduto
            editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            editarProduto.receberProduto(produtoDAO.consultarProduto(editarProduto.getProduto().getNome()));
            
        } else if(evento.getSource() == camposDeTexto[15]){//Acionado pelo campo nome do produto classe DeletarProduto
            /*deletarProduto = DeletarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            deletarProduto.receberProduto(produtoDAO.consultarProduto(deletarProduto.getProduto().getNome()));
            */
        } else if(evento.getSource() == camposDeTexto[16]){//Acionado pelo campo titulo do passeio classe EditarPasseio
            
            
        } else if(evento.getSource() == camposDeTexto[17]){//Acionado pelo campo titulo do passeio classe DeletarPasseio
           /* deletarPasseio = DeletarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            deletarPasseio.receberPasseio(passeioDAO.consultarPasseio(deletarPasseio.getPasseio().getTitulo()));
            */
        } else if(evento.getSource() == camposDeTexto[18]){
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Buscando o cliente no banco.
            clienteDAO = ClienteDAO.getInstance();
            DTOCliente cliente = clienteDAO.consultarCliente(adicionarPassageiroPasseio.getCliente());
            
            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            
            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            //Enviando as informações para a tela de cadastro de passageiro.
            adicionarPassageiroPasseio.receberCliente(cliente, quarto, reserva);
            
        } else if(evento.getSource() == camposDeTexto[19]){//Acionado pelo campo nome do cliente classe CadastrarPedidoQuarto
            //Obtendo os objetos.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            
            //Buscando o cliente no banco.
            clienteDAO = ClienteDAO.getInstance();
            DTOCliente cliente = clienteDAO.consultarCliente(cadastrarPedidoQuarto.getCpfHospede());
            
            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            DTOReserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            
            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            DTOQuarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            cadastrarPedidoQuarto.receberHospede(reserva, cliente, quarto);
        } else if(evento.getSource() == camposDeTexto[20]){//Acionado pelo campo nome do cliente classe ConsultarPedidoQuarto
            //Obtendo os objetos.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            produtoDAO = ProdutoDAO.getInstance();
            
            consultarPedidoQuarto = ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
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
            
            
        }
    }
    //----------------------------------------------------//-------------------------------------------------------------------

    public static ControlarEventos getInstance(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, DTOUsuario usuarioLogado) {
        //Garantir , caso já exista uma instancia da classe ControlarEventos, quee os vetores não ficarão faltando objetos.
        ControlarEventos.botoes = botoes;
        ControlarEventos.itensDeMenu = itensDeMenu;
        ControlarEventos.camposDeTexto = camposDeTexto;

        if (instance == null) {
            instance = new ControlarEventos(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
        }
        return instance;
    }

}
