package ddd.magdy.fashione_commerace.ui.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ddd.magdy.fashione_commerace.MainActivity;
import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.SuccessActivity;
import ddd.magdy.fashione_commerace.databinding.ActivitySignupBinding;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.ui.login.LoginActivity;
import ddd.magdy.fashione_commerace.viewmodels.SignupViewModel;

public class SignupActivity extends AppCompatActivity implements SignUpNavigator {

    private ActivitySignupBinding binding;
    private SignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        viewModel.navigator = this;

        observeToField();
        setUpClickListener();
        setupIconVisible();

    }


    private void setupIconVisible() {
        binding.signupEditTextUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.signupEditTextUserName.getText().length() > 0) {
                    setDrawableUserName(ContextCompat.
                            getDrawable(SignupActivity.this, R.drawable.icon_correct));
                } else {
                    setDrawableUserName(null);
                }
            }
        });
    }

    private void setDrawableUserName(Drawable drawable) {
        binding.signupEditTextUserName.
                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable
                        , null);

    }


    private void observeToField() {
        viewModel.showLoading.observe(this, show -> {
            if (show) {
                binding.signupProgressbar.setVisibility(View.VISIBLE);
            } else {
                binding.signupProgressbar.setVisibility(View.INVISIBLE);
            }
        });
        viewModel.messageError.observe(this, this::showAlertDialog);

        viewModel.messageFocus.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

    private void showAlertDialog(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle(message);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    private void setUpClickListener() {
        binding.signupBtnSignUp.setOnClickListener(v -> {
            String email = binding.signupEditTextEmail.getText().toString();
            String password = binding.signupInputEditTextPassword.getText().toString();
            String confirmPassword = binding.signupInputEditTextConfirmPassword.getText().toString();
            String userName = binding.signupEditTextUserName.getText().toString();
            viewModel.createUserWithEmailAndPassword(email, password, confirmPassword, userName);


        });

        binding.signupCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.signupBtnSignUp.setClickable(isChecked);
            binding.signupBtnSignUp.setEnabled(isChecked);

        });
    }

    @Override
    public void goToHome() {
        startActivity(new Intent(SignupActivity.this, SuccessActivity.class));
        finish();
    }
}




