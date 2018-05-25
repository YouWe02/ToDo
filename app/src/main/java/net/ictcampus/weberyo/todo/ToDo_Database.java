package net.ictcampus.weberyo.todo;

import java.util.Date;

public class ToDo_Database {
    private Date date;
    private String title;
    private String description;
    private int priority;
    private String theme;
    private boolean privat;

    public ToDo_Database (Date date, String title, String description, int priority, String theme, boolean privat){
        this.date = date;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.theme = theme;
        this.privat = privat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isPrivat() {
        return privat;
    }

    public void setPrivat(boolean privat) {
        this.privat = privat;
    }
}
