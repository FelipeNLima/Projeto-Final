package Constantes;

public enum StatusConsulta {
    Finalizada("F"), Atrazada("A") ;

    public String status;

    StatusConsulta(String status) {
        this.status = status;
    }
}
