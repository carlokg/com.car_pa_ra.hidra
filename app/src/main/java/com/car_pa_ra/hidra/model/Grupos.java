package com.car_pa_ra.hidra.model;

public class Grupos {

    private String ayuSoc;
    private String imagen;
    private String titulo;
    private String descripcion;
    private String tipo;
    private  String ubicacion;

    public Grupos() {
    }

    public Grupos(String ayuSoc, String imagen, String titulo, String descripcion, String tipo, String ubicacion) {
        this.ayuSoc = ayuSoc;
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public String getAyuSoc() {
        return ayuSoc;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
