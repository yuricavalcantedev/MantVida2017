package com.heavendevelopment.mantvida2018.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.heavendevelopment.mantvida2018.R;

public class VisualizarEventoActivity extends AppCompatActivity {

    private TextView tvTituloEvento;
    private TextView tvDataEvento;
    private TextView tvDescricaoEvento;
    private TextView tvSituacaoEvento;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_evento);

        tvTituloEvento = (TextView) findViewById(R.id.tv_titulo_visualizar_evento);
        tvDataEvento = (TextView) findViewById(R.id.tv_data_visualizar_evento);
        tvDescricaoEvento = (TextView) findViewById(R.id.tv_descricao_visualizar_evento);
        tvSituacaoEvento = (TextView) findViewById(R.id.tv_situacao_visualizar);


        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ver evento");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        tvTituloEvento.setText(bundle.getString("tituloEvento"));
        tvDataEvento.setText(bundle.getString("dataEvento"));
        tvDescricaoEvento.setText(bundle.getString("descricaoEvento"));

        String situacaoEvento = bundle.getString("situacaoEvento");

        if(situacaoEvento.equals("Realizado"))
            tvSituacaoEvento.setTextColor(Color.RED);
        else if(situacaoEvento.equals("Em andamento"))
            tvSituacaoEvento.setTextColor(Color.BLUE);

        tvSituacaoEvento.setText(situacaoEvento);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }else if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
