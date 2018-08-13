package com.example.alj.riceapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btn1, btn2, btnTest;
    private EditText editTxt1;
    private RequestQueue req;
    private StringRequest strReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewIds();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                if(editTxt1.getText().toString().toUpperCase().equals("TESTING")) {
                    openHomeActivity();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                editTxt1.setText("");
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

/*        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestAndPrintResponse();
            }
        });*/
    }

/*    private void sendRequestAndPrintResponse() {
        req = Volley.newRequestQueue(this);
        String url = "http://192.168.1.101/13/off";
        strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Responded");
                Log.i(TAG,String.format("Response: %s",response.toString()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Responded");
                Log.i(TAG,String.format("Response: %s", error.toString()));

                if(error instanceof NoConnectionError) {
                    new AlertDialog.Builder(MainActivity.this).setMessage(
                            "Unable to connect to the server! Please ensure your internet is working!").show();
                }
            }
        });

        strReq.setTag(REQUESTTAG);
        req.add(strReq);
    }*/

/*    @Override
    protected void onStop() {
        super.onStop();
        if(req!=null) {
            req.cancelAll(REQUESTTAG);
        }
    }*/

    private void setViewIds()
    {
        Log.d(TAG, "setViewIds: Created");
        btn1 = findViewById(R.id.btnOk);
        btn2 = findViewById(R.id.btnCancel);
        btnTest = findViewById(R.id.btnStart);
        editTxt1 = findViewById(R.id.txtPassword);
    }
    private void openHomeActivity(){
        Log.d(TAG, "openHomeActivity: Clicked");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void startService(View view){
/*        Intent intent =  new Intent(MainActivity.this, MyService.class);
        startService(intent);*/
        Intent intent = new Intent(MainActivity.this, NewService.class);
        startService(intent);

        startAlarm(true,false);
    }

    private void startAlarm(boolean isNotification, boolean isOnTime) {
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent;
        PendingIntent pendingIntent = null;

        if(isNotification) {
            intent = new Intent(MainActivity.this, AlarmService.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        }

        manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, pendingIntent);
/*        manager.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + 3000, 3000, pendingIntent);*/
    }

    public void clockTime () {
        CountDownTimer timer = new CountDownTimer(20 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                btnTest.setText(String.valueOf(l / 60000) + ":" + String.format("%02d", (l % 60000) / 1000));

            }

            @SuppressLint("MissingPermission")
            @Override
            public void onFinish() {
                btnTest.setText("0:00");
                Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                if(ringtone != null) {
                    ringtone.play();
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Time is up")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        }.start();
        new PowerlockService(MainActivity.this, 10);
    }

    public void stopService(View view){
/*        Intent intent =  new Intent(MainActivity.this, MyService.class);
        stopService(intent);*/
        Intent intent = new Intent(MainActivity.this, NewService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: Clicked");
        moveTaskToBack(true);
    }
}

