package net.ictcampus.weberyo.todo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface TodoDAO {

    //Querys

    @Query("SELECT * FROM todo")
    List<Todo> getAll();

    @Query("SELECT * FROM todo WHERE date = :date ORDER BY priority DESC")
    List<Todo> getTodoByDay(String date);

    @Query("SELECT * FROM todo WHERE date = :date ORDER BY priority DESC LIMIT 4 ")
    List<Todo> getTodoByDayLimi4(String date);

    @Query("SELECT * FROM todo WHERE date = :date AND title = :title ORDER BY title DESC LIMIT 1")
    Todo getToDoByTitle(String date, String title);

    @Query("SELECT * FROM todo WHERE ID_Todo = :id")
    Todo getToDoById(int id);


    //Inserts

    @Insert
    void insertTodo(Todo... todos);

    //Deletes

    @Delete
    void deleteTodo(Todo todo);




}
