package com.example.bottomdemo.Student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.bottomdemo.R;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Setqd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.utils.DBUtils;

import java.text.SimpleDateFormat;

public class S_setqddd extends AppCompatActivity implements View.OnClickListener {
    private TextView locationInfoTextView = null;
    private Button startButton = null;
    private LocationClient locationClient = null;
    private static final int UPDATE_TIME = 10000;
    public LocationClient mLocationClient = null;
    public MyLocationListener myListener = new MyLocationListener();
    private MapView mMapView=null;
    public BaiduMap bMap;
    public boolean istrue=true;
    private Finish_qd finish_qd;
    private Student stu;
    private Button back, setqddd;
    private String cid, continue_time, lng, lat, cname,address;
    private String starttime;
    private Setqd setqd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.s_dingwei_set);
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        finish_qd=(Finish_qd)getData.getSerializableExtra("finishqd");
        back = (Button) findViewById(R.id.back18);
        back.setOnClickListener(this);
        setqddd = (Button) findViewById(R.id.setqddd2);
        setqddd.setOnClickListener(this);
        mMapView=(MapView)findViewById(R.id.mmapView2);
        bMap=mMapView.getMap();
        //定位开启
        bMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        locationInfoTextView = (TextView) this.findViewById(R.id.text3);
        startButton = (Button) this.findViewById(R.id.btn_start2);
        locationClient = new LocationClient(this);
        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedAddress(true);//获取位置
        option.setIsNeedLocationDescribe(true);
        option.setCoorType("bd09ll");

        option.setPriority(LocationClientOption.NetWorkFirst);
        //设置定位优先级
        option.setProdName("LocationDemo");
        //设置产品线名称
        option.setScanSpan(UPDATE_TIME);
        //设置定时定位的时间间隔。单位毫秒
        locationClient.setLocOption(option);
//注册位置监听器
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                int tag=2;
                //lng=location.getAddrStr();
                if (location == null) {
                    return;
                }
                StringBuffer sb = new StringBuffer(256);

                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {

                    for (int i = 0; i < 1; i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append("定位:");
                        sb.append(poi.getName() + "\n");
                    }
                }

                if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    sb.append("\n地址 : ");

                    sb.append(location.getAddrStr());
                    address=location.getAddrStr();


                }
                locationInfoTextView.setText(sb.toString());
            }

        });
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println(locationInfoTextView.getText().toString());
                if (locationClient == null) {
                    return;
                }
                if (locationClient.isStarted()) {
                    startButton.setText("定位");
                    locationClient.stop();
                }
                else {
                    startButton.setText("停止");
                    locationClient.start();
                    locationClient.requestLocation();

                }
            }
        });
        initLocationOption();
        mLocationClient.start();//开启
    }
    private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
        LocationClient locationClient = new LocationClient(getApplicationContext());
//声明LocationClient类实例并配置定位参数
        LocationClientOption locationOption = new LocationClientOption();
        MyLocationListener myLocationListener = new MyLocationListener();
//注册监听函数
        locationClient.registerLocationListener(myLocationListener);
//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        locationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationOption.setCoorType("BD09ll");
//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        locationOption.setScanSpan(1000);
//可选，设置是否需要地址信息，默认不需要
        locationOption.setIsNeedAddress(true);
//可选，设置是否需要地址描述
        locationOption.setIsNeedLocationDescribe(true);
//可选，设置是否需要设备方向结果
        locationOption.setNeedDeviceDirect(false);
//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        locationOption.setLocationNotify(true);
//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        locationOption.setIgnoreKillProcess(true);
//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationOption.setIsNeedLocationDescribe(true);
//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationOption.setIsNeedLocationPoiList(true);
//可选，默认false，设置是否收集CRASH信息，默认收集
        locationOption.SetIgnoreCacheException(false);
//可选，默认false，设置是否开启Gps定位
        locationOption.setOpenGps(true);
//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        locationOption.setIsNeedAltitude(false);
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        locationOption.setOpenAutoNotifyMode();
//设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        //locationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        locationOption.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        locationClient.setLocOption(locationOption);

//开始定位

        //配置定位地图现实的方式 现在是罗盘形式展示
        bMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS,true,null));

        locationClient.start();
    }

    class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明


            if (mMapView == null || location == null) {
                return;
            }

            //定位数据
            MyLocationData data = new MyLocationData.Builder().accuracy(location.getRadius())
                    .direction(50)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            //把定位的数据加到百度地图上
            bMap.setMyLocationData(data);
            if (istrue) {
                istrue = false;
                //地图的状态
                MapStatus.Builder status = new MapStatus.Builder();
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                //设置缩放的等级和中心点
                status.zoom(19).target(ll);
                //地图改变的是的状态的动画
                bMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(status.build()));

            }


        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setqddd2:
                Intent intent=new Intent(S_setqddd.this, S_success_qdActivity.class);
                intent.putExtra("stu",stu);
                intent.putExtra("finishqd",finish_qd);
                intent.putExtra("address",address);
                startActivity(intent);
                finish();
                break;
            case R.id.back18:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stop();
            locationClient = null;
        }

        mMapView.onDestroy();
    }

}
