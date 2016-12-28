package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Adapter.AdapterDevocional;
import com.heavendevelopment.mantvida2017.Dominio.Devocional;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.DevocionalService;

import java.util.List;
import java.util.Locale;

public class DevocionaisMain extends AppCompatActivity {

    Context context;
    ListView listViewDevocionais;
    private AdapterDevocional adapterDevocional;
    private List<Devocional> listaDevocionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devocionais_main);

        listViewDevocionais = (ListView) findViewById(R.id.listview_devocionais_main);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Devocionais");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fillListViewDevocionais();

        listViewDevocionais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textViewId = (TextView) view.findViewById(R.id.tv_item_devocional_idDevocional);
                int idDevocional = Integer.parseInt(textViewId.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putInt("idDevocional", idDevocional);

                Intent intent = new Intent(context, VisualizarDevocional.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        listViewDevocionais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String idString = ((TextView)view.findViewById(R.id.tv_item_devocional_idDevocional)).getText().toString();
                final int idDevocional = Integer.parseInt(idString);

                final DevocionalService devocionalService = new DevocionalService(context);
                final Devocional devocionalExcluir = devocionalService.getDevocionalById(idDevocional);

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Excluir Devocional")
                        .setMessage("Você realmente deseja excluir o devocional : " + devocionalExcluir.getTitulo())
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                RelativeLayout relativeLayoutProjetoVidaMain = (RelativeLayout) findViewById(R.id.activity_devocionais_main);

                                devocionalService.deletarDevocional(idDevocional);

                                Snackbar.make(relativeLayoutProjetoVidaMain, "Devocional excluído com sucesso", Snackbar.LENGTH_LONG).show();

                                fillListViewDevocionais();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_criar_devocional);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, CriarDevocional.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu_search, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);

    SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    searchView.setQueryHint("Título...");
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
            adapterDevocional.filter(searchQuery.toString().trim());
            listViewDevocionais.invalidate();
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

        if(id == android.R.id.home){
            finish();
        }else if (id == R.id.action_search) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillListViewDevocionais(){

        DevocionalService devocionalService = new DevocionalService(context);

        for(int i = 0; i < 20; i ++){

            Devocional devocional = new Devocional("Título "+i,"03/12/2016","TextoChave "+i,"Mensagem "+i);
            devocionalService.criarDevocional(devocional);
        }

        listaDevocionais = devocionalService.getDevocionais();

        adapterDevocional = new AdapterDevocional(context,listaDevocionais);
        listViewDevocionais.setAdapter(adapterDevocional);

    }


}
