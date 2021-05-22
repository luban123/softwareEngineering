package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Student;

import java.util.List;

public class StudentListAdapter extends BaseAdapter {
    private List<Student> mData;
    private Context mContext;
    public StudentListAdapter(List<Student> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.t_student_item,parent,false);
        TextView sname=(TextView)convertView.findViewById(R.id.sname);
        TextView sno=(TextView)convertView.findViewById(R.id.sno);
        TextView sphone=(TextView)convertView.findViewById(R.id.sphone);
        sname.setText(mData.get(position).getSname());
        sno.setText(mData.get(position).getSno());
        sphone.setText(mData.get(position).getSphone());
        return convertView;
    }
}
