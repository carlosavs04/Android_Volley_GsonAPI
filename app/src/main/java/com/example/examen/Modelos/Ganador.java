package com.example.examen.Modelos;

import java.util.List;

public class Ganador {
    private final boolean status;
    private final List<Data> data;

    public Ganador(boolean status, List<Data> data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public List<Data> getData() {
        return data;
    }
}
