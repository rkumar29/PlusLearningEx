package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.SubjectRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.SyllabusRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetUserDetailBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.SubjectBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.SyllabusBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.CustomRecyclerInterface;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyllabusFragment extends Fragment implements CustomRecyclerInterface {

    Toolbar mToolbar;
    RecyclerView mHeaderRecycler,mDetailsRecycler;
    List<SubjectBean> subjectBeansList;
    List<SyllabusBean> syllabusBeanList;
    SyllabusRecyclerAdapter syllabusRecyclerAdapter;
    SubjectRecyclerAdapter subjectRecyclerAdapter;
    PieChart pieChart;
    LinearLayout mStatusSyllabus;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);
        getUiObject(view);
        subjectRecycler();
        syllabusRecycler();
        setChart();


        return view;
    }


    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Syllabus");
        mHeaderRecycler = view.findViewById(R.id.rv_headers);
        mDetailsRecycler = view.findViewById(R.id.rv_syllabus);
        pieChart = view.findViewById(R.id.pie_chart_syllabus);
        mStatusSyllabus = view.findViewById(R.id.ll_status);

    }

    public void subjectRecycler(){
        subjectBeansList = new ArrayList<>();
        subjectBeansList.add(new SubjectBean("Physics",true));
        subjectBeansList.add(new SubjectBean("Chemistry",true));
        subjectBeansList.add(new SubjectBean("Maths",true));
        subjectBeansList.add(new SubjectBean("Biology",true));

        subjectRecyclerAdapter = new SubjectRecyclerAdapter(getActivity(),subjectBeansList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mHeaderRecycler.setLayoutManager(layoutManager);
        mHeaderRecycler.setItemAnimator(new DefaultItemAnimator());
        mHeaderRecycler.setAdapter(subjectRecyclerAdapter);
    }

    /*private void check(List<SubjectBean> subjectBeansList) {
        for (SubjectBean subject : subjectBeansList) {
            if (subject.getSubject().equalsIgnoreCase("All")){
                pieChart.setVisibility(View.VISIBLE);
            }
        }
    }
*/
    public void getUserDetails(){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        Map<String,String> fields = new HashMap<>();
        fields.put("action", ApiActions.ACTION_GET_SYLLABUS);
        fields.put("userid", String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));


        ApiClient.get().getUserDetails(fields).enqueue(new Callback<GetUserDetailBean>() {
            @Override
            public void onResponse(@NonNull Call<GetUserDetailBean> call, @NonNull Response<GetUserDetailBean> response) {
                ProDilog.getInstance().dismiss();

            }
            @Override
            public void onFailure(@NonNull Call<GetUserDetailBean> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->",t.toString());
            }
        });
    }
    public void syllabusRecycler(){
        syllabusBeanList = new ArrayList<>();
        syllabusBeanList.add(new SyllabusBean("Chapter - 1","complete"));
        syllabusBeanList.add(new SyllabusBean("Chapter - 2","ongoing"));
        syllabusBeanList.add(new SyllabusBean("Chapter - 3","not done"));
        syllabusRecyclerAdapter = new SyllabusRecyclerAdapter(getActivity(),syllabusBeanList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mDetailsRecycler.setLayoutManager(layoutManager);
        mDetailsRecycler.setItemAnimator(new DefaultItemAnimator());
        mDetailsRecycler.setAdapter(syllabusRecyclerAdapter);
    }

    public void setChart(){
        List<PieEntry> pieEntryList = new ArrayList<>();
        PieDataSet set;
        int[] colors = {Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red),
                Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow_green)};
        PieData data;
        Description description = new Description();


        pieEntryList.add(new PieEntry(43f, "Incomplete"));
        pieEntryList.add(new PieEntry(57f, "Complete"));

        set = new PieDataSet(pieEntryList, "");

        set.setColors(colors);
        set.setSliceSpace(2f);
        set.setValueTextSize(11f);

        data = new PieData(set);
        pieChart.setData(data);
        description.setText("Syllabus Report");
        pieChart.setDescription(description);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLUE);
        pieChart.invalidate();
    }



    @Override
    public void recyclerClick(int position, String subject) {

    }
}
