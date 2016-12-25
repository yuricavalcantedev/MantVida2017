package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Versículo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Yuri on 21/12/2016.
 */

public class LeituraService {

    private Context context;

    public LeituraService(Context context) {
        this.context = context;
    }

    public String getRefReadingOfDay(int day, int month){

        String referenceBiblical;

        //pegar do sharedPreferences
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        referenceBiblical = databaseAccess.getRefReadingOfDay(day,month);
        databaseAccess.close();

        return referenceBiblical;

    }

    public ArrayList<Versículo> getLeituraDiaria(int id_livro, int capitulo){


        //pegar do sharedPreferences
        int versaoBiblia = 1 ;


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        ArrayList <Versículo> listaVersiculos = databaseAccess.getReadingOfDay(id_livro, capitulo);
        databaseAccess.close();

        return listaVersiculos;
    }

}
