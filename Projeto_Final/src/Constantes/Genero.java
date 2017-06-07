package Constantes;

public enum Genero {
    Masculino("M"), Feminino("F");

    public String sexo;

    Genero(String sexo) {
        this.sexo = sexo;
    }

    public static boolean isMasculino(String genero) {
        return Masculino.toString().equals(genero);
    }

    public static boolean isFeminino(String genero) {
        return Feminino.toString().equals(genero);
    }

    public static String getNome(String genero) {
        if (isMasculino(genero)) {
            return "Masculino";
        }

        return "Feminino";
    }
}
