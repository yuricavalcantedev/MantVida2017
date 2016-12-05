package com.heavendevelopment.mantvida2017.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heavendevelopment.mantvida2017.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class PlanoLeituraMain extends AppCompatActivity {

    Context context;
    MaterialCalendarView calendarView;
    String [] labelsMeses = new String[]{"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
                                        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};

    String [] labelsDias = new String[]{"Seg","Ter","Qua","Qui","Sex","Sáb","Dom"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano_leitura_main);

        context = this;

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

                int dia = date.getDay();
                int mes = date.getMonth()+1;
                int ano = date.getYear();

                String dataSelecionada = dia + "/" + mes + "/" + ano;

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Plano de Leitura")
                        .setMessage("Função ainda não implementada. \nData selecionada : " +dataSelecionada );
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
}
