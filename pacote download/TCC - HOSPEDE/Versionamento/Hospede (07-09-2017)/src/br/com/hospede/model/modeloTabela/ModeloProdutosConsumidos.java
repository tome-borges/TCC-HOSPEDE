package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOProduto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloProdutosConsumidos extends AbstractTableModel{
    
    private ArrayList<DTOProduto>  dados;
    private String[]                   colunas = {"Nome", "Quantidade Pedida", "preço"};
    public static ModeloProdutosConsumidos instance = null;
    private SimpleDateFormat           formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModeloProdutosConsumidos(){
        dados = new ArrayList<>();
    }
    
    public void adicionarLista(ArrayList<DTOProduto> lista){
        dados = lista;
        fireTableDataChanged();
    }
    
    public void adicionar(DTOProduto produto){
        dados.add(produto);
         fireTableDataChanged();
    }
    
     public DTOProduto getProduto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
     
     public void remover(int linha){
        try{
        dados.remove(linha);
        fireTableDataChanged();
        }catch(Exception erro){
                
                }
    }
     
    public void limparDados(){
        dados = new ArrayList<>();
        fireTableDataChanged();
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

        try {
            switch (coluna) {
                case 0:
                    return dados.get(linha).getNome();

                case 1:
                    return dados.get (linha).getQuantidade();
                    
                case 2:
                    return dados.get (linha).getPreco();
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }
    
    
    public static ModeloProdutosConsumidos getInstance(){
        if(instance == null){
            instance = new ModeloProdutosConsumidos();
        }
        return instance;
    }
}
