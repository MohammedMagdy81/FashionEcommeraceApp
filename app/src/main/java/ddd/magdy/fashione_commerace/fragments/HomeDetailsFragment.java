package ddd.magdy.fashione_commerace.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.adapter.CategoryDetailsAdapter;
import ddd.magdy.fashione_commerace.databinding.FragmentHomeDetailsBinding;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import ddd.magdy.fashione_commerace.viewmodels.CategoryDetailsViewModel;

public class HomeDetailsFragment extends Fragment implements CategoryDetailsAdapter.OnItemDetailClickListener,
        CategoryDetailsAdapter.OnItemFavoriteClick {

    private FragmentHomeDetailsBinding binding;
    private CategoryDetailsAdapter adapter;
    private CategoryDetailsViewModel viewModel;
    private Bundle bundle;
    private ProductDetailFragment fragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CategoryDetailsAdapter(requireContext());
        viewModel = new ViewModelProvider((ViewModelStoreOwner) requireContext()).get(CategoryDetailsViewModel.class);
        bundle = new Bundle();
        fragment = new ProductDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.onItemDetailClickListener = this::onItemDetailClick;
        adapter.itemFavoriteClick = this::onItemFavoriteClick;
        Bundle bundle = this.getArguments();
        ProductResponseItem item = (ProductResponseItem) bundle.getSerializable(Constant.PRODUCT_ITEM_KEY);
        binding.homeDetailProductName.setText(item.getCategory());
        viewModel.getItemsByCategory(item.getCategory());
        observeToField();

        setUpClickListener();

    }

    private void setUpClickListener() {
        binding.imageIconBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .popBackStack();
        });
    }

    private void observeToField() {
        viewModel.showLoading.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                binding.homeDetailProgress.setVisibility(View.VISIBLE);
            } else {
                binding.homeDetailProgress.setVisibility(View.INVISIBLE);
            }
        });
        viewModel.messageError.observe(getViewLifecycleOwner(), message -> {
            showAlertDialog(message);
        });
        viewModel.getProductResponseItemMutableLiveData().observe(getViewLifecycleOwner(), productResponseItems -> {
            binding.homeDetailsRv.setAdapter(adapter);
            adapter.setData(productResponseItems);
        });
    }

    private void showAlertDialog(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(message);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.create().show();
    }

    @Override
    public void onItemDetailClick(int position, ProductResponseItem item) {
        bundle.putSerializable(Constant.PRODUCT_DETAIL_ITEM_KEY, item);
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemFavoriteClick(ProductResponseItem item) {
        viewModel.addItemToDB(item, requireContext());
        Toast.makeText(requireContext(), "item Added Successfully to Wishlist", Toast.LENGTH_SHORT).show();
    }
}






