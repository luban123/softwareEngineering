package com.example.bottomdemo.Adapter;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.T_Student_Choice_List;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.utils.DBUtils;

import java.util.List;
import android.os.Handler;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class Student_Choice_ListAdapter extends BaseAdapter {
    private List<Student> mData;
    private Context mContext;
    private static final int CHANGE_TEXT=1;
    private String cid;
    private String tid;
    int i;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case CHANGE_TEXT:
                    notifyDataSetChanged();
                    break;
            }
        }
    };

    public Student_Choice_ListAdapter(List<Student> mData, Context mContext,String cid,String tid) {
        this.mData = mData;
        this.mContext = mContext;
        this.cid=cid;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null)
        {
            convertView=LayoutInflater.from(mContext).inflate(R.layout.t_student_choice_item,parent,false);
            holder=new ViewHolder();
            holder.sname=(TextView)convertView.findViewById(R.id.sname3);
            holder.sno=(TextView)convertView.findViewById(R.id.sno2);
            holder.accept=(TextView)convertView.findViewById(R.id.accept);
            holder.reject=(TextView)convertView.findViewById(R.id.reject);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

            holder.sname.setText(mData.get(position).getSname());
            holder.sno.setText(mData.get(position).getSno());
            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DBUtils.Accept_Student(mData.get(position).getSno(), cid);
                            mData.remove(position);
                            Message message=new Message();
                            message.what=CHANGE_TEXT;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DBUtils.Reject_Student(mData.get(position).getSno(), cid);
                        mData.remove(position);
                        Message message=new Message();
                        message.what=CHANGE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView sname,sno,accept,reject;
    }
}