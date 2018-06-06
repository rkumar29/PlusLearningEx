package pluslearning.bailiwick.rajesh.com.pluslearning.time_table;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.TimeTableRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Frus;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetTimeTable;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Mon;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Sat;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Thu;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TimeTableBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Tue;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Wed;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTableFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner mWeekSpinner;
    RadioButton mMon, mTues, mWed, mThu, mFri, mSat;
    RecyclerView mMonRecycler, mTuesRecycler, mWedRecycler, mThuRecycler, mFriRecycler, mSatRecycler;
    List<TimeTableBean> mMonBeans, mTuesBeans, mWedBeans, mThursBeans, mFriBeans, mSatBeans;
    TimeTableRecyclerAdapter timeTableRecyclerAdapter;
    Toolbar mToolbar;
    List<String> weeks = new ArrayList<>();
    LinearLayout mLayoutTimeTable;
    String mon = null, tues = null, wed = null, thu = null, fri = null, sat = null, current_day = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pl_time_table, container, false);
        getUiObject(view);

        getTimeTable(Calendar.getInstance().get(Calendar.WEEK_OF_MONTH),Calendar.getInstance().get(Calendar.MONTH));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yy", Locale.getDefault());
        Date date = new Date();
        current_day = simpleDateFormat.format(date);
        settingSpinner();
        setDatesDay();
        //  getDateDaysMonth();

        return view;
    }

    private void setDatesDay() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            if (i == 0) {
                mon = simpleDateFormat.format(calendar.getTime());
            } else if (i == 1) {
                tues = simpleDateFormat.format(calendar.getTime());
            } else if (i == 2) {
                wed = simpleDateFormat.format(calendar.getTime());
            } else if (i == 3) {
                thu = simpleDateFormat.format(calendar.getTime());
            } else if (i == 4) {
                fri = simpleDateFormat.format(calendar.getTime());
            } else if (i == 5) {
                sat = simpleDateFormat.format(calendar.getTime());
            }
        }
        mMon.setText(mon);
        mTues.setText(tues);
        mWed.setText(wed);
        mThu.setText(thu);
        mFri.setText(fri);
        mSat.setText(sat);

        //  checkCurrentDay(mWeekSpinner.getSelectedItemPosition(),day);
    }

    public void checkCurrentDay(int position, String day) {

        if (position == Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1) {

            if (day.equals(mon)) {
                mMon.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mMonRecycler.setPadding(3, 3, 3, 3);
                mMonRecycler.setBackgroundResource(R.drawable.blue_rectangle);

            } else if (day.equals(tues)) {
                mTues.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mTuesRecycler.setPadding(3, 3, 3, 3);
                mTuesRecycler.setBackgroundResource(R.drawable.blue_rectangle);

            } else if (day.equals(wed)) {
                mWed.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mWedRecycler.setPadding(3, 3, 3, 3);
                mWedRecycler.setBackgroundResource(R.drawable.blue_rectangle);

            } else if (day.equals(thu)) {
                mThu.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mThuRecycler.setPadding(3, 3, 3, 3);
                mThuRecycler.setBackgroundResource(R.drawable.blue_rectangle);

            } else if (day.equals(fri)) {
                mFri.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mFriRecycler.setPadding(3, 3, 3, 3);
                mFriRecycler.setBackgroundResource(R.drawable.blue_rectangle);

            } else if (day.equals(sat)) {
                mSat.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
                mSatRecycler.setPadding(3, 3, 3, 3);
                mSatRecycler.setBackgroundResource(R.drawable.blue_rectangle);
            }

        } else {
            mMon.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mMonRecycler.setPadding(0, 0, 0, 0);

            mTues.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mTuesRecycler.setPadding(0, 0, 0, 0);

            mWed.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mWedRecycler.setPadding(0, 0, 0, 0);

            mThu.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mThuRecycler.setPadding(0, 0, 0, 0);

            mFri.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mFriRecycler.setPadding(0, 0, 0, 0);

            mSat.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.black));
            mSatRecycler.setPadding(0, 0, 0, 0);

        }
    }

    private void getUiObject(View view) {
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Time Table");
        mWeekSpinner = view.findViewById(R.id.spinner_week);
        mMon = view.findViewById(R.id.rb_mon);
        mTues = view.findViewById(R.id.rb_tues);
        mWed = view.findViewById(R.id.rb_wed);
        mThu = view.findViewById(R.id.rb_thu);
        mFri = view.findViewById(R.id.rb_fri);
        mSat = view.findViewById(R.id.rb_sat);
        mMonRecycler = view.findViewById(R.id.rv_monday);
        mTuesRecycler = view.findViewById(R.id.rv_tuesday);
        mWedRecycler = view.findViewById(R.id.rv_wednesday);
        mThuRecycler = view.findViewById(R.id.rv_thursday);
        mFriRecycler = view.findViewById(R.id.rv_friday);
        mSatRecycler = view.findViewById(R.id.rv_saturday);
        mLayoutTimeTable = view.findViewById(R.id.ll_main_layout_time_table);

        mWeekSpinner.setOnItemSelectedListener(this);

        mMon.setChecked(true);
        mTues.setChecked(true);
        mWed.setChecked(true);
        mThu.setChecked(true);
        mFri.setChecked(true);
        mSat.setChecked(true);
    }

    public void settingSpinner() {

        for (int i = 1; i <= numberOfWeeks(); i++) {
            weeks.add("Week " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                R.layout.spinner_item, weeks) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(12);
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
        };

        mWeekSpinner.setAdapter(adapter);
        mWeekSpinner.setSelection(Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1);
    }

    public void getTimeTable(int week_id, int month_id) {

        Map<String, String> fields = new HashMap<>();
        fields.put(Constant.ACTION, ApiActions.ACTION_GET_TIME_TABLE);
        fields.put(Constant.CLASS, "10");
        fields.put(Constant.USER_ID, String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
        fields.put("month_id", String.valueOf(month_id));
        fields.put("week_id", String.valueOf(week_id));

        mMonBeans = new ArrayList<>();
        mTuesBeans = new ArrayList<>();
        mWedBeans = new ArrayList<>();
        mThursBeans = new ArrayList<>();
        mFriBeans = new ArrayList<>();
        mSatBeans = new ArrayList<>();

        ProDilog.getInstance().show(getActivity(), "Loading...");
        ApiClient.get().gettimetable(fields).enqueue(new Callback<GetTimeTable>() {
            @Override
            public void onResponse(@NonNull Call<GetTimeTable> call, @NonNull Response<GetTimeTable> response) {
                mLayoutTimeTable.setVisibility(View.VISIBLE);
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus() == 1) {

                    for (Mon mon : response.body().getTimetable().getMon()) {
                        mMonBeans.add(new TimeTableBean(mon.getTime(), mon.getSubject()));
                    }
                    setAdapter(mMonBeans, mMonRecycler);

                    for (Tue tue : response.body().getTimetable().getTue()) {
                        mTuesBeans.add(new TimeTableBean(tue.getTime(), tue.getSubject()));
                    }
                    setAdapter(mTuesBeans, mTuesRecycler);

                    for (Wed wed : response.body().getTimetable().getWed()) {
                        mWedBeans.add(new TimeTableBean(wed.getTime(), wed.getSubject()));
                    }
                    setAdapter(mWedBeans, mWedRecycler);

                    for (Thu thu : response.body().getTimetable().getThu()) {
                        mThursBeans.add(new TimeTableBean(thu.getTime(), thu.getSubject()));
                    }
                    setAdapter(mThursBeans, mThuRecycler);

                    for (Frus fri : response.body().getTimetable().getFri()) {
                        mFriBeans.add(new TimeTableBean(fri.getTime(), fri.getSubject()));
                    }
                    setAdapter(mFriBeans, mFriRecycler);

                    for (Sat sat : response.body().getTimetable().getSat()) {
                        mSatBeans.add(new TimeTableBean(sat.getTime(), sat.getSubject()));
                    }
                    setAdapter(mSatBeans, mSatRecycler);
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetTimeTable> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setAdapter(List<TimeTableBean> timeTableBeans, RecyclerView mMonRecycler) {
        timeTableRecyclerAdapter = new TimeTableRecyclerAdapter(getActivity(), timeTableBeans);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mMonRecycler.setLayoutManager(layoutManager);
        mMonRecycler.setItemAnimator(new DefaultItemAnimator());
        mMonRecycler.setAdapter(timeTableRecyclerAdapter);
    }

    public int numberOfWeeks() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int current_week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) - 1;
        if (mWeekSpinner.getSelectedItemPosition() == current_week) {

            setDatesDay();
            checkCurrentDay(position, current_day);

        } else if (mWeekSpinner.getSelectedItemPosition() > current_week) {

            int diff = mWeekSpinner.getSelectedItemPosition() - current_week;
            spinnerInc(diff);
            checkCurrentDay(position, current_day);
            getTimeTable(mWeekSpinner.getSelectedItemPosition(), Calendar.getInstance().get(Calendar.MONTH));

        } else if (mWeekSpinner.getSelectedItemPosition() < current_week) {

            int diff = mWeekSpinner.getSelectedItemPosition() - current_week;
            spinnerDec(diff);
            checkCurrentDay(position, current_day);
            getTimeTable(mWeekSpinner.getSelectedItemPosition(), Calendar.getInstance().get(Calendar.MONTH));

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

   /* public void getDateDaysMonth() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getFirstDayOfWeek());
        for (int i = 0; i < Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
         //   Log.e("Day" + i + " ", simpleDateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);

        }
    }*/

    public void spinnerInc(int increment) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/yy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7 * increment);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            if (i == 0) {
                mon = sdf.format(calendar.getTime());
            } else if (i == 1) {
                tues = sdf.format(calendar.getTime());
            } else if (i == 2) {
                wed = sdf.format(calendar.getTime());
            } else if (i == 3) {
                thu = sdf.format(calendar.getTime());
            } else if (i == 4) {
                fri = sdf.format(calendar.getTime());
            } else if (i == 5) {
                sat = sdf.format(calendar.getTime());
            }
        }

        mMon.setText(mon);
        mTues.setText(tues);
        mWed.setText(wed);
        mThu.setText(thu);
        mFri.setText(fri);
        mSat.setText(sat);

    }

    public void spinnerDec(int decrement) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd/MM/yy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7 * decrement);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.DATE, 1);
            if (i == 0) {
                mon = sdf.format(calendar.getTime());
            } else if (i == 1) {
                tues = sdf.format(calendar.getTime());
            } else if (i == 2) {
                wed = sdf.format(calendar.getTime());
            } else if (i == 3) {
                thu = sdf.format(calendar.getTime());
            } else if (i == 4) {
                fri = sdf.format(calendar.getTime());
            } else if (i == 5) {
                sat = sdf.format(calendar.getTime());
            }
        }

        mMon.setText(mon);
        mTues.setText(tues);
        mWed.setText(wed);
        mThu.setText(thu);
        mFri.setText(fri);
        mSat.setText(sat);

    }


}
