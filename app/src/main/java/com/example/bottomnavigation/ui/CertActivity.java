package com.example.bottomnavigation.ui;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivityCertBinding;
import com.example.bottomnavigation.utils.StatusBarUtil;
import com.gyf.immersionbar.ImmersionBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CertActivity extends AppCompatActivity {

    private ActivityCertBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cert);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String dateStr = format.format(new Date());
        binding.tvTime.setText(dateStr);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Rect rect = new Rect();
                binding.scrollView.getHitRect(rect);
                if (v.getLocalVisibleRect(rect)) {
                }else {

                }
            });
        }
        binding.tvBack.setOnClickListener(v->{
            finish();
        });
        binding.tvClose.setOnClickListener(v->{
            finish();
        });


    }

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),400);
    }
}
