package pluslearning.bailiwick.rajesh.com.pluslearning.firebase;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        if (!TextUtils.isEmpty(refreshedToken)) {
            Log.d(TAG, "Refreshed token: " + refreshedToken);
        }
    }

}