package br.com.hospede.control;

import br.com.hospede.model.DTO.AuditarHospedagem;
import br.com.hospede.model.DTO.ManterReserva;
import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.DTOProduto;
import br.com.hospede.model.DTO.ManterCliente;
import br.com.hospede.model.DTO.ManterQuarto;
import br.com.hospede.model.DTO.ManterUsuario;
import br.com.hospede.model.DTO.Quarto;
import br.com.hospede.model.DTO.Reserva;
import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.model.dao.ClienteDAO;
import br.com.hospede.model.dao.AuditarDAO;
import br.com.hospede.model.dao.EntrandoOuSaindoDAO;
import br.com.hospede.model.dao.PassageiroDAO;
import br.com.hospede.model.dao.PasseioDAO;
import br.com.hospede.model.dao.ProdutoDAO;
import br.com.hospede.model.dao.QuartoDAO;
import br.com.hospede.model.dao.ReservaDAO;
import br.com.hospede.model.dao.UsuarioDAO;
import br.com.hospede.model.modeloTabela.ModeloAuditarCliente;
import br.com.hospede.model.modeloTabela.ModeloAuditarHospedagem;
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
import br.com.hospede.model.modeloTabela.ModeloPedidosDeQuarto;
import br.com.hospede.model.modeloTabela.ModeloProdutoPedido;
import br.com.hospede.model.modeloTabela.ModeloSelecionarPasseio;
import br.com.hospede.model.modeloTabela.ModeloSelecionarQuarto;
import br.com.hospede.view.AdicionarPassageiroPasseio;
import br.com.hospede.view.CadastrarUsuario;
import br.com.hospede.view.ConsultarUsuario;
import br.com.hospede.view.DeletarUsuario;
import br.com.hospede.view.EditarUsuario;
import br.com.hospede.view.Login;
import br.com.hospede.view.RelatorioServicos;
import br.com.hospede.view.SelecionarQuarto;
import br.com.hospede.view.CadastrarCliente;
import br.com.hospede.view.ConsultarCliente;
import br.com.hospede.view.DeletarCliente;
import br.com.hospede.view.EditarCliente;
import br.com.hospede.view.CadastrarHospedagem;
import br.com.hospede.view.CadastrarPasseios;
import br.com.hospede.view.CadastrarPedidoQuarto;
import br.com.hospede.view.CadastrarProduto;
import br.com.hospede.view.ConsultarHospedagem;
import br.com.hospede.view.DeletarHospedagem;
import br.com.hospede.view.EditarHospedagem;
import br.com.hospede.view.FecharHospedagem;
import br.com.hospede.view.CadastrarQuarto;
import br.com.hospede.view.DeletarQuarto;
import br.com.hospede.view.EditarQuarto;
import br.com.hospede.view.CadastrarReserva;
import br.com.hospede.view.ConsultarPedidoQuarto;
import br.com.hospede.view.ConsultarProduto;
import br.com.hospede.view.ConsultarQuarto;
import br.com.hospede.view.ConsultarPasseio;
import br.com.hospede.view.ConsultarReserva;
import br.com.hospede.view.DeletarPasseio;
import br.com.hospede.view.DeletarProduto;
import br.com.hospede.view.DeletarReserva;
import br.com.hospede.view.EditarPasseio;
import br.com.hospede.view.EditarProduto;
import br.com.hospede.view.EditarReserva;
import br.com.hospede.view.ProdutoPedido;
import br.com.hospede.view.SelecionarPasseioCadastrarPassageiro;
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
                                          ADICIONAR_PASSAGEIRO_PASSEIO = 43, PRODUTO_PEDIDO = 44, CONSULTAR_PEDIDO_QUARTO = 45;

    //Objetos de usuário.
    Login                    login;
    private Usuario          usuarioLogado;
    private InterfaceFactory interfaceCadastrarUsuario = null;
    private InterfaceFactory interfaceDeletarUsuario   = null;
    private InterfaceFactory interfaceConsultarUsuario = null;
    private InterfaceFactory interfaceEditarUsuario    = null;
    private CadastrarUsuario cadastrarUsuario;
    private DeletarUsuario   deletarUsuario;
    private ConsultarUsuario consultarUsuario;
    private EditarUsuario    editarUsuario;
    private UsuarioDAO       usuarioDAO;
    private ModeloUsuario    modeloUsuario;

    //Objetos de cliente.
    private InterfaceFactory interfaceCadastrarCliente = null;
    private InterfaceFactory interfaceDeletarCliente   = null;
    private InterfaceFactory interfaceEditarCliente    = null;
    private InterfaceFactory interfaceConsultarCliente = null;
    private ClienteDAO       clienteDAO;
    private CadastrarCliente cadastrarCliente;
    private DeletarCliente   deletarCliente;
    private EditarCliente    editarCliente;
    private ConsultarCliente consultarCliente;
    private ModeloCliente    modeloCliente;
    
    //Objetos de quarto.
    private InterfaceFactory interfaceCadastrarQuarto = null;
    private InterfaceFactory interfaceConsultarQuarto = null;
    private InterfaceFactory interfaceDeletarQuarto   = null;
    private InterfaceFactory interfaceEditarQuarto    = null;
    private CadastrarQuarto  cadastrarQuarto;
    private DeletarQuarto    deletarQuarto;
    private EditarQuarto     editarQuarto;
    private ModeloQuarto     modeloQuarto;
    private QuartoDAO        quartoDAO;
    private ConsultarQuarto  consultarQuarto;
    private ModeloSelecionarQuarto modeloSelecionarQuarto;
    
    //Objetos reserva.
    private InterfaceFactory interfaceCadastrarReserva = null;
    private InterfaceFactory interfaceSelecionarQuarto = null;
    private InterfaceFactory interfaceConsultarReserva = null;
    private InterfaceFactory interfaceDeletarReserva   = null;
    private InterfaceFactory interfaceEditarReserva    = null;
    private InterfaceFactory interfaceFecharHospedagem = null;
    private SelecionarQuarto selecionarQuarto;
    private CadastrarReserva cadastrarReserva;
    private DeletarReserva   deletarReserva;
    private EditarReserva    editarReserva;
    private ConsultarReserva consultarReserva;
    private FecharHospedagem fecharHospedagem;
    private ReservaDAO       reservaDAO;
    private ModeloReserva    modeloReserva;
    
    //Objetos hospedagem.
    private InterfaceFactory    interfaceCadastrarHospedagem = null;
    private InterfaceFactory    interfaceConsultarHospedagem = null;
    private InterfaceFactory    interfaceDeletarHospedagem   = null;
    private InterfaceFactory    interfaceEditarHospedagem    = null;
    private CadastrarHospedagem cadastrarHospedagem;
    private DeletarHospedagem   deletarHospedagem;
    private EditarHospedagem    editarHospedagem;
    private ModeloHospedagem    modeloHospedagem;
    private ConsultarHospedagem consultarHospedagem;

    //Objetos auditar.
    private InterfaceFactory        interfaceAuditarQuarto     = null;
    private InterfaceFactory        interfaceAuditarCliente    = null;
    private InterfaceFactory        interfaceAuditarUsuario    = null;
    private InterfaceFactory        interfaceAuditarReserva    = null;
    private InterfaceFactory        interfaceAuditarHospedagem = null;
    private ModeloAuditarQuarto     modeloAuditarQuarto;
    private ModeloAuditarCliente    modeloAuditarCliente;
    private ModeloAuditarUsuario    modeloAuditarUsuario;
    private ModeloAuditarReserva    modeloAuditarReserva;
    private ModeloAuditarHospedagem modeloAuditarHospedagem;
    private ManterReserva           manterReserva;
    private AuditarDAO              auditarDAO;

    //Objeto Tela Principal.
    private InterfaceFactory    interfaceTelaPrincipal     = null;
    private InterfaceFactory    interfaceRelatorioServicos = null;
    private InterfaceFactory    interfaceEntrandoOuSaindo;
    private RelatorioServicos   relatorioServicos;
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
    private DeletarPasseio      deletarPasseio;
    private ModeloPasseio       modeloPasseio;  
    private ConsultarPasseio    consultarPasseio;
    private SelecionarPasseioCadastrarPassageiro selecionarPasseioCadastrarPassageiro; 
    private ModeloPassageiros  modeloPassageiro;
    private AdicionarPassageiroPasseio adicionarPassageiroPasseio;
    private ModeloSelecionarPasseio modeloSelecionarPasseio;
    
            
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
    private DeletarProduto      deletarProduto;
    private CadastrarPedidoQuarto cadastrarPedidoQuarto;
    private ProdutoPedido       produtoPedido;
    private ModeloProdutoPedido modeloProdutoPedido;
    private ModeloPedidosDeQuarto modeloPedidosDeQuarto;
    private ConsultarPedidoQuarto consultarPedidoQuarto;
    private ConsultarProduto      consultarProduto;
    
    //Objetos passageiros
    private PassageiroDAO passageiroDAO;
    private ModeloPassageiros modeloPassageiros;
    
    public static ControlarEventos instance = null;
    private Date dataAtual = new Date();
    

    //Construtor principal.
    public ControlarEventos(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, Usuario usuarioLogado) {
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
        } else if (evento.getSource() == botoes[2]) {//Evento disparado após clicar no botão Usuario na classe "TelaPrincipal".
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
            Usuario usuarioCadastrar;
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
                        ManterUsuario manterUsuario = new ManterUsuario();
                        usuarioCadastrar = usuarioDAO.consultarUsuario(cadastrarUsuario.getUsuario().getLogin());
                        manterUsuario.setId_suario_alterado(usuarioCadastrar.getId_usuario());
                        manterUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        manterUsuario.setUsuario_alterado(usuarioCadastrar.getLogin());
                        manterUsuario.setAcao("Cadastrou");
                        
                        //Salva na tabale para auditação.
                        auditarDAO = AuditarDAO.getInstance();
                        auditarDAO.salvarManterUsuario(manterUsuario);
                        
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
        } else if (evento.getSource() == botoes[6]) {//Acionado após clique no botão "Deletar" da classe "DeltarUsuario".
            usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuarioDeletar;
            deletarUsuario = DeletarUsuario.getInstance(botoes, itensDeMenu, usuarioLogado, camposDeTexto);
           
            //Verifica se o usuário já existe no banco.
            try{
            usuarioDeletar = usuarioDAO.consultarUsuario(deletarUsuario.getUsuario().getLogin());
            
            if(deletarUsuario.getUsuario().getLogin().equals("")){//Caso aperte editar com o campo login da classe "EditarUsuario" em branco.
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                
              } else {//Caso seja encontrado no banco, pode excluir.
                
                
                       boolean editou = false; //Verifica se o a exclusão foi concluída.
                       editou = usuarioDAO.deletarUsuario(deletarUsuario.getUsuario().getLogin());
                       
                       if(editou != false){
                          //Pega o ID do usuario cadastrado.
                        ManterUsuario manterUsuario = new ManterUsuario();
                        manterUsuario.setId_suario_alterado(usuarioDeletar.getId_usuario());
                        manterUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        manterUsuario.setUsuario_alterado(usuarioDeletar.getLogin());
                        manterUsuario.setAcao("Deletou");
                        
                        //Salva na tabela de auditoria.
                         auditarDAO = AuditarDAO.getInstance();
                         auditarDAO.salvarManterUsuario(manterUsuario);
                    
                          deletarUsuario.limparCampos();
                          deletarUsuario.dispose();
                          deletarUsuario = null;
                       }
                      
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }

            //--------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[7]) {//Acionado após clique no botão "Editar" da classe "EditarUsuario"
            usuarioDAO = UsuarioDAO.getInstance();
            Usuario usuarioEditar;
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
                        ManterUsuario manterUsuario = new ManterUsuario();
                        manterUsuario.setId_suario_alterado(usuarioEditar.getId_usuario());
                        manterUsuario.setId_usuario_reponsavel(usuarioLogado.getId_usuario());
                        manterUsuario.setUsuario_alterado(usuarioEditar.getLogin());
                        manterUsuario.setAcao("Editou");
                        
                        //Salva na tabela de auditoria.
                         auditarDAO = AuditarDAO.getInstance();
                         auditarDAO.salvarManterUsuario(manterUsuario);
                         
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
            auditarDAO = auditarDAO.getInstance();
            Cliente clienteCadastrar;
            cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);

            //Verifica se o cliente já existe no banco.
            clienteCadastrar = clienteDAO.consultarCliente(cadastrarCliente.getCliente().getCpf());
            
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
                              ManterCliente manterCliente = new ManterCliente();
                              clienteCadastrar = clienteDAO.consultarCliente(cadastrarCliente.getCliente().getNome());
                              manterCliente.setId_cliente(clienteCadastrar.getId_cliente());
                              manterCliente.setNome_cliente(clienteCadastrar.getNome());
                              manterCliente.setId_usuario(usuarioLogado.getId_usuario());
                              manterCliente.setAcao("Cadastrou");
                              
                              //Sala na tabela
                              auditarDAO.salvarManterCliente(manterCliente);
                              
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
            auditarDAO = AuditarDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            if(cadastrarHospedagem.getHospedagem().getTipo().equals("")){
                JOptionPane.showMessageDialog(null, "   O cliente não esta reservado");
                
            } else {
                //Altera o quarto para "HOSPEDADO"
                         Quarto quartoEditar = quartoDAO.getQuartoPorID(cadastrarHospedagem.getReservaParaHospedar());
                         quartoEditar.setSituacao("HOSPEDADO"); //Passa o quarto de reservado para hospedado.
                         quartoDAO.editarQuarto(quartoEditar);
                         
                         //Depois altera a reserva para hospedagem.
                         Reserva reservaEditar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                         reservaEditar.setTipo("HOSPEDAR");
                         boolean editou = false;
                         editou = reservaDAO.editarReserva(reservaEditar);
                         
                         //Salva a hospedagem feita na tabela que audita as hospedagens.
                         if(editou != false){
                             
                             //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                            AuditarHospedagem auditarHospedagem = new AuditarHospedagem();
                            auditarDAO = AuditarDAO.getInstance();
                            auditarHospedagem.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                            auditarHospedagem.setLogin(usuarioLogado.getLogin());
                            auditarHospedagem.setAcao("Hospedou");
                            auditarHospedagem.setTipo_reserva("HOSPEDAR");
                    
                            //Obter o ID da reserva.
                            reservaEditar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                            auditarHospedagem.setId_hospedagem(reservaEditar.getId_reserva());
                            auditarHospedagem.setCliente_hospedado(cadastrarHospedagem.getCliente().getNome());
                    
                            //Salva na tabela.
                             auditarDAO.salvarManterHospedagem(auditarHospedagem);
                             
                             cadastrarHospedagem.limparCampos();
                             cadastrarHospedagem.dispose();
                             modeloHospedagem = ModeloHospedagem.getInstance();
                             modeloHospedagem.adicionarLista(reservaDAO.listarHospedagem());
                         }
            }
           
            
            
            //------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[12]){//Acionado pelo botão "Deletar" da classe "DeletarCliente".
            clienteDAO = ClienteDAO.getInstance(); 
            Cliente clienteDeletar;
            deletarCliente = DeletarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Verifica se o usuário existe no banco.
            clienteDeletar = clienteDAO.consultarCliente(deletarCliente.getCliente().getNome());
            try{
                if(deletarCliente.getCliente().getNome().equals("")){ //Caso aperte deletar com o campo "nome" da classe "DeletarCliente" em branco.
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                    
                } else {//caso seja encontrado, pode xcluir.
                    
                    
                    boolean deletou = false;
                    deletou = clienteDAO.deletarCliente(deletarCliente.getCliente().getNome());
                    
                    if(deletou != false){
                        
                        ManterCliente manterCliente = new ManterCliente();
                        auditarDAO = AuditarDAO.getInstance();
                        
                        //Pegando o ID do cliente Deletado.
                        manterCliente.setId_cliente(clienteDeletar.getId_cliente());
                        manterCliente.setNome_cliente(clienteDeletar.getNome());
                        manterCliente.setId_usuario(usuarioLogado.getId_usuario());
                        manterCliente.setAcao("Deletou");
                        
                        auditarDAO.salvarManterCliente(manterCliente);
                        
                        deletarCliente.limparCampos();
                        deletarCliente.dispose();
                        deletarCliente = null;
                    }
                    
                    
                }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }
                          
            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == botoes[13]){//Acionado pelo botão "Editar" da classe "EditarCliente".
               clienteDAO = ClienteDAO.getInstance();
               Cliente clienteEditar;
               editarCliente = EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Verifica se o cliente existe no banco.
               clienteEditar = clienteDAO.consultarCliente(editarCliente.getCliente().getNome());
               
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
                           
                           ManterCliente manterCliente = new ManterCliente();
                           auditarDAO = AuditarDAO.getInstance();
                           manterCliente.setId_cliente(clienteEditar.getId_cliente());
                           
                           //Pegando o ID do cliente editado.
                            manterCliente.setId_cliente(clienteEditar.getId_cliente());
                            manterCliente.setNome_cliente(clienteEditar.getNome());
                            manterCliente.setId_usuario(usuarioLogado.getId_usuario());
                            manterCliente.setAcao("Editou");
                            
                            auditarDAO.salvarManterCliente(manterCliente);
                           
                           
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
                 Quarto quartoCadastrar;
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
                                 AuditarDAO auditarDAO = new AuditarDAO();
                                 ManterQuarto manterQuarto = new ManterQuarto();
                                 AuditarDAO manterQuartoDAO = AuditarDAO.getInstance();
                                 
                                 //Obtem o ID do quarto cadastrado.
                                 quartoCadastrar = quartoDAO.consultarQuarto(Integer.parseInt(cadastrarQuarto.getQuarto().getNumero()));
                                 manterQuarto.setId_quarto(quartoCadastrar.getId_quarto());
                                 manterQuarto.setNumero_quarto(Integer.parseInt(quartoCadastrar.getNumero()));
                                 manterQuarto.setId_usuario(usuarioLogado.getId_usuario());
                                 manterQuarto.setAcao("Cadastrou");
                                 
                                 //Salva na tabela.
                                 auditarDAO.salvarManterQuarto(manterQuarto);
                                 
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
            quartoDAO = QuartoDAO.getInstance();
            Quarto quartoDeletar;
            deletarQuarto = DeletarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            //Verifica se o quarto está cadastrado.
            quartoDeletar = quartoDAO.consultarQuarto(Integer.parseInt(deletarQuarto.getQuarto().getNumero()));
            if(deletarQuarto.getQuarto().getNumero().equals("")){//Caso o campo "número" da classe "DeletarrQuarto" esteja em branco.
                JOptionPane.showMessageDialog(null, "Digite informações obrigatórias do quarto para cadastro.");
                
              } else {//Se for encontrado no banco, pode deletar.
                      boolean deletou = false;//Controla se a exclusão foi bem sucedida.
                      deletou = quartoDAO.deletarQuarto(quartoDeletar.getNumero());
                      
                      if(deletou != false){//Se a exclusão foi bem sucedida, este método registra na tabela "manter_quarto" quem excluiu e qual quarto foi excluido.
                          
                                 ManterQuarto manterQuarto = new ManterQuarto();
                                 AuditarDAO manterQuartoDAO = AuditarDAO.getInstance();
                                 
                                 //Obtem o ID do quarto cadastrado.
                                 manterQuarto.setId_quarto(quartoDeletar.getId_quarto());
                                 manterQuarto.setId_usuario(usuarioLogado.getId_usuario());
                                 manterQuarto.setNumero_quarto(Integer.parseInt(quartoDeletar.getNumero()));
                                 manterQuarto.setAcao("Deletou");
                                 
                                 //Salva na tabela.
                                 manterQuartoDAO.salvarManterQuarto(manterQuarto);
                                  JOptionPane.showMessageDialog(null, "Quarto deletado com sucesso!.");
                                  deletarQuarto.dispose();
                                  deletarQuarto.limparCampos();
                                  deletarQuarto = null;  
                      }
                     
                 }
            }catch(Exception erro){
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }
            
            //--------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[17]){//Acionado pelo botão 'Editar" da classe "EditarQuarto"
            quartoDAO = QuartoDAO.getInstance();
            Quarto quartoEditar;
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
                        
                                 ManterQuarto manterQuarto = new ManterQuarto();
                                 AuditarDAO manterQuartoDAO = AuditarDAO.getInstance();
                                 
                                 //Obtem o ID do quarto cadastrado.
                                 manterQuarto.setId_quarto(quartoEditar.getId_quarto());
                                 manterQuarto.setNumero_quarto(Integer.parseInt(quartoEditar.getNumero()));
                                 manterQuarto.setId_usuario(usuarioLogado.getId_usuario());
                                 manterQuarto.setAcao("Editou");
                                 
                                 //Salva na tabela.
                                 manterQuartoDAO.salvarManterQuarto(manterQuarto);
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
        } else if(evento.getSource() == botoes[18]){//Acionado após cliue do botão "Reservar" da classe "TelaPrincipal.
               if(interfaceCadastrarReserva == null || interfaceCadastrarReserva.isValid()){
                  interfaceCadastrarReserva = InterfaceFactory.getInterface(CADASTRAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  interfaceCadastrarReserva.setVisible(true);
                  
               } else {
                   cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   cadastrarReserva.limparCampos();
                   interfaceCadastrarReserva.setVisible(true);
               }
            //---------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[19]){ //Acionado aós clique no botão "Reservar" da classe "CadastrarReserva".
               
                  
                  
                  //Verifica se o cliente está cadastrado
                  clienteDAO = ClienteDAO.getInstance();
                  cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  Cliente cliente = clienteDAO.consultarCliente(cadastrarReserva.getCliente().getCpf());
                  
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
            Quarto quartoEditar;
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
                Reserva reservaCadastrar;
                reservaCadastrar = cadastrarReserva.getReserva();
                reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());
           
                boolean reservou = false;//Responsável por verificar se conseguiu reservar.
                Cliente cliente;
                cliente = cadastrarReserva.getCliente();
                reservou = reservaDAO.cadastrarReserva(reservaCadastrar, cliente, quartoEditar);
                
                if(reservou != false){
                    
                    //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    ManterReserva manterReserva = new ManterReserva();
                    auditarDAO = AuditarDAO.getInstance();
                    manterReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    manterReserva.setLogin(usuarioLogado.getLogin());
                    manterReserva.setAcao("Reservou");
                    manterReserva.setTipo_reserva("RESERVAR");
                    manterReserva.setCliente_reservado(cadastrarReserva.getCliente().getNome());
                    
                    
                    //Obter o ID da reserva.
                    reservaCadastrar = reservaDAO.consultarReserva(cadastrarReserva.getCliente().getId_cliente());
                    manterReserva.setId_reserva(reservaCadastrar.getId_reserva());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterReserva(manterReserva);
                    
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
                Reserva reservaCadastrar;
                reservaCadastrar = cadastrarHospedagem.getHospedagem();
                reservaCadastrar.setId_quarto(quartoEditar.getId_quarto());
                reservaCadastrar.setTipo("HOSPEDAR");
            
                Cliente cliente;
                cliente = cadastrarHospedagem.getCliente();
                
                boolean hospedou = false;
                hospedou = reservaDAO.cadastrarHospedagem(reservaCadastrar, cliente, quartoEditar); 
                
                if(hospedou != false){
                    
                    //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    AuditarHospedagem auditarHospedagem = new AuditarHospedagem();
                    auditarDAO = AuditarDAO.getInstance();
                    auditarHospedagem.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    auditarHospedagem.setLogin(usuarioLogado.getLogin());
                    auditarHospedagem.setAcao("Hospedou");
                    auditarHospedagem.setTipo_reserva("HOSPEDAR");
                    
                    //Obter o ID da reserva.
                    reservaCadastrar = reservaDAO.consultarReserva(cadastrarHospedagem.getCliente().getId_cliente());
                    auditarHospedagem.setId_hospedagem(reservaCadastrar.getId_reserva());
                    auditarHospedagem.setCliente_hospedado(cadastrarHospedagem.getCliente().getNome());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterHospedagem(auditarHospedagem);
                    
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
            quartoDAO = QuartoDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            deletarReserva = DeletarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
               //Alterando o quarto para livre.
               Reserva reserva = deletarReserva.getReserva();//Pegando o ID do quarto para ser alterado.
               
               Quarto quarto = quartoDAO.getQuartoPorID(reserva);
               
               try{
               //Verifica se o quarto foi encontrado. Caso não seja, exibe uma mensagem de alerta sobre o nome fornecido no campo nome, visto que aconterá um erro por causa do campo em branco.
               if(quarto.getNumero().equals("")){
                       
               } else  {
               quarto.setSituacao("LIVRE");
               quartoDAO.editarQuarto(quarto);
               
               boolean deletou = false;
               //Em sequência, deletar a reserva.
               deletou = reservaDAO.deletarReserva(reserva);
               
               if(deletou != false ){
                   //Salva a ação feita na tabela manter_Reserva, para posterior auditação.
                    ManterReserva manterReserva = new ManterReserva();
                    auditarDAO = AuditarDAO.getInstance();
                    manterReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                    manterReserva.setLogin(usuarioLogado.getLogin());
                    manterReserva.setAcao("Deletou");
                    manterReserva.setTipo_reserva("RESERVAR");
                    manterReserva.setCliente_reservado(deletarReserva.getCliente().getNome());
                    
                    //Obter o ID da reserva.
                    manterReserva.setId_reserva(deletarReserva.getReserva().getId_reserva());
                    manterReserva.setCliente_reservado(deletarReserva.getCliente().getNome());
                    
                    //Salva na tabela.
                    auditarDAO.salvarManterReserva(manterReserva);
                    
                    deletarReserva.dispose();
                    deletarReserva.limparCampos(); 
               }
               
               
               }
               }catch(Exception erro){
                   JOptionPane.showMessageDialog(null, "Reserva não encontrada. Certifique-se se o cliente está reservado.");
               }
        
              
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
                           ManterReserva manterReserva = new ManterReserva();
                           auditarDAO = AuditarDAO.getInstance();
                           manterReserva.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                           manterReserva.setLogin(usuarioLogado.getLogin());
                           manterReserva.setAcao("Editou");
                           manterReserva.setTipo_reserva("RESERVAR");
                           manterReserva.setCliente_reservado(editarReserva.getCliente().getNome());
                    
                           //Obter o ID da reserva.
                           manterReserva.setId_reserva(editarReserva.getReserva().getId_reserva());
                           manterReserva.setCliente_reservado(editarReserva.getCliente().getNome());
                    
                            //Salva na tabela.
                            auditarDAO.salvarManterReserva(manterReserva);
                    
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
        } else if(evento.getSource() == botoes[24]){//Acionado pelo botão "Relatório" da classe "TelaPrincipal".
            if(interfaceRelatorioServicos == null || interfaceRelatorioServicos.isValid()){
               interfaceRelatorioServicos = InterfaceFactory.getInterface(RELATORIO_SERVICOS, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Busca no banco, os serviços e retorna os totais encontrados.
               relatorioServicos = RelatorioServicos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               quartoDAO = QuartoDAO.getInstance();
               int[] totais  = new int[5];
               totais = quartoDAO.quantidadeServicosQuartos();
               relatorioServicos.receberQuantias(totais);
               interfaceRelatorioServicos.setVisible(true);
               
            } else {
                
                //Busca no banco, os serviços e retorna os totais encontrados.
                int[] totais  = new int[5];
                totais = quartoDAO.quantidadeServicosQuartos();
                relatorioServicos.receberQuantias(totais);
                interfaceRelatorioServicos.setVisible(true);
            }
            
            //---------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[25]){//Acionado pelo botão Hospedar da classe "Telaprincipal".
                if(interfaceCadastrarHospedagem == null || interfaceCadastrarHospedagem.isValid()){
                   interfaceCadastrarHospedagem = InterfaceFactory.getInterface(CADASTRAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   interfaceCadastrarHospedagem.setVisible(true);
                   
                } else {
                    cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    cadastrarHospedagem.limparCampos();
                    interfaceCadastrarHospedagem.setVisible(true);
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
                        AuditarHospedagem auditarHospedagem = new AuditarHospedagem();
                        auditarDAO = AuditarDAO.getInstance();
                        auditarHospedagem.setId_usuario_responsavel(usuarioLogado.getId_usuario());
                        auditarHospedagem.setLogin(usuarioLogado.getLogin());
                        auditarHospedagem.setAcao("Editou");
                        auditarHospedagem.setTipo_reserva("HOSPEDAR");
                    
                        //Obter o ID da reserva.
                       auditarHospedagem.setId_hospedagem(editarHospedagem.getHospedagem().getId_reserva());
                       auditarHospedagem.setCliente_hospedado(editarHospedagem.getCliente().getNome());
                    
                       //Salva na tabela.
                       auditarDAO.salvarManterHospedagem(auditarHospedagem);
                    
                    
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
              quartoDAO = QuartoDAO.getInstance();
              reservaDAO = ReservaDAO.getInstance();
              deletarHospedagem= DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
              
               //Alterando o quarto para livre.
               Reserva reserva = deletarHospedagem.getHospedagem();//Pegando o ID do quarto para ser alterado.
               
               Quarto quarto = quartoDAO.getQuartoPorID(reserva);
               
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
                    auditarDAO = AuditarDAO.getInstance();
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
              
            
            //----------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == botoes[29]){//Acionado pelo botão "Hospedar" da classe "CadastrarHospedagem".
            
                  clienteDAO = ClienteDAO.getInstance();
                  cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  Cliente cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente().getCpf());
                 
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
            } else{
                fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                fecharHospedagem.limparCampos();
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
                    if(consultarCliente.getPesquisa().equals("   .   .   -  ")){
                        JOptionPane.showMessageDialog(null, "Digite um valor válido para pesquisa.");
                        
                    } else {
                        
                //Adicionando o usuário pesquisado a tabela que exibe os usuários.
                modeloCliente.adicionar(clienteDAO.consultarCliente(consultarCliente.getPesquisa()));
                
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
                if(consultarReserva.getPesquisa().equals("")){
                    new Exception();
                    
                } else {
                reservaDAO = ReservaDAO.getInstance();
                consultarReserva = ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                modeloReserva = ModeloReserva.getInstance();
                
                //Obtendo cliente.
                clienteDAO = ClienteDAO.getInstance();
                Cliente cliente = clienteDAO.consultarCliente(consultarReserva.getPesquisa());
                
                //Adicionando o quarto pesquisado a tabela que exibe os quartos.
                modeloHospedagem = ModeloHospedagem.getInstance();
                modeloHospedagem.adicionar(reservaDAO.pesquisarReserva(cliente));
                
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
                Cliente cliente = clienteDAO.consultarCliente(consultarHospedagem.getPesquisa());
                
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
            //Acionado pelo menu Pedido de Quarto da classe Tela Principal.
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
            
            ProdutoDAO produtoDAO = ProdutoDAO.getInstance();
            produtoDAO.cadastrarProduto(produto);
            
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
           
           produtoDAO.editarProduto(editarProduto.getProduto());
           
           editarProduto.limparCampos();
           editarProduto.dispose();
           modeloProduto = ModeloProduto.getInstance();
           modeloProduto.adicionarLista(produtoDAO.listarProduto());
            
            
            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[49]){
            //Acionado pelo botão Deletar da classe DeletarProduto
            deletarProduto = DeletarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            produtoDAO.deletarProduto(deletarProduto.getProduto());
            deletarProduto.limparCampos();
            deletarProduto.dispose();
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[50]){
            //Acionado pelo botão Cadastrar da classe CadastrarPasseio
            cadastrarPasseio = CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            passeioDAO.cadastrarPasseio(cadastrarPasseio.getPasseio());
            
            cadastrarPasseio.limparCampos();
            cadastrarPasseio.dispose();
            
            modeloPasseio = ModeloPasseio.getInstance();
            modeloPasseio.adicionarLista(passeioDAO.listarPasseio());

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[51]){
            //Acionado pelo botão Editar da classe EditarPasseio
            editarPasseio = EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            passeioDAO.editarPasseio(editarPasseio.getPasseio());
            
            editarPasseio.limparCampos();
            editarPasseio.dispose();
            
            modeloPasseio = ModeloPasseio.getInstance();
            modeloPasseio.adicionarLista(passeioDAO.listarPasseio());
            

            //--------------------------------------------------------------------------------------------------------------------------------
       } else if(evento.getSource() == botoes[52]){
            //Acionado pelo botão Deletar da classe DeletarPasseio
            deletarPasseio = DeletarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            passeioDAO.deletarPasseio(deletarPasseio.getPasseio());
            
            deletarPasseio.limparCampos();
            deletarPasseio.dispose();
    

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
                
                produtoDAO = ProdutoDAO.getInstance();
                modeloProdutoPedido = ModeloProdutoPedido.getInstance();
                modeloProdutoPedido.adicionarLista(produtoDAO.listarProduto());
                interfaceProdutoPedido.setVisible(true);

            } else {
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
            modeloProdutoPedido = ModeloProdutoPedido.getInstance();
            cadastrarPedidoQuarto = CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
             for(int posicao = 0; posicao<modeloProdutoPedido.getProdutosPedidos().size(); posicao ++){
                produtoDAO.cadastrarProdutoPedido(cadastrarPedidoQuarto.getReserva(),modeloProdutoPedido.getProduto(posicao) );
            }
            
            cadastrarPedidoQuarto.dispose();
            cadastrarPedidoQuarto.limparCampos();
            modeloProdutoPedido.limparDados();
            
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
        } else if (evento.getSource() == itensDeMenu[1]) {//Evento acionado pelo iten de menu "Editar Usuario" da classe "TelaPrincipal".
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
            if (interfaceDeletarCliente == null || interfaceDeletarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceDeletarCliente = InterfaceFactory.getInterface(DELETAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceDeletarCliente.setVisible(true);

            } else {
                deletarCliente = DeletarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarCliente.limparCampos();
                interfaceDeletarCliente.setVisible(true);
            }

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
        } else if(evento.getSource() == itensDeMenu[7]){//Acionado pela item de manu "Deletar Quarto" da classe "TelaPrincipal".
            if(interfaceDeletarQuarto == null || interfaceDeletarQuarto.isValid()){
               interfaceDeletarQuarto = InterfaceFactory.getInterface(DELETAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceDeletarQuarto.setVisible(true);
               
            } else {
                deletarQuarto = DeletarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarQuarto.limparCampos();
                interfaceDeletarQuarto.setVisible(true);
            }
            
            //------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[8]){//Acionado pela item de manu "Editar Quarto" da classe "TelaPrincipal".
            if(interfaceEditarQuarto == null || interfaceEditarQuarto.isValid()){
               interfaceEditarQuarto = InterfaceFactory.getInterface(EDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceEditarQuarto.setVisible(true);
               
            } else {
                editarQuarto = EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                editarQuarto.limparCampos();
                interfaceEditarQuarto.setVisible(true);
            }
            
            //---------------------------------------------------------------------------------------------------------------------------------------   
        }else if(evento.getSource() == itensDeMenu[9]){//Acionado pela item de manu "Cadastrar Cliente" da classe "TelaPrincipal".
            if (interfaceCadastrarCliente == null || interfaceCadastrarCliente.isValid()) {//Controla a quantidade de interface abertas. Só permite abrir uma.
                interfaceCadastrarCliente = InterfaceFactory.getInterface(CADASTRAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                interfaceCadastrarCliente.setVisible(true);

            } else {
                cadastrarCliente = CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarCliente.limparCampos();
                interfaceCadastrarCliente.setVisible(true);
            }
            //---------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[10]){//Acionado pelo iten e menu "Consultar Cliente" da classe "TelaPrincipal".
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
            if(interfaceDeletarReserva == null || interfaceDeletarReserva.isValid()){
               interfaceDeletarReserva = InterfaceFactory.getInterface(DELETAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceDeletarReserva.setVisible(true);
               
            } else {
                deletarReserva = DeletarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                deletarReserva.limparCampos();
                interfaceDeletarReserva.setVisible(true);
            }
            
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
        } else if(evento.getSource() == itensDeMenu[13]){//Acionado pelo item de menu "Cadastrar Reserva" da classe "TelaPrincipal".
            if(interfaceCadastrarReserva == null || interfaceCadastrarReserva.isValid()){
                  interfaceCadastrarReserva = InterfaceFactory.getInterface(CADASTRAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                  interfaceCadastrarReserva.setVisible(true);
                  
               } else {
                cadastrarReserva = CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                cadastrarReserva.limparCampos();
                   interfaceCadastrarReserva.setVisible(true);
               }
            
            //----------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[14]){//Acionado pelo item de Menu "Consultar Reserva" da classe "TelaPrincipal".
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
        } else if(evento.getSource() == itensDeMenu[16]){//Acionaod pelo item de menu "Auditar Quarto" da classe "TelaPrincipal".
            if(interfaceAuditarQuarto == null || interfaceAuditarQuarto.isValid()){
               interfaceAuditarQuarto = InterfaceFactory.getInterface(AUDITAR_QUARTO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Lista as ações realizadas por usuários sobre os quartos.
               auditarDAO = AuditarDAO.getInstance();
               modeloAuditarQuarto = ModeloAuditarQuarto.getInstance();
               modeloAuditarQuarto.adicionarLista(auditarDAO.auditarQuarto());
               interfaceAuditarQuarto.setVisible(true);
               
            } else {
                //Lista as ações realizadas por usuários sobre os quartos.
                modeloAuditarQuarto.adicionarLista(auditarDAO.auditarQuarto());
                interfaceAuditarQuarto.setVisible(true);
                interfaceAuditarQuarto.setVisible(true);
            }
            
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
            if(interfaceDeletarHospedagem == null || interfaceDeletarHospedagem.isValid()){
                   interfaceDeletarHospedagem = InterfaceFactory.getInterface(DELETAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                   interfaceDeletarHospedagem.setVisible(true);
                   
                } else {
                    deletarHospedagem = DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                    deletarHospedagem.limparCampos();
                    interfaceDeletarHospedagem.setVisible(true);
                }
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
        } else if(evento.getSource() == itensDeMenu[21]){//Acionado pelo item de menu "Auditar Cliente".
            if(interfaceAuditarCliente == null || interfaceAuditarCliente.isValid()){
               interfaceAuditarCliente = InterfaceFactory.getInterface(AUDITAR_CLIENTE, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Acionando método que preenche a tabela.
               auditarDAO = AuditarDAO.getInstance();
               modeloAuditarCliente = ModeloAuditarCliente.getInstance();
               modeloAuditarCliente.adicionarLista(auditarDAO.auditarCliente());
               interfaceAuditarCliente.setVisible(true);
               
            } else {
                modeloAuditarCliente.adicionarLista(auditarDAO.auditarCliente());
               interfaceAuditarCliente.setVisible(true);
            }
            //-------------------------------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[22]){//Acionado pelo item de menu "Auditar Usuario".
            if(interfaceAuditarUsuario == null || interfaceAuditarUsuario.isValid()){
               interfaceAuditarUsuario= InterfaceFactory.getInterface(AUDITAR_USUARIO, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               //Lista a tabela com as ações feita nos usuários.
               auditarDAO = AuditarDAO.getInstance();
               modeloAuditarUsuario = ModeloAuditarUsuario.getInstance();
               modeloAuditarUsuario.adicionarLista(auditarDAO.auditarUsuario());
               interfaceAuditarUsuario.setVisible(true);
            } else {
                modeloAuditarUsuario.adicionarLista(auditarDAO.auditarUsuario());
               interfaceAuditarUsuario.setVisible(true);
            }
            
            //---------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[23]){//Acionado pelo item de menu "Auditar Reserva" da classe "TelaPrincipal".
            if(interfaceAuditarReserva == null || interfaceAuditarReserva.isValid()){
               interfaceAuditarReserva = InterfaceFactory.getInterface(AUDITAR_RESERVA, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
               
               //Preenche a tabela da classe "Auditar Reserva".
               auditarDAO = AuditarDAO.getInstance();
               modeloAuditarReserva = ModeloAuditarReserva.getInstance();
               modeloAuditarReserva.adicionarLista(auditarDAO.auditarReserva());
               interfaceAuditarReserva.setVisible(true);
               
            } else {
                modeloAuditarReserva.adicionarLista(auditarDAO.auditarReserva());
                interfaceAuditarReserva.setVisible(true);
            }
        //----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == itensDeMenu[24]){//Acionado pelo item de menu "Auditar Hsopedagem" da classe "TelaPrincipal".
            if(interfaceAuditarHospedagem == null || interfaceAuditarHospedagem.isValid()){
               interfaceAuditarHospedagem = InterfaceFactory.getInterface(AUDITAR_HOSPEDAGEM, botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               interfaceAuditarHospedagem.setVisible(true);
               
               //Preenche a tabela da classe "Auditar Hospedagem".
               auditarDAO = AuditarDAO.getInstance();
               modeloAuditarHospedagem = ModeloAuditarHospedagem.getInstance();
               modeloAuditarHospedagem.adicionarLista(auditarDAO.auditarHospedagem());
            } else {
                 modeloAuditarHospedagem.adicionarLista(auditarDAO.auditarHospedagem());
                 interfaceAuditarHospedagem.setVisible(true);
            }
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
        } else if(evento.getSource() == itensDeMenu[34]){//Acionado pelo menu Consultar Pedidos de Quarto da classe Tela Principal.
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

    }

    @Override
    public void focusLost(FocusEvent evento
    ) {
        if (evento.getSource() == camposDeTexto[0]) {//Acionado quando o campo "Login" da classe "DeletarUsuario" perde o foco.
            deletarUsuario = DeletarUsuario.getInstance(botoes, itensDeMenu, usuarioLogado, camposDeTexto);
            usuarioDAO = UsuarioDAO.getInstance();

            //Busca o usuário informado no banco de dados antes de excluir e retorna para a classe "DeletarUsuario".
            deletarUsuario.recebeUsuario(usuarioDAO.consultarUsuario(deletarUsuario.getUsuario().getLogin()));

            //----------------------------------------------------------------------------------------------------------------
        } else if (evento.getSource() == camposDeTexto[1]) {//Acionado quando o campo "Login" da classe "EditarUsuario" perde o foco.
            Usuario usuarioLocal;
            editarUsuario = EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            usuarioDAO = UsuarioDAO.getInstance();
            usuarioLocal = usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin());

            //Busca o usuário informado no banco de dados antes de editar e retorna para a classe "EditarUsuario".
            editarUsuario.recebeUsuario(usuarioDAO.consultarUsuario(editarUsuario.getUsuario().getLogin()));
            
            //-------------------------------------------------------------------------------------------------------------------
        }  else if(evento.getSource() == camposDeTexto[4]){//Acionado quando o campo "Nome" da classe "DeletarCliente" perde o foco.
            clienteDAO = ClienteDAO.getInstance();
            deletarCliente = DeletarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Busca o cliente informado no banco de dados antes de excluir e retorna para a classe "DeletarCliente".
            deletarCliente.receberCliente(clienteDAO.consultarCliente(deletarCliente.getCliente().getNome()));
            
           //---------------------------------------------------------------------------------------------------------------------------
        }  else if(evento.getSource() == camposDeTexto[5]){//Acionado quando o campo "Numero" da classe "DeletarQuarto" perde o foco.
            quartoDAO = QuartoDAO.getInstance();
            deletarQuarto = DeletarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            try{
            //Busca o quarto informado no banco de dados antes de excluir e retorna para a classe "DeletarCliente".
            deletarQuarto.receberQuarto(quartoDAO.consultarQuarto(Integer.parseInt(deletarQuarto.getQuarto().getNumero())));
            }catch(Exception erro){
                deletarQuarto.limparCampos();
            }
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
             Cliente cliente = clienteDAO.consultarCliente(cadastrarReserva.getCliente().getCpf());
             if(cliente.getId_cliente() == reservaDAO.consultarReserva(cliente.getId_cliente()).getId_cliente() && !cliente.getCpf().equals("")){
                 JOptionPane.showMessageDialog(null, "Cliente encontra-se reservado.");
                 
             } else {
                 cadastrarReserva.receberliente(clienteDAO.consultarCliente(cadastrarReserva.getCliente().getCpf()));
             }
                
             }catch(Exception erro){
                 
            }
            //-----------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[8]){//Acionado quando o campo Nome da classe DeletarReserva perde o foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            deletarReserva = DeletarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            Cliente cliente = clienteDAO.consultarCliente(deletarReserva.getCliente().getNome());
            //Segundo, reserva.
            Reserva reserva = reservaDAO.consultarReserva(cliente.getId_cliente());
            //Terceiro, quarto.
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            deletarReserva.receberReserva(reserva, cliente, quarto);
            
            
        } else if(evento.getSource() == camposDeTexto[10]){//Acionado quando o campo nome da classe "CadastrarHospedagem" perder foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            cadastrarHospedagem = CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            if(cadastrarHospedagem.getCliente().getCpf().equals("")){//Se o campo "nome" estiver em branco, ele nem procura o cliente no banco.
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                cadastrarHospedagem.btnHospedar.setEnabled(true);
                
            } else {
                
                   //Verifica se o cliente está reservado antes de hospeda-lo.
                   Reserva reserva;
                   Cliente cliente; //Pegando o ID do cliente.
                   cliente = clienteDAO.consultarCliente(cadastrarHospedagem.getCliente().getCpf());
                   reserva = reservaDAO.consultarReserva(cliente.getId_cliente());
                   
                   
                   try{
                   if(reserva.getEntrada() == null){//Significa que não há reserva no nome daquele cliente.
                       
                       //Este if, investiga se o cliente já está hospedado. Se tiver, não permite que ele se hospede novamente.
                       if(cliente.getId_cliente() == reservaDAO.consultarHospedagem(cliente.getId_cliente()).getId_cliente() && !cliente.getCpf().equals("")){//Significa que o cliente está hospedado.
                           JOptionPane.showMessageDialog(null, "Cliente encontra-se hospedado.");
                          
                       } else {
                            cadastrarHospedagem.receberliente(clienteDAO.consultarCliente(cadastrarHospedagem.getCliente().getCpf())); 
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
            Cliente cliente = clienteDAO.consultarCliente(editarHospedagem.getCliente().getNome());
            //Segundo, hospedagem.
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            //E por último, o querto.
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
                
            editarHospedagem.receberHospedagem(reserva, cliente, quarto);;
            }catch(Exception erro){
                
            }
            //----------------------------------------------------------------------------------------------------------------------------------------
        } else if(evento.getSource() == camposDeTexto[12]){//Acionado quando o campo nome da classe "DeletarHospedagem" perder foco.
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            deletarHospedagem = DeletarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            Cliente cliente = clienteDAO.consultarCliente(deletarHospedagem.getCliente().getNome());
            //Segundo, reserva.
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            //Terceiro, quarto.
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            deletarHospedagem.receberHospedagem(reserva, cliente, quarto);
            
        } else if(evento.getSource() == camposDeTexto[13]){//Acionado pelo campo nome da classe "FecharHsopedagem".
            clienteDAO = ClienteDAO.getInstance();
            reservaDAO = ReservaDAO.getInstance();
            quartoDAO = QuartoDAO.getInstance();
            fecharHospedagem = FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Primeiro busca o cliente.
            Cliente cliente = clienteDAO.consultarCliente(fecharHospedagem.getCliente().getNome());
            //Segundo, reserva.
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            //Terceiro, quarto.
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            fecharHospedagem.receberHospedagem(reserva, cliente, quarto);
        } else if(evento.getSource() == camposDeTexto[14]){//Acionado pelo campo nome do produto classe EditarProduto
            editarProduto = EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            editarProduto.receberProduto(produtoDAO.consultarProduto(editarProduto.getProduto().getNome()));
            
        } else if(evento.getSource() == camposDeTexto[15]){//Acionado pelo campo nome do produto classe DeletarProduto
            deletarProduto = DeletarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            produtoDAO = ProdutoDAO.getInstance();
            
            deletarProduto.receberProduto(produtoDAO.consultarProduto(deletarProduto.getProduto().getNome()));
            
        } else if(evento.getSource() == camposDeTexto[16]){//Acionado pelo campo titulo do passeio classe EditarPasseio
            
            
        } else if(evento.getSource() == camposDeTexto[17]){//Acionado pelo campo titulo do passeio classe DeletarPasseio
            deletarPasseio = DeletarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            passeioDAO = PasseioDAO.getInstance();
            
            deletarPasseio.receberPasseio(passeioDAO.consultarPasseio(deletarPasseio.getPasseio().getTitulo()));
            
        } else if(evento.getSource() == camposDeTexto[18]){
            adicionarPassageiroPasseio = AdicionarPassageiroPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            //Buscando o cliente no banco.
            clienteDAO = ClienteDAO.getInstance();
            Cliente cliente = clienteDAO.consultarCliente(adicionarPassageiroPasseio.getCliente().getCpf());
            
            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            
            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
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
            Cliente cliente = clienteDAO.consultarCliente(cadastrarPedidoQuarto.getHospede());
            
            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            
            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
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
            Cliente cliente = clienteDAO.consultarCliente(consultarPedidoQuarto.getHospede());
            
            //Buscando a hospedagem.
            reservaDAO = ReservaDAO.getInstance();
            Reserva reserva = reservaDAO.consultarHospedagem(cliente.getId_cliente());
            
            //Buscando quarto.
            quartoDAO = QuartoDAO.getInstance();
            Quarto quarto = quartoDAO.getQuartoPorID(reserva);
            
            consultarPedidoQuarto.receberHospede(reserva, cliente, quarto);
            
            
            
            modeloPedidosDeQuarto.adicionarLista(produtoDAO.listarPedidosPorHospedagem(reserva));
            
            
        } 
    }
    //----------------------------------------------------//-------------------------------------------------------------------

    public static ControlarEventos getInstance(JButton[] botoes, JMenuItem[] itensDeMenu, JTextField[] camposDeTexto, Usuario usuarioLogado) {
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
