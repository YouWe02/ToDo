package net.ictcampus.weberyo.todo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.ThreadGetTodayTodosLimit4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MIN_DISTANCEUP = 50;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public static Activity activity;
    private List<LinearLayout> allWeekButtons = new ArrayList<LinearLayout>();
    private int resetMonth;
    private int resetYear;
    private int resetWeek;
    private int resetDay;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
    private Date actualDate;
    private String actualDateFormatted;
    private String actualMonth;
    private Date date;
    private ProgressDialog dialog;
    private GestureDetector mGestureDetector3;
    private View viewGestureisOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woche);
        activity = this;

        mGestureDetector3 = new GestureDetector(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS, WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        final Intent intent = getIntent();
        initFloatButton();
        resetMonth = intent.getIntExtra("Month", 5);
        resetYear = intent.getIntExtra("Year", 2);
        resetWeek = intent.getIntExtra("Week", 1);
        resetDay = intent.getIntExtra("Day", 1);
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche1));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche2));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche3));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche4));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche5));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche6));
        allWeekButtons.add((LinearLayout) findViewById(R.id.kalenderwoche7));
        actualDate = Calendar.getInstance().getTime();
        actualDateFormatted = dateFormatter.format(actualDate);
        try {
            date = dateFormatter.parse(actualDateFormatted);
        } catch (java.text.ParseException e) {
        }
        resetMonth = intent.getIntExtra("Month", date.getMonth());
        resetYear = intent.getIntExtra("Year", 2);
        resetWeek = intent.getIntExtra("Week", 1);
        resetDay = intent.getIntExtra("Day", 1);
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
        setWeek(1, resetMonth, resetYear);

        for (LinearLayout a : allWeekButtons) {
            a.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, final MotionEvent event) {
                    viewGestureisOn = (LinearLayout) v;
                    mGestureDetector3.onTouchEvent(event);
                    return true;
                }
            });
        }

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Constraint);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                viewGestureisOn = (ConstraintLayout) v;
                mGestureDetector3.onTouchEvent(event);
                return true;
            }
        });
    }

    public boolean setWeek(int woche, int monat, int jahr) {
        for (LinearLayout b : allWeekButtons) {
            b.setVisibility(View.VISIBLE);
            TextView day = (TextView) b.getChildAt(0);
            day.setText("default");
        }
        boolean check = true;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date actualDate = Calendar.getInstance().getTime();
        String actualDateFormatted = dateFormatter.format(actualDate);
        Date date;
        try {
            date = dateFormatter.parse(actualDateFormatted);
            ImportDates dates = new ImportDates();
            List<Date> allDates = dates.defineDateinterval();
            List<Date> datesActualMonth = new ArrayList<Date>();
            List<Date> datesMonthBefore = new ArrayList<Date>();
            List<Date> datesMonthAfter = new ArrayList<Date>();
            List<Date> woche1 = new ArrayList<Date>();
            List<Date> woche2 = new ArrayList<Date>();
            List<Date> woche3 = new ArrayList<Date>();
            List<Date> woche4 = new ArrayList<Date>();
            List<Date> woche5 = new ArrayList<Date>();
            List<Date> woche6 = new ArrayList<Date>();
            for (Date a : allDates) {
                if (a.getMonth() == monat) {
                    if (jahr == 2 & a.getYear() == date.getYear()) {
                        datesActualMonth.add(a);
                    } else if (jahr == 1 & a.getYear() == date.getYear() - 1) {
                        datesActualMonth.add(a);
                    } else if (jahr == 3 & a.getYear() == date.getYear() + 1) {
                        datesActualMonth.add(a);
                    }
                }
                if (monat != 11 & monat != 0) {
                    if (a.getMonth() == monat + 1) {
                        if (jahr == 2 & a.getYear() == date.getYear()) {
                            datesMonthAfter.add(a);
                        } else if (jahr == 1 & a.getYear() == date.getYear() - 1) {
                            datesMonthAfter.add(a);
                        } else if (jahr == 3 & a.getYear() == date.getYear() + 1) {
                            datesMonthAfter.add(a);
                        }
                    }
                    if (a.getMonth() == monat - 1) {
                        if (jahr == 2 & a.getYear() == date.getYear()) {
                            datesMonthBefore.add(a);
                        } else if (jahr == 1 & a.getYear() == date.getYear() - 1) {
                            datesMonthBefore.add(a);
                        } else if (jahr == 3 & a.getYear() == date.getYear() + 1) {
                            datesMonthBefore.add(a);
                        }
                    }
                } else if (monat == 11) {
                    if (a.getMonth() == 0) {
                        if (jahr == 2 & a.getYear() == date.getYear() + 1) {
                            datesMonthAfter.add(a);
                        } else if (jahr == 1 & a.getYear() == date.getYear()) {
                            datesMonthAfter.add(a);
                        }
                    }
                    if (a.getMonth() == monat - 1) {
                        if (jahr == 2 & a.getYear() == date.getYear()) {
                            datesMonthBefore.add(a);
                        } else if (jahr == 1 & a.getYear() == date.getYear() - 1) {
                            datesMonthBefore.add(a);
                        } else if (jahr == 3 & a.getYear() == date.getYear() + 1) {
                            datesMonthBefore.add(a);
                        }
                    }
                } else if (monat == 0) {
                    if (a.getMonth() == monat + 1) {
                        if (jahr == 2 & a.getYear() == date.getYear()) {
                            datesMonthAfter.add(a);
                        } else if (jahr == 1 & a.getYear() == date.getYear() - 1) {
                            datesMonthAfter.add(a);
                        } else if (jahr == 3 & a.getYear() == date.getYear() + 1) {
                            datesMonthAfter.add(a);
                        }
                    }
                    if (a.getMonth() == 11) {
                        if (jahr == 2 & a.getYear() == date.getYear() - 1) {
                            datesMonthBefore.add(a);
                        } else if (jahr == 3 & a.getYear() == date.getYear()) {
                            datesMonthBefore.add(a);
                        }
                    }
                }
            }
            for (Date b : datesMonthAfter) {
                for (Date c : datesMonthBefore) {
                    if (b.getDate() == 1) {
                        if (c.getDate() == datesMonthBefore.size()) {
                            if (c.getDay() == 0) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche5.add(a);
                                    }
                                }
                            } else if (c.getDay() == 1) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche5.add(a);
                                    }
                                }
                            } else if (c.getDay() == 2) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche5.add(a);
                                    }
                                }
                            } else if (c.getDay() == 3) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche5.add(a);
                                    }
                                }
                            } else if (c.getDay() == 4) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche5.add(a);
                                    }
                                }
                            } else if (c.getDay() == 5) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche5.add(a);
                                    } else {
                                        woche6.add(a);
                                    }
                                }
                            } else if (c.getDay() == 6) {
                                for (Date a : datesActualMonth) {
                                    if (a.getDate() == 1) {
                                        woche1.add(a);
                                    } else if (a.getDate() == 2) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 3) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 4) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 5) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 6) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 7) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 8) {
                                        woche2.add(a);
                                    } else if (a.getDate() == 9) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 10) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 11) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 12) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 13) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 14) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 15) {
                                        woche3.add(a);
                                    } else if (a.getDate() == 16) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 17) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 18) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 19) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 20) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 21) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 22) {
                                        woche4.add(a);
                                    } else if (a.getDate() == 23) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 24) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 25) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 26) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 27) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 28) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 29) {
                                        woche5.add(a);
                                    } else if (a.getDate() == 30) {
                                        woche6.add(a);
                                    } else {
                                        woche6.add(a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (woche == 1) {
                if (woche1.size() != 0) {
                    for (Date a : woche1) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 1;
                        String stringTitle = "Week 1 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            } else if (woche == 2) {
                if (woche2.size() != 0) {
                    for (Date a : woche2) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 2;
                        String stringTitle = "Week 2 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            } else if (woche == 3) {
                if (woche3.size() != 0) {
                    for (Date a : woche3) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 3;
                        String stringTitle = "Week 3 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            } else if (woche == 4) {
                if (woche4.size() != 0) {
                    for (Date a : woche4) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 4;
                        String stringTitle = "Week 4 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            } else if (woche == 5) {
                if (woche5.size() != 0) {
                    for (Date a : woche5) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 5;
                        String stringTitle = "Week 5 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            } else {
                if (woche6.size() != 0) {
                    for (Date a : woche6) {
                        String stringMonth = "default";
                        if (a.getMonth() == 0) {
                            stringMonth = "January";
                        } else if (a.getMonth() == 1) {
                            stringMonth = "February";
                        } else if (a.getMonth() == 2) {
                            stringMonth = "March";
                        } else if (a.getMonth() == 3) {
                            stringMonth = "April";
                        } else if (a.getMonth() == 4) {
                            stringMonth = "May";
                        } else if (a.getMonth() == 5) {
                            stringMonth = "June";
                        } else if (a.getMonth() == 6) {
                            stringMonth = "July";
                        } else if (a.getMonth() == 7) {
                            stringMonth = "August";
                        } else if (a.getMonth() == 8) {
                            stringMonth = "September";
                        } else if (a.getMonth() == 9) {
                            stringMonth = "October";
                        } else if (a.getMonth() == 10) {
                            stringMonth = "November";
                        } else if (a.getMonth() == 11) {
                            stringMonth = "December";
                        }
                        resetWeek = 6;
                        String stringTitle = "Week 6 " + stringMonth + " " + (a.getYear() + 1900);
                        setTitle(stringTitle);
                        if (a.getDay() == 0) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche7);
                            deleteOldTodo(button);
                            String string = "SUN " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 1) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche1);
                            deleteOldTodo(button);
                            String string = "MON " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 2) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche2);
                            deleteOldTodo(button);
                            String string = "TUE " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 3) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche3);
                            deleteOldTodo(button);
                            String string = "WED " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 4) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche4);
                            deleteOldTodo(button);
                            String string = "THU " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else if (a.getDay() == 5) {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche5);
                            deleteOldTodo(button);
                            String string = "FRI " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        } else {
                            LinearLayout button = (LinearLayout) findViewById(R.id.kalenderwoche6);
                            deleteOldTodo(button);
                            String string = "SAT " + a.getDate();
                            TextView text = (TextView) button.getChildAt(0);
                            text.setText(string);
                            initGetTodosForThisButtons(button, a);
                        }
                    }
                    for (LinearLayout b : allWeekButtons) {
                        TextView text = (TextView) b.getChildAt(0);
                        if (text.getText().toString().toLowerCase().contains("default")) {
                            b.setVisibility(View.INVISIBLE);
                        }
                    }
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (
                java.text.ParseException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void swipeRight() {
        if (!(getTitle().toString().toLowerCase().contains("week 1") & getTitle().toString().toLowerCase().contains(actualMonth.toLowerCase()) & getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + ""))) {
            if (getTitle().toString().toLowerCase().contains("january")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 11, 1) == false) {
                            setWeek(5, 11, 1);
                            resetMonth = 11;
                            resetYear = 1;
                        } else {
                            resetMonth = 11;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 0, 2) == false) {
                            setWeek(6, 11, 1);
                            resetMonth = 11;
                            resetYear = 1;

                        } else {
                            resetMonth = 0;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 0, 2);
                        resetMonth = 0;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 0, 2);
                        resetMonth = 0;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 0, 2);
                        resetMonth = 0;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 0, 2);
                        resetMonth = 0;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 0, 1) == false) {
                            resetMonth = 0;
                            resetYear = 1;

                        } else {
                            resetMonth = 0;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 0, 1);
                        resetMonth = 0;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 0, 1);
                        resetMonth = 0;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 0, 1);
                        resetMonth = 0;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 0, 1);
                        resetMonth = 0;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 11, 2) == false) {
                            setWeek(5, 11, 2);
                            resetMonth = 11;
                            resetYear = 2;

                        } else {
                            resetMonth = 11;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 0, 3) == false) {
                            setWeek(6, 11, 2);
                            resetMonth = 11;
                            resetYear = 2;

                        } else {
                            resetMonth = 0;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 0, 3);
                        resetMonth = 0;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 0, 3);
                        resetMonth = 0;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 0, 3);
                        resetMonth = 0;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 0, 3);
                        resetMonth = 0;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("february")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 0, 2) == false) {
                            setWeek(5, 0, 2);
                            resetMonth = 0;
                            resetYear = 2;

                        } else {
                            resetMonth = 0;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 1, 2) == false) {
                            setWeek(6, 0, 2);
                            resetMonth = 0;
                            resetYear = 2;

                        } else {
                            resetMonth = 1;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        if (setWeek(2, 1, 2) == false) {
                            setWeek(1, 1, 2);
                            resetMonth = 1;
                            resetYear = 2;

                        } else {
                            resetMonth = 1;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 1, 2);
                        resetMonth = 1;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 1, 2);
                        resetMonth = 1;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        if (setWeek(5, 1, 2) == false) {
                            setWeek(4, 1, 2);
                            resetMonth = 1;
                            resetYear = 2;

                        } else {
                            resetMonth = 1;
                            resetYear = 2;

                        }
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 0, 1) == false) {
                            setWeek(5, 0, 1);
                            resetMonth = 0;
                            resetYear = 1;

                        } else {
                            resetMonth = 0;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 1, 1) == false) {
                            setWeek(6, 0, 1);
                            resetMonth = 0;
                            resetYear = 1;

                        } else {
                            resetMonth = 1;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        if (setWeek(2, 1, 1) == false) {
                            setWeek(1, 1, 1);
                            resetMonth = 1;
                            resetYear = 1;

                        } else {
                            resetMonth = 1;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 1, 1);
                        resetMonth = 1;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 1, 1);
                        resetMonth = 1;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        if (setWeek(5, 1, 1) == false) {
                            setWeek(4, 1, 1);
                            resetMonth = 1;
                            resetYear = 1;

                        } else {
                            resetMonth = 1;
                            resetYear = 1;

                        }
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 0, 3) == false) {
                            setWeek(5, 0, 3);
                            resetMonth = 0;
                            resetYear = 3;

                        } else {
                            resetMonth = 0;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 1, 3) == false) {
                            setWeek(6, 0, 3);
                            resetMonth = 0;
                            resetYear = 3;

                        } else {
                            resetMonth = 1;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        if (setWeek(2, 1, 3) == false) {
                            setWeek(1, 1, 3);
                            resetMonth = 1;
                            resetYear = 3;

                        } else {
                            resetMonth = 1;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 1, 3);
                        resetMonth = 1;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 1, 3);
                        resetMonth = 1;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        if (setWeek(5, 1, 3) == false) {
                            setWeek(4, 1, 3);
                            resetMonth = 1;
                            resetYear = 3;

                        } else {
                            resetMonth = 1;
                            resetYear = 3;

                        }
                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("march")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 1, 2) == false) {
                            setWeek(5, 1, 2);
                            resetMonth = 1;
                            resetYear = 2;

                        } else {
                            resetMonth = 1;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 2, 2) == false) {
                            setWeek(6, 1, 2);
                            resetMonth = 1;
                            resetYear = 2;

                        } else {
                            resetMonth = 2;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 1, 1) == false) {
                            setWeek(5, 1, 1);
                            resetMonth = 1;
                            resetYear = 1;

                        } else {
                            resetMonth = 1;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 2, 1) == false) {
                            setWeek(6, 1, 1);
                            resetMonth = 1;
                            resetYear = 1;

                        } else {
                            resetMonth = 2;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 1, 3) == false) {
                            setWeek(5, 1, 3);
                            resetMonth = 1;
                            resetYear = 3;

                        } else {
                            resetMonth = 1;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 2, 3) == false) {
                            setWeek(6, 1, 3);
                            resetMonth = 1;
                            resetYear = 3;

                        } else {
                            resetMonth = 2;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("april")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 2, 2) == false) {
                            setWeek(5, 2, 2);
                            resetMonth = 2;
                            resetYear = 2;

                        } else {
                            resetMonth = 2;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 3, 2) == false) {
                            setWeek(6, 2, 2);
                            resetMonth = 2;
                            resetYear = 2;

                        } else {
                            resetMonth = 3;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 3, 2);
                        resetMonth = 3;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 3, 2);
                        resetMonth = 3;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 3, 2);
                        resetMonth = 3;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 3, 2);
                        resetMonth = 3;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 2, 1) == false) {
                            setWeek(5, 2, 1);
                            resetMonth = 2;
                            resetYear = 1;

                        } else {
                            resetMonth = 2;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 3, 1) == false) {
                            setWeek(6, 2, 1);
                            resetMonth = 2;
                            resetYear = 1;

                        } else {
                            resetMonth = 3;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 3, 1);
                        resetMonth = 3;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 3, 1);
                        resetMonth = 3;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 3, 1);
                        resetMonth = 3;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 3, 1);
                        resetMonth = 3;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 2, 3) == false) {
                            setWeek(5, 2, 3);
                            resetMonth = 2;
                            resetYear = 3;

                        } else {
                            resetMonth = 2;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 3, 3) == false) {
                            setWeek(6, 2, 3);
                            resetMonth = 2;
                            resetYear = 3;

                        } else {
                            resetMonth = 3;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 3, 3);
                        resetMonth = 3;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 3, 3);
                        resetMonth = 3;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 3, 3);
                        resetMonth = 3;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 3, 3);
                        resetMonth = 3;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("may")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 3, 2) == false) {
                            setWeek(5, 3, 2);
                            resetMonth = 3;
                            resetYear = 2;

                        } else {
                            resetMonth = 3;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 4, 2) == false) {
                            setWeek(6, 3, 2);
                            resetMonth = 3;
                            resetYear = 2;

                        } else {
                            resetMonth = 4;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 4, 2);
                        resetMonth = 4;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 4, 2);
                        resetMonth = 4;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 4, 2);
                        resetMonth = 4;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 4, 2);
                        resetMonth = 4;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 3, 1) == false) {
                            setWeek(5, 3, 1);
                            resetMonth = 3;
                            resetYear = 1;

                        } else {
                            resetMonth = 3;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 4, 1) == false) {
                            setWeek(6, 3, 1);
                            resetMonth = 3;
                            resetYear = 1;

                        } else {
                            resetMonth = 4;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 4, 1);
                        resetMonth = 4;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 4, 1);
                        resetMonth = 4;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 4, 1);
                        resetMonth = 4;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 4, 1);
                        resetMonth = 4;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 3, 3) == false) {
                            setWeek(5, 3, 3);
                            resetMonth = 3;
                            resetYear = 3;

                        } else {
                            resetMonth = 3;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 4, 3) == false) {
                            setWeek(6, 3, 3);
                            resetMonth = 3;
                            resetYear = 3;

                        } else {
                            resetMonth = 4;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("june")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 4, 2) == false) {
                            setWeek(5, 4, 2);
                            resetMonth = 4;
                            resetYear = 2;

                        } else {
                            resetMonth = 4;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 5, 2) == false) {
                            setWeek(6, 4, 2);
                            resetMonth = 4;
                            resetYear = 2;

                        } else {
                            resetMonth = 5;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 5, 2);
                        resetMonth = 5;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 5, 2);
                        resetMonth = 5;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 5, 2);
                        resetMonth = 5;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 5, 2);
                        resetMonth = 5;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 4, 1) == false) {
                            setWeek(5, 4, 1);
                            resetMonth = 4;
                            resetYear = 1;

                        } else {
                            resetMonth = 4;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 5, 1) == false) {
                            setWeek(6, 4, 1);
                            resetMonth = 4;
                            resetYear = 1;

                        } else {
                            resetMonth = 5;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 5, 1);
                        resetMonth = 5;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 5, 1);
                        resetMonth = 5;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 5, 1);
                        resetMonth = 5;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 5, 1);
                        resetMonth = 5;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 4, 3) == false) {
                            setWeek(5, 4, 3);
                            resetMonth = 4;
                            resetYear = 3;

                        } else {
                            resetMonth = 4;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 5, 3) == false) {
                            setWeek(6, 4, 3);
                            resetMonth = 4;
                            resetYear = 3;

                        } else {
                            resetMonth = 5;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 5, 3);
                        resetMonth = 5;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 5, 3);
                        resetMonth = 5;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 5, 3);
                        resetMonth = 5;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("july")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 5, 2) == false) {
                            setWeek(5, 5, 2);
                            resetMonth = 5;
                            resetYear = 2;

                        } else {
                            resetMonth = 5;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 6, 2) == false) {
                            setWeek(6, 5, 2);
                            resetMonth = 5;
                            resetYear = 2;

                        } else {
                            resetMonth = 6;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 6, 2);
                        resetMonth = 6;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 6, 2);
                        resetMonth = 6;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 6, 2);
                        resetMonth = 6;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 6, 2);
                        resetMonth = 6;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 5, 1) == false) {
                            setWeek(5, 5, 1);
                            resetMonth = 5;
                            resetYear = 1;

                        } else {
                            resetMonth = 5;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 6, 1) == false) {
                            setWeek(6, 5, 1);
                            resetMonth = 5;
                            resetYear = 1;

                        } else {
                            resetMonth = 6;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 6, 1);
                        resetMonth = 6;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 6, 1);
                        resetMonth = 6;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 6, 1);
                        resetMonth = 6;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 6, 1);
                        resetMonth = 6;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 5, 3) == false) {
                            setWeek(5, 5, 3);
                            resetMonth = 5;
                            resetYear = 3;

                        } else {
                            resetMonth = 5;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 6, 3) == false) {
                            setWeek(6, 5, 3);
                            resetMonth = 5;
                            resetYear = 3;

                        } else {
                            resetMonth = 6;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 6, 3);
                        resetMonth = 6;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 6, 3);
                        resetMonth = 6;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 6, 3);
                        resetMonth = 6;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 6, 3);
                        resetMonth = 6;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("august")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 6, 2) == false) {
                            setWeek(5, 6, 2);
                            resetMonth = 6;
                            resetYear = 2;

                        } else {
                            resetMonth = 6;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 7, 2) == false) {
                            setWeek(6, 6, 2);
                            resetMonth = 6;
                            resetYear = 2;

                        } else {
                            resetMonth = 7;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 7, 2);
                        resetMonth = 7;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 7, 2);
                        resetMonth = 7;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 7, 2);
                        resetMonth = 7;
                        resetYear = 4;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 7, 2);
                        resetMonth = 7;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 6, 1) == false) {
                            setWeek(5, 6, 1);
                            resetMonth = 6;
                            resetYear = 1;

                        } else {
                            resetMonth = 6;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 7, 1) == false) {
                            setWeek(6, 6, 1);
                            resetMonth = 6;
                            resetYear = 1;

                        } else {
                            resetMonth = 7;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 7, 1);
                        resetMonth = 7;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 7, 1);
                        resetMonth = 7;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 7, 1);
                        resetMonth = 7;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 7, 1);
                        resetMonth = 7;
                        resetYear = 1;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 6, 3) == false) {
                            setWeek(5, 6, 3);
                            resetMonth = 6;
                            resetYear = 3;

                        } else {
                            resetMonth = 6;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 7, 3) == false) {
                            setWeek(6, 6, 3);
                            resetMonth = 6;
                            resetYear = 3;

                        } else {
                            resetMonth = 7;
                            resetYear = 3;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 7, 3);
                        resetMonth = 7;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 7, 3);
                        resetMonth = 7;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 7, 3);
                        resetMonth = 7;
                        resetYear = 3;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 7, 3);
                        resetMonth = 7;
                        resetYear = 3;

                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("september")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 7, 2) == false) {
                            setWeek(5, 7, 2);
                            resetMonth = 7;
                            resetYear = 2;

                        } else {
                            resetMonth = 7;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 8, 2) == false) {
                            setWeek(6, 7, 2);
                            resetMonth = 7;
                            resetYear = 2;

                        } else {
                            resetMonth = 8;
                            resetYear = 2;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 8, 2);
                        resetMonth = 8;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 8, 2);
                        resetMonth = 8;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 8, 2);
                        resetMonth = 8;
                        resetYear = 2;

                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 8, 2);
                        resetMonth = 8;
                        resetYear = 2;

                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 7, 1) == false) {
                            setWeek(5, 7, 1);
                            resetMonth = 7;
                            resetYear = 1;

                        } else {
                            resetMonth = 7;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 8, 1) == false) {
                            setWeek(6, 7, 1);
                            resetMonth = 7;
                            resetYear = 1;

                        } else {
                            resetMonth = 8;
                            resetYear = 1;

                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 8, 1);
                        resetMonth = 8;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 8, 1);
                        resetMonth = 8;
                        resetYear = 1;

                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 8, 1);
                        resetMonth = 8;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 8, 1);
                        resetMonth = 8;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 7, 3) == false) {
                            setWeek(5, 7, 3);
                            resetMonth = 7;
                            resetYear = 3;
                        } else {
                            resetMonth = 7;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 8, 3) == false) {
                            setWeek(6, 7, 3);
                            resetMonth = 7;
                            resetYear = 3;
                        } else {
                            resetMonth = 8;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 8, 3);
                        resetMonth = 8;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 8, 3);
                        resetMonth = 8;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 8, 3);
                        resetMonth = 8;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 8, 3);
                        resetMonth = 8;
                        resetYear = 3;
                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("october")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 8, 2) == false) {
                            setWeek(5, 8, 2);
                            resetMonth = 8;
                            resetYear = 2;
                        } else {
                            resetMonth = 8;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 9, 2) == false) {
                            setWeek(6, 8, 2);
                            resetMonth = 8;
                            resetYear = 2;
                        } else {
                            resetMonth = 9;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 9, 2);
                        resetMonth = 9;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 9, 2);
                        resetMonth = 9;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 9, 2);
                        resetMonth = 9;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 9, 2);
                        resetMonth = 9;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 8, 1) == false) {
                            setWeek(5, 8, 1);
                            resetMonth = 8;
                            resetYear = 1;
                        } else {
                            resetMonth = 8;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 9, 1) == false) {
                            setWeek(6, 8, 1);
                            resetMonth = 8;
                            resetYear = 1;
                        } else {
                            resetMonth = 9;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 9, 1);
                        resetMonth = 9;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 9, 1);
                        resetMonth = 9;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 9, 1);
                        resetMonth = 9;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 9, 1);
                        resetMonth = 9;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 8, 3) == false) {
                            setWeek(5, 8, 3);
                            resetMonth = 8;
                            resetYear = 3;
                        } else {
                            resetMonth = 8;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 9, 3) == false) {
                            setWeek(6, 8, 3);
                            resetMonth = 8;
                            resetYear = 3;
                        } else {
                            resetMonth = 9;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 9, 3);
                        resetMonth = 9;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 9, 3);
                        resetMonth = 9;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 9, 3);
                        resetMonth = 9;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 9, 3);
                        resetMonth = 9;
                        resetYear = 3;
                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("november")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 9, 2) == false) {
                            setWeek(5, 9, 2);
                            resetMonth = 9;
                            resetYear = 2;
                        } else {
                            resetMonth = 9;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 10, 2) == false) {
                            setWeek(6, 9, 2);
                            resetMonth = 9;
                            resetYear = 2;
                        } else {
                            resetMonth = 10;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 10, 2);
                        resetMonth = 10;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 10, 2);
                        resetMonth = 10;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 10, 2);
                        resetMonth = 10;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 10, 2);
                        resetMonth = 10;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 9, 1) == false) {
                            setWeek(5, 9, 1);
                            resetMonth = 9;
                            resetYear = 1;
                        } else {
                            resetMonth = 9;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 10, 1) == false) {
                            setWeek(6, 9, 1);
                            resetMonth = 9;
                            resetYear = 1;
                        } else {
                            resetMonth = 10;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 10, 1);
                        resetMonth = 10;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 10, 1);
                        resetMonth = 10;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 10, 1);
                        resetMonth = 10;
                        resetYear = 10;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 10, 1);
                        resetMonth = 10;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 9, 3) == false) {
                            setWeek(5, 9, 3);
                            resetMonth = 9;
                            resetYear = 3;
                        } else {
                            resetMonth = 9;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 10, 3) == false) {
                            setWeek(6, 9, 3);
                            resetMonth = 9;
                            resetYear = 3;
                        } else {
                            resetMonth = 10;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 10, 3);
                        resetMonth = 10;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 10, 3);
                        resetMonth = 10;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 10, 3);
                        resetMonth = 10;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 10, 3);
                        resetMonth = 10;
                        resetYear = 3;
                    }
                }
            } else if (getTitle().toString().toLowerCase().contains("december")) {
                if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 10, 2) == false) {
                            setWeek(5, 10, 2);
                            resetMonth = 10;
                            resetYear = 2;
                        } else {
                            resetMonth = 10;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 11, 2) == false) {
                            setWeek(6, 10, 2);
                            resetMonth = 10;
                            resetYear = 2;
                        } else {
                            resetMonth = 11;
                            resetYear = 2;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 11, 2);
                        resetMonth = 11;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 11, 2);
                        resetMonth = 11;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 11, 2);
                        resetMonth = 11;
                        resetYear = 2;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 11, 2);
                        resetMonth = 11;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 10, 1) == false) {
                            setWeek(5, 10, 1);
                            resetMonth = 10;
                            resetYear = 1;
                        } else {
                            resetMonth = 10;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 11, 1) == false) {
                            setWeek(6, 10, 1);
                            resetMonth = 10;
                            resetYear = 1;
                        } else {
                            resetMonth = 11;
                            resetYear = 1;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 11, 1);
                        resetMonth = 11;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 11, 1);
                        resetMonth = 11;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 11, 1);
                        resetMonth = 11;
                        resetYear = 1;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 11, 1);
                        resetMonth = 11;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                    if (getTitle().toString().toLowerCase().contains("week 1")) {
                        if (setWeek(6, 10, 3) == false) {
                            setWeek(5, 10, 3);
                            resetMonth = 10;
                            resetYear = 3;
                        } else {
                            resetMonth = 10;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                        if (setWeek(1, 11, 3) == false) {
                            setWeek(6, 10, 3);
                            resetMonth = 10;
                            resetYear = 3;
                        } else {
                            resetMonth = 11;
                            resetYear = 3;
                        }
                    } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                        setWeek(2, 11, 3);
                        resetMonth = 11;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                        setWeek(3, 11, 3);
                        resetMonth = 11;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                        setWeek(4, 11, 3);
                        resetMonth = 11;
                        resetYear = 3;
                    } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                        setWeek(5, 11, 3);
                        resetMonth = 11;
                        resetYear = 3;
                    }
                }
            }
        }
    }

    public void swipeLeft() {
        int resetWeek2 = resetWeek;
        if (setWeek(6, date.getMonth(), 3) == true) {
            setWeek(resetWeek2, resetMonth, resetYear);
            if (!(getTitle().toString().toLowerCase().contains("week 6") & getTitle().toString().toLowerCase().contains(actualMonth.toLowerCase()) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        } else if (setWeek(5, date.getMonth(), 3) == true) {
            setWeek(resetWeek2, resetMonth, resetYear);
            if (!(getTitle().toString().toLowerCase().contains("week 5") & getTitle().toString().toLowerCase().contains(actualMonth.toLowerCase()) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        } else {
            setWeek(resetWeek2, resetMonth, resetYear);
            if (!(getTitle().toString().toLowerCase().contains("week 4") & getTitle().toString().toLowerCase().contains(actualMonth.toLowerCase()) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        }
    }

    public void controlSwipeLeft() {
        if (getTitle().toString().toLowerCase().contains("january")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 0, 2) == false) {
                        setWeek(1, 1, 2);
                        resetMonth = 1;
                        resetYear = 2;
                    } else {
                        resetMonth = 0;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 0, 1);
                    resetMonth = 0;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 0, 1);
                    resetMonth = 0;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 0, 1);
                    resetMonth = 0;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 0, 1);
                    resetMonth = 0;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 0, 1) == false) {
                        setWeek(1, 1, 1);
                        resetMonth = 1;
                        resetYear = 1;
                    } else {
                        resetMonth = 0;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 0, 3) == false) {
                        setWeek(1, 1, 3);
                        resetMonth = 1;
                        resetYear = 3;
                    } else {
                        resetMonth = 0;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 1, 3);
                    resetMonth = 0;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("february")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 1, 2);
                    resetMonth = 1;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    if (setWeek(5, 1, 2) == false) {
                        setWeek(1, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;
                    } else {
                        resetMonth = 1;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 1, 2) == false) {
                        setWeek(1, 2, 2);
                        resetMonth = 2;
                        resetYear = 2;
                    } else {
                        resetMonth = 1;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 1, 1);
                    resetMonth = 1;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    if (setWeek(5, 1, 1) == false) {
                        setWeek(1, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;
                    } else {
                        resetMonth = 1;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 1, 1) == false) {
                        setWeek(1, 2, 1);
                        resetMonth = 2;
                        resetYear = 1;
                    } else {
                        resetMonth = 1;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 1, 3);
                    resetMonth = 1;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 1, 3);
                    resetMonth = 1;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 1, 3);
                    resetMonth = 1;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    if (setWeek(5, 1, 3) == false) {
                        setWeek(1, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;
                    } else {
                        resetMonth = 1;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 1, 3) == false) {
                        setWeek(1, 2, 3);
                        resetMonth = 2;
                        resetYear = 3;
                    } else {
                        resetMonth = 1;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("march")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 2, 2);
                    resetMonth = 2;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 2, 2) == false) {
                        setWeek(1, 3, 2);
                        resetMonth = 3;
                        resetYear = 2;
                    } else {
                        resetMonth = 2;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 2, 1);
                    resetMonth = 2;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 2, 1) == false) {
                        setWeek(1, 3, 1);
                        resetMonth = 3;
                        resetYear = 1;
                    } else {
                        resetMonth = 2;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 2, 3);
                    resetMonth = 2;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 2, 3) == false) {
                        setWeek(1, 3, 3);
                        resetMonth = 3;
                        resetYear = 3;
                    } else {
                        resetMonth = 2;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("april")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 3, 2);
                    resetMonth = 3;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 3, 2) == false) {
                        setWeek(1, 4, 2);
                        resetMonth = 4;
                        resetYear = 2;
                    } else {
                        resetMonth = 3;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 3, 1);
                    resetMonth = 3;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 3, 1) == false) {
                        setWeek(1, 4, 1);
                        resetMonth = 4;
                        resetYear = 1;
                    } else {
                        resetMonth = 3;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 3, 3);
                    resetMonth = 3;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 3, 3) == false) {
                        setWeek(1, 4, 3);
                        resetMonth = 4;
                        resetYear = 3;
                    } else {
                        resetMonth = 3;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("may")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 4, 2);
                    resetMonth = 4;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 4, 2) == false) {
                        setWeek(1, 5, 2);
                        resetMonth = 5;
                        resetYear = 2;
                    } else {
                        resetMonth = 4;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 4, 1);
                    resetMonth = 4;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 4, 1) == false) {
                        setWeek(1, 5, 1);
                        resetMonth = 5;
                        resetYear = 1;
                    } else {
                        resetMonth = 4;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 4, 3);
                    resetMonth = 4;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 4, 3) == false) {
                        setWeek(1, 5, 3);
                        resetMonth = 5;
                        resetYear = 3;
                    } else {
                        resetMonth = 4;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("june")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 5, 2);
                    resetMonth = 5;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 5, 2) == false) {
                        setWeek(1, 6, 2);
                        resetMonth = 6;
                        resetYear = 2;
                    } else {
                        resetMonth = 5;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 5, 1);
                    resetMonth = 5;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 5, 1) == false) {
                        setWeek(1, 6, 1);
                        resetMonth = 6;
                        resetYear = 1;
                    } else {
                        resetMonth = 5;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 5, 3);
                    resetMonth = 5;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 5, 3) == false) {
                        setWeek(1, 6, 3);
                        resetMonth = 6;
                        resetYear = 3;
                    } else {
                        resetMonth = 5;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("july")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 6, 2);
                    resetMonth = 6;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 6, 2) == false) {
                        setWeek(1, 7, 2);
                        resetMonth = 7;
                        resetYear = 2;
                    } else {
                        resetMonth = 6;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 6, 1);
                    resetMonth = 6;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 6, 1) == false) {
                        setWeek(1, 7, 1);
                        resetMonth = 7;
                        resetYear = 1;
                    } else {
                        resetMonth = 6;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 6, 3);
                    resetMonth = 6;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 6, 3) == false) {
                        setWeek(1, 7, 3);
                        resetMonth = 7;
                        resetYear = 3;
                    } else {
                        resetMonth = 6;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("august")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 7, 2);
                    resetMonth = 7;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 7, 2) == false) {
                        setWeek(1, 8, 2);
                        resetMonth = 8;
                        resetYear = 2;
                    } else {
                        resetMonth = 7;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 7, 1);
                    resetMonth = 7;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 7, 1) == false) {
                        setWeek(1, 8, 1);
                        resetMonth = 8;
                        resetYear = 1;
                    } else {
                        resetMonth = 7;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 7, 3);
                    resetMonth = 7;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 7, 3) == false) {
                        setWeek(1, 8, 3);
                        resetMonth = 8;
                        resetYear = 3;
                    } else {
                        resetMonth = 7;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("september")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 8, 2);
                    resetMonth = 8;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 8, 2) == false) {
                        setWeek(1, 9, 2);
                        resetMonth = 9;
                        resetYear = 2;
                    } else {
                        resetMonth = 8;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 8, 1);
                    resetMonth = 8;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 8, 1) == false) {
                        setWeek(1, 9, 1);
                        resetMonth = 9;
                        resetYear = 1;
                    } else {
                        resetMonth = 8;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 8, 3);
                    resetMonth = 8;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 8, 3) == false) {
                        setWeek(1, 9, 3);
                        resetMonth = 9;
                        resetYear = 3;
                    } else {
                        resetMonth = 8;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("october")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 9, 2);
                    resetMonth = 9;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 9, 2) == false) {
                        setWeek(1, 10, 2);
                        resetMonth = 10;
                        resetYear = 2;
                    } else {
                        resetMonth = 9;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 9, 1);
                    resetMonth = 9;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 9, 1) == false) {
                        setWeek(1, 10, 1);
                        resetMonth = 10;
                        resetYear = 1;
                    } else {
                        resetMonth = 9;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 9, 3);
                    resetMonth = 9;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 9, 3) == false) {
                        setWeek(1, 10, 3);
                        resetMonth = 10;
                        resetYear = 3;
                    } else {
                        resetMonth = 9;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("november")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 10, 2);
                    resetMonth = 10;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 10, 2) == false) {
                        setWeek(1, 11, 2);
                        resetMonth = 11;
                        resetYear = 2;
                    } else {
                        resetMonth = 10;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 10, 1);
                    resetMonth = 10;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 10, 1) == false) {
                        setWeek(1, 11, 1);
                        resetMonth = 11;
                        resetYear = 1;
                    } else {
                        resetMonth = 10;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 10, 3);
                    resetMonth = 10;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 10, 3) == false) {
                        setWeek(1, 11, 3);
                        resetMonth = 11;
                        resetYear = 3;
                    } else {
                        resetMonth = 10;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                }
            }
        } else if (getTitle().toString().toLowerCase().contains("december")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 11, 2);
                    resetMonth = 11;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 11, 2) == false) {
                        setWeek(1, 0, 3);
                        resetMonth = 0;
                        resetYear = 3;
                    } else {
                        resetMonth = 11;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 0, 3);
                    resetMonth = 0;
                    resetYear = 3;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 11, 1);
                    resetMonth = 11;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 11, 1) == false) {
                        setWeek(1, 0, 2);
                        resetMonth = 0;
                        resetYear = 2;
                    } else {
                        resetMonth = 11;
                        resetYear = 1;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1, 0, 2);
                    resetMonth = 0;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2, 11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3, 11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4, 11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5, 11, 3);
                    resetMonth = 11;
                    resetYear = 3;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6, 11, 3) == false) {
                        resetMonth = 11;
                        resetYear = 3;
                    } else {
                        resetMonth = 11;
                        resetYear = 3;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    resetMonth = 11;
                    resetYear = 3;
                }
            }
        }
    }

    public void initFloatButton() {
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatweek);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateToDoActivity.class);
                intent.putExtra("Activity", "week");
                startActivity(intent);
            }
        });
    }

    public void getTodosForThisButton(String dateOfLayout, LinearLayout layout) {

        List<Todo> todos;

        View divider1 = layout.findViewById(R.id.divider_1);
        View divider2 = layout.findViewById(R.id.divider_2);
        View divider3 = layout.findViewById(R.id.divider_3);

        LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
        LinearLayout layout2 = (LinearLayout) layout.getChildAt(3);
        LinearLayout layout3 = (LinearLayout) layout.getChildAt(5);
        LinearLayout layout4 = (LinearLayout) layout.getChildAt(7);

        FontAwesome icon1 = (FontAwesome) layout1.getChildAt(0);
        FontAwesome icon2 = (FontAwesome) layout2.getChildAt(0);
        FontAwesome icon3 = (FontAwesome) layout3.getChildAt(0);
        FontAwesome icon4 = (FontAwesome) layout4.getChildAt(0);

        TextView prio1 = (TextView) layout1.getChildAt(1);
        TextView prio2 = (TextView) layout2.getChildAt(1);
        TextView prio3 = (TextView) layout3.getChildAt(1);
        TextView prio4 = (TextView) layout4.getChildAt(1);

        ThreadGetTodayTodosLimit4 getTodayTodos = new ThreadGetTodayTodosLimit4(dateOfLayout, this);

        try {
            getTodayTodos.start();
            getTodayTodos.join();
        } catch (Exception e) {

        }
        todos = getTodayTodos.getAll();

        if (todos.size() > 0) {
            icon1.setText(getStringIdentifier(this, todos.get(0).getTheme()));
            switch (todos.get(0).getPriority()) {
                case 1:
                    prio1.setBackgroundColor(getResources().getColor(R.color.priority1));
                    break;
                case 2:
                    prio1.setBackgroundColor(getResources().getColor(R.color.priority2));
                    break;
                case 3:
                    prio1.setBackgroundColor(getResources().getColor(R.color.priority3));
                    break;
                case 4:
                    prio1.setBackgroundColor(getResources().getColor(R.color.priority4));
                    break;
                case 5:
                    prio1.setBackgroundColor(getResources().getColor(R.color.priority5));
                    break;
            }
        }
        if (todos.size() > 1) {
            divider1.setBackgroundColor(getResources().getColor(R.color.classic_dark));
            icon2.setText(getStringIdentifier(this, todos.get(1).getTheme()));
            switch (todos.get(1).getPriority()) {
                case 1:
                    prio2.setBackgroundColor(getResources().getColor(R.color.priority1));
                    break;
                case 2:
                    prio2.setBackgroundColor(getResources().getColor(R.color.priority2));
                    break;
                case 3:
                    prio2.setBackgroundColor(getResources().getColor(R.color.priority3));
                    break;
                case 4:
                    prio2.setBackgroundColor(getResources().getColor(R.color.priority4));
                    break;
                case 5:
                    prio2.setBackgroundColor(getResources().getColor(R.color.priority5));
                    break;
            }
        }

        if (todos.size() > 2) {
            divider2.setBackgroundColor(getResources().getColor(R.color.classic_dark));
            icon3.setText(getStringIdentifier(this, todos.get(2).getTheme()));
            switch (todos.get(2).getPriority()) {
                case 1:
                    prio3.setBackgroundColor(getResources().getColor(R.color.priority1));
                    break;
                case 2:
                    prio3.setBackgroundColor(getResources().getColor(R.color.priority2));
                    break;
                case 3:
                    prio3.setBackgroundColor(getResources().getColor(R.color.priority3));
                    break;
                case 4:
                    prio3.setBackgroundColor(getResources().getColor(R.color.priority4));
                    break;
                case 5:
                    prio3.setBackgroundColor(getResources().getColor(R.color.priority5));
                    break;
            }
        }
        if (todos.size() > 3) {
            divider3.setBackgroundColor(getResources().getColor(R.color.classic_dark));
            icon4.setText(getStringIdentifier(this, todos.get(3).getTheme()));
            switch (todos.get(3).getPriority()) {
                case 1:
                    prio4.setBackgroundColor(getResources().getColor(R.color.priority1));
                    break;
                case 2:
                    prio4.setBackgroundColor(getResources().getColor(R.color.priority2));
                    break;
                case 3:
                    prio4.setBackgroundColor(getResources().getColor(R.color.priority3));
                    break;
                case 4:
                    prio4.setBackgroundColor(getResources().getColor(R.color.priority4));
                    break;
                case 5:
                    prio4.setBackgroundColor(getResources().getColor(R.color.priority5));
                    break;
            }
        }
        dialog.dismiss();

    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    public void initGetTodosForThisButtons(LinearLayout button, Date a) {
        dialog = ProgressDialog.show(this, "", "Loading...", true);
        String dateOf;
        String year = Integer.toString(a.getYear() + 1900);
        String month = Integer.toString(a.getMonth() + 1);
        String day = Integer.toString(a.getDate());
        if (month.length() < 2) {
            month = "0" + month;
        }
        if (day.length() < 2) {
            day = "0" + day;
        }
        dateOf = year + "-" + month + "-" + day + " 00:00:00.000";
        getTodosForThisButton(dateOf, button);
    }

    public void deleteOldTodo(LinearLayout layout) {
        LinearLayout layout1 = (LinearLayout) layout.getChildAt(1);
        LinearLayout layout2 = (LinearLayout) layout.getChildAt(3);
        LinearLayout layout3 = (LinearLayout) layout.getChildAt(5);
        LinearLayout layout4 = (LinearLayout) layout.getChildAt(7);

        FontAwesome icon1 = (FontAwesome) layout1.getChildAt(0);
        FontAwesome icon2 = (FontAwesome) layout2.getChildAt(0);
        FontAwesome icon3 = (FontAwesome) layout3.getChildAt(0);
        FontAwesome icon4 = (FontAwesome) layout4.getChildAt(0);

        TextView prio1 = (TextView) layout1.getChildAt(1);
        TextView prio2 = (TextView) layout2.getChildAt(1);
        TextView prio3 = (TextView) layout3.getChildAt(1);
        TextView prio4 = (TextView) layout4.getChildAt(1);

        View divider1 = layout.findViewById(R.id.divider_1);
        View divider2 = layout.findViewById(R.id.divider_2);
        View divider3 = layout.findViewById(R.id.divider_3);

        divider1.setBackgroundColor(getResources().getColor(R.color.classic));
        divider2.setBackgroundColor(getResources().getColor(R.color.classic));
        divider3.setBackgroundColor(getResources().getColor(R.color.classic));

        icon1.setText("");
        icon2.setText("");
        icon3.setText("");
        icon4.setText("");

        prio1.setBackgroundColor(getResources().getColor(R.color.classic));
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if(viewGestureisOn instanceof LinearLayout){
            TextView textview = (TextView)(((LinearLayout) viewGestureisOn).getChildAt(0));
            String[] trim = textview.getText().toString().split(" ");
            String[] trim2 = getTitle().toString().split(" ");
            int day = Integer.parseInt(trim[1]);
            int year = 0;
            if(Integer.parseInt(trim2[3]) == date.getYear() + 1900){
                year = 2;
            }
            else if(Integer.parseInt(trim2[3]) == date.getYear() + 1899){
                year = 1;
            }
            else if(Integer.parseInt(trim2[3]) == date.getYear() + 1901){
                year = 3;
            }
            int month = 0;
            if(trim2[2].equals("January")){
                month = 0;
            }
            else if(trim2[2].equals("February")){
                month = 1;
            }
            else if(trim2[2].equals("March")){
                month = 2;
            }
            else if(trim2[2].equals("April")){
                month = 3;
            }
            else if(trim2[2].equals("May")){
                month = 4;
            }
            else if(trim2[2].equals("June")){
                month = 5;
            }
            else if(trim2[2].equals("July")){
                month = 6;
            }
            else if(trim2[2].equals("August")){
                month = 7;
            }
            else if(trim2[2].equals("September")){
                month = 8;
            }
            else if(trim2[2].equals("October")){
                month = 9;
            }
            else if(trim2[2].equals("November")){
                month = 10;
            }
            else if(trim2[2].equals("December")){
                month = 11;
            }
            Intent intentset = new Intent(WeekActivity.this, DayActivity.class);
            intentset.putExtra("Year", year);
            intentset.putExtra("Month", month);
            intentset.putExtra("Week", resetWeek);
            intentset.putExtra("Day", day);
            startActivity(intentset);
            return true;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            // right to left swipe
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE & Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeLeft();
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE & Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                swipeRight();
            }
            else if(velocityY > this.SWIPE_MIN_DISTANCEUP & Math.abs(e1.getY() - e2.getY()) > this.SWIPE_MIN_DISTANCEUP){
                if(e1.getY() > e2.getY()){
                    Intent intentset = new Intent(WeekActivity.this, DayActivity.class);
                    intentset.putExtra("Year", resetYear);
                    intentset.putExtra("Month", resetMonth);
                    intentset.putExtra("Week", resetWeek);
                    intentset.putExtra("Day", resetDay);
                    startActivity(intentset);
                }
                else{
                    Intent intentset = new Intent(WeekActivity.this, MainActivity.class);
                    intentset.putExtra("Year", resetYear);
                    intentset.putExtra("Month", resetMonth);
                    intentset.putExtra("Week", resetWeek);
                    intentset.putExtra("Day", resetDay);
                    startActivity(intentset);
                }
            }
        } catch (Exception e) {

        }
        return true;
    }
}