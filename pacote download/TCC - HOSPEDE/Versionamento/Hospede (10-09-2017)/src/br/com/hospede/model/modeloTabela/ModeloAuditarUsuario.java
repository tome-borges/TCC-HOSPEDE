package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOHistoricoUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarUsuario extends AbstractTableModel{
    private ArrayList<DTOHistoricoUsuario>  dados;
    private String[]                   colunas = {"ID_Usuário", "Usuário", "Ação", "Usuário editado", "Data e Hora"};
    public static ModeloAuditarUsuario instance = null;
    private SimpleDateFormat           formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public ModeloAuditarUsuario() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<DTOHistoricoUsuario> lista) {
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
                    return dados.get(linha).getId_usuario_reponsavel();

                case 1:
                    return dados.get (linha).getLogin_usuario_responsavel();
                    
                case 2:
                    return dados.get (linha).getAcao();
                    
                case 3:
                    return dados.get(linha).getLogin_usuario_alterado();
                    
                case 4:
                           //Transformando a data em dia/mês/ano.
                    return formatarData.format(dados.get(linha).getDt_acao());
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }

    public static ModeloAuditarUsuario getInstance() {
        if (instance == null) {
            instance = new ModeloAuditarUsuario();
        }
        return instance;
    }
}
