package br.com.hospede.control;

import br.com.hospede.model.DTO.Usuario;
import br.com.hospede.view.EntrandoOuSaindo;
import br.com.hospede.view.RelatorioServicos;
import br.com.hospede.view.SelecionarQuarto;
import br.com.hospede.view.CadastrarUsuario;
import br.com.hospede.view.ConsultarUsuario;
import br.com.hospede.view.EditarUsuario;
import br.com.hospede.view.TelaPrincipal;
import br.com.hospede.view.AuditarCliente;
import br.com.hospede.view.AuditarHospedagem;
import br.com.hospede.view.AuditarQuarto;
import br.com.hospede.view.AuditarReserva;
import br.com.hospede.view.AuditarUsuario;
import br.com.hospede.view.CadastrarCliente;
import br.com.hospede.view.ConsultarCliente;
import br.com.hospede.view.EditarCliente;
import br.com.hospede.view.CadastrarHospedagem;
import br.com.hospede.view.ConsultarHospedagem;
import br.com.hospede.view.EditarHospedagem;
import br.com.hospede.view.FecharHospedagem;
import br.com.hospede.view.CadastrarQuarto;
import br.com.hospede.view.ConsultarQuarto;
import br.com.hospede.view.EditarQuarto;
import br.com.hospede.view.CadastrarReserva;
import br.com.hospede.view.ConsultarPasseio;
import br.com.hospede.view.ConsultarReserva;
import br.com.hospede.view.EditarReserva;
import br.com.hospede.view.CadastrarPasseios;
import br.com.hospede.view.CadastrarPedidoQuarto;
import br.com.hospede.view.CadastrarProduto;
import br.com.hospede.view.EditarPasseio;
import br.com.hospede.view.ConsultarPedidoQuarto;
import br.com.hospede.view.ConsultarProduto;
import br.com.hospede.view.EditarProduto;
import br.com.hospede.view.ProdutoPedido;
import br.com.hospede.view.SelecionarPasseioCadastrarPassageiro;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class InterfaceFactory extends JFrame {

    public static InterfaceFactory getInterface(int codigoInterface, JButton botoes[], JMenuItem itensDeMenu[],
                                                 JTextField camposDeTexto[], Usuario usuarioLogado) { 

        switch (codigoInterface) {
            case 1:
                //Retorna uma inst??ncia da classe "TelaPrincipal".
                return TelaPrincipal.getInstance(usuarioLogado);

            case 2:
                //Retorna uma inst??ncia da classe "CadastrarUsuario".
                return CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);

            case 4:
                //Retorna uma inst??ncia da classe "ConsultarUsuario".
                return ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 5:
                //Retorna uma inst??ncia da classe "EditarUsuario".
                return EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 6:
                //Retorna uma inst??ncia da classe "CadastrarCliente".
                return CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);  
                
            case 8:
                //Retorna uma inst??ncia da classe "ConsultarCliente".
                return ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 9:
                //Retorna uma inst??ncia da classe "EditarCliente".
                return EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 10:
                //Retorna uma inst??ncia da classe "CadastrarQuarto".
                return CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 11:
                //Retorna uma inst??ncia da classe "ConsultarQuarto".
                return ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 12:
                //Retorna uma inst??ncia da classe "EditarQuarto".
                return EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);    
                
            case 14:
                //Retorna uma inst??ncia da classe "CadastrarReserva".
                return CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 15:
                //Retorna uma inst??ncia da classe "SelecionarQuarto".
                return SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
            case 16:
                //Retorna uma inst??ncia da classe "ConsultarReserva".
                return ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 18:
                //Retorna uma inst??ncia da classe "EditarReserva".
                return EditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 19:
                //Retorna uma inst??ncia da classe "RelatorioServicos".
                return RelatorioServicos.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
            case 20:
                //Retorna uma inst??ncia da classe "AuditarQuarto".
                return AuditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
            case 21:
                //Retorna uma inst??ncia da classe "CadastrarHospedagem".
                return CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
            case 22:
                //Retorna uma inst??ncia da classe "ConsultarHospedagem".
                return ConsultarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
            case 24:
                //Retorna uma inst??ncia da classe "EditarHospedagem".
                return EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);    
                
            case 25:
                //Retorna uma inst??ncia da classe "AuditarCliente".
                return AuditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
           case 26:
                //Retorna uma inst??ncia da classe "AuditarUsuario".
                return AuditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
           case 27:
                //Retorna uma inst??ncia da classe "AuditarReserva".
                return AuditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
                
           case 28:
                //Retorna uma inst??ncia da classe "FecarHospedagem".
                return FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
           case 29:
                //Retorna uma inst??ncia da classe "EntrandoOuSaindo".
                return EntrandoOuSaindo.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
                
           case 30:
                //Retorna uma inst??ncia da classe "AuditarHospedagem".
               return AuditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
               
           case 31:
                //Retorna uma inst??ncia da classe "ReservarPasseio".
               return CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
               
           case 32:
                //Retorna uma inst??ncia da classe "ConsultarPasseio".
               return EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
               
           case 33:
                //Retorna uma inst??ncia da classe "ConsultarPasseio".
               return EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado); 
               
           case 35:
                //Retorna uma inst??ncia da classe "ConsultarPasseio".
               return ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto,usuarioLogado);
               
           case 36:
                //Retorna uma inst??ncia da classe "ConsultarPasseio".
               return CadastrarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
           case 37:
                //Retorna uma inst??ncia da classe "EditarProduto".
               return EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
           case 39:
                //Retorna uma inst??ncia da classe "ConsultarProduto".
               return ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
           case 40:
                //Retorna uma inst??ncia da classe "CadastrarPedidoQuarto".
               return CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
           case 41:
                //Retorna uma inst??ncia da classe "ConsultarPasseio".
               return ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
               
           case 42:
                //Retorna uma inst??ncia da classe "SelecionarPasseioCadastrarPassageiro".
               return SelecionarPasseioCadastrarPassageiro.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            
           case 44:
                //Retorna uma inst??ncia da classe "ProdutoPedido".
               return ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
           
           case 45:
                //Retorna uma inst??ncia da classe "ConsultarPedidoQuarto".
               return ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado);
            default:
                break;
        }
        return null;
    }
}
