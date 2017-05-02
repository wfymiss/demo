package com.fuguoyong.news.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import com.fuguoyong.news.adapter.Myadapter;
import com.fuguoyong.news.db.Bitmapmanager;
import com.fuguoyong.news.listener.Bitmaplistener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2017/3/9.
 */

public class Mytask extends AsyncTask<String, Void, Bitmap> {

    private Bitmaplistener listener;
    Bitmap bitmap;

    public  Mytask(Myadapter myadapter){
        this.listener = myadapter;

    }

    //执行耗时操作
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            //创建一个URL 对象
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            //想要操作BitMAP  需要通过bitmapfactory方法，把数据转化为bitmap
            bitmap = BitmapFactory.decodeStream(inputStream);
            Bitmapmanager.getinstance().getcache().put(strings[0],bitmap);



        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }

    //设置进度条
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //返回值
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        listener.SetBitmap(bitmap);
        Log.e("======",bitmap+"");

    }

    //加载进度条
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
