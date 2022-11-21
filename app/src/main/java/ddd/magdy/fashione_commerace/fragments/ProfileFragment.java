package ddd.magdy.fashione_commerace.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentProfileBinding;
import ddd.magdy.fashione_commerace.viewmodels.ProfileViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        viewModel.getUserData();
        setUpClick();
        observeToData();
    }

    private void observeToData() {
        viewModel.email.observe(getViewLifecycleOwner(), email -> {
            if (!email.isEmpty()){
                binding.profileEmail.setText(email);
            }
        });
        viewModel.userName.observe(getViewLifecycleOwner(), userName -> {
           if (!userName.isEmpty()){
               binding.profileName.setText(userName);
           }
        });
        viewModel.userImage.observe(getViewLifecycleOwner(),userImage->{

            Glide.with(requireContext())
                    .load(userImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(400))
                    .into(binding.layoutProfileImage);
        });

    }

    private void setUpClick() {
        binding.profileIconSettings.setOnClickListener(v -> {
            replaceFragment(new SettingsFragment());
        });
        binding.profileLayoutSettings.setOnClickListener(v -> {
            replaceFragment(new SettingsFragment());
        });
        binding.profileLayoutMyFavorites.setOnClickListener(v -> {
            replaceFragment(new FavoriteFragment());
        });
    }


    void replaceFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }


}





