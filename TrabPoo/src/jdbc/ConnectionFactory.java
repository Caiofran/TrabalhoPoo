/* Caio França Ricciardi, Murilo Gabriel Ferreira, Jônatas dos Santos de Sales e 
Flávio Henrique do Nascimento*/

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVE = "com.mysql.jdbc.Driver";
    // private static final String URL =
    // "jdbc:mysql://database-2.cuve3svqyxqb.us-east-1.rds.amazonaws.com:3306/?user=admin";database-2.cuve3svqyxqb.us-east-1.rds.amazonaws.com
    private static final String URL = "jdbc:mysql://database-2.cuve3svqyxqb.us-east-1.rds.amazonaws.com:3306/bancoprog?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String SENHA = "password";

    public static Connection getConnection() throws ClassNotFoundException {
        try {

            // Class forName = Class.forName(DRIVE); //opcional dependendo da versao
            // Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
            // System.out.println(e);
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}