package com.example.gmontejo.juanluna.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.gmontejo.juanluna.model.Response;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    private ImageView iv;
    private ImageLoaderListener imageLoaderListener;

    public ImageDownloader(ImageView iv, ImageLoaderListener imageLoaderListener) {
        this.iv = iv;
        this.imageLoaderListener = imageLoaderListener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String method = strings[0];
        String imgUrl = strings[1];
        URL url = null;
        HttpURLConnection connection = null;
        Bitmap bitmap = null;

        url = createURL(imgUrl, url);

        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Response response = new Response();
                response.setStatusCode(connection.getResponseCode());
                response.setStatus(connection.getResponseMessage());

                byte[] blob = IOUtils.toByteArray(inputStream);
                bitmap = createBitmap(blob);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
        } else {
        }
    }

    // Compose the URL object, which we will use to open a connection.
    private URL createURL(String imgUrl, URL url) {
        try {

            url = new URL(imgUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("ALPHACENTAUR", e.getMessage());
        }
        return url;
    }

    private Bitmap createBitmap(byte[] blob) {
        return BitmapFactory.decodeByteArray(blob, 0, blob.length);
    }
}
