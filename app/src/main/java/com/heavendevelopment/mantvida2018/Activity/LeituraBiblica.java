package com.heavendevelopment.mantvida2018.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.heavendevelopment.mantvida2018.Dominio.Leitura;
import com.heavendevelopment.mantvida2018.Dominio.Versículo;
import com.heavendevelopment.mantvida2018.R;
import com.heavendevelopment.mantvida2018.Service.LeituraService;

import java.util.ArrayList;
import java.util.List;

import de.mrapp.android.bottomsheet.BottomSheet;

public class LeituraBiblica extends AppCompatActivity {

    Context context; 
    LeituraService leituraService;
    int tamFonteLeitura;
    boolean modoNoturno;
    int versaoBiblia;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

//    private int[] tabIcons = {
//            R.drawable.ic_fire,
//            R.drawable.ic_home,
//            R.drawable.ic_world_1
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura_biblica);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        SharedPreferences preferences = getSharedPreferences(getResources().getString(R.string.settings_preferences), MODE_PRIVATE);
        tamFonteLeitura = preferences.getInt("setting_tam_fonte",20);
        modoNoturno = preferences.getBoolean("setting_modo_noturno", false);

        //A VERSÃO 1 É A VERÃO NVI - NOVA VERSÃO INTERNACIONAL
        versaoBiblia = preferences.getInt("setting_version_bible", 1);
        leituraService = new LeituraService(context,versaoBiblia);

        //lembrar de verificar se a leitura está em modo noturno
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        //usado para pegar o dia e o mes que estão as leituras do dia
        Bundle bundle = getIntent().getExtras();
        int diaLeitura = bundle.getInt("diaLeitura");
        int mesLeitura = bundle.getInt("mesLeitura");


        LeituraManhaFragment manhaFragment = new LeituraManhaFragment();
        LeituraTardeFragment tardeFragment= new LeituraTardeFragment();
        LeituraNoiteFragment noiteFragment = new LeituraNoiteFragment();

        Bundle bundleManha = new Bundle();
        bundle.putInt("diaLeitura", diaLeitura);
        bundle.putInt("mesLeitura", mesLeitura);
        bundle.putInt("posicaoLeitura", 0);
        bundle.putInt("tamanhoFonte", tamFonteLeitura);
        bundle.putInt("versaoBiblia",versaoBiblia);
        bundle.putBoolean("modoNoturno", modoNoturno);


        Bundle bundleTarde = new Bundle();
        bundle.putInt("diaLeitura", diaLeitura);
        bundle.putInt("mesLeitura", mesLeitura);
        bundle.putInt("posicaoLeitura", 1);
        bundle.putInt("tamanhoFonte", tamFonteLeitura);
        bundle.putInt("versaoBiblia",versaoBiblia);
        bundle.putBoolean("modoNoturno", modoNoturno);



        Bundle bundleNoite = new Bundle();
        bundle.putInt("diaLeitura", diaLeitura);
        bundle.putInt("mesLeitura", mesLeitura);
        bundle.putInt("posicaoLeitura", 2);
        bundle.putInt("tamanhoFonte", tamFonteLeitura);
        bundle.putInt("versaoBiblia",versaoBiblia);
        bundle.putBoolean("modoNoturno", modoNoturno);

        manhaFragment.setArguments(bundleManha);
        tardeFragment.setArguments(bundleTarde);
        noiteFragment.setArguments(bundleNoite);


        adapter.addFragment(manhaFragment, "Manhã");
        adapter.addFragment(tardeFragment, "Tarde");
        adapter.addFragment(noiteFragment, "Noite");

        viewPager.setAdapter(adapter);
    }

//    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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


}
