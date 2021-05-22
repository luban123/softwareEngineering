package com.example.bottomdemo.Student;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bottomdemo.CourseActivity;
import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Student;

public class SFirstFragment extends Fragment {

    private SFirstViewModel mViewModel;
    private ImageView s_choice_course;//已选课程
    private ImageView s_join_course;
    private ImageView s_wait_qd;
    private ImageView s_have_qd;
    private Student stu;

    public static SFirstFragment newInstance() {
        return new SFirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.s_first_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SFirstViewModel.class);
        // TODO: Use the ViewModel
        Intent getData=getActivity().getIntent();
        stu=(Student) getData.getSerializableExtra("stu");
        s_choice_course=(ImageView) getActivity().findViewById(R.id.s_choice_course);
        s_choice_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), S_Have_CourseActivity.class);
                intent.putExtra("stu",stu);
                startActivity(intent);
            }
        });
        s_join_course=(ImageView)getActivity().findViewById(R.id.s_join_course);
        s_join_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), S_join_CourseActivity.class);
                intent.putExtra("stu",stu);
                startActivity(intent);
            }
        });
        s_wait_qd=(ImageView)getActivity().findViewById(R.id.s_wait_qd);
        s_wait_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), S_Wait_Qd_CourseActivity.class);
                intent.putExtra("stu",stu);
                startActivity(intent);
            }
        });

        s_have_qd=(ImageView)getActivity().findViewById(R.id.s_have_qd);
        s_have_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), S_Finish_Qd_CourseActivity.class);
                intent.putExtra("stu",stu);
                startActivity(intent);
            }
        });

    }

}
