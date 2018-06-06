package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.faq.FaqFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.login.LoginActivity;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;

public class PlearnActivity extends AppCompatActivity implements View.OnClickListener {

   TextView mFaq,mNoticeBoard,mHome;
   Toolbar mToolbar;
   ActionBar actionBar;
   ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plearn);
        progressDialog = new ProgressDialog(this);
        getUiObject();
        goToDashboard();
        setToolbar();
    }

    public void getUiObject(){
        mFaq = findViewById(R.id.tv_faq);
        mToolbar = findViewById(R.id.toolbar_main);
        mNoticeBoard = findViewById(R.id.tv_notice_board);
        mHome = findViewById(R.id.tv_home);

        mFaq.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mNoticeBoard.setOnClickListener(this);
    }

    public void goToDashboard(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlearnDashFragment plearnDashFragment = new PlearnDashFragment();
        fragmentTransaction.replace(R.id.frame_plearn,plearnDashFragment,null).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_faq:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FaqFragment faqFragment = new FaqFragment();
                fragmentTransaction.replace(R.id.frame_plearn,faqFragment,null).addToBackStack(null).commit();
                break;

            case R.id.tv_notice_board:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                NoticeFragment noticeFragment = new NoticeFragment();
                fragmentTransaction1.replace(R.id.frame_plearn,noticeFragment,null).addToBackStack(null).commit();
                break;

            case R.id.tv_home:
                /*FragmentManager homeFm = getSupportFragmentManager();
                FragmentTransaction homeFt = homeFm.beginTransaction();
                PlearnDashFragment plearnDashFragment = new PlearnDashFragment();
                homeFt.replace(R.id.frame_plearn,plearnDashFragment,null).commit();*/
                goToDashboard();
                break;
        }
    }

    public void setToolbar(){
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
           // actionBar.setHomeButtonEnabled(true);
         //   actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

   /* public void setDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notify:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NotificationFragment notificationFragment = new NotificationFragment();
                fragmentTransaction.replace(R.id.frame_plearn,notificationFragment,null).addToBackStack(null).commit();
                break;

            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are You Sure To Logout ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.setMessage("Logging Out. Please Wait...");
                        progressDialog.show();
                        showProgress();

                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){

            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                getSupportFragmentManager().popBackStack();
            }
        }else{
            showExitDialog();
        }
    }

    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Want to Exit ?");

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showProgress(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPref.clearAll(PlearnActivity.this);
                Intent intent = new Intent(PlearnActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }


}
