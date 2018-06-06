package pluslearning.bailiwick.rajesh.com.pluslearning.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static SharedPreferences sharedPreferences;
    private static String MODE_TYPE = "echo";
    private static String IS_LOGIN = "isLoggedIn";
    private static String USER_ID = "00000";
    private static String NAME = "pUser";

    public static void setLogIn(Context context,boolean isLogin){
        sharedPreferences = context.getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN,isLogin);
        editor.apply();
    }

    public static boolean getLogin(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public static void setName(Context context,String name){
        sharedPreferences = context.getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME,name);
        editor.apply();
    }

    public static String getName(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        return sharedPreferences.getString(NAME,"Default User");
    }

    public static void setUserId(Context context,int user_id){
        sharedPreferences = context.getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID,user_id);
        editor.apply();
    }

    public static int getUserId(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_ID,-1);
    }

    public static void clearAll(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(MODE_TYPE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
