package Validacoes;

import javax.swing.JOptionPane;

/*
 ** Desenvolvido por..: wesley
 ** Data..............: 04/06/2017  
 */
public class Excecoes {

    public static void mostrarExcecoes(Exception ex) {
	JOptionPane.showMessageDialog(null, ex.toString(), "Exceção",JOptionPane.ERROR_MESSAGE);
    }
}
