package Modelos;

import java.util.Date;

public class Pagamento {

    private int id;
    private Date data;
    private double valor;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Pagamento() {
        this.ativo = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
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

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold>
}
