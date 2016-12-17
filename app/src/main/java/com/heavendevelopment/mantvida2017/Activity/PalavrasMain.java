package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Adapter.AdapterDevocional;
import com.heavendevelopment.mantvida2017.Adapter.AdapterPalavra;
import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.Dominio.Palavra;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.DevocionalService;

import java.util.ArrayList;

public class PalavrasMain extends AppCompatActivity {

    Context context;
    ListView listViewPalavras;
    AdapterPalavra adapterPalavra;
    ArrayList<Palavra> listPalavras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palavras_main);
        context = this;

        listViewPalavras = (ListView) findViewById(R.id.listview_palavras);

        fillListViewPalavras();

        listViewPalavras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TODO

                TextView textViewId = (TextView) view.findViewById(R.id.tv_item_id_palavra);
                int idPalavra = Integer.parseInt(textViewId.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putInt("idPalavra", idPalavra);

                Intent intent = new Intent(context, VisualizarPalavra.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Busque por título...");
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                adapterPalavra.filter(searchQuery.toString().trim());
                listViewPalavras.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillListViewPalavras(){
//
//        DevocionalService devocionalService = new DevocionalService(context);
//
//        for(int i = 0; i < 20; i ++){
//
//            Devocional devocional = new Devocional("Parashá "+i,"Título "+i,"03/12/2016","TextoChave "+i,"Mensagem "+i);
//            devocionalService.criarDevocional(devocional);
//        }
//
//        listaDevocionais = devocionalService.getDevocionais();
//
//        adapterDevocional = new AdapterDevocional(context,listaDevocionais);
//        listViewDevocionais.setAdapter(adapterDevocional);

    }

}
