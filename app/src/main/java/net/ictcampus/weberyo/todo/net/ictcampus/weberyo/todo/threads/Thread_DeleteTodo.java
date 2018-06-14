package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

public class Thread_DeleteTodo extends Thread{

    private AppDatabase db;
    private Context context;
    private int id;

    public Thread_DeleteTodo(Context context, int id){
        this.context = context;
        this.id = id;
        db = AppDatabase.getAppDatabase(context);
    }
    public void run(){
        Todo todo = db.todoDAO().getToDoById(id);
        db.todoDAO().deleteTodo(todo);
    }
}
