package ddd.magdy.fashione_commerace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ddd.magdy.fashione_commerace.databinding.ActivitySignupBinding;
import ddd.magdy.fashione_commerace.databinding.ActivitySuccessBinding;

public class SuccessActivity extends AppCompatActivity {

    private ActivitySuccessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.successBtnStartShopping.setOnClickListener(v->{
            startActivity(new Intent(SuccessActivity.this,MainActivity.class));
            finish();
        });

    }
}