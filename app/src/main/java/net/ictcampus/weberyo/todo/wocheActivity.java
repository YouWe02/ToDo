package net.ictcampus.weberyo.todo;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class wocheActivity extends AppCompatActivity {
    private List<Button> allWeekButtons = new ArrayList<Button>();
    private int resetMonth;
    private int resetYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woche);
        Intent intent = getIntent();
        resetMonth = intent.getIntExtra("Month", 0);
        resetYear = intent.getIntExtra("Year", 2);
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche1));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche2));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche3));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche4));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche5));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche6));
        allWeekButtons.add((Button) findViewById(R.id.kalenderwoche7));

        for(Button a:allWeekButtons){
            a.setOnTouchListener(new OnSwipeTouchListener(wocheActivity.this) {
                public void onSwipeTop() {

                }

                public void onSwipeRight() {

                }

                public void onSwipeLeft() {

                }

                public void onSwipeBottom() {
                    Intent intent = new Intent(wocheActivity.this, MainActivity.class);
                    intent.putExtra("Year",resetYear);
                    intent.putExtra("Month", resetMonth);
                    startActivity(intent);
                    finish();
                    overridePendingTransition( R.anim.swipe_right_2, R.anim.swipe_right_1);
                }
            });
        }
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Constraint);
        layout.setOnTouchListener(new OnSwipeTouchListener(wocheActivity.this) {
            public void onSwipeTop() {

            }

            public void onSwipeRight() {

            }

            public void onSwipeLeft() {

            }

            public void onSwipeBottom() {
                Intent intent = new Intent(wocheActivity.this, MainActivity.class);
                intent.putExtra("Year",resetYear);
                intent.putExtra("Month", resetMonth);
                startActivity(intent);
                finish();
                overridePendingTransition( R.anim.swipe_right_2, R.anim.swipe_right_1);
            }
        });
    }

    public void setWeek(){

    }
}
