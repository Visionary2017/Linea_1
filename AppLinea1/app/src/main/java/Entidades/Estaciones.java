package Entidades;

/**
 * Created by JuanCR on 02/10/2017.
 */

public class Estaciones {

    private int id_estaciones;
    private String nombre_estacion;
    private String descripcion;
    private String direccion;
    private String distrito;
    private String latitud;
    private String longitud;


    public Estaciones() {
    }

    public Estaciones(int id_estaciones, String nombre_estacion, String descripcion, String direccion, String distrito, String latitud, String longitud) {
        this.id_estaciones = id_estaciones;
        this.nombre_estacion = nombre_estacion;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.distrito = distrito;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId_estaciones() {
        return id_estaciones;
    }

    public void setId_estaciones(int id_estaciones) {
        this.id_estaciones = id_estaciones;
    }

    public String getNombre_estacion() {
        return nombre_estacion;
    }

    public void setNombre_estacion(String nombre_estacion) {
        this.nombre_estacion = nombre_estacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
