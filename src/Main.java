import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Conexion conexionBdd = new Conexion();
    static LibroDAO libro = new LibroDAO(conexionBdd);
    static AutorDAO autor = new AutorDAO(conexionBdd);
    static UsuarioDAO usuario = new UsuarioDAO(conexionBdd);
    static PrestamoDAO prestamo = new PrestamoDAO(conexionBdd);
    static LibroAutorDAO asociarLibroaAutor = new LibroAutorDAO(conexionBdd);
    static Sincronizacion sincronizacion = new Sincronizacion(autor, libro, usuario, prestamo, asociarLibroaAutor);

    static Scanner scanner = new Scanner(System.in);


    public static void menu() throws SQLException {
        sincronizacion.sincronizar();
        System.out.println("\n----------------------MENÚ----------------------");
        System.out.println("         ---------CREAR---------");
        System.out.printf("             1. CREAR LIBRO \n             2. CREAR AUTOR\n             3. CREAR USUARIO\n             4. CREAR PRÉSTAMO\n");
        System.out.println("         ---------LEER---------");
        System.out.printf("             5. LEER LIBROS\n             6. LEER AUTORES\n             7. LEER USUARIOS\n             8. LEER PRÉSTAMOS\n");
        System.out.println("         ---------ACTUALIZAR---------");
        System.out.printf("             9. ACTUALIZAR LIBRO\n            10. ACTUALIZAR AUTOR\n            11. ACTUALIZAR USUARIO \n");
        System.out.println("         ---------ELIMINAR---------");
        System.out.printf("            12. ELIMINAR LIBRO\n            13. ELIMINAR AUTOR\n            14. ELIMINAR USUARIO\n            15. ELIMINAR PRÉSTAMO\n");
        System.out.println("         ---------ASOCIACIÓN LIBRO Y AUTOR---------");
        System.out.printf("            16. ASOCIAR LIBRO A AUTOR\n            17. ELIMINAR RELACIÓN LIBRO-AUTOR\n\n");
        System.out.println("            18. SALIR\n");
        System.out.print("Introduce una opción: ");
        int opcion = scanner.nextInt();


        switch (opcion) {
            case 1:
                libro.crearLibro();
                sincronizacion.sincronizar();
                menu();
            case 2:
                autor.crearAutor();
                sincronizacion.sincronizar();
                menu();
            case 3:
                usuario.insertUsuario();
                sincronizacion.sincronizar();
                menu();
            case 4:
                sincronizacion.leerUsuarios();
                sincronizacion.leerLibros();
                prestamo.insertPrestamo();
                sincronizacion.sincronizar();
                menu();
            case 5:
                sincronizacion.leerLibros();
                menu();
            case 6:
                sincronizacion.leerAutores();
                menu();
            case 7:
                sincronizacion.leerUsuarios();
                menu();
            case 8:
                sincronizacion.leerPrestamos();
                menu();
            case 9:
                libro.actualizarLibro();
                sincronizacion.sincronizar();
                menu();
            case 10:
                autor.actualizarAutor();
                sincronizacion.sincronizar();
                menu();
            case 11:
                usuario.actualizarUsuario();
                sincronizacion.sincronizar();
                menu();
            case 12:
                libro.eliminarLibro();
                sincronizacion.sincronizar();
                menu();
            case 13:
                autor.eliminarAutor();
                sincronizacion.sincronizar();
                menu();
            case 14:
                usuario.eliminarUsuario();
                sincronizacion.sincronizar();
                menu();
            case 15:
                prestamo.eliminarPrestamos();
                sincronizacion.sincronizar();
                menu();
            case 16:
                sincronizacion.leerAutores();
                sincronizacion.leerLibros();
                asociarLibroaAutor.anadirAutoraLibro();
                sincronizacion.sincronizar();
                menu();
            case 17:
                sincronizacion.leerLibros();
                sincronizacion.leerAutores();
                asociarLibroaAutor.eliminarRelacion();
                sincronizacion.sincronizar();
                menu();

            case 18:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("ERROR. INTRODUZCA UN NÚMERO VÁLIDO");
        }

    }

    public static void main(String[] args) throws SQLException {
        menu();
    }

}


