package com.heavendevelopment.mantvida2017.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.heavendevelopment.mantvida2017.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecuperarInfoActivity extends AppCompatActivity {

    @BindView(R.id.et_data_nasc_recuperar_info)
    EditText etRecuperarInfo;

    @BindView(R.id.bt_recuperar_info)
    Button btRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_info);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_recuperar_info)
    public void recuperarInfo(){

        //vai no service
        //se a data for igual retorna o login e a senha num alertDialog.

    }
}
