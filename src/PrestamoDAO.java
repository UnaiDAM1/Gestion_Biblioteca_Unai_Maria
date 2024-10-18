import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PrestamoDAO {
    String tab = "Prestamo";
    Connection conexion;
    public PrestamoDAO(Conexion conexion){
        this.conexion = conexion.conectar();
    }
    public void insertPrestamo(PrestamoDTO pre){
        String insert = "INSERT INTO " + tab + " (fechaInicio, fechaFin, usuarioId, libroId) VALUES (?,?,?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setDate(1, Date.valueOf(pre.fechaInicio));
            ps.setDate(2, Date.valueOf(pre.fechaFin));
            ps.setInt(3, pre.usuarioId);
            ps.setInt(4, pre.libroId);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePrestamo(PrestamoDTO pre){

    }
}
