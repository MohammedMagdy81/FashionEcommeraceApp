package ddd.magdy.fashione_commerace.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import ddd.magdy.fashione_commerace.utils.Constant;

public class ProfileViewModel extends ViewModel {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userImage = new MutableLiveData<>();

    public void getUserData() {
        firestore.collection(Constant.USER_COLLECTION).document(auth.getCurrentUser().getUid())
                .addSnapshotListener((snapshot, error) -> {
                    if (snapshot.exists()) {
                        if (snapshot.contains("imagePath")) {
                            userImage.postValue(snapshot.get("imagePath").toString());
                        }
                        if (snapshot.contains("email")) {
                            email.postValue(snapshot.get("email").toString());
                        }
                        if (snapshot.contains("userName")) {
                            userName.postValue(snapshot.get("userName").toString());
                        }
                    }
                });
    }

}
