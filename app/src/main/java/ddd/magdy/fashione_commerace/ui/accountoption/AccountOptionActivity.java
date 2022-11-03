package ddd.magdy.fashione_commerace.ui.accountoption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.ActivityAccountOptionBinding;
import ddd.magdy.fashione_commerace.ui.login.LoginActivity;
import ddd.magdy.fashione_commerace.ui.signup.SignupActivity;

public class AccountOptionActivity extends AppCompatActivity {

    private ActivityAccountOptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.accountOptionBtnLogin.setOnClickListener(v -> {
            startActivity(new Intent(AccountOptionActivity.this, LoginActivity.class));

        });

        binding.accountOptionBtnSignup.setOnClickListener(v -> {
            startActivity(new Intent(AccountOptionActivity.this, SignupActivity.class));

        });

    }
}