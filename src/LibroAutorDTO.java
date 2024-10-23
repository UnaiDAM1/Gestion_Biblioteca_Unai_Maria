public class LibroAutorDTO {
    //Variables idAutor e idLibro
    int idLibro;
    int idAutor;

    //Constructor
    public LibroAutorDTO(int idLibro, int idAutor){
        this.idAutor = idAutor;
        this.idLibro = idLibro;
    }

    //Getters Y Setters
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


    //To String
    @Override
    public String toString() {
        return  idLibro + " - " +
                 idAutor;
    }
}
