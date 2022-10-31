package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOPassageiro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAdicionarClientePasseio extends AbstractTableModel{
    
    private ArrayList<DTOPassageiro>  dados;
    private String[]                      colunas = {"Nome","CPF","Quarto","Celular"};
    public static ModeloAdicionarClientePasseio instance = null;
    private SimpleDateFormat              formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModeloAdicionarClientePasseio(){
        dados = new ArrayList<>();
        
    }
    
    public void adicionarLista(ArrayList<DTOPassageiro> lista){
        dados = lista;
       
    }
    
    
    public void remover(int indexLinha) {
        dados.remove(indexLinha);
        fireTableDataChanged();
    }
    
    public void adicionar(DTOPassageiro passageiro){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(passageiro);
        fireTableDataChanged();
    }    
    
    
    public DTOPassageiro getPasseio(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
    
    public void zerarLista(){
        dados = new ArrayList<>();
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
                    return dados.get(linha).getNome_passageiro();

                case 1:
                    return dados.get (linha).getCpf();
                    
                case 2:
                    return dados.get (linha).getQuarto();
                 
                case 3:
                    return dados.get (linha).getResponsavel();
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }
    
    
    public static ModeloAdicionarClientePasseio getInstance(){
        if(instance == null){
            instance = new ModeloAdicionarClientePasseio();
        }
        return instance;
    }
}
