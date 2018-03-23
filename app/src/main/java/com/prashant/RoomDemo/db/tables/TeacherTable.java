package com.prashant.RoomDemo.db.tables;

/**
 * Created by Prashant on 22/03/18.
 */

public class TeacherTable {

    public static String TEACHER_TABLE = "teacher";
    public static String TEACHER_ID = "tid";
    public static String TEACHER_NAME = "tname";

    public static String TEACHER_CREATE_QUERY = "CREATE TABLE " + TEACHER_TABLE + " ("
            + TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TEACHER_NAME + " TEXT"
            + ")";
}
