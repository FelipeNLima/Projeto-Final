package Model;

/*
 ** Desenvolvido por..: Felipe
 ** Data..............: 01/06/2017  
*/
public class Categoria {
    
    private int id;
    private String descricao;
    private Boolean ativo;
    

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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    

}
