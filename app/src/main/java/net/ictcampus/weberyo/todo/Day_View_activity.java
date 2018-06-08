package net.ictcampus.weberyo.todo;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_CreateTodo;
import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_GetTodayTodos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Day_View_activity extends AppCompatActivity {

    private TextView day;
    private int resetYear;
    private int resetMonth;
    private int resetWeek;
    private int resetDay;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
    private Date actualDate;
    private String actualDateFormatted;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__view_activity);
        day = (TextView) findViewById(R.id.dayview_date_header);
        Intent intentget = getIntent();
        String date = intentget.getStringExtra("Date");
        resetYear = intentget.getIntExtra("Year", 2);
        resetMonth = intentget.getIntExtra("Month", 6);
        resetWeek = intentget.getIntExtra("Week" , 1);
        resetDay = intentget.getIntExtra("Day" , 1);
        setDate();
        setDate();
        ListView list = (ListView) findViewById(R.id.dayview_todo_list);
        list.setAdapter(ArrayAdapter(date));
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.linear);
        layout.setOnTouchListener(new OnSwipeTouchListener(Day_View_activity.this) {
            public void onSwipeTop() {

            }

            public void onSwipeRight() {

            }

            public void onSwipeLeft() {

            }

            public void onSwipeBottom() {
                Intent intentset = new Intent(Day_View_activity.this, wocheActivity.class);
                intentset.putExtra("Year", resetYear);
                intentset.putExtra("Month", resetMonth);
                intentset.putExtra("Week", resetWeek);
                intentset.putExtra("Day", resetDay);
                startActivity(intentset);
            }
        });
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


        } catch (Exception e) {
            Log.d("DayView_ArrayAdapter()_Error", e.getMessage());
            return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
    }

    public void setDate() {
        actualDate = Calendar.getInstance().getTime();
        actualDateFormatted = dateFormatter.format(actualDate);
        try {
            date = dateFormatter.parse(actualDateFormatted);
        }
        catch(java.text.ParseException e){
            e.printStackTrace();
        }
        String actualMonth = "default";
        if (date.getMonth() == 0) {
            actualMonth = "January";
        }
        if (date.getMonth() == 1) {
            actualMonth = "February";
        }
        if (date.getMonth() == 2) {
            actualMonth = "March";
        }
        if (date.getMonth() == 3) {
            actualMonth = "April";
        }
        if (date.getMonth() == 4) {
            actualMonth = "May";
        }
        if (date.getMonth() == 5) {
            actualMonth = "June";
        }
        if (date.getMonth() == 6) {
            actualMonth = "July";
        }
        if (date.getMonth() == 7) {
            actualMonth = "August";
        }
        if (date.getMonth() == 8) {
            actualMonth = "September";
        }
        if (date.getMonth() == 9) {
            actualMonth = "October";
        }
        if (date.getMonth() == 10) {
            actualMonth = "November";
        }
        if (date.getMonth() == 11) {
            actualMonth = "December";
        }
        int year = 1;
        if (resetYear == 2) {
            year = date.getYear() + 1900;
        } else if (resetYear == 1) {
            year = date.getYear() + 1899;
        } else {
            year = date.getYear() + 1901;
        }
        setTitle(resetDay + " " + actualMonth + " " + year);
    }
}
