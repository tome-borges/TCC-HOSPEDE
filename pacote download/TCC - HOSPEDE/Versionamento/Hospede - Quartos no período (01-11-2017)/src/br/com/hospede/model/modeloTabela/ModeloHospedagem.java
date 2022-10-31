package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.dto.DTOReserva;
import br.com.hospede.model.DAO.QuartoDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloHospedagem extends AbstractTableModel{
    private ArrayList<DTOReserva> dados;
    private String[]           colunas      = {"Nome", "Estado", "Telefone", "Celular", "Email",
                                               "Entrada", "Saída", "Diária(s)",
                                               "Quarto","Editar"};
    public static ModeloHospedagem instance = null;
    private SimpleDateFormat       formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private QuartoDAO              quartoDAO;

    public void adicionar(DTOReserva reserva){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(reserva);
        fireTableDataChanged();
    }
    
    public DTOReserva getHospedagem(int rowIndex) {     
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
    
    public ModeloHospedagem() {
        dados = new ArrayList<>();
    }

    public void adicionarLista(ArrayList<DTOReserva> listaReserva) {
        dados = listaReserva;
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
        try{
        switch (coluna) {

            case 0:
                return dados.get(linha).getNome_cliente();
            case 1:
                return dados.get(linha).getEstado_cliente();
            case 2:
                return dados.get(linha).getTelefone_cliente();
            case 3:
                return dados.get(linha).getCelular_cliente();
            case 4:
                return dados.get(linha).getEmail_cliente();
            case 5:
                       //Formatando data para dia/mês/ano.
                return formatarData.format(dados.get(linha).getEntrada());
            case 6:
                       //Formatando data para dia/mês/ano.
                return formatarData.format(dados.get(linha).getSaida());
            case 7:
             //Resgatando o quarto para enviar a diária ao mmétodo que calcula o dias hospedados e total a pagar.
                quartoDAO = QuartoDAO.getInstance();
                dados.get(linha).totalPagar(quartoDAO.consultarQuarto(Integer.parseInt(dados.get(linha).getNumero_quarto())));
                return dados.get(linha).getDias_hospedados();
            case 8:
                return dados.get(linha).getNumero_quarto();
            case 9:
                return dados.get(linha).getEditar();
            default:
                return null;
        }
        }catch(Exception erro){
                return null;
          }

    }

    public String getColumnName(int indexColumn) {
        return colunas[indexColumn];
    }

    public void limparDados() {
        dados = new ArrayList<>();
    }

    public static ModeloHospedagem getInstance() {
        if (instance == null) {
            instance = new ModeloHospedagem();
        }
        return instance;
    }
}
