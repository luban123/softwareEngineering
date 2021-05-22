package com.example.bottomdemo.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.MainActivity;
import com.example.bottomdemo.R;
import com.example.bottomdemo.Student.Student_MainActivity;
import com.example.bottomdemo.utils.DBUtils;


import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;//右上角的注册按钮
    private TextView Return;//找回密码按钮
    private Button btn_login;
    private EditText usr,pwd;
    private String phone,password,part;
    private Spinner spinner;
    private CheckBox autoLogin;
    private User u;
    private Student stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找到按钮
        btn_login = findViewById(R.id.Login_button);
        //找到editText的值
        usr = findViewById(R.id.login_yonghuming);//手机号
        pwd = findViewById(R.id.login_mima);//密码
        autoLogin=(CheckBox)findViewById(R.id.autoLogin);//复选框
        spinner=findViewById(R.id.spinner_login);//列表框
        btn_login.setOnClickListener(this);
        //btn_cancle.setOnClickListener(this);
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击右上角跳转
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Login_button://这个是登录按钮
                part=(String)spinner.getSelectedItem().toString().trim();
                phone = usr.getText().toString().trim();
                password = pwd.getText().toString().trim();
                if (TextUtils.isEmpty(phone)||
                        TextUtils.isEmpty(password))//如果账号密码为空
                {
                    Toast.makeText(LoginActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else{//如果不为空
                    if(part.equals("请选择你的身份"))
                    {
                        Toast.makeText(LoginActivity.this,"请选择你的身份",Toast.LENGTH_SHORT).show();
                    }
                    else if(part.equals("教师"))
                    {
                        u = new User();
                        u.setTphone(phone);
                        u.setTpassword(password);
                        checkLogin(u);
                    }
                    else if(part.equals("学生"))
                    {
                        stu=new Student();
                        stu.setSphone(phone);
                        stu.setSpassword(password);
                        checkLogin_Stu(stu);
                    }

                }

                break;
            //case R.id.btn_cancle://这个是取消按钮
            //    break;
        }
    }
    class DBThread implements Runnable {//教师登录线程
        private User user;
        private Context context;
        public void setUser(User user) {
            this.user = user;
        }
        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            Map<String,String> result= DBUtils.Login(user);
            if(result==null)
            {
                Toast.makeText(LoginActivity.this,"手机号或密码错误",Toast.LENGTH_SHORT).show();
            }
            else if (result != null && result.size() > 0) {
                u.setTname(result.get("tname"));
                u.setTid(result.get("tid"));
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user",u);
                context.startActivity(intent);
            }
            Looper.loop();
        }
    }
    private void checkLogin(User u) {
        DBThread dt = new DBThread();
        dt.setUser(u);
        dt.setContext(this);
        Thread thread = new Thread(dt);
        thread.start();
    }
    class DBThread2 implements Runnable {//学生登录线程

        private Student stu;
        private Context context;
        public void setStu(Student stu) {
            this.stu = stu;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            Map<String,String> result= DBUtils.Stu_LOGIN(stu);
            if(result==null) {
                Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
            } else if (result != null && result.size() > 0) {
                stu.setSname(result.get("sname"));
                stu.setSno(result.get("sno"));
                Intent intent=new Intent(LoginActivity.this, Student_MainActivity.class);
                intent.putExtra("stu",stu);
                context.startActivity(intent);
            }
            Looper.loop();
        }
    }
    private void checkLogin_Stu(Student stu) {//学生登录
        DBThread2 dt2 = new DBThread2();
        dt2.setStu(stu);
        dt2.setContext(this);
        Thread thread2 = new Thread(dt2);
        thread2.start();
    }


}


