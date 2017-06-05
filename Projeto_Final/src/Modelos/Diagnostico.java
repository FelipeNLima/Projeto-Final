package Modelos;

public class Diagnostico {

    private int id;
    private double esferico;
    private double cilindrico;
    private double adicao;
    private double eixo;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEsferico() {
        return esferico;
    }

    public void setEsferico(double esferico) {
        this.esferico = esferico;
    }

    public double getCilindrico() {
        return cilindrico;
    }

    public void setCilindrico(double cilindrico) {
        this.cilindrico = cilindrico;
    }

    public double getAdicao() {
        return adicao;
    }

    public void setAdicao(double adicao) {
        this.adicao = adicao;
    }

    public double getEixo() {
        return eixo;
    }

    public void setEixo(double eixo) {
        this.eixo = eixo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold>
}
