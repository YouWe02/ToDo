package net.ictcampus.weberyo.todo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.ictcampus.weberyo.todo.net.ictcampus.weberyo.todo.threads.Thread_CreateTodo;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Day_View_activity extends FragmentActivity {

    public static Activity activity;
    private TextView day;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day__view_activity);
        activity = this;
        initFloatButton();
        setDate();
        day = (TextView) findViewById(R.id.dayview_date_header);
        Intent intentget = getIntent();
        String date = intentget.getStringExtra("Date");
        resetYear = intentget.getIntExtra("Year", 2);
        resetMonth = intentget.getIntExtra("Month", 6);
        resetWeek = intentget.getIntExtra("Week", 1);
        resetDay = intentget.getIntExtra("Day", 1);
        actualMonth = intentget.getStringExtra("Monthstring");
        setDate();
        setDate();

        ListView list = (ListView) findViewById(R.id.dayview_todo_list);
        list.setAdapter(ArrayAdapter(date));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = parent.getItemAtPosition(position).toString();
                onClickList(title);

            }
        });

        /*final RelativeLayout layout = (RelativeLayout) findViewById(R.id.linear);
        layout.setOnTouchListener(new OnSwipeTouchListener(Day_View_activity.this) {
            public void onSwipeTop() {

            }

            public void onSwipeRight() {

            }

            public void onSwipeLeft() {

            }

            public void onSwipeBottom() {
                Intent intentset = new Intent(Day_View_activity.this, wocheActivity.class);
                intentset.putExtra("Year", resetYear);
                intentset.putExtra("Month", resetMonth);
                intentset.putExtra("Week", resetWeek);
                intentset.putExtra("Day", resetDay);
                startActivity(intentset);
            }
        });*/
    }

    public ArrayAdapter ArrayAdapter(String date) {
        try {
            ArrayAdapter todosOfToday = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
            Thread_GetTodayTodos thread_getTodayTodos = new Thread_GetTodayTodos(date, this);
            thread_getTodayTodos.start();
            thread_getTodayTodos.join();
            List<Todo> todos = thread_getTodayTodos.getAll();
            for (Todo todo : todos) {
                todosOfToday.add(todo.getTitle());
            }

            return todosOfToday;


        } catch (Exception e) {
            Log.d("DayView_ArrayAdapter()_Error", e.getMessage());
            return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
    }

    public void setDate() {
        actualDate = Calendar.getInstance().getTime();
        actualDateFormatted = dateFormatter.format(actualDate);
        try {
            date = dateFormatter.parse(actualDateFormatted);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        int year = 1;
        if (resetYear == 2) {
            year = date.getYear() + 1900;
        } else if (resetYear == 1) {
            year = date.getYear() + 1899;
        } else {
            year = date.getYear() + 1901;
        }
        setTitle(resetDay + " " + actualMonth + " " + year);
    }

    public void onClickList(String title) {
        int year = 0;
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
                dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            } else {
                dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            }
        } else {
            actualDate = resetDay + "";
            if (resetMonth + 1 < 10) {
                dateGetToDos = year + "-" + "0" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            } else {
                dateGetToDos = year + "-" + (resetMonth + 1) + "-" + actualDate + " " + "00:00:00.000";
            }
        }
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
                if (dialog != null && dialog.isVisible()) {
                    dialog.missing(1);
                }
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                if (dialog != null && dialog.isVisible()) {
                    dialog.missing(2);
                }
            }
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                if (dialog != null && dialog.isVisible()) {
                    dialog.missing(3);
                }
            } else {
                if (dialog != null && dialog.isVisible()) {
                    dialog.missing(4);
                }
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

    private class FingerprintException extends Exception {
        public FingerprintException(Exception e) {
            super(e);
        }
    }
}
