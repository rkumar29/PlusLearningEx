package pluslearning.bailiwick.rajesh.com.pluslearning.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TrackReferal extends BroadcastReceiver {
    private String referrer = "";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
        String referrerId = intent.getStringExtra("referrer");
            Log.e("Referral code is ",referrerId);
        }

}}
