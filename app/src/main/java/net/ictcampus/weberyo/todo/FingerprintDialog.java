package net.ictcampus.weberyo.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FingerprintDialog extends DialogFragment{

    FingerprintDialog self;
    View view;
    ImageView dialogView;
    TextView textview;

    //if the dialog was created
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_fingerprint, null);

        self = this;

        dialogView = view.findViewById(R.id.closeDialog);
        dialogView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                self.dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }

    public void updateStatus(String ident, String text) {
        textview = view.findViewById(R.id.textViewFingerprint);
        if(ident.equals("error")){
            textview.setTextColor(Color.RED);
            textview.setText(text);
        }
        else if(ident.equals("failed")){
            textview.setTextColor(Color.RED);
            textview.setText(text);
        }
        else if(ident.equals("help")){
            textview.setTextColor(Color.RED);
            textview.setText(text);
        }
        else if(ident.equals("success")){
            textview.setTextColor(Color.BLACK);
            this.dismiss();
            Day_View_activity activity = (Day_View_activity) getActivity();
            activity.startDescriptActivity();
        }
    }

    public void missing(int thing){
        textview = view.findViewById(R.id.textViewFingerprint);
        if(thing == 1){
            String error = "Your device doesn't support fingerprint authentication";
            textview.setText(error);
            textview.setTextColor(Color.RED);
        }
        else if(thing == 2){
            String error = "Please grant this app the fingerprint permission";
            textview.setText(error);
            textview.setTextColor(Color.RED);
        }
        else if(thing == 3){
            String error = "No fingerprint configured. Please register at least one fingerprint in your device's Settings";
            textview.setText(error);
            textview.setTextColor(Color.RED);
        }
        else if(thing == 4){
            textview.setTextColor(Color.BLACK);
        }
    }
}
