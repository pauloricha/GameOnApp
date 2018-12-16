package com.gameondigital.gameonapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gameondigital.gameonapp.R;

public class ShowToast {

    public static void toast(Activity activity, String message){
        Typeface montserratRegular = Typeface.createFromAsset(activity.getAssets(), "fonts/Montserrat-Regular.otf");

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        //LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error,
                (ViewGroup) activity.findViewById(R.id.ll_toast));

        TextView txt_message_toast = (TextView) layout.findViewById(R.id.txt_message_toast);
        txt_message_toast.setText(message);
        txt_message_toast.setTypeface(montserratRegular);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
