package com.example.bottomdemo.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.User;

public class S_wait_qd_FaceActivity extends AppCompatActivity {
    private Button FaceButton;
    private Button back;
    private Finish_qd finish_qd;
    private Student stu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wait_qd_face);
        FaceButton=(Button)findViewById(R.id.face_button);
        back=findViewById(R.id.back16);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        finish_qd=(Finish_qd)getData.getSerializableExtra("finishqd");
        FaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(S_wait_qd_FaceActivity.this, S_success_qdActivity.class);
                intent.putExtra("finishqd",finish_qd);
                intent.putExtra("stu",stu);
                intent.putExtra("address","");
                startActivity(intent);
            }
        });
    }
}
