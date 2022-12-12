package com.example.examen.Modelos;

public class Data {
    private final String nombre;
    private final String numero;
    //private final String urlImg;

    public Data(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
        //this.urlImg = urlImg;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    //public String getUrlImg() {
       // return urlImg;
    //}
}
