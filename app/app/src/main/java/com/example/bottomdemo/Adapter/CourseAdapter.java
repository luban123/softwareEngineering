package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Courses;

import java.util.List;
import java.util.Map;

public class CourseAdapter extends BaseAdapter {
    private List<Courses> mData;
    private Context mContext;
    private String tname;
    private Map map,map2;
    public CourseAdapter(List<Courses> mData, Context mContext, String tname, Map map,Map map2){
        this.mData=mData;
        this.mContext=mContext;
        this.tname=tname;
        this.map=map;
        this.map2=map2;
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.t_list_item,parent,false);
        TextView cname=(TextView)convertView.findViewById(R.id.cname_2);
        TextView tid=(TextView)convertView.findViewById(R.id.tname_2);
        TextView count_People=(TextView)convertView.findViewById(R.id.count_student2);
        TextView count_chuli_People=(TextView)convertView.findViewById(R.id.chuli);
        cname.setText(mData.get(position).getCname());
        tid.setText(tname);
        String count_Peo=String.valueOf(map.get(mData.get(position).getCid()));
        count_People.setText(count_Peo);
        String count_chuli_Peo=String.valueOf(map2.get(mData.get(position).getCid()));
        count_chuli_People.setText(count_chuli_Peo);

        return convertView;
    }
}
