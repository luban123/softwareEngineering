package com.example.bottomdemo;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bottomdemo.login.LoginActivity;
import com.example.bottomdemo.login.RegisterActivity;
import com.example.bottomdemo.login.User;

public class TFirstFragment extends Fragment {
    private TFirstViewModel mViewModel;
    private ImageView insert_course;//添加课程
    private ImageView have_course;
    private ImageView qdxq;
    private ImageView qd_set;
    private User user;//教师
    public static TFirstFragment newInstance() {
        return new TFirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.t_first_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //接收参数
        Intent getData=getActivity().getIntent();
        user=(User) getData.getSerializableExtra("user");
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TFirstViewModel.class);
        // TODO: Use the ViewModel
        insert_course=(ImageView) getActivity().findViewById(R.id.insert_course);
        insert_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),InsertCourse.class);
                intent.putExtra("usr",user);
                startActivity(intent);
            }
        });
        have_course=(ImageView)getActivity().findViewById(R.id.have_course);
        have_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CourseActivity.class);
                intent.putExtra("usr",user);
                startActivity(intent);
            }
        });
        qdxq=(ImageView)getActivity().findViewById(R.id.qdxq_button);
        qdxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),QdxqActivity.class);
                intent.putExtra("usr",user);
                startActivity(intent);
            }
        });
        qd_set=(ImageView)getActivity().findViewById(R.id.qd_set);
        qd_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),InsertSetQd.class);
                intent.putExtra("usr",user);
                startActivity(intent);
            }
        });
    }
}
