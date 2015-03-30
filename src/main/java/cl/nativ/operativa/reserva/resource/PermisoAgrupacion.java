package cl.nativ.operativa.reserva.resource;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class PermisoAgrupacion {
    private String dominio;
    private String nombrePermiso;
    private String moduleKey;
    private String codigo;

    public PermisoAgrupacion() {}

    public String getCodigo()
    {
        return this.codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDominio()
    {
        return this.dominio;
    }

    public void setDominio(String dominio)
    {
        this.dominio = dominio;
    }

    public String getNombrePermiso()
    {
        return this.nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso)
    {
        this.nombrePermiso = nombrePermiso;
    }

    public String getModuleKey()
    {
        return this.moduleKey;
    }

    public void setModuleKey(String moduleKey)
    {
        this.moduleKey = moduleKey;
    }
}
