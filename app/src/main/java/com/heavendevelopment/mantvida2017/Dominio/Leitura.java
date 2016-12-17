package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 05/12/2016.
 */

public class Leitura {

    private int id;
    private int dia;
    private int mes;
    private int ano;
    private String [] referencias;
    private String [] versiculos;

    public Leitura() {
    }

    public Leitura(int mes, int ano, String[] referencias, String[] versiculos, int dia) {
        this.mes = mes;
        this.ano = ano;
        this.referencias = referencias;
        this.versiculos = versiculos;
        this.dia = dia;
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

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public String[] getReferencias() {
        return referencias;
    }
    public void setReferencias(String[] referencias) {
        this.referencias = referencias;
    }

    public String[] getVersiculos() {
        return versiculos;
    }
    public void setVersiculos(String[] versiculos) {
        this.versiculos = versiculos;
    }


}
