package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificViewFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notific_view, container, false);
        getUiObject(view);

        return view;
    }

    public void getUiObject(View view){

    }

}
