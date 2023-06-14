package com.example.hwextended;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        textView= (TextView) findViewById(R.id.textViewReceive);
        Intent receiveIntent = getIntent();
        String receivedValue = receiveIntent.getStringExtra("KEY_SENDER");
        textView.setText(receivedValue);
    }
}