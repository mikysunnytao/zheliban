package com.example.bottomnavigation.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivitySplashBinding;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

import tech.gujin.toast.ToastUtil;


public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
//        setContentView(R.layout.activity_splash);
        initView();
    }

    int seconds = 3;

    protected void initView() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0) {
                    cancel();
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();
                } else {
                    seconds--;
                    binding.tvTime.setText("跳过"+seconds+"S");
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
        binding.tvTime.setOnClickListener(v->{
            task.cancel();
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            overridePendingTransition(0,0);
            finish();
        });
//        countDownUtil = new CountDownUtil2(3000, 1000, binding.tvTime, this);

//        countDownUtil.start();
    }




}





