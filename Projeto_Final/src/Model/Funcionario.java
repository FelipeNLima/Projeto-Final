package Model;

import java.util.Date;

/*
 ** Desenvolvido por..: Felipe
 ** Data..............: 01/06/2017  
*/
public class Funcionario extends Pessoa{
    
    private Double salario;
    private Date dataDeAdmissao;
    

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }
}
