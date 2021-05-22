package com.example.bottomdemo.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.R;
import com.example.bottomdemo.utils.Utils;


public class infoActivity extends AppCompatActivity {
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv1=findViewById(R.id.info_username);
        tv2=findViewById(R.id.info_password);

        Intent getData=getIntent();
        //User user = (User)getData.getSerializableExtra("user");
        Student stu=(Student)getData.getSerializableExtra("user");
        tv1.setText("用户名为：" +stu.getSname());
        tv2.setText("密码为："+   Utils.getPwd(stu.getSpassword()));

    }
}