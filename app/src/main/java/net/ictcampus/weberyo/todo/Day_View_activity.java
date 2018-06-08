package net.ictcampus.weberyo.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_CreateTodo;
import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_GetTodayTodos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Day_View_activity extends AppCompatActivity {

    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__view_activity);
        activity = this;
        initFloatButton();
        setDate("");
        ListView list = (ListView) findViewById(R.id.dayview_todo_list);
        list.setAdapter(ArrayAdapter("2018-06-06 00:00:00.000"));

    }

    public ArrayAdapter ArrayAdapter(String date) {
        try {
            ArrayAdapter todosOfToday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            Thread_GetTodayTodos thread_getTodayTodos = new Thread_GetTodayTodos(date, this);
            thread_getTodayTodos.start();
            thread_getTodayTodos.join();
            List<Todo> todos = thread_getTodayTodos.getAll();
            for (Todo todo : todos) {
                todosOfToday.add(todo.getDescription());
            }

            return todosOfToday;


        } catch (Exception e){
            Log.d("DayView_ArrayAdapter()_Error", e.getMessage());
            return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
    }

    public void setDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if(date.equals("")){
            this.setTitle(dateFormat.format(new Date()));
        }

    }

    public void initFloatButton() {
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatday);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Create_Todo_Activity.class);
                intent.putExtra("Activity", "day");
                startActivity(intent);
            }
        });
    }
}
