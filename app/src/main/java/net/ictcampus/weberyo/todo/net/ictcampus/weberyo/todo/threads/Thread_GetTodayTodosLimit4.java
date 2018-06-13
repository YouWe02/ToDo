package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

public class Thread_GetTodayTodosLimit4 extends Thread{
    private AppDatabase db;
    private Context context;
    private String date;
    private List<Todo> all;

    public Thread_GetTodayTodosLimit4(String date, Context context){
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
        this.start();

        return all;
    }

}
