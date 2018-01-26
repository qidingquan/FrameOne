package com.frameone.spring.frameone.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.frameone.spring.frameone.R;

/**
 * Created by Administrator on 2018/1/5.
 */

public class MapUtil {
    /**
     * 设置一些amap的属性
     */
    public static void setUpMap(AMap aMap) {
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    /**
     * 设置自定义定位蓝点
     */
    public static void setupLocationStyle(AMap aMap) {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.mipmap.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.BLUE);
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(1);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.parseColor("#33ff0000"));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
    }

    /**
     * 初始化定位相关参数
     *
     * @param context
     * @param mLocationClient
     */
    public static void initLocation(Context context, AMapLocationClient mLocationClient) {
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置只定位一次
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    /**
     * 在屏幕中心添加一个Marker
     */
    public static Marker addMarkerInScreenCenter(AMap aMap,int markerRes) {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        Marker screenMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(markerRes)));
        //设置Marker在屏幕上,不跟随地图移动
        screenMarker.setPositionByPixels(screenPosition.x, screenPosition.y);
        return screenMarker;
    }

    /**
     * 获取marker配置参数
     *
     * @param latLng    经纬度
     * @param markerRes marker图标资源
     * @return
     */
    public static MarkerOptions getMarkerOption( LatLng latLng, int markerRes) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.fromResource(markerRes));
        return options;
    }
}
