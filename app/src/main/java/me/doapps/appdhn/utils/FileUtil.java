package me.doapps.appdhn.utils;

/**
 * Created by William_ST on 05/10/15.
 */
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtil {
    private final String TAG = FileUtil.class.getSimpleName();
    private final int  MEGABYTE = 1024 * 1024;
    private Context context;

    public FileUtil(Context context){
        this.context = context;
    }

    public void downloadFile(String fileUrl, File directory){
        try {

            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer))>0 ){
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException "+e.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException"+e.toString());
        } catch (IOException e) {
            Log.e(TAG, "IOException"+e.toString());
        }
    }
}