package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Pagamento implements ICadastro {

    private int id;
    private Consulta consulta;
    private FormaDePagamento formaDePagamento;
    private Date data;
    private double valor;
    private byte qtdParcela;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Pagamento() {
        this.ativo = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public byte getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(byte qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER, CARREGAR POR ID E CARREGAR">  
    
    @Override
    public void inserir() {
        try {
            String query
                    = "INSERT INTO pagamentos                                                "
                    + "	  (id_forma_pagamento, id_consulta, data, valor, qtd_parcela, ativo) "
                    + "OUTPUT inserted.id_pagamento                                          "
                    + "VALUES                                                                "
                    + "	  (?, ?, ?, ?, ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.formaDePagamento.getId());
            Banco.cmd.setInt(2, this.consulta.getId());
            Banco.cmd.setDate(3, Validacoes.Funcoes.converterData(this.data));
            Banco.cmd.setDouble(4, this.valor);
            Banco.cmd.setByte(5, this.qtdParcela);
            Banco.cmd.setInt(6, this.ativo ? 1 : 0);
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
                    = "UPDATE pagamentos SET         "
                    + "     id_forma_pagamento	= ?, "
                    + "     id_consulta		= ?, "
                    + "     data		= ?, "
                    + "     valor		= ?, "
                    + "     qtd_parcela		= ?, "
                    + "     ativo		= ?  "
                    + "WHERE                         "
                    + "     id_pagamento = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.formaDePagamento.getId());
            Banco.cmd.setInt(2, this.consulta.getId());
            Banco.cmd.setDate(3, Validacoes.Funcoes.converterData(this.data));
            Banco.cmd.setDouble(4, this.valor);
            Banco.cmd.setByte(5, this.qtdParcela);
            Banco.cmd.setInt(6, this.ativo ? 1 : 0);
            Banco.cmd.setInt(7, this.id);

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
    public void carregar() {
        carregarPorId(this.id);
    }

    @Override
    public void carregarPorId(int id) {

        try {
            String query
                    = "SELECT                   "
                    + "     id_forma_pagamento, "
                    + "     id_consulta,        "
                    + "     data,               "
                    + "     valor,              "
                    + "     qtd_parcela,        "
                    + "     ativo               "
                    + "FROM                     "
                    + "     pagamentos          "
                    + "WHERE                    "
                    + "     id_pagamento = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.consulta = new Consulta();
                this.formaDePagamento = new FormaDePagamento();

                this.formaDePagamento.setId(Banco.leitor.getInt("id_forma_pagamento"));
                this.consulta.setId(Banco.leitor.getInt("id_consulta"));
                this.data = Banco.leitor.getDate("data");
                this.qtdParcela = Banco.leitor.getByte("qtd_parcela");
                this.ativo = Banco.leitor.getInt("ativo") == 1;
            }

            Banco.cmd.close();
            consulta.carregar();
            formaDePagamento.carregar();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }

    public static ArrayList<Pagamento> carregarPorIdConsulta(int idConsulta) {
        ArrayList<Pagamento> lista = new ArrayList<>();

        try {
            String query
                    = "SELECT                   "
                    + "     id_pagamento,       "
                    + "     id_forma_pagamento, "
                    + "     id_consulta,        "
                    + "     data,               "
                    + "     valor,              "
                    + "     qtd_parcela,        "
                    + "     ativo               "
                    + "FROM                     "
                    + "     pagamentos          "
                    + "WHERE                    "
                    + "     id_consulta = ?     "
                    + "     AND                 "
                    + "     ativo       = 1";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, idConsulta);
            Banco.leitor = Banco.cmd.executeQuery();

            while (Banco.leitor.next()) {
                Pagamento pag = new Pagamento();
                FormaDePagamento f = new FormaDePagamento();
                Consulta c = new Consulta();

                pag.setId(Banco.leitor.getInt("id_pagamento"));
                f.setId(Banco.leitor.getInt("id_forma_pagamento"));
                c.setId(Banco.leitor.getInt("id_consulta"));
                pag.setData(Banco.leitor.getDate("data"));
                pag.setValor(Banco.leitor.getDouble("valor"));
                pag.setQtdParcela(Banco.leitor.getByte("qtd_parcela"));
                pag.setAtivo(true);

                lista.add(pag);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }

        return lista;
    }
    // </editor-fold> 
}
