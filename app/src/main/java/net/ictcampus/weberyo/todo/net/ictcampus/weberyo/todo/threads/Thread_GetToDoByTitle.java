package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

public class Thread_GetToDoByTitle extends Thread {
    private AppDatabase db;
    private Context context;
    private String date;
    private String title;
    private Todo ToDo;

    public Thread_GetToDoByTitle(String date, String title, Context context){
        this.date = date;
        this.title = title;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    public synchronized void run(){
        ToDo = db.todoDAO().getToDoByTitle(date, title);
    }

    public synchronized Todo getAll(){
        return ToDo;
    }

}
