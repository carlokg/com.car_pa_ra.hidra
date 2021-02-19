package com.car_pa_ra.hidra.model;

public class Grupos {

    private int imagen;
    private String titulo;
    private String descripcion;
    private String tipo;

    public Grupos(int imagen, String titulo, String descripcion, String tipo) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
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
