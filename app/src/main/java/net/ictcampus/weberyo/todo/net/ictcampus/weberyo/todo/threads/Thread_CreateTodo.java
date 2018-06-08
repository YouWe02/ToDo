package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

public class Thread_CreateTodo extends Thread  {
    private AppDatabase db;
    private Context context;
    private String title;
    private String description;
    private String date;
    private String theme;
    private boolean privacy;
    private int priority;

    public Thread_CreateTodo(String title, String description, String date, String theme, boolean privacy, int priority, Context context){
        this.title = title;
        this.description = description;
        this.date = date;
        this.theme = theme;
        this.privacy = privacy;
        this.priority = priority;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }
    public void run(){
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDate(date);
        todo.setTheme(theme);
        todo.setPrivacy(privacy);
        todo.setPriority(priority);
        db.todoDAO().insertTodo(todo);
    }

    public void refreshThread(String title, String description, String date, String theme, boolean privacy, int priority){
        this.title = title;
        this.description = description;
        this.date = date;
        this.theme = theme;
        this.privacy = privacy;
        this.priority = priority;
    }
}
