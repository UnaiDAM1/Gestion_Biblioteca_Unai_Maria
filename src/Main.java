import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Conexion conexionBdd = new Conexion();
    static LibroDAO libro = new LibroDAO(conexionBdd);
    static AutorDAO autor = new AutorDAO(conexionBdd);
    static UsuarioDAO usuario = new UsuarioDAO(conexionBdd);
    static PrestamoDAO prestamo = new PrestamoDAO(conexionBdd);

    static Scanner scanner = new Scanner(System.in);



    public static void menu() throws SQLException {
        System.out.println("----------------------MENÚ----------------------");
        System.out.printf("1. CREAR LIBRO \n2. CREAR AUTOR\n3. CREAR USUARIO\n 4.CREAR PRÉSTAMO\n");
        System.out.printf("5. LEER LIBROS\n6. LEER AUTORES\n7. LEER USUARIOS\n 8.LEER PRÉSTAMOS\n");
        System.out.printf("9. ACTUALIZAR LIBRO\n10. ACTUALIZAR AUTOR\n 11.ACTUALIZAR USUARIO \n");
        System.out.printf("12. ELIMINAR LIBRO\n13. ELIMINAR AUTOR\n14.ELIMINAR USUARIO\n15.ELIMINAR PRÉSTAMO\n");
        System.out.println("11. SALIR\n");
        System.out.print("Introduce una opción: ");
        int opcion = scanner.nextInt();


        switch (opcion) {
            case 1:
                libro.crearLibro();
                menu();
            case 2:
                autor.crearAutor();
                menu();
            case 3:
                usuario.insertUsuario();
                menu();
            case 4:
                prestamo.insertPrestamo();
            case 5:
                libro.leerLibros();
                menu();
            case 6:
                autor.leerAutores();
                menu();
            case 7:
                usuario.leerUsuarios();
                menu();
            case 8:
                prestamo.leerPrestamos();
                menu();
            case 9:
                libro.actualizarLibro();
                menu();
            case 10:
                autor.actualizarAutor();
                menu();
            case 11:
                usuario.actualizarUsuario();
                menu();
            case 12:
                libro.eliminarLibro();
                menu();
            case 13:
                autor.eliminarAutor();
                menu();
            case 14:
                usuario.eliminarUsuario();
                menu();
            case 15:
                prestamo.eliminarPrestamos();
                menu();
            default:
                System.out.println("ERROOOOOOR");
        }

    }

    public static void main(String[] args) throws SQLException {
        menu();
    }

}


