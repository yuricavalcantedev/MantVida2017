package com.heavendevelopment.mantvida20182;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Yuri on 17/02/2017.
 */

public class LeituraDecoratorRealizadoDecorator implements DayViewDecorator {

    private Context context;
    private HashSet<CalendarDay> dates;
    private int color;

    // 0 - não lida, 1 - lida, 2 - atrasada

    public LeituraDecoratorRealizadoDecorator(Context context, Collection<CalendarDay> dates) {

        this.dates = new HashSet<>(dates);
        this.context = context;

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        //TODO : REVER TODA A LÓGICA DESSA BIBLIOTECA, MAS É BOM LER A DOCUMENTAÇÃO
        //ESTOU INDO NO CAMINHHO CERTO
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new DotSpan(5,context.getResources().getColor(R.color.day_decorator_readed)));
    }
}
