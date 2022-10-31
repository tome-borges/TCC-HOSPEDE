package br.com.hospede.model.dto;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class DTOReserva {
    
    private int    id_reserva, id_cliente, id_quarto, dias_hospedados = 0;
    private String data,tipo, nome_cliente, estado_cliente, cidade_cliente,
                   telefone_cliente, celular_cliente, email_cliente, numero_quarto, status;
    private Double totalPagar, saldo;
    private Date   entrada, saida;
    private ImageIcon editar, deletar;
    private ImageIcon        imgSituacao;
    private SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    private Date             dataAtual = new Date();
    

    public DTOReserva() {
        
                    URL resourceEditar = getClass().getResource("/br/com/hospede/view/imagens/Editar.png");
                    editar =new ImageIcon(resourceEditar);
                    
                    
                    URL resourceDeletar = getClass().getResource("/br/com/hospede/view/imagens/Deletar.png");
                    deletar = new ImageIcon(resourceDeletar);
    }
    
    

    public ImageIcon getEditar() {
        return editar;
    }

    public void setEditar(ImageIcon editar) {
        this.editar = editar;
    }

    public ImageIcon getDeletar() {
        return deletar;
    }

    public void setDeletar(ImageIcon deletar) {
        this.deletar = deletar;
    }
    
    
    public Date getEntrada() {
        
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
    

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(String estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public String getCidade_cliente() {
        return cidade_cliente;
    }

    public void setCidade_cliente(String cidade_cliente) {
        this.cidade_cliente = cidade_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        this.telefone_cliente = telefone_cliente;
    }

    public String getCelular_cliente() {
        return celular_cliente;
    }

    public void setCelular_cliente(String celular_cliente) {
        this.celular_cliente = celular_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getNumero_quarto() {
        return numero_quarto;
    }

    public void setNumero_quarto(String numero_quarto) {
        this.numero_quarto = numero_quarto;
    }

    public int getDias_hospedados() {
        return dias_hospedados;
    }

    public void setDias_hospedados(int dias_hospedados) {
        this.dias_hospedados = dias_hospedados;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ImageIcon getImgSituacao() {
        return imgSituacao;
    }

    public void setImgSituacao(ImageIcon imgSituacao) {
        this.imgSituacao = imgSituacao;
    }

    public SimpleDateFormat getFormatarData() {
        return formatarData;
    }

    public void setFormatarData(SimpleDateFormat formatarData) {
        this.formatarData = formatarData;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public void totalPagarFechar(DTOQuarto quarto){
        //Quantidade de dias hospedados.
        DateTime entrada = new DateTime(getEntrada());
        DateTime saida = new DateTime(getSaida());
        DateTime diaCorrente = new DateTime(dataAtual);
        
        if(dataAtual.getTime()<getSaida().getTime()){
            dias_hospedados = Days.daysBetween(entrada, diaCorrente).getDays();
        } else {
        dias_hospedados = Days.daysBetween(entrada, saida).getDays();
        }
        
        //Multiplica os dias hospedados com o preço da diário do quarto, passado por parâmetro.
         try{
        totalPagar = Double.parseDouble(quarto.getDiaria()) * dias_hospedados;
         }catch(Exception erro){
             
         }
    }
    
    public void totalPagar(DTOQuarto quarto){
        //Quantidade de dias hospedados.
        DateTime entrada = new DateTime(getEntrada());
        DateTime saida = new DateTime(getSaida());
        dias_hospedados = Days.daysBetween(entrada, saida).getDays();
        
        //Multiplica os dias hospedados com o preço da diário do quarto, passado por parâmetro.
         try{
        totalPagar = Double.parseDouble(quarto.getDiaria()) * dias_hospedados;
         }catch(Exception erro){
             
         }
    }
}
