
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Funcoes {

    public static Date getData(String dataStr) {
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            data = sdf.parse(dataStr);
        } catch (ParseException ex) {
            Validacoes.Excecoes.mostrarExcecoes(ex);
        }

        return data;
    }

    public static String getData(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
}
