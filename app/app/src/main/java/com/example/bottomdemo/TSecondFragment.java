package com.example.bottomdemo;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomdemo.login.LoginActivity;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.User;

public class TSecondFragment extends Fragment {
    View view;
    private TSecondViewModel mViewModel;
    private Button Exit;
    private User user;
    private TextView usertext;
    public static TSecondFragment newInstance() {
        return new TSecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.t_second_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TSecondViewModel.class);
        // TODO: Use the ViewModel
        Exit=(Button)getActivity().findViewById(R.id.exit);
        usertext=(TextView)getActivity().findViewById(R.id.usertext);
        Intent getData=getActivity().getIntent();//接收参数
        user=(User) getData.getSerializableExtra("user");
        System.out.println(user.getTname());
        usertext.setText("你好"+user.getTname());
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog=new AlertDialog.Builder(getActivity())
                        .setTitle("是否确认退出")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getActivity(),LoginActivity.class);
                                startActivity(intent);
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
        });

    }

}
