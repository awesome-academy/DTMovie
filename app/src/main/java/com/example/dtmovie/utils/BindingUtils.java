package com.example.dtmovie.utils;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.dtmovie.R;

public class BindingUtils {
    @BindingAdapter("setSplashAnimation")
    public static void setSplashAnimation(ImageView imageView, boolean isSet) {
        if (isSet) {
            imageView.setAnimation(
                    AnimationUtils.loadAnimation(imageView.getContext(), R.anim.animation_splash));
        }
    }
}
