package com.heavendevelopment.mantvida2018.Dominio;

/**
 * Created by Yuri on 05/12/2016.
 */

public class Evento {

    private double id;
    private String nome;
    private String data;
    private String descricao;
    private String dia;
    private int mes;

    public Evento() {
    }

    public Evento(String nome, String data, String descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;

    }

    public double getId() {
        return id;
    }
    public void setId(double id) {
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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
}
