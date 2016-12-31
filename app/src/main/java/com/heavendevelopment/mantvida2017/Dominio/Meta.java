package com.heavendevelopment.mantvida2017.Dominio;


/**
 * Created by Yuri on 22/11/2016.
 */

public class Meta {

    private int id;
    private String titulo;
    private String como;
    private String objetivo;

    //1 - Família, 2 - Ministério, 3 - Formação, 4 - Restituição, 5 - Finanças
    private int idCategoria;
    private String dataInicio;
    private String dataCriacao;
    private String dataConclusao;
    private int realizada;

    public Meta(){

        super();
    }

    public Meta(String titulo, String como, String objetivo, int idCategoria, String dataCriacao, String dataInicio, String dataConclusao) {

        super();
        this.titulo = titulo;
        this.como = como;
        this.objetivo = objetivo;
        this.idCategoria = idCategoria;
        this.dataCriacao = dataCriacao;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.realizada = 0;
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

    public String getComo() {
        return como;
    }
    public void setComo(String como) {
        this.como = como;
    }

    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }
    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public void setRealizada(int realizada) {
        this.realizada = realizada;
    }
    public int getRealizada() {
        return realizada;
    }
}
