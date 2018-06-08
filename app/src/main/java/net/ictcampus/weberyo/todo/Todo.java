package net.ictcampus.weberyo.todo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    private int ID_Todo;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private  String description;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "theme")
    private String theme;

    @ColumnInfo(name = "privacy")
    private boolean privacy;





    public int getID_Todo() {
        return ID_Todo;
    }

    public void setID_Todo(int ID_Todo) {
        this.ID_Todo = ID_Todo;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }


}
