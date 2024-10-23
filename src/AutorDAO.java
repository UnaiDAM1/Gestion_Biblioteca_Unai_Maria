import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
* Clase AutorDAO es la que se encarga de la gestión de los autores dentro de la base de datos
* */
public class AutorDAO {
    Connection conexion;
    LibroAutorDAO libroAutorDAO;

    Scanner scanner = new Scanner(System.in);

    //Constructor de la clase
    public AutorDAO(Conexion conexion) {
        this.conexion = conexion.conectar();
        libroAutorDAO = new LibroAutorDAO(conexion);
    }



    //Crear un nuevo Autor
    public void crearAutor() throws SQLException {
        System.out.print("Introduce el nombre del autor:  ");
        String nombre = scanner.nextLine();



        //Comprobar si existe el autor
        String comprobarLibro = "SELECT COUNT(*) FROM autor WHERE nombre = ?";
        PreparedStatement pstm = conexion.prepareStatement(comprobarLibro);
        pstm.setString(1, nombre);
        ResultSet rs = pstm.executeQuery();

        //Si existe mostramos un error
        if (rs.next() && rs.getInt(1) > 0) {
            System.out.println("Error:" + nombre + " ya esta registrado.");



        } else {

            //Si no existe, lo añadimos
            String insertSql = "INSERT INTO autor (nombre) VALUES (?)";
            try (PreparedStatement insertStmt = conexion.prepareStatement(insertSql)) {
                insertStmt.setString(1, nombre);
                insertStmt.executeUpdate();

            }
        }
    }

//Actualizar Autor
    public void actualizarAutor() throws SQLException {
        //Pedimos el ID del autor que queremos actualizar
        System.out.print("¿Qué autor deseas actualizar? (Introduce el ID) : ");
        int id = scanner.nextInt();

        //Comprobamos si el libro existe
        String comprobarLibro = "SELECT COUNT(*) FROM autor WHERE id = ?";
        try (PreparedStatement checkStmt = conexion.prepareStatement(comprobarLibro)) {
            checkStmt.setInt(1, id);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    System.out.println("Error: El autor con id " + id + " no existe.");
                }
            }
        }

        //Si existe, pedimos los nuevos datos
        System.out.print("Introduce el nuevo nombre: ");
        String nombre = scanner.nextLine();

        //Lo actualizamos...
        String update = "UPDATE libro SET nombre = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(update)) {
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //Eliminar un autor
    public void eliminarAutor() throws SQLException {
        //Pedimos el ID del autor que queremos eliminar
        System.out.print("¿Qué autor deseas eliminar? (Introduce el ID): ");
        int id = scanner.nextInt();

        //Eliminamos el libro
        String eliminarEquipo = "DELETE FROM autor WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(eliminarEquipo)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        libroAutorDAO.eliminarPorAutor(id);
    }

    //Metodo para sacar los autores guardados en la base de datos en una lista
    public List<AutorDTO> leerAutores() {
        List<AutorDTO> listaAutores = new ArrayList<>();
        String select = "SELECT * from autor";

        //Creamos la sentencia para sacar los datos de cada autor y meterlo en la lista
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery(select)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                AutorDTO nuevoAutor= new AutorDTO(nombre);
                nuevoAutor.setId(id);
                listaAutores.add(nuevoAutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAutores;
    }




}
