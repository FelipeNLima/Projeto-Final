package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;

public class FormaDePagamento implements ICadastro {

    private int id;
    private String descricao;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CRT">  
    public FormaDePagamento() {
        this.ativo = true;
    }

    public FormaDePagamento(String descricao) {
        this.descricao = descricao;
        ativo = true;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER E CARREGAR POR ID">  
    @Override
    public void inserir() {
        try {
            String query
                    = "INSERT INTO forma_de_pagamento        "
                    + "	(descricao, ativo)                   "
                    + "OUTPUT inserted.id_forma_de_pagamento "
                    + "VALUES                                "
                    + "	(? , ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setString(1, this.descricao);
            Banco.cmd.setInt(2, this.ativo ? 1 : 0);
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
                    = "UPDATE forma_de_pagamento SET "
                    + "	descricao  = ?,              "
                    + "	ativo	   = ?               "
                    + "WHERE                         "
                    + "	id_forma_de_pagamento = ?    ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setString(1, this.descricao);
            Banco.cmd.setInt(2, this.ativo ? 1 : 0);
            Banco.cmd.setInt(3, this.id);

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
                    = "SELECT                    "
                    + "	descricao,               "
                    + "	ativo                    "
                    + "FROM                      "
                    + "	forma_de_pagamento       "
                    + "WHERE                     "
                    + "	id_forma_pagamento = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.descricao = Banco.leitor.getString("descricao");
                this.ativo = Banco.leitor.getInt("ativo") == 1;
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }
    // </editor-fold> 
}
