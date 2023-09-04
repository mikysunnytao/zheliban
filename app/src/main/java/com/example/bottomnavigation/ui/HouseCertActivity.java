package com.example.bottomnavigation.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivityCertBinding;

public class HouseCertActivity extends AppCompatActivity {

    ActivityCertBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cert);
        binding.tvBack.setOnClickListener(v->{
            finish();
        });
        binding.tvClose.setOnClickListener(v->{
            finish();
        });
    }
}
