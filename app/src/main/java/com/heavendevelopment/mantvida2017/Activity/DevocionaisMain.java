package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DevocionaisMain extends AppCompatActivity {

    Context context;
    ListView listViewDevocionais ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devocionais_main);

        listViewDevocionais = (ListView) findViewById(R.id.listview_devocionais_main);
        context = this;

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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    private void fillListViewDevocionais(){

        DevocionalService devocionalService = new DevocionalService(context);
        List<Devocional> listaDevocionais = devocionalService.getDevocionais();

        AdapterDevocional adapterDevocional = new AdapterDevocional(context,listaDevocionais);
        listViewDevocionais.setAdapter(adapterDevocional);

    }
}
