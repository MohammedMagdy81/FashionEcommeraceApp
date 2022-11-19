package ddd.magdy.fashione_commerace.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import ddd.magdy.fashione_commerace.databinding.FragmentCartShoppingBinding;
import ddd.magdy.fashione_commerace.viewmodels.CartViewModel;

public class CartShoppingFragment extends Fragment {


    private FragmentCartShoppingBinding binding;
    private CartViewModel viewModel;
    private double totalPrice = 0;
    private int totalCountIte = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartShoppingBinding.inflate(inflater);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        //viewModel.getCartItems();
        viewModel.getItems().observe(getViewLifecycleOwner(), productResponseItemList -> {
        });











        //viewModel.getCartItems();
//        viewModel.getAllProduct(requireContext()).observe(getViewLifecycleOwner(), productItems -> {
//            if (productItems.size()==0){
//                binding.cartShoppingCardView.setVisibility(View.INVISIBLE);
//                binding.cartShoppingBtnProceedToCheckout.setVisibility(View.INVISIBLE);
//            }
//            adapter.setData(productItems);
//            productItems.forEach(item -> {
//                totalPrice += item.getPrice();
//                totalCountIte += item.getCount();
//            });
//            binding.cartShoppingSubtotalTxtPrice.setText("$ " + String.format("%.2f", totalPrice));
//            binding.cartShippingTxtPrice.setText("$ 20");
//            totalPrice += 20;
//            binding.cartShoppingBagtotalTxtPrice.setText("$ " +String.format("%.2f", totalPrice));
//            binding.cartShoppingItemCount.setText("( " + totalCountIte + " item)");
//
//        });

    }

    private void setRecyclerView() {
        binding.cartShoppingItemRv.setHasFixedSize(true);
    }
}








