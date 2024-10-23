import java.util.ArrayList;
import java.util.List;

public class Sincronizacion {

    //Creación de listas
    List<AutorDTO> autores = new ArrayList<>();
    List<LibroDTO> libros = new ArrayList<>();
    List<UsuarioDTO> usuarios = new ArrayList<>();
    List<PrestamoDTO> prestamos = new ArrayList<>();
    List<LibroAutorDTO> libroAutores = new ArrayList<>();
    AutorDAO autorDAO;
    LibroDAO libroDAO;
    UsuarioDAO usuarioDAO;
    PrestamoDAO prestamoDAO;
    LibroAutorDAO libroAutorDAO;


    //Constructor e inicialización de los DAOS
    public Sincronizacion(AutorDAO autorDAO, LibroDAO libroDAO, UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO, LibroAutorDAO libroAutorDAO) {
        this.autorDAO = autorDAO;
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
        this.libroAutorDAO = libroAutorDAO;
    }


    //Clase sincronizar que actualiza las listas
    public void sincronizar() {
        autores = autorDAO.leerAutores();
        libros = libroDAO.leerLibros();
        usuarios = usuarioDAO.leerUsuarios();
        prestamos = prestamoDAO.leerPrestamos();
        libroAutores = libroAutorDAO.leerLibroAutor();
    }

    //Método para leer la lista de los autores
    public void leerAutores() {
        System.out.println("-AUTORES-");
        for (AutorDTO autor : autores) {
            System.out.println(autor);
        }
    }

    //Método para leer la lista de los libros
    public void leerLibros() {
        System.out.println("-LIBROS-");

        for (LibroDTO libro : libros) {

            boolean autorEncontrado = false;
            //Si tiene autor asociado muestra la información del libro y el nombre del autor
            for (LibroAutorDTO libroautor : libroAutores) {
                if (libroautor.getIdLibro() == libro.getId()) {
                    for (AutorDTO autor : autores) {
                        if (libroautor.getIdAutor() == autor.getId()) {
                            System.out.println(libro + " - Autor: " + autor.getAutor());
                            autorEncontrado = true;
                        }
                    }
                }
            }
            //Si no tiene autor asociado solo muestra la información del libro
            if (!autorEncontrado) {
                System.out.println(libro);
            }
        }
}

 //Método para leer la lista de usuarios
    public void leerUsuarios(){
        System.out.println("-USUARIOS-");
        for (UsuarioDTO usuario : usuarios){
            System.out.println(usuario);
        }
    }

    //Método para leer la lista de préstamos
    public void leerPrestamos(){
        System.out.println("-PRÉSTAMOS-");
        for (PrestamoDTO prestamo : prestamos){
            System.out.println(prestamo);
        }
    }

//    public void leerLibroAutor(){
//        System.out.println("-LIBRO AUTOR-");
//        for (LibroAutorDTO libroAutor : libroAutores){
//            System.out.println(libroAutor);
//        }
//    }
}
