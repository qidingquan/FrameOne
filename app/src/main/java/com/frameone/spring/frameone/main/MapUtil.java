package com.frameone.spring.frameone.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

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
    /**
     * 调用百度地图导航
     *
     * @param context    上下文
     * @param coord_type coord_type 可选 坐标类型，可选参数，默认为bd09经纬度坐标
     * @param src        必选 调用来源，规则：companyName|appName。
     * @param location   经纬度 例如：39.9761,116.3282
     */
    private void baiduNavi(Context context, String coord_type, @NonNull String src, @NonNull String location) {
        StringBuffer stringBuffer = new StringBuffer("baidumap://map/navi?");
        if (!TextUtils.isEmpty(coord_type)) {
            stringBuffer.append("coord_type=").append(coord_type);
        }
        stringBuffer.append("&src=").append(src);
        stringBuffer.append("&location=").append(location);
        Intent intent = new Intent();
        intent.setData(Uri.parse(stringBuffer.toString()));
        context.startActivity(intent);
    }

    /**
     * 启动高德App进行导航
     *
     * @param sourceApplication 必填 第三方调用应用名称。如 amap
     * @param poiname           非必填 POI 名称
     * @param lat               必填 纬度
     * @param lon               必填 经度
     * @param dev               必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * @param style             必填 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
     */
    public static void gaodeNavi(Context context, String sourceApplication, String poiname, String lat, String lon, String dev, String style) {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append(sourceApplication);
        if (!TextUtils.isEmpty(poiname)) {
            stringBuffer.append("&poiname=").append(poiname);
        }
        stringBuffer.append("&lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&dev=").append(dev)
                .append("&style=").append(style);

        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }
}
