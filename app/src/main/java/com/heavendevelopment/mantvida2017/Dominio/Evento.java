package com.heavendevelopment.mantvida2017.Dominio;

/**
 * Created by Yuri on 05/12/2016.
 */

public class Evento {

    private int id;
    private String nome;
    private String data;
    private String descricao;
    private String texto;
    private byte[] imagem;
    private String caminho;

    public Evento() {
    }

    public Evento(String nome, String data, String descricao, String texto, byte[] imagem, String caminho) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.texto = texto;
        this.imagem = imagem;
        this.caminho = caminho;
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

    public String getCaminho() {
        return caminho;
    }
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}
