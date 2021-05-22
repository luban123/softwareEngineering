package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.Time.TimeUtils;
import com.example.bottomdemo.login.Finish_qd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class S_Wait_Qd_Adapter extends BaseAdapter {
    private List<Finish_qd> mData;
    private Context mContext;
    private Map map;
    private Map map2;
    private Map map3;
    private List<Integer> li=new ArrayList<Integer>();
    private int position2=-1;
//    ViewHolder viewHolder2;
    private SparseArray<CountDownTimer> countDownCounters;
    public S_Wait_Qd_Adapter(List<Finish_qd> mData, Context mContext, Map map,Map map2,Map map3){
        this.mData=mData;
        this.mContext=mContext;
        this.map=map;
        this.map2=map2;
        this.map3=map3;
        this.countDownCounters = new SparseArray<>();
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
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        for (int i = 0; i < li.size(); i++) {
            if (position==li.get(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        position2=-1;
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.s_wait_qd_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.cname=(TextView)convertView.findViewById(R.id.cname_14);
            viewHolder.qtime=(TextView)convertView.findViewById(R.id.start_time2);
            viewHolder.longtime=(TextView)convertView.findViewById(R.id.longtime_2);
            viewHolder.count_qd=(TextView)convertView.findViewById(R.id.count_qd_2);
            viewHolder.finish=(TextView)convertView.findViewById(R.id.Finish);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        long timer =  (long)map2.get(mData.get(position).getCid());
        CountDownTimer countDownTimer = countDownCounters.get(viewHolder.longtime.hashCode());
        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }

        timer = timer - System.currentTimeMillis();
        //expirationTime 与系统时间做比较，timer 小于零，则此时倒计时已经结束。
        if (timer > 0) {
            countDownTimer = new CountDownTimer(timer, 1000) {
                public void onTick(long millisUntilFinished) {
                    viewHolder.longtime.setText(TimeUtils.getCountTimeByLong(millisUntilFinished));
                }
                public void onFinish() {
                    viewHolder.longtime.setText("00:00:00");
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(viewHolder.longtime.hashCode(), countDownTimer);
        } else {
            viewHolder.longtime.setText("00:00:00");
            //时间到，禁止操作
            viewHolder.finish.setText("已结束");
            li.add(position);
        }

        viewHolder.qtime.setText((String)map3.get(mData.get(position).getCid()));
        viewHolder.cname.setText(mData.get(position).getCname());
        viewHolder.longtime.setText(mData.get(position).getContinue_time());
        String count_Peo=String.valueOf(map.get(mData.get(position).getCid()));
        viewHolder.count_qd.setText("已有"+count_Peo+"人参与");


        return convertView;
    }

    public class ViewHolder {
        public TextView cname;
        public TextView qtime;
        public TextView longtime;
        public TextView count_qd;
        public TextView finish;
    }
    public void cancelAllTimers() {
        if (countDownCounters == null) {
            return;
        }
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            CountDownTimer cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.cancel();
            }
        }
    }
}
