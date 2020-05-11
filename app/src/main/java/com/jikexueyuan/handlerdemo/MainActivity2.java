package com.jikexueyuan.handlerdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;

import static com.jikexueyuan.handlerdemo.R.id.get_content;


public class MainActivity2 extends AppCompatActivity {

    String[] aphorism = {
            "明日复明日，\n明日何其多，\n我生待明日，\n万事成蹉跎",
            "盛年不重来，\n一日难再晨。\n及时当勉励，\n岁月不待人",
            "抛弃时间的人，\n时间也抛弃他",
            "莫等闲，\n白了少年头，\n空悲切!",
            "少壮不努力，\n老大徒伤悲。",
            "劝君莫惜金缕衣，\n劝君惜取少年时。",
            "花开堪折直须折，\n莫待无花空折枝。"
    };

    Handler handler = new MyHandler(this);
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView timeShow = (TextView) findViewById(R.id.time_show);
        final TextView contentShow = (TextView) findViewById(R.id.show_content);
        final Button start = (Button) findViewById(R.id.get_content);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    timeShow.setText("时间显示区");
                    contentShow.setText("内容显示区");
                    timeShow.setTextColor(0xff000000);
                    contentShow.setTextColor(0xff000000);
                    start.setText("开始计时");
                    isRunning = false;
                }else {
                    start.setText("正在计时");
                    isRunning = true;
                    Executors.defaultThreadFactory().newThread(new Runnable() {
                        @Override
                        public void run() {
                            int count = 0;
                            while (true) {
                                //发送计时的消息
                                Message tMsg = Message.obtain();
                                tMsg.what = 1;
                                tMsg.arg1 = count;
                                tMsg.arg2 = 0xff000000 + (int) (0xffffff * Math.random());

                                if(isRunning) {
                                    handler.sendMessage(tMsg);
                                }else{
                                    break;
                                }

                                //更新内容的消息
                                Message cMsg = Message.obtain();
                                cMsg.what = 2;
                                cMsg.arg1 = 0xff000000 + (int) (0xffffff * Math.random());

                                Bundle bundle = new Bundle();
                                bundle.putString("content", aphorism[(count / 5) % aphorism.length]);
                                cMsg.setData(bundle);

                                handler.sendMessage(cMsg);

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                count++;
                            }
                        }
                    }).start();

                }
            }
        });
    }


    static class MyHandler extends Handler{

        WeakReference<Activity> wf;

        MyHandler(Activity activity){
            wf = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if(wf.get()!=null){
                MainActivity2 mainActivity2 = (MainActivity2) wf.get();
                TextView timeShow = (TextView) mainActivity2.findViewById(R.id.time_show);
                TextView contentShow = (TextView) mainActivity2.findViewById(R.id.show_content);
                if(msg.what==1){
                    timeShow.setTextColor(msg.arg2);
                    timeShow.setText("第"+msg.arg1+"秒");
                }else if(msg.what==2){
                    contentShow.setTextColor(msg.arg1);
                    Bundle bundle = msg.getData();
                    contentShow.setText(bundle.getString("content"));
                }
            }

        }
    }
}
