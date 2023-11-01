package com.example.bottomnavigation.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.utils.AppManager;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;

import java.util.List;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.activity_loading);
        XXPermissions.with(this).permission(Manifest.permission.CAMERA).request((permissions, allGranted) -> new Handler().postDelayed(()->{
            startActivity(new Intent(LoadingActivity.this,PreviewActivity.class));
            finish();
        },100));

    }
}
