package Validacoes;

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

    public static java.sql.Date converterData(java.util.Date dtUtil) {
        java.sql.Date dtSql = new java.sql.Date(dtUtil.getTime());
        dtSql.setDate(dtUtil.getDate());
        dtSql.setMonth(dtUtil.getMonth());
        dtSql.setYear(dtUtil.getYear());

        return dtSql;
    }
}
