package com.example.dtmovie.data.model;

public class MovieCategory {
    private String mCategoryTitle;
    private String mCategoryKey;

    public MovieCategory() {
    }

    public MovieCategory(String categoryTitle, String categoryKey) {
        mCategoryTitle = categoryTitle;
        mCategoryKey = categoryKey;
    }

    public String getCategoryTitle() {
        return mCategoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        mCategoryTitle = categoryTitle;
    }

    public String getCategoryKey() {
        return mCategoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        mCategoryKey = categoryKey;
    }
}
