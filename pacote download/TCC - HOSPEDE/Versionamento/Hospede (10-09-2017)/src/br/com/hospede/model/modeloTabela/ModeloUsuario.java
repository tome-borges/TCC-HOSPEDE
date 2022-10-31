package br.com.hospede.model.modeloTabela;

import br.com.hospede.model.DTO.DTOUsuario;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloUsuario extends AbstractTableModel{
    private static ArrayList<DTOUsuario> dados;
    private String[]                  colunas  = {"Nome", "Email", "Senha", "Telefone", "Função", "Editar", "Deletar"};
    public static ModeloUsuario       instance = null;
    
    
    public ModeloUsuario(){
        dados = new ArrayList<DTOUsuario>();
    }
    
    public void adicionar(DTOUsuario usuario){
        dados = null; //Para uma pesquisa, limpa-se a tabela anulando o array.
        dados = new ArrayList<DTOUsuario>();//Depois instancia do zero um novo para adicionar apenas o usuário encontrado.
        dados.add(usuario);
        fireTableDataChanged();
    }
    
    public void adicionarLista(ArrayList<DTOUsuario> listaUsuarios) {
        //Recebe a lista de usuarios do método "listarUsuarios()" da classe"usuarioDAO".
        dados = listaUsuarios;
        fireTableDataChanged();
    }
    
    public DTOUsuario getUsuario(int rowIndex) {     
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

    public void setDados(ArrayList<DTOUsuario> dados){
        this.dados=dados;
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
                return dados.get(linha).getLogin();
            case 1:
                return dados.get(linha).getEmail();
            case 2:
                return dados.get(linha).getSenha();
            case 3:
                return dados.get(linha).getTelefone();
            case 4:
                return dados.get(linha).getFuncao();
            case 5:
                return dados.get(linha).getEditar();
            case 6:
                return dados.get(linha).getDeletar();
            default:
                return null;
        }
    }
    
 
    @Override
     public String getColumnName(int columnIndexName){
        return colunas[columnIndexName];
    }
     
     public static ModeloUsuario getInstance(){
         if(instance == null){
             instance = new ModeloUsuario();
         }
         return instance;
     }
}
