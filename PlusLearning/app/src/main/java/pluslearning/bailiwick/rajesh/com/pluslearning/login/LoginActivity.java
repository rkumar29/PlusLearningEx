package pluslearning.bailiwick.rajesh.com.pluslearning.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.bean.LoginBean;
import pluslearning.bailiwick.rajesh.com.pluslearning.dashboard.DashBoredActivity;
import pluslearning.bailiwick.rajesh.com.pluslearning.rest.ApiClient;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.ApiActions;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button mLogin;
    TextView mForgotPass;
    EditText mUsername,mPassword;
    String mUser,mPass;
    ProgressDialog progressDialog;


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);

        getUiObject();
    }

    public void getUiObject(){
        mLogin = findViewById(R.id.btn_login);
        mForgotPass = findViewById(R.id.tv_forget_password);
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);

        mLogin.setOnClickListener(this);
        mForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (mUsername.getText().toString().isEmpty() && mPassword.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }else{
                    makeApiRequest();
                }
                break;

            case R.id.tv_forget_password:
                Toast.makeText(this, "Please Contact Adminstrator For New Password.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void makeApiRequest(){

        progressDialog.setMessage("Please Wait While Logging In...");
        progressDialog.show();
        mUser = mUsername.getText().toString().trim();
        mPass = mPassword.getText().toString().trim();

        Map<String,String> fields = new HashMap<>();
        fields.put("log_email",mUser);
        fields.put("log_password",mPass);
        fields.put("action", ApiActions.ACTION_LOGIN);


        ApiClient.get().getLogin(fields).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(@NonNull Call<LoginBean> call, @NonNull Response<LoginBean> response) {
                /*Log.e("Response == > ",response.toString());
                Log.e("Response == > ",response.body().getStatus());*/
                if (response.body().getMsg().equalsIgnoreCase("success")){
                    progressDialog.hide();
                    SharedPref.setLogIn(LoginActivity.this,true);
                    SharedPref.setUserId(LoginActivity.this,response.body().getUserid());
                    Intent loginIntent = new Intent(LoginActivity.this, DashBoredActivity.class);
                    startActivity(loginIntent);
                    finish();
                }else{
                    progressDialog.hide();
                    Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginBean> call, @NonNull Throwable t) {
                Log.e("Error == > ",t.toString());
            }
        });

      //  volleyHit(mUser,mPass);
    }


    public void volleyHit(final String mUser, final String mPass){

        final String BASE_URL = "http://bailiwicksolution.com/learning/wp-admin/admin-ajax.php/";
        final String action = "login_form_submitapi";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest loginRequest = new StringRequest(Request.Method.POST, BASE_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Volley Hit ==> ",response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error ==> ",error.toString());
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("key","NjAxNzI1OmUyZmJlZW");

                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("action",action);
                params.put("log_email",mUser);
                params.put("log_password",mPass);

                return params;
            }
        };

        queue.add(loginRequest);
    }


}
