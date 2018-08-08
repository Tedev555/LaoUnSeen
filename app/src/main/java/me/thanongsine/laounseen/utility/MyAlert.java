package me.thanongsine.laounseen.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import me.thanongsine.laounseen.R;

public class MyAlert {
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void normalDialog(String titleString, String messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.baseline_add_alert_black_36);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
