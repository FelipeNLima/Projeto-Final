package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;

public class Endereco implements ICadastro {

    private int id;
    private String cep;
    private String uf;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="CTR">
    public Endereco() {
        this.ativo = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return this.uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
                    = "INSERT INTO enderecos                                                "
                    + "   (cep, uf, cidade, bairro, logradouro, complemento, numero, ativo) "
                    + "OUTPUT inserted.id_endereco                                          "
                    + "VALUES                                                               "
                    + "	   ( ?, ?, ?, ?, ?, ?, ?, ?) ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setString(1, this.cep);
            Banco.cmd.setString(2, this.uf);
            Banco.cmd.setString(3, this.cidade);
            Banco.cmd.setString(4, this.bairro);
            Banco.cmd.setString(5, this.logradouro);
            Banco.cmd.setString(6, this.complemento);
            Banco.cmd.setString(7, this.numero);
            Banco.cmd.setInt(8, this.ativo ? 1 : 0);
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
                    = "UPDATE enderecos SET  "
                    + "	  cep		= ?, "
                    + "	  uf		= ?, "
                    + "	  cidade	= ?, "
                    + "   bairro        = ?, "
                    + "	  logradouro	= ?, "
                    + "	  complemento	= ?, "
                    + "	  numero	= ?, "
                    + "	  ativo		= ?  "
                    + "WHERE                 "
                    + "	  id_endereco = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setString(1, this.cep);
            Banco.cmd.setString(2, this.uf);
            Banco.cmd.setString(3, this.cidade);
            Banco.cmd.setString(4, this.bairro);
            Banco.cmd.setString(5, this.logradouro);
            Banco.cmd.setString(6, this.complemento);
            Banco.cmd.setString(7, this.numero);
            Banco.cmd.setInt(8, this.ativo ? 1 : 0);
            Banco.cmd.setInt(9, this.id);

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
                    + "     cep,            "
                    + "     uf,             "
                    + "     cidade,         "
                    + "     bairro,         "
                    + "     logradouro,     "
                    + "     complemento,    "
                    + "     numero,         "
                    + "     ativo           "
                    + "FROM                 "
                    + "     enderecos       "
                    + "WHERE                "
                    + "     id_endereco = ? ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.cep = Banco.leitor.getString("cep");
                this.uf = Banco.leitor.getString("uf");
                this.cidade = Banco.leitor.getString("cidade");
                this.bairro = Banco.leitor.getString("bairro");
                this.logradouro = Banco.leitor.getString("logradouro");
                this.complemento = Banco.leitor.getString("complemento");
                this.numero = Banco.leitor.getString("numero");
                this.ativo = Banco.leitor.getInt("ativo") == 1;
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }
    }
    // </editor-fold> 
}
