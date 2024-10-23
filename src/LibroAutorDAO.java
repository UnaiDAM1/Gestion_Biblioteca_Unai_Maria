import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Clase LibroAutorDAO es la que se encarga de la gestión de la relación entre libro y autor dentro de la base de datos
 * */
public class LibroAutorDAO {
    Scanner scanner = new Scanner(System.in);
    String tab = "Libro_Autor";
    List<LibroDTO> libros = new ArrayList<LibroDTO>();
    List<AutorDTO> autores = new ArrayList<AutorDTO>();
    Connection conexion;
    public LibroAutorDAO(Conexion conexion){
        this.conexion = conexion.conectar();
    }
    //Constructor de la clase
    public void inicializarListas(AutorDAO autores, LibroDAO libros){
        this.autores = autores.leerAutores();
        this.libros = libros.leerLibros();
    }

    //Crear una nueva relación libroAutor
    public void anadirAutoraLibro(){
        //Pedimos el ID del libro y del autor
        System.out.print("Introduzca el ID del autor: ");
        int idAutor = scanner.nextInt();
        System.out.print("Introduzca el ID del libro: ");
        int idLibro = scanner.nextInt();
        //Cramos la relación libroAutor
        String insert = "INSERT INTO " + tab + " (idLibro, idAutor) VALUES (?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setInt(1, idLibro);
            ps.setInt(2, idAutor);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Esta Asociación ya existe");
        }
    }
    public void eliminarPorAutor(int idAutor){
        String deletePorAutor = "DELETE * FROM " + tab + " WHERE idAutor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(deletePorAutor)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para eliminar todas las relaciones de un libro
    public void eliminarPorLibro(int idLibro){
        String deletePorLibro = "DELETE * FROM " + tab + " WHERE idAutor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(deletePorLibro)) {
            stmt.setInt(1, idLibro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para eliminar todas las relaciones de un autor
    public void eliminarRelacion() {
        System.out.print("Introduce el ID del libro: ");
        int idLibro = scanner.nextInt();
        System.out.print("Introduce el ID del autor: ");
        int idAutor = scanner.nextInt();

        String delete = "DELETE FROM " + tab + " WHERE idAutor = ? AND idLibro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(delete)) {
            stmt.setInt(1, idAutor);
            stmt.setInt(2, idLibro);
            int rowsAffected = stmt.executeUpdate();

            //Comprueba si los IDs introducidos existen en la base de datos
            if (rowsAffected <= 0) {
                throw new RuntimeException("No se encontró la relación con los IDs proporcionados.");
            }
            else {
                System.out.println("Relación eliminada");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //Metodo para sacar los autores guardados en la base de datos en una lista
        public List<LibroAutorDTO> leerLibroAutor() {
        List<LibroAutorDTO> listaLibroAutor = new ArrayList<>();
        String select = "SELECT * from " + tab;

        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                int idAutor = rs.getInt("idAutor");

                LibroAutorDTO nuevoLibroAutor= new LibroAutorDTO(idLibro, idAutor);
                listaLibroAutor.add(nuevoLibroAutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibroAutor;
    }
/*
 */
}
