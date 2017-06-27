package Validacoes;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Funcoes {

    public static Date getData(String dataStr) {

        if (dataStr.isEmpty()) {
            return null;
        }

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

    public static Time getTime(String data) {
        String[] x = data.split(":");

        return new Time(Integer.parseInt(x[0]), Integer.parseInt(x[1]), 0);
    }

    public static java.sql.Date converterData(java.util.Date dtUtil) {
        if(dtUtil == null)
            return null;
        
        java.sql.Date dtSql = new java.sql.Date(dtUtil.getTime());
        dtSql.setDate(dtUtil.getDate());
        dtSql.setMonth(dtUtil.getMonth());
        dtSql.setYear(dtUtil.getYear());

        return dtSql;
    }

    public static DefaultTableModel limparLinhas(DefaultTableModel model) {
        Validacoes.Mensagens.mostrarAviso(model.getRowCount() + "");
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            model.setRowCount(0);
        }

        return model;
    }
}
