public class UsuarioDTO {
    int id;
    String nombre;

    public UsuarioDTO(String nombre) {
        this.nombre = nombre;
    }

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

    @Override
    public String toString() {
        return "UsuarioDTO: " +
                "ID = " + id +
                ", Nombre = '" + nombre;
    }
}
