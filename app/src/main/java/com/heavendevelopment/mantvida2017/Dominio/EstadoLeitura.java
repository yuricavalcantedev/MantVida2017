package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 17/02/2017.
 */

public class EstadoLeitura {

    private int id;
    private int mes;
    private int dia;
    private int estado;

    public  EstadoLeitura(){}

    public EstadoLeitura(int mes, int dia, int estado){

        this.id = 0;
        this.mes = mes;
        this.dia = dia;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
