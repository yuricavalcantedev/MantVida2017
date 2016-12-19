package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Evento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 17/12/2016.
 */

public class EventoService {

    private Context context;

    public  EventoService(Context context){
        super();
        this.context = context;
    }

    public ArrayList<Evento> getEventos(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        ArrayList<Evento> listEventos = databaseAccess.getEventos();

        databaseAccess.close();

        return listEventos;

    }
}
