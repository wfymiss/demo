package com.fuguoyong.news.Activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fuguoyong.news.R;
import com.fuguoyong.news.adapter.Myadapter;
import com.fuguoyong.news.bean.NewsIofo;
import com.fuguoyong.news.db.DbManager;
import com.fuguoyong.news.util.HttpUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/3/14.
 */

public class JsFragment extends Fragment {
    View view;
    ListView lv;
    Myadapter adapter;
    List<NewsIofo> list = new ArrayList<NewsIofo>();
    String url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(getActivity(), "操", Toast.LENGTH_LONG).show();
            } else {
                list = (List<NewsIofo>) msg.obj;
                adapter.setlistdata(list);
                adapter.notifyDataSetChanged();
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.js_fragment_item, null);
        lv = (ListView) view.findViewById(R.id.lv);
        setadapter();
        long along = DbManager.getinstanse(getActivity()).getDBcount();
        if (along == 0) {
            Log.e("-----", "走的网络");
            setdata();
        } else {
            Log.e("++++", "走的数据库");
            List<NewsIofo> news = DbManager.getinstanse(getActivity()).select();
            Log.e("++++", news+"");
            adapter.setlistdata(news);
            adapter.notifyDataSetChanged();
        }

        return view;
    }

    private void setadapter() {
        adapter = new Myadapter(getActivity());
        lv.setAdapter(adapter);

    }

    private void setdata() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = HttpUtils.getinstance().httpGet(url);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String message = jsonObject.getString("message");
                    int status = jsonObject.getInt("status");
                    JSONArray data1 = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data1.length(); i++) {
                        JSONObject jb = data1.getJSONObject(i);
                        String summary = jb.getString("summary");
                        String icon = jb.getString("icon");
                        String stamp = jb.getString("stamp");
                        String title = jb.getString("title");
                        int nid = jb.getInt("nid");
                        String link = jb.getString("link");
                        int type = jb.getInt("type");
                        NewsIofo newsIofo = new NewsIofo( summary,  icon,  stamp,  title,  nid,  link,  type);
                        DbManager.getinstanse(getActivity()).instert(newsIofo);
                        list.add(newsIofo);


                        Message msg = Message.obtain();
                        msg.obj = list;
                        handler.sendMessage(msg);
                        Log.e("_________", msg + " ");
                    }
                } catch (Exception e) {
                    handler.sendEmptyMessage(1);
                }


            }
        }).start();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
