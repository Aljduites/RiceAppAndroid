package com.example.alj.riceapp;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
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
        btnTest = findViewById(R.id.btnTest);
        editTxt1 = findViewById(R.id.txtPassword);
    }
    private void openHomeActivity(){
        Log.d(TAG, "openHomeActivity: Clicked");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    private void startService(View view){
        Intent intent =  new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    private void stopService(View view){
        Intent intent =  new Intent(MainActivity.this, MyService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: Clicked");
        moveTaskToBack(true);
    }
}
