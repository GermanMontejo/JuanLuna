package com.example.gmontejo.juanluna.presenter;

import android.graphics.Bitmap;

import com.example.gmontejo.juanluna.model.Response;

public interface ImageLoaderListener {
    void onSuccess(Bitmap bitmap, Response response);
    void onFailure(Response Response);
}
