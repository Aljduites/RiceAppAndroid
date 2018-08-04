package com.example.alj.riceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private EditText editTxt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewIds();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                editTxt1.setText("");
            }
        });
    }
    public void setViewIds()
    {
        btn1 = findViewById(R.id.btnOk);
        btn2 = findViewById(R.id.btnCancel);
        editTxt1 = findViewById(R.id.txtPassword);
    }
    public void openHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
