package ddd.magdy.fashione_commerace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentMessagesBinding;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessagesBinding.inflate(inflater);
        return binding.getRoot();
    }
}