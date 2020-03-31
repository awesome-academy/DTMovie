package com.example.dtmovie.ui.main;

import android.content.Context;
import android.content.Intent;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseActivity;
import com.example.dtmovie.base.BaseViewModel;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected int getVariables() {
        return 0;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
