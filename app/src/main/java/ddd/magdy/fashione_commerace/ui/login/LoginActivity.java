package ddd.magdy.fashione_commerace.ui.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import ddd.magdy.fashione_commerace.MainActivity;
import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.ActivityLoginBinding;
import ddd.magdy.fashione_commerace.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements LoginNavigator {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    private FirebaseAuth mAuth;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.navigator = this;
        mAuth = FirebaseAuth.getInstance();

//        BeginSignInRequest build = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                .build();


        setUpClickListener();
        observeToField();
    }

    private void observeToField() {
        viewModel.messageError.observe(this, this::showAlertDialog);
        viewModel.messageErrorEmail.observe(this, message -> {
            binding.loginEditTextEmail.setError(message);
        });


        viewModel.messageErrorPassword.observe(this, message -> {
            binding.loginEditTextPassword.setError(message);
        });
        viewModel.showLoading.observe(this, show -> {
            if (show) {
                binding.loginProgressbar.setVisibility(View.VISIBLE);
            } else {
                binding.loginProgressbar.setVisibility(View.INVISIBLE);
            }
        });

    }


    private void setUpClickListener() {
        binding.loginBtnLogin.setOnClickListener(v -> {
            viewModel.signInWithEmailAndPassword(binding.loginEditTextEmail.getText().toString(),
                    binding.loginEditTextPassword.getText().toString());
        });

        binding.loginFacebook.setOnClickListener(v -> {

        });
        binding.loginGmail.setOnClickListener(v -> {

            signInWithGmail();

        });
        binding.loginApple.setOnClickListener(v -> {

        });

        setUpEditTextIconVisible();
    }

    private void signInWithGmail() {
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, signInOptions);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // account that has been selected
            GoogleSignInAccount result = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
            if (result != null) {
                // this uer now is google user not firebase user
                googleAuthWithFirebase(result);
            }
        }
    }

    private void googleAuthWithFirebase(GoogleSignInAccount result) {
        AuthCredential credential = GoogleAuthProvider.getCredential(result.getIdToken(), null);
        try {
            mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Login Success ", Toast.LENGTH_LONG).show();
                    goToHome();
                }
            });

        } catch (Exception e) {
            showAlertDialog(e.getMessage());
        }
    }

    private void setUpEditTextIconVisible() {
        binding.loginEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.loginEditTextEmail.getText().length() > 0) {
                    setDrawableEmail(ContextCompat.
                            getDrawable(LoginActivity.this, R.drawable.icon_correct));
                } else {
                    setDrawableEmail(null);
                }
            }
        });
        binding.loginEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.loginEditTextPassword.getText().length() > 0) {
                    setDrawablePassword(ContextCompat.
                            getDrawable(LoginActivity.this, R.drawable.icon_correct));
                } else {
                    setDrawablePassword(null);
                }
            }
        });
    }

    private void setDrawableEmail(Drawable drawable) {
        binding.loginEditTextEmail.
                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable
                        , null);
    }

    private void setDrawablePassword(Drawable drawable) {
        binding.loginEditTextPassword.
                setCompoundDrawablesWithIntrinsicBounds(null, null, drawable
                        , null);
    }


    @Override
    public void goToHome() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void showAlertDialog(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle(message);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

}