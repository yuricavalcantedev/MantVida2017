package com.heavendevelopment.mantvida2017.Dominio;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

/**
 * Created by Yuri on 22/11/2016.
 */

public class Meta extends Model{

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "como")
    private String como;

    @Column(name = "objetivo")
    private String objetivo;

    @Column(name = "idCategoria")
    private int idCategoria;

    @Column(name = "dataInicio")
    private String dataInicio;

    @Column(name = "dataTermino")
    private String dataTermino;

    public Meta(){}

    public Meta(String titulo, String como, String objetivo, int idCategoria, String dataInicio, String dataTermino) {
        this.titulo = titulo;
        this.como = como;
        this.objetivo = objetivo;
        this.idCategoria = idCategoria;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
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

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }


}
