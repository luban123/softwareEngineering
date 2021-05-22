package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.Student_info;

import java.util.List;
import java.util.Map;

public class S_CourseAdapter extends BaseAdapter {
    private List<Student_info> mData;
    private Context mContext;
    private Map map;
    public S_CourseAdapter(List<Student_info> mData, Context mContext,Map map){
        this.mData=mData;
        this.mContext=mContext;
        this.map=map;
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.s_list_item,parent,false);
        TextView cname=(TextView)convertView.findViewById(R.id.cname_10);
        TextView tname=(TextView)convertView.findViewById(R.id.tname_3);
        TextView count_People=(TextView)convertView.findViewById(R.id.count_student3);


        cname.setText(mData.get(position).getCname());
        tname.setText(mData.get(position).getTname());
        String count_Peo=String.valueOf(map.get(mData.get(position).getCid()));
        count_People.setText(count_Peo);


        return convertView;
    }
}
