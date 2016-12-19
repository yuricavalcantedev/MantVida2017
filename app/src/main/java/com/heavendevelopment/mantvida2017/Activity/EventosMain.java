package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

        EventoService eventoService = new EventoService(context);
        ArrayList<Evento> listEventos = eventoService.getEventos();

        AdapterEvento adapterEvento = new AdapterEvento(context,listEventos);
        listViewEventos.setAdapter(adapterEvento);

    }
}

