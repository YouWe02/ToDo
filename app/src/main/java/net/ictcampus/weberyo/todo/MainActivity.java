package net.ictcampus.weberyo.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImportDates dates = new ImportDates();
        List<Date> allDates = dates.defineDateinterval();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        for(Date a:allDates){
            Log.i("TAAAAAAAAAAAAAAAAG", dateFormatter.format(a));
        }
    }
}
