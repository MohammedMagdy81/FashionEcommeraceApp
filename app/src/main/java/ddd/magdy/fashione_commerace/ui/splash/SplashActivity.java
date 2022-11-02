package ddd.magdy.fashione_commerace.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.ui.onboarding.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
                        finish();
                    }
                },2000);
    }
}