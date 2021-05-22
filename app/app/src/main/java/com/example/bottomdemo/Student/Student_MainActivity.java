package com.example.bottomdemo.Student;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bottomdemo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Student_MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView2);
        NavController navController= Navigation.findNavController(this,R.id.fragment2);
        AppBarConfiguration configuration=new AppBarConfiguration.Builder(R.id.s_firstFragment,R.id.s_secondFragment).build();
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }
}
