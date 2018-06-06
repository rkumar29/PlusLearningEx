package pluslearning.bailiwick.rajesh.com.pluslearning.util;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by androidsys1 on 3/31/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public static final int GRANTED = 0;
    public static final int DENIED = 1;
    public static final int BLOCKED = 2;
    public static int invoiceId = 0;

    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    public  static int MY_SOCKET_TIMEOUT_MS = 60000;

    public ProgressDialog progressDialog;


    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        //MultiDex.install(this);
        super.onCreate();
        mInstance = this;

        //getDialog();


    }

    @PermissionStatus
    public static int getPermissionStatus(Activity activity, String androidPermissionName) {
        if (ContextCompat.checkSelfPermission(activity, androidPermissionName) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, androidPermissionName)) {
                return BLOCKED;
            }
            return DENIED;
        }
        return GRANTED;
    }





    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    public ProgressDialog getDialog(){
        if (progressDialog ==null){
            progressDialog = new ProgressDialog(getApplicationContext());
        }
        return progressDialog;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public  void showDialog(String name){
        getDialog().setMessage(name);
        getDialog().setCancelable(false);
        getDialog().create();
        //getDialog().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        getDialog().show();

    }
    public void hideDialog(){
        getDialog().dismiss();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public static void  retryPolicey(StringRequest stringRequest){
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }



    public void handleUncaughtException(Thread thread, Throwable e) {
        e.printStackTrace();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(
            {GRANTED, DENIED, BLOCKED})
    public @interface PermissionStatus {
    }

}

