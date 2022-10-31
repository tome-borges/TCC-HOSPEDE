package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOHistoricoPasseio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarPasseio extends AbstractTableModel{
    
    private ArrayList<DTOHistoricoPasseio>  dados;
    private String[]                  colunas = {"Usuário", "Ação", "Passeio", "Data e Hora"};
    public static ModeloAuditarPasseio instance = null;
    private SimpleDateFormat          formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ModeloAuditarPasseio() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<DTOHistoricoPasseio> lista) {
        dados = lista;
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
                    return dados.get (linha).getUsuarioResponsavel();
                    
                case 1:
                    return dados.get (linha).getAcao();
                    
                case 2:
                    return dados.get(linha).getPasseio();
                    
                case 3:
                           //Formatando data para dia/mês/ano.
                    return formatarData.format(dados.get(linha).getData_registro());
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }

    public static ModeloAuditarPasseio getInstance() {
        if (instance == null) {
            instance = new ModeloAuditarPasseio();
        }
        return instance;
    }
}
