package com.heavendevelopment.mantvida2018.Activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.heavendevelopment.mantvida2018.Adapter.AdapterMeta;
import com.heavendevelopment.mantvida2018.Dominio.Meta;
import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Service.MetaService;
import com.heavendevelopment.mantvida2018.Util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoVidaMain extends AppCompatActivity {

    private Context context;
    private ListView listViewProjetoVida;
    private List<Meta> listaMetas;
    private AdapterMeta adapterMeta;
    private Document document;
    private final int PERMISSION_CODE = 200;
    private AlertDialog alerta;
    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_vida_main);

        listViewProjetoVida = (ListView) findViewById(R.id.listview_projetoVida_main);
        context = this;
        util = new Util(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Projeto de Vida");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public void onResume(){
        super.onResume();

        fillListViewProjetoVida();

    }

    private void fillListViewProjetoVida(){

        MetaService metaService = new MetaService(context);
        listaMetas = metaService.getMetas();

        TextView tv_nenhuma_meta = (TextView) findViewById(R.id.tv_nenhum_projeto_vida_main);

        if(listaMetas .size() == 0)
            tv_nenhuma_meta
                    .setVisibility(View.VISIBLE);
        else
            tv_nenhuma_meta
                    .setVisibility(View.INVISIBLE);

        adapterMeta = new AdapterMeta(context,listaMetas);
        listViewProjetoVida.setAdapter(adapterMeta);

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

            //chama a rotina de criar o PDF.
            callWriteOnSDCard();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_CODE:
                for (int i = 0; i < permissions.length; i++) {

                    if (permissions[i].equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        createDeleteFolder();
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void callWriteOnSDCard() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                callDialog(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
            }
        } else {
            createDeleteFolder();
        }

    }

    // FILE
    public void createDeleteFolder() {
        String path = Environment.getExternalStorageDirectory().toString() + "/Mant Vida 2017";
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Mant Vida 2017");

        if (file.exists()) {
            new File(Environment.getExternalStorageDirectory().toString() + "/Mant Vida 2017", "Projeto de Vida.pdf").delete();
            if (file.delete()) {
                util.toast("Aperte mais uma vez para gerar o Projeto de Vida em pdf");
            }
        } else {
            if (file.mkdir()) {
                createFile(path);
            } else {
            }
        }
    }

    public void createFile(final String path) {

        try {

            String filename = "Projeto de Vida 2017.pdf";
            String projetoVida;

            document = new Document(PageSize.A4);

            File dir = new File(path, filename);
            if (!dir.exists()) {
                dir.getParentFile().mkdirs();
            }

            FileOutputStream fOut = new FileOutputStream(dir);
            fOut.flush();

            //Fontes
            Font fontTitulo = new Font(Font.FontFamily.COURIER, 25, Font.BOLD);
            Font fontCategoria = new Font(Font.FontFamily.TIMES_ROMAN, 18);
            Font fontMetas = new Font(Font.FontFamily.HELVETICA, 14);
            Font fontSubTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18);

            Paragraph titulo = new Paragraph("Projeto de Vida 2017", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Paragraph subTitulo = new Paragraph("O ano da chuva de avivamento e bençãos de Deus", fontSubTitulo);
            subTitulo.setAlignment(Element.ALIGN_CENTER);

            PdfWriter.getInstance(document, fOut);
            document.open();

            document.add(titulo);

            document.addTitle("Projeto de Vida 2017");
            document.addSubject("O ano da chuva de avivamento e bençãos de Deus");

            MetaService metaService = new MetaService(context);

            ArrayList<Meta> metasFamilia = metaService.getMetasByCategory(1);
            ArrayList<Meta> metasMinisterio = metaService.getMetasByCategory(2);
            ArrayList<Meta> metasFormacao = metaService.getMetasByCategory(3);
            ArrayList<Meta> metasRestituicao = metaService.getMetasByCategory(4);
            ArrayList<Meta> metasFinancas = metaService.getMetasByCategory(5);

            ArrayList<ArrayList<Meta>> listMetas = new ArrayList<>();
            listMetas.add(metasFamilia);
            listMetas.add(metasMinisterio);
            listMetas.add(metasFormacao);
            listMetas.add(metasRestituicao);
            listMetas.add(metasFinancas);

            //titulo, dataInicio, dataTérmino
            //1 - Família, 2 - Ministério, 3 - Formação, 4 - Restituição, 5 - Finanças

            for (int i = 0; i < listMetas.size() - 1; i++) {

                projetoVida = "";
                String nomeCategoria = escolheNomeCategoria(i + 1);
                Paragraph paragraphAreas = new Paragraph(nomeCategoria + "\n\n", fontCategoria);
                paragraphAreas.setAlignment(Element.CHAPTER);
                document.add(paragraphAreas);

                Paragraph paragraphMetas;

                for (int j = 0; j <= listMetas.get(i).size() - 1; j++) {

                    Meta meta = listMetas.get(i).get(j);
                    projetoVida += (meta.getTitulo() + " - de " + meta.getDataInicio() + " a " + meta.getDataConclusao() + "\n" );
                }

                paragraphMetas = new Paragraph(projetoVida, fontMetas);
                paragraphMetas.setSpacingAfter(20);
                document.add(paragraphMetas);

            }

           util.toast("Projeto de vida.pdf criado com sucesso na pasta MantVida 2017");

            //Image logoIgreja = Image.getInstance(getClass().getResource("/com/example/alysson/mantvida2016/lg_icant72x72_gold.png"));
            //logoIgreja.setAlignment(Element.ALIGN_BOTTOM);
            //document.add(logoIgreja);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    // UTIL
    public void callDialog(final String[] permissions) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deseja criar o pdf do seu Projeto de Vida 2017 ?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                ActivityCompat.requestPermissions(ProjetoVidaMain.this, permissions, PERMISSION_CODE);
                util.toast("Pdf criado com sucesso!");

                alerta.cancel();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.cancel();
            }
        });


        alerta = builder.create();
        alerta.show();

    }

    private String escolheNomeCategoria (int idCategoria){

        String nomeCategoria;

        if(idCategoria == 1)
            nomeCategoria = "Família";
        else if (idCategoria == 2)
            nomeCategoria = "Ministério";
        else if (idCategoria == 3)
            nomeCategoria = "Formação";
        else if (idCategoria == 4)
            nomeCategoria = "Restituição";
        else
            nomeCategoria = "Finanças";

        return nomeCategoria;
    }

}
