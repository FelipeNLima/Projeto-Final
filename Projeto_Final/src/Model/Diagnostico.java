package Model;

/*
 ** Desenvolvido por..: Felipe
 ** Data..............: 01/06/2017  
*/
public class Diagnostico {
    
    private int id;
    private Double esferico;
    private Double cilindrico;
    private Double adicao;
    private Double eixo;
    private Boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getEsferico() {
        return esferico;
    }

    public void setEsferico(Double esferico) {
        this.esferico = esferico;
    }

    public Double getCilindrico() {
        return cilindrico;
    }

    public void setCilindrico(Double cilindrico) {
        this.cilindrico = cilindrico;
    }

    public Double getAdicao() {
        return adicao;
    }

    public void setAdicao(Double adicao) {
        this.adicao = adicao;
    }

    public Double getEixo() {
        return eixo;
    }

    public void setEixo(Double eixo) {
        this.eixo = eixo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
