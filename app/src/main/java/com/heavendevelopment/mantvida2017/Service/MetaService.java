package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Meta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 22/11/2016.
 */

public class MetaService {

    private Context context;

    public MetaService(Context context){
        super();
        this.context = context;
    }

    public boolean criarMeta(Meta meta){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean cadastrado = databaseAccess.cadastrarMeta(meta);

        databaseAccess.close();

        return cadastrado;
    }

    public List<Meta> getMetas(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        List<Meta> listaMetas = databaseAccess.getMetas();

        databaseAccess.close();
        return listaMetas;

    }

    public ArrayList<Meta> getMetasByCategory(int idCategoria){

        //1 - Família, 2 - Ministério, 3 - Formação, 4 - Restituição, 5 - Finanças

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        ArrayList<Meta> listaMetas = databaseAccess.getMetasByCategory(idCategoria);

        databaseAccess.close();
        return listaMetas;
    }

    public Meta getMetaById(int idMeta){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        Meta meta = databaseAccess.getMetaById(idMeta);

        databaseAccess.close();

        return meta;
    }

    public boolean atualizarMeta(Meta meta){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean retornoUpdate = databaseAccess.atualizarMeta(meta);

        databaseAccess.close();
        return retornoUpdate;

    }

    public boolean deletarMeta(int idMeta){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        boolean deletado = databaseAccess.deletarMeta(idMeta);

        databaseAccess.close();

        return deletado;
    }

}
