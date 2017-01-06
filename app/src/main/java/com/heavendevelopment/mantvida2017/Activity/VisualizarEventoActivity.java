package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisualizarEventoActivity extends AppCompatActivity {

    @BindView(R.id.tv_titulo_visualizar_evento)
    TextView tvTituloEvento;

    @BindView(R.id.tv_data_visualizar_evento)
    TextView tvDataEvento;

    @BindView(R.id.tv_descricao_visualizar_evento)
    TextView tvDescricaoEvento;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_evento);

        context = this;
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ver evento");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        tvTituloEvento.setText(bundle.getString("tituloEvento"));
        tvDataEvento.setText(bundle.getString("dataEvento"));
        tvDescricaoEvento.setText(bundle.getString("descricaoEvento"));

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
