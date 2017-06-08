package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categoria implements ICadastro {

    private int id;
    private String descricao;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Categoria() {
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
                    = "INSERT INTO categorias       "
                    + "	(descricao, ativo)          "
                    + "OUTPUT inserted.id_categoria "
                    + "VALUES                       "
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
                    = "UPDATE categorias SET "
                    + "	descricao    = ?,    "
                    + "	ativo	     = ?     "
                    + "WHERE                 "
                    + "	id_categoria = ?  ";

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
                    = "SELECT           "
                    + "	descricao,      "
                    + "	ativo           "
                    + "FROM             "
                    + "	categorias      "
                    + "WHERE            "
                    + "	id_categoria = ?";

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

    public static ArrayList<Categoria> carregarTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();

        try {
            String query
                    = "SELECT          "
                    + "	 id_categoria, "
                    + "	 descricao     "
                    + "FROM            "
                    + "	 categorias    "
                    + "WHERE           "
                    + "	 ativo = 1  ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.leitor = Banco.cmd.executeQuery();

            while (Banco.leitor.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(Banco.leitor.getInt("id_categoria"));
                categoria.setDescricao(Banco.leitor.getString("descricao"));

                lista.add(categoria);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
             Excecoes.mostrarExcecoes(ex);
        }

        return lista;
    }

// </editor-fold> 
}
