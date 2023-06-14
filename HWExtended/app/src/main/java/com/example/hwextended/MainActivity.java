package com.example.hwextended;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextSender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSender = (EditText) findViewById(R.id.editTextSenderData);
    }
    public void buttonSenderPressed(View v) {
        Intent senderIntent= new Intent(this, ReceiveActivity.class);
        senderIntent.putExtra("KEY_SENDER", editTextSender.getText().toString());
        startActivity(senderIntent);
    }
}