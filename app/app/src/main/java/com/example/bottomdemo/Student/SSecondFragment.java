package com.example.bottomdemo.Student;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.VersionInfo;
import com.arcsoft.face.enums.RuntimeABI;
import com.example.bottomdemo.Face.ChooseFunctionActivity;
import com.example.bottomdemo.Face.FaceManageActivity;
import com.example.bottomdemo.Face.common.Constants;
import com.example.bottomdemo.Face.fragment.ChooseDetectDegreeDialog;
import com.example.bottomdemo.R;
import com.example.bottomdemo.login.LoginActivity;
import com.example.bottomdemo.login.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SSecondFragment extends Fragment {

    private SSecondViewModel mViewModel;
    private Button Exit,jumpToBatchRegisterActivity2;
    private Student stu;
    private TextView stutext; private static final String TAG = "ChooseFunctionActivity";
    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;
    // 在线激活所需的权限
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };
    boolean libraryExists = true;
    // Demo 所需的动态库文件
    private static final String[] LIBRARIES = new String[]{
            // 人脸相关
            "libarcsoft_face_engine.so",
            "libarcsoft_face.so",
            // 图像库相关
            "libarcsoft_image_util.so",
    };
    // 修改配置项的对话框
    ChooseDetectDegreeDialog chooseDetectDegreeDialog;

    public static SSecondFragment newInstance() {
        return new SSecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.s_second_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SSecondViewModel.class);
        // TODO: Use the ViewModel

        Exit=(Button)getActivity().findViewById(R.id.exit2);
        stutext=(TextView)getActivity().findViewById(R.id.studenttext);
        Intent getData=getActivity().getIntent();//接收参数
        stu=(Student) getData.getSerializableExtra("stu");
        System.out.println(stu.getSname());
        stutext.setText("你好"+stu.getSname());
        jumpToBatchRegisterActivity2=(Button)getActivity().findViewById(R.id.jumpToBatchRegisterActivity2);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog=new AlertDialog.Builder(getActivity())
                        .setTitle("是否确认退出")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getActivity(), LoginActivity.class);
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
        jumpToBatchRegisterActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLibraryAndJump(FaceManageActivity.class);
            }
        });

        libraryExists = checkSoFile(LIBRARIES);
        ApplicationInfo applicationInfo = getActivity().getApplicationInfo();
        Log.i(TAG, "onCreate: " + applicationInfo.nativeLibraryDir);
        if (!libraryExists) {
            Toast.makeText(getActivity(),getString(R.string.library_not_found), Toast.LENGTH_SHORT).show();

        }else {
            VersionInfo versionInfo = new VersionInfo();
            int code = FaceEngine.getVersion(versionInfo);
            Log.i(TAG, "onCreate: getVersion, code is: " + code + ", versionInfo is: " + versionInfo);
        }

    }
    private boolean checkSoFile(String[] libraries) {
        File dir = new File(getActivity().getApplicationInfo().nativeLibraryDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        List<String> libraryNameList = new ArrayList<>();
        for (File file : files) {
            libraryNameList.add(file.getName());
        }
        boolean exists = true;
        for (String library : libraries) {
            exists &= libraryNameList.contains(library);
        }
        return exists;
    }


    /**
     * 批量注册和删除功能
     *
     * @param view
     */

    /**
     * 激活引擎
     *
     * @param view
     */
    public void activeEngine(final View view) {
        if (!libraryExists) {
            Toast.makeText(getActivity(),getString(R.string.library_not_found), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkPermissions(NEEDED_PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), NEEDED_PERMISSIONS, ACTION_REQUEST_PERMISSIONS);
            return;
        }
        if (view != null) {
            view.setClickable(false);
        }
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) {
                RuntimeABI runtimeABI = FaceEngine.getRuntimeABI();
                Log.i(TAG, "subscribe: getRuntimeABI() " + runtimeABI);

                long start = System.currentTimeMillis();
                int activeCode = FaceEngine.activeOnline(getActivity(), Constants.APP_ID, Constants.SDK_KEY);
                Log.i(TAG, "subscribe cost: " + (System.currentTimeMillis() - start));
                emitter.onNext(activeCode);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer activeCode) {
                        if (activeCode == ErrorInfo.MOK) {
                            Toast.makeText(getActivity(),getString(R.string.active_success), Toast.LENGTH_SHORT).show();

                        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
                            Toast.makeText(getActivity(),getString(R.string.already_activated), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(),getString(R.string.active_failed, activeCode), Toast.LENGTH_SHORT).show();
                        }

                        if (view != null) {
                            view.setClickable(true);
                        }
                        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
                        int res = FaceEngine.getActiveFileInfo(getActivity(), activeFileInfo);
                        if (res == ErrorInfo.MOK) {
                            Log.i(TAG, activeFileInfo.toString());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(),e.getMessage(), Toast.LENGTH_SHORT).show();
                        if (view != null) {
                            view.setClickable(true);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    void checkLibraryAndJump(Class activityClass) {
        if (!libraryExists) {
            Toast.makeText(getActivity(), getString(R.string.library_not_found), Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(getActivity(), activityClass));
    }


    protected boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(getActivity(), neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }




}
