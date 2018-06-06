package pluslearning.bailiwick.rajesh.com.pluslearning.student_info;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.adapter.SyllabusRecyclerAdapter;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.SyllabusBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestSyllabusFragment extends Fragment {

    Toolbar mToolbar;
    List<SyllabusBean> syllabusBeanList;
    SyllabusRecyclerAdapter syllabusRecyclerAdapter;
    RecyclerView mTestSylRecycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_syllabus, container, false);
        getUiObject(view);
        setRecycler();

        return view;
    }

    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Test Syllabus");
        mTestSylRecycler = view.findViewById(R.id.rv_test_syllabus);

    }

    public void setRecycler(){
        syllabusBeanList = new ArrayList<>();
        syllabusBeanList.add(new SyllabusBean("Chapter 1","complete"));
        syllabusBeanList.add(new SyllabusBean("Chapter 2","complete"));

        syllabusRecyclerAdapter = new SyllabusRecyclerAdapter(getActivity(),syllabusBeanList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mTestSylRecycler.setLayoutManager(layoutManager);
        mTestSylRecycler.setItemAnimator(new DefaultItemAnimator());
        mTestSylRecycler.setAdapter(syllabusRecyclerAdapter);


    }


}
