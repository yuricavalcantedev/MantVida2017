package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 21/12/2016.
 */

public class LeituraDiaria {

    private int dia;
    private int mes;
    private String tituloleitura;
    private int idLivro;
    private int capitulo;

    public LeituraDiaria(){}

    public LeituraDiaria(int dia, int mes, String tituloleitura, int idLivro, int capitulo) {
        this.dia = dia;
        this.mes = mes;
        this.tituloleitura = tituloleitura;
        this.idLivro = idLivro;
        this.capitulo = capitulo;
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

    public String getTituloleitura() {
        return tituloleitura;
    }

    public void setTituloleitura(String tituloleitura) {
        this.tituloleitura = tituloleitura;
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
}
