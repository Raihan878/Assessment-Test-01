package com.example.firstapplication.SplashSceen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firstapplication.FirstSceen.FirstSceenActivity;
import com.example.firstapplication.R;

public class SplashActivity extends AppCompatActivity {

    Thread objectthread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
    private void startSplash(){
        try {
            objectthread = new Thread(){
                @Override
                public void run() {
                int pauseIt = 0;
                while (pauseIt<6000){
                    try{
                        sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    pauseIt +=100;
                }
                startActivity(new Intent(SplashActivity.this, FirstSceenActivity.class));
                SplashActivity.this.finish();
                }
            };
            objectthread.start();
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
