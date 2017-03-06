package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 30/11/2016.
 */

public class MarcadorMaps {

    private int id;
    private String nome;
    private double latitude;
    private double longitude;

    public MarcadorMaps(){
        super();
    }

    public MarcadorMaps(String nome, double latitude, double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
