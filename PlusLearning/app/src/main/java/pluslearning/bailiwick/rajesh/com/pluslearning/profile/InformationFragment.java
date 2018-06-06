package pluslearning.bailiwick.rajesh.com.pluslearning.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ProfileResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment {

    List<ProfileResponse> results;
    private TextView tv_classname ,tv_address,tv_rollno,tv_contactno,tv_email;

    public static InformationFragment newInstance (List<ProfileResponse> results) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.RESULT, (Serializable) results);
        InformationFragment fragment = new InformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        results = new ArrayList<>();
        getUiObject(view);
        setData();

        return view;
    }

    private void setData() {
        if (null != getArguments() && getArguments().containsKey(Constant.RESULT)) {
            results= (List<ProfileResponse>) getArguments().getSerializable(Constant.RESULT);

            if (results!=null) {
                for (ProfileResponse result : results ){
                    tv_classname.setText("Standard : " + result.getClass_());
                   // tv_address.setText(result.getClass_());
                    tv_contactno.setText(result.getMobile());
                    tv_email.setText(result.getEmail());
                }
        }
    }}

    public void getUiObject(View view){
        tv_classname = view.findViewById(R.id.tv_classname);
        tv_address = view.findViewById(R.id.tv_address);
        tv_contactno = view.findViewById(R.id.tv_contactno);
        tv_email = view.findViewById(R.id.tv_email);
        tv_rollno = view.findViewById(R.id.tv_rollno);
    }

}
