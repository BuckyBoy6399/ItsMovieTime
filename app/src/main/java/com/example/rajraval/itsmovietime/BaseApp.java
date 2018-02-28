package com.example.rajraval.itsmovietime;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;


public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("Default");
    }
}
