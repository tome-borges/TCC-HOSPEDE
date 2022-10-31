package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOPasseio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloSelecionarPasseio extends AbstractTableModel{
    
    private ArrayList<DTOPasseio> dados;
    private String[]           colunas = {"Título","Ingresso","Data"};
    public static ModeloSelecionarPasseio instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloSelecionarPasseio() {
        dados = new ArrayList<DTOPasseio>();
    }

    //Recebendo as listas de quarto, cliente e reserva.
    public void adicionarLista(ArrayList<DTOPasseio> lista) {
        dados = lista;
        fireTableDataChanged();
    }
    
     public void remover(int indexLinha) {
        dados.remove(indexLinha);
        fireTableDataChanged();
    }
    
    public void adicionar(DTOPasseio passeio){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(passeio);
        fireTableDataChanged();
    }    
    
    
    public DTOPasseio getPasseio(int rowIndex) {     
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
                return dados.get(linha).getTitulo();
            case 1:
                return dados.get(linha).getIngresso();
            case 2:
                return dados.get (linha).getData();
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
  
    public static ModeloSelecionarPasseio getInstance() {
        if (instance == null) {
            instance = new ModeloSelecionarPasseio();
        }
        return instance;
    }
}
