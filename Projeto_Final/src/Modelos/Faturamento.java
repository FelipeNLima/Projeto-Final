/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/*
 ** Desenvolvido por..: via varejo
 ** Data..............: 19/06/2017  
*/
public class Faturamento {
    
    private String Descricao;
    private Double Valor;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }
    
    
    public static ArrayList<Faturamento> carregarFaturamento(Date inicial, Date Final)
    {
        ArrayList<Faturamento> lista = new ArrayList<>();

        try {
            String query
                    = "SELECT                                                                "
                    + "     forma_de_pagamento.descricao,                                    "
                    + "     SUM(valor)          AS 'valor'                                   "
                    + "FROM                                                                  "
                    + "    pagamentos                                                        "
                    + "INNER JOIN forma_de_pagamento ON                                      "
		    + "pagamentos.id_forma_pagamento = forma_de_pagamento.id_forma_pagamento "
                    + "WHERE data BETWEEN ? AND  ?                                           "
                    + "GROUP BY forma_de_pagamento.descricao                                 ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setDate(1, Validacoes.Funcoes.converterData(inicial));
            Banco.cmd.setDate(2, Validacoes.Funcoes.converterData(Final));
            Banco.leitor = Banco.cmd.executeQuery();

            while (Banco.leitor.next()) {
                Faturamento forma = new Faturamento();
                forma.setDescricao(Banco.leitor.getString("descricao"));
                forma.setValor(Banco.leitor.getDouble("valor"));

                lista.add(forma);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }

        return lista;
    }

}
