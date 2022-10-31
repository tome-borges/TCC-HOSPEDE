package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOHistoricoReserva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloAuditarReserva extends AbstractTableModel{
    private ArrayList<DTOHistoricoReserva>   dados;
    private String[]                   colunas = {"ID_Usuário", "Usuário", "Ação", "ID Reserva", "Cliente reservado", "Data e Hora"};
    public static ModeloAuditarReserva instance = null;
    private SimpleDateFormat           formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public ModeloAuditarReserva(){
        dados = new ArrayList<>();
    }
    
    public void adicionarLista(ArrayList<DTOHistoricoReserva> lista){
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
                    return dados.get(linha).getId_usuario_responsavel();

                case 1:
                    return dados.get (linha).getLogin();
                    
                case 2:
                    return dados.get (linha).getAcao();
                    
                case 3:
                    return dados.get(linha).getId_reserva();
                    
                case 4:
                    return dados.get(linha).getCliente_reservado();
                    
                case 5:
                           //Transformando a data em dia/mês/ano.
                    return formatarData.format(dados.get(linha).getDt_acao());
                default:
                    return null;
            }
        } catch (Exception erro) {
               return null;
        }
    }
    
    
    public static ModeloAuditarReserva getInstance(){
        if(instance == null){
            instance = new ModeloAuditarReserva();
        }
        return instance;
    }
}
