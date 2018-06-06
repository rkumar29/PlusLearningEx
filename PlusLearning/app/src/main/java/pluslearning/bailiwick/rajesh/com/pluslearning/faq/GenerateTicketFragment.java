package pluslearning.bailiwick.rajesh.com.pluslearning.faq;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.GenericResponse;
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
public class GenerateTicketFragment extends Fragment implements View.OnClickListener ,TextWatcher {

    EditText mTitle, mDescritpion;
    Button mSubmit;
    private TextView tv_char;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generate_ticket, container, false);
        getUiObject(view);
        progressDialog = new ProgressDialog(getActivity());

        return view;
    }

    public void getUiObject(View view) {
        tv_char =view.findViewById(R.id.tv_char);
        mTitle = view.findViewById(R.id.et_title);
        mDescritpion = view.findViewById(R.id.et_description);
        mSubmit = view.findViewById(R.id.btn_submit);
        mDescritpion.addTextChangedListener(this);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (mTitle.getText().toString().isEmpty()) {
                    mTitle.setError("Required");
                } else if (mDescritpion.getText().toString().isEmpty()) {
                    mDescritpion.setError("Required");
                }
                else if (mDescritpion.getText().length() !=100){
                    mDescritpion.setError("Lenght Must be 100..");
                }else {
                    String title = mTitle.getText().toString();
                    String description = mDescritpion.getText().toString();
                    submitQuery(title , description);
                }

                break;
        }
    }

    public void submitQuery(String title, String description){
        ProDilog.getInstance().show(getActivity(),"Loading...");
        Map<String,String> fields = new HashMap<>();
        fields.put("action", ApiActions.ACTION_GET_TIME_TABLE);
        fields.put("description", description);
        fields.put("title",title );
        fields.put("user_id", String.valueOf(SharedPref.getUserId(Objects.requireNonNull(getActivity()))));

        ApiClient.get().submitQuery(fields).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                ProDilog.getInstance().dismiss();
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();


            }
            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                ProDilog.getInstance().dismiss();
                Log.e("user detail->",t.toString());
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
          //  tv_char.setText(String.valueOf(count));
    }

    @Override
    public void afterTextChanged(Editable s) {
        ;
        tv_char.setText(String.valueOf(s.length()));
    }
}
