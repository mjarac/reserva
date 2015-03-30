package cl.nativ.operativa.reserva.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by marcos.jara on 26-03-2015.
 */
/*@Entity
@Table(name = "aut_permiso")*/
public class Permiso {
    @Id
    private long id;

    @Column(name = "activo")
    private int activo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "modulo_id")
    private long modulo_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getModulo_id() {
        return modulo_id;
    }

    public void setModulo_id(long modulo_id) {
        this.modulo_id = modulo_id;
    }
}
