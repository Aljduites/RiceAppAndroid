package com.example.alj.riceapp;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SetTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "SetTimeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Log.d(TAG, "onCreate: SetTimeActivity");
        Button btnSetTime = findViewById(R.id.btnTimePicker);

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        boolean isPM = (i >= 12);
        TextView lblSetTime = findViewById(R.id.lblCookSetTime);

        lblSetTime.setText(String.format("%02d:%02d %s",
                (i == 12 || i == 0) ? 12 : i % 12, i1, isPM ? "PM" : "AM"));
    }
}
