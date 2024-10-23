public class LibroDTO {
    //Variables id titulo e isbn
    int id;
    String titulo;
    String isbn;

    //Constructor
    public LibroDTO(String titulo, String isbn) {
        this.titulo = titulo;
        this.isbn = isbn;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    //To String
    @Override
    public String toString() {
        return  id + " - "+ titulo + "  " + isbn;
    }
}
