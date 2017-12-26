package com.heavendevelopment.mantvida2018.Dominio;

/**
 * Created by Yuri on 22/11/2016.
 */

public class Devocional {

    private int id;
    private String titulo;
    private String dataCriacao;
    private String textoChave;
    private String mensagemDeDeus;

    public Devocional(){

        super();
    }

    public Devocional(String titulo, String dataCriacao, String textoChave, String mensagemDeDeus) {

        super();
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.textoChave = textoChave;
        this.mensagemDeDeus = mensagemDeDeus;
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

    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTextoChave() {
        return textoChave;
    }
    public void setTextoChave(String textoChave) {
        this.textoChave = textoChave;
    }

    public String getMensagemDeDeus() {
        return mensagemDeDeus;
    }
    public void setMensagemDeDeus(String mensagemDeDeus) {
        this.mensagemDeDeus = mensagemDeDeus;
    }

}
