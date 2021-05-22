package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.utils.DBUtils;
import com.example.bottomdemo.utils.Utils;

import java.util.List;
import java.util.Map;

public class QqxqAdapter extends BaseAdapter {
    private List<Courses> mData;
    private Context mContext;
    private Map map;
    private Map map2;
    private Map map3;
    public QqxqAdapter()
    {}

    public QqxqAdapter(List<Courses> mData, Context mContext,Map map,Map map2,Map map3) {
        this.mData = mData;
        this.mContext = mContext;
        this.map=map;
        this.map2=map2;
        this.map3=map3;
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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.t_qdxq_item,parent,false);
        TextView cname=(TextView)convertView.findViewById(R.id.cname_4);
        TextView count_finish=(TextView)convertView.findViewById(R.id.count_finish);
        TextView count_People=(TextView)convertView.findViewById(R.id.count_student);
        TextView place_view=(TextView)convertView.findViewById(R.id.place_imshow);
        cname.setText(mData.get(position).getCname());
        String count_fin=String.valueOf(map.get(mData.get(position).getCid()));
        count_finish.setText(count_fin);
        String count_Peo=String.valueOf(map2.get(mData.get(position).getCid()));
        count_People.setText(count_Peo);
        String View_place=String.valueOf(map3.get(mData.get(position).getCid()));
        place_view.setText(View_place);
        place_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        place_view.setSingleLine(true);
        place_view.setSelected(true);

        return convertView;
    }
}
