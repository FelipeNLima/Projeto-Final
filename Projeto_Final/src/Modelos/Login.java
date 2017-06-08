package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login implements ICadastro {

    private int id;
    private Funcionario funcionario;
    private String usuario;
    private String senha;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTR">
    public Login() {
        this.ativo = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
                    = "INSERT INTO logins                       "
                    + "	(id_funcionario, usuario, senha, ativo) "
                    + "OUTPUT inserted.id_login                 "
                    + "VALUES                                   "
                    + "	(?, ? , ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, funcionario.getId());
            Banco.cmd.setString(2, this.usuario);
            Banco.cmd.setString(3, this.senha);
            Banco.cmd.setInt(4, this.ativo ? 1 : 0);
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
                    = "UPDATE logins SET    "
                    + " id_funcionario = ?, "
                    + "	usuario        = ?, "
                    + "	senha          = ?, "
                    + "	ativo          = ?  "
                    + "WHERE                "
                    + "	id_login = ? ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.funcionario.getId());
            Banco.cmd.setString(2, this.usuario);
            Banco.cmd.setString(3, this.senha);
            Banco.cmd.setInt(4, this.ativo ? 1 : 0);
            Banco.cmd.setInt(5, this.id);

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
                    + " id_funcionario, "
                    + "	usuario,        "
                    + "	senha,          "
                    + "	ativo           "
                    + "FROM             "
                    + "	logins          "
                    + "WHERE            "
                    + "	id_login = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.funcionario = new Funcionario();
                this.id = id;
                this.funcionario.setId(Banco.leitor.getInt("id_funcionario"));
                this.usuario = Banco.leitor.getString("usuario");
                this.senha = Banco.leitor.getString("senha");
                this.ativo = Banco.leitor.getInt("ativo") == 1;
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }

    public static ArrayList<Login> carregarTodos() {
        ArrayList<Login> lista = new ArrayList<>();

        try {
            String query
                    = "SELECT            "
                    + "	 id_login,       "
                    + "  id_funcionario, "
                    + "	 usuario,        "
                    + "  senha           "
                    + "FROM              "
                    + "	 logins          "
                    + "WHERE             "
                    + "	 ativo = 1  ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.leitor = Banco.cmd.executeQuery();

            while (Banco.leitor.next()) {
                Login login = new Login();
                Funcionario func = new Funcionario();

                login.setId(Banco.leitor.getInt("id_login"));
                func.setId(Banco.leitor.getInt("id_funcionario"));
                login.setFuncionario(func);
                login.setUsuario(Banco.leitor.getString("usuario"));
                login.setSenha(Banco.leitor.getString("senha"));

                lista.add(login);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }

        return lista;
    }

// </editor-fold> 
}
