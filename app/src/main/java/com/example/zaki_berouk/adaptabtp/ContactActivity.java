package com.example.zaki_berouk.adaptabtp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zaki_berouk.adaptabtp.Adapter.StaffAdapterLV;
import com.example.zaki_berouk.adaptabtp.VocalCommand.CommandProccessed;
import com.example.zaki_berouk.adaptabtp.mocked_data.StaffSetter;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        List<Staff> staff;
        StaffSetter staffSetter = new StaffSetter();
        staff = staffSetter.staffListRetrieve();

        StaffAdapterLV adapter = new StaffAdapterLV(getApplicationContext(), R.layout.staff_list_item, staff);
        ListView list_staff = (ListView) findViewById(R.id.staffAll);
        list_staff.setAdapter(adapter);
        checkPermission();

        final Button button = findViewById(R.id.voice_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displaySpeechRecognizer();
            }
        });
    }


    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            VocalCommandAnalyzer vca = new VocalCommandAnalyzer();
            CommandProccessed commandProccessed = vca.analyzeCommand(spokenText, ContactActivity.this, false);

            if (commandProccessed != null) {
                String cmd = commandProccessed.getCommand();
                if (checkForPermission(commandProccessed.getCommand())) {
                    if (cmd.equals("Message")) {
                        String msg = commandProccessed.getContent();
                        String tar = commandProccessed.getTargets().get(0).getPhone();
                        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, ContactActivity.class), 0);
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(tar, null, msg, pi, null);
                    } else if(cmd.equals("Alerte")){
                        String msg = commandProccessed.getContent();
                        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, ContactActivity.class), 0);
                        SmsManager sms = SmsManager.getDefault();
                        for(Staff s : commandProccessed.getTargets()){
                            sms.sendTextMessage(s.getPhone(), null, msg, pi, null);
                        }
                    }
                    else if(cmd.equals("Appel")){
                        String tar = commandProccessed.getTargets().get(0).getPhone();
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tar)));

                    }

                } else {
                    requestPermission(commandProccessed.getCommand());
                }
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

    private Boolean checkForPermission(String cmd) {
        if (cmd.equals("Message") || cmd.equals("Alerte")) {
            int result = ContextCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.SEND_SMS);
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else if (cmd.equals("Appel")) {
            int result = ContextCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE);
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    private void requestPermission(String cmd) {
        if (cmd.equals("Message") || cmd.equals("Alerte")) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        if (cmd.equals("Appel")) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }


}
