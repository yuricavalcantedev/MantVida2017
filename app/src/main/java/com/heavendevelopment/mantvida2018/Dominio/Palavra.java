package com.heavendevelopment.mantvida2018.Dominio;

/**
 * Created by Yuri on 08/12/2016.
 */

public class Palavra {

    private int id;
    private String titulo;
    private String autor;
    private String data;
    private String mensagem;
    private String referenciaBiblica;


    public Palavra() {
    }

    public Palavra(int id, String titulo, String autor, String data, String mensagem, String referenciaBiblica) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.data = data;
        this.mensagem = mensagem;
        this.referenciaBiblica = referenciaBiblica;
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

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getReferenciaBiblica() {
        return referenciaBiblica;
    }
    public void setReferenciaBiblica(String referenciaBiblica) {
        this.referenciaBiblica = referenciaBiblica;
    }
}
