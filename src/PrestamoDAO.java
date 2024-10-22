import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrestamoDAO {
    Scanner scanner = new Scanner(System.in);
    String tab = "Prestamo";
    Connection conexion;

    public PrestamoDAO(Conexion conexion){
        this.conexion = conexion.conectar();
    }
    public void insertPrestamo() {
        System.out.println("Introduzca su ID de usuario: ");
        int idUs = scanner.nextInt();
        System.out.println("Introduzca el ID del libro: ");
        int idLi = scanner.nextInt();
        System.out.println("Introduzca la fecha de devolución: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  // Formato de fecha
        LocalDate fechaFin = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.println("Introduce una fecha en formato dd/MM/yyyy:");
            String fechaStr = scanner.nextLine();

            try {
                fechaFin = LocalDate.parse(fechaStr, formatter);  // Parseamos la fecha con el formato especificado
                fechaValida = true;  // Si no hay error, la fecha es válida
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Inténtalo de nuevo.");
            }
        }

        LocalDate fechaInicio = LocalDate.now();
        String insert = "INSERT INTO " + tab + " (fechaInicio, fechaFin, usuarioId, libroId) VALUES (?,?,?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)) {
            ps.setDate(1, Date.valueOf(fechaInicio));
            ps.setDate(2, Date.valueOf(fechaFin));
            ps.setInt(3, idUs);
            ps.setInt(4, idLi);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarPrestamos() throws SQLException {
        //Pedimos el ID del libro que queremos eliminar
        System.out.print("¿Qué prestamo deseas eliminar? (Introduce el ID): ");
        int id = scanner.nextInt();

        //Eliminamos el libro
        String eliminarPrestamo = "DELETE FROM " + tab + " WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarPrestamo)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public List<PrestamoDTO> leerPrestamos() {
        List<PrestamoDTO> listaPrestamos = new ArrayList<>();
        String select = "SELECT * from " + tab;

        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fechaFin").toLocalDate();
                int idUsuario = rs.getInt("usuarioId");
                int idLibro = rs.getInt("libroId");

                PrestamoDTO nuevoPrestamo = new PrestamoDTO(fechaInicio, fechaFin, idUsuario, idLibro);
                nuevoPrestamo.setId(id);
                listaPrestamos.add(nuevoPrestamo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrestamos;
    }
}
