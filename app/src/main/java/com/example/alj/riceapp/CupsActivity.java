package com.example.alj.riceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CupsActivity extends AppCompatActivity {
    private static final String TAG = "CupsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cups);
        Log.d(TAG, "onCreate: CupsActivity");

        Button btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSetTimeActivity();
            }
        });
    }

    public void openSetTimeActivity(){
        Intent intent = new Intent(this, SetTimeActivity.class);
        startActivity(intent);
    }

}
