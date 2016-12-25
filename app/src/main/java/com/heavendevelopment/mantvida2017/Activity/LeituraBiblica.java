package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.heavendevelopment.mantvida2017.Adapter.AdapterLeitura;
import com.heavendevelopment.mantvida2017.DataBaseAccess.DatabaseAccess;
import com.heavendevelopment.mantvida2017.Dominio.Versículo;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.LeituraService;

import java.util.ArrayList;

public class LeituraBiblica extends AppCompatActivity {

    String id_livros, capitulos;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura_biblica);

        context = this;

        Bundle bundle = getIntent().getExtras();
        id_livros = bundle.getString("id_livros");
        capitulos = bundle.getString("capitulos");

        String [] arrayIdLivros = id_livros.split(",");
        String [] arrayCapitulos = capitulos.split(",");

        //fazer uma verificação se tem mais de um livro a leitura

        int idPrimeiroLivro = Integer.parseInt(arrayIdLivros[0]);
        int primeiroCapitulo = Integer.parseInt(arrayCapitulos[0]);

        LeituraService leituraService = new LeituraService(context);

        ArrayList<Versículo> listaVersiculos = leituraService.getLeituraDiaria(idPrimeiroLivro, primeiroCapitulo);

        ListView listView = (ListView) findViewById(R.id.listview_leitura_biblica);
        AdapterLeitura adapterLeitura = new AdapterLeitura(context, listaVersiculos);

        listView.setAdapter(adapterLeitura);

    }
}
