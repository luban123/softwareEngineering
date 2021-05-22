package com.example.bottomdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.StudentListAdapter;
import com.example.bottomdemo.Adapter.Student_Choice_ListAdapter;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class T_Student_Choice_List extends AppCompatActivity {
    private List<Student> mData=null;
    private Context mContext;
    private Student_Choice_ListAdapter mAdapter=null;
    private ListView listView;
    private Button back;
    private TextView cname_text;
    private Handler handler=null;
    private String tid;
    private String cid;
    private String cname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_choicestudentlists);
        mContext=this;
        listView=(ListView)findViewById(R.id.choice_student_list);
        cname_text=(TextView) findViewById(R.id.cname_9);
        back=findViewById(R.id.back11);
        Intent getData=getIntent();
        tid=(String)getData.getSerializableExtra("tid");
        cid=(String)getData.getSerializableExtra("cid");
        cname=(String) getData.getSerializableExtra("cname");
        cname_text.setText(cname);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler=new Handler();

        new Thread(){
            public void run(){
                mData= DBUtils.Select_Choice_StudentByTidAndCid(tid,cid);
                handler.post(runnableUi);
            }
        }.start();
    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new Student_Choice_ListAdapter(mData,mContext,cid,tid);
            listView.setAdapter(mAdapter);
        }
    };
}

