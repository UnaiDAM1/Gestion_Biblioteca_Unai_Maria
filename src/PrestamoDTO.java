import java.time.LocalDate;
public class PrestamoDTO {
    int id;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    int usuarioId;
    int libroId;

    public PrestamoDTO(LocalDate fechaInicio, LocalDate fechaFin, int usuarioId, int libroId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    @Override
    public String toString() {
        return "PrestamoDTO: " +
                "ID = " + id +
                ", Fecha de Inicio = " + fechaInicio +
                ", Fecha de Fin = " + fechaFin +
                ", ID Usuario = " + usuarioId +
                ", ID Libro = " + libroId;
    }
}
