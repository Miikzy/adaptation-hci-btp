package com.example.zaki_berouk.adaptabtp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zaki_berouk.adaptabtp.Adapter.StaffAdapter;
import com.example.zaki_berouk.adaptabtp.Adapter.StaffAdapterLV;
import com.example.zaki_berouk.adaptabtp.mocked_data.StaffSetter;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {


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

//        RecyclerView staffRV = findViewById(R.id.staffAll);
//        if(staffRV!= null){
//            staffRV.setHasFixedSize(true);
//            final StaffAdapter sAdapter = new StaffAdapter(staff);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//            mLayoutManager.setAutoMeasureEnabled(true);
//            staffRV.setLayoutManager(mLayoutManager);
//            staffRV.setItemAnimator(new DefaultItemAnimator());
//            staffRV.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//            staffRV.setAdapter(sAdapter);
//        }



    }
}
