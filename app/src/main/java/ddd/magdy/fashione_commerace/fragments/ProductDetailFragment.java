package ddd.magdy.fashione_commerace.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpClickListener();
    }

    private void setUpClickListener() {
        binding.productDetailView.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .popBackStack();
        });
        binding.productDetailLayoutSizes1.setOnClickListener(v -> {
            setBackDrawable(binding.productDetailLayoutSizes1, binding.productDetailLayoutSizes2, binding.productDetailLayoutSizes3);
        });
        binding.productDetailLayoutSizes2.setOnClickListener(v -> {
            setBackDrawable(binding.productDetailLayoutSizes2, binding.productDetailLayoutSizes1, binding.productDetailLayoutSizes3);
        });
        binding.productDetailLayoutSizes3.setOnClickListener(v -> {
            setBackDrawable(binding.productDetailLayoutSizes3, binding.productDetailLayoutSizes2, binding.productDetailLayoutSizes1);
        });
    }

    void setBackDrawable(View view1, View view2, View view3) {
        view1.setBackground(ContextCompat.
                getDrawable(requireContext(), R.drawable.product_detail_size_active));
        TextView textView1 = (TextView) view1;
        textView1.setTextColor(Color.WHITE);

        view2.setBackground(ContextCompat.
                getDrawable(requireContext(), R.drawable.product_detail_size_inactive));
        TextView textView2 = (TextView) view2;
        textView2.setTextColor(Color.BLACK);

        view3.setBackground(ContextCompat.
                getDrawable(requireContext(), R.drawable.product_detail_size_inactive));
        TextView textView3 = (TextView) view3;
        textView3.setTextColor(Color.BLACK);
    }
}














