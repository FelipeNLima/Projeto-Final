package BaseDeDados;

/*
 ** Desenvolvido por..: Wesley
 ** Data..............: 04/06/2017  
 */

public class ConfiguracaoBanco {

    public static String servidor = "localhost";
    public static String banco = "projeto_clinica";
    public static String usuario = "sa";
    public static String senha = "1234";
    public static int porta = 1433;

    public static String getStringDeConexao() {
        return String.format("jdbc:sqlserver://%1$s:%2$s;databaseName=%3$s;user=%4$s;password=%5$s;", servidor, porta, banco, usuario, senha);
    }
}
