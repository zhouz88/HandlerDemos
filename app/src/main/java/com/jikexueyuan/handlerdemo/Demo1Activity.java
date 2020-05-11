package com.jikexueyuan.handlerdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Demo1Activity extends AppCompatActivity {

    //创建主线程handler
//    Handler handler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if(msg.what==1){
//                Toast.makeText(Demo1Activity.this,"First handle",Toast.LENGTH_LONG).show();
//            }
//            return false;//false需要消息进一步处理
//        }
//    }){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what==1){
//                Toast.makeText(Demo1Activity.this,"What is 1", Toast.LENGTH_LONG).show();
//            }
//        }
//    };

    String msgText = "MsgText";

    //创建主线程Handler
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what==1){
//                Toast.makeText(Demo1Activity.this, msgText, Toast.LENGTH_LONG).show();
//            }
//        }
//    };

    Handler handler = new MyHandler(this);

    /* 静态的Handler子类，使用弱引用与外部Activity关联，避免内存泄露 */
    static class MyHandler extends Handler{

        WeakReference<Activity> wf;

        MyHandler(Activity activity){
            wf = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(wf.get()!=null){
                    Toast.makeText(wf.get(),((Demo1Activity)wf.get()).msgText,Toast.LENGTH_LONG).show();
                }else{
                    System.out.println("Exit");
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(Demo1Activity.this, "Run", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        //发送消息
//        handler.sendEmptyMessage(1);

        handler.sendEmptyMessageDelayed(1, 100000);
    }
}
