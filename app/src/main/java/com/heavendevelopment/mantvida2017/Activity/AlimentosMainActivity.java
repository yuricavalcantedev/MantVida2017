package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Adapter.AdapterAlimentoCelular;
import com.heavendevelopment.mantvida2017.Adapter.AdapterDevocional;
import com.heavendevelopment.mantvida2017.Dominio.AlimentoCelular;
import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.AlimentoCelularService;
import com.heavendevelopment.mantvida2017.Service.DevocionalService;

import java.util.List;

public class AlimentosMainActivity extends AppCompatActivity {

    Context context;
    ListView listViewAlimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_main);

        listViewAlimentos = (ListView) findViewById(R.id.listview_alimentos_main);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Alimentos Celulares");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final AlimentoCelularService alimentoCelularService = new AlimentoCelularService(context);

        List<AlimentoCelular> listAlimentos = alimentoCelularService.getAlimentos();

        AdapterAlimentoCelular adapterAlimentoCelular = new AdapterAlimentoCelular(context,listAlimentos);
        listViewAlimentos.setAdapter(adapterAlimentoCelular);

        listViewAlimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!checkConnection()) {
                    Toast.makeText(context, "Você está sem acesso a internet, verifique sua conexão e tente novamente.", Toast.LENGTH_LONG).show();

                }else{

                    TextView tvNumero = (TextView) view.findViewById(R.id.tv_item_numero_alimento_celular);
                    int numero = Integer.parseInt(tvNumero.getText().toString());

                    String link = alimentoCelularService.getLinkAlimentoByNumero(numero);

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    startActivity(i);
                }

            }
        });

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
        }

        return super.onOptionsItemSelected(item);
    }

    public  boolean checkConnection() {
        boolean connected;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

}
