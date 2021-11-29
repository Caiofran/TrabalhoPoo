import java.sql.Connection;

public class TesteBanco {

    public static void main(String[] args) {

        try {
            Connection banco = ConnectionFactory.getConnection();
            System.out.println("Conexao OK!!!!");
        } catch (Exception e) {
            System.out.println("Erro na Conexão<<>>");
            System.out.println(e);
        }

    }

}
