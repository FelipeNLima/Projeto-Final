package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;

public class Diagnostico implements ICadastro {

    private int id;
    private Categoria categoria;
    private float esferico;
    private float cilindro;
    private float adicao;
    private float eixo;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public double getEsferico() {
        return esferico;
    }

    public void setEsferico(float esferico) {
        this.esferico = esferico;
    }

    public float getCilindro() {
        return cilindro;
    }

    public void setCilindro(float cilindro) {
        this.cilindro = cilindro;
    }

    public float getAdicao() {
        return adicao;
    }

    public void setAdicao(float adicao) {
        this.adicao = adicao;
    }

    public float getEixo() {
        return eixo;
    }

    public void setEixo(float eixo) {
        this.eixo = eixo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER E CARREGAR POR ID">  
    @Override
    public void inserir() {
        try {
            String query = "INSERT INTO diagnosticos                                "
                    + "	(id_categoria, esferico, cilindro, adicao, eixo, ativo)     "
                    + "OUTPUT inserted.id_diagnostico                               "
                    + "VALUES                                                       "
                    + "	(?, ?, ?, ?, ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.categoria.getId());
            Banco.cmd.setFloat(2, this.esferico);
            Banco.cmd.setFloat(3, this.cilindro);
            Banco.cmd.setFloat(4, this.adicao);
            Banco.cmd.setFloat(5, this.eixo);
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
            String query = "UPDATE diagnosticos SET   "
                    + "	id_categoria     = ?, "
                    + "	esferico	 = ?, "
                    + "	cilindro	 = ?, "
                    + "	adicao		 = ?, "
                    + "	eixo		 = ?, "
                    + "	ativo		 = ?  "
                    + "WHERE                     "
                    + "	id_diagnostico = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.categoria.getId());
            Banco.cmd.setFloat(2, this.esferico);
            Banco.cmd.setFloat(3, this.cilindro);
            Banco.cmd.setFloat(4, this.adicao);
            Banco.cmd.setFloat(5, this.eixo);
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
    public void carregarPorId(int id) {

        try {
            String query = "SELECT                  "
                    + "	diagnosticos.id_categoria,  "
                    + "	diagnosticos.esferico,      "
                    + "	diagnosticos.cilindro,      "
                    + "	diagnosticos.adicao,        "
                    + "	diagnosticos.eixo,          "
                    + "	diagnosticos.ativo,         "
                    + "	categorias.id_categoria,    "
                    + "	categorias.descricao		AS 'categoria',      "
                    + "	categorias.ativo                AS 'ativo_categoria' "
                    + "FROM                                                  "
                    + "	diagnosticos                                         "
                    + "INNER JOIN categorias                                 "
                    + "	ON categorias.id_categoria	= diagnosticos.id_diagnostico "
                    + "WHERE "
                    + "	id_diagnostico = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.esferico = Banco.leitor.getFloat("esferico");
                this.cilindro = Banco.leitor.getFloat("cilindro");
                this.adicao = Banco.leitor.getFloat("adicao");
                this.eixo = Banco.leitor.getFloat("eixo");
                this.ativo = Banco.leitor.getInt("ativo") == 1;

                categoria = new Categoria();
                categoria.setId(Banco.leitor.getInt("id_categoria"));
                categoria.setDescricao(Banco.leitor.getString("categoria"));
                categoria.setAtivo(Banco.leitor.getInt("ativo_categoria") == 1);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    // </editor-fold> 
}
