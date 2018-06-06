package pluslearning.bailiwick.rajesh.com.pluslearning.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.ProfileResponse;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentInformationFragment extends Fragment {

    private TextView tv_fathername,tv_fatheroccu,tv_fathercontact,
    tv_mothername,tv_motheroccu,tv_mothercontact;
    private ImageView iv_father,iv_mother;
    private List<ProfileResponse> results;

    public static ParentInformationFragment newInstance (List<ProfileResponse> results) {
        Bundle args = new Bundle();
        args.putSerializable(Constant.RESULT, (Serializable) results);
        ParentInformationFragment fragment = new ParentInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_information, container, false);
        getUiObject(view);
        setData();


        return view;
    }

    private void setData() {
        if (null != getArguments() && getArguments().containsKey(Constant.RESULT)) {
            results= (List<ProfileResponse>) getArguments().getSerializable(Constant.RESULT);

            if (results!=null) {
                for (ProfileResponse result : results ){
                    tv_fathername.setText(result.getFatherName());
                    // tv_address.setText(result.getClass_());
                    tv_fatheroccu.setText(result.getFatherOccupation());
                    tv_fathercontact.setText(result.getFatherContact());
                    tv_mothername.setText(result.getMotherName());
                    tv_motheroccu.setText(result.getMotherOccupation());
                    tv_mothercontact.setText(result.getMotherContact());
                  //  Picasso.get().load(results.get(0).getFatherImage()).into(iv_father);
                  //  Picasso.get().load(results.get(0).getFatherImage()).into(iv_mother);



                }
            }
        }}

    public void getUiObject(View view){
        tv_fathername = view.findViewById(R.id.tv_fathername);
        tv_fatheroccu = view.findViewById(R.id.tv_fatheroccu);
        tv_fathercontact = view.findViewById(R.id.tv_fathercontact);
        tv_mothername = view.findViewById(R.id.tv_mothername);
        tv_motheroccu = view.findViewById(R.id.tv_motheroccu);
        tv_mothercontact = view.findViewById(R.id.tv_mothercontact);


    }


}
