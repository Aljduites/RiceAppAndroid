package com.example.alj.riceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

public class SplashActivity extends Activity {
    /** Called when the activity is first created */
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String storedPassword = preferences.getString("Key_Password","");
        Long storedExpiration = preferences.getLong("ExpiredTime", -1);

        if(storedExpiration > System.currentTimeMillis()){
            intent = new Intent(SplashActivity.this, HomeActivity.class);
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, 3000);

        } else {
            intent = new Intent(SplashActivity.this, MainActivity.class);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }
    }

}
