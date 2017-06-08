package Validacoes;

import javax.swing.JOptionPane;

public class Mensagens {

    public static void mostrarMsg(String texto, String titulo) {
        JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int mostrarConfirmacao(String texto, String titulo) {
        return JOptionPane.showConfirmDialog(null, texto, titulo, JOptionPane.YES_NO_OPTION);
    }

    public static void mostrarAviso(String texto) {
        mostrarMsg(texto, "Aviso");
    }

    public static int mostrarDesejaRemover() {
        return mostrarConfirmacao("Tem certeza que deseja remover esse registro?", "Aviso");
    }
}
