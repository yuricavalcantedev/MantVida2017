package com.heavendevelopment.mantvida2017;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Yuri on 19/02/2017.
 */

public class DiaAtualDecorator implements DayViewDecorator {

    private Context context;
    private HashSet<CalendarDay> dates;
    private int color;

    // 0 - não lida, 1 - lida, 2 - atrasada

    public DiaAtualDecorator(Context context, Collection<CalendarDay> dates) {

        this.dates = new HashSet<>(dates);
        this.context = context;

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        //TODO : REVER TODA A LÓGICA DESSE NEGÓCIO, MAS É BOM LER A DOCUMENTAÇÃO
        //TO INDO NO CAMINHHO CERTO
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new DotSpan(5, context.getResources().getColor(R.color.day_decorator_today)));
    }
}