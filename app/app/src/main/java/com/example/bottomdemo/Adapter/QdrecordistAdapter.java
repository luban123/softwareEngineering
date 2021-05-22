package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Qdrecord;
import com.example.bottomdemo.login.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QdrecordistAdapter extends BaseAdapter {
    private List<Qdrecord> mData2;
    private Context mContext;
    public QdrecordistAdapter(Context mContext,List<Qdrecord> mData2) {
        this.mData2 = mData2;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData2.size();
    }

    @Override
    public Object getItem(int position) {
        return mData2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mContext).inflate(R.layout.t_qdrecord_item,parent,false);
        TextView sname=(TextView)convertView.findViewById(R.id.sname2);
        TextView qtime=(TextView)convertView.findViewById(R.id.qtime);
        TextView place=(TextView)convertView.findViewById(R.id.place2);
        TextView qteachermsg=(TextView)convertView.findViewById(R.id.qteachermsg);
        String place_str=mData2.get(position).getAddress();
        place.setText(place_str);
        if(place_str!=null)
        {
            place.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            place.setSingleLine(true);
            place.setSelected(true);
            place.setFocusable(true);
            place.setFocusableInTouchMode(true);
        }


        Date date=mData2.get(position).getQtime();
        if(date!=null)
        {
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_str=format.format(date);
            qtime.setText(date_str);
        }
       else
        {
            qtime.setText("");
        }

        sname.setText(mData2.get(position).getSname());

        if(mData2.get(position).getQteachermsg().equals("1"))
        {
            qteachermsg.setText("已签到");
            qteachermsg.setTextColor(Color.GREEN);
        }
        else if(mData2.get(position).getQteachermsg().equals("0"))
        {
            qteachermsg.setText("未签到");
            qteachermsg.setTextColor(Color.RED);
        }

        return convertView;
    }
}
