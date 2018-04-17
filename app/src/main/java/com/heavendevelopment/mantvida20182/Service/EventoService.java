package com.heavendevelopment.mantvida20182.Service;

import android.content.Context;

import com.heavendevelopment.mantvida20182.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida20182.Dominio.Evento;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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

    public Evento getEventoDoDia(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int diaHoje = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mesHoje = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;

        Evento evento = databaseAccess.getEventoDoDia(diaHoje,mesHoje);
        databaseAccess.close();

        return evento;

    }


}
