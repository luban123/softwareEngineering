package com.example.bottomdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.Setqd;
import com.example.bottomdemo.login.User;
import com.example.bottomdemo.utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InsertSetQd extends AppCompatActivity implements View.OnClickListener {
    private List<String> eduList = null;
    private ArrayAdapter<String> eduAdapter = null;
    private Spinner eduSpinner= null;
    private Courses courses;
    private Handler handler=null;
    private TextView starttime;
    private EditText timing;
    String starttime_str,timing_str,cname,tid;
    private User user;
    private Button back;
    private String cid=null;
    private Button setqd;//保存
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_qdset);
        eduSpinner=(Spinner)super.findViewById(R.id.eduSpinner);
        handler=new Handler();
        eduSpinner.setPrompt("请选择你的课程");
        eduList=new ArrayList<String>();
        starttime=(TextView)findViewById(R.id.starttime);
        timing=(EditText)findViewById(R.id.timing);
        back=(Button) findViewById(R.id.back8);
        setqd=(Button)findViewById(R.id.setqd);
        back.setOnClickListener(this);
        setqd.setOnClickListener(this);
        Intent getData=getIntent();
        user=(User)getData.getSerializableExtra("usr");
        tid=user.getTid();
        new Thread(){
            public void run(){
                eduList= DBUtils.Select_CourseNameByTid(tid);
                handler.post(runnableUi);
            }
        }.start();
    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            eduAdapter=new ArrayAdapter<String>(InsertSetQd.this,R.layout.support_simple_spinner_dropdown_item,eduList);
            eduAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            eduSpinner.setAdapter(eduAdapter);
        }
    };
    @Override
    public void onClick(View v) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timing_str = timing.getText().toString().trim();
        starttime_str = df.format(new Date());
        cname=(String)eduSpinner.getSelectedItem().toString().trim();
        tid = user.getTid();
        switch (v.getId()) {
            case R.id.setqd://这个是登录按钮
                if (TextUtils.isEmpty(timing_str))
                {
                    Toast.makeText(InsertSetQd.this,"签到信息不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog dialog=new AlertDialog.Builder(InsertSetQd.this)
                            .setTitle("是否保存")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    checkLogin(cname);
                                }
                            })
                            .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create();
                    dialog.show();
                 }
                break;
            case R.id.back8:
                finish();
                break;
        }
    }
    class DBThread8 implements Runnable{
        private String cname;
        private Context context;

        public void setCourses(String cname) {
            this.cname = cname;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            Map<String,String> result1= DBUtils.Select_isSetqd(cname);
            if(result1!=null&& result1.size() > 0)
            {
                Toast.makeText(InsertSetQd.this,"该课程签到已存在,添加失败",Toast.LENGTH_SHORT).show();
            }
            else if(result1==null)
            {
                cid=DBUtils.cnameBycid(cname);
                Intent intent=new Intent(InsertSetQd.this, T_setqddd.class);
                intent.putExtra("cname",cname);
                intent.putExtra("cid",cid);
                intent.putExtra("starttime",starttime_str);
                intent.putExtra("timing",timing_str);
                context.startActivity(intent);
                finish();
            }
            Looper.loop();
        }
    }
    private void checkLogin(String cname) {
        DBThread8 dt8 = new DBThread8();
        dt8.setCourses(cname);
        dt8.setContext(this);
        Thread thread8 = new Thread(dt8);
        thread8.start();
    }


}
