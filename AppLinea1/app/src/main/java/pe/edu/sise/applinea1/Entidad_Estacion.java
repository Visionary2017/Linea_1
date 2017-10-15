package pe.edu.sise.applinea1;

/**
 * Created by Angel Mamani on 6/05/2017.
 */

public class Entidad_Estacion {

    public int id_estacion;
    public  String descripcion;
    public  String descripcionGeneral;
    public  String direccion;
    public String icon;
    private String latitud;
    private String longitud;
    private String descripcionestacion;


    public Entidad_Estacion() {
    }

    public Entidad_Estacion(int id_categoria, String descripcion, String descripcionGeneral, String direccion, String icon, String latitud, String longitud, String descripcionestacion) {
        this.id_estacion = id_categoria;
        this.descripcion = descripcion;
        this.descripcionGeneral = descripcionGeneral;
        this.direccion = direccion;
        this.icon = icon;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcionestacion = descripcionestacion;
    }

    public int getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(int id_estacion) {
        this.id_estacion = id_estacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getDescripcionestacion() {
        return descripcionestacion;
    }

    public void setDescripcionestacion(String descripcionestacion) {
        this.descripcionestacion = descripcionestacion;
    }
}
