package ddd.magdy.fashione_commerace.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentProductDetailBinding;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import ddd.magdy.fashione_commerace.viewmodels.CartViewModel;

public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding binding;
    private int numOfItem = 1;
    ProductResponseItem productResponseItem;
    double totalPrice = 1;
    private CartViewModel viewModel;

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
        viewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        setUpClickListener();
        receivedData();

    }


    private void receivedData() {
        Bundle bundle = this.getArguments();
        productResponseItem = (ProductResponseItem) bundle.getSerializable(Constant.PRODUCT_DETAIL_ITEM_KEY);
        setUpData(productResponseItem);
    }

    private void setUpData(ProductResponseItem product) {
        binding.productDetailProductPrice.setText("$ " + product.getPrice());
        binding.productDetailRating.setRating((float) product.getRating().getRate());
        binding.productDetailTextReview.setText("(" + product.getRating().getCount() + " review)");
        binding.productDetailProductName.setText(product.getTitle());
        binding.productDetailProductDesc.setText(product.getTitle());
        binding.productDetailProductDescription.setText(product.getDescription());
        Glide.with(binding.productDetailImage)
                .load(product.getImage())
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .into(binding.productDetailImage);

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

        binding.productDetailBtnAdd.setOnClickListener(v -> {
            binding.productDetailNumberAdded.setText(String.valueOf(++numOfItem));
            totalPrice = numOfItem * productResponseItem.getPrice();
            binding.productDetailProductPrice.setText("$ " + totalPrice);

        });
        binding.productDetailBtnRemove.setOnClickListener(v -> {
            if (numOfItem == 1) {
                binding.productDetailNumberAdded.setText(String.valueOf(1));
            } else {
                binding.productDetailNumberAdded.setText(String.valueOf(--numOfItem));
                totalPrice = numOfItem * productResponseItem.getPrice();
                binding.productDetailProductPrice.setText("$ " + totalPrice);
            }

        });
        binding.productDetailLayoutAddToCartSmall.setOnClickListener(v -> {


        });
        binding.productDetailColor1.setOnClickListener(v -> {
            changeButtonsColor(binding.productDetailColor1, binding.productDetailColor2, binding.productDetailColor3,
                    binding.productDetailColor4);
        });
        binding.productDetailColor2.setOnClickListener(v -> {
            changeButtonsColor(binding.productDetailColor2, binding.productDetailColor1, binding.productDetailColor3,
                    binding.productDetailColor4);
        });
        binding.productDetailColor3.setOnClickListener(v -> {
            changeButtonsColor(binding.productDetailColor3, binding.productDetailColor2, binding.productDetailColor1,
                    binding.productDetailColor4);
        });
        binding.productDetailColor4.setOnClickListener(v -> {
            changeButtonsColor(binding.productDetailColor4, binding.productDetailColor2, binding.productDetailColor3,
                    binding.productDetailColor1);
        });

        binding.productDetailIconFavorite.setOnClickListener(v -> {
            viewModel.addToDB(requireContext(), productResponseItem);
            Toast.makeText(requireContext(), "Item Added Successfully", Toast.LENGTH_SHORT).show();

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

    void changeButtonsColor(ImageButton btn1, ImageButton btn2, ImageButton btn3, ImageButton btn4) {
        btn1.setImageDrawable(ContextCompat
                .getDrawable(requireContext(), R.drawable.ic_check));
        btn2.setImageDrawable(null);
        btn3.setImageDrawable(null);
        btn4.setImageDrawable(null);
    }
}






























