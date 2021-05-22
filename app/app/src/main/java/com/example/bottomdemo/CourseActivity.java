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

import com.example.bottomdemo.Adapter.CourseAdapter;
import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.User;
import com.example.bottomdemo.utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Courses> mData=null;
    private Context mContext;
    private CourseAdapter mAdapter=null;
    private ListView listView;
    private Button back;
    private User user;
    private Handler handler=null;
    String tid,tname;
    Map map;
    Map map2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_course_list);
        mContext=this;
        listView=(ListView)findViewById(R.id.main_courses);
        listView.setOnItemClickListener(this);
        back=findViewById(R.id.back);
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
        handler=new Handler();
        new Thread(){
            public void run(){
                map=new HashMap();
                map2=new HashMap();
                mData= DBUtils.Select_CourseByTid(tid);
                for(Courses cr:mData){
                map.put(cr.getCid(),DBUtils.Count_People(cr.getCid()));
                map2.put(cr.getCid(),DBUtils.Count_Chuli_People(cr.getCid()));

            }
                handler.post(runnableUi);
            }
        }.start();

    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new CourseAdapter(mData,mContext,tname,map,map2);
            listView.setAdapter(mAdapter);
        }
    };
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(CourseActivity.this, T_choice_student.class);
        intent.putExtra("tid",tid);
        intent.putExtra("cid",mData.get(position).getCid());
        intent.putExtra("cname",mData.get(position).getCname());
        startActivity(intent);

    }
}
