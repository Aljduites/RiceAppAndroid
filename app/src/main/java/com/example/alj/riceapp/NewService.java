package com.example.alj.riceapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class NewService extends IntentService {

    public NewService() {
        super("MyWorkerThread");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(NewService.this, "Service Started", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(NewService.this, "Service Stop...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        synchronized (this) {
            int count = 0;
            while (count < 10) {
                try {
                    wait(1500);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
