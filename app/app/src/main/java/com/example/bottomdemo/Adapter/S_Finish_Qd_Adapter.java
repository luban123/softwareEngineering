package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Student_info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class S_Finish_Qd_Adapter extends BaseAdapter {
    private List<Finish_qd> mData;
    private Context mContext;
    private Map map;
    public S_Finish_Qd_Adapter(List<Finish_qd> mData, Context mContext, Map map){
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.s_finish_qd_item,parent,false);
        TextView cname=(TextView)convertView.findViewById(R.id.cname_13);
        TextView qtime=(TextView)convertView.findViewById(R.id.start_time1);
        TextView longtime=(TextView)convertView.findViewById(R.id.longtime);
        TextView count_qd=(TextView)convertView.findViewById(R.id.count_qd);
        Date date=mData.get(position).getQtime();
        if(date!=null)
        {
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_str=format.format(date);
            qtime.setText(date_str);
        }

        cname.setText(mData.get(position).getCname());
        longtime.setText(mData.get(position).getContinue_time());
        String count_Peo=String.valueOf(map.get(mData.get(position).getCid()));
        count_qd.setText("已有"+count_Peo+"人参与");


        return convertView;
    }
}
