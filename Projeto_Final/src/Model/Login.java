
import Model.CadastroInterface;

/*
 ** Desenvolvido por..: wesley
 ** Data..............: 01/06/2017  
 */
public class Login implements CadastroInterface {

    private int id;
    private String usuario;
    private String senha;
    private Boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
