package com.xiang.neonlightproject;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int currentColor = 0;
    //定义颜色数字
    final int[] colors = new int[]{R.color.red, R.color.green, R.color.mazarine, R.color.yello, R.color.pink, R.color.azury,};
    final int[] names = new int[]{R.id.view1, R.id.view2, R.id.view3, R.id.view4, R.id.view5, R.id.view6};
    TextView[] views = new TextView[names.length];

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //表明消息来着本程序所发
            if (msg.what == 0x123) {
                for (int i = 0; i < names.length; i++) {
                    views[i].setBackgroundResource(colors[(i + currentColor) % names.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i <  names.length; i++) {
            views[i]= (TextView) findViewById(names[i]);
            // 用定时器 定义一个线程周期性的改变currentColor变量值
            //程序没毫秒执行一次任务，通知text view颜色改变

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                   handler.sendEmptyMessage(0x123);
                }
            },0,200);
        }
    }
}
