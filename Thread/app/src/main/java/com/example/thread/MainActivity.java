package com.example.thread;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);

        final TextView textview = (TextView) findViewById(R.id.textview);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                textview.setText(MainActivity.this.getString(R.string.begin));
                textview.setText(MainActivity.this.getString(R.string.end));
                try {
                    Thread.sleep(3500);
                    //(while(true){})
                } catch (InterruptedException e) {
                }
// for (int 1 = 0; 1 < 1;) ;
                textview.setText(MainActivity.this.getString(R.string.end));
            }
        });
        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox) ;
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView,
                                          boolean isChecked){
                textview.setText(Boolean.toString(isChecked));
            }
        });
        checkbox.setChecked(true);
    }}
