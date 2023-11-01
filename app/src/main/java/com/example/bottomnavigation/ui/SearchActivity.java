package com.example.bottomnavigation.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivitySearchBinding;
import com.example.bottomnavigation.utils.AppManager;
import com.kongzue.dialogx.dialogs.CustomDialog;
import com.kongzue.dialogx.dialogs.TipDialog;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnBindView;

import tech.gujin.toast.ToastUtil;

public class SearchActivity extends AppCompatActivity {

    protected ActivitySearchBinding binding;

    private boolean verify = false;

    private boolean checked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.flLoading.setVisibility(View.VISIBLE);
        binding.tvBack.setOnClickListener(v -> {
            finish();
        });

     CustomDialog customDialog = CustomDialog.build();
     customDialog.setMaskColor(Color.parseColor("#99000000"));
     customDialog.setCustomView(new OnBindView<CustomDialog>(R.layout.dialog_confirm1) {
         @Override
         public void onBind(CustomDialog dialog, View v) {
             v.findViewById(R.id.btn_disagree).setOnClickListener(v1->{
                 dialog.dismiss();
             });

             v.findViewById(R.id.btn_agree).setOnClickListener(v1->{
                 dialog.dismiss();
             });
         }
     });
     customDialog.show();

        binding.tvClose.setOnClickListener(v -> {
            finish();
        });

        binding.lightCode.setOnClickListener(v -> {
            ToastUtil.show("网络连接失败");
        });
        binding.btnCert.setOnClickListener(v -> {
            binding.scrollView.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);
        });
        binding.tvSearch.setOnClickListener(v -> {
            binding.scrollView.setVisibility(View.GONE);
            binding.llSearchContent.setVisibility(View.VISIBLE);
            binding.tvTitle.setText("不动产权属查询");
        });

        binding.btnSearch.setOnClickListener(v -> {
            if (checked) {
                showConfirmDialog();
            }
        });

        binding.llNo.setOnClickListener(v -> {
            binding.ivYes.setImageResource(R.mipmap.radio);
            binding.ivNo.setImageResource(R.mipmap.radio_selected);
        });
        binding.llYes.setOnClickListener(v -> {
            binding.ivNo.setImageResource(R.mipmap.radio);
            binding.ivYes.setImageResource(R.mipmap.radio_selected);
        });
        binding.houseCert.setOnClickListener(v -> {
            binding.flSearchInfo.setVisibility(View.GONE);
            new Handler().postDelayed(() -> {
                binding.flHouseInfo.setVisibility(View.VISIBLE);
            }, 100);
        });
        new Handler().postDelayed(() -> {
            binding.llBottom.setVisibility(View.VISIBLE);
            binding.tvClose.setVisibility(View.VISIBLE);
            binding.ivLoading.setImageResource(R.mipmap.ic_verifying_login);
            new Handler().postDelayed(() -> {
                binding.ivLoading.setImageResource(R.mipmap.ic_loading);
                new Handler().postDelayed(() -> {
                    binding.flLoading.setVisibility(View.GONE);
//                    binding.mainView.setVisibility(View.VISIBLE);
                    binding.scrollView.setVisibility(View.VISIBLE);
//                    binding.mainView.setImageResource(R.mipmap.main_view);
                }, 200);
            }, 600);
        }, 600);
        binding.cbCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checked = isChecked;
            if (isChecked) {
                binding.btnSearch.setBackgroundColor(Color.parseColor("#0073eb"));
            } else {
                binding.btnSearch.setBackgroundColor(Color.parseColor("#87b7f4"));

            }
        });
    }

    private void showConfirmDialog() {
        CustomDialog customDialog1 = CustomDialog.build().setMaskColor(Color.parseColor("#99000000"));
        customDialog1.setCustomView(new OnBindView<CustomDialog>(R.layout.dialog_confirm) {
            @Override
            public void onBind(CustomDialog dialog, View v) {
                TextView tvContent = v.findViewById(R.id.tv_confirm);
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#71a3bb"));
                SpannableString spannableString = new SpannableString(tvContent.getText());
                String content = tvContent.getText().toString();
                spannableString.setSpan(colorSpan, content.indexOf("《"), content.indexOf("》"), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvContent.setText(spannableString);
                TextView btnAgree = v.findViewById(R.id.btn_agree);
                TextView btnDisagree = v.findViewById(R.id.btn_disagree);
                btnAgree.setOnClickListener(v1 -> {
                    verify = true;
                    startActivity(new Intent(SearchActivity.this, VerifyingActivity.class));
                });
                btnDisagree.setOnClickListener(v1->{
                    dialog.dismiss();
                });
            }
        });
        customDialog1.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        WaitDialog.show("查询中..");
        new Handler().postDelayed(() -> {
            TipDialog.show("查询成功", WaitDialog.TYPE.SUCCESS);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(this, CertActivity.class));
//                AppManager.getAppManager().finishActivity(SearchActivity.class);
                    finish();
            }, 500);
//            finish();
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (verify) {

            verify = false;
        }
    }
}
