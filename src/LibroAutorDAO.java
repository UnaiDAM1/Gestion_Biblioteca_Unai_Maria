import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibroAutorDAO {
    Scanner scanner = new Scanner(System.in);
    String tab = "Libro_Autor";
    List<LibroDTO> libros = new ArrayList<LibroDTO>();
    List<AutorDTO> autores = new ArrayList<AutorDTO>();
    Connection conexion;
    public LibroAutorDAO(Conexion conexion){
        this.conexion = conexion.conectar();
    }
    public void inicializarListas(AutorDAO autores, LibroDAO libros){
        this.autores = autores.leerAutores();
        this.libros = libros.leerLibros();
    }
    public void anadirAutoraLibro(){
        System.out.println("Introduzca el ID del autor: ");
        int idAutor = scanner.nextInt();
        System.out.println("Introduzca el ID del libro: ");
        int idLibro = scanner.nextInt();
        String insert = "INSERT INTO " + tab + " (idLibro, idAutor) VALUES (?,?);";
        try (PreparedStatement ps = conexion.prepareStatement(insert)){
            ps.setInt(1, idLibro);
            ps.setInt(2, idAutor);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
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
/*
 */
}