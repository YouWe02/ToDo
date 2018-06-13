package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class ToDoDescribtion extends AppCompatActivity {
    private String title;
    private String description;
    private String date;
    private int priority;
    private String category;
    private boolean privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_describtion);

        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        date = intent.getStringExtra("Date");
        priority = intent.getIntExtra("Priority", 0);
        category = intent.getStringExtra("Category");
        privacy = intent.getBooleanExtra("Private", false);

        initToolbar();
        setDescription();
    }

    public void setDescription() {
        TextView describtionTextView = (TextView)findViewById(R.id.textViewDescribtion);
        TextView dateTextView = (TextView)findViewById(R.id.textViewDate);
        RadioButton priority1 = (RadioButton) findViewById(R.id.radiobutton_priority_1);
        RadioButton priority2 = (RadioButton) findViewById(R.id.radiobutton_priority_2);
        RadioButton priority3 = (RadioButton) findViewById(R.id.radiobutton_priority_3);
        RadioButton priority4 = (RadioButton) findViewById(R.id.radiobutton_priority_4);
        RadioButton priority5 = (RadioButton) findViewById(R.id.radiobutton_priority_5);
        TextView categoryTextView = (TextView) findViewById(R.id.textViewCategory);
        FontAwesome category2TextView = (FontAwesome) findViewById(R.id.textViewCategory2);
        CheckBox privateCheckBox = (CheckBox) findViewById(R.id.private_checkbox2);

        setTitle(title);
        describtionTextView.setText(description);

        String[] dateSplitting = date.split(" ");
        String dateTrimmed = dateSplitting[0];
        dateTextView.setText(dateTrimmed);

        if(priority == 1){
            priority1.setChecked(true);
        }
        else if(priority == 2){
            priority2.setChecked(true);
        }
        else if(priority == 3){
            priority3.setChecked(true);
        }
        else if(priority == 4){
            priority4.setChecked(true);
        }
        else if(priority == 5){
            priority5.setChecked(true);
        }
        String[] categoryName = category.split("_");
        String categoryTrimmed = categoryName[1];

        category2TextView.setText(getStringIdentifier(this,category));
        categoryTextView.setText(categoryTrimmed);

        if(privacy == true){
            privateCheckBox.setChecked(true);
        }
    }

    public void initToolbar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

}
