package pluslearning.bailiwick.rajesh.com.pluslearning.student_info;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Attendence;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.AttendenceResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.calender.DateDecorator;
import pluslearning.bailiwick.rajesh.com.pluslearning.calender.Dates;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment implements View.OnClickListener {

    MaterialCalendarView calendarView;
    PieChart pieChart;
    Toolbar mToolbar;
    Button mMonthly, mOverall;
    TextView tv_presentdays,tv_absentdays,tv_holidays;
    Attendence attendence ;
    AttendenceResponse attendenceResponse ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        getUiObject(view);
        getAttendence("1", String.valueOf(getMonth()));
        getAttendenceMonthly("0", String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));


        return view;
    }

    public void getUiObject(View view) {
        calendarView = view.findViewById(R.id.cal_view);
        pieChart = view.findViewById(R.id.pie_chart);
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Attendance");
        mMonthly = view.findViewById(R.id.btn_monthly);
        mOverall = view.findViewById(R.id.btn_overall);

        tv_presentdays =view.findViewById(R.id.tv_presentdays);
        tv_absentdays =view.findViewById(R.id.tv_absentdays);
        tv_holidays =view.findViewById(R.id.tv_holidays);
        mMonthly.setOnClickListener(this);
        mOverall.setOnClickListener(this);


         int i = calendarView.getCurrentDate().getMonth();
         int y = calendarView.getCurrentDate().getYear();


        tv_presentdays.setText("Present Days : 19");
        tv_absentdays.setText("Absent Days : 5");
        tv_holidays.setText("HoliDays : 5");
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                //Do something like this
                Log.e("Widget" ,date.getMonth() +  "");
                getAttendenceMonthly("0", String.valueOf(date.getMonth()));


            }
        });
        //calendarView.ge

    }

    private int getMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }



    public void getAttendence(final String overview , String month_id){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        Map<String,String> fields = new HashMap<>();
        fields.put(Constant.ACTION, ApiActions.ACTION_GET_ATTENDENCE);
        fields.put(Constant.MONTH_ID, month_id);
        fields.put("overview",overview);
        fields.put("class","10");
        fields.put(Constant.USER_ID, "4");

        ApiClient.get().getAttendence(fields).enqueue(new Callback<Attendence>() {
            @Override
            public void onResponse(@NonNull Call<Attendence> call, @NonNull Response<Attendence> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus() == 1) {
                    attendence = response.body();
                    mMonthly.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.green_yellow));
                    mOverall.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.gainsboro));
                    float absentTotal  = (float) attendence.getCurrentMonth().getAbsentTotal();
                    float presentTotal  = (float) attendence.getCurrentMonth().getPresentTotal();
                    float numberOfHolidays  = (float) attendence.getCurrentMonth().getNumberOfHolidays();
                    setPieChart(absentTotal,presentTotal,numberOfHolidays);

                }
            }

            @Override
            public void onFailure(@NonNull Call<Attendence> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->", t.toString());
            }

        });}


    public void getAttendenceMonthly(final String overview , String month_id){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        Map<String,String> fields = new HashMap<>();
        fields.put(Constant.ACTION, ApiActions.ACTION_GET_ATTENDENCE);
        fields.put(Constant.MONTH_ID, "0"+month_id);
        fields.put("overview",overview);
        fields.put("class","10");
        fields.put(Constant.USER_ID, "4");

        ApiClient.get().getAttendenceMontly(fields).enqueue(new Callback<AttendenceResponse>() {
            @Override
            public void onResponse(@NonNull Call<AttendenceResponse> call, @NonNull Response<AttendenceResponse> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus() == 1) {
                    attendenceResponse = response.body();
                    int year = 0, month = 0,day = 0;
                    int yearH = 0, monthH = 0,dayH = 0;
                    int yearA = 0, monthA = 0,dayA = 0;

                    for ( String date :response.body().getAttendence().getPresentDays()){
                          year = Integer.parseInt(date.substring(0,4));
                          month = Integer.parseInt(date.substring(5,7));
                          day = Integer.parseInt(date.substring(8));
                     //   Log.e("Present  Dayy :",day + "month :" +month +"Year  :" + year +"");
                        getPresentDays(day,month,year);

                    }
                   // Log.e("Dayy :",day + "month :" +month +"Year  :" + year +"");

                    for (String date : response.body().getAttendence().getAbsentDays()){
                        yearA = Integer.parseInt(date.substring(0,4));
                        monthA = Integer.parseInt(date.substring(5,7));
                        dayA = Integer.parseInt(date.substring(8));

                      //  Log.e("Absent  Dayy :",dayA + "month :" +monthA +"Year  :" + yearA +"");
                        getAbsentDays(dayA,monthA,yearA);

                    }
                    for (String date : response.body().getAttendence().getNumberOfHolidays()){
                        yearH = Integer.parseInt(date.substring(0,4));
                        monthH = Integer.parseInt(date.substring(5,7));
                        dayH = Integer.parseInt(date.substring(8));
                        //Log.e("Holi  Dayy :",dayH + "month :" +monthH +"Year  :" + yearH +"");
                        getHolidays(dayH,monthH,yearH);

                    }

                }
                else{
                    Log.e("Date ","jlbvvjvjhvhuvhyuv");

                }
            }

            @Override
            public void onFailure(@NonNull Call<AttendenceResponse> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->", t.toString());
            }

        });}

     private void getHolidays(int dayH, int monthH, int yearH) {

        List<Dates> holidaDays = new ArrayList<Dates>();
        //absentDays.clear();
        holidaDays.add(new Dates(dayH,monthH,yearH));
        List<CalendarDay> list3 = new ArrayList<CalendarDay>();
        Calendar calendar3 = Calendar.getInstance();


        for (Dates dates3 : holidaDays) {
            // might be a more elegant way to do this part, but this is very explicit
            int years = dates3.getYear();
            int months = dates3.getMonths(); // months are 0-based in Calendar
            int day = dates3.getDate();

            calendar3.set(years, months, day);
            CalendarDay calendarDay3 = CalendarDay.from(calendar3);
            list3.add(calendarDay3);
        }
        calendarView.addDecorator(new DateDecorator(getActivity(), getColor(getActivity(), R.color.deep_blue), list3));

    }

    private void getAbsentDays(int dayA, int month, int year) {
        List<Dates> absentDays = new ArrayList<Dates>();
        // absentDays.clear();
        absentDays.add(new Dates(dayA,month,year));
        List<CalendarDay> list2 = new ArrayList<CalendarDay>();
        Calendar calendar2 = Calendar.getInstance();

        for (Dates dates2 : absentDays) {
            // might be a more elegant way to do this part, but this is very explicit
            int years = dates2.getYear();
            int months = dates2.getMonths(); // months are 0-based in Calendar
            int day = dates2.getDate();

            calendar2.set(years, months, day);
            CalendarDay calendarDay2 = CalendarDay.from(calendar2);
            list2.add(calendarDay2);
        }
        calendarView.addDecorator(new DateDecorator(getActivity(), getColor(getActivity(), R.color.red), list2));

    }


    private void getPresentDays(int day, int month,int year){
        List<Dates> presentDays = new ArrayList<Dates>();
        // presentDays.clear();
        presentDays.add(new Dates(day,month,year));
        List<CalendarDay> list = new ArrayList<CalendarDay>();
        Calendar calendar = Calendar.getInstance();

        for (Dates dates : presentDays) {
            // might be a more elegant way to do this part, but this is very explicit
            int years = dates.getYear();
            int months = dates.getMonths(); // months are 0-based in Calendar
            int day_ = dates.getDate();

            calendar.set(years, months, day_);
            CalendarDay calendarDay = CalendarDay.from(calendar);
            list.add(calendarDay);
        }
        calendarView.addDecorator(new DateDecorator(getActivity(), getColor(getActivity(), R.color.green), list));

    }

    public void setPieChart(float absent , float  present , float off) {
        List<PieEntry> pieEntryList = new ArrayList<>();
        PieDataSet set;
        int[] colors = {Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red),
                Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow_green), Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow)};
        PieData data;
        Description description = new Description();


        pieEntryList.add(new PieEntry(absent, "Absent"));
        pieEntryList.add(new PieEntry(present, "Present"));
        pieEntryList.add(new PieEntry(off, "Holiday"));

        set = new PieDataSet(pieEntryList, "");

        set.setColors(colors);
        set.setSliceSpace(2f);
        set.setValueTextSize(11f);

        data = new PieData(set);
        pieChart.setData(data);
        description.setText("Attendance Result");
        pieChart.setDescription(description);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLUE);
        pieChart.invalidate();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_monthly:
                mMonthly.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.green_yellow));
                mOverall.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.gainsboro));
                float absentTotal  = (float) attendence.getCurrentMonth().getAbsentTotal();
                float presentTotal  = (float) attendence.getCurrentMonth().getPresentTotal();
                float numberOfHolidays  = (float) attendence.getCurrentMonth().getNumberOfHolidays();
                setPieChart(absentTotal,presentTotal,numberOfHolidays);

                break;

            case R.id.btn_overall:

                mMonthly.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.gainsboro));
                mOverall.setBackgroundColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.green_yellow));

                float absentTotalO  = (float) attendence.getOverall().getAbsentTotal();
                float presentTotalO  = (float) attendence.getOverall().getPresentTotal();
                float numberOfHolidaysO  = (float) attendence.getOverall().getNumberOfHolidays();
                setPieChart(absentTotalO,presentTotalO,numberOfHolidaysO);
                break;
        }
    }
}
