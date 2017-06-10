package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;

public class Receita implements ICadastro {

    private int id;
    private Categoria categoria;
    private double esferico;
    private double cilindro;
    private double adicao;
    private double eixo;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Receita() {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getEsferico() {
        return esferico;
    }

    public void setEsferico(double esferico) {
        this.esferico = esferico;
    }

    public double getCilindro() {
        return cilindro;
    }

    public void setCilindro(double cilindro) {
        this.cilindro = cilindro;
    }

    public double getAdicao() {
        return adicao;
    }

    public void setAdicao(double adicao) {
        this.adicao = adicao;
    }

    public double getEixo() {
        return eixo;
    }

    public void setEixo(double eixo) {
        this.eixo = eixo;
    }

    public boolean isAtivo() {
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
                    = "INSERT INTO diagnosticos                                        "
                    + "	  (id_categoria, esferico, cilindro, adicao, eixo, ativo)      "
                    + "VALUES                                                          "
                    + "   (@id_categoria, @esferico, @cilindro, @adicao, @eixo, @ativo)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, categoria.getId());
            Banco.cmd.setDouble(2, this.esferico);
            Banco.cmd.setDouble(3, this.cilindro);
            Banco.cmd.setDouble(4, this.adicao);
            Banco.cmd.setDouble(5, this.eixo);
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
                    = "UPDATE diagnosticos SET  "
                    + "	  id_categoria	   = ?, "
                    + "	  esferico	   = ?, "
                    + "	  cilindro	   = ?, "
                    + "	  adicao	   = ?, "
                    + "	  eixo		   = ?, "
                    + "	  ativo		   = ?  "
                    + "WHERE                    "
                    + "	  id_diagnostico  = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, categoria.getId());
            Banco.cmd.setDouble(2, this.esferico);
            Banco.cmd.setDouble(3, this.cilindro);
            Banco.cmd.setDouble(4, this.adicao);
            Banco.cmd.setDouble(5, this.eixo);
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
                    = "SELECT               "
                    + "	   id_categoria,    "
                    + "	   esferico,        "
                    + "	   cilindro,        "
                    + "	   adicao,          "
                    + "	   eixo,            "
                    + "	   ativo            "
                    + "FROM                 "
                    + "	  diagnosticos      "
                    + "WHERE                "
                    + "	  id_diagnostico = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.categoria = new Categoria();
                this.esferico = Banco.leitor.getDouble("esferico");
                this.cilindro = Banco.leitor.getDouble("cilindro");
                this.adicao = Banco.leitor.getDouble("adicao");
                this.eixo = Banco.leitor.getDouble("eixo");
                this.ativo = Banco.leitor.getByte("ativo") == 1;
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }

    // </editor-fold> 
}
