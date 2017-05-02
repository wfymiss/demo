package com.fuguoyong.news.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fuguoyong.news.R;

public class HomepagerActivity extends AppCompatActivity implements View.OnClickListener {
    TextView left;
    TextView right;
    JsFragment jsfragment;
    ShFragment shFragment;
    FragmentManager manager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepager);
        initview();
        initfragment();
        setonclick();
    }

    private void initfragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        jsfragment = new JsFragment();


        transaction.replace(R.id.frame, jsfragment);


        transaction.commit();


    }

    private void setonclick() {

        left.setOnClickListener(this);
        right.setOnClickListener(this);

    }

    private void initview() {
        left = (TextView) findViewById(R.id.home_left_tv);
        right = (TextView) findViewById(R.id.home_right_tv);


    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.home_left_tv:
                if (jsfragment == null){
                      jsfragment=new  JsFragment();
                }
                transaction.replace(R.id.frame, jsfragment);
                transaction.commit();
                break;
            case R.id.home_right_tv:
                if (shFragment == null){
                    shFragment = new ShFragment();
                }

                transaction.replace(R.id.frame, shFragment);
                transaction.commit();
                break;
        }

    }
}
