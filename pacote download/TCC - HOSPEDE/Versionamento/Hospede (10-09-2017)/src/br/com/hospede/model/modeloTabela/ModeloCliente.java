package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOCliente;
import br.com.hospede.model.DTO.DTOQuarto;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloCliente extends AbstractTableModel{

    private ArrayList<DTOCliente>  dados;
    private String[]            colunas = {"Nome", "CPF", "RG", "Estado", "Cidade", "Endereço", "Bairro", "CEP",
                                           "Telefone", "Celular", "Email","Editar","Deletar"};
    public static ModeloCliente instance;

    public ModeloCliente() {
        dados = new ArrayList<>();
    }
    
    public void adicionar(DTOCliente cliente){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(cliente);
        fireTableDataChanged();
        
    }

    public void adicionarLista(ArrayList<DTOCliente> clienteLista) {
        //Recebe a lista de cliente do método "listarCliente()" da classe"clienteDAO".
        dados = clienteLista;
        fireTableDataChanged();
    }
    
    public DTOCliente getCliente(int rowIndex) {     
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
                return dados.get(linha).getNome();
            case 1:
                return dados.get(linha).getCpf();
            case 2:
                return dados.get(linha).getRg();
            case 3:
                return dados.get(linha).getEstado();
            case 4:
                return dados.get(linha).getCidade();
            case 5:
                return dados.get(linha).getEndereco();
            case 6:
                return dados.get(linha).getBairro();
            case 7:
                return dados.get(linha).getCep();
            case 8:
                return dados.get(linha).getTelefone();
            case 9:
                return dados.get(linha).getCelular();
            case 10:
                return dados.get(linha).getEmail();
            case 11:
                return dados.get(linha).getEditar();
            case 12:
                return dados.get(linha).getDeletar();
            default:
                return null;
        }
    }

    public String getColumnName(int index) {
        return colunas[index];
    }


    public static ModeloCliente getInstance() {
        if (instance == null) {
            return instance = new ModeloCliente();
        }
        return instance;
    }
}
