package ddd.magdy.fashione_commerace.viewmodels;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

import ddd.magdy.fashione_commerace.model.User;
import ddd.magdy.fashione_commerace.ui.login.LoginNavigator;
import ddd.magdy.fashione_commerace.utils.Constant;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> messageErrorEmail = new MutableLiveData<>();
    public MutableLiveData<String> messageErrorPassword = new MutableLiveData<>();
    public MutableLiveData<String> messageError = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData();
    public CallbackManager callbackManager = CallbackManager.Factory.create();

    ;


    private FirebaseAuth auth = FirebaseAuth.getInstance();
    public FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public LoginNavigator navigator;

    public void signInWithEmailAndPassword(String email, String password) {
        if (!isValid(email, password)) return;
        showLoading.setValue(true);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    showLoading.postValue(false);
                    if (task.isSuccessful()) {
                        // goToHome
                        reteriveUser(auth.getCurrentUser().getUid(), task1 -> {
                            if (task1.isSuccessful()) {
                                User user = task1.getResult().toObject(User.class);
                                Constant.user = user;
                            }
                        });
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
        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                navigator.goToSuccess();
            } else {
                messageError.setValue(task.getException().getMessage());
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

    public void googleAuthWithFirebase(GoogleSignInAccount result) {
        AuthCredential credential = GoogleAuthProvider.getCredential(result.getIdToken(), null);
        try {
            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    navigator.goToSuccess();
                }
            });

        } catch (Exception e) {
            messageError.setValue(e.getMessage());
        }
    }

    public void signInWithFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                messageError.setValue(error.getMessage());
            }
        });

    }

    public void reteriveUser(String userId, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        firestore.collection(Constant.USER_COLLECTION)
                .document(userId)
                .get()
                .addOnCompleteListener(onCompleteListener);

    }

}
