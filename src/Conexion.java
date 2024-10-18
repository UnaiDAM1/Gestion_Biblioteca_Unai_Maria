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


    public Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + BDD, USER, PASSW);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection conectar() {
        return conexion;
    }


    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

