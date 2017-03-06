package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;
import android.provider.ContactsContract;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.Dominio.Usuario;

import java.util.List;

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
