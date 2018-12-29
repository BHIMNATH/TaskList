package com.abnstudio.tasklist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

class TodoListSQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "com.abnstudio.tasklist";
    public static final String TABLE_NAME = "TASK_LIST";
    public static final String COL1_TASK = "todo";
    public static final String _ID = BaseColumns._ID;

    public TodoListSQLHelper(Context context){
        super(context,DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDb) {
        String createTaskListTable = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, ) " + COL1_TASK + " TEXT )";
        sqlDb.execSQL(createTaskListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDb, int oldVersion, int newVersion) {
        sqlDb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlDb);
    }
}
