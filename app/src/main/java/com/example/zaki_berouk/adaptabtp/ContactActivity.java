package com.example.zaki_berouk.adaptabtp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.zaki_berouk.adaptabtp.Adapter.StaffAdapter;
import com.example.zaki_berouk.adaptabtp.Adapter.StaffAdapterLV;
import com.example.zaki_berouk.adaptabtp.mocked_data.StaffSetter;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
    private static final int SPEECH_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        List<Staff> staff;
        StaffSetter staffSetter = new StaffSetter();
        staff = staffSetter.staffListRetrieve();

        StaffAdapterLV adapter = new StaffAdapterLV(getApplicationContext(), R.layout.staff_list_item, staff);
        ListView list_staff = (ListView) findViewById(R.id.staffAll);
        list_staff.setAdapter(adapter);

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
            System.out.println("\n*\n*" + spokenText + "\n*\n*");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
