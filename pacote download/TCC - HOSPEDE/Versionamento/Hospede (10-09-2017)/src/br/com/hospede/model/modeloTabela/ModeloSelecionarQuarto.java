/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOQuarto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fabricio
 */
public class ModeloSelecionarQuarto extends AbstractTableModel{
    
    private ArrayList<DTOQuarto> dados;
    private String[]           colunas = {"Situação", "Quarto", "Categoria", "Cliente", "Diária", "Entrada", "Saída"};
    public static ModeloSelecionarQuarto instance = null;
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloSelecionarQuarto() {
        dados = new ArrayList<DTOQuarto>();
    }

    //Recebendo as listas de quarto, cliente e reserva.
    public void adicionarLista(ArrayList<DTOQuarto> lista) {
        dados = lista;
        fireTableDataChanged();
    }
    
    
    public DTOQuarto getQuarto(int rowIndex) {     
        try{
        return dados.get(rowIndex);
        }catch(Exception erro){
                return null;
        }
    }
    
    public void adicionar(DTOQuarto quarto){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<DTOQuarto>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
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
  
    public static ModeloSelecionarQuarto getInstance() {
        if (instance == null) {
            instance = new ModeloSelecionarQuarto();
        }
        return instance;
    }
}
