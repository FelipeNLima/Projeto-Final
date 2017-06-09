package Modelos;

import BaseDeDados.Banco;
import Validacoes.Excecoes;
import java.sql.SQLException;
import java.util.Date;

public class Funcionario extends Pessoa implements ICadastro {
    
    private Double salario;
    private Date dataDeAdmissao;
    private Cargo cargo;
    

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }
    
    
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="INSERIR, ATUALIZAR, REMOVER E CARREGAR POR ID">
 
    @Override
    public void inserir() {
        try {
            String query
                    = "INSERT INTO funcionarios                                 "
                    + "	(id_endereco, id_cargo, nome, cpf, genero, data_de_nascimento, celular, telefone, email, salario, data_de_admissao, ativo) "
                    + "OUTPUT inserted.id_funcionario                           "
                    + "VALUES                                                   "
                    + "	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.endereco.getId());
            Banco.cmd.setInt(2, this.cargo.getId());
            Banco.cmd.setString(3, this.nome);
            Banco.cmd.setString(4, this.cpf);
            Banco.cmd.setString(5, this.genero);
            Banco.cmd.setDate(6, Validacoes.Funcoes.converterData(dataDeNascimento));
            Banco.cmd.setString(7, this.celular);
            Banco.cmd.setString(8, this.telefone);
            Banco.cmd.setString(9, this.email);
            Banco.cmd.setDouble(10, this.salario);
            Banco.cmd.setDate(11, Validacoes.Funcoes.converterData(dataDeAdmissao));
            Banco.cmd.setInt(12, this.ativo ? 1 : 0);
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
                    = "UPDATE funcionarios      SET     "
                    + "	id_endereco             = ?,    "
                    + "	id_cargo                = ?,    "
                    + " nome                    = ?,    "
                    + " cpf                     = ?,    "
                    + " genero                  = ?,    "
                    + " data_de_nascimento      = ?,    "
                    + " celular                 = ?,    "
                    + " telefone                = ?,    "
                    + " email                   = ?,    "
                    + " salario                 = ?,    "
                    + " data_de_admissao        = ?,    "
                    + " ativo                   = ?     "
                    + " WHERE                           "
                    + "	id_funcionario          = ?     ";

            Banco.cmd = Banco.getConexao().prepareStatement(query);
            Banco.cmd.setInt(1, this.endereco.getId());
            Banco.cmd.setInt(2, this.cargo.getId());
            Banco.cmd.setString(3, this.nome);
            Banco.cmd.setString(4, this.cpf);
            Banco.cmd.setString(5, this.genero);
            Banco.cmd.setDate(6, Validacoes.Funcoes.converterData(dataDeNascimento));
            Banco.cmd.setString(7, this.celular);
            Banco.cmd.setString(8, this.telefone);
            Banco.cmd.setString(9, this.email);
            Banco.cmd.setDouble(10, this.salario);
            Banco.cmd.setDate(11, Validacoes.Funcoes.converterData(dataDeAdmissao));
            Banco.cmd.setInt(12, this.ativo ? 1 : 0);
            Banco.cmd.setInt(13, this.id);

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
//        try {
//            String query
//                    = "SELECT                                                        "
//                    + "     diagnosticos.id_categoria,                               "
//                    + "     diagnosticos.esferico,                                   "
//                    + "     diagnosticos.cilindro,                                   "
//                    + "     diagnosticos.adicao,                                     "
//                    + "     diagnosticos.eixo,                                       "
//                    + "     diagnosticos.ativo,                                      "
//                    + "     categorias.id_categoria,                                 "
//                    + "     categorias.descricao		AS 'categoria',      "
//                    + "     categorias.ativo                AS 'ativo_categoria'     "
//                    + "FROM                                                          "
//                    + "     diagnosticos                                             "
//                    + "INNER JOIN categorias                                         "
//                    + "     ON categorias.id_categoria = diagnosticos.id_diagnostico "
//                    + "WHERE "
//                    + "     id_diagnostico = ?";
//
//            Banco.cmd = Banco.getConexao().prepareStatement(query);
//            Banco.cmd.setInt(1, id);
//            Banco.leitor = Banco.cmd.executeQuery();
//
//            if (Banco.leitor.next()) {
////                this.id = id;
////                this.esferico = Banco.leitor.getFloat("esferico");
////                this.cilindro = Banco.leitor.getFloat("cilindro");
////                this.adicao = Banco.leitor.getFloat("adicao");
////                this.eixo = Banco.leitor.getFloat("eixo");
////                this.ativo = Banco.leitor.getInt("ativo") == 1;
////
////                categoria = new Categoria();
////                categoria.setId(Banco.leitor.getInt("id_categoria"));
////                categoria.setDescricao(Banco.leitor.getString("categoria"));
////                categoria.setAtivo(Banco.leitor.getInt("ativo_categoria") == 1);
//            }
//
//            Banco.cmd.close();
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        }
    }


    @Override
    public void carregar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // </editor-fold>

}
