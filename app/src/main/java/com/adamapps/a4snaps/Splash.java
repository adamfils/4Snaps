package com.adamapps.a4snaps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Task().execute();
    }
    public class Task extends AsyncTask{

        @Override
        protected void onPostExecute(Object o) {
            finish();
            startActivity(new Intent(Splash.this,GettingStarted.class));
            super.onPostExecute(o);

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
