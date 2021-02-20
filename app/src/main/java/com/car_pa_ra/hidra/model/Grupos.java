package com.car_pa_ra.hidra.model;

public class Grupos {

    private String ayuSoc;
    private int imagen;
    private String titulo;
    private String descripcion;
    private String tipo;

    public Grupos() {
    }

    public Grupos(String ayuSoc, int imagen, String titulo, String descripcion, String tipo) {
        this.ayuSoc = ayuSoc;
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getAyuSoc() {
        return ayuSoc;
    }

    public int getImagen() {
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


}
