package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Consulta implements ICadastro {

    private int id;
    private Cliente cliente;
    private Funcionario medico;
    private Date data;
    private Time horario;
    private double valor;
    private Constantes.StatusConsulta status;
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

    public Constantes.StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(Constantes.StatusConsulta status) {
        this.status = status;
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
        try {
            String query
                    = "INSERT INTO consultas                                           "
                    + "	  (id_cliente, id_medico, data, horario, valor, status, ativo) "
                    + "OUTPUT inserted.id_consulta                                     "
                    + "VALUES                                                          "
                    + "	  (?, ?, ?, ?, ?, ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.cliente.getId());
            Banco.cmd.setInt(2, this.medico.getId());
            Banco.cmd.setDate(3, Validacoes.Funcoes.converterData(this.data));
            Banco.cmd.setTime(4, this.horario);
            Banco.cmd.setDouble(5, this.valor);
            Banco.cmd.setString(6, Constantes.StatusConsulta.getStatus(this.status));
            Banco.cmd.setInt(7, this.ativo ? 1 : 0);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = Banco.leitor.getInt(1);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }

    }

    @Override
    public void atualizar() {
        try {
            String query
                    = "UPDATE consultas SET    "
                    + "	  id_cliente     = ?,  "
                    + "	  id_medico      = ?,  "
                    + "	  data           = ?,  "
                    + "	  horario        = ?,  "
                    + "	  valor		 = ?,  "
                    + "	  status	 = ?,  "
                    + "	  ativo		 = ?   "
                    + "WHERE                   "
                    + "	   id_consulta = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.cliente.getId());
            Banco.cmd.setInt(2, this.medico.getId());
            Banco.cmd.setDate(3, Validacoes.Funcoes.converterData(this.data));
            Banco.cmd.setTime(4, this.horario);
            Banco.cmd.setDouble(5, this.valor);
            Banco.cmd.setString(6, Constantes.StatusConsulta.getStatus(this.status));
            Banco.cmd.setInt(7, this.ativo ? 1 : 0);
            Banco.cmd.setInt(8, this.id);

            Banco.cmd.executeUpdate();
            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }

    @Override
    public void remover() {
        this.ativo = false;
        atualizar();
    }

    @Override
    public void carregarPorId(int id) {
        try {
            String query
                    = "SELECT                                                        "
                    + "     diagnosticos.id_categoria,                               "
                    + "     diagnosticos.esferico,                                   "
                    + "     diagnosticos.cilindro,                                   "
                    + "     diagnosticos.adicao,                                     "
                    + "     diagnosticos.eixo,                                       "
                    + "     diagnosticos.ativo,                                      "
                    + "     categorias.id_categoria,                                 "
                    + "     categorias.descricao            AS 'categoria',      "
                    + "     categorias.ativo                AS 'ativo_categoria'     "
                    + "FROM                                                          "
                    + "     diagnosticos                                             "
                    + "INNER JOIN categorias                                         "
                    + "     ON categorias.id_categoria = diagnosticos.id_diagnostico "
                    + "WHERE "
                    + "     id_diagnostico = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {

            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void carregar() {
        carregarPorId(this.id);
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
