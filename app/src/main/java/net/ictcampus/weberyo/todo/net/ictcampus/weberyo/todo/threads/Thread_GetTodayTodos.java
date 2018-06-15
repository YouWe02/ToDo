package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

/**
 * In dieser Klasse werden alle todos mit dem mitgegebenen
 * Datum abgerufen.
 */
public class Thread_GetTodayTodos extends Thread {
    private AppDatabase db;
    private Context context;
    private String date;
    private List<Todo> all;

    /**
     * Initialisiert die Instanzvariablen
     * @param date Datum für die Abfrage
     * @param context Inhalt der App
     */
    public Thread_GetTodayTodos(String date, Context context){
        this.date = date;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    /**
     * Ruft die Todos ab und speichert Sie in all
     * Wird ausgeführt, wenn der THread gestartet wird.
     */
    public synchronized void run(){
        all = db.todoDAO().getTodoByDay(date);
    }

    /**
     * Gibt alle
     * @return gibt die Abfrage zurück-
     */
    public synchronized List<Todo> getAll(){
        return all;
    }

}
