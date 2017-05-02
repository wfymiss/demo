package com.fuguoyong.news.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/15.
 */

public class DbHolper extends SQLiteOpenHelper {

    //创建文件
    public DbHolper(Context context) {
        super(context, "news.db", null, 1);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建数据库的字段
        String sql = " CREATE TABLE  news(_id INTEGER PRIMARY KEY AUTOINCREMENT, summary TEXT, icon TEXT, stamp TEXT, title TEXT, nid INTEGER, link TEXT, type TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

