package com.heavendevelopment.mantvida2017.Service;

import android.content.Context;

import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.AlimentoCelular;


import java.util.List;

/**
 * Created by Yuri on 25/01/2017.
 */

public class AlimentoCelularService {

    private Context context;

    public AlimentoCelularService(Context context){
        this.context = context;
    }

    public boolean cadastrarAlimento(AlimentoCelular alimentoCelular){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();


        boolean cadastrado = databaseAccess.cadastrarAlimentoCelular(alimentoCelular);
        //cadastrar

        databaseAccess.close();

        return cadastrado;
    }

    public List<AlimentoCelular> getAlimentos(){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        List<AlimentoCelular> listaAlimentos = databaseAccess.getAlimentosCelulares();

        databaseAccess.close();
        return listaAlimentos;

    }

    public String getLinkAlimentoByNumero(int numero){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        databaseAccess.open();

        AlimentoCelular alimento = databaseAccess.getAlimentoCelularByNumero(numero);

        String linkAlimento = alimento.getLink();

        databaseAccess.close();
        return linkAlimento;

    }
}
