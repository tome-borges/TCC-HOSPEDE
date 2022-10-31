package br.com.hospede.control;

import br.com.hospede.model.dto.DTOUsuario;
import br.com.hospede.view.SelecionarQuarto;
import br.com.hospede.view.CadastrarUsuario;
import br.com.hospede.view.ConsultarUsuario;
import br.com.hospede.view.EditarUsuario;
import br.com.hospede.view.TelaPrincipal;
import br.com.hospede.view.AuditarCliente;
import br.com.hospede.view.AuditarHospedagem;
import br.com.hospede.view.AuditarPasseio;
import br.com.hospede.view.AuditarProduto;
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
import br.com.hospede.view.SelecionarCliente;
import br.com.hospede.view.SelecionarPasseioCadastrarPassageiro;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class InterfaceFactory extends JFrame implements ItemListener{

    public static InterfaceFactory getInterface(int codigoInterface, JButton botoes[], JMenuItem itensDeMenu[],
                                                 JTextField camposDeTexto[], DTOUsuario usuarioLogado, Object[] modeloTabelas) { 

        switch (codigoInterface) {
            case 1:
                //Retorna uma instância da classe "TelaPrincipal".
                return TelaPrincipal.getInstance(usuarioLogado);

            case 2:
                //Retorna uma instância da classe "CadastrarUsuario".
                return CadastrarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);

            case 4:
                //Retorna uma instância da classe "ConsultarUsuario".
                return ConsultarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 5:
                //Retorna uma instância da classe "EditarUsuario".
                return EditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 6:
                //Retorna uma instância da classe "CadastrarCliente".
                return CadastrarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);  
                
            case 8:
                //Retorna uma instância da classe "ConsultarCliente".
                return ConsultarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 9:
                //Retorna uma instância da classe "EditarCliente".
                return EditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 10:
                //Retorna uma instância da classe "CadastrarQuarto".
                return CadastrarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 11:
                //Retorna uma instância da classe "ConsultarQuarto".
                return ConsultarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 12:
                //Retorna uma instância da classe "EditarQuarto".
                return EditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);    
                
            case 14:
                //Retorna uma instância da classe "CadastrarReserva".
                return CadastrarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 15:
                //Retorna uma instância da classe "SelecionarQuarto".
                return SelecionarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            
            case 16:
                //Retorna uma instância da classe "ConsultarReserva".
                return ConsultarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 18:
                //Retorna uma instância da classe "EditarReserva".
                return EditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
            case 20:
                //Retorna uma instância da classe "AuditarQuarto".
                return AuditarQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
            case 21:
                //Retorna uma instância da classe "CadastrarHospedagem".
                return CadastrarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
            case 22:
                //Retorna uma instância da classe "ConsultarHospedagem".
                return ConsultarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
            case 24:
                //Retorna uma instância da classe "EditarHospedagem".
                return EditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);    
                
            case 25:
                //Retorna uma instância da classe "AuditarCliente".
                return AuditarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
           case 26:
                //Retorna uma instância da classe "AuditarUsuario".
                return AuditarUsuario.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
           case 27:
                //Retorna uma instância da classe "AuditarReserva".
                return AuditarReserva.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
                
           case 28:
                //Retorna uma instância da classe "FecarHospedagem".
                return FecharHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
                
           case 30:
                //Retorna uma instância da classe "AuditarHospedagem".
               return AuditarHospedagem.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
               
           case 31:
                //Retorna uma instância da classe "ReservarPasseio".
               return CadastrarPasseios.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
               
           case 32:
                //Retorna uma instância da classe "ConsultarPasseio".
               return EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
               
           case 33:
                //Retorna uma instância da classe "ConsultarPasseio".
               return EditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas); 
               
           case 35:
                //Retorna uma instância da classe "ConsultarPasseio".
               return ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto,usuarioLogado, modeloTabelas);
               
           case 36:
                //Retorna uma instância da classe "ConsultarPasseio".
               return CadastrarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
               
           case 37:
                //Retorna uma instância da classe "EditarProduto".
               return EditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
               
           case 39:
                //Retorna uma instância da classe "ConsultarProduto".
               return ConsultarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
               
           case 40:
                //Retorna uma instância da classe "CadastrarPedidoQuarto".
               return CadastrarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
               
           case 41:
                //Retorna uma instância da classe "ConsultarPasseio".
               return ConsultarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
               
           case 42:
                //Retorna uma instância da classe "SelecionarPasseioCadastrarPassageiro".
               return SelecionarPasseioCadastrarPassageiro.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            
           case 44:
                //Retorna uma instância da classe "ProdutoPedido".
               return ProdutoPedido.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
           
           case 45:
                //Retorna uma instância da classe "ConsultarPedidoQuarto".
               return ConsultarPedidoQuarto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
           case 46:
                //Retorna uma instância da classe "AuditarProduto".
               return AuditarProduto.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
           case 47:
                //Retorna uma instância da classe "AuditarPasseio".
               return AuditarPasseio.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
           case 48:
                //Retorna uma instância da classe "SelecionarCliente".
               return SelecionarCliente.getInstance(botoes, itensDeMenu, camposDeTexto, usuarioLogado, modeloTabelas);
            default:
                break;
        }
        return null;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
