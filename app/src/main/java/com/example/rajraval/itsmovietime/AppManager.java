package com.example.rajraval.itsmovietime;


import android.content.Context;
import android.content.SharedPreferences;

class AppManager {
    static SharedPreferences sharedPreferences;
     static SharedPreferences.Editor editor;
    public static void spPutBoolean(Context activity, String key, boolean value) {
        try {
            sharedPreferences = activity.getSharedPreferences("package com.example.rajraval.itsmovietime",
                    Context.MODE_PRIVATE);


            editor.putBoolean(key, value);
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean spGetBoolean(Context activity, String key) {
        sharedPreferences = activity.getSharedPreferences("package com.example.rajraval.itsmovietime",
                Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key, false);
    }
}
