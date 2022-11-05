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
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.adapter.CategoryAdapter;
import ddd.magdy.fashione_commerace.databinding.FragmentHomeBinding;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import ddd.magdy.fashione_commerace.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CategoryAdapter adapter;
    private HomeViewModel viewModel;
    private HomeDetailsFragment fragment;
    Bundle bundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CategoryAdapter(requireContext());
        fragment = new HomeDetailsFragment();
        bundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(HomeViewModel.class);
        viewModel.getProducts();
        observeToField();

    }


    private void showAlertDialog(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(message);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }


    private void observeToField() {
        viewModel.showLoading.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                binding.homeProgress.setVisibility(View.VISIBLE);
            } else {
                binding.homeProgress.setVisibility(View.INVISIBLE);
            }
        });
        viewModel.messageError.observe(getViewLifecycleOwner(), message -> {
            showAlertDialog(message);
        });

        viewModel.getProductResponseItemMutableLiveData().observe(getViewLifecycleOwner(), productResponseItems -> {
            adapter.setData(productResponseItems);
            binding.categoryRecyclerView.setAdapter(adapter);

        });
        adapter.onCategoryItemClick = (product, position) -> {
            bundle.putSerializable(Constant.PRODUCT_ITEM_KEY, product);
            fragment.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, fragment)
                    .commit();
        };


    }


}

