package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Leitura;
import com.heavendevelopment.mantvida2017.Dominio.Versículo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

}
