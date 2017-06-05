
import Model.CadastroInterface;

/*
 ** Desenvolvido por..: Wesley
 ** Data..............: 01/06/2017  
 */
public class Endereco implements CadastroInterface {

    private int id;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;
    private boolean ativo;

    // <editor-fold defaultstate="collapsed" desc="GETTERS E SETTERS">  
    public int getId() {
        return id;
    }

    public String getCep() {
        return cep;
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
