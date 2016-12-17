package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 08/12/2016.
 */

public class Palavra {

    private int id;
    private String titulo;
    private String autor;
    private String data;
    private String mensagem;


    public Palavra() {
    }

    public Palavra(int id, String titulo, String autor, String data, String mensagem) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.data = data;
        this.mensagem = mensagem;
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
}
