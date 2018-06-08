package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_GetTodayTodos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intentget;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
    private Date actualDate;
    private String actualDateFormatted;
    private List<Button> allMonthButtons = new ArrayList<Button>();
    List<Button> buttonsdisabled = new ArrayList<Button>();
    List<Button> buttonsactivated = new ArrayList<Button>();
    private String actualMonth;
    private Date date;
    private int resetMonth;
    private int resetYear;
    private int resetWeek;
    private int resetDay;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFloatButton();
        actualDate = Calendar.getInstance().getTime();
        actualDateFormatted = dateFormatter.format(actualDate);
        try {
            date = dateFormatter.parse(actualDateFormatted);
        } catch (java.text.ParseException e) {
        }
        if (date.getMonth() == 0) {
            actualMonth = "january";
        }
        if (date.getMonth() == 1) {
            actualMonth = "february";
        }
        if (date.getMonth() == 2) {
            actualMonth = "march";
        }
        if (date.getMonth() == 3) {
            actualMonth = "april";
        }
        if (date.getMonth() == 4) {
            actualMonth = "may";
        }
        if (date.getMonth() == 5) {
            actualMonth = "june";
        }
        if (date.getMonth() == 6) {
            actualMonth = "july";
        }
        if (date.getMonth() == 7) {
            actualMonth = "august";
        }
        if (date.getMonth() == 8) {
            actualMonth = "september";
        }
        if (date.getMonth() == 9) {
            actualMonth = "october";
        }
        if (date.getMonth() == 10) {
            actualMonth = "november";
        }
        if (date.getMonth() == 11) {
            actualMonth = "december";
        }
        intentget = getIntent();
        resetMonth = intentget.getIntExtra("Month", date.getMonth());
        resetYear = intentget.getIntExtra("Year", 2);
        resetWeek = intentget.getIntExtra("Week", 1);
        resetDay = intentget.getIntExtra("Day", 1);
        setDatesToButtons(resetMonth, resetYear);
        getAllToDosMonth();

        final CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.grid);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Intent intentset = new Intent(MainActivity.this, wocheActivity.class);
                intentset.putExtra("Year", resetYear);
                intentset.putExtra("Month", resetMonth);
                intentset.putExtra("Week", resetWeek);
                intentset.putExtra("Day", resetDay);
                startActivity(intentset);
            }

            public void onSwipeRight() {
                swipeRight();
                getAllToDosMonth();
            }

            public void onSwipeLeft() {
                swipeLeft();
                getAllToDosMonth();
            }

            public void onSwipeBottom() {
            }
        });

        for (Button b : allMonthButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button c = (Button) v;
                    int länge = c.getText().toString().length();
                    String text = c.getText().toString();
                    String dateFormatted = "default";
                    String actualdate = "default";
                    int year = 0;
                    if (resetYear == 2) {
                        year = date.getYear() + 1900;
                    } else if (resetYear == 1) {
                        year = date.getYear() + 1899;
                    } else {
                        year = date.getYear() + 1901;
                    }
                    if (länge < 2) {
                        actualdate = 0 + "" + text;
                        if (resetMonth + 1 < 10) {
                            dateFormatted = year + "-" + "0" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                        } else {
                            dateFormatted = year + "-" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                        }
                    } else {
                        actualdate = text + "";
                        if (resetMonth + 1 < 10) {
                            dateFormatted = year + "-" + "0" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                        } else {
                            dateFormatted = year + "-" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                        }
                    }
                    Intent intent = new Intent(MainActivity.this, Day_View_activity.class);
                    intent.putExtra("Year", resetYear);
                    intent.putExtra("Month", resetMonth);
                    intent.putExtra("Week", resetWeek);
                    intent.putExtra("Day", resetDay);
                    intent.putExtra("Date", dateFormatted);
                    startActivity(intent);
                }
            });
        }


    }

    public void setDatesToButtons(int month, int year) {
        ImportDates dates = new ImportDates();
        List<Date> allDates = dates.defineDateinterval();
        List<Date> datesMonth = new ArrayList<Date>();
        String YearText = "default";
        for (Date a : allDates) {
            if (a.getMonth() == month) {
                if (year == 2) {
                    if (a.getYear() == date.getYear()) {
                        datesMonth.add(a);
                        YearText = (a.getYear() + 1900) + "";
                    }
                } else if (year == 1) {
                    if (a.getYear() == date.getYear() - 1) {
                        datesMonth.add(a);
                        YearText = (a.getYear() + 1900) + "";
                    }
                } else {
                    if (a.getYear() == date.getYear() + 1) {
                        datesMonth.add(a);
                        YearText = (a.getYear() + 1900) + "";
                    }
                }
            }
        }

        int i = 0;
        for (Date a : datesMonth) {
            if (i == 0) {
                int weekDay = a.getDay();
                allMonthButtons.clear();
                buttonsactivated.clear();
                buttonsdisabled.clear();
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
                allMonthButtons.add(button1);
                allMonthButtons.add(button2);
                allMonthButtons.add(button3);
                allMonthButtons.add(button4);
                allMonthButtons.add(button5);
                allMonthButtons.add(button6);
                allMonthButtons.add(button7);
                allMonthButtons.add(button8);
                allMonthButtons.add(button9);
                allMonthButtons.add(button10);
                allMonthButtons.add(button11);
                allMonthButtons.add(button12);
                allMonthButtons.add(button13);
                allMonthButtons.add(button14);
                allMonthButtons.add(button15);
                allMonthButtons.add(button16);
                allMonthButtons.add(button17);
                allMonthButtons.add(button18);
                allMonthButtons.add(button19);
                allMonthButtons.add(button20);
                allMonthButtons.add(button21);
                allMonthButtons.add(button22);
                allMonthButtons.add(button23);
                allMonthButtons.add(button24);
                allMonthButtons.add(button25);
                allMonthButtons.add(button26);
                allMonthButtons.add(button27);
                allMonthButtons.add(button28);
                allMonthButtons.add(button29);
                allMonthButtons.add(button30);
                allMonthButtons.add(button31);
                allMonthButtons.add(button32);
                allMonthButtons.add(button33);
                allMonthButtons.add(button34);
                allMonthButtons.add(button35);
                allMonthButtons.add(button36);
                allMonthButtons.add(button37);
                for (Button b : allMonthButtons) {
                    b.setVisibility(View.VISIBLE);
                }
                if (weekDay == 1) {
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button30);
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
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
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else if (weekDay == 2) {
                    buttonsdisabled.add(button1);
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button30);
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else if (weekDay == 3) {
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button31);
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else if (weekDay == 4) {
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button32);
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else if (weekDay == 5) {
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button33);
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else if (weekDay == 6) {
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    buttonsdisabled.add(button5);
                    if (datesMonth.size() == 31) {
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {

                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button34);
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                } else {
                    buttonsdisabled.add(button1);
                    buttonsdisabled.add(button2);
                    buttonsdisabled.add(button3);
                    buttonsdisabled.add(button4);
                    buttonsdisabled.add(button5);
                    buttonsdisabled.add(button6);
                    if (datesMonth.size() == 31) {

                    } else if (datesMonth.size() == 30) {
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 29) {
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    } else if (datesMonth.size() == 28) {
                        buttonsdisabled.add(button35);
                        buttonsdisabled.add(button36);
                        buttonsdisabled.add(button37);
                    }
                    for (Button b : buttonsdisabled) {
                        b.setVisibility(View.INVISIBLE);
                    }
                }
                for (Button b : allMonthButtons) {
                    if (!buttonsdisabled.contains(b)) {
                        buttonsactivated.add(b);
                    }
                }
                int x = 1;
                for (Button b : buttonsactivated) {
                    b.setText(x + "");
                    x++;
                }
            }
            i++;
        }
        String MonthText = "default";
        if (month == 0) {
            MonthText = "January";
        }
        if (month == 1) {
            MonthText = "February";
        }
        if (month == 2) {
            MonthText = "March";
        }
        if (month == 3) {
            MonthText = "April";
        }
        if (month == 4) {
            MonthText = "May";
        }
        if (month == 5) {
            MonthText = "June";
        }
        if (month == 6) {
            MonthText = "July";
        }
        if (month == 7) {
            MonthText = "August";
        }
        if (month == 8) {
            MonthText = "September";
        }
        if (month == 9) {
            MonthText = "October";
        }
        if (month == 10) {
            MonthText = "November";
        }
        if (month == 11) {
            MonthText = "December";
        }
        setTitle(MonthText + " " + YearText);
    }

    public void swipeRight() {
        if (!(getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + ""))) {
            if (getTitle().toString().toLowerCase().contains("january")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1901) + "")) {
                    setDatesToButtons(11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else {

                }
            } else if (getTitle().toString().toLowerCase().contains("december")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else {
                    setDatesToButtons(10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("february")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(0, 1);
                    resetMonth = 0;
                    resetYear = 1;
                } else {
                    setDatesToButtons(0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("march")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                } else {
                    setDatesToButtons(1, 3);
                    resetMonth = 1;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("april")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else {
                    setDatesToButtons(2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("may")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else {
                    setDatesToButtons(3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("june")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else {
                    setDatesToButtons(4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("july")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else {
                    setDatesToButtons(5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("august")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else {
                    setDatesToButtons(6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("september")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else {
                    setDatesToButtons(7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("october")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else {
                    setDatesToButtons(8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("november")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else {
                    setDatesToButtons(9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                }
            }
        } else {
            resetMonth = date.getMonth();
            resetYear = 1;
        }

    }

    public void swipeLeft() {
        if (!(getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains((date.getYear() + 1901) + ""))) {
            if (getTitle().toString().toLowerCase().contains("december")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else {

                }
            } else if (getTitle().toString().toLowerCase().contains("january")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                } else {
                    setDatesToButtons(1, 3);
                    resetMonth = 1;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("february")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else {
                    setDatesToButtons(2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("march")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else {
                    setDatesToButtons(3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("april")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else {
                    setDatesToButtons(4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("may")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else {
                    setDatesToButtons(5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("june")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else {
                    setDatesToButtons(6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("july")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else {
                    setDatesToButtons(7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("august")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else {
                    setDatesToButtons(8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("september")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else {
                    setDatesToButtons(9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("october")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else {
                    setDatesToButtons(10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains("november")) {
                if (getTitle().toString().toLowerCase().contains((date.getYear() + 1900) + "")) {
                    setDatesToButtons(11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains((date.getYear() + 1899) + "")) {
                    setDatesToButtons(11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else {
                    setDatesToButtons(11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                }
            }
        } else {
            resetMonth = date.getMonth();
            resetYear = 3;
        }
    }

    public void getAllToDosMonth(){
        int year = 0;
        if (resetYear == 2) {
            year = date.getYear() + 1900;
        } else if (resetYear == 1) {
            year = date.getYear() + 1899;
        } else {
            year = date.getYear() + 1901;
        }
        int i = 1;
        Thread_GetTodayTodos thread_getTodayTodos = new Thread_GetTodayTodos("default", this);
        for (Button b : buttonsactivated) {
            String dateGetToDos = "default";
            String actualdate = "default";
            if (i < 10) {
                actualdate = 0 + "" + i;
                if (resetMonth + 1 < 10) {
                    dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                } else {
                    dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                }
            } else {
                actualdate = i + "";
                if (resetMonth + 1 < 10) {
                    dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                } else {
                    dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualdate + " " + "00:00:00.000";
                }
            }
            try {
                thread_getTodayTodos.refreshThread(dateGetToDos);
                thread_getTodayTodos.start();
                thread_getTodayTodos.join();
                List<Todo> todos = thread_getTodayTodos.getAll();
                if (todos.size() == 0) {
                    Drawable draw = getResources().getDrawable(R.drawable.greencircle_sized);
                    b.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
                } else if (todos.size() <= 2) {
                    Drawable draw = getResources().getDrawable(R.drawable.yellowcircle_sized);
                    b.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
                } else if (todos.size() <= 5) {
                    Drawable draw = getResources().getDrawable(R.drawable.orangecircle_sized);
                    b.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
                } else {
                    Drawable draw = getResources().getDrawable(R.drawable.redcircle_sized);
                    b.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
                }
            } catch (java.lang.InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public void initFloatButton() {
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatmonth);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Create_Todo_Activity.class);
                startActivity(intent);
            }
        });
    }
}

