package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.PlRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetUserDetailBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.PlBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ProfileResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.download.DownloadFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.profile.ProfileFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.student_info.AttendanceFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.student_info.TestResultFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.student_info.TestSyllabusFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.time_table.TimeTableFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class PlearnDashFragment extends Fragment implements  View.OnClickListener {

    RecyclerView mCatRecycler;
    List<PlBean> plBeanList;
    PlRecyclerAdapter plRecyclerAdapter;
    Toolbar mToolbar;
    List<ProfileResponse> results;
    CardView mProfile,mAttendance,mTestResult,mTestSyllabus,mBusLocation,mDownloads,mSyllabus,mTimeTable;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plearn_dash, container, false);
        getUiObect(view);
        setContent();
        //getUserDetails();

        return view;
    }

    private void getUiObect(View view) {
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Plus Learning");
        mCatRecycler = view.findViewById(R.id.rv_cat_types);

        mProfile = view.findViewById(R.id.cv_profile);
        mAttendance = view.findViewById(R.id.cv_attendance);
        mTestResult = view.findViewById(R.id.cv_test_result);
        mTestSyllabus = view.findViewById(R.id.cv_test_syllabus);
        mBusLocation = view.findViewById(R.id.cv_bus_location);
        mDownloads = view.findViewById(R.id.cv_download);
        mSyllabus = view.findViewById(R.id.cv_syllabus);
        mTimeTable = view.findViewById(R.id.cv_time_table);


        mProfile.setOnClickListener(this);
        mAttendance.setOnClickListener(this);
        mTestResult.setOnClickListener(this);
        mTestSyllabus.setOnClickListener(this);
        mBusLocation.setOnClickListener(this);
        mDownloads.setOnClickListener(this);
        mSyllabus.setOnClickListener(this);
        mTimeTable.setOnClickListener(this);

    }

    public void setContent(){
        plBeanList = new ArrayList<>();
        plBeanList.add(new PlBean("Profile",R.drawable.account_outline));
        plBeanList.add(new PlBean("Attendance",R.drawable.account_outline));
        plBeanList.add(new PlBean("Test Result",R.drawable.test_result));
        plBeanList.add(new PlBean("Test Syllabous",R.drawable.test_syllabus));
        plBeanList.add(new PlBean("Bus Location",R.drawable.bus_location));
        plBeanList.add(new PlBean("Download",R.drawable.downloads));
        plBeanList.add(new PlBean("Coming Soon",R.drawable.account_outline));
        plBeanList.add(new PlBean("Time Table",R.drawable.account_outline));

        plRecyclerAdapter = new PlRecyclerAdapter(getActivity(),plBeanList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mCatRecycler.setLayoutManager(layoutManager);
        mCatRecycler.setItemAnimator(new DefaultItemAnimator());
        mCatRecycler.setAdapter(plRecyclerAdapter);

    }
    





    public void getUserDetails(){
        ProDilog.getInstance().show(getActivity(),"Loading...");

        Map<String,String> fields = new HashMap<>();
        fields.put("action", ApiActions.ACTION_GET_USER_DETAILS);
        fields.put("userid", String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));
        fields.put("device_id","");
        fields.put("fcm_id","abcdefghijkl");
        fields.put("emei_id","");

        ApiClient.get().getUserDetails(fields).enqueue(new Callback<GetUserDetailBean>() {
            @Override
            public void onResponse(@NonNull Call<GetUserDetailBean> call, @NonNull Response<GetUserDetailBean> response) {
                ProDilog.getInstance().dismiss();
                results = response.body().getResult();
            }

            @Override
            public void onFailure(@NonNull Call<GetUserDetailBean> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->",t.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_profile:

                FragmentManager fmProfile = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftProfile = fmProfile.beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("getDetail", (Serializable) results);
                profileFragment.setArguments(bundle);
                ftProfile.replace(R.id.frame_plearn,profileFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_attendance:

                FragmentManager fmAttendance = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftAttendance = fmAttendance.beginTransaction();
                AttendanceFragment attendanceFragment = new AttendanceFragment();
                ftAttendance.replace(R.id.frame_plearn,attendanceFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_test_result:

                FragmentManager fmTestResult = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftTestResult = fmTestResult.beginTransaction();
                TestResultFragment testResultFragment = new TestResultFragment();
                ftTestResult.replace(R.id.frame_plearn,testResultFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_test_syllabus:

                FragmentManager fmTestSyallbus = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftTestSyllabus = fmTestSyallbus.beginTransaction();
                TestSyllabusFragment testSyllabusFragment = new TestSyllabusFragment();
                ftTestSyllabus.replace(R.id.frame_plearn,testSyllabusFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_bus_location:
                Toast.makeText(getActivity(), "Screen Will Be Available Soon", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cv_download:

                FragmentManager fmDownload = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftDownload = fmDownload.beginTransaction();
                DownloadFragment downloadFragment = new DownloadFragment();
                ftDownload.replace(R.id.frame_plearn,downloadFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_syllabus:

                FragmentManager syllabusFm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction syllabusFt = syllabusFm.beginTransaction();
                SyllabusFragment syllabusFragment = new SyllabusFragment();
                syllabusFt.replace(R.id.frame_plearn,syllabusFragment,null).addToBackStack(null).commit();

                break;

            case R.id.cv_time_table:

                FragmentManager fmTimeTable = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction ftTimeTable = fmTimeTable.beginTransaction();
                TimeTableFragment plTimeTableFragment = new TimeTableFragment();
                ftTimeTable.replace(R.id.frame_plearn,plTimeTableFragment,null).addToBackStack(null).commit();

                break;
        }
    }
}
