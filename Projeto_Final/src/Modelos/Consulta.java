package Modelos;

import java.sql.Time;
import java.util.Date;

public class Consulta implements ICadastro {

    private int id;
    private Cliente cliente;
    private Funcionario medico;
    private Date data;
    private Time horario;
    private double valor;
    private String status;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getMedico() {
        return medico;
    }

    public void setMedico(Funcionario medico) {
        this.medico = medico;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(Constantes.StatusConsulta status) {
        this.status = status.toString();
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
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
    public void carregar() {
        carregarPorId(this.id);
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
