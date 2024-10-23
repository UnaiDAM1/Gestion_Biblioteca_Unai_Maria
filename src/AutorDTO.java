public class AutorDTO {
    //Variables id y autor
    int id;
    String autor;

    //Contrsuctor
    public AutorDTO(String autor) {
        this.autor = autor;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    //To String
    @Override
    public String toString() {
        return  id + " - "+ autor;
    }
}
