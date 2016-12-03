package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 30/11/2016.
 */

public class MarcadorIgrejaMaps {

    private String nome;
    private String endereco;
    private String latitude;
    private String longitude;

    public MarcadorIgrejaMaps(){
        super();
    }

    public MarcadorIgrejaMaps(String nome, String endereco, String latitude, String longitude) {
        this.nome = nome;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
