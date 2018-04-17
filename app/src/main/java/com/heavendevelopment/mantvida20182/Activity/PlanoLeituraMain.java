package com.heavendevelopment.mantvida20182.Activity;

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

import com.heavendevelopment.mantvida20182.DiaAtualDecorator;
import com.heavendevelopment.mantvida20182.Dominio.EstadoLeitura;
import com.heavendevelopment.mantvida20182.Dominio.Leitura;
import com.heavendevelopment.mantvida20182.LeituraDecoratorNaoRealizadoDecorator;
import com.heavendevelopment.mantvida20182.LeituraDecoratorRealizadoDecorator;
import com.heavendevelopment.mantvida20182.R;
import com.heavendevelopment.mantvida20182.Service.LeituraService;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

public class PlanoLeituraMain extends AppCompatActivity {

    Context context;
    LeituraService leituraService;
    ArrayList<Leitura> listLeituraHoje;
    MaterialCalendarView calendarView;
    private int anoAtual = 2018;
    String[] labelsMeses = new String[]{"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    String[] labelsDias = new String[]{"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};

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

        String refLeituraDeHoje = leituraService.getRefReadingOfDay(diaHoje,mesHoje);
        String dataLeitura = diaHoje + " de " + chooseMonthName(mesHoje) + " de " + anoAtual;

        TextView tvLeituraHoje = (TextView) findViewById(R.id.tv_leitura_diaria_plano_leitura_main);
        tvLeituraHoje.setText(refLeituraDeHoje);

        TextView tvData = (TextView) findViewById(R.id.tv_data_plano_leitura_main);
        tvData.setText(dataLeitura);

        initializeCalendarView();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                final int dia = date.getDay();
                final int mes = date.getMonth() + 1;

                listLeituraHoje = leituraService.getReadingOfDay(dia, mes);

                String refLeituraDataSelecionada = leituraService.getRefReadingOfDay(dia,mes);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Plano de Leitura")
                        .setMessage("Leitura: " + refLeituraDataSelecionada);
                builder.setPositiveButton("Ler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LeituraService leituraService = new LeituraService(context);
                        leituraService.setEstadoLeituraDecorator(mes - 1,dia,1);

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

    protected void onResume(){
        super.onResume();
        changeColorOfDaysDecorator();

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

    private void initializeCalendarView(){

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(anoAtual, 0, 1))
                .setMaximumDate(CalendarDay.from(anoAtual, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.setTitleMonths(labelsMeses);
        calendarView.setWeekDayLabels(labelsDias);


    }

    private void changeColorOfDaysDecorator(){

        calendarView.invalidateDecorators();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        int diaAtual = gregorianCalendar.get(gregorianCalendar.DAY_OF_MONTH);
        int mesAtual = gregorianCalendar.get(gregorianCalendar.MONTH);

        HashSet<CalendarDay> leiturasRealizadasDecorator = new HashSet<>();
        HashSet<CalendarDay> leiturasNaoRealizadasDecorator = new HashSet<>();
        HashSet<CalendarDay> leituraHoje = new HashSet<>();

        LeituraService leituraService = new LeituraService(context);
        CalendarDay calendarDay;

        List<EstadoLeitura> listEstadoLeitura = leituraService.getEstadoLeituraDecorator(mesAtual,diaAtual);

        //TODO : Por enquanto vou deixar ele indo até o mês de Junho, mas na verdade, esse método eu tenho que colocar
        //quando carregar a activity e no clique de passar o mês.
        leituraHoje.add(new CalendarDay(anoAtual,5,diaAtual));

        for(int i = 0; i < listEstadoLeitura.size(); i++){

            EstadoLeitura objEstadoLeitura = listEstadoLeitura.get(i);
            objEstadoLeitura.setId(0);

            int estadoLeitura = objEstadoLeitura.getEstado();


                if (mesAtual == objEstadoLeitura.getMes() && objEstadoLeitura.getDia() < diaAtual && estadoLeitura == 0 ||
                        objEstadoLeitura.getMes() < mesAtual && estadoLeitura == 0) {
                    leituraService.setEstadoLeituraDecorator(objEstadoLeitura.getMes(), objEstadoLeitura.getDia(), 2);
                    estadoLeitura = 2;
                }


            calendarDay = new CalendarDay(anoAtual,objEstadoLeitura.getMes(),objEstadoLeitura.getDia());

            if(estadoLeitura == 1)
                leiturasRealizadasDecorator.add(calendarDay);
            else if(estadoLeitura == 2)
                leiturasNaoRealizadasDecorator.add(calendarDay);
        }

        calendarView.addDecorators(new LeituraDecoratorRealizadoDecorator(context,leiturasRealizadasDecorator), new LeituraDecoratorNaoRealizadoDecorator(context,leiturasNaoRealizadasDecorator), new DiaAtualDecorator(context,leituraHoje));

    }

    private String chooseMonthName(int month){

        String monthName = "";

        switch (month){

            case 1 : monthName ="Janeiro";
                break;

            case 2 : monthName ="Fevereiro";
                break;

            case 3 : monthName ="Março";
                break;

            case 4 : monthName ="Abril";
                break;

            case 5 : monthName ="Maio";
                break;

            case 6 : monthName ="Junho";
                break;

            case 7 : monthName ="Julho";
                break;

            case 8 : monthName ="Agosto";
                break;

            case 9 : monthName ="Setembro";
                break;

            case 10 : monthName ="Outubro";
                break;

            case 11 : monthName ="Novembro";
                break;

            case 12 : monthName ="Dezembro";
                break;
        }

        return monthName;

    }


}