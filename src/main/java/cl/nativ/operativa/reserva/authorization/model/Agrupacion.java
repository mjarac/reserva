package cl.nativ.operativa.reserva.authorization.model;

public class Agrupacion{

    private String codigo;
    private String grupoId;
    private String nombreFacultad;
    private String tipo;

    public Agrupacion(String codigo, String grupoId, String nombreFacultad, String tipo)
    {
        this.codigo = codigo;
        this.grupoId = grupoId;
        this.nombreFacultad = nombreFacultad;
        this.tipo = tipo;
    }

    public String getCodigo()
    {
        return this.codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getGrupoId()
    {
        return this.grupoId;
    }

    public void setGrupoId(String grupoId)
    {
        this.grupoId = grupoId;
    }

    public String getNombreFacultad()
    {
        return this.nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad)
    {
        this.nombreFacultad = nombreFacultad;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String toString()
    {
        return this.nombreFacultad;
    }
}