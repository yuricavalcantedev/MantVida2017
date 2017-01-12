package com.heavendevelopment.mantvida2017.Dominio;

import java.util.GregorianCalendar;

/**
 * Created by Yuri on 05/12/2016.
 */

public class Evento {

    private int id;
    private String nome;
    private String data;
    private String descricao;


    public Evento() {
    }

    public Evento(String nome, String data, String descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;

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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
