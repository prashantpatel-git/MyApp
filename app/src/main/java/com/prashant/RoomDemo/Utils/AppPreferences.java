package com.prashant.RoomDemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

/**
 * Created by Prashant on 11/03/18.
 */

public class AppPreferences {

    private final static String PREF_NAME = "My Prefs";
    private final static String PREF_LATITUDE = "p_lat";
    private final static String PREF_LONGITUDE = "p_long";
    private static AppPreferences appPreferences;
    private static SharedPreferences preferences;

    private AppPreferences(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void init(Context context) {
        if (appPreferences == null)
            appPreferences = new AppPreferences(context);

    }

    public static AppPreferences getInstance() {

        if (appPreferences == null) {
            Log.e("AppPreferences", "Call AppPreferences.init() in application class");
            return null;
        }

        return appPreferences;
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setLocation(Location location) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(PREF_LATITUDE, location.getLatitude() + "");
        editor.putString(PREF_LONGITUDE, location.getLongitude() + "");
        editor.commit();
    }

    public double getLatitude() {
        return Double.parseDouble(preferences.getString(PREF_LATITUDE, "0"));
    }

    public double getLongitude() {
        return Double.parseDouble(preferences.getString(PREF_LONGITUDE, "0"));
    }
}
