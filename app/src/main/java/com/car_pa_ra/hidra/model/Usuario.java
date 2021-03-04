package com.car_pa_ra.hidra.model;


public class Usuario {

    private String img;
    private String uId;
    private String nombre;
    private String email;
    private String dir;
    private String desc;

    public Usuario() {
    }

    public Usuario(String img, String uId, String nombre, String email, String dir, String desc) {
        this.img = img;
        this.uId = uId;
        this.nombre = nombre;
        this.email = email;
        this.dir = dir;
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public String getuId() {
        return uId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDir() {
        return dir;
    }

    public String getDesc() {
        return desc;
    }

}
