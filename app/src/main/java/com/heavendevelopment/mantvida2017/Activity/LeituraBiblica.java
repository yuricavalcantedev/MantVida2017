package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Adapter.AdapterLeitura;
import com.heavendevelopment.mantvida2017.Dominio.Leitura;
import com.heavendevelopment.mantvida2017.Dominio.Versículo;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.LeituraService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeituraBiblica extends AppCompatActivity {

    Context context;
    LeituraService leituraService;
    Leitura leituraAtual;
    ArrayList<Leitura> listLeitura;
    int posLeituraAtual;
    int tamFonteLeitura;
    boolean modoNoturno;
    int versaoBiblia;

    @BindView(R.id.tv_titulo_leitura_biblica)
    TextView tvTituloLeitura;

    @BindView(R.id.img_seta_voltar_leitura)
    ImageView img_seta_voltar;

    @BindView(R.id.img_seta_avancar_leitura)
    ImageView img_seta_avancar;

    @BindView(R.id.listview_leitura_biblica)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura_biblica);

        context = this;
        posLeituraAtual = 0;
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Leitura");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.settings_preferences), MODE_PRIVATE);
        tamFonteLeitura = preferences.getInt("setting_tam_fonte",20);
        modoNoturno = preferences.getBoolean("setting_modo_noturno", false);

        //A VERSÃO 1 É A VERÃO NVI - NOVA VERSÃO INTERNACIONAL
        versaoBiblia = preferences.getInt("setting_version_bible", 1);
        leituraService = new LeituraService(context,versaoBiblia);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_leitura_biblica);

        //VERIFICA SE A LEITURA ESTÁ EM MODO NOTURNO.
        if(modoNoturno){
            img_seta_avancar.setBackgroundResource(R.drawable.ic_seta_avancar_white);
            img_seta_voltar.setBackgroundResource(R.drawable.ic_seta_voltar_white);
            tvTituloLeitura.setTextColor(Color.WHITE);
            coordinatorLayout.setBackgroundColor(Color.BLACK);
        }

        try{

            Bundle bundle = getIntent().getExtras();
            int diaLeitura = bundle.getInt("diaLeitura");
            int mesLeitura = bundle.getInt("mesLeitura");

            listLeitura = leituraService.getReadingOfDay(diaLeitura,mesLeitura);

            ArrayList<Versículo> versiculosLeitura;
            for(int i = 0; i < listLeitura.size(); i++) {
                versiculosLeitura = leituraService.getLeituraDiaria(listLeitura.get(i).getIdLivro(), listLeitura.get(i).getCapitulo());
                listLeitura.get(i).setVersiculos(versiculosLeitura);
            }

            leituraAtual = listLeitura.get(posLeituraAtual);
            tvTituloLeitura.setText(leituraAtual.getTitulo());

            AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(), tamFonteLeitura, modoNoturno);
            listView.setAdapter(adapterLeitura);
            img_seta_voltar.setVisibility(View.INVISIBLE);

            img_seta_voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posLeituraAtual --;
                    if(posLeituraAtual >= 0) {
                        leituraAtual = listLeitura.get(posLeituraAtual);
                        AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(), tamFonteLeitura, modoNoturno);
                        listView.setAdapter(adapterLeitura);

                        img_seta_avancar.setEnabled(true);
                        tvTituloLeitura.setText(leituraAtual.getTitulo());
                    }

                    if(posLeituraAtual == 0){
                        img_seta_voltar.setVisibility(View.INVISIBLE);
                        img_seta_voltar.setEnabled(false);
                    }

                    img_seta_avancar.setVisibility(View.VISIBLE);
                }
            });

            img_seta_avancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posLeituraAtual ++;

                    if(posLeituraAtual <= listLeitura.size() - 1) {
                        leituraAtual = listLeitura.get(posLeituraAtual);
                        AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(),tamFonteLeitura, modoNoturno);
                        listView.setAdapter(adapterLeitura);

                        tvTituloLeitura.setText(leituraAtual.getTitulo());
                        img_seta_voltar.setEnabled(true);
                    }

                    if(posLeituraAtual + 1 == listLeitura.size()){
                        img_seta_avancar.setVisibility(View.INVISIBLE);
                        img_seta_avancar.setEnabled(false);
                    }

                    img_seta_voltar.setVisibility(View.VISIBLE);

                }
            });

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_leitura_diaria, menu);
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
        }else if(id == R.id.ic_menu_devocional){
            startActivity(new Intent(context, CriarDevocional.class));
        }

        return super.onOptionsItemSelected(item);
    }



}
