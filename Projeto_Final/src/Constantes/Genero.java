package Constantes;

public class Genero {

    public static final String masculino = "M";
    public static final String feminino = "F";

    public static boolean isMasculino(String genero) {
        return genero.equals(masculino);
    }

    public static boolean isFeminino(String genero) {
        return genero.equals(feminino);
    }

    public static String getNome(String genero) {
        if (isMasculino(genero)) {
            return "Masculino";
        }

        return "Feminino";
    }
}
