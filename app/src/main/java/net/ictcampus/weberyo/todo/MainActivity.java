package net.ictcampus.weberyo.todo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDatesToButtons(1, 2);

        GridLayout layout = (GridLayout) findViewById(R.id.grid);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {

            }
            public void onSwipeRight() {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
                Date actualDate = Calendar.getInstance().getTime();
                String actualDateFormatted = dateFormatter.format(actualDate);
                Date date;
                try {
                    date = dateFormatter.parse(actualDateFormatted);
                    String monthtest;
                    monthtest = "default";
                    if(date.getMonth() == 0){
                        monthtest = "january";
                    }
                    if(date.getMonth() == 1){
                        monthtest = "february";
                    }
                    if(date.getMonth() == 2){
                        monthtest = "march";
                    }
                    if(date.getMonth() == 3){
                        monthtest = "april";
                    }
                    if(date.getMonth() == 4){
                        monthtest = "may";
                    }
                    if(date.getMonth() == 5){
                        monthtest = "june";
                    }
                    if(date.getMonth() == 6){
                        monthtest = "july";
                    }
                    if(date.getMonth() == 7){
                        monthtest = "august";
                    }
                    if(date.getMonth() == 8){
                        monthtest = "september";
                    }
                    if(date.getMonth() == 9){
                        monthtest = "october";
                    }
                    if(date.getMonth() == 10){
                        monthtest = "november";
                    }
                    if(date.getMonth() == 11){
                        monthtest = "december";
                    }
                    if(!(getTitle().toString().toLowerCase().contains(monthtest) & getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + ""))){
                        if(getTitle().toString().toLowerCase().contains("january")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(11, 1);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1901) + "")){
                                setDatesToButtons(11, 2);
                            }
                            else{

                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("december")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(10, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(10, 1);
                            }
                            else{
                                setDatesToButtons(10, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("february")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(0, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(0, 1);
                            }
                            else{
                                setDatesToButtons(0, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("march")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(1, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(1, 1);
                            }
                            else{
                                setDatesToButtons(1, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("april")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(2, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(2, 1);
                            }
                            else{
                                setDatesToButtons(2, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("may")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(3, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(3, 1);
                            }
                            else{
                                setDatesToButtons(3, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("june")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(4, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(4, 1);
                            }
                            else{
                                setDatesToButtons(4, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("july")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(5, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(5, 1);
                            }
                            else{
                                setDatesToButtons(5, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("august")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(6, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(6, 1);
                            }
                            else{
                                setDatesToButtons(6, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("september")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(7, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(7, 1);
                            }
                            else{
                                setDatesToButtons(7, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("october")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(8, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(8, 1);
                            }
                            else{
                                setDatesToButtons(8, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("november")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(9, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(9, 1);
                            }
                            else{
                                setDatesToButtons(9, 3);
                            }
                        }
                    }

                }
                catch(java.text.ParseException e){
                    e.printStackTrace();
                }
            }
            public void onSwipeLeft() {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
                Date actualDate = Calendar.getInstance().getTime();
                String actualDateFormatted = dateFormatter.format(actualDate);
                Date date;
                try {
                    date = dateFormatter.parse(actualDateFormatted);
                    String monthtest;
                    monthtest = "default";
                    if(date.getMonth() == 0){
                        monthtest = "january";
                    }
                    if(date.getMonth() == 1){
                        monthtest = "february";
                    }
                    if(date.getMonth() == 2){
                        monthtest = "march";
                    }
                    if(date.getMonth() == 3){
                        monthtest = "april";
                    }
                    if(date.getMonth() == 4){
                        monthtest = "may";
                    }
                    if(date.getMonth() == 5){
                        monthtest = "june";
                    }
                    if(date.getMonth() == 6){
                        monthtest = "july";
                    }
                    if(date.getMonth() == 7){
                        monthtest = "august";
                    }
                    if(date.getMonth() == 8){
                        monthtest = "september";
                    }
                    if(date.getMonth() == 9){
                        monthtest = "october";
                    }
                    if(date.getMonth() == 10){
                        monthtest = "november";
                    }
                    if(date.getMonth() == 11){
                        monthtest = "december";
                    }
                    if(!(getTitle().toString().toLowerCase().contains(monthtest) & getTitle().toString().toLowerCase().contains((date.getYear() + 1901) + ""))){
                        if(getTitle().toString().toLowerCase().contains("december")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(0, 3);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(0, 2);
                            }
                            else{

                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("january")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(1, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(1, 1);
                            }
                            else{
                                setDatesToButtons(1, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("february")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(2, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(2, 1);
                            }
                            else{
                                setDatesToButtons(2, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("march")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(3, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(3, 1);
                            }
                            else{
                                setDatesToButtons(3, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("april")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(4, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(4, 1);
                            }
                            else{
                                setDatesToButtons(4, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("may")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(5, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(5, 1);
                            }
                            else{
                                setDatesToButtons(5, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("june")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(6, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(6, 1);
                            }
                            else{
                                setDatesToButtons(6, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("july")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(7, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(7, 1);
                            }
                            else{
                                setDatesToButtons(7, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("august")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(8, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(8, 1);
                            }
                            else{
                                setDatesToButtons(8, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("september")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(9, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(9, 1);
                            }
                            else{
                                setDatesToButtons(9, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("october")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(10, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(10, 1);
                            }
                            else{
                                setDatesToButtons(10, 3);
                            }
                        }
                        else if(getTitle().toString().toLowerCase().contains("november")){
                            if(getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")){
                                setDatesToButtons(11, 2);
                            }
                            else if(getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")){
                                setDatesToButtons(11, 1);
                            }
                            else{
                                setDatesToButtons(11, 3);
                            }
                        }
                    }
                }
                catch(java.text.ParseException e){
                    e.printStackTrace();
                }
            }
            public void onSwipeBottom() {

            }
        });
    }

    public void setDatesToButtons(int month, int year){
        ImportDates dates = new ImportDates();
        List<Date> allDates = dates.defineDateinterval();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        List<Date> datesMonth = new ArrayList<Date>();
        String YearText = "default";
        for(Date a:allDates){
            if(a.getMonth() == month){
                Date actualDate = Calendar.getInstance().getTime();
                String actualDateFormatted = dateFormatter.format(actualDate);
                try{
                    Date date = dateFormatter.parse(actualDateFormatted);
                    if(year == 2){
                        if(a.getYear() == date.getYear()){
                            datesMonth.add(a);
                            YearText = (a.getYear() + 1900) + "";
                        }
                    }
                    else if(year == 1){
                        if(a.getYear() == date.getYear() - 1){
                            datesMonth.add(a);
                            YearText = (a.getYear() + 1900) + "";
                        }
                    }
                    else if(year == 3){
                        if(a.getYear() == date.getYear() + 1){
                            datesMonth.add(a);
                            YearText = (a.getYear() + 1900) + "";
                        }
                    }
                }
                catch(java.text.ParseException e){
                    e.printStackTrace();
                }
            }
        }
        int i = 0;
        for(Date a:datesMonth){
            if(i == 0){
                int weekDay = a.getDay();
                List<Button> buttonsdisabled = new ArrayList<Button>();
                List<Button> buttonsall = new ArrayList<Button>();
                List<Button> buttonsactivated = new ArrayList<Button>();
                Button button1 = (Button) findViewById(R.id.kalendermonat1);
                Button button2 = (Button) findViewById(R.id.kalendermonat2);
                Button button3 = (Button) findViewById(R.id.kalendermonat3);
                Button button4 = (Button) findViewById(R.id.kalendermonat4);
                Button button5 = (Button) findViewById(R.id.kalendermonat5);
                Button button6 = (Button) findViewById(R.id.kalendermonat6);
                Button button7 = (Button) findViewById(R.id.kalendermonat7);
                Button button8 = (Button) findViewById(R.id.kalendermonat8);
                Button button9 = (Button) findViewById(R.id.kalendermonat9);
                Button button10 = (Button) findViewById(R.id.kalendermonat10);
                Button button11 = (Button) findViewById(R.id.kalendermonat11);
                Button button12 = (Button) findViewById(R.id.kalendermonat12);
                Button button13 = (Button) findViewById(R.id.kalendermonat13);
                Button button14 = (Button) findViewById(R.id.kalendermonat14);
                Button button15 = (Button) findViewById(R.id.kalendermonat15);
                Button button16 = (Button) findViewById(R.id.kalendermonat16);
                Button button17 = (Button) findViewById(R.id.kalendermonat17);
                Button button18 = (Button) findViewById(R.id.kalendermonat18);
                Button button19 = (Button) findViewById(R.id.kalendermonat19);
                Button button20 = (Button) findViewById(R.id.kalendermonat20);
                Button button21 = (Button) findViewById(R.id.kalendermonat21);
                Button button22 = (Button) findViewById(R.id.kalendermonat22);
                Button button23 = (Button) findViewById(R.id.kalendermonat23);
                Button button24 = (Button) findViewById(R.id.kalendermonat24);
                Button button25 = (Button) findViewById(R.id.kalendermonat25);
                Button button26 = (Button) findViewById(R.id.kalendermonat26);
                Button button27 = (Button) findViewById(R.id.kalendermonat27);
                Button button28 = (Button) findViewById(R.id.kalendermonat28);
                Button button29 = (Button) findViewById(R.id.kalendermonat29);
                Button button30 = (Button) findViewById(R.id.kalendermonat30);
                Button button31 = (Button) findViewById(R.id.kalendermonat31);
                Button button32 = (Button) findViewById(R.id.kalendermonat32);
                Button button33 = (Button) findViewById(R.id.kalendermonat33);
                Button button34 = (Button) findViewById(R.id.kalendermonat34);
                Button button35 = (Button) findViewById(R.id.kalendermonat35);
                Button button36 = (Button) findViewById(R.id.kalendermonat36);
                Button button37 = (Button) findViewById(R.id.kalendermonat37);
                buttonsall.add(button1);
                buttonsall.add(button2);
                buttonsall.add(button3);
                buttonsall.add(button4);
                buttonsall.add(button5);
                buttonsall.add(button6);
                buttonsall.add(button7);
                buttonsall.add(button8);
                buttonsall.add(button9);
                buttonsall.add(button10);
                buttonsall.add(button11);
                buttonsall.add(button12);
                buttonsall.add(button13);
                buttonsall.add(button14);
                buttonsall.add(button15);
                buttonsall.add(button16);
                buttonsall.add(button17);
                buttonsall.add(button18);
                buttonsall.add(button19);
                buttonsall.add(button20);
                buttonsall.add(button21);
                buttonsall.add(button22);
                buttonsall.add(button23);
                buttonsall.add(button24);
                buttonsall.add(button25);
                buttonsall.add(button26);
                buttonsall.add(button27);
                buttonsall.add(button28);
                buttonsall.add(button29);
                buttonsall.add(button30);
                buttonsall.add(button31);
                buttonsall.add(button32);
                buttonsall.add(button33);
                buttonsall.add(button34);
                buttonsall.add(button35);
                buttonsall.add(button36);
                buttonsall.add(button37);
                for(Button b:buttonsall){
                    b.setVisibility(View.VISIBLE);
                }
                if(weekDay == 1){
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button30);
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button29);
                        buttonsdisabled.add(button30);
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else if(weekDay == 2){
                    buttonsdisabled.add(button1);
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button30);
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else if(weekDay == 3){
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else if(weekDay == 4){
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else if(weekDay == 5){
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else if(weekDay == 6){
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    buttonsdisabled.add(button5);
                    if(datesMonth.size() == 31){
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){

                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    buttonsdisabled.add(button5);
                    buttonsdisabled.add(button6);
                    if(datesMonth.size() == 31){

                    }
                    else if(datesMonth.size() == 30){
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 29){
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    else if(datesMonth.size() == 28){
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for(Button b:buttonsdisabled){
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                for(Button b:buttonsall){
                    if(!buttonsdisabled.contains(b)){
                        buttonsactivated.add(b);
                    }
                }
                int x = 1;
                for(Button b:buttonsactivated){
                    b.setText(x + "");
                    x++;
                }
            }
            i++;
        }
        String MonthText = "default";
        if(month == 0){
            MonthText = "January";
        }
        if(month == 1){
            MonthText = "February";
        }
        if(month == 2){
            MonthText = "March";
        }
        if(month == 3){
            MonthText = "April";
        }
        if(month == 4){
            MonthText = "May";
        }
        if(month == 5){
            MonthText = "June";
        }
        if(month == 6){
            MonthText = "July";
        }
        if(month == 7){
            MonthText = "August";
        }
        if(month == 8){
            MonthText = "September";
        }
        if(month == 9){
            MonthText = "October";
        }
        if(month == 10){
            MonthText = "November";
        }
        if(month == 11){
            MonthText = "December";
        }
        setTitle(MonthText + " " + YearText);
    }
}
