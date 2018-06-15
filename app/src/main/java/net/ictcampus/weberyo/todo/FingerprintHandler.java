package net.ictcampus.weberyo.todo;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private Context context;
    private FingerprintDialog dialog;

    //constructor set context
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject, FingerprintDialog dialog) {
        //signal for releasing the fingerprint hardware when cancelled, not used whatever
        cancellationSignal = new CancellationSignal();
        //set this dialog to the gotten dialog
        this.dialog = dialog;
        //start authenticate with the fingerprint hardware and the variables gotten on this class
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    //if an error occurred in the authentification
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        //start method that writes the error in a textview
        DayActivity dayview = new DayActivity();
        dayview.updateFingerprintSensor("error","Error!\n" + errString, dialog);
    }

    //if there weren't any matches
    @Override
    public void onAuthenticationFailed() {
        //start method that writes the Authentification failed in a textview
        DayActivity dayview = new DayActivity();
        dayview.updateFingerprintSensor("failed","Authentication failed!", dialog);
    }

    //if the user did something wrong, e. not the whole finger on the sensor
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        //start method that writes the tipp in a textview
        DayActivity dayview = new DayActivity();
        dayview.updateFingerprintSensor("help", helpString.toString(), dialog);
    }

    //if authentification succeeded
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        //start method that closes the dialog and start the description of a todo
        DayActivity dayview = new DayActivity();
        dayview.updateFingerprintSensor("success", "", dialog);
    }

}
