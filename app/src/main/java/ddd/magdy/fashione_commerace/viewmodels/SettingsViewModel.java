package ddd.magdy.fashione_commerace.viewmodels;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

import ddd.magdy.fashione_commerace.utils.Constant;

public class SettingsViewModel extends ViewModel {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private StorageTask task;

    private String myUri = "";
    public MutableLiveData<String> imageProfile = new MutableLiveData<>();
    public MutableLiveData<String> userNameProfile = new MutableLiveData<>();
    public MutableLiveData<String> emailProfile = new MutableLiveData<>();

    public MutableLiveData<String> showTitleDialog = new MutableLiveData<>();
    public MutableLiveData<String> showMessageDialog = new MutableLiveData<>();
    public MutableLiveData<String> showMessage = new MutableLiveData<>();
    public MutableLiveData<Boolean> showDialog = new MutableLiveData<>(false);

    public void uploadImage(Uri imageUri) {
        if (imageUri != null) {
            showDialog.postValue(true);
            showTitleDialog.postValue("Uploading...");
            // Defining the child of storageReference
            StorageReference ref
                    = storageReference.child(auth.getCurrentUser().getUid() + ".jpg");
            task = ref.putFile(imageUri)
                    .addOnProgressListener(snapshot -> {
                        double progress
                                = (100.0 * snapshot.getBytesTransferred()
                                / snapshot.getTotalByteCount());
                        showMessageDialog.postValue("Uploaded " + (int) progress + "%");
                    });
            task.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    showMessage.postValue("Failed" + task.getException().toString());
                }
                return ref.getDownloadUrl();
            })
                    .addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            myUri = downloadUri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("imagePath", myUri);
                            firestore.collection(Constant.USER_COLLECTION)
                                    .document(auth.getCurrentUser().getUid())
                                    .update(map);
                            showDialog.postValue(false);
                        }
                    });
            getUserInfo();
        } else {
            showDialog.postValue(false);
            showMessage.postValue("Image Not Selected !");
        }


    }

    public void getUserInfo() {
        firestore.collection(Constant.USER_COLLECTION).document(auth.getCurrentUser().getUid())
                .addSnapshotListener((snapshot, error) -> {
                    if (snapshot.exists()) {
                        if (snapshot.contains("imagePath")) {
                            imageProfile.postValue(snapshot.get("imagePath").toString());
                        }
                        if (snapshot.contains("email")) {
                            emailProfile.postValue(snapshot.get("email").toString());
                        }
                        if (snapshot.contains("userName")) {
                            userNameProfile.postValue(snapshot.get("userName").toString());
                        }
                    }
                });
    }

}




