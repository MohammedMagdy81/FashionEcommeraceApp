package ddd.magdy.fashione_commerace.fragments;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.adapter.WishListAdapter;
import ddd.magdy.fashione_commerace.databinding.FragmentFavoriteBinding;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.viewmodels.WishListViewModel;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private WishListAdapter adapter;
    private WishListViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new WishListAdapter(new ArrayList<>());
        viewModel = new ViewModelProvider(requireActivity()).get(WishListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.favoriteRecyclerView.setAdapter(adapter);
        binding.favoriteRecyclerView.setHasFixedSize(true);
        viewModel.getAllFavoriteList(requireContext()).observe(getViewLifecycleOwner(), productResponseItemList -> {
            adapter.setData(productResponseItemList);
        });

        swipeToDelete();

    }

    private void swipeToDelete() {
    }


}
















