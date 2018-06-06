package pluslearning.bailiwick.rajesh.com.pluslearning.student_info;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.ViewPagerTestResultAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestResultFragment extends Fragment {

    Toolbar mToolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerTestResultAdapter testResultAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, container, false);
        getUiObject(view);
        setPages();

        return view;
    }

    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Test Result");
        viewPager = view.findViewById(R.id.view_results);
        tabLayout = view.findViewById(R.id.tab_test_result);
    }

    public void setPages(){
        testResultAdapter = new ViewPagerTestResultAdapter(getActivity(),getChildFragmentManager());
        viewPager.setAdapter(testResultAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
