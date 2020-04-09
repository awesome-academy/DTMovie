package com.example.dtmovie.utils;

import com.example.dtmovie.constant.Constant;

public class StringUtils {
    public static String getImage(String image_path) {
        return String.format("%s%s%s", Constant.BASE_IMAGE_PATH, Constant.IMAGE_SIZE_W500, image_path);
    }
}
