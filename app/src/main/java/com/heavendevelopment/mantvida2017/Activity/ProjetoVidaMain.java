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

import com.heavendevelopment.mantvida2017.Adapter.AdapterMeta;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.MetaService;
import com.heavendevelopment.mantvida2017.Util;

import java.util.List;

public class ProjetoVidaMain extends AppCompatActivity {

    Context context;
    ListView listViewProjetoVida;
    List<Meta> listaMetas;
    AdapterMeta adapterMeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_vida_main);

        listViewProjetoVida = (ListView) findViewById(R.id.listview_projetoVida_main);
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Projeto de Vida");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fillListViewProjetoVida();

        //LISTENER DE VISUALIZAR UMA META CRIADA
        listViewProjetoVida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_projetovida_idMeta);
                int idMeta = Integer.parseInt(tvId.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putInt("idMeta",idMeta);

                Intent intent = new Intent(context, VisualizarMetaProjetoVida.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });


        //LISTENER DE DELETAR UMA META
        listViewProjetoVida.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String idString = ((TextView)view.findViewById(R.id.tv_item_projetovida_idMeta)).getText().toString();
                final int idMeta = Integer.parseInt(idString);

                final MetaService metaService = new MetaService(context);
                final Meta metaExcluir = metaService.getMetaById(idMeta);

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Excluir Meta")
                        .setMessage("Você realmente deseja excluir a meta : " + metaExcluir.getTitulo())
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
                                RelativeLayout relativeLayoutProjetoVidaMain = (RelativeLayout) findViewById(R.id.activity_projeto_vida_main);

                                metaService.deletarMeta(idMeta);

                                Snackbar.make(relativeLayoutProjetoVidaMain, "Meta excluída com sucesso", Snackbar.LENGTH_LONG).show();

                                fillListViewProjetoVida();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_criar_meta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, CriarMetaProjetoVida.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_projeto_vida, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Título ou categoria...");
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
                adapterMeta.filter(searchQuery.toString().trim());
                listViewProjetoVida.invalidate();
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
        if(id == android.R.id.home){
            finish();
        }else if (id == R.id.action_search) {

            return true;
        }else if (id == R.id.action_gerar_projeto_vida){

            Util util = new Util(this);
            util.toast("Gerar Projeto de Vida em PDF. Ainda não implementado");
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillListViewProjetoVida(){

        MetaService metaService = new MetaService(context);
        listaMetas = metaService.getMetas();

        int idCategoriaAux;
        for(int i = 1; i < 20; i ++){

            idCategoriaAux = i;

            if(i > 5)
                idCategoriaAux = 1;

            Meta meta = new Meta("Título "+i,"","04/12/2016",idCategoriaAux,"","","04/12/2016");
            metaService.criarMeta(meta);
        }

        adapterMeta = new AdapterMeta(context,listaMetas);
        listViewProjetoVida.setAdapter(adapterMeta);

    }

}
