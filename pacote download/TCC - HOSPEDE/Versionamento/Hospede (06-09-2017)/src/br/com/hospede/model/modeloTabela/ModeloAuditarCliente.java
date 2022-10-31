package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.AuditarCliente;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarCliente extends AbstractTableModel{
    
    private ArrayList<AuditarCliente>  dados;
    private String[]                   colunas = {"ID_Usuário", "Usuário", "Ação", "Cliente", "Data e Hora"};
    public static ModeloAuditarCliente instance = null;
    private SimpleDateFormat           formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModeloAuditarCliente(){
        dados = new ArrayList<>();
    }
    
    public void adicionarLista(ArrayList<AuditarCliente> lista){
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
                    return dados.get(linha).getNome_cliente();
                    
                case 4:
                          //Froamando data para dia/mês/ano
                    return formatarData.format(dados.get(linha).getDt_acao());
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }
    
    
    public static ModeloAuditarCliente getInstance(){
        if(instance == null){
            instance = new ModeloAuditarCliente();
        }
        return instance;
    }
}
