package ddd.magdy.fashione_commerace.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.adapter.MessageListAdapter;
import ddd.magdy.fashione_commerace.adapter.MessageListAdapterHorizontal;
import ddd.magdy.fashione_commerace.databinding.FragmentMessageListBinding;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import ddd.magdy.fashione_commerace.viewmodels.MessageListViewModel;

public class MessageListFragment extends Fragment implements MessageListAdapter.OnMessagesListItemClick,
        MessageListAdapterHorizontal.OnMessageListItemClick {

    private FragmentMessageListBinding binding;
    private MessageListAdapter adapter;
    private MessageListAdapterHorizontal adapterHorizontal;
    private MessageListViewModel viewModel;
    private MessagesFragment fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MessageListAdapter(new ArrayList<>());
        adapterHorizontal = new MessageListAdapterHorizontal(new ArrayList<>());

        adapter.onMessagesListItemClick = this::onMessageItemClick;
        adapterHorizontal.onMessageListItemClick = this::onItemClick;
        viewModel = new ViewModelProvider(requireActivity()).get(MessageListViewModel.class);
        fragment = new MessagesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessageListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUsers();
        viewModel.getUsersList().observe(getViewLifecycleOwner(), responseUserItems -> {
            adapter.setData(responseUserItems);
            adapterHorizontal.setData(responseUserItems);

            binding.messageListVerticalRv.setHasFixedSize(true);
            binding.messageListVerticalRv.setAdapter(adapter);

            binding.messageListHorzontalRv.setHasFixedSize(true);
            binding.messageListHorzontalRv.setAdapter(adapterHorizontal);
        });

    }

    @Override
    public void onMessageItemClick(int position, ResponseUserItem userItem) {
        goToMessagesChat(userItem);
    }

    @Override
    public void onItemClick(int position, ResponseUserItem userItem) {
        goToMessagesChat(userItem);
    }


    void goToMessagesChat(ResponseUserItem userItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.ITEM_CHAT, userItem);
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_fragment, fragment)
                .commit();
    }
}












