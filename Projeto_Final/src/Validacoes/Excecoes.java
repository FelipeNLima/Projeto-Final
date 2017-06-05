package Validacoes;

import javax.swing.JOptionPane;

public class Excecoes {

    public static void mostrarExcecoes(Exception ex) {
	JOptionPane.showMessageDialog(null, ex.toString(), "Exceção",JOptionPane.ERROR_MESSAGE);
    }
}
