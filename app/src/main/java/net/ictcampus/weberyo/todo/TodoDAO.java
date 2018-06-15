package net.ictcampus.weberyo.todo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoDAO {

    //selects
    //get all ToDos of a date ordered by the priority of the ToDo
    @Query("SELECT * FROM todo WHERE date = :date ORDER BY priority DESC")
    List<Todo> getTodoByDay(String date);

    //get the 4 ToDos of a date who have the highest priority
    @Query("SELECT * FROM todo WHERE date = :date ORDER BY priority DESC LIMIT 4 ")
    List<Todo> getTodoByDayLimi4(String date);

    @Query("SELECT * FROM todo WHERE date = :date AND title = :title ORDER BY title DESC LIMIT 1")
    Todo getToDoByTitle(String date, String title);

    @Query("SELECT * FROM todo WHERE ID_Todo = :id")
    Todo getToDoById(int id);


    //Inserts

    @Insert
    void insertTodo(Todo... todos);

    //deletes
    @Delete
    void deleteTodo(Todo todo);




}
