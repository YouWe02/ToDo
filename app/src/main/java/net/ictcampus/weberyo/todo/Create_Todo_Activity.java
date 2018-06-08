package net.ictcampus.weberyo.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

import java.time.Year;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Create_Todo_Activity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__todo_);
        initDatePicker();
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
}
