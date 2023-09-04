package com.example.bottomnavigation.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.view.FaceView;

public class VerificationActivity extends AppCompatActivity {

    private FaceView faceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_11);
        faceView = findViewById(R.id.fv_title);
        faceView.startAnimator();
    }
}
