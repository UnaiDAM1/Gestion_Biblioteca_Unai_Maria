import java.sql.*;
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
    public void eliminarPorLibro(int idLibro){
        String deletePorLibro = "DELETE * FROM " + tab + " WHERE idAutor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(deletePorLibro)) {
            stmt.setInt(1, idLibro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarRelacion(){
        System.out.println("Introduce el ID del libro: ");
        int idLibro = scanner.nextInt();
        System.out.println("Introduce el ID del autor: ");
        int idAutor = scanner.nextInt();
        String delete = "DELETE * FROM " + tab + " WHERE idAutor = ? idLibro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(delete)) {
            stmt.setInt(1, idAutor);
            stmt.setInt(2, idLibro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
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
