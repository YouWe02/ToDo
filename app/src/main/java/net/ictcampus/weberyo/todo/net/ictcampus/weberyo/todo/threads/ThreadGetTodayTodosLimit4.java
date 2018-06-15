package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

/**
 * Gibt die 4 wichtigsten Todos vom gewünschten Datum zurück.
 */
public class ThreadGetTodayTodosLimit4 extends Thread{
    private AppDatabase db;
    private Context context;
    private String date;
    private List<Todo> all;

    /**
     * Konstruktor, initialisiert die Instanzvariablen
     * @param date Datum
     * @param context INhalt der App
     */
    public ThreadGetTodayTodosLimit4(String date, Context context){
        this.date = date;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    public synchronized void run(){
        all = db.todoDAO().getTodoByDayLimi4(date);
    }

    public void refreshThread(String date){
        this.date = date;
    }

    public synchronized List<Todo> getAll(){
        return all;
    }

}
