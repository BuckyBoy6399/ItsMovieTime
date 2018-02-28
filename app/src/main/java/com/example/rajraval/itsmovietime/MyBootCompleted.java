package com.example.rajraval.itsmovietime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public class MyBootCompleted extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,homescreen.class);
        context.startActivity(i);
    }
}
