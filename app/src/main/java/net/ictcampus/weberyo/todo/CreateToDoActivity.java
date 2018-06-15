package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.ThreadCreateTodo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreateToDoActivity extends AppCompatActivity {
    private String[] days_array;
    private Map<String, String> months = new LinkedHashMap<String, String>();
    private String[] months_array = new String[12];
    private int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private String[] years;
    private NumberPicker picker_day;
    private NumberPicker picker_month;
    private NumberPicker picker_year;
    private Spinner spinner_category;
    private View.OnClickListener buttonListener;

    //all final values to insert in db
    private boolean allValuesRight;
    private String title;
    private String date;
    private int priority;
    private boolean privacy;
    private String description;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__todo_);
        initToolbar();
        initDatePicker();
        initCategorySpinner();
        initListener();
    }

    public void initToolbar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void initDatePicker(){
        picker_day = (NumberPicker) findViewById(R.id.datepicker_day);
        picker_month = (NumberPicker) findViewById(R.id.datepicker_month);
        picker_year = (NumberPicker) findViewById(R.id.datepicker_year);
        int i = 0;

        //add months
        months.put("january", "31");
        months.put("february", "28");
        months.put("march", "31");
        months.put("april", "30");
        months.put("may", "31");
        months.put("june", "30");
        months.put("july", "31");
        months.put("august", "31");
        months.put("september", "30");
        months.put("october", "31");
        months.put("november", "30");
        months.put("december", "31");

        //add months to picker
        for(String month : months.keySet()){
            months_array[i] = month;
            i++;
        }

        picker_month.setMinValue(0);
        picker_month.setMaxValue(months_array.length - 1);
        picker_month.setDisplayedValues(months_array);

        //Adds the right amount of Days to the numberpicker of the days, depending on the actual month & year
        picker_month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                refreshDays(newVal);
            }
        });

        //adds the years to the picker
        years = new String[]{Integer.toString(year - 1), Integer.toString(year), Integer.toString(year + 1)};
        picker_year.setMinValue(0);
        picker_year.setMaxValue(years.length - 1);
        picker_year.setDisplayedValues(years);
        picker_year.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(Integer.parseInt(years[newVal]) % 4 == 0){
                    if(Integer.parseInt(years[newVal]) % 100 == 0){
                        if (Integer.parseInt(years[newVal]) % 400 == 0){
                            months.put("february", "29");
                        }
                        else {
                            months.put("february", "28");
                        }
                    }
                    else {
                        months.put("february", "29");
                    }
                }
                else {
                    months.put("february", "28");
                }
                refreshDays(picker_month.getValue());
            }
        });

        //Set default value to today
        picker_month.setValue(month);
        refreshDays(picker_month.getValue());
        picker_day.setValue(day - 1);
        picker_year.setValue(1);

    }

    public void refreshDays(int newVal){
        int k = 0;
        days_array = new String[Integer.parseInt(months.get(months_array[newVal]))];
        for (int j = 0; j < days_array.length; j++){
            k = j + 1;
            days_array[j] = Integer.toString(k);
        }
        picker_day.setMinValue(0);
        picker_day.setDisplayedValues(null);
        picker_day.setMaxValue(days_array.length - 1);
        picker_day.setDisplayedValues(days_array);
    }

    public void initCategorySpinner(){
        spinner_category = (Spinner) findViewById(R.id.spinner_category);
        List<String> icons = new ArrayList<String>();
        List<String> categories = new ArrayList<String>();
        String[] icons_array;
        String[] categories_array;
        for (Field field : R.string.class.getDeclaredFields()){
            if(Modifier.isStatic(field.getModifiers()) && !Modifier.isPrivate(field.getModifiers())){
                try {
                    if(field.getName().startsWith("category_")){
                        int id = field.getInt(null);
                        String[] name = field.getName().split("_");
                        categories.add(name[1]);
                        icons.add(getString(id));
                    }
                } catch (IllegalArgumentException e){

                } catch (IllegalAccessException e){

                }
            }
        }
        icons_array = new String[icons.size()];
        categories_array = new String[categories.size()];
        icons_array = icons.toArray(icons_array);
        categories_array = categories.toArray(categories_array);

        ArrayAdapterSpinner spinner = new ArrayAdapterSpinner(this, icons_array, categories_array);
        spinner_category.setAdapter(spinner);
    }

    public void initListener(){
        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allValuesRight = true;

                //Read Date an do it in the right pattern:
                picker_day = (NumberPicker) findViewById(R.id.datepicker_day);
                picker_month = (NumberPicker) findViewById(R.id.datepicker_month);
                picker_year = (NumberPicker) findViewById(R.id.datepicker_year);

                String day = picker_day.getDisplayedValues()[picker_day.getValue()];
                if(day.length() < 2){
                    day = "0" + day;
                }

                String month = Integer.toString(picker_month.getValue() + 1);
                if(month.length() < 2){
                    month = "0" + month;
                }

                String year = picker_year.getDisplayedValues()[picker_year.getValue()];

                date = year + "-" + month + "-" + day + " 00:00:00.000";

                //Read Title
                title = "";
                TextView title_todo = (TextView) findViewById(R.id.Title_Todo);
                title = title_todo.getText().toString();
                if(title.length() < 1){
                    allValuesRight = false;
                }

                //Read Priority
                priority = 0;
                RadioButton button;
                RadioGroup prio_group = (RadioGroup) findViewById(R.id.radiobutton_priority);
                int id = prio_group.getCheckedRadioButtonId();
                int count = prio_group.getChildCount();
                for (int i = 0; i < count; i++){
                    if(id == prio_group.getChildAt(i).getId()){
                        button = (RadioButton) prio_group.getChildAt(i);
                        priority = Integer.parseInt(button.getText().toString());
                    }
                }
                if (priority == 0){
                    allValuesRight = false;
                }

                //Read privacy
                CheckBox box = (CheckBox) findViewById(R.id.private_checkbox);
                if(box.isChecked()){
                    privacy = true;
                }
                else {
                    privacy = false;
                }

                //Read description
                description = "";
                TextView description_todo = (TextView) findViewById(R.id.Description_Todo);
                description = description_todo.getText().toString();
                if(description.length() < 1){
                    allValuesRight = false;
                }

                //Read category
                Spinner cat = (Spinner) findViewById(R.id.spinner_category);
                category = cat.getSelectedItem().toString();
                category = "category_" + category;

                if (allValuesRight) {
                    createTodo();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill out all fields";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        };
        Button button = (Button) findViewById(R.id.submit_todo);
        button.setOnClickListener(buttonListener);
    }

    public void createTodo(){
        try {
            ThreadCreateTodo createTodo = new ThreadCreateTodo(title, description, date, category, privacy, priority, this);
            createTodo.start();
            createTodo.join();
            CharSequence text = "ToDo created!";
            Toast hint = Toast.makeText(this, text, Toast.LENGTH_LONG);
            clearFields();
            hint.show();
            finish();
        }catch (Exception e){
            Log.e("Create Todo", e.getMessage());
        }
    }

    public void clearFields(){
        RadioButton button;
        RadioGroup prio_group = (RadioGroup) findViewById(R.id.radiobutton_priority);
        int id = prio_group.getCheckedRadioButtonId();
        int count = prio_group.getChildCount();
        EditText title = (EditText) findViewById(R.id.Title_Todo);
        EditText description = (EditText) findViewById(R.id.Description_Todo);
        CheckBox privacy = (CheckBox) findViewById(R.id.private_checkbox);
        privacy.setChecked(false);
        title.setText("");
        description.setText("");
        for (int i = 0; i < count; i++){
            if(id == prio_group.getChildAt(i).getId()){
                button = (RadioButton) prio_group.getChildAt(i);
                button.setChecked(false);
            }
        }
    }
    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return  super.onOptionsItemSelected(item);
    }
}
