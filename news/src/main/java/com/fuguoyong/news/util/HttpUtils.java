package com.fuguoyong.news.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */


public class HttpUtils {
    private static HttpUtils httputils;
    String data;

    private HttpUtils() {

    }

    public static HttpUtils getinstance() {
        if (httputils == null) {
            httputils = new HttpUtils();

        }
        return httputils;

    }

    public String httpGet(String url) {

        //获取httpclient
        HttpClient defaulthttpclient = new DefaultHttpClient();
        //设置请求方式get方法
        HttpGet get = new HttpGet(url);
        //通过Httpclient.execute 发送
        try {
            HttpResponse execute = defaulthttpclient.execute(get);
            //获取服务器返回值
            HttpEntity entity = execute.getEntity();
            //把HttpEntity 转化成String类型

            data = EntityUtils.toString(entity);


        } catch (IOException e) {

        }
        return data;
    }

    public String posthttp(String url, String key, String value) {
        //获取httpclient
        HttpClient defaulthttpclient = new DefaultHttpClient();
        //获取传递方式psot
        HttpPost post = new HttpPost(url);
        //client post方式发送


        try {

            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair(key, value));

            post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

            HttpResponse execute = defaulthttpclient.execute(post);


            //接受服务器返回值
            HttpEntity etity = execute.getEntity();

            //转换为String 类型
            data = EntityUtils.toString(etity);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return data;
    }


}