package Modelos;

import Validacoes.Excecoes;
import BaseDeDados.Banco;
import java.sql.SQLException;

public class Cliente extends Pessoa 
{

// <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER E CARREGAR POR ID">
    @Override
    public void inserir() {

        try {
            String query = "INSERT INTO clientes                                    "
                    + "	(nome, cpf, genero, data_de_nascimento, celular, telefone, email, ativo)     "
                    + "OUTPUT inserted.id_cliente                                   "
                    + "VALUES                                                       "
                    + "	(?, ?, ?, ?, ?, ?, ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
//            Banco.cmd.setInt(1, this.endereco.getId());
            Banco.cmd.setString(1, this.nome);
            Banco.cmd.setString(2, this.cpf);
            Banco.cmd.setString(3, this.genero);
            Banco.cmd.setDate(4, Validacoes.Funcoes.converterData(dataDeNascimento));
            Banco.cmd.setString(5, this.celular);
            Banco.cmd.setString(6, this.telefone);
            Banco.cmd.setString(7, this.email);
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
            String query = "UPDATE clientes SET"
                    + "	id_cliente = ?,"
                    + " id_endereco = ?,"
                    + " nome = ?,"
                    + " cpf = ?,"
                    + " genero = ?,"
                    + " data_de_nascimento = ?,"
                    + " celular, "
                    + " telefone = ?,"
                    + " email = ?,"
                    + " ativo = ?,"
                    + " WHERE "
                    + " id_endereco = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.endereco.getId());
            Banco.cmd.setString(2, this.nome);
            Banco.cmd.setString(3, this.cpf);
            Banco.cmd.setString(4, this.genero);
            Banco.cmd.setDate(5, Validacoes.Funcoes.converterData(dataDeNascimento));
            Banco.cmd.setString(6, this.celular);
            Banco.cmd.setString(7, this.telefone);
            Banco.cmd.setString(8, this.email);
            Banco.cmd.setInt(9, this.ativo ? 1 : 0);
            Banco.cmd.setInt(10, this.id);
            Banco.leitor = Banco.cmd.executeQuery();

            Banco.cmd.executeUpdate();
            Banco.cmd.close();
        } catch (SQLException ex) {
            Excecoes.mostrarExcecoes(ex);
        }

    }

    @Override
    public void carregar() {
        carregarPorId(this.id);
    }

    @Override
    public void remover() {
        this.ativo = false;
        atualizar();
    }
    
    @Override
    public void carregarPorId(int id) {
        try {
            String query = "SELECT "
                    + "	cliente.id_cliente,"
                    + "	cliente.nome,"
                    + "	cliente.cpf,"
                    + "	cliente.genero,"
                    + "	cliente.dataDeNascimento,"
                    + "	cliente.celular,"
                    + "cliente.telefone, "
                    + "cliente.email, "
                    + "	cliente.id_cliente,    "
                    + "	cliente.ativo                   AS 'ativo_cliente' "
                    + "FROM                                                  "
                    + "	cliente                                         "
                    + "INNER JOIN endereco                                 "
                    + "	ON endereco.id_endereco	= cliente.id_cliente "
                    + "WHERE "
                    + "	iid_cliente = ?";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, id);
            Banco.leitor = Banco.cmd.executeQuery();

            if (Banco.leitor.next()) {
                this.id = id;
                this.nome = Banco.leitor.getString("nome");
                this.cpf = Banco.leitor.getString("cpf");
                this.genero = Banco.leitor.getString("genero");
                this.dataDeNascimento = Banco.leitor.getDate("data de nascimento");
                this.celular = Banco.leitor.getString("celular");
                this.telefone = Banco.leitor.getString("telefone");
                this.email = Banco.leitor.getString("email");
                this.ativo = Banco.leitor.getInt("ativo") == 1;

                endereco = new Endereco();
                endereco.setId(Banco.leitor.getInt("id_endereco"));
                endereco.setCep(Banco.leitor.getString("cep"));
                endereco.setUf(Banco.leitor.getString("uf"));
                endereco.setCidade(Banco.leitor.getString("cidade"));
                endereco.setBairro(Banco.leitor.getString("bairro"));
                endereco.setLogradouro(Banco.leitor.getString("logradouro"));
                endereco.setComplemento(Banco.leitor.getString("complemento"));
                endereco.setNumero(Banco.leitor.getString("numero"));
                endereco.setAtivo(Banco.leitor.getInt("ativo_categoria") == 1);
            }

            Banco.cmd.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    // </editor-fold>
}
