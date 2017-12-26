package com.heavendevelopment.mantvida2018.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2018.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2018.Dominio.EstadoLeitura;
import com.heavendevelopment.mantvida2018.Dominio.Leitura;
import com.heavendevelopment.mantvida2018.Dominio.Versículo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 21/12/2016.
 */

public class LeituraService {

    private Context context;
    private int versionBible;

    public LeituraService(Context context) {
        this.context = context;
    }

    public LeituraService(Context context, int versionBible) {

        this.context = context;
        this.versionBible = versionBible;
    }

    public ArrayList<Leitura> getReadingOfDay(int day, int month){

        ArrayList<Leitura> listLeitura = new ArrayList<>();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        listLeitura = databaseAccess.getReadingOfDay(day,month);
        databaseAccess.close();

        return listLeitura;

    }

    public String getRefReadingOfDay(int day, int month){

        String referenciaLeitura;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        referenciaLeitura = databaseAccess.getRefReadingOfDay(day,month);
        databaseAccess.close();

        return referenciaLeitura;

    }

    public ArrayList<Versículo> getLeituraDiaria(int id_livro, int capitulo){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context, versionBible);
        databaseAccess.open();

        ArrayList <Versículo> listaVersiculos = databaseAccess.getLeitura(id_livro, capitulo);
        databaseAccess.close();

        return listaVersiculos;
    }

    public void setEstadoLeituraDecorator(int mes,int dia, int estado){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        databaseAccess.setEstadoLeitura(mes,dia,estado);
        databaseAccess.close();


    }

    public EstadoLeitura getEstadoLeituraUmDia(int mes, int dia){


        EstadoLeitura estadoLeitura;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        estadoLeitura = databaseAccess.getLeituraUmDia(mes,dia);
        databaseAccess.close();

        return estadoLeitura;

    }

    public List<EstadoLeitura> getEstadoLeituraDecorator(int mes, int dia){

        List<EstadoLeitura> listEstadoLeituras = new ArrayList<>();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        listEstadoLeituras = databaseAccess.getLeiturasAteHoje(mes,dia);
        databaseAccess.close();

        return listEstadoLeituras;
    }
}
