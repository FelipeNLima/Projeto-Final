package BaseDeDados;

public class ConfiguracaoBanco {

    private static final String servidor = "localhost";
    private static final String banco = "projeto_clinica";
    private static final String usuario = "sa";
    private static final String senha = "1234";
    private static final int porta = 1433;

    public static String getStringDeConexao() {
        return String.format("jdbc:sqlserver://%1$s:%2$s;databaseName=%3$s;user=%4$s;password=%5$s;",
                servidor, porta, banco, usuario, senha);
    }
}
