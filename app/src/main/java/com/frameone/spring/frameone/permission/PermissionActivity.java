package com.frameone.spring.frameone.permission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.frameone.spring.frameone.R;
import com.tbruyelle.rxpermissions.RxPermissions;

/**
 * Created by Administrator on 2018/1/4.
 * 6.0以上动态权限设置
 */

public class PermissionActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 100;
    private static final int CALL_PHONE = 111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

    }

    @SuppressLint("MissingPermission")
    public void callPhone() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "10086");
            intent.setData(data);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void call(View view) {
        rxPermission();
//        commonPermission();
    }

    private void rxPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.CALL_PHONE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        callPhone();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Toast.makeText(PermissionActivity.this, "没有了权限", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PermissionActivity.this, "拒绝了权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void commonPermission() {
        if (selfPermissionGranted(this, Manifest.permission.CALL_PHONE)) {
            callPhone();
        } else {
            Toast.makeText(PermissionActivity.this, "没有权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {

                    Toast.makeText(PermissionActivity.this, "点击权限，并打开全部权限", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            } else {
                // Permission Denied
                Toast.makeText(PermissionActivity.this, "没有权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public boolean selfPermissionGranted(Context context, String permission) {

        boolean ret = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.M) {
                ret = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                ret = PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
        }
        return ret;
    }
}
