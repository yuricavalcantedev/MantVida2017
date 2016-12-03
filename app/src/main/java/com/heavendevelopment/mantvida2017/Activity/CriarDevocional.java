package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.DevocionalService;
import com.heavendevelopment.mantvida2017.Service.MetaService;

import java.util.GregorianCalendar;

public class CriarDevocional extends AppCompatActivity {

        Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_devocional);

        context = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novo_devocional_meta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_menu_save) {

            criarDevocional();
        }else if(id == R.id.ic_menu_share){

            compartilharDevocional();
        }

        return super.onOptionsItemSelected(item);
    }

    private void criarDevocional(){

        TextView tvParasha = (TextView) findViewById(R.id.tv_parasha_criar_devocional);

        TextInputLayout tilTitulo = (TextInputLayout) findViewById(R.id.til_titulo_criar_devocional);
        TextInputLayout tilTextoChave = (TextInputLayout) findViewById(R.id.til_textoChave_criar_devocional);
        TextInputLayout tilMensagemDeus = (TextInputLayout) findViewById(R.id.til_mensagemDeus_criar_devocional);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int dia = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = gregorianCalendar.get(GregorianCalendar.MONTH) + 1;
        int ano = gregorianCalendar.get(GregorianCalendar.YEAR);

        String parasha = tvParasha.getText().toString();
        String titulo = tilTitulo.getEditText().getText().toString();
        String dataCriacao = dia+"/"+mes+"/"+ano;
        String textoChave = tilTextoChave.getEditText().getText().toString();
        String mensagemDeDeus = tilMensagemDeus.getEditText().getText().toString();


        //checa se as informações são válidas

        boolean camposValidados = true;

        //falta comparar se a data de conclusão é menor do que a do início.

        if(titulo.length() < 8){
            tilTitulo.setError("Este campo deve ter no mínimo 8 caracteres");
            camposValidados = false;
        }else{
            tilTitulo.setErrorEnabled(false);
        }

        if(textoChave.length() < 8){
            tilTextoChave.setError("Este campo deve ter no mínimo 8 caracteres");
            camposValidados = false;
        }else{
            tilTextoChave.setErrorEnabled(false);
        }

        if(mensagemDeDeus.length() < 10){
            tilMensagemDeus.setError("Este campo deve ter no mínimo 10 caracteres");
            camposValidados = false;
        }else{
            tilMensagemDeus.setErrorEnabled(false);
        }

        if(camposValidados){

            Devocional devocional = new Devocional(parasha, titulo,dataCriacao,textoChave,mensagemDeDeus);

            DevocionalService devocionalService = new DevocionalService(context);
            boolean devocionalCriado = devocionalService.criarDevocional(devocional);

            if(devocionalCriado) {

                Toast.makeText(this, "Devocional criado com sucesso", Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();
            }else
                Toast.makeText(this, "Ocorreu algum erro. Tente novamente daqui a alguns segundos.", Toast.LENGTH_SHORT).show();
        }

    }

    private void compartilharDevocional(){}
}
