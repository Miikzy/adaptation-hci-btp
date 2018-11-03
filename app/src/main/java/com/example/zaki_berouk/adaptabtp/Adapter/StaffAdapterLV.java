package com.example.zaki_berouk.adaptabtp.Adapter;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zaki_berouk.adaptabtp.R;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.LinkedList;
import java.util.List;

public class StaffAdapterLV extends ArrayAdapter<Staff> {

    private final Context mContext;
    private List<Staff> mStaff;
    public TextView nameStaff, affectation, phoneStaff, presenceStaff;

    public StaffAdapterLV(Context context, int resource, List<Staff> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mStaff = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.staff_list_item, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        nameStaff = (TextView) convertView.findViewById(R.id.nameStaff_item);
        affectation = (TextView) convertView.findViewById(R.id.roleStaff_item);
        phoneStaff = (TextView) convertView.findViewById(R.id.phoneStaff_item);
        presenceStaff = (TextView) convertView.findViewById(R.id.presenceStaff_item);

        Staff staff = mStaff.get(position);
        nameStaff.setText(staff.getName());
        affectation.setText(staff.getRole());
        phoneStaff.setText(staff.getPhone());
        presenceStaff.setText(staff.getPresent());

        return convertView;
    }
}
