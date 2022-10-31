package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOHistoricoQuarto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarQuarto extends AbstractTableModel {

    private ArrayList<DTOHistoricoQuarto>  dados;
    private String[]                  colunas = {"Usuário", "Ação", "Quarto", "Data e Hora"};
    public static ModeloAuditarQuarto instance = null;
    private SimpleDateFormat          formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ModeloAuditarQuarto() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<DTOHistoricoQuarto> lista) {
        dados = lista;
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

    @Override
    public Object getValueAt(int linha, int coluna) {

        try {
            switch (coluna) {
                case 0:
                    return dados.get (linha).getLogin();
                    
                case 1:
                    return dados.get (linha).getAcao();
                    
                case 2:
                    return dados.get(linha).getNumero_quarto();
                    
                case 3:
                           //Formatando data para dia/mês/ano.
                    return formatarData.format(dados.get(linha).getDt_acao());
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }

    public static ModeloAuditarQuarto getInstance() {
        if (instance == null) {
            instance = new ModeloAuditarQuarto();
        }
        return instance;
    }

}
