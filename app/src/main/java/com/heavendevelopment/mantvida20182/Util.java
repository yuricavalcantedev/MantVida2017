package com.heavendevelopment.mantvida20182;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yuri on 05/12/2016.
 */

public class Util{

    Context context;

    public Util(Context context){

        super();
        this.context = context;
    }

    public void toast(String mensagem){

        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

}
