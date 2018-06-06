package pluslearning.bailiwick.rajesh.com.pluslearning.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Deswal on 27-02-2018.
 */

public class SavedData {

    public static final String USERNAME = "username";
    public static final String COMETLOGIN = "cometlogin";

    public static final String USER_ID = "user_id";
    public static final String USERPASSWORD = "password";

    public static final String USER_CHAT_ID = "chat_id";


    public static final String IMAGE = "image";
    public static final String FROMGROUPID = "from_group_id";
    public static final String FROMGROUPOWNERID = "from_group_owner_id";
    public static final String FROMGROUPNAME = "from_group_name";

    static SharedPreferences prefs;

    public static SharedPreferences getInstance() {
        if (prefs == null) {
            prefs = PreferenceManager.getDefaultSharedPreferences(AppController.getInstance());
        }
        return prefs;
    }

    public static String getImage() {
        return getInstance().getString(IMAGE, null);
    }

    public static void saveImage(String messagelength) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(IMAGE, messagelength);
        editor.apply();
    }


    /*From group data*/
    public static int getfromGroupId() {
        return getInstance().getInt(FROMGROUPID, 0);
    }

    public static void savefromGroupId(int messagelength) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putInt(FROMGROUPID, messagelength);
        editor.apply();
    }
    public static String getfromGroupName() {
        return getInstance().getString(FROMGROUPNAME,"");
    }

    public static void savefromGroupName(String fromgroupname) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(FROMGROUPNAME, fromgroupname);
        editor.apply();
    }
    public static int getfromGroupOwnerId() {
        return getInstance().getInt(FROMGROUPOWNERID, 0);
    }

    public static void savefromGroupOwnerId(int fromgroupownerid) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putInt(FROMGROUPOWNERID, fromgroupownerid);
        editor.apply();
    }

    public static void clearFromGroupData() {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.remove(FROMGROUPID);
        editor.remove(FROMGROUPNAME);
        editor.remove(FROMGROUPOWNERID);
        editor.apply();
    }





    public static long getuserchatid() {
        return getInstance().getLong(USER_CHAT_ID, 0);
    }

    public static void saveuserchatid(long user_id) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putLong(USER_CHAT_ID, user_id);
        editor.apply();
    }
    public static String getchatusername() {
        return getInstance().getString(USERNAME, null);
    }

    public static void savechatusername(String username) {
        if (username!=null){
            username =  username.substring(0,username.indexOf("@"));
        }else{}
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(USERNAME, username);
        editor.apply();

    }

    public static boolean iscometlogin() {
        return getInstance().getBoolean(COMETLOGIN, false);
    }

    public static void setcometloginstatus(boolean login) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putBoolean(COMETLOGIN, login);
        editor.apply();
    }


    public static String getchatpassword() {
        return getInstance().getString(USERPASSWORD, null);
    }

    public static void savechatuserpassword(String password) {
        SharedPreferences.Editor editor = getInstance().edit();
        editor.putString(USERPASSWORD, password);
        editor.apply();
    }

}
