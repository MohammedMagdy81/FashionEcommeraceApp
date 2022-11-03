package ddd.magdy.fashione_commerace.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                        navigator.goToHome();
                    } else {
                        messageError.setValue(task.getException().getMessage());
                    }

                })
                .addOnFailureListener(e -> {
                    messageError.setValue(e.getMessage());
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
