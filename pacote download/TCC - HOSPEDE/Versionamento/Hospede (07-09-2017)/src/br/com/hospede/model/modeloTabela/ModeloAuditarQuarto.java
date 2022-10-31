package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.AuditarQuarto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarQuarto extends AbstractTableModel {

    private ArrayList<AuditarQuarto>  dados;
    private String[]                  colunas = {"ID_Usuário", "Usuário", "Ação", "Quarto", "Data e Hora"};
    public static ModeloAuditarQuarto instance = null;
    private SimpleDateFormat          formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ModeloAuditarQuarto() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<AuditarQuarto> lista) {
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
                    return dados.get(linha).getId_usuario();

                case 1:
                    return dados.get (linha).getLogin();
                    
                case 2:
                    return dados.get (linha).getAcao();
                    
                case 3:
                    return dados.get(linha).getNumero();
                    
                case 4:
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
