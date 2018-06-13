package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

public class Thread_DeleteTodo extends Thread{

    private AppDatabase db;
    Context context;

    public Thread_DeleteTodo(Context context){
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }
    public void run(){

    }
}
