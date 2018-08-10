package com.example.alj.riceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {

    final class MyThreadClass implements Runnable{

        int serviceId;
        MyThreadClass(int serviceId){
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            int i = 0;
            synchronized (this) {
                while (i < 10){
                    try {
                        wait(1500);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(serviceId);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
/*        return super.onStartCommand(intent, flags, startId);*/
        Toast.makeText(MyService.this, "Service Started...", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new MyThreadClass(startId));
                thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
/*        super.onDestroy();*/

        Toast.makeText(MyService.this,"Service Destroyed...",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
