
import Modelos.*;
import java.util.Date;
import javafx.scene.paint.Color;

public class Programa {

    public static void main(String[] args) {
        new Telas.frmModeloConsultaPequeno().setVisible(true);

//      Cargo cargo = new Cargo();
//      cargo.setDescricao("FINNAZII");
//      cargo.inserir();
//      Cargo cargo = new Cargo();
//      cargo.carregarPorId(1);
//      cargo.remover();
//      Cargo cargo = new Cargo();
//      cargo.carregarPorId(11);
//      cargo.setDescricao("ALFA");
//      cargo.setAtivo(true);
//      cargo.atualizar();
//        Endereco end = new Endereco();
//        end.setCep("1246000");
//        end.setCidade("CAMPOS DO JORDÃO");
//        end.setBairro("VILA BRITANIA");
//        end.setLogradouro("RUA ANTORNIO FURTADO DE SOUZA");
//        end.setNumero("565");
//        end.setComplemento("COMPLEMENTO");
//        end.setUf("SP");
//        end.setAtivo(true);
//        end.inserir();
        Consulta c = new Consulta();
        c.setId(3);

        Date dt = Validacoes.Funcoes.getData("30/10/2017");
        FormaDePagamento f = new FormaDePagamento();
        f.carregarPorId(1);

        Pagamento pag = new Pagamento();
        pag.setConsulta(c);
        pag.setData(dt);
        pag.setValor(100.00);
        pag.setQtdParcela((byte) 1);
        pag.setAtivo(true);
        pag.setFormaDePagamento(f);

        // pag.inserir();
        pag.carregarPorId(3);
        pag.setValor(666.00);
        
        for (Pagamento p : Pagamento.carregarPorIdConsulta(3)) {
            Validacoes.Mensagens.mostrarAviso(p.getId() + "\n" + p.getData() + "\n" + p.getValor());
        }

    }
}
