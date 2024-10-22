import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioDAO {
    Scanner scanner = new Scanner(System.in);
    String tab = "Usuario";
    Connection conexion;
    public UsuarioDAO(Connection conexion){
        this.conexion = conexion;
    }
    public void insertUsuario(){
        System.out.println("Introduzca su nombre de usuario: ");
        String nombre = scanner.nextLine();
        String insert = "INSERT INTO " + tab + " (nombre) VALUES (?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setString(1, nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarUsuario() throws SQLException {
        //Pedimos el ID del usuario que queremos actualizar
        System.out.print("¿Qué usuario deseas actualizar? (Introduce el ID) : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //Comprobamos si el libro existe
        String comprobarUsuario = "SELECT COUNT(*) FROM " + tab + " WHERE id = ?";
        try (PreparedStatement checkStmt = conexion.prepareStatement(comprobarUsuario)) {
            checkStmt.setInt(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("Error: El usuario " + id + " no existe.");
                }
            }
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        //Lo actualizamos...
        String update = "UPDATE " + tab + " SET nombre = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(update)) {
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarUsuario() throws SQLException {
        //Pedimos el ID del libro que queremos eliminar
        System.out.print("¿Qué usuario deseas eliminar? (Introduce el ID): ");
        int id = scanner.nextInt();

        //Eliminamos el libro
        String eliminarUsuario = "DELETE FROM " + tab + " WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarUsuario)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public List<UsuarioDTO> leerUsuarios() {
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();
        String select = "SELECT * from " + tab;

        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                UsuarioDTO nuevoUsuario = new UsuarioDTO(nombre);
                nuevoUsuario.setId(id);
                listaUsuarios.add(nuevoUsuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}
