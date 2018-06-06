package pluslearning.bailiwick.rajesh.com.pluslearning.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private static String currentChannel = null;
    public static ArrayList<String> chatroomChannelList = new ArrayList<String>();


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.e("Message in ",remoteMessage.toString());

        Log.e("Message in fire",remoteMessage.getData().toString());
        }}