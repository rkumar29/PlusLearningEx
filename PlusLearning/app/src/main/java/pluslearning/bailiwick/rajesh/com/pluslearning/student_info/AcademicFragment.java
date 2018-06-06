package pluslearning.bailiwick.rajesh.com.pluslearning.student_info;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.SubjectRadioAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.Result;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TestResultResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TestTypeResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.TestTypes;
import pluslearning.bailiwick.rajesh.com.pluslearning.interfaces.CustomRecyclerInterface;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AcademicFragment extends Fragment implements CustomRecyclerInterface {

    BarChart barChart;
    private Spinner sp_testtype;
    ProgressDialog progressDialog;
    private RadioGroup rg_sujgroup;
    private RecyclerView rv_subjects;
    private TextView tv_rank;
   // private ArrayList<TestType> testTypes;
    private List<TestTypes> testTypes;
    private List<Result> testResult;
    private ArrayList<String> showtestType;
    private ArrayAdapter arrayAdapter ;
    private SubjectRadioAdapter subjectRadioAdapter;
    private RadioButton rb_all;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_academic, container, false);
        progressDialog = new ProgressDialog(getActivity());
        testTypes = new ArrayList<TestTypes>();
        showtestType =new ArrayList<>();
        testResult =new ArrayList<>();
        getUiObject(view);
        getTestType();
        //setAdapter();
        setSubjectAdapter();
        //setGraph();
        clickEvents();
        return view;
    }

    private void clickEvents() {
        sp_testtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //testTypes.get(position).getTestId();
               // Log.e("ID",testTypes.get(position).getTestId());
                //getResult(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rg_sujgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_all:
                     //   setGraph(testResult.get(position).getMarksObtained());
                        break;
                    case R.id.rb_physics:
                       // setGraphIndivisual(6f,30f);
                        break;
                    case R.id.rb_chemistry:
                      //  setGraphIndivisual(6f,60f);

                        break;
                    case R.id.rb_maths:
                       // setGraphIndivisual(6f,80f);

                        break;

                        default:

                            //setGraph(testResult.get(position).getMarksObtained());
                            break;

                }
            }
        });
    }

    private void getTestType() {
        ProDilog.getInstance().show(getActivity(), "Loading...");
        Map<String, String> fields = new HashMap<>();
        fields.put(Constant.ACTION, ApiActions.ACTION_GET_TEST_TYPES);
       // fields.put(Constant.USER_ID, String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
        fields.put(Constant.USER_ID, "4");

        ApiClient.get().getTestType(fields).enqueue(new Callback<TestTypeResponse>() {
            @Override
            public void onResponse(@NonNull Call<TestTypeResponse> call, @NonNull Response<TestTypeResponse> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus()==1){
                    testTypes.clear();
                    testTypes.addAll(response.body().getTests());
                    setAdapter();
                    getResult(testTypes.get(0).getTestTypeId());

                }

            }

            @Override
            public void onFailure(@NonNull Call<TestTypeResponse> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->", t.toString());
            }
        });


    }

    private void getResult(String position) {
        ProDilog.getInstance().show(getActivity(), "Loading...");
        Map<String, String> fields = new HashMap<>();
        fields.put(Constant.ACTION, ApiActions.ACTION_GET_TEST_RESULT);
        fields.put("type","0");
        fields.put("test_type_id",position);
       // fields.put(Constant.USER_ID, String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
        fields.put(Constant.USER_ID, "4");

        ApiClient.get().getResult(fields).enqueue(new Callback<TestResultResponse>() {
            @Override
            public void onResponse(@NonNull Call<TestResultResponse> call, @NonNull Response<TestResultResponse> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus()==1){
                    tv_rank.setText(response.body().getRank().toString());
                    testResult.clear();
                    testResult.add(new Result("All","0","0","0"));
                    testResult.addAll(response.body().getResults());
                    subjectRadioAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(@NonNull Call<TestResultResponse> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->", t.toString());
            }
        });


    }


    private void setAdapter() {
        for (TestTypes  name :testTypes){

            showtestType.add(name.getTestType());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),R.layout.spinner_item,showtestType);
        sp_testtype.setAdapter(arrayAdapter);

    }

    public void getUiObject(View view){
        barChart = view.findViewById(R.id.bar_graph_acad);
        sp_testtype = view.findViewById(R.id.sp_testtype);
        tv_rank = view.findViewById(R.id.tv_rank);
        rg_sujgroup =view.findViewById(R.id.rg_sujgroup);
        rv_subjects =view.findViewById(R.id.rv_subjects);
        rb_all =view.findViewById(R.id.rb_all);
        rb_all.setChecked(true);
       // setGraph(testResult.get(position).getMarksObtained());
    }



    public void setGraph(String marksObtained){

        List<BarEntry> barEntries = new ArrayList<>();
        int[] colors = {Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red),
                Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow_green), Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow)};
        Description description = new Description();

        float marks = Float.valueOf(marksObtained);
       // barEntries.add(new BarEntry(3f,30f));
        barEntries.add(new BarEntry(6f,marks));


        BarDataSet set = new BarDataSet(barEntries,"");
        set.setColors(colors);
        description.setText("Test Result");
        BarData barData = new BarData(set);
        barData.setBarWidth(2f);
        barChart.setDescription(description);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setEnabled(true);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setDrawLabels(false);
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);

        barChart.invalidate();

    }

    public void setGraphIndivisual(float x , float y){

        List<BarEntry> barEntries = new ArrayList<>();
        int[] colors = {Objects.requireNonNull(getActivity()).getResources().getColor(R.color.red),
                Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow_green), Objects.requireNonNull(getActivity()).getResources().getColor(R.color.yellow)};
        Description description = new Description();

        barEntries.add(new BarEntry(x,y));



        BarDataSet set = new BarDataSet(barEntries,"");
        set.setColors(colors);
        description.setText("Test Result");
        BarData barData = new BarData(set);
        barData.setBarWidth(1f);
        barChart.setDescription(description);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.setPinchZoom(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setEnabled(true);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false);
        barChart.getAxis(YAxis.AxisDependency.LEFT).setDrawLabels(false);
        barChart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);

        barChart.invalidate();

    }

    private void setSubjectAdapter(){
        subjectRadioAdapter = new SubjectRadioAdapter(getActivity(),testResult,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rv_subjects.setLayoutManager(layoutManager);
        rv_subjects.setAdapter(subjectRadioAdapter);


    }

    @Override
    public void recyclerClick(int position, String subject) {
        Log.e("GRAPH",testResult.get(position).getSubName());
        testResult.get(position).getMarksObtained();
        setGraph(testResult.get(position).getMarksObtained());

    }
}
