package com.fuguoyong.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fuguoyong.news.bean.NewsIofo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class DbManager {
    private static DbManager dbManager;
    DbHolper dbHelper;
    NewsIofo news;
    List<NewsIofo> list = new ArrayList<NewsIofo>();

    private DbManager(Context context) {
        dbHelper = new DbHolper(context);
    }

    public static DbManager getinstanse(Context context) {
        if (dbManager == null) {

            dbManager = new DbManager(context);
        }
        return dbManager;
    }

    // 把网络数据加到写到手机里边
    public void instert(NewsIofo newsIofo) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("summary", newsIofo.getSummary());
        values.put("icon", newsIofo.getIcon());
        values.put("stamp", newsIofo.getStamp());
        values.put("title", newsIofo.getTitle());
        values.put("nid", newsIofo.getNid());
        values.put("link", newsIofo.getLink());
        values.put("type", newsIofo.getType());
        sqLiteDatabase.insert("news", null, values);


    }

    public List<NewsIofo> select() {
        List<NewsIofo> list = new ArrayList<NewsIofo>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select * from news";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String stamp = cursor.getString(cursor.getColumnIndex("stamp"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String link = cursor.getString(cursor.getColumnIndex("link"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));


                NewsIofo news = new NewsIofo(summary, icon, stamp, title, nid, link, type);
                Log.e("1111",news+"");
                list.add(news);
                Log.e("2222",list+"");
            } while (cursor.moveToNext());

        }
        Log.e("33333",list+"");
        return list;
    }

    long aLong;

    public long getDBcount() {

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String sql = "select count(*) from news";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            aLong = cursor.getLong(0);
        }
        return aLong;
    }

    public void updata(NewsIofo newsIofo) {
        SQLiteDatabase sql = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("summary", newsIofo.getSummary());
        values.put("icon", newsIofo.getIcon());
        values.put("stamp", newsIofo.getStamp());
        values.put("title", newsIofo.getTitle());
        values.put("nid", newsIofo.getNid());
        values.put("link", newsIofo.getLink());
        values.put("type", newsIofo.getType());
        String str[] = new String[]{String.valueOf(newsIofo.getNid())};
        sql.update("news", values, "nid=?", str);


    }

    public void delete() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // String [] srt = new  String[]{String.valueOf()};
        // database.update("news","nid=?",srt);
    }


}

