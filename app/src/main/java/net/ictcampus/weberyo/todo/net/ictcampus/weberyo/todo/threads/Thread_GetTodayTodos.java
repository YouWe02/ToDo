package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

public class Thread_GetTodayTodos extends Thread {
    private AppDatabase db;
    private Context context;
    private String date;
    private List<Todo> all;

    public Thread_GetTodayTodos(String date, Context context){
        this.date = date;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    public synchronized void run(){
        all = db.todoDAO().getTodoByDay(date);
    }

    public synchronized List<Todo> getAll(){
        return all;
    }

}
