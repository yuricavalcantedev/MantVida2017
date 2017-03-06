package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 20/02/2017.
 */

public class CabecalhoAjuda {

    private int id;
    private String titulo;

    public CabecalhoAjuda(){}

    public CabecalhoAjuda(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
