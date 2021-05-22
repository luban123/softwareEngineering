package com.example.bottomdemo.Student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.CourseAdapter;
import com.example.bottomdemo.Adapter.S_Join_CourseAdapter;
import com.example.bottomdemo.InsertCourse;
import com.example.bottomdemo.R;
import com.example.bottomdemo.T_choice_student;
import com.example.bottomdemo.login.Courses;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.Student_info;
import com.example.bottomdemo.login.User;
import com.example.bottomdemo.utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S_join_CourseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<Student_info> mData=null;
    private Context mContext;
    private S_Join_CourseAdapter mAdapter=null;
    private static final int CHANGE_TEXT=1;
    private ListView listView;
    private Button back,apply;
    private Student stu;
    private Student_info student_info;
    private Handler handler=null;
    String sno,sname;
    Map map;
    Map map2;
    private Handler handler2=new Handler()
    {
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case CHANGE_TEXT:
                    mAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_join_course_list);
        mContext=this;
        listView=(ListView)findViewById(R.id.join_courses);
        listView.setOnItemClickListener(this);
        back=findViewById(R.id.back13);
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        sno=stu.getSno();
        sname=stu.getSname();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        apply=findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(S_join_CourseActivity.this, S_Handle_join_CourseActivity.class);
                intent.putExtra("stu",stu);
                startActivity(intent);
            }
        });
        handler=new Handler();
        new Thread(){
            public void run(){
                map=new HashMap();
                mData= DBUtils.SelectJoinCourse(sno);
                for(Student_info cr:mData){
                map.put(cr.getCid(),DBUtils.Count_People(cr.getCid()));
            }
                handler.post(runnableUi);
            }
        }.start();

    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new S_Join_CourseAdapter(mData,mContext,map);
            listView.setAdapter(mAdapter);
        }
    };
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog dialog=new AlertDialog.Builder(S_join_CourseActivity.this)
                .setTitle("是否加入该课程")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                student_info=new Student_info();
                                student_info.setSno(sno);
                                student_info.setCid(mData.get(position).getCid());
                                student_info.setTid(mData.get(position).getTid());
                                student_info.setCname(mData.get(position).getCname());
                                student_info.setTname(mData.get(position).getTname());
                                DBUtils.join_Insert_Course(student_info);
                                mData.remove(position);
                                Message message=new Message();
                                message.what=CHANGE_TEXT;
                                handler2.sendMessage(message);
                            }

                        }).start();
                        Toast.makeText(S_join_CourseActivity.this,"申请成功",Toast.LENGTH_SHORT).show();
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
}
