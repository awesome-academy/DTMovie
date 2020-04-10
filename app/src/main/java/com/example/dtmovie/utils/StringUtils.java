package com.example.dtmovie.utils;

import com.example.dtmovie.constant.Constant;

public class StringUtils {
    public static String getImage(String image_path) {
        return String.format("%s%s%s", Constant.BASE_IMAGE_PATH, Constant.IMAGE_SIZE_W500, image_path);
    }

    public static String getImageUrl(String image_path) {
        return new StringBuilder().append(Constant.BASE_IMAGE_PATH)
                .append(Constant.IMAGE_SIZE_W500)
                .append(image_path).toString();
    }
}
