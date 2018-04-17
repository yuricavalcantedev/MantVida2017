package com.heavendevelopment.mantvida20182.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.heavendevelopment.mantvida20182.Adapter.AdapterAjuda;
import com.heavendevelopment.mantvida20182.Dominio.CabecalhoAjuda;
import com.heavendevelopment.mantvida20182.Dominio.ItemCabecalhoAjuda;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.AjudaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AjudaMain extends AppCompatActivity {


    private ExpandableListView expandableListView;

    private Context context;
    private List<String> listaNomeGrupos;
    private HashMap<String, List<String>> listaItensGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda_main);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_listview_ajuda);

        context = this;


        prepareListData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Ajuda");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdapterAjuda adapter = new AdapterAjuda(context, listaNomeGrupos, listaItensGrupo);
        expandableListView.setAdapter(adapter);
    }

    private void prepareListData() {

        listaNomeGrupos = new ArrayList<>();
        listaItensGrupo = new HashMap<>();

        AjudaService ajudaService = new AjudaService(context);

        //pega todos os cabecalhos da ajuda
        List<CabecalhoAjuda> lisCabecalhoAjuda = ajudaService.getCabecalhos();
        List<ItemCabecalhoAjuda> listItensPorCabecalho;

        //percorro todos os cabecalhos
        for(int i = 0; i < lisCabecalhoAjuda.size(); i++) {

            //a cada cabecalho eu adiciono o titulo dele na lista do nome dos grupos do exp. listview
            CabecalhoAjuda cabecalho = lisCabecalhoAjuda.get(i);
            listaNomeGrupos.add(cabecalho.getTitulo());

            //pego os itens do cabecalho atual do banco.
            listItensPorCabecalho = ajudaService.getItensPorCabecalho(cabecalho.getId());

            //percorro a lista desses itens e adiciono numa lista apenas com o titulo deles.
            List<String> listaTitulosItens = new ArrayList<>();
            for(int j = 0; j < listItensPorCabecalho.size(); j++)
                listaTitulosItens.add(listItensPorCabecalho.get(j).getTitulo());

            //adiciono na posicao atual do cabecalho a lista de itens correspondentes a ele.
            listaItensGrupo.put(listaNomeGrupos.get(i), listaTitulosItens);
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
