public class LibroDTO {
    int id;
    String titulo;
    String isbn;

    public LibroDTO(String titulo, String isbn) {
        this.titulo = titulo;
        this.isbn = isbn;
    }

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

    @Override
    public String toString() {
        return  id + " - "+ titulo + "  " + isbn;
    }
}
