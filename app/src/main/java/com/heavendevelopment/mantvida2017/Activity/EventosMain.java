package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Adapter.AdapterEvento;
import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.EventoService;

import java.util.ArrayList;
import java.util.List;

public class EventosMain extends AppCompatActivity {

    ListView listViewEventos;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_main);

        context = this;
        listViewEventos = (ListView) findViewById(R.id.listview_eventos_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eventos");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try{

            EventoService eventoService = new EventoService(context);
            ArrayList<Evento> listEventos = eventoService.getEventos();

            AdapterEvento adapterEvento = new AdapterEvento(context,listEventos);
            listViewEventos.setAdapter(adapterEvento);

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

