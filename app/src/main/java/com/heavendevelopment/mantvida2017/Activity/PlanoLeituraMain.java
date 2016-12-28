package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.heavendevelopment.mantvida2017.Dominio.Leitura;
import com.heavendevelopment.mantvida2017.R;
import com.heavendevelopment.mantvida2017.Service.LeituraService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlanoLeituraMain extends AppCompatActivity {

    Context context;
    LeituraService leituraService;
    ArrayList<Leitura> listLeituraHoje;
    MaterialCalendarView calendarView;
    String[] labelsMeses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    String[] labelsDias = new String[]{"Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_leitura_main);

        context = this;
        leituraService = new LeituraService(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Plano de Leitura");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int diaHoje = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
        int mesHoje = gregorianCalendar.get(gregorianCalendar.MONTH) + 1;

        //String leituraDeHoje = leituraService.getReadingOfDay(diaHoje,mesHoje);

        TextView tvLeituraHoje = (TextView) findViewById(R.id.tv_leitura_diaria_plano_leitura_main);
        tvLeituraHoje.setText("ainda não implementado");


        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SATURDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2017, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.setTitleMonths(labelsMeses);
        calendarView.setWeekDayLabels(labelsDias);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                listLeituraHoje = new ArrayList<Leitura>();

                int dia = date.getDay();
                int mes = date.getMonth() + 1;

                listLeituraHoje = leituraService.getReadingOfDay(dia, mes);

                String tituloLeituraDiaria = "";

                for(int i = 0; i < listLeituraHoje.size(); i++)
                    tituloLeituraDiaria += listLeituraHoje.get(i).getTitulo()+ " ";


                final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Plano de Leitura")
                        .setMessage("Leitura : " + tituloLeituraDiaria);
                builder.setPositiveButton("Ler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Bundle bundle = new Bundle();
                        bundle.putInt("diaLeitura", listLeituraHoje.get(0).getDia());
                        bundle.putInt("mesLeitura", listLeituraHoje.get(0).getMes());

                        Intent intent = new Intent(context, LeituraBiblica.class);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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
        }

        return super.onOptionsItemSelected(item);
    }


    private String chooseStage(int month) {

        String stage;

        if (month < 4)
            stage = "Etapa 1";
        else if (month < 7)
            stage = "Etapa 2";
        else if (month < 9)
            stage = "Etapa 3";
        else
            stage = "Etapa 4";

        return stage;
    }

}