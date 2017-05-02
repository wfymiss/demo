package com.example.administrator.fragmentdemo;

/**
 * Created by Administrator on 2017/3/14.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;

    private HttpUtils() {
    }

    public static HttpUtils getinstance() {
        if (httpUtils == null) {

            httpUtils = new HttpUtils();


        }


        return httpUtils;

    }



    public  void  Gethttp(){



    }


}
