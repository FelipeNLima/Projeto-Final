package Constantes;

public enum Genero {
    Masculino("M"), Feminino("F");

    public String sexo;

    Genero(String sexo) {
        this.sexo = sexo;
    }

    public boolean isMasculino(String genero) {
        return Masculino.toString().equals(genero);
    }

    public boolean isFeminino(String genero) {
        return Feminino.toString().equals(genero);
    }

    public String getNome(String genero) {
        if (isMasculino(genero)) {
            return "Masculino";
        }

        return "Feminino";
    }
}
