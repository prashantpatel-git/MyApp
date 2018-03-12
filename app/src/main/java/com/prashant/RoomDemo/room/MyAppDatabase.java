package com.prashant.RoomDemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.prashant.RoomDemo.models.NearbyPlace;

/**
 * Created by Prashant on 11/03/18.
 */

@Database(entities = {NearbyPlace.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    private static final String DB_NAME = "MyDb";
    private static MyAppDatabase myAppDatabase;

    public static void init(Context context) {
        myAppDatabase = Room.databaseBuilder(context, MyAppDatabase.class, DB_NAME).build();
    }

    public static MyAppDatabase getInstance() {
        return myAppDatabase;
    }

    public abstract RoomDao roomDao();
}
