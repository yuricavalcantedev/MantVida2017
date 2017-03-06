package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.MarcadorMaps;

import java.util.List;

/**
 * Created by Yuri on 19/02/2017.
 */

public class MarcadorMapsService {


    private Context context;

    public MarcadorMapsService(Context context){
        super();
        this.context = context;
    }


    public boolean adicionarMarcador(MarcadorMaps marcadorMaps){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean cadastrado = databaseAccess.adicionarMarcadorMaps(marcadorMaps);

        databaseAccess.close();

        return cadastrado;

    }

    public List<MarcadorMaps> getMarcadoresMaps(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        List<MarcadorMaps> listMarcadores = databaseAccess.getMarcadoresMaps();

        databaseAccess.close();

        return listMarcadores;

    }

    public boolean atualizarMarcador(String nomeMarcador, double latitude, double longitude){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean eventoAtualizado = databaseAccess.atualizarMarcadorMaps(nomeMarcador, latitude, longitude);

        databaseAccess.close();

        return eventoAtualizado;

    }

}
