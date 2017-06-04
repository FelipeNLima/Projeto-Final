package Model;

import java.util.Date;

/*
 ** Desenvolvido por..: Felipe
 ** Data..............: 01/06/2017  
 */
public class Pagamento {

    private int id;
    private Date data;
    private double valor;
    private Boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
