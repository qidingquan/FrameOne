package com.frameone.spring.frameone.main;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.frameone.spring.frameone.R;
import com.frameone.spring.frameone.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页
 */
public class MainActivity extends BaseActivity implements AMapLocationListener,MainContract.View {


    @BindView(R.id.map_view)
    MapView mMapView;

    private AMap aMap;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;
    private Marker screenMarker = null;//屏幕中间的位置
    private ArrayList<MarkerOptions> markerOptionsList = new ArrayList<>();
    MainContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView.onCreate(savedInstanceState);
        initMap();
        presenter=new MainPresenter();
        presenter.leaseStatus();
        presenter.historyTrack();
    }

    private void initMap() {
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MapUtil.setUpMap(aMap);
        MapUtil.setupLocationStyle(aMap);
        //地图加载完成后在屏幕中央添加标识
        aMap.setOnMapLoadedListener(() -> MapUtil.addMarkerInScreenCenter(aMap, R.drawable.purple_pin));
        // 设置可视范围变化时的回调的接口方法
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition postion) {
                //将所有marker显示在屏幕上
              /*  MarkerOptions options=new MarkerOptions();
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
                options.position(postion.target);
                markerOptionsList.add(options);
                aMap.addMarkers(markerOptionsList,true);*/
            }
        });
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        MapUtil.initLocation(this, mLocationClient);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                //首次定位,选择移动到地图中心点并修改级别到15级
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude()
                        , aMapLocation.getLongitude()), 15));
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
