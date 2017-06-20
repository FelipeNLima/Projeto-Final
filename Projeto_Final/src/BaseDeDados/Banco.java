package BaseDeDados;

import java.sql.*;

public class Banco {

    public static PreparedStatement cmd;
    public static ResultSet leitor;

    public static Connection getConexao() {
        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(ConfiguracaoBanco.getStringDeConexao());
        } catch (Exception ex) {
           Validacoes.Mensagens.mostrarAviso(ex.toString());
        }

        return con;
    }
}
