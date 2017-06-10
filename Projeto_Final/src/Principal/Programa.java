
import Constantes.StatusConsulta;
import Modelos.*;
import java.sql.Time;

public class Programa {

    public static void main(String[] args) {
        Consulta c = new Consulta();
        Cliente cli = new Cliente();
        Funcionario medico = new Funcionario();
        medico.carregarPorId(4);
        cli.carregarPorId(7);

        c.setAtivo(true);
        c.setCliente(cli);
        c.setMedico(medico);
        c.setData(Validacoes.Funcoes.getData("10/10/2017"));
        c.setHorario(new Time(9, 10, 1));
        c.setValor(100);
        c.setStatus(StatusConsulta.Atrazada);

        c.inserir();
    }
}
