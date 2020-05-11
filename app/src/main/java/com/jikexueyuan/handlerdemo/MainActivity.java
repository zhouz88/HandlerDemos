package com.jikexueyuan.handlerdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //创建主线程Handler
    Handler handler = new Handler();

    /*{//创建消息

        Message message = Message.obtain();
        Message message1 = Message.obtain(message);//拷贝消息对象
        Message message2 = Message.obtain(handler, new Runnable() {//为消息对象设置可执行代码
            @Override
            public void run() {
                //do something
            }
        });

        handler.obtainMessage();//创建一个与handler对象绑定的消息对象

    }

    {//发送消息

        Message message = Message.obtain();
        handler.sendMessage(message);
        handler.sendMessageAtTime(message, 1000);//基于的时间是android.os.SystemClock#uptimeMillis()返回的时间
        handler.sendMessageDelayed(message, 1000);
        handler.sendMessageAtFrontOfQueue(message);

        handler.sendEmptyMessage(1);//是sendMessage系列方法的简约版本

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //do something
            }
        };
        Object token = new Object();

        handler.post(runnable);//是sendMessage系列方法的简约版本
        handler.postAtTime(runnable, token, 1000);
        //基于时间是android.os.SystemClock#uptimeMillis()时间值

        //判断消息队列中是否有指定的消息对象
        handler.hasMessages(1);
        handler.hasMessages(1,token);

        //撤销消息
        handler.removeMessages(1);
        handler.removeMessages(1, token);

        handler.removeCallbacks(runnable);
        handler.removeCallbacks(runnable, token);

        handler.removeCallbacksAndMessages(token);

        //撤销消息对象的方法中，如果token为null就会移除其他条件所匹配的所有消息对象
        //如果token值不为空，token值也会作为匹配条件
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button demo1Btn = (Button) findViewById(R.id.demo1Btn);
        demo1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Demo1Activity.class);
                startActivity(intent);
            }
        });

    }
}
