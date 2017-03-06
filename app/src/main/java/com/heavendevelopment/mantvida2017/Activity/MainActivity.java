package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.heavendevelopment.mantvida2017.Dominio.Evento;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.EventoService;

import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Evento eventoDeHoje;
    Context context;

    @BindView(R.id.fab_main)
    FloatingActionButton fabLeitura;

    @BindView(R.id.tv_main_titulo_evento)
    TextView tvTituloEvento;

    @BindView(R.id.tv_main_data_evento)
    TextView tvDataEvento;

    @BindView(R.id.tv_main_descricao_evento)
    TextView tvDescricaoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        context = this;
        ButterKnife.bind(this);

        final EventoService eventoService = new EventoService(context);

        try{


            fabLeitura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //esse código está se repetindo no "PlanoLeituraMain", isso não é bom, depois tento resolver.
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    int diaHoje = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
                    int mesHoje = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;

                    if(mesHoje == 2 && diaHoje < 7)
                        diaHoje -= 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("diaLeitura", diaHoje);
                    bundle.putInt("mesLeitura", mesHoje);

                    Intent intent = new Intent(context, LeituraBiblica.class);
                    intent.putExtras(bundle);

                    startActivity(intent);

                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            CardView cardViewProjetoVida = (CardView) findViewById(R.id.cardview_projeto_vida_main);
            CardView cardViewEvento = (CardView) findViewById(R.id.cardview_proximo_evento);


            eventoDeHoje = eventoService.getEventoDoDia();

            tvTituloEvento.setText(eventoDeHoje.getNome());
            tvDataEvento.setText(eventoDeHoje.getData());
            tvDescricaoEvento.setText(eventoDeHoje.getDescricao());

            cardViewProjetoVida.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(context, ProjetoVidaTexto.class));
                }
            });

            cardViewEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("tituloEvento", eventoDeHoje.getNome());
                    bundle.putString("dataEvento", eventoDeHoje .getData());
                    bundle.putString("descricaoEvento", eventoDeHoje .getDescricao());

                    if(eventoDeHoje.getNome().equals("Nenhum evento"))
                        bundle.putString("situacaoEvento", "");
                    else
                        bundle.putString("situacaoEvento", "Em andamento");

                    Intent intent = new Intent(context, VisualizarEventoActivity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });

            //LÓGICA REFERENTE A ATUALIZAÇÃO DE SABER SE A LEITURA FOI OU NÃO REALIZADA, UTILIZANDO CORES NA LEITURA DIÁRIA.
            SharedPreferences sharedPreferencesMainActivity = context.getSharedPreferences("preferencesMainActivityUpdateLeituraDecorator",MODE_PRIVATE);
            boolean updatedLeituraDecorator = sharedPreferencesMainActivity.getBoolean("updated",true);

            if(!updatedLeituraDecorator) {
                changeColorMesesJanFev();

                SharedPreferences.Editor editor = sharedPreferencesMainActivity.edit();
                editor.putBoolean("updated",true);

                editor.apply();
            }

        }catch(Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_plano_leitura){

            startActivity(new Intent(context,PlanoLeituraMain.class));
        }

        else if (id == R.id.nav_projeto_vida) {
            // Handle the camera action) {

            startActivity(new Intent(context, ProjetoVidaMain.class));

        } else if (id == R.id.nav_devocional) {

            startActivity(new Intent(context, DevocionaisMain.class));

        } else if (id == R.id.nav_eventos) {

            startActivity(new Intent(context, EventosMain.class));

        } else if (id == R.id.nav_alimento_celular) {

            startActivity(new Intent(context, AlimentosMainActivity.class));

        }else if(id == R.id.nav_localizacao){

            startActivity(new Intent(context, MapsActivity.class));

        }else if(id == R.id.nav_ajuda){

            startActivity(new Intent(context, AjudaMain.class));

        } else if (id == R.id.nav_configuracoes) {

            startActivity(new Intent(context, ConfiguracoesActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeColorMesesJanFev(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("preferencesLeituraDecorator",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int mes = 1;
        int dia = 1;
        boolean keepGoing = true;

        while(keepGoing){

            editor.putInt(mes+"/"+dia,1);
            dia++;

            if(dia == 31) {
                mes = 2;
                dia = 1;
            }

            if(dia == 16 && mes == 2)
                keepGoing = false;
        }

        editor.apply();
    }
}
