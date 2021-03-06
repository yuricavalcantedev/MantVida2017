package com.heavendevelopment.mantvida20182.Activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida20182.Adapter.AdapterLeitura;
import com.heavendevelopment.mantvida20182.Dominio.Leitura;
import com.heavendevelopment.mantvida20182.Dominio.Versículo;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.LeituraService;
import com.heavendevelopment.mantvida20182.Util;

import java.util.ArrayList;

import de.mrapp.android.bottomsheet.BottomSheet;

public class LeituraBiblica extends AppCompatActivity {

    Context context;
    Activity thisActivity;
    LeituraService leituraService;
    Leitura leituraAtual;
    ArrayList<Leitura> listLeitura;
    int posLeituraAtual;
    int tamFonteLeitura;
    boolean modoNoturno;
    int versaoBiblia;

    private ListView listViewLeitura;
    private TextView tvTituloLeitura;
    ImageView imgVoltar;
    ImageView imgAvancar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura_biblica);


        thisActivity = this;
        context = this;
        posLeituraAtual = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Leitura");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        listViewLeitura.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {

                PopupMenu popupMenu = new PopupMenu(context, view, Gravity.END);


                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_leitura, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        TextView tvLivroCapitulo = (TextView) findViewById(R.id.tv_titulo_leitura_biblica);
                        TextView tvNumVersiculo = (TextView) view.findViewById(R.id.tv_item_num_versiculo_leitura);
                        TextView tvTextoVersiculo = (TextView) view.findViewById(R.id.tv_item_texto_versiculo_leitura);

                        String livro_capitulo = tvLivroCapitulo.getText().toString();
                        livro_capitulo = livro_capitulo.substring(0, livro_capitulo.length() - 1);

                        String numVersiculo = tvNumVersiculo.getText().toString();
                        String textoVersiculo = tvTextoVersiculo.getText().toString();
                        String versao = versaoBibliaTexto(versaoBiblia);

                        String versoCompleto = textoVersiculo + " - " + versao + "\n" + livro_capitulo + "." + numVersiculo;

                        if (item.getItemId() == R.id.item_copiar) {

                            android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                            ClipData clipData = ClipData.newPlainText("text", versoCompleto);
                            clipboardManager.setPrimaryClip(clipData);

                            Util util = new Util(context);
                            util.toast("Versículo copiado");

                        } else if (item.getItemId() == R.id.item_devocional) {

                            Intent intent = new Intent(context, CriarDevocional.class);
                            intent.putExtra("versiculoCopiado", versoCompleto);

                            startActivity(intent);

                        } else {

                            versoCompleto += "\nMANT VIDA 2017";

                            BottomSheet.Builder builder = new BottomSheet.Builder(context);
                            builder.setTitle("Compartilhar via...");
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT, versoCompleto);
                            intent.setType("text/plain");

                            builder.setIntent(thisActivity, intent);
                            builder.setStyle(BottomSheet.Style.GRID);

                            BottomSheet bottomSheet = builder.create();
                            bottomSheet.show();

                        }

                        return true;
                    }

                });

                return true;
            }
        });

        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.settings_preferences), MODE_PRIVATE);
        tamFonteLeitura = preferences.getInt("setting_tam_fonte",20);
        modoNoturno = preferences.getBoolean("setting_modo_noturno", false);

        //A VERSÃO 1 É A VERÃO NVI - NOVA VERSÃO INTERNACIONAL
        versaoBiblia = preferences.getInt("setting_version_bible", 1);
        leituraService = new LeituraService(context,versaoBiblia);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_leitura_biblica);

        //VERIFICA SE A LEITURA ESTÁ EM MODO NOTURNO.
        if(modoNoturno){
            imgAvancar.setBackgroundResource(R.drawable.ic_seta_avancar_white);
            imgVoltar.setBackgroundResource(R.drawable.ic_seta_voltar_white);
            tvTituloLeitura.setTextColor(Color.WHITE);
            coordinatorLayout.setBackgroundColor(Color.BLACK);
        }

        try{

            Bundle bundle = getIntent().getExtras();
            int diaLeitura = bundle.getInt("diaLeitura");
            int mesLeitura = bundle.getInt("mesLeitura");

            listLeitura = leituraService.getReadingOfDay(diaLeitura,mesLeitura);

            ArrayList<Versículo> versiculosLeitura;
            for(int i = 0; i < listLeitura.size(); i++) {
                versiculosLeitura = leituraService.getLeituraDiaria(listLeitura.get(i).getIdLivro(), listLeitura.get(i).getCapitulo());
                listLeitura.get(i).setVersiculos(versiculosLeitura);
            }

            leituraAtual = listLeitura.get(posLeituraAtual);
            tvTituloLeitura.setText(leituraAtual.getTitulo());

            AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(), tamFonteLeitura, modoNoturno);
            listViewLeitura.setAdapter(adapterLeitura);
            imgVoltar.setVisibility(View.INVISIBLE);

            imgVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posLeituraAtual --;
                    if(posLeituraAtual >= 0) {
                        leituraAtual = listLeitura.get(posLeituraAtual);
                        AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(), tamFonteLeitura, modoNoturno);
                        listViewLeitura.setAdapter(adapterLeitura);

                        imgAvancar.setEnabled(true);
                        tvTituloLeitura.setText(leituraAtual.getTitulo());
                    }

                    if(posLeituraAtual == 0){
                        imgVoltar.setVisibility(View.INVISIBLE);
                        imgVoltar.setEnabled(false);
                    }

                    imgAvancar.setVisibility(View.VISIBLE);
                }
            });

            imgAvancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posLeituraAtual ++;

                    if(posLeituraAtual <= listLeitura.size() - 1) {
                        leituraAtual = listLeitura.get(posLeituraAtual);
                        AdapterLeitura adapterLeitura = new AdapterLeitura(context, leituraAtual.getVersiculos(),tamFonteLeitura, modoNoturno);
                        listViewLeitura.setAdapter(adapterLeitura);

                        tvTituloLeitura.setText(leituraAtual.getTitulo());
                        imgVoltar.setEnabled(true);
                    }

                    if(posLeituraAtual + 1 == listLeitura.size()){
                        imgAvancar.setVisibility(View.INVISIBLE);
                        imgAvancar.setEnabled(false);
                    }

                    imgVoltar.setVisibility(View.VISIBLE);

                }
            });

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_leitura_diaria, menu);
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
        }else if(id == R.id.ic_menu_devocional){
            startActivity(new Intent(context, CriarDevocional.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private String versaoBibliaTexto(int versaoBiblia){

        String versaoTexto;

        if(versaoBiblia == 1)
            versaoTexto = "NVI";
        else if(versaoBiblia == 2)
            versaoTexto = "NTLH";
        else if(versaoBiblia == 3)
            versaoTexto = "ACF";
        else
            versaoTexto = "KJV";

        return versaoTexto;
    }

    private void initViews(){

        listViewLeitura = (ListView) findViewById(R.id.listview_leitura_biblica);
        tvTituloLeitura = (TextView) findViewById(R.id.tv_titulo_leitura_biblica);
        imgVoltar = (ImageView) findViewById(R.id.img_seta_voltar_leitura);
        imgAvancar = (ImageView) findViewById(R.id.img_seta_avancar_leitura);
    }
}
