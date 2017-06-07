package Modelos;

public class Receita {

    private int id;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Receita() {
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold>
}
