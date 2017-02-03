package com.heavendevelopment.mantvida2017;

import android.graphics.Typeface;
import android.text.style.BackgroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Yuri on 18/01/2017.
 */

public class OneDayDecorator implements DayViewDecorator {

    private CalendarDay date;

    public OneDayDecorator() {
        date = CalendarDay.today();
    }

    public OneDayDecorator(int mes, int dia){

        mes -= 1;
        date = CalendarDay.from(2017,mes,dia);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.addSpan(new BackgroundColorSpan(android.R.color.holo_red_dark));

    }

//    /**
//     * We're changing the internals, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
//     */

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}
