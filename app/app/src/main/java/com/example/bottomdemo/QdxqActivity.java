package com.example.bottomdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.QqxqAdapter;
import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.User;
import com.example.bottomdemo.utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QdxqActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Courses> mData=null;
    private Context mContext;
    private QqxqAdapter mAdapter=null;
    private ListView listView;
    private Button back;
    private User user;
   // private DBThread6 dt6;
   // private Thread thread6;
    private Handler handler=null;
    String tid,tname;
    Map map;
    Map map2;
    Map map3;//地点
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qdxq);
        mContext=this;
        listView=(ListView)findViewById(R.id.qd_list);
        listView.setOnItemClickListener(this);
        back=findViewById(R.id.back5);
        handler=new Handler();
        Intent getData=getIntent();
        user=(User)getData.getSerializableExtra("usr");
        tid=user.getTid();
        tname=user.getTname();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new Thread(){
            public void run(){
                map=new HashMap();
                map2=new HashMap();
                map3=new HashMap();
                mData= DBUtils.Select_CourseByQDXQ(tid);
                for(Courses cr:mData) {
                    map.put(cr.getCid(), DBUtils.Count_finish(cr.getCid()));
                    map2.put(cr.getCid(), DBUtils.Count_People(cr.getCid()));
                    map3.put(cr.getCid(),DBUtils.View_Place(cr.getCid()));
                }
                handler.post(runnableUi);
            }
        }.start();
    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new QqxqAdapter(mData,mContext,map,map2,map3);
            listView.setAdapter(mAdapter);
            }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(QdxqActivity.this, T_Qdrecord_List.class);
        intent.putExtra("cid",mData.get(position).getCid());
        intent.putExtra("cname",mData.get(position).getCname());
        intent.putExtra("tid",tid);
        startActivity(intent);
    }
}
