package com.heavendevelopment.mantvida2018.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2018.DataBaseAccess.DatabaseAccess;

/**
 * Created by Yuri on 02/12/2016.
 */

public class UsuarioService {

    private Context context;

    public UsuarioService(Context context){
        super();
        this.context = context;
    }


    public String getNomeUsuario(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        String nome = databaseAccess.getUserName();
        databaseAccess.close();

        return nome;
    }

}
