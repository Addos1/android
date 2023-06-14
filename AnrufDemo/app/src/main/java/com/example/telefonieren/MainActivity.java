package com.example.telefonieren;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

class MainActivity extends BroadcastReceiver {
    private TelephonyManager mTelephonyManager;
    public static boolean isListening = false;

    @Override
    public void onReceive(final Context context, Intent intent) {

        mTelephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

        PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);

                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Toast.makeText(context, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Toast.makeText(context, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Toast.makeText(context, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        if(!isListening) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
            isListening = true;
        }

    }

}











    /*

    //private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 123;
    private static final String EXTRA_MESSAGE = ;
    private Context thisActivity;

    private Button buttonSofort;

    //public final static String EXTRA_MESSAGE = "com.example.hwe_dku.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSofort = (Button) findViewById(R.id.buttonSofort);
        buttonSofort.setOnClickListener((DialogInterface.OnClickListener) v -> {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:+71116677788"));
            try{
                startActivity(intent);
            }catch (SecurityException e){
                Toast.makeText(MainActivity.this,
                        R.string.no_permission,
                        Toast.LENGTH_LONG).show();
            }
        });
        boolean allowed = true;

        if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
                allowed = false;
                requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }

        buttonSofort.setEnabled(allowed);
        final Button buttonDialog = (Button) findViewById(R.id.buttonDialog);
        buttonDialog.setOnClickListener(OnClickListener()); {

            @Override
                    public void onClick(View v){
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: +123-45-67-89"));
                    startActivity(intent);
            }
        }

        final Button buttonSms = (Button) findViewById(R.id.buttonSms);
        buttonSms.setOnClickListener(OnClickListener(){
            @Override
                    public void onClick(View view){
                String telnr = "123-456-789";
                Uri buttonSmsUri = Uri.parse("smsto:"+telnr);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO,
                        buttonSmsUri);
                sendIntent.putExtra("sms_body",
                        "Hier steht der Text der Nachricht...");
                startActivity(sendIntent);
            }
        }
    }}


    @SuppressLint("MissingSuperCall")
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if ((requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) &&
                (grantResults.length > 0
                         && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)) {
            buttonSofort.setEnabled(true);
        }
    }


private View.OnClickListener OnClickListener(){


private void sendMessage(View v){
        Intent intent=new Intent(this,DisplayMessageActivity.class);
        EditText editText=(EditText)findViewById(R.id.edit_message);
        String message=editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
        }
        }}
/*
    public void onCreate2(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        Intent DialIntent = new
                Intent(Intent.ACTION_DIAL, Uri.parse("tel:87771112233"));
        DialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(DialIntent);
        int permissionCheck = ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.WRITE_CALENDAR);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    public static class DisplayMessageActivity extends Activity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

            TextView textView = new TextView(this);
            textView.setTextSize(40);
            textView.setText(message);

            setContentView(textView);
        }
    }

}



*/
