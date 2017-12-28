
package com.parse.TekSee;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.parse.ParseAnalytics;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        getSupportActionBar().hide();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(getApplicationContext(),RadioOption.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}