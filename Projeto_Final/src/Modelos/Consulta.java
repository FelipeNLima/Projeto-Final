package Modelos;

import java.sql.Time;
import java.util.Date;

public class Consulta implements CadastroInterface {

    private int id;
    private Date data;
    private Time horario;
    private Double valor;
    private String status;
    private boolean ativo;

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

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    //</editor-fold>
    @Override
    public void inserir() {
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void remover() {
    }

    @Override
    public void carregarPorId(int id) {
    }

    public static void trocar(Consulta consultaA, Consulta consultaB) {
        Consulta consultaTemp = consultaA;

        consultaA.setData(consultaB.getData());
        consultaA.setHorario(consultaB.getHorario());

        consultaB.setData(consultaTemp.getData());
        consultaB.setHorario(consultaTemp.getHorario());

        consultaA.atualizar();
        consultaB.atualizar();
    }
}
