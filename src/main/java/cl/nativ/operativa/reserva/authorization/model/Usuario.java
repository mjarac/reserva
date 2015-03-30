package cl.nativ.operativa.reserva.authorization.model;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class Usuario {
    private String username;
    private String rut;

    public Usuario() {}

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getRut()
    {
        return this.rut;
    }

    public void setRut(String rut)
    {
        this.rut = rut;
    }
}
