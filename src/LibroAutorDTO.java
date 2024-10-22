public class LibroAutorDTO {
    int idLibro;
    int idAutor;
    public LibroAutorDTO(int idLibro, int idAutor){
        this.idAutor = idAutor;
        this.idLibro = idLibro;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}
