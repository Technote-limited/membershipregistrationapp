package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EasySplashScreen config = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(AgentRegister.class)
                .withSplashTimeOut(6000)
                .withBackgroundColor(Color.parseColor("#333366"))
                //.withHeaderText("ODM")
                // .withFooterText("ODM")
                // .withBeforeLogoText("Before Logo Text")
                .withAfterLogoText("ODM Party")
                .withLogo(R.drawable.orange);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
       /* config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);*/

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
