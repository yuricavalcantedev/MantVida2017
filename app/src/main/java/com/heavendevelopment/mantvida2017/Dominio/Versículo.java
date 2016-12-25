package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 21/12/2016.
 */

public class Versículo {

    private int id;
    private int numVersiculo;
    private int capitulo;
    private int idLivro;
    private String texto;

    public Versículo() {
        super();
    }

    public Versículo(int id, int num, int capitulo, int idLivro, String texto) {
        super();
        this.id = id;
        this.numVersiculo = num;
        this.capitulo = capitulo;
        this.idLivro = idLivro;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumVersiculo() {
        return numVersiculo;
    }

    public void setNumVersiculo(int numVersiculo) {
        this.numVersiculo = numVersiculo;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
