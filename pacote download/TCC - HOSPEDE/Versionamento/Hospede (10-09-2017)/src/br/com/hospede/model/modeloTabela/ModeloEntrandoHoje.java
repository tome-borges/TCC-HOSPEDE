package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOReserva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloEntrandoHoje extends AbstractTableModel{
    private ArrayList<DTOReserva> dados;
    private String[]                    colunas  = {"Situação", "Nome", "Quarto", "Entrada", "Saída", "Celular"};
    public static ModeloEntrandoHoje    instance = null;
    private SimpleDateFormat            formatarData = new SimpleDateFormat("dd/MM/yyyy");

    public ModeloEntrandoHoje() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<DTOReserva> entrandoOuSaindo) {
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
                return dados.get(linha).getNome_cliente();
            case 2:
                return dados.get(linha).getNumero_quarto();
            case 3:
                       //Formatando data.
                return formatarData.format(dados.get(linha).getEntrada());
            case 4:
                       //Formatando data.
                return formatarData.format(dados.get(linha).getSaida());
            case 5:
                return dados.get(linha).getCelular_cliente();
            default:
                return null;
        }

    }

    public String getColumnName(int indexColumn) {
        return colunas[indexColumn];
    }


    public static ModeloEntrandoHoje getInstance() {
        if (instance == null) {
            instance = new ModeloEntrandoHoje();
        }
        return instance;
    }
}
