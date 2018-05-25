package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ToDo_DBQuery {

    private static final String TAG = ToDo_DBQuery.class.getSimpleName();

    private SQLiteDatabase database;
    private ToDo_DBHelper dbHelper;

    public ToDo_DBQuery(Context context){
        Log.d(TAG, "DBHelper created");
        dbHelper = new ToDo_DBHelper(context);
    }

    public void open(){
        Log.d(TAG, "Database opened");
        database = dbHelper.getWritableDatabase();
    }

    public void clos() {
        Log.d(TAG, "Database closed");
        dbHelper.close();
    }
}
