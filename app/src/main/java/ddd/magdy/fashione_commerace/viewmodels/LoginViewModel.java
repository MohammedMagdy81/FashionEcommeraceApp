package ddd.magdy.fashione_commerace.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import ddd.magdy.fashione_commerace.ui.login.LoginNavigator;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> messageErrorEmail = new MutableLiveData<>();
    public MutableLiveData<String> messageErrorPassword = new MutableLiveData<>();
    public MutableLiveData<String> messageError = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData();


    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public LoginNavigator navigator;

    public void signInWithEmailAndPassword(String email, String password) {
        if (!isValid(email, password)) return;
        showLoading.setValue(true);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    showLoading.postValue(false);
                    if (task.isSuccessful()) {
                        // goToHome
                        navigator.goToSuccess();
                    } else {
                        messageError.setValue(task.getException().getMessage());
                    }

                })
                .addOnFailureListener(e -> {
                    messageError.setValue(e.getMessage());
                });
    }


    public void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    navigator.goToSuccess();
                } else {
                    messageError.setValue(task.getException().getMessage());
                }
            }
        });
    }

    private Boolean isValid(String email, String password) {
        if (email.isEmpty()) {
            messageErrorEmail.setValue("Invalid E-mail");
            return false;
        }
        if (password.isEmpty()) {
            messageErrorPassword.setValue("Invalid Password");
            return false;
        }
        return true;
    }
}
