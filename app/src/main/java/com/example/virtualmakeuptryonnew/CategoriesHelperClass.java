package com.example.virtualmakeuptryonnew;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {
    private GradientDrawable gradient;
    private int image;
    private String categoryName;

    public CategoriesHelperClass(GradientDrawable gradient, int image, String categoryName) {
        this.gradient = gradient;
        this.image = image;
        this.categoryName = categoryName;
    }

    public GradientDrawable getGradient() {
        return gradient;
    }

    public int getImage() {
        return image;
    }

    public String getCategoryName() {
        return categoryName;
    }

}