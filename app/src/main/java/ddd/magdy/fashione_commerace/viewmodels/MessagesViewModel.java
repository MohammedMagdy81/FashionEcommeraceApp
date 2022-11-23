package ddd.magdy.fashione_commerace.viewmodels;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import ddd.magdy.fashione_commerace.model.Message;
import ddd.magdy.fashione_commerace.utils.Constant;

public class MessagesViewModel extends ViewModel {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    public void sendMessage(String messageText) {
        Message message = new Message(messageText, Timestamp.now(), Constant.user.getId(), Constant.user.getUserName());
        firestore.collection(Constant.USER_COLLECTION).document(auth.getCurrentUser().getUid())
                .collection(Constant.MESSAGES_COLLECTION)
                .add(message)
                .addOnSuccessListener(documentReference -> {

                });
    }
    public CollectionReference getMessagesRef() {
        return firestore.collection(Constant.USER_COLLECTION)
                .document(auth.getCurrentUser().getUid())
                .collection(Constant.MESSAGES_COLLECTION);
    }
}
