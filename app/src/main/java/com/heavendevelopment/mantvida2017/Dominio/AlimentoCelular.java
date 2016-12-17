package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 05/12/2016.
 */

public class AlimentoCelular {

    private int id;
    private String nome;
    private String data;
    private String versiculoChave;
    private String texto;
    private byte[] imagem;

    public AlimentoCelular() {
    }

    public AlimentoCelular(String nome, String data, String versiculoChave, String texto, byte[] imagem) {
        this.nome = nome;
        this.data = data;
        this.versiculoChave = versiculoChave;
        this.texto = texto;
        this.imagem = imagem;
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

    public String getVersiculoChave() {
        return versiculoChave;
    }
    public void setVersiculoChave(String versiculoChave) {
        this.versiculoChave = versiculoChave;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public byte[] getImagem() {
        return imagem;
    }
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
