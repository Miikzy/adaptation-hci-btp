package com.example.zaki_berouk.adaptabtp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zaki_berouk.adaptabtp.R;
import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.MyViewHolder>{
    private List<Staff> staffList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameStaff, affectation, phoneStaff, presenceStaff;

        public MyViewHolder(View view) {
            super(view);
            nameStaff = (TextView) view.findViewById(R.id.nameStaff);
            affectation = (TextView) view.findViewById(R.id.roleStaff);
            phoneStaff = (TextView) view.findViewById(R.id.phoneStaff);
            presenceStaff = (TextView) view.findViewById(R.id.presenceStaff);
        }
    }

    public StaffAdapter(List<Staff> list){
        this.staffList = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View staffView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_rv, parent, false);

        return new MyViewHolder(staffView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Staff staff = staffList.get(i);
        holder.nameStaff.setText(staff.getName());
        holder.affectation.setText(staff.getRole());
        holder.phoneStaff.setText(staff.getPhone());
        holder.presenceStaff.setText(staff.getPresent());
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }
}
