package com.example.administrator.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    TextView tv1;
    FragmentManager manager;
    ShipingFragment shipingfragment;
    Fragmentdemo fragmentdemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建对象
        manager = getFragmentManager();
        initview();
        initfragment();
        setonclick();


    }

    private void setonclick() {

    }

    private void initfragment() {


        //开启事物

        FragmentTransaction fragmentTransaction = manager.beginTransaction();


        // 创建fragment

        fragmentdemo = new Fragmentdemo();


        fragmentTransaction.replace(R.id.frame, fragmentdemo);

        // 提交
        fragmentTransaction.commit();
    }

    private void initview() {

        tv = (TextView) findViewById(R.id.textView4);
        tv1 = (TextView) findViewById(R.id.texttv);
    }


    @Override
    public void onClick(View view) {
        FragmentTransaction saction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.textView4:
                Bundle bundle = new Bundle();
                bundle.putString("tex" , str);
                shipingfragment.setArguments(bundle);
                saction.replace(R.id.frame, shipingfragment);

                break;

            case R.id.texttv:
                if (fragmentdemo == null) {
                    fragmentdemo = new Fragmentdemo();
                }
                saction.replace(R.id.frame, fragmentdemo);

                break;

            default:
                break;

        }
        saction.commit();
    }

}
