
import java.awt.Component;
import javax.swing.JTextField;

public final class Validar {

    public static void limparCampos(Component[] components) {
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
    }

    public static boolean validarCamposObrigatorios(Component[] components) {
        for (Component c : components) {
            if (c instanceof JTextField) {
                JTextField text = ((JTextField) c);
                if (text.getName().contains("Obr")) {
                    if (text.getText().equals("")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean validarCPF(String CPF) {

        if (CPF.length() != 11) {
            return false;
        }

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        for (int i = 1; i <= 9; i++) {
            if (CPF.equals(repete(i + "", 11))) {
                return false;
            }
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (Exception ex) {
            return (false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    private static String repete(String s, int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            str += s;
        }

        return str;
    }
}
