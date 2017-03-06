package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.CabecalhoAjuda;
import com.heavendevelopment.mantvida2017.Dominio.ItemCabecalhoAjuda;

import java.util.List;

/**
 * Created by Yuri on 20/02/2017.
 */

public class AjudaService {

    private Context context;

    public AjudaService(Context context){

        this.context = context;

    }


    public List<CabecalhoAjuda> getCabecalhos(){

        List<CabecalhoAjuda> listCabecalhos;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        listCabecalhos = databaseAccess.getCabecalhosAjuda();

        databaseAccess.close();

        return listCabecalhos;
    }

    public List<ItemCabecalhoAjuda> getItensPorCabecalho(int idCabecalho){

        List<ItemCabecalhoAjuda> listItensCabecalhos;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        listItensCabecalhos = databaseAccess.getItensPorCabecalhoAjuda(idCabecalho);

        databaseAccess.close();

        return listItensCabecalhos;

    }
}
