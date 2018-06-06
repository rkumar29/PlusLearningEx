/*
package pluslearning.bailiwick.rajesh.com.pluslearning.dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pluslearning.bailiwick.rajesh.com.pluslearning.R;
import pluslearning.bailiwick.rajesh.com.pluslearning.faq.FaqFragment;
import pluslearning.bailiwick.rajesh.com.pluslearning.util.SharedPref;

public class DashBoredActivity extends AppCompatActivity implements View.OnClickListener {

   TextView mFaq,mNoticeBoard,mSyllabus,mRef;
   Toolbar mToolbar;
   ActionBar actionBar;
   DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);
        getUiObject();
        goToDashboard();
        setToolbar();
        setDrawer();
    }

    public void getUiObject(){
        mFaq = findViewById(R.id.tv_faq);
        mToolbar = findViewById(R.id.toolbar_main);
        mDrawer = findViewById(R.id.drawer_main);
        mNoticeBoard = findViewById(R.id.tv_notice_board);
        mSyllabus = findViewById(R.id.tv_syllabus);
        mRef=findViewById(R.id.tv_ref);

        mFaq.setOnClickListener(this);
        mSyllabus.setOnClickListener(this);
        mNoticeBoard.setOnClickListener(this);
    }

    public void goToDashboard(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlearnDashFragment plearnDashFragment = new PlearnDashFragment();
        fragmentTransaction.replace(R.id.frame_plearn,plearnDashFragment,null).commit();
    }

    public void sendReferral() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getInvitationMessage());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        sendIntent.setType("text/plain");
        this.startActivity(Intent.createChooser(sendIntent, "Title"));
    }

    private String getInvitationMessage(){
        //String invitationId;
        String playStoreLink = "https://play.google.com/store/apps/details?id=com.nmss.wickets.match.live.score&referrer=";
        return  playStoreLink = playStoreLink + "123456";


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

            case R.id.tv_syllabus:
                FragmentManager syllabusFm = getSupportFragmentManager();
                FragmentTransaction syllabusFt = syllabusFm.beginTransaction();
                SyllabusFragment syllabusFragment = new SyllabusFragment();
                syllabusFt.replace(R.id.frame_plearn,syllabusFragment,null).addToBackStack(null).commit();
                break;

            case R.id.tv_ref:
                sendReferral();
                break;


        }
    }

    public void setToolbar(){
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    public void setDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

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

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else{
            showExitDialog();
        }
    }

    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure To Exit ?");

        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPref.clearAll(DashBoredActivity.this);
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

}
*/
