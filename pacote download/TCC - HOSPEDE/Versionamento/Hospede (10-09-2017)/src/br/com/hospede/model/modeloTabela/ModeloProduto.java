package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOProduto;
import br.com.hospede.model.DTO.DTOUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloProduto extends AbstractTableModel{
    
    private ArrayList<DTOProduto> dados;
    private String[]           colunas = {"Nome","Preço","Data Entrega","Quantidade","Editar","Deletar"};
    public static ModeloProduto instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

     public void adicionar(DTOProduto produto){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<DTOProduto>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(produto);
        fireTableDataChanged();
    }
     
    public ModeloProduto() {
        dados = new ArrayList<DTOProduto>();
    }
    
    public DTOProduto getProduto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
    
    public void remover(int indexLinha) {
        dados.remove(indexLinha);
        fireTableDataChanged();
    }

    //Recebendo as listas de quarto, cliente e reserva.
    public void adicionarLista(ArrayList<DTOProduto> lista) {
        dados = lista;
        fireTableDataChanged();
    }
    
    
    public DTOProduto getQuarto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
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
            case 2:
                return dados.get (linha).getEntrega();
            case 3:
                return dados.get (linha).getQuantidade();
            case 4:
                return dados.get (linha).getEditar();
            case 5:
                return dados.get (linha).getDeletar();
 
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
  
    public static ModeloProduto getInstance() {
        if (instance == null) {
            instance = new ModeloProduto();
        }
        return instance;
    }
}
