package com.example.bottomdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class T_choice_student extends AppCompatActivity implements View.OnClickListener {
    private ImageView view_studentlist;
    private ImageView view_choicestudent;
    private Button back;
    private String tid;
    private String cid;
    private String cname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t_choice_course);
        view_studentlist=(ImageView)findViewById(R.id.view_studentlist);
        view_choicestudent=(ImageView)findViewById(R.id.view_choicestudent);
        back=(Button)findViewById(R.id.back10);
        Intent getData=getIntent();
        tid=(String)getData.getSerializableExtra("tid");
        cid=(String)getData.getSerializableExtra("cid");
        cname=(String) getData.getSerializableExtra("cname");
        view_choicestudent.setOnClickListener(this);
        view_studentlist.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_choicestudent:
                Intent intent1=new Intent(T_choice_student.this, T_Student_Choice_List.class);
                intent1.putExtra("tid",tid);
                intent1.putExtra("cid",cid);
                intent1.putExtra("cname",cname);
                startActivity(intent1);
                break;
            case R.id.view_studentlist:
                Intent intent=new Intent(T_choice_student.this, T_Student_List.class);
                intent.putExtra("tid",tid);
                intent.putExtra("cid",cid);
                intent.putExtra("cname",cname);
                startActivity(intent);
                break;
            case R.id.back10:
                finish();
                break;

        }
    }
}
