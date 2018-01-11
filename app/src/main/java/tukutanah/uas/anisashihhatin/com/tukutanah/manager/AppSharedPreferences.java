package tukutanah.uas.anisashihhatin.com.tukutanah.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by neogeekscamp on 11/01/18.
 */

public class AppSharedPreferences {
    private static final String PREFS_NAME = "TUKU_TANAH_PREFS";

    public static void setStringValue(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringValue(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }
}
