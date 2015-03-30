package cl.nativ.operativa.reserva.authorization.model;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class Privilegio {
    private String codigo;
    private String grupoId;
    private String nombreFacultad;
    private String tipo;
    private ArrayList<Permiso> permisos = new ArrayList();

    public Privilegio(String codigo, String grupoId, ArrayList<Permiso> permisos){
        this.codigo = codigo;
        this.grupoId = grupoId;
        this.permisos = permisos;
    }

    public Privilegio() {}

    public String getCodigo(){
        return this.codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getGrupoId(){
        return this.grupoId;
    }

    public void setGrupoId(String grupoId){
        this.grupoId = grupoId;
    }

    public ArrayList<Permiso> getPermisos(){
        return this.permisos;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setPermisos(ArrayList<Permiso> permisos){
        this.permisos = permisos;
    }

    public String getNombreFacultad(){
        return this.nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad){
        this.nombreFacultad = nombreFacultad;
    }

    public String toString(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(this);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
