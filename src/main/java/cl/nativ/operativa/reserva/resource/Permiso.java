package cl.nativ.operativa.reserva.resource;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class Permiso {
    private String nombre;
    private  String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Permiso(String nombre)
    {
        this.nombre = nombre;
    }

    public Permiso() {}

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
