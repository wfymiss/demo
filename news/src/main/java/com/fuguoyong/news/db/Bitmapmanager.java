package com.fuguoyong.news.db;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Bitmapmanager {
    private static Bitmapmanager manager;
    HashMap<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();
    BitmapCache cache;

    private Bitmapmanager() {


    }

    public static Bitmapmanager getinstance() {
        if (manager == null) {

            manager = new Bitmapmanager();

        }
        return manager;

    }

    public BitmapCache getcache() {

        int size = (int) Runtime.getRuntime().maxMemory();

        if (cache == null) {
            cache = new BitmapCache(size / 8, map);
        }
        return cache;


    }

    public Bitmap Getbitmap(String url) {

        Bitmap bitmap = getcache().get(url);
        if (bitmap != null) {

            return bitmap;
        } else {

            if (map.get(url) == null) {


                map.remove(url);

                return null;
            } else {


                getcache().put(url, map.get(url).get());


                return bitmap;
            }


        }
    }


}
