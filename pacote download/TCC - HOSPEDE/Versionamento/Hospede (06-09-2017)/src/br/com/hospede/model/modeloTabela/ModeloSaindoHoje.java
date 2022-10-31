package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.EntrandoOuSaindo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloSaindoHoje extends AbstractTableModel{
     private ArrayList<EntrandoOuSaindo> dados;
    private String[]                     colunas  = {"Situação", "Nome", "Quarto", "Entrada", "Saída", "Celular"};
    public static ModeloSaindoHoje       instance = null;
    private SimpleDateFormat             formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloSaindoHoje() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<EntrandoOuSaindo> entrandoOuSaindo) {
        dados = entrandoOuSaindo;
        fireTableDataChanged();

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
                return dados.get(linha).getImgSituacao();
            case 1:
                return dados.get(linha).getNomeDoCliente();
            case 2:
                return dados.get(linha).getNumeroDoQuarto();
            case 3:
                       //Formatando data.
                return formatarData.format(dados.get(linha).getEntrada());
            case 4:
                       //Formatando data.
                return formatarData.format(dados.get(linha).getSaida());
            case 5:
                return dados.get(linha).getCelular();
            default:
                return null;
        }

    }

    public String getColumnName(int indexColumn) {
        return colunas[indexColumn];
    }


    public static ModeloSaindoHoje getInstance() {
        if (instance == null) {
            instance = new ModeloSaindoHoje();
        }
        return instance;
    }
}
