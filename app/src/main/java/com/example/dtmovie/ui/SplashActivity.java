package com.example.dtmovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.dtmovie.R;
import com.example.dtmovie.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int TIME_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        }, TIME_DELAY);
    }
}
