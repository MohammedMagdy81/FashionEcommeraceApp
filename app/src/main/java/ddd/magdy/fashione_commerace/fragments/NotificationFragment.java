package ddd.magdy.fashione_commerace.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.adapter.ReviewAdapter;
import ddd.magdy.fashione_commerace.databinding.FragmentNotificationBinding;
import ddd.magdy.fashione_commerace.model.ReviewModel;
import ddd.magdy.fashione_commerace.viewmodels.NotificationViewModel;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;
    private ReviewAdapter adapter;
    private NotificationViewModel viewModel;
    private List<ReviewModel> reviewModelList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(NotificationViewModel.class);
        reviewModelList = viewModel.reviewModelList();
        adapter = new ReviewAdapter(new ArrayList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.reviewsRv.setAdapter(adapter);
        binding.reviewsRv.setHasFixedSize(true);
        adapter.setData(reviewModelList);
    }


}
