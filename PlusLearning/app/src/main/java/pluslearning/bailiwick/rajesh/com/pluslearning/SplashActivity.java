package pluslearning.bailiwick.rajesh.com.pluslearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import pluslearning.bailiwick.rajesh.com.pluslearning.dashboard.DashBoredActivity;
import pluslearning.bailiwick.rajesh.com.pluslearning.login.LoginActivity;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;

public class SplashActivity extends AppCompatActivity {

    public static final int SPLASH_TIME = 4*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToNext();



    }

    public void goToNext(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPref.getLogin(SplashActivity.this)){
                    Intent dashIntent = new Intent(SplashActivity.this, DashBoredActivity.class);
                    startActivity(dashIntent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIME);
    }

}
