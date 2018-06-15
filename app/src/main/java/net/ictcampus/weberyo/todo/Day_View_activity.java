package net.ictcampus.weberyo.todo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_CreateTodo;
import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_DeleteTodo;
import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_GetToDoByTitle;
import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_GetTodayTodos;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class Day_View_activity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MIN_DISTANCEUP = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    public static Activity activity;
    private int resetYear;
    private int resetMonth;
    private int resetWeek;
    private int resetDay;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
    private Date actualDate;
    private String actualDateFormatted;
    private String actualMonth;
    private Date date;
    private static final String KEY_NAME = "hvibdsdv";
    private Cipher cipher;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private TextView textView;
    private FingerprintManager.CryptoObject cryptoObject;
    private FingerprintManager fingerprintManager;
    private FingerprintDialog dialog;
    private Todo todo;
    private GestureDetector mGestureDetector2;
    private int year;
    private int drag;
    private ArrayList<String> todosOfTodayID;
    private ArrayAdapter todosOfToday;
    private ListView list;
    private boolean inside = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__view_activity);
        activity = this;

        mGestureDetector2 = new GestureDetector(this);

        initFloatButton();
        setTitleActivity();
        Intent intentget = getIntent();
        String datestring = intentget.getStringExtra("Date");
        resetYear = intentget.getIntExtra("Year", 2);
        resetMonth = intentget.getIntExtra("Month", 6);
        resetWeek = intentget.getIntExtra("Week", 1);
        resetDay = intentget.getIntExtra("Day", 1);
        actualMonth = intentget.getStringExtra("Monthstring");
        setTitleActivity();

        list = (ListView) findViewById(R.id.dayview_todo_list);
        list.setAdapter(ArrayAdapter(getFormattedString()));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Todo title = (Todo)(parent.getItemAtPosition(position));
                String titlestring = title.getTitle();
                onClickListElement(titlestring);
            }
        });

        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.relativeDay);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                mGestureDetector2.onTouchEvent(event);
                return true;
            }
        });


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("Hi", "Hello");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                drag = position;
                return true;
            }
        });

        list.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                FontAwesome trash;
                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED){
                    trash = (FontAwesome) findViewById(R.id.trash);
                    trash.setVisibility(View.VISIBLE);

                }
                if(event.getAction() == DragEvent.ACTION_DRAG_ENDED){
                    trash = (FontAwesome) findViewById(R.id.trash);
                    trash.setVisibility(View.INVISIBLE);
                    if(inside){
                        int id = Integer.parseInt(todosOfTodayID.get(drag));
                        deleteTodo(id, drag);
                    }
                }
                return true;
            }
        });

        FontAwesome trash = (FontAwesome) findViewById(R.id.trash);
        trash.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_ENTERED){
                    v.setBackground(getResources().getDrawable(R.drawable.linear_layout_border));
                    inside = true;
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_EXITED){
                    v.setBackground(null);
                    inside = false;
                }
                return true;
            }
        });
    }

    public ArrayAdapter ArrayAdapter(String date) {
        try {
            todosOfTodayID =  new ArrayList<String>();
            Thread_GetTodayTodos thread_getTodayTodos = new Thread_GetTodayTodos(date, this);
            thread_getTodayTodos.start();
            thread_getTodayTodos.join();
            List<Todo> todos = thread_getTodayTodos.getAll();
            String[] titles = new String[todos.size()];
            String[] icons = new String[todos.size()];
            boolean[] privacy = new boolean[todos.size()];
            int[] priorities = new int[todos.size()];

            int counter = 0;
            for (Todo todo : todos) {
                todosOfTodayID.add(Integer.toString(todo.getID_Todo()));
                titles[counter] = todo.getTitle();
                icons[counter] = todo.getTheme();
                privacy[counter] = todo.isPrivacy();
                priorities[counter] = todo.getPriority();
                counter++;
            }
            TextView textview =(TextView) findViewById(R.id.textviewnotodos);
            todosOfToday = new ArrayAdapter_Dayviewrow(this, icons, titles, privacy, priorities);
            for(Todo todo : todos){
                todosOfToday.add(todo);
            }
            if(counter != 0) {
                textview.setVisibility(View.INVISIBLE);
                return todosOfToday;
            }
            else{
                textview.setVisibility(View.VISIBLE);
                textview.setText("No ToDos yet");
                return todosOfToday;
            }



        } catch (Exception e) {
            Log.d("DayView_ArrayAdapter()_Error", e.getMessage());
            return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
    }

    public void setTitleActivity() {
        actualDate = Calendar.getInstance().getTime();
        actualDateFormatted = dateFormatter.format(actualDate);
        try {
            date = dateFormatter.parse(actualDateFormatted);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (resetYear == 2) {
            year = date.getYear() + 1900;
        } else if (resetYear == 1) {
            year = date.getYear() + 1899;
        } else {
            year = date.getYear() + 1901;
        }
        if(resetMonth == 0){
            actualMonth = "January";
        }
        else if(resetMonth == 1){
            actualMonth = "February";
        }
        else if(resetMonth == 2){
            actualMonth = "March";
        }
        else if(resetMonth == 3){
            actualMonth = "April";
        }
        else if(resetMonth == 4){
            actualMonth = "May";
        }
        else if(resetMonth == 5){
            actualMonth = "June";
        }
        else if(resetMonth == 6){
            actualMonth = "July";
        }
        else if(resetMonth == 7){
            actualMonth = "August";
        }
        else if(resetMonth == 8){
            actualMonth = "September";
        }
        else if(resetMonth == 9){
            actualMonth = "October";
        }
        else if(resetMonth == 10){
            actualMonth = "November";
        }
        else if(resetMonth == 11){
            actualMonth = "December";
        }
        setTitle(resetDay + " " + actualMonth + " " + year);
    }

    public void swipeLeft(){
        ImportDates id = new ImportDates();
        List<Date> dates = id.defineDateinterval();
        String[] trim = getTitle().toString().split(" ");
        int day = Integer.parseInt(trim[0]);
        int month = resetMonth;
        for(Date a:dates){
            if(a.getDate() == day & a.getMonth() == resetMonth & a.getYear() + 1900 == year){
                Calendar c = Calendar.getInstance();
                c.setTime(a);
                c.add(Calendar.DATE, 1);
                Date newDate = c.getTime();
                if(!(newDate.getYear() + 1900 == date.getYear() + 1901 & newDate.getDate() == 28 & newDate.getMonth() == resetMonth)) {
                    if (newDate.getYear() == date.getYear()) {
                        year = 2;
                    } else if (newDate.getYear() == date.getYear() + 1) {
                        year = 3;
                    } else if (newDate.getYear() == date.getYear() - 1) {
                        year = 1;
                    }
                    resetYear = year;
                    resetMonth = newDate.getMonth();
                    resetDay = newDate.getDate();
                    list.setAdapter(ArrayAdapter(getFormattedString()));
                    setTitleActivity();
                }
            }
        }

    }

    public void swipeRight(){
        ImportDates id = new ImportDates();
        List<Date> dates = id.defineDateinterval();
        String[] trim = getTitle().toString().split(" ");
        int day = Integer.parseInt(trim[0]);
        int month = resetMonth;
        for(Date a:dates){
            if(a.getDate() == day & a.getMonth() == resetMonth & a.getYear() + 1900 == year){
                Calendar c = Calendar.getInstance();
                c.setTime(a);
                c.add(Calendar.DATE, -1);
                Date newDate = c.getTime();
                if(!(newDate.getYear() + 1900 == date.getYear() + 1899 & newDate.getDate() == 1 & newDate.getMonth() == resetMonth)) {
                    if (newDate.getYear() == date.getYear()) {
                        year = 2;
                    } else if (newDate.getYear() == date.getYear() + 1) {
                        year = 3;
                    } else if (newDate.getYear() == date.getYear() - 1) {
                        year = 1;
                    }
                    resetYear = year;
                    resetMonth = newDate.getMonth();
                    resetDay = newDate.getDate();
                    list.setAdapter(ArrayAdapter(getFormattedString()));
                    setTitleActivity();
                }
            }
        }

    }

    public String getFormattedString(){
        if (resetYear == 2) {
            year = date.getYear() + 1900;
        } else if (resetYear == 1) {
            year = date.getYear() + 1899;
        } else {
            year = date.getYear() + 1901;
        }
        String actualDate;
        String dateGetToDos;
        if (resetDay < 10) {
            actualDate = 0 + "" + resetDay;
            if (resetMonth + 1 < 10) {
                return dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            } else {
                return dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            }
        } else {
            actualDate = resetDay + "";
            if (resetMonth + 1 < 10) {
                return dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            } else {
                return dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            }
        }
    }

    public void onClickListElement(String title) {
        String dateGetToDos = getFormattedString();
        try {
            Thread_GetToDoByTitle thread_getToDoByTitle = new Thread_GetToDoByTitle(dateGetToDos, title, this);
            thread_getToDoByTitle.start();
            thread_getToDoByTitle.join();
            todo = thread_getToDoByTitle.getAll();
            if (todo != null) {
                if (todo.isPrivacy() == false) {
                    Intent intent = new Intent(Day_View_activity.this, ToDoDescribtion.class);
                    intent.putExtra("Title", todo.getTitle());
                    intent.putExtra("Description", todo.getDescription());
                    intent.putExtra("Date", todo.getDate());
                    intent.putExtra("Priority", todo.getPriority());
                    intent.putExtra("Category", todo.getTheme());
                    intent.putExtra("Private", todo.isPrivacy());
                    startActivity(intent);
                } else {
                    openDialog();
                    controlFingerprint();
                }
            }
        } catch (java.lang.InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initFloatButton() {
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatday);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Create_Todo_Activity.class);
                intent.putExtra("Activity", "day");
                startActivity(intent);
            }
        });
    }

    public void openDialog() {
        dialog = new FingerprintDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void updateFingerprintSensor(String ident, String text, FingerprintDialog dialog) {
        this.dialog = dialog;
        this.dialog.updateStatus(ident, text);
    }

    public void startDescriptActivity() {
        Intent intent = new Intent(Day_View_activity.this, ToDoDescribtion.class);
        intent.putExtra("Title", todo.getTitle());
        intent.putExtra("Description", todo.getDescription());
        intent.putExtra("Date", todo.getDate());
        intent.putExtra("Priority", todo.getPriority());
        intent.putExtra("Category", todo.getTheme());
        intent.putExtra("Private", todo.isPrivacy());
        startActivity(intent);
    }

    public void controlFingerprint() {
        if (Build.VERSION.SDK_INT >= 23) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            if (!fingerprintManager.isHardwareDetected()) {
                dialog.dismiss();
                CharSequence text = "Your device doesn't support fingerprint authentication";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,text,duration);
                toast.show();
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                dialog.dismiss();
                CharSequence text = "Please grant this app the fingerprint permission";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,text,duration);
                toast.show();
            }
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                dialog.dismiss();
                CharSequence text = "No fingerprint configured. Please register at least one fingerprint in your device's Settings";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(this,text,duration);
                toast.show();
            } else {

                try {
                    generateKey();
                } catch (FingerprintException e) {
                    e.printStackTrace();
                }
                if (initCipher()) {
                    cryptoObject = new FingerprintManager.CryptoObject(cipher);

                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAuth(fingerprintManager, cryptoObject, dialog);
                }
            }
        }
    }

    private void generateKey() throws FingerprintException {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");

            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);

            keyGenerator.init(new

                    KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                    .setUserAuthenticationRequired(true).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());

            keyGenerator.generateKey();

        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException | CertificateException | IOException exc) {
            exc.printStackTrace();
            throw new FingerprintException(exc);
        }
    }


    public boolean initCipher() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }
        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
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
                    return true;
                }
                else{
                    Intent intentset = new Intent(Day_View_activity.this, wocheActivity.class);
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

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }

    public void deleteTodo(int id, int drag){
        Todo todo = (Todo) todosOfToday.getItem(drag);
        todosOfToday.remove(todosOfToday.getItem(drag));
        todosOfTodayID.remove(drag);
        todosOfToday.notifyDataSetChanged();
        list.deferNotifyDataSetChanged();
        Thread_DeleteTodo thread_deleteTodo = new Thread_DeleteTodo(this, id);
        thread_deleteTodo.start();
        try{
            thread_deleteTodo.join();
        }catch (Exception e){

        }
        list.setAdapter(ArrayAdapter(getFormattedString()));
    }
}
