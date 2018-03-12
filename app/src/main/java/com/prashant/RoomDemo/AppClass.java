package com.prashant.RoomDemo;

import android.app.Application;

import com.prashant.RoomDemo.Utils.AppPreferences;
import com.prashant.RoomDemo.room.MyAppDatabase;

/**
 * Created by Prashant on 11/03/18.
 */

public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferences.init(this);
        MyAppDatabase.init(this);
    }
}
