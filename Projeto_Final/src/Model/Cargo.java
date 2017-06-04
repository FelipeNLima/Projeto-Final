
import Validacoes.Excecoes;
import BaseDeDados.Banco;
import Model.CadastroInterface;
import java.sql.SQLException;

/*
 ** Desenvolvido por..: Wesley
 ** Data..............: 01/06/2017  
 */
public class Cargo implements CadastroInterface {

    private int id;
    private String descricao;
    private Boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTOR">  
    public Cargo() {
        ativo = true;
    }

    public Cargo(String descricao) {
        this.descricao = descricao;
        this.ativo = true;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER E CARREGAR POR ID">  
    @Override
    public void inserir() {
        try {
            String query = "INSERT INTO cargos         "
                    + "	(descricao, ativo)     "
                    + "OUTPUT inserted.id_cargo   "
                    + "VALUES                     "
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
            String query = "UPDATE cargos SET   "
                    + "	descricao  = ?, "
                    + "	ativo	   = ?  "
                    + "WHERE               "
                    + "	id_cargo   = ?  ";

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
    public void carregarPorId(int id) {

        try {
            String query = "SELECT          "
                    + "	descricao,  "
                    + "	ativo       "
                    + "FROM	    "
                    + "	cargos      "
                    + "WHERE           "
                    + "	id_cargo = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.descricao = Banco.leitor.getString("descricao");
                this.ativo = Banco.leitor.getInt("ativo") == 1 ? true : false;
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    // </editor-fold> 
}
