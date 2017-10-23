package com.tyondo.apps.tyondoenterprise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    //splash screen timer
    private static int SPLASH_TIME_OUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //This handler is using runnable that will be used to pause the activity for set duration of time

        new Handler().postDelayed(new Runnable() {
            //showing splashscreen with a timer. best for showing the company brand
            @Override
            public void run() {
                //this will be run once the timer is over i.e start the main activity
                //Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                //close the splash activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
