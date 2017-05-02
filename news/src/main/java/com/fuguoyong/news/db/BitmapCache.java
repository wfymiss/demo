package com.fuguoyong.news.db;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/14.
 */

public class BitmapCache extends LruCache<String, Bitmap> {

    HashMap<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();


    public BitmapCache(int maxSize, HashMap<String, SoftReference<Bitmap>> map) {
        super(maxSize);
        this.map =map;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return super.sizeOf(key, value);
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);
        if (evicted) {

            SoftReference<Bitmap> bitmapSoftReference = new SoftReference<>(oldValue);


            map.put(key, bitmapSoftReference);


        }
    }


}
