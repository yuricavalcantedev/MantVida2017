package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.EventoService;
import com.heavendevelopment.mantvida2017.Service.LeituraService;
import com.heavendevelopment.mantvida2017.Util;

import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;

    @BindView(R.id.fab_main)
    FloatingActionButton fabLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        context = this;
        ButterKnife.bind(this);

        fabLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //esse código está se repetindo no "PlanoLeituraMain", isso não é bom, depois tento resolver.
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                int diaHoje = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
                int mesHoje = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;
                int ano = gregorianCalendar.get(gregorianCalendar.YEAR);

                //só para quando as pessoas quiserem fazer alguma leitura, antes do dia primeiro, serem jogadas direto para o dia primeiro
                if(ano == 2016){
                    diaHoje = 1;
                    mesHoje = 1;
                }

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

        cardViewProjetoVida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProjetoVidaTexto.class));
            }
        });

        TextView tvDescricaoEvento = (TextView) findViewById(R.id.tv_main_descricao_evento);
        EventoService eventoService = new EventoService(context);


        //FAZER ISSO USANDO UMA LÓGICA PRA PEGAR O EVENTO ATUAL.
        String descricaoAtual = eventoService.getDescricaoEvento(1);
        tvDescricaoEvento.setText(descricaoAtual);

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

        Util util = new Util(this);

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

            util.toast("Função ainda não disponível");

        }else if (id == R.id.nav_configuracoes) {

            startActivity(new Intent(context, ConfiguracoesActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
