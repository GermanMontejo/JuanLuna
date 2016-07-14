package com.example.gmontejo.juanluna.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class JuanLuna {
    public static final String GET = "GET";
    public static final String POST = "POST";
    private static JuanLuna juanLuna = null;
    private Bitmap bitmap;
    private String url;
    private String method;
    private ImageView imageView;


    private JuanLuna(String url) {
        this.url = url;
    }

    public static JuanLuna load(String url) {
        if (juanLuna == null) {
            return new JuanLuna(url);
        }
        return juanLuna;
    }

    public JuanLuna setMethod(String method) {
        this.method = method;
        return this;
    }

    public JuanLuna intoImageView(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public JuanLuna start(ImageLoaderListener imageLoaderListener) {
        // call image downloader here
        ImageDownloader imageDownloader = new ImageDownloader(this.imageView, imageLoaderListener);
        imageDownloader.execute(this.method, this.url);
        return this;
    }


}
