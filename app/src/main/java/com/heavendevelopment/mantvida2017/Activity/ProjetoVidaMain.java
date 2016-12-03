package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Adapter.AdapterMeta;
import com.heavendevelopment.mantvida2017.Dominio.Meta;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.MetaService;

import java.util.List;

public class ProjetoVidaMain extends AppCompatActivity {

    Context context;
    ListView listViewProjetoVida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_vida_main);

        listViewProjetoVida = (ListView) findViewById(R.id.listview_projetoVida_main);
        context = this;

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

    private void fillListViewProjetoVida(){

        MetaService metaService = new MetaService(context);
        List<Meta> listaMetas = metaService.getMetas();

        AdapterMeta adapterMeta = new AdapterMeta(context,listaMetas);
        listViewProjetoVida.setAdapter(adapterMeta);

    }

}
