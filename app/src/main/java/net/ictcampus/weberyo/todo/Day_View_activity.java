package net.ictcampus.weberyo.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Day_View_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__view_activity);
        TextView view = (TextView) findViewById(R.id.dayview_date_header);
        view.setText("Hallo");
    }
}
