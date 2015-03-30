package cl.nativ.operativa.reserva.authorization.model;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class Permiso {
    private String nombre;

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
