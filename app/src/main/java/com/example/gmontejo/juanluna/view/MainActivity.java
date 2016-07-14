package com.example.gmontejo.juanluna.view;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.gmontejo.juanluna.R;
import com.example.gmontejo.juanluna.model.Response;
import com.example.gmontejo.juanluna.presenter.ImageLoaderListener;
import com.example.gmontejo.juanluna.presenter.JuanLuna;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.imageView);

        JuanLuna.load("https://www.royalcanin.com/~/media/Royal-Canin/Product-Categories/dog-medium-landing-hero.ashx")
                .setMethod("GET")
                .intoImageView(iv)
                .start(new ImageLoaderListener() {
                    @Override
                    public void onSuccess(Bitmap bitmap, Response response) {

                    }

                    @Override
                    public void onFailure(Response Response) {

                    }
                });
    }
}
