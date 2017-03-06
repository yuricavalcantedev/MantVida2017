package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;
import android.provider.ContactsContract;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Evento;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    public boolean atualizarEvento(Evento evento){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean eventoAtualizado = databaseAccess.atualizarEvento(evento);

        databaseAccess.close();

        return eventoAtualizado;

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
