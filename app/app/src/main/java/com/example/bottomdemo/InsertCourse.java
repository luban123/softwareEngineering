package com.example.bottomdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.LoginActivity;
import com.example.bottomdemo.login.Teacher_main;
import com.example.bottomdemo.login.User;
import com.example.bottomdemo.utils.DBUtils;

import java.util.Map;

public class InsertCourse extends AppCompatActivity implements View.OnClickListener{
    private TextView tname;
    private EditText cid,cname;
    String cid_str,cname_str,tid;
    private User user;
    private Courses courses;
    private Button back;
    private TextView save;//保存
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_course);
        tname=findViewById(R.id.t_name);
        cid=findViewById(R.id.cid);
        cname=findViewById(R.id.cname);
        save=findViewById(R.id.save);
        back=findViewById(R.id.back);
        save.setOnClickListener(this);
        back.setOnClickListener(this);
        Intent getData=getIntent();
        user=(User)getData.getSerializableExtra("usr");
        tname.setText(user.getTname());
    }

    @Override
    public void onClick(View v) {
        cid_str = cid.getText().toString().trim();
        cname_str = cname.getText().toString().trim();
        tid = user.getTid();
        switch (v.getId()) {
            case R.id.save://这个是登录按钮
                if (TextUtils.isEmpty(cid_str)||
                        TextUtils.isEmpty(cname_str))
                {
                    Toast.makeText(InsertCourse.this,"课程信息不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog dialog=new AlertDialog.Builder(InsertCourse.this)
                            .setTitle("是否保存")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    courses=new Courses();
                                    courses.setCid(cid_str);
                                    courses.setCname(cname_str);
                                    courses.setTid(tid);
                                    checkLogin(courses);
                                    finish();
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
            case R.id.back:
                finish();
                break;
        }
    }
    class DBThread5 implements Runnable{
        private Courses courses;
        private Context context;

        public void setCourses(Courses courses) {
            this.courses = courses;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            Map<String,String> result1= DBUtils.Select_Course(courses);
            if(result1!=null&& result1.size() > 0)
            {
                Toast.makeText(InsertCourse.this,"该课程号已存在,添加失败",Toast.LENGTH_SHORT).show();
            }
            else if(result1==null)
            {
                DBUtils.Insert_Course(courses);
                Toast.makeText(InsertCourse.this,"添加成功",Toast.LENGTH_SHORT).show();

            }
            Looper.loop();

        }
    }
    private void checkLogin(Courses courses) {
        DBThread5 dt5 = new DBThread5();
        dt5.setCourses(courses);
        dt5.setContext(this);
        Thread thread5 = new Thread(dt5);
        thread5.start();
    }
}
