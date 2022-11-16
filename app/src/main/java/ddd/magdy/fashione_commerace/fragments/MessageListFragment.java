package ddd.magdy.fashione_commerace.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentMessageListBinding;

public class MessageListFragment extends Fragment {

    private FragmentMessageListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessageListBinding.inflate(inflater);
        return binding.getRoot();
    }
}