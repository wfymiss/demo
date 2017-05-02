package com.fuguoyong.news.adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuguoyong.news.Activity.JsFragment;
import com.fuguoyong.news.R;
import com.fuguoyong.news.bean.NewsIofo;
import com.fuguoyong.news.db.Bitmapmanager;
import com.fuguoyong.news.listener.Bitmaplistener;
import com.fuguoyong.news.util.Mytask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class Myadapter extends BaseAdapter implements Bitmaplistener{
    List<NewsIofo> list = new ArrayList<NewsIofo>();
    Context context;
    ViewHolder viewHolder;

    public Myadapter(Activity jsFragment) {
        this.context = jsFragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.homepagerlayout, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.pager_context);
            viewHolder.tv = (TextView) view.findViewById(R.id.pager_title);
            viewHolder.time = (TextView) view.findViewById(R.id.pager_time);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.pager_im);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.time.setText(list.get(i).getStamp());
        viewHolder.tv.setText(list.get(i).getTitle());
        viewHolder.textView.setText(list.get(i).getSummary());
        //AsyncTask 开启

        Bitmap bitmap = Bitmapmanager.getinstance().Getbitmap(list.get(i).getIcon());
        if (bitmap == null) {
            new Mytask(this).execute(list.get(i).getIcon());
        } else {
            viewHolder.imageView.setImageBitmap(bitmap);
        }


        return view;
    }

    @Override
    public void SetBitmap(Bitmap bitmap) {
        viewHolder.imageView.setImageBitmap(bitmap);
    }



    class ViewHolder {
        TextView textView;
        TextView time;
        TextView tv;
        ImageView imageView;

    }

    public void setlistdata(List<NewsIofo> listdata) {
        this.list = listdata;
    }
}

