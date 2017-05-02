package com.example.administrator.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ShipingFragment extends Fragment {
    ListView lv;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragmentlayout, null);
          lv= (ListView) view.findViewById(R.id.lv);

        setadapter();

        setdata();














        return view;
    }

    private void setdata() {
          HttpUtils.getinstance().g
    }

    private void setadapter() {
          Myadapter adapter= new Myadapter();
         lv.setAdapter(adapter);

    }

    private void initview() {
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
