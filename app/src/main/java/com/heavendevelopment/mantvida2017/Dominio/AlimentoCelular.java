package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 05/12/2016.
 */

public class AlimentoCelular {

    private int id;
    private int numero;
    private String nome;
    private String data;
    private String link;

    public AlimentoCelular() {
    }

    public AlimentoCelular(String nome, String data, String link) {
        this.nome = nome;
        this.data = data;
        this.link = link;
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

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
