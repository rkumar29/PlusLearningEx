package pluslearning.bailiwick.rajesh.com.pluslearning.calender;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class DateDecorator implements DayViewDecorator {
    private int color = 0;
    private final HashSet<CalendarDay> dates;
    private ColorDrawable drawable;
    private Context context;

    public DateDecorator(Context context, int color, Collection<CalendarDay> dates){
        this.context = context;
        this.color = color;
        this.dates = new HashSet<>(dates);
        drawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        Log.e("Function Call","here");
        view.addSpan(new ForegroundColorSpan(color));
        //view.addSpan(new BackgroundColorSpan(Color.BLUE));
       // view.setBackgroundDrawable(Objects.requireNonNull(ContextCompat.getDrawable(context, R.color.red)));
    }
}
