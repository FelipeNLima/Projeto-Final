
import Modelos.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    public static void main(String[] args) {
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
//      new Telas.frmPrincipal().setVisible(true);
        
        Date data = Funcoes.getData("25/12/2017");
        System.out.println(Funcoes.getData(data));

    }
}
