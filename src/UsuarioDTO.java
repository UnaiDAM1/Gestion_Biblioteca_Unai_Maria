public class UsuarioDTO {
    //Variables id y nombre
    int id;
    String nombre;


    //Constructor
    public UsuarioDTO(String nombre) {
        this.nombre = nombre;
    }


    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //To String
    @Override
    public String toString() {
        return  id + " - " + nombre;
    }
}
