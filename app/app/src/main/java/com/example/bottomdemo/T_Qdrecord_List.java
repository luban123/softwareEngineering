package com.example.bottomdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.QdrecordistAdapter;
import com.example.bottomdemo.Adapter.StudentListAdapter;
import com.example.bottomdemo.login.Qdrecord;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.utils.DBUtils;

import java.util.List;

public class T_Qdrecord_List extends AppCompatActivity {
    private List<Qdrecord> mData2=null;
    private Context mContext;
    private QdrecordistAdapter mAdapter=null;
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
        setContentView(R.layout.viewqdrecordlists);
        mContext=this;
        listView=(ListView)findViewById(R.id.view_qdrecord_list);
        cname_text=(TextView) findViewById(R.id.cname_5);
        back=findViewById(R.id.back6);
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
                mData2=DBUtils.Select_QdrecordByCid(cid);
                handler.post(runnableUi);
            }
        }.start();
    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new QdrecordistAdapter(mContext,mData2);
            listView.setAdapter(mAdapter);
        }
    };
}
