package com.example.bottomnavigation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.utils.AppManager;

public class VerifyingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setContentView(R.layout.activity_verifying);
        new Handler().postDelayed(()->{
            startActivity(new Intent(this,LoadingActivity.class));
            finish();
        },100);
    }
}
