package net.ictcampus.weberyo.todo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private Context context;
    private FingerprintDialog dialog;

    public FingerprintHandler(Context mContext) {
        context = mContext;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject, FingerprintDialog dialog) {
        cancellationSignal = new CancellationSignal();
        this.dialog = dialog;
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Day_View_activity dayview = new Day_View_activity();
        dayview.updateFingerprintSensor("error","Error!\n" + errString, dialog);
    }

    @Override
    public void onAuthenticationFailed() {
        Day_View_activity dayview = new Day_View_activity();
        dayview.updateFingerprintSensor("failed","Authentication failed!", dialog);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Day_View_activity dayview = new Day_View_activity();
        dayview.updateFingerprintSensor("help", helpString.toString(), dialog);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Day_View_activity dayview = new Day_View_activity();
        dayview.updateFingerprintSensor("success", "", dialog);
    }

}
