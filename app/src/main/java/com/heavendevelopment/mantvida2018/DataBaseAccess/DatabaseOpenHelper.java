package com.heavendevelopment.mantvida2018.DataBaseAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by Alysson on 17/11/2015.
 */


public class DatabaseOpenHelper extends SQLiteAssetHelper {

    //a nova versão que vai pra ser publicada é a 12

    private static final String BD_NAME = "db_mantvida_2018";
    private static final int BD_VERSION = 1 ;
    private Context context;

    public DatabaseOpenHelper(Context ctx) {
        super(ctx, BD_NAME, null, BD_VERSION);
        context = ctx;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //NÃO APAGAR AS VERSÕES ANTERIORES DO BANCO!!!

        try{

            switch(newVersion) {

            }

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * @param db db of application
     **/

}