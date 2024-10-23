import java.util.ArrayList;
import java.util.List;

public class Sincronizacion {
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

    public Sincronizacion(AutorDAO autorDAO, LibroDAO libroDAO, UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO, LibroAutorDAO libroAutorDAO) {
        this.autorDAO = autorDAO;
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
        this.libroAutorDAO = libroAutorDAO;
    }

    public void sincronizar() {
        autores = autorDAO.leerAutores();
        libros = libroDAO.leerLibros();
        usuarios = usuarioDAO.leerUsuarios();
        prestamos = prestamoDAO.leerPrestamos();
        libroAutores = libroAutorDAO.leerLibroAutor();
    }

    public void leerAutores() {
        System.out.println("-AUTORES-");
        for (AutorDTO autor : autores) {
            System.out.println(autor);
        }
    }

    public void leerLibros() {
        System.out.println("-LIBROS-");

        for (LibroDTO libro : libros) {
            boolean autorEncontrado = false;
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
            if (!autorEncontrado) {
                System.out.println(libro);
            }
        }
}
    public void leerUsuarios(){
        System.out.println("-USUARIOS-");
        for (UsuarioDTO usuario : usuarios){
            System.out.println(usuario);
        }
    }
    public void leerPrestamos(){
        System.out.println("-PRÉSTAMOS-");
        for (PrestamoDTO prestamo : prestamos){
            System.out.println(prestamo);
        }
    }
    public void leerLibroAutor(){
        System.out.println("-LIBRO AUTOR-");
        for (LibroAutorDTO libroAutor : libroAutores){
            System.out.println(libroAutor);
        }
    }
}
