package com.example.examen.Modelos;

public class Data {
    private final String Nombre;
    private final String Cantidad;
    private final String urlImg;

    public Data(String nombre, String cantidad, String urlImg) {
        Nombre = nombre;
        Cantidad = cantidad;
        this.urlImg = urlImg;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public String getUrlImg() {
        return urlImg;
    }
}
