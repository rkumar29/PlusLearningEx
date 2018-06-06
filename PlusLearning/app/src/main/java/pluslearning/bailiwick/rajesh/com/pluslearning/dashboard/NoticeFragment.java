package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GetNotices;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ProDilog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment implements View.OnClickListener {

    Toolbar mToolbar;
    ImageView mNext,mPrev;
    List<String> notices = new ArrayList<>();
    TextView mNotices;
    int counter = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        getUiObject(view);
        check();
        setNotices();

        return view;
    }

    public void getUiObject(View view){
        mToolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_main);
        mToolbar.setTitle("Notice Board");
        mNext = view.findViewById(R.id.iv_next);
        mPrev = view.findViewById(R.id.iv_prev);
        mNotices = view.findViewById(R.id.tv_notice);

        mNext.setOnClickListener(this);
        mPrev.setOnClickListener(this);

    }

    public void check(){
        if (counter == 0){
            mPrev.setVisibility(View.GONE);
            mNext.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Click Next Button to Check More Notices", Toast.LENGTH_SHORT).show();
        }else if (counter > 0 && counter < notices.size()-1){
            mNext.setVisibility(View.VISIBLE);
            mPrev.setVisibility(View.VISIBLE);
        }else if (counter == notices.size()-1){
            mNext.setVisibility(View.GONE);
            mPrev.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "No More Notices Available", Toast.LENGTH_SHORT).show();
        }
    }

    public void setNotices(){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        ApiClient.get().getNotices().enqueue(new Callback<GetNotices>() {
            @Override
            public void onResponse(@NonNull Call<GetNotices> call, @NonNull Response<GetNotices> response) {
                ProDilog.getInstance().dismiss();
                if (response.body().getStatus() == 1){
                    mNotices.setVisibility(View.VISIBLE);
                    for (int i = 0; i < response.body().getNotice().size(); i++) {
                        notices.add(response.body().getNotice().get(i).getDescription());
                    }
                    mNotices.setText(notices.get(counter));
                }else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetNotices> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
            }
        });

       // Toast.makeText(getActivity(), ""+notices.size(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_next:
                if(counter < notices.size()-1){
                    counter = counter+1;
                }
                check();
                mNotices.setText(notices.get(counter));
             //   Toast.makeText(getActivity(), ""+counter, Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_prev:
                if (counter > 0){
                    counter = counter-1;
                }
              //  Toast.makeText(getActivity(), ""+counter, Toast.LENGTH_SHORT).show();
                check();
                mNotices.setText(notices.get(counter));
                break;
        }
    }




}
