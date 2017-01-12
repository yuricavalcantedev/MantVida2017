package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Adapter.AdapterEvento;
import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.EventoService;
import com.heavendevelopment.mantvida2017.Util;

import java.util.ArrayList;
import java.util.List;

public class EventosMain extends AppCompatActivity {

    private ListView listViewEventos;
    private Context context;
    private ArrayList<Evento> listEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_main);

        context = this;
        final Util util = new Util(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eventos");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewEventos = (ListView) findViewById(R.id.listview_eventos_main);


        EventoService eventoService = new EventoService(context);
        listEventos = eventoService.getEventos();

        AdapterEvento adapterEvento = new AdapterEvento(context,listEventos);
        listViewEventos.setAdapter(adapterEvento);

        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvSituacao = (TextView) view.findViewById(R.id.tv_item_estado_evento);

                Evento evento = listEventos.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("tituloEvento", evento.getNome());
                bundle.putString("dataEvento", evento.getData());
                bundle.putString("descricaoEvento", evento.getDescricao());
                bundle.putString("situacaoEvento", tvSituacao.getText().toString());

                Intent intent = new Intent(context, VisualizarEventoActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        try{


        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

