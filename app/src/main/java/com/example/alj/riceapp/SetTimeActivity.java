package com.example.alj.riceapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SetTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = SetTimeActivity.class.getName();
    private Button btnSetTime, btnCancel, btnOk;
    private TextView lblTime;
    private String _retVal;
    private Bundle bundle;
    private AlertDialog.Builder builder1;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Log.d(TAG, "onCreate: SetTimeActivity");
        setViewIds();

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: Clicked");
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked");
                returnCups();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder1.setMessage("Are you sure?");
                builder1.setCancelable(true);

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startService();
                    }
                });

                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                if(lblTime.getText().toString().isEmpty()) {
                    Toast.makeText(SetTimeActivity.this, "Please set cook time.", Toast.LENGTH_LONG).show();
                }
                else{
                    alertDialog = builder1.create();
                    alertDialog.show();
                }

            }
        });
    }
    private void setViewIds(){
        Log.d(TAG, "setViewIds: Clicked");
        btnSetTime = findViewById(R.id.btnTimePicker);
        btnOk = findViewById(R.id.btnOkTime);
        btnCancel = findViewById(R.id.btnCancelTime);
        lblTime = findViewById(R.id.lblCookSetTime);
        bundle = getIntent().getExtras();
        _retVal = bundle.getString("key");
        builder1 = new AlertDialog.Builder(SetTimeActivity.this);

    }

    /*public void startService(View view) {*/
    public void startService() {
        Intent intent = new Intent(SetTimeActivity.this, NewService.class);
        Bundle bundles = new Bundle();

        bundles.putString("serviceTime",lblTime.getText().toString());
        intent.putExtras(bundles);
        startService(intent);
        openHome();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Log.d(TAG, "onTimeSet: Set");
        boolean isPM = (i >= 12);
        TextView lblSetTime = findViewById(R.id.lblCookSetTime);

        lblSetTime.setText(String.format("%02d:%02d %s",
                (i == 12 || i == 0) ? 12 : i % 12, i1, isPM ? "PM" : "AM"));
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: Clicked");
        moveTaskToBack(true);
    }

    private void openHome(){
        Log.d(TAG, "openHome: Used");
        Intent intent = new Intent(SetTimeActivity.this, HomeActivity.class);
        Bundle bundles = new Bundle();

        bundles.putString("time",lblTime.getText().toString());
        intent.putExtras(bundles);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void returnCups(){
        Log.d(TAG, "returnCups: Clicked");
        Intent intent = new Intent(this, CupsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
