package com.louis.mvp.model;

import android.graphics.Bitmap;
import android.view.View;

public class ImageBean {
    private String requestPath;
    private Bitmap mBitmap;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}
