package net.ictcampus.weberyo.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class wocheActivity extends AppCompatActivity {
    public static Activity activity;
    private List<Button> allWeekButtons = new ArrayList<Button>();
    private int resetMonth;
    private int resetYear;
    private int resetWeek;
    private int resetDay;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
    private Date actualDate;
    private String actualDateFormatted;
    private String actualMonth;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woche);
        activity = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS, WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        final Intent intent = getIntent();
        initFloatButton();
        resetMonth = intent.getIntExtra("Month", 5);
        resetYear = intent.getIntExtra("Year", 2);
        resetWeek = intent.getIntExtra("Week", 1);
        resetDay = intent.getIntExtra("Day", 1);
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche1));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche2));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche3));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche4));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche5));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche6));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche7));
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
        setWeek(1, resetMonth, resetYear);

        for (Button a : allWeekButtons) {
            a.setOnTouchListener(new OnSwipeTouchListener(wocheActivity.this) {
                public void onSwipeTop() {
                    Intent intentset = new Intent(wocheActivity.this, Day_View_activity.class);
                    intentset.putExtra("Year", resetYear);
                    intentset.putExtra("Month", resetMonth);
                    intentset.putExtra("Week", resetWeek);
                    intentset.putExtra("Day", resetDay);
                    startActivity(intentset);
                }

                public void onSwipeRight() {
                    swipeRight();
                }

                public void onSwipeLeft() {
                    swipeLeft();
                }

                public void onSwipeBottom() {
                    Intent intent = new Intent(wocheActivity.this, MainActivity.class);
                    intent.putExtra("Year", resetYear);
                    intent.putExtra("Month", resetMonth);
                    intent.putExtra("Week", resetWeek);
                    intent.putExtra("Day", resetDay);
                    startActivity(intent);
                }
            });
        }
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Constraint);
        layout.setOnTouchListener(new OnSwipeTouchListener(wocheActivity.this) {
            public void onSwipeTop() {
                Intent intentset = new Intent(wocheActivity.this, Day_View_activity.class);
                intentset.putExtra("Year", resetYear);
                intentset.putExtra("Month", resetMonth);
                intentset.putExtra("Week", resetWeek);
                intentset.putExtra("Day", resetDay);
                startActivity(intentset);
            }

            public void onSwipeRight() {
                swipeRight();
            }

            public void onSwipeLeft() {
                swipeLeft();
            }

            public void onSwipeBottom() {
                Intent intent = new Intent(wocheActivity.this, MainActivity.class);
                intent.putExtra("Year", resetYear);
                intent.putExtra("Month", resetMonth);
                intent.putExtra("Week", resetWeek);
                intent.putExtra("Day", resetDay);
                startActivity(intent);
            }
        });
    }

    public boolean setWeek(int woche, int monat, int jahr) {
        for (Button b : allWeekButtons) {
            b.setVisibility(View.VISIBLE);
            b.setText("default");
        }
        boolean check = true;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
                            Button button = (Button) findViewById(R.id.kalenderwoche7);
                            String string = "SUN " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 1) {
                            Button button = (Button) findViewById(R.id.kalenderwoche1);
                            String string = "MON " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 2) {
                            Button button = (Button) findViewById(R.id.kalenderwoche2);
                            String string = "TUE " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 3) {
                            Button button = (Button) findViewById(R.id.kalenderwoche3);
                            String string = "WED " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 4) {
                            Button button = (Button) findViewById(R.id.kalenderwoche4);
                            String string = "THU " + a.getDate();
                            button.setText(string);
                        } else if (a.getDay() == 5) {
                            Button button = (Button) findViewById(R.id.kalenderwoche5);
                            String string = "FRI " + a.getDate();
                            button.setText(string);
                        } else {
                            Button button = (Button) findViewById(R.id.kalenderwoche6);
                            String string = "SAT " + a.getDate();
                            button.setText(string);
                        }
                    }
                    for (Button b : allWeekButtons) {
                        if (b.getText().toString().toLowerCase().contains("default")) {
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
        if (!(getTitle().toString().toLowerCase().contains("week 1") & getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + ""))) {
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
            if (!(getTitle().toString().toLowerCase().contains("week 6") & getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        } else if (setWeek(5, date.getMonth(), 3) == true) {
            setWeek(resetWeek2, resetMonth, resetYear);
            if (!(getTitle().toString().toLowerCase().contains("week 5") & getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        } else {
            setWeek(resetWeek2, resetMonth, resetYear);
            if (!(getTitle().toString().toLowerCase().contains("week 4") & getTitle().toString().toLowerCase().contains(actualMonth) & getTitle().toString().toLowerCase().contains(date.getYear() + 1901 + ""))) {
                controlSwipeLeft();
            }
        }
    }

    public void controlSwipeLeft() {
        if (getTitle().toString().toLowerCase().contains("january")) {
            if (getTitle().toString().toLowerCase().contains(date.getYear() + 1900 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2,0,2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3,0,2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 3")) {
                    setWeek(4,0,2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 4")) {
                    setWeek(5,0,2);
                    resetMonth = 0;
                    resetYear = 2;
                } else if (getTitle().toString().toLowerCase().contains("week 5")) {
                    if (setWeek(6,0,2) == false) {
                        setWeek(1,1,2);
                        resetMonth = 1;
                        resetYear = 2;
                    } else {
                        resetMonth = 0;
                        resetYear = 2;
                    }
                } else if (getTitle().toString().toLowerCase().contains("week 6")) {
                    setWeek(1,1,2);
                    resetMonth = 1;
                    resetYear = 2;
                }
            } else if (getTitle().toString().toLowerCase().contains(date.getYear() + 1899 + "")) {
                if (getTitle().toString().toLowerCase().contains("week 1")) {
                    setWeek(2,0,1);
                    resetMonth = 0;
                    resetYear = 1;
                } else if (getTitle().toString().toLowerCase().contains("week 2")) {
                    setWeek(3,0,1);
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
                    if (setWeek(6,0,1) == false) {
                        setWeek(1,1,1);
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
    public void initFloatButton(){
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatweek);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Create_Todo_Activity.class);
                intent.putExtra("Activity", "week");
                startActivity(intent);
            }
        });
    }
}

