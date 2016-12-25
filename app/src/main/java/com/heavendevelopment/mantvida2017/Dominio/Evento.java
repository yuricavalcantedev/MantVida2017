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

    //0 - não realizado, 1- em andamento, 2 - próximo evento, 3 - realizado
    private int estadoEvento;

    public Evento() {
    }

    public Evento(String nome, String data, String descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.estadoEvento = 0;
        this.setEstadoEvento(verificaEstadoEvento());

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

    public int getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(int realizado) { this.estadoEvento = realizado; }

    private int verificaEstadoEvento(){

        //0 - não realizado, 1- em andamento, 2 - próximo evento, 3 - realizado

        int estadoEvento = 0;
        int dia = 0, mes;
        int diaComposto1 = 0,diaComposto2 = 0;

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int diaAtual = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
        int mesAtual = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;

        diaAtual = 30;
        mesAtual = 6;

        String []arrayData = data.split(".");

        mes = Integer.parseInt(arrayData[1]);

        if(arrayData[0].contains("a")){

            String [] arrayDataAux = arrayData[0].split("a");
            diaComposto1 = Integer.parseInt(arrayDataAux[0]);
            diaComposto2 = Integer.parseInt(arrayDataAux[1]);

        }else
            dia = Integer.parseInt(arrayData[0]);

        //TODO : Pensar como fazer a parte do próximo evento.

        //se entrar aqui é porque o evento é de um dia só.
        if(diaComposto1 == 0){
            if(dia == diaAtual && mes == mesAtual)
                estadoEvento = 1;
            else if(mes <= mesAtual && dia < diaAtual)
                estadoEvento = 3;
            else if(mes >= mesAtual && dia > diaAtual)
                estadoEvento = 0;
        }else{

            if(mes == mesAtual && diaAtual >= diaComposto1 && diaAtual <= diaComposto2)
                estadoEvento = 1;
            else if(mes <= mesAtual && diaAtual > diaComposto2)
                estadoEvento = 3;
            else if(mes >= mesAtual && diaAtual < diaComposto1)
                estadoEvento = 0;
        }

        return estadoEvento;
    }
}
