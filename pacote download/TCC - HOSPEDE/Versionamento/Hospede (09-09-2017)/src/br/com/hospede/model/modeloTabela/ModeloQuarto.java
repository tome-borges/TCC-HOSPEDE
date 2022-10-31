package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.Quarto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloQuarto extends AbstractTableModel{
    private ArrayList<Quarto> dados;
    private String[]           colunas = {"Situação", "Quarto", "Categoria", "Cliente", "Diária", "Entrada", "Saída","Editar","Deletar"};
    public static ModeloQuarto instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloQuarto() {
        dados = new ArrayList<Quarto>();
    }

    //Recebendo as listas de quarto, cliente e reserva.
    public void adicionarLista(ArrayList<Quarto> lista) {
        dados = lista;
        fireTableDataChanged();
    }
    
    
    public Quarto getQuarto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
    
    public void adicionar(Quarto quarto){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<Quarto>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(quarto);
        fireTableDataChanged();
    }
    
    public void remover(int indexLinha) {
        dados.remove(indexLinha);
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
       try{
        switch (coluna) {
            case 0:
                return dados.get(linha).getIconeDeSituacao();
            case 1:
                return dados.get(linha).getNumero();
            case 2:
                return dados.get (linha).getCategoria();
            case 3:
                return dados.get (linha).getNome_cliente();
            case 4:
                return "R$ " + dados.get(linha).getDiaria();
            case 5:
                       //Formatando data para dia/mês/ano
                return formatarData.format(dados.get(linha).getEntrada());
            case 6:
                        //Formatando data para dia/mês/ano
                return formatarData.format(dados.get (linha).getSaida());
            case 7:
                return dados.get(linha).getEditar();
            case 8:
                return dados.get(linha).getDeletar();
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
  
    public static ModeloQuarto getInstance() {
        if (instance == null) {
            instance = new ModeloQuarto();
        }
        return instance;
    }
}
