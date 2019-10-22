package me.doapps.appdhn.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by William_ST on 07/10/15.
 */
public class ImageUtil {

    public static Bitmap downloadImage(String urlImage) {
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(urlImage).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

}
