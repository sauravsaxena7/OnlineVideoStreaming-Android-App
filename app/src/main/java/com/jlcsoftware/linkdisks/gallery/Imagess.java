package com.jlcsoftware.linkdisks.gallery;

import android.graphics.Bitmap;

import okhttp3.ResponseBody;

public class Imagess {




   private ResponseBody responseBody;

    public Imagess(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }


    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
