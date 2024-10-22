import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibroDAO {
    Connection conexion;
    Scanner scanner = new Scanner(System.in);

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearLibro() throws SQLException {
        System.out.print("Introduce el titulo:  ");

        String titulo = scanner.nextLine();
        System.out.print("Introduce el ISBN:  ");
        String isbn = scanner.nextLine();


        //Comprobar si existe el libro
        String comprobarLibro = "SELECT COUNT(*) FROM libro WHERE titulo = ?";
        PreparedStatement pstm = conexion.prepareStatement(comprobarLibro);
        pstm.setString(1, titulo);
        ResultSet rs = pstm.executeQuery();

        //Si existe mostramos un error
        if (rs.next() && rs.getInt(1) > 0) {
            System.out.println("Error: El libro" + titulo + " ya existe.");

        } else {
            //Si no existe, lo añadimos
            String insertSql = "INSERT INTO libro (titulo, isbn) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conexion.prepareStatement(insertSql)) {
                insertStmt.setString(1, titulo);
                insertStmt.setString(2, isbn);
                insertStmt.executeUpdate();
            }
        }
    }


    public void actualizarLibro() throws SQLException {
        //Pedimos el ID del libro que queremos actualizar
        System.out.print("¿Qué libro deseas actualizar? (Introduce el ID) : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        //Comprobamos si el libro existe
        String comprobarLibro = "SELECT COUNT(*) FROM libro WHERE id = ?";
        try (PreparedStatement checkStmt = conexion.prepareStatement(comprobarLibro)) {
            checkStmt.setInt(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("Error: El libro " + id + " no existe.");
                }
            }
        }


        System.out.print("Título: ");
        String nombre = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();


        //Lo actualizamos...
        String update = "UPDATE libro SET titulo = ?, isbn = ?  WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(update)) {
            ps.setString(1, nombre);
            ps.setString(2, isbn);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    //Eliminar libro
    public void eliminarLibro() throws SQLException {
        //Pedimos el ID del libro que queremos eliminar
        System.out.print("¿Qué libro deseas eliminar? (Introduce el ID): ");
        int id = scanner.nextInt();

        //Eliminamos el libro
        String eliminarEquipo = "DELETE FROM libro WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarEquipo)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    //Leer los libros
    public List<LibroDTO> leerLibros() {
        List<LibroDTO> listaLibros = new ArrayList<>();
        String select = "SELECT * from libro";

        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String isbn = rs.getString("isbn");

                LibroDTO nuevoLibro = new LibroDTO(titulo, isbn);
                nuevoLibro.setId(id);
                listaLibros.add(nuevoLibro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }



}
