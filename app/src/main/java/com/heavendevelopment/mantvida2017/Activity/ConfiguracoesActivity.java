package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfiguracoesActivity extends AppCompatActivity {

    @BindView(R.id.switch_modo_noturno_configuracoes) Switch switchModoNoturno;
    @BindView(R.id.seekbar_tamanho_letra_configuracoes) SeekBar seekBarTamFonte;
    @BindView(R.id.spinner_versao_biblia_configuracoes) Spinner spinnerVersoesBiblia;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        ButterKnife.bind(this);

        //pegar do shared_preferences, as informações salvas.

        SharedPreferences sharedPreferences = getSharedPreferences("configuracoes_preferences", MODE_PRIVATE);
        boolean modoNoturno = sharedPreferences.getBoolean("pref_config_modo_noturno", false);
        int tamFonteLeitura = sharedPreferences.getInt("pref_config_tam_fonte", 20);
        String versaoBiblia = sharedPreferences.getString("pref_config_versao_biblia", "NVI - Nova Versão Internacional");

        switchModoNoturno.setEnabled(modoNoturno);
        seekBarTamFonte.setProgress(tamFonteLeitura);
        spinnerVersoesBiblia.setPrompt(versaoBiblia);

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
        if (id == R.id.ic_menu_save) {
            salvarConfiguracoes();
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarConfiguracoes(){

        SharedPreferences sharedPreferences = getSharedPreferences("configuracoes_preferences", MODE_PRIVATE);
        boolean modoNoturno;

        int tamFonteLeitura = seekBarTamFonte.getProgress();
        String versaoBiblia = "";
        modoNoturno = switchModoNoturno.isEnabled();

//        Spinner spinnerVersoesBiblia = (Spinner) findViewById(R.id.spinner_versao_biblia_configuracoes);
//        versaoBiblia = spinnerVersoesBiblia.getPrompt().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("pref_config_modo_noturno", modoNoturno);
        editor.putInt("pref_config_tam_fonte", tamFonteLeitura);
        editor.putString("pref_config_versao_biblia", versaoBiblia);

        editor.commit();

    }

}
