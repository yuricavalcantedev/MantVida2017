package com.heavendevelopment.mantvida2017;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Yuri on 18/01/2017.
 */

public class LeituraDecoratorNaoRealizadoDecorator implements DayViewDecorator {

    private Context context;
    private HashSet<CalendarDay> dates;

    // 0 - não lida, 1 - lida, 2 - atrasada
    public LeituraDecoratorNaoRealizadoDecorator(Context context, Collection<CalendarDay> dates){
        this.dates = new HashSet<>(dates);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.addSpan(new DotSpan(5,context.getResources().getColor(R.color.day_decorator_unreaded)));
    }
}
