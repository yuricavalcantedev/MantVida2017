package com.heavendevelopment.mantvida2018.Dominio;


/**
 * Created by Yuri on 20/02/2017.
 */

public class ItemCabecalhoAjuda {

    private int id;
    private int idCabecalho;
    private String titulo;

    public ItemCabecalhoAjuda(){}

    public ItemCabecalhoAjuda(int id, int idCabecalho, String titulo) {
        this.id = id;
        this.idCabecalho = idCabecalho;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCabecalho() {
        return idCabecalho;
    }
    public void setIdCabecalho(int idCabecalho) {
        this.idCabecalho = idCabecalho;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
