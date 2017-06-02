
import Model.CadastroInterface;

/*
 ** Desenvolvido por..: wesley
 ** Data..............: 01/06/2017  
 */
public class Cargo implements CadastroInterface {

    private int id;
    private String descricao;
    private Boolean ativo;

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

    @Override
    public void inserir() {
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void remover() {
    }

    @Override
    public void carregarPorId(int id) {
    }
}
