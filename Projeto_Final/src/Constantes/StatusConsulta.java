package Constantes;

public enum StatusConsulta {
    Finalizada("F"), Atrazada("A");

    public String status;

    StatusConsulta(String status) {
        this.status = status;
    }

    public static String getStatus(StatusConsulta valor) {
        if (valor == Finalizada) {
            return "F";
        }

        return "A";
    }
}
