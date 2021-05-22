package com.example.bottomdemo.Student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.MainActivity;
import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.utils.DBUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class S_success_qdActivity extends AppCompatActivity {
    private Button qd_success,dingweibtn;
    private Button back;
    private Finish_qd finish_qd;
    private Student stu;
    private TextView sname4,cname_15,start_time3,location;
    private static final int CHANGE_TEXT=1;
    private Handler handler2=null;
    private String date_str,location_str;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case CHANGE_TEXT:
                    Intent intent=new Intent(S_success_qdActivity.this, Student_MainActivity.class);
                    intent.putExtra("stu",stu);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_qd_info);
        sname4=(TextView)findViewById(R.id.sname4);
        cname_15=(TextView)findViewById(R.id.cname_15);
        start_time3=(TextView)findViewById(R.id.start_time3);
        location=(TextView)findViewById(R.id.location);
        qd_success=(Button)findViewById(R.id.qd_success);
        dingweibtn=(Button)findViewById(R.id.dingweibtn);
        back=findViewById(R.id.back17);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        finish_qd=(Finish_qd)getData.getSerializableExtra("finishqd");
        location_str=(String) getData.getSerializableExtra("address");
        if(!location_str.equals(""))
        {
            location.setText(location_str);
            location.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            location.setSingleLine(true);
            location.setSelected(true);
            location.setFocusable(true);
            location.setFocusableInTouchMode(true);
        }
        sname4.setText(stu.getSname());
        cname_15.setText(finish_qd.getCname());
        Date date=finish_qd.getQtime();

        if(date!=null)
        {
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date_str=format.format(date);
            start_time3.setText("当前时间");
        }

        dingweibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(S_success_qdActivity.this, S_setqddd.class);
                intent.putExtra("stu",stu);
                intent.putExtra("finishqd",finish_qd);
                startActivity(intent);
                finish();
            }
        });


        qd_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        if(location.getText().equals(""))
                        {
                            Toast.makeText(S_success_qdActivity.this, "请设置签到地点", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            DBUtils.Update_QD_info(stu.getSno(), date_str, finish_qd.getCid(),location_str);
                            Message message = new Message();
                            message.what = CHANGE_TEXT;
                            handler.sendMessage(message);
                            Toast.makeText(S_success_qdActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                }).start();

            }
        });

    }
}
