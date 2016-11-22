package com.heavendevelopment.mantvida2017.Dominio;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by Yuri on 22/11/2016.
 */

//a data é a data que o devocional foi criado

public class Devocional extends Model {

    @Column(name = "parasha")
    private String parasha;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data")
    private String data;

    @Column(name = "texto_chave")
    private String textoChave;

    @Column(name = "mensagem_de_Deus")
    private String mensagemDeDeus;

    public Devocional(){}

    public Devocional(String parasha, String titulo, String data, String textoChave, String mensagemDeDeus) {
        this.parasha = parasha;
        this.titulo = titulo;
        this.data = data;
        this.textoChave = textoChave;
        this.mensagemDeDeus = mensagemDeDeus;
    }

    public String getParasha() {
        return parasha;
    }

    public void setParasha(String parasha) {
        this.parasha = parasha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
