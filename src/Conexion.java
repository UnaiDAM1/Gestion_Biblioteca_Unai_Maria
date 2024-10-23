import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Variables
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String BDD = "biblioteca";
    private static final String USER = "root";
    private static final String PASSW = "";
    private Connection conexion;


    //Establecer la conexión a la base de datos
    public Conexion() {
        try {
            //Cargar el driver JDBC para MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            //Establecer la conexión
            conexion = DriverManager.getConnection(URL + BDD, USER, PASSW);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //Devuelve el objeto Connection para operar en la base de datos
    public Connection conectar() {
        return conexion;
    }


}

