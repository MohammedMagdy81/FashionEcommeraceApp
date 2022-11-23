package ddd.magdy.fashione_commerace.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.adapter.MessagesAdapter;
import ddd.magdy.fashione_commerace.databinding.FragmentMessagesBinding;
import ddd.magdy.fashione_commerace.model.Message;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import ddd.magdy.fashione_commerace.viewmodels.MessagesViewModel;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    private Bundle bundle;
    private ResponseUserItem userItem;
    private MessagesViewModel viewModel;
    private MessagesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();
        userItem = (ResponseUserItem) bundle.getSerializable(Constant.ITEM_CHAT);
        viewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        adapter = new MessagesAdapter(new ArrayList<>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessagesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpData();
        binding.fragmentMessageMessages.setHasFixedSize(true);
        binding.fragmentMessageMessages.setAdapter(adapter);

        setUpClick();
        subscripeToMessages();
    }

    private void setUpClick() {
        binding.fragmentMessageIconSend.setOnClickListener(view -> {
            if (binding.fragmenEtMessage.getText().length() > 1) {
                viewModel.sendMessage(binding.fragmenEtMessage.getText().toString());
                binding.fragmenEtMessage.setText("");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void subscripeToMessages() {
        viewModel.getMessagesRef().orderBy("timeStamp")
                .addSnapshotListener((snapshot, error) -> {
                    if (error != null) {
                        Toast.makeText(requireContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                    List<Message> messageList = new ArrayList<>();
                    snapshot.getDocumentChanges().forEach(dc -> {
                        switch (dc.getType()) {
                            case ADDED:
                                Message message = dc.getDocument().toObject(Message.class);
                                messageList.add(message);
                                break;

                        }
                    });
                    adapter.setData(messageList);
                    if (adapter.messageList.size() > 3) {
                        binding.fragmentMessageMessages.smoothScrollToPosition(adapter.messageList.size() - 1);
                    }

                });
    }

    private void setUpData() {
        binding.fragmentMessageName.setText(userItem.getUsername());
        binding.fragmentMessageImage.setImageResource(R.drawable.image_review4);
    }

}