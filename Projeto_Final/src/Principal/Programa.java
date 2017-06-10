
import Modelos.*;

public class Programa {

    public static void main(String[] args) {
        Endereco end = new Endereco();
        end.setAtivo(true);
        end.setBairro("VB");
        end.setLogradouro("RUA");
        end.setCep("124600");
        end.setCidade("CJ");
        end.setComplemento("1");
        end.setNumero("123");
        end.setUf("SP");
        end.inserir();
        
        Cliente c = new Cliente();
//        c.setNome("WESLEY");
//        c.setGenero("M");
//        c.setCelular("997791652");
//        c.setTelefone("36624625");
//        c.setEndereco(end);
//        c.setDataDeNascimento(Validacoes.Funcoes.getData("10/10/2010"));
//        c.setEmail("WESLEY_COSTA@OUTLOOK.COM");
//        c.setAtivo(true);
//
//        c.inserir();

        c.carregarPorId(7);
        c.setCpf("123");
        c.atualizar();
        c.setEndereco(end);

        Validacoes.Mensagens.mostrarAviso(c.getNome() + "\n"
                + c.getTelefone() + "\n"
                + c.getEndereco().getLogradouro() + "\n"
                + c.getEndereco().getId());
    }
}
