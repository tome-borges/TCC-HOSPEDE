package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOProduto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloProdutoPedido extends AbstractTableModel{
     private ArrayList<DTOProduto> dados;
    private String[]           colunas = {"Nome","Preço","Data Entrega","Quantidade"};
    public static ModeloProdutoPedido instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloProdutoPedido() {
        dados = new ArrayList<DTOProduto>();
    }

    //Recebendo as listas de quarto, cliente e reserva.
    public void adicionarLista(ArrayList<DTOProduto> lista) {
        dados = lista;
        fireTableDataChanged();
    }
    
    
    public DTOProduto getProduto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
    
    public ArrayList<DTOProduto> getProdutosPedidos(){
        return dados;
    }
    

    public int getRowCount() {
        return dados.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int index) {
        return colunas[index];
    }

   public Object getValueAt(int linha, int coluna) {
       try{
        switch (coluna) {
            case 0:
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getPreco();
            
            case 3:
                return dados.get (linha).getQuantidade();
 
            default:
                return null;
        }
                        
        }catch(Exception erro){
                return null;
        }
    }
   
  
   
   public void limparDados(){
       dados = new ArrayList<>();
   }
  
    public static ModeloProdutoPedido getInstance() {
        if (instance == null) {
            instance = new ModeloProdutoPedido();
        }
        return instance;
    }
}
