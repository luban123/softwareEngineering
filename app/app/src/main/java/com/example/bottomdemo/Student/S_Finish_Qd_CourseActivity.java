package com.example.bottomdemo.Student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.S_CourseAdapter;
import com.example.bottomdemo.Adapter.S_Finish_Qd_Adapter;
import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.Student_info;
import com.example.bottomdemo.utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S_Finish_Qd_CourseActivity extends AppCompatActivity{
    private List<Finish_qd> mData=null;
    private Context mContext;
    private S_Finish_Qd_Adapter mAdapter=null;
    private ListView listView;
    private Button back;
    private Student stu;
    private Handler handler=null;
    String sno,sname;
    Map map;
    Map map2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_finish_qd);
        mContext=this;
        listView=(ListView)findViewById(R.id.finish_qd);
        back=findViewById(R.id.back15);
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        sno=stu.getSno();
        sname=stu.getSname();
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
                mData= DBUtils.Finish_qd_by_sno(stu.getSno());
                for(Finish_qd cr:mData){
                    map.put(cr.getCid(),DBUtils.Count_finish(cr.getCid()));
                }
                handler.post(runnableUi);
            }
        }.start();

    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new S_Finish_Qd_Adapter(mData,mContext,map);
            listView.setAdapter(mAdapter);
        }
    };
}
