package com.prashant.RoomDemo.db.tables;

import static com.prashant.RoomDemo.db.tables.TeacherTable.TEACHER_ID;

/**
 * Created by Prashant on 22/03/18.
 */

public class StudentTable {

    public static String STUDENT_TABLE = "student";
    public static String STUDENT_ID = "sid";
    public static String STUDENT_NAME = "sname";

    public static String STUDENT_CREATE_QUERY = "CREATE TABLE " + STUDENT_TABLE + " ("
            + STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + STUDENT_NAME + "TEXT,"
            + TEACHER_ID + " INTEGER"
            + ")";


}
