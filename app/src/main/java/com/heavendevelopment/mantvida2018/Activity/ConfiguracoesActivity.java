package com.heavendevelopment.mantvida2018.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Util;


public class ConfiguracoesActivity extends AppCompatActivity {

    private Switch switchModoNoturno;
    private SeekBar seekBarTamFonte;
    private Spinner spinnerVersoesBiblia;

    private Context context;
    private int versaoSelecionadaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        switchModoNoturno = (Switch) findViewById(R.id.switch_modo_noturno_configuracoes);
        seekBarTamFonte = (SeekBar) findViewById(R.id.seekbar_tamanho_letra_configuracoes);
        spinnerVersoesBiblia = (Spinner) findViewById(R.id.spinner_versao_biblia_configuracoes);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Configurações");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pegar do shared_preferences, as informações salvas.

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.settings_preferences), MODE_PRIVATE);
        boolean modoNoturno = sharedPreferences.getBoolean("setting_modo_noturno", false);
        int tamFonteLeitura = sharedPreferences.getInt("setting_tam_fonte", 20);
        int versaoBiblia = sharedPreferences.getInt("setting_version_bible", 1);

        switchModoNoturno.setChecked(modoNoturno);
        seekBarTamFonte.setProgress(tamFonteLeitura);
        spinnerVersoesBiblia.setSelection(versaoBiblia - 1); // -1 porque eu salvo começando de 1.


        spinnerVersoesBiblia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                versaoSelecionadaSpinner = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_criar_meta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            finish();
        }else if (id == R.id.ic_menu_save) {
            salvarConfiguracoes();
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarConfiguracoes(){

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.settings_preferences), MODE_PRIVATE);
        boolean modoNoturno;

        int tamFonteLeitura = seekBarTamFonte.getProgress();
        modoNoturno = switchModoNoturno.isChecked();

        String nomeVersao = escolheVersaoBiblia(versaoSelecionadaSpinner);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("setting_modo_noturno", modoNoturno);
        editor.putInt("setting_tam_fonte", tamFonteLeitura);
        editor.putString("setting_versao_biblia_string", nomeVersao);
        editor.putInt("setting_version_bible", versaoSelecionadaSpinner);

        editor.commit();

        Util util = new Util(context);
        util.toast("As configurações foram salvas");

    }

    private String escolheVersaoBiblia(int itemSpinnerSelecionado){

        String nomeVersao;

        if(itemSpinnerSelecionado == 1)
            nomeVersao = "NVI - Nova Versão Internacional";
        else if(itemSpinnerSelecionado == 2)
            nomeVersao = "NTLH - Nova Tradução na Linguagem de Hoje";
        else if(itemSpinnerSelecionado == 3)
            nomeVersao = "ACF - Almeida Corrigida Fiel";
        else
            nomeVersao = "KJV - King James Version";

        return nomeVersao;
    }

}
