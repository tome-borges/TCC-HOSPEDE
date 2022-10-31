package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.Cliente;
import br.com.hospede.model.DTO.DTOPasseio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloPasseio extends AbstractTableModel{
    
    private ArrayList<DTOPasseio> dados;
    private String[]           colunas = {"Título","Ingresso","Data","Editar","Deletar"};
    public static ModeloPasseio instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloPasseio() {
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
            case 3:
                return dados.get (linha).getEditar();
            case 4:
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
  
    public static ModeloPasseio getInstance() {
        if (instance == null) {
            instance = new ModeloPasseio();
        }
        return instance;
    }
}
