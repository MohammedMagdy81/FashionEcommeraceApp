package ddd.magdy.fashione_commerace.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import ddd.magdy.fashione_commerace.model.LoginAuthUser;
import ddd.magdy.fashione_commerace.model.LoginResponse;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.model.User;
import ddd.magdy.fashione_commerace.network.RetrofitApi;
import ddd.magdy.fashione_commerace.network.RetrofitClient;
import ddd.magdy.fashione_commerace.ui.signup.SignUpNavigator;
import ddd.magdy.fashione_commerace.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends ViewModel {

    public MutableLiveData<String> messageError = new MutableLiveData<>();
    public MutableLiveData<String> messageFocus = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData();

    public SignUpNavigator navigator;

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public void createUserWithEmailAndPassword(String email, String password,
                                               String confirmPassword, String userName) {


        if (!checkValidation(email, userName, password, confirmPassword)) return;
        showLoading.setValue(true);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(authResult -> {
                    showLoading.setValue(false);
                    if (authResult.isSuccessful()) {
                        addUserToFirestore(auth.getCurrentUser().getUid(), email, userName);
                        Constant.user = new User(userName, email, "", auth.getCurrentUser().getUid());
                    } else {
                        messageError.setValue(authResult.getException().getLocalizedMessage());
                    }

                })
                .addOnFailureListener(e -> {
                    showLoading.setValue(false);
                    messageError.setValue(e.getMessage());
                });

    }


    private void addUserToFirestore(String uid, String email, String userName) {
        User user = new User(userName, email, "", uid);
        firestore.collection(Constant.USER_COLLECTION).document(uid)
                .set(user)
                .addOnCompleteListener(task -> {
                    showLoading.setValue(false);
                    navigator.goToHome();
                })
                .addOnFailureListener(e -> {
                    messageError.setValue(e.getLocalizedMessage());
                });
    }


    public boolean checkValidation(String email, String userName, String password, String confirmPassword) {
        if (email.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageFocus.setValue("Complete All information please !");
            return false;

        }
        if (password.length() < 6) {
            messageFocus.setValue("Password Must be 6 char at least !");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            messageFocus.setValue("Password does not math !");
            return false;
        }
        return true;
    }

}










