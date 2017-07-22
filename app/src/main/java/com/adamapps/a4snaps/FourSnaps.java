package com.adamapps.a4snaps;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class FourSnaps extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(FirebaseAuth.getInstance()!=null){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
