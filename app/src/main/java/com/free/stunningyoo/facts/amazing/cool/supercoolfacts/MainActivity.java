package com.free.stunningyoo.facts.amazing.cool.supercoolfacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //DatabaseHelper myDB;
    private final int Duration=3000;
    private Thread mslashThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mslashThread=new Thread(){
            @Override
            public void run() {
                synchronized (this){
                    try {
                        wait(Duration);
                    }
                    catch (InterruptedException e){
                    }
                    finally {
                        finish();
                        Intent intent=new Intent(getBaseContext(),FactScreenActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        mslashThread.start();
    }





}