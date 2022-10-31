package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOReceitaDiaria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloReceitaDiaria extends AbstractTableModel{
    
    private ArrayList<DTOReceitaDiaria>  dados = new ArrayList<DTOReceitaDiaria>();
    private String[]            colunas = {"Hospedagem","Quarto","Cliente","Entrada","Saída","Data Fechamento", "Status","Saldo","Consumo"};
    private SimpleDateFormat   formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static ModeloReceitaDiaria instance;

    public ModeloReceitaDiaria() {
        
    }
    
    public void adicionar(DTOReceitaDiaria cliente){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(cliente);
        fireTableDataChanged();
        
    }

    public void adicionarLista(ArrayList<DTOReceitaDiaria> clienteLista) {
              dados = clienteLista;
              fireTableDataChanged();
    }
    
    public DTOReceitaDiaria getReserva(int rowIndex) {     
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

    public void setDados(ArrayList<DTOReceitaDiaria> dados){
        this.dados=dados;
    }
    
    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getId_reserva();
            case 1:
                return dados.get(linha).getNumeroQuarto();
            case 2:
                return dados.get(linha).getCliente();
            case 3:
                return formatarData.format(dados.get(linha).getEntrada());
            case 4:
                return formatarData.format(dados.get(linha).getSaida());
            case 5:
                try{
                return formatarData.format(dados.get(linha).getDt_fechamento());
                }catch(Exception erro){
                 
                }
            case 6:
                return dados.get(linha).getStatus();
            case 7:
                return dados.get(linha).getSaldo();
            case 8:
                return dados.get(linha).getConsumo();
            default:
                return null;
        }
    }

    public String getColumnName(int index) {
        return colunas[index];
    }


    public static ModeloReceitaDiaria getInstance() {
        if (instance == null) {
            return instance = new ModeloReceitaDiaria();
        }
        return instance;
    }
}
