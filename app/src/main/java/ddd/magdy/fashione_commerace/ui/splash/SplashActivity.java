package ddd.magdy.fashione_commerace.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ddd.magdy.fashione_commerace.MainActivity;
import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.ui.onboarding.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> {
                    startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
                    finish();
//                    if (currentUser != null) {
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        finish();
//                    } else {
//                        startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
//                        finish();
//                    }

                }, 2000);
    }
}