package pluslearning.bailiwick.rajesh.com.pluslearning.time_table;


import android.annotation.SuppressLint;
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
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.TimeTableRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TimeTableBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlTimeTableFragment extends Fragment {

    Spinner mWeekSpinner;
    RadioButton mMon,mTues,mWed,mThu,mFri,mSat;
    RecyclerView mMonRecycler,mTuesRecycler,mWedRecycler,mThuRecycler,mFriRecycler,mSatRecycler;
    List<TimeTableBean> timeTableBeans;
    TimeTableRecyclerAdapter timeTableRecyclerAdapter;
    Toolbar mToolbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pl_time_table, container, false);

        getUiObject(view);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String day = simpleDateFormat.format(date);

        settingSpinner();
        setRecycler();

        checkDay(day);

        return view;
    }

    private void checkDay(String day) {
      //  Toast.makeText(getActivity(), day, Toast.LENGTH_SHORT).show();
        if (day.equalsIgnoreCase("Monday")){

            mMon.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mMonRecycler.setPadding(3,3,3,3);
            mMonRecycler.setBackgroundResource(R.drawable.blue_rectangle);

        }else if (day.equalsIgnoreCase("Tuesday")){

            mTues.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mTuesRecycler.setPadding(3,3,3,3);
            mTuesRecycler.setBackgroundResource(R.drawable.blue_rectangle);

        }else if (day.equalsIgnoreCase("Wednesday")){

            mWed.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mWedRecycler.setPadding(3,3,3,3);
            mWedRecycler.setBackgroundResource(R.drawable.blue_rectangle);

        }else if (day.equalsIgnoreCase("Thursday")){

            mThu.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mThuRecycler.setPadding(3,3,3,3);
            mThuRecycler.setBackgroundResource(R.drawable.blue_rectangle);

        }else if (day.equalsIgnoreCase("Friday")){

            mFri.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mFriRecycler.setPadding(3,3,3,3);
            mFriRecycler.setBackgroundResource(R.drawable.blue_rectangle);

        }else {

            mSat.setTextColor(Objects.requireNonNull(getActivity()).getResources().getColor(R.color.blue));
            mSatRecycler.setPadding(3,3,3,3);
            mSatRecycler.setBackgroundResource(R.drawable.blue_rectangle);
        }

    }

    private void getUiObject(View view){
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


        mMon.setChecked(true);
        mTues.setChecked(true);
        mWed.setChecked(true);
        mThu.setChecked(true);
        mFri.setChecked(true);
        mSat.setChecked(true);
    }

    public void settingSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()),
                R.layout.spinner_item,getActivity().getResources().getStringArray(R.array.select_week)){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView)v).setTextSize(12);
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView)v).setGravity(Gravity.CENTER);

                return v;
            }
        };

        mWeekSpinner.setAdapter(adapter);
    }

    public void setRecycler(){
        timeTableBeans = new ArrayList<>();
        timeTableBeans.add(new TimeTableBean("2:00Pm - 3:00PM","Mathematics"));
        timeTableBeans.add(new TimeTableBean("2:00Pm - 3:00PM","Physics"));
        timeTableBeans.add(new TimeTableBean("2:00Pm - 3:00PM","Chemistry"));
        timeTableBeans.add(new TimeTableBean("2:00Pm - 3:00PM","English"));
        timeTableBeans.add(new TimeTableBean("2:00Pm - 3:00PM","Hindi"));

        timeTableRecyclerAdapter = new TimeTableRecyclerAdapter(getActivity(),timeTableBeans);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mMonRecycler.setLayoutManager(layoutManager);
        mMonRecycler.setItemAnimator(new DefaultItemAnimator());
        mMonRecycler.setAdapter(timeTableRecyclerAdapter);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mTuesRecycler.setLayoutManager(layoutManager1);
        mTuesRecycler.setItemAnimator(new DefaultItemAnimator());
        mTuesRecycler.setAdapter(timeTableRecyclerAdapter);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mWedRecycler.setLayoutManager(layoutManager2);
        mWedRecycler.setItemAnimator(new DefaultItemAnimator());
        mWedRecycler.setAdapter(timeTableRecyclerAdapter);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mThuRecycler.setLayoutManager(layoutManager3);
        mThuRecycler.setItemAnimator(new DefaultItemAnimator());
        mThuRecycler.setAdapter(timeTableRecyclerAdapter);

        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mFriRecycler.setLayoutManager(layoutManager4);
        mFriRecycler.setItemAnimator(new DefaultItemAnimator());
        mFriRecycler.setAdapter(timeTableRecyclerAdapter);

        RecyclerView.LayoutManager layoutManager5 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mSatRecycler.setLayoutManager(layoutManager5);
        mSatRecycler.setItemAnimator(new DefaultItemAnimator());
        mSatRecycler.setAdapter(timeTableRecyclerAdapter);

    }

}
