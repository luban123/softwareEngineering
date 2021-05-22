package com.example.bottomdemo.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.bottomdemo.Face.InitActivity2;
import com.example.bottomdemo.R;
import com.example.bottomdemo.utils.DBUtils;

import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView login;//右上角的登录按钮
    private Button register;//注册按钮
    private EditText register_account;//手机号
    private EditText register_password;//密码
    private EditText register_password_again;//确认密码
    private EditText register_id;//学号
    private EditText yanzhengma;//验证码
    private EditText register_name;//姓名
    private Button yzm_btn;
    private int q,b,g,s;
    private Student stu;
    String register_accountStr,register_passwordStr,register_passwordStr_again,
            register_idStr,regiest_yanzheng,yzm_btnn,register_nameStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //验证码
        int sum_1=0;
        q=(int)(Math.random()*9);
        b=(int)(Math.random()*9);
        g=(int)(Math.random()*9);
        s=(int)(Math.random()*9);
        sum_1=q*1000+b*100+g*10+s;

        //找到按钮
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击右上角跳转
                Intent intent=new Intent();
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        //找到editText的值
        register=findViewById(R.id.register_button);
        register_account=(EditText)findViewById(R.id.register_account);
        register_password=(EditText)findViewById(R.id.register_mima);
        register_password_again=(EditText)findViewById(R.id.register_mima_again);
        register_id=(EditText)findViewById(R.id.register_id);
        register_name=(EditText)findViewById(R.id.register_name);
        yanzhengma=(EditText)findViewById(R.id.yanzheng);
        yzm_btn=(Button)findViewById(R.id.yzm);
        yzm_btn.setText(sum_1+"");
        yzm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum=0;
                q=(int)(Math.random()*9);
                b=(int)(Math.random()*9);
                g=(int)(Math.random()*9);
                s=(int)(Math.random()*9);
                sum=q*1000+b*100+g*10+s;
                yzm_btn.setText(sum+"");
            }
        });
        register.setOnClickListener(this);
        //btn_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        register_accountStr=register_account.getText().toString();
        register_passwordStr=register_password.getText().toString();
        register_passwordStr_again=register_password_again.getText().toString();
        register_idStr=register_id.getText().toString();
        regiest_yanzheng=yanzhengma.getText().toString();
        yzm_btnn=yzm_btn.getText().toString();
        register_nameStr=register_name.getText().toString();

        switch (view.getId()){
            case R.id.register_button://这个是注册按钮
                if (TextUtils.isEmpty(register_accountStr)||
                    TextUtils.isEmpty(register_passwordStr)||
                    TextUtils.isEmpty(register_passwordStr_again)||
                    TextUtils.isEmpty(register_idStr)||
                    TextUtils.isEmpty(regiest_yanzheng))
                {
                    Toast.makeText(RegisterActivity.this,"注册信息不能为空",Toast.LENGTH_SHORT).show();
                }
                if(!register_passwordStr.equals(register_passwordStr_again))
                {
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!regiest_yanzheng.equals(yzm_btnn))
                {
                    Toast.makeText(RegisterActivity.this,"验证码不一致",Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                else{//如果不为空
                    stu = new Student();
                    stu.setSname(register_nameStr);
                    stu.setSno(register_idStr);
                    stu.setSphone(register_accountStr);
                    stu.setSpassword(register_passwordStr);
                    checkLogin(stu);
                }

                break;
            //case R.id.btn_cancle://这个是取消按钮
            //    break;
        }


    }

    class DBThread3 implements Runnable {//线程
        private Student stu;
        private Context context;
        public void setUser(Student stu) {
            this.stu = stu;
        }
        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            /*Map<String,String> result= DBUtils.Login(user);
            if(result==null)
            {
                Toast.makeText(RegisterActivity.this,"手机号或密码错误",Toast.LENGTH_SHORT).show();
            }
            else if (result != null && result.size() > 0) {

             */
                Map<String,String> result1= DBUtils.Register_Phone(stu);
                Map<String,String> result2=DBUtils.Register_id(stu);
                if(result1 != null && result1.size() > 0)
                {
                    Toast.makeText(RegisterActivity.this,"手机号已经存在",Toast.LENGTH_SHORT).show();
                }
                else if(result2!= null && result2.size() > 0)
                {
                    Toast.makeText(RegisterActivity.this,"学号已经存在",Toast.LENGTH_SHORT).show();
                }

                else if(result1==null&&result2==null)
                {

                        //DBUtils.Register(stu);
                        Toast.makeText(RegisterActivity.this, "请进行人脸注册", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, InitActivity2.class);
                        intent.putExtra("stu",stu);
                        context.startActivity(intent);

                }

                /*
            }

                 */
            Looper.loop();
        }
    }
    private void checkLogin(Student u) {
        DBThread3 dt3 = new DBThread3();
        dt3.setUser(u);
        dt3.setContext(this);
        Thread thread3 = new Thread(dt3);
        thread3.start();
    }
}
