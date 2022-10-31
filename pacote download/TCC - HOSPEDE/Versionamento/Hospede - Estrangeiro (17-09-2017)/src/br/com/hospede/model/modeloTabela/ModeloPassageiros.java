package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOPassageiro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloPassageiros extends AbstractTableModel{
    
    private ArrayList<DTOPassageiro>  dados;
    private String[]                      colunas = {"Nome","CPF/PASSAPORTE","Quarto","Responsavel","Editar","Deletar"};
    public static ModeloPassageiros instance = null;
    private SimpleDateFormat              formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModeloPassageiros(){
        dados = new ArrayList<>();
        
    }
    
    public void limparDados(){
        dados = new ArrayList<>();
    }
    public void adicionarLista(ArrayList<DTOPassageiro> lista){
        dados = lista;
       
    }
    
    public void adicionar(DTOPassageiro adicionar){
        dados.add(adicionar);
         fireTableDataChanged();
    }
    
    public ArrayList<DTOPassageiro> getPassageiros(){
        return dados;
    }
    
    public DTOPassageiro getPassageiro(int indice){
        return dados.get(indice);
    }
    
    public void remover(int linha){
        try{
        dados.remove(linha);
        fireTableDataChanged();
        }catch(Exception erro){
                
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
                case 4:
                    return dados.get (linha).getEditar();
                case 5:
                    return dados.get (linha).getDeletar();
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }
    
    
    public static ModeloPassageiros getInstance(){
        if(instance == null){
            instance = new ModeloPassageiros();
        }
        return instance;
    }
}
