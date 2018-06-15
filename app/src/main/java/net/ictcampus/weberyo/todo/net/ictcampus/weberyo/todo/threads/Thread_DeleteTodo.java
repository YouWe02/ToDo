package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

import java.util.List;

/**
 * In diesem Thread wird ein Todo von der DB gelöscht
 */
public class Thread_DeleteTodo extends Thread{

    private AppDatabase db;
    private Context context;
    private int id;

    /**
     * Konstruktor, initialisiert alle Instanzvariablen
     * @param context Inhalt der App
     * @param id id vom Todo, welches gelöscht werden soll.
     */
    public Thread_DeleteTodo(Context context, int id){
        this.context = context;
        this.id = id;
        db = AppDatabase.getAppDatabase(context);
    }

    /**
     * Löscht das Todo.
     * Wird ausgeführt, wenn der Thread gestartet wird.
     */
    public void run(){
        Todo todo = db.todoDAO().getToDoById(id);
        db.todoDAO().deleteTodo(todo);
    }
}
