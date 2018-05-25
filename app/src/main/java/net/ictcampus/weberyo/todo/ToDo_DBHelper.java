package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ToDo_DBHelper extends SQLiteOpenHelper {

    private  static  final String TAG = ToDo_DBHelper.class.getSimpleName();

    //DB Name
    public static final String DB_NAME = "todo.db";
    public static final int DB_VERSION = 1;

    //Tabelle Bereich
    public static final String TABLE_BEREICH = "bereich";
    public static final String BEREICH_COLUMN_ID_BEREICH = "_id";
    public static final String BEREICH_COLUMN_BEREICH = "Bereich";

    public static final String CREATE_TABLE_BEREICH = "CREATE TABLE" + TABLE_BEREICH +
            "(" + BEREICH_COLUMN_ID_BEREICH + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            BEREICH_COLUMN_BEREICH + "STRING);";


     //Tabelle To-Do
    public static final String TABLE_TODO = "todo";
    public static final String TODO_COLUMN_ID_TODO = "_id";
    public static final String TODO_COLUMN_TITLE = "Title";
    public static final String TODO_COLUMN_DESCRIPTION = "Description";
    public static final String TODO_COLUMN_BEREICH = "Bereich";
    public static final String TODO_COLUMN_PRIORITAET = "Priority";
    public static final String TODO_COLUMN_DATE = "Date";
    public static final String TODO_COLUMN_PRIVACY = "Private";

    public static final String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO +
            "(" + TODO_COLUMN_ID_TODO + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TODO_COLUMN_TITLE + "TEXT," +
            TODO_COLUMN_DESCRIPTION + "TEXT," +
            TODO_COLUMN_BEREICH + "INTEGER," + //Fremdschlüssel
            TODO_COLUMN_PRIORITAET + "INTEGER," +
            TODO_COLUMN_DATE + "STRING," + //Format: YYYY-MM-DD
            TODO_COLUMN_PRIVACY + "INTEGER," + //1 = TRUE , 0 = FALSE
            "FOREIGN KEY ("+TODO_COLUMN_BEREICH+") REFERENCES " + TABLE_BEREICH + "(" + BEREICH_COLUMN_ID_BEREICH + ");";


    public ToDo_DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "Database created: " + getDatabaseName());
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(TAG, "Nun werden die Tabellen angelegt.");
            db.execSQL(CREATE_TABLE_BEREICH);
            db.execSQL(CREATE_TABLE_TODO);
        } catch (Exception e){
            Log.d(TAG, "Sadly, an Error occured while we tried to create the tables :(");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
