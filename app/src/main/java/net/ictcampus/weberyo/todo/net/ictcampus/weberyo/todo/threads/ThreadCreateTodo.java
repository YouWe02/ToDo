package net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads;

import android.content.Context;

import net.ictcampus.weberyo.todo.AppDatabase;
import net.ictcampus.weberyo.todo.Todo;

/**
 * In dieser Klasse wird ein neuer Eintrag in der Datenbank verfasst.
 * @db = Datenbank
 * @context = Inhalt der App
 * @title = Titel vom Todo
 * @description = Beschreibung vom Todo
 * @date = Datum vom Todo
 * @theme = Thema vom Todo
 * @privacy = privacy vom Todo
 * @priority = Priorität vom Todo
 */
public class ThreadCreateTodo extends Thread  {
    private AppDatabase db;
    private Context context;
    private String title;
    private String description;
    private String date;
    private String theme;
    private boolean privacy;
    private int priority;

    /**
     * Konstruktor. Hier werden die Instanzvariablen initialisiert & die DB wird abgerufen.
     */
    public ThreadCreateTodo(String title, String description, String date, String theme, boolean privacy, int priority, Context context){
        this.title = title;
        this.description = description;
        this.date = date;
        this.theme = theme;
        this.privacy = privacy;
        this.priority = priority;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
    }

    /**
     * Methode wird beim Starten vom Thread aufgerufen. Speichert das Todo in der DB
     */
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

    /**
     * Aktualisiert alle unteren Instanzvariablen
     * @param title Titel
     * @param description Beschreibung
     * @param date Datum
     * @param theme Kategorie
     * @param privacy Private oder nicht
     * @param priority Priorität
     */
    public void refreshThread(String title, String description, String date, String theme, boolean privacy, int priority){
        this.title = title;
        this.description = description;
        this.date = date;
        this.theme = theme;
        this.privacy = privacy;
        this.priority = priority;
    }
}
