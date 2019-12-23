package com.example.firstapplication.ui.SplashSceen;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapplication.R;
import com.example.firstapplication.ui.FirstSceen.FirstSceenActivity;
import com.scwang.wave.MultiWaveHeader;

public class SplashActivity extends AppCompatActivity {

    Thread objectthread;
    MultiWaveHeader waveHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startSplash();
        waveHeader = findViewById(R.id.webHeader);


    }
    private void startSplash(){
        try {
            Animation objectnAnimation = AnimationUtils.loadAnimation(
                    this,R.anim.translate
            );
            objectnAnimation.reset();
            ImageView ObjectImageView = findViewById(R.id.SplashImage);
            ObjectImageView.clearAnimation();
            ObjectImageView.startAnimation(objectnAnimation);

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
