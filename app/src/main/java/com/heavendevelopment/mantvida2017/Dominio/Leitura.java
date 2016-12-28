package com.heavendevelopment.mantvida2017.Dominio;

import java.util.ArrayList;

/**
 * Created by Yuri on 21/12/2016.
 */

public class Leitura {

    private int id;
    private int dia;
    private int mes;
    private String titulo;
    private int idLivro;
    private int capitulo;
    private ArrayList<Versículo> versiculos;

    public Leitura() {
    }

    public Leitura(int id, int dia, int mes, String titulo, int idLivro, int capitulo, ArrayList<Versículo> versiculos) {
        this.id = id;
        this.dia = dia;
        this.mes = mes;
        this.titulo = titulo;
        this.idLivro = idLivro;
        this.capitulo = capitulo;
        this.versiculos = versiculos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public ArrayList<Versículo> getVersiculos() {
        return versiculos;
    }

    public void setVersiculos(ArrayList<Versículo> versiculos) {
        this.versiculos = versiculos;
    }
}
