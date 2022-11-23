package ddd.magdy.fashione_commerace.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.databinding.FragmentSettingsBinding;
import ddd.magdy.fashione_commerace.ui.splash.SplashActivity;
import ddd.magdy.fashione_commerace.viewmodels.SettingsViewModel;


public class SettingsFragment extends Fragment implements SettingsNavigator {

    private FragmentSettingsBinding binding;

    private Uri imageUri;
    private SettingsViewModel viewModel;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        progressDialog = new ProgressDialog(requireContext());
        viewModel.navigator = this;
        setUpClickListener();
        observeToFields();
        viewModel.getUserInfo();
        setupRadioBtnShape();
    }

    private void observeToFields() {
        viewModel.showDialog.observe(getViewLifecycleOwner(), show -> {
            if (show) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
            }
        });

        viewModel.showTitleDialog.observe(getViewLifecycleOwner(), title -> {
            progressDialog.setTitle(title);
        });
        viewModel.showMessageDialog.observe(getViewLifecycleOwner(), message -> {
            progressDialog.setMessage(message);
        });
        viewModel.showMessage.observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
        });
        viewModel.imageProfile.observe(getViewLifecycleOwner(), imageString -> {
            Glide.with(requireContext())
                    .load(imageString)
                    .into(binding.settingsProfileImage);
        });
        viewModel.userNameProfile.observe(getViewLifecycleOwner(), userName -> {
            binding.settingsEtName.setHint(userName);
            binding.settingsEtName.setClickable(false);
            binding.settingsEtName.setEnabled(false);
        });
        viewModel.emailProfile.observe(getViewLifecycleOwner(), email -> {
            binding.settingsEtEmail.setHint(email);
            binding.settingsEtEmail.setClickable(false);
            binding.settingsEtEmail.setEnabled(false);
        });


    }

    private void setupRadioBtnShape() {
        binding.settingsMaleRadiobtn.setClickable(false);
        binding.settingsMaleRadiobtn.setChecked(true);
        binding.settingsFemaleRadiobtn.setClickable(false);
    }

    private void setUpClickListener() {
        binding.settingsLayoutPick.setOnClickListener(v -> {
            pickImageFromGallery();
        });

        binding.settingsLayoutLogout.setOnClickListener(view -> {
            showAlertDialog();
        });

        binding.settingsSwitchDarkMode.setOnCheckedChangeListener((compoundButton, checked) -> {
            if (checked) {
                binding.settingsTextSwitch.setText("on");

            } else {
                binding.settingsTextSwitch.setText("off");
            }
        });
        binding.settingsLayoutLanguage.setOnClickListener(v -> {
            goToLanguageFragment();
        });
        binding.settingsEtAge.setHint(String.valueOf(24));
        binding.settingsEtAge.setClickable(false);
        binding.settingsEtAge.setEnabled(false);

    }

    private void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are You Want to Logout From Fashion App ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.logout();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void goToLanguageFragment() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, new LanguageFragment())
                .addToBackStack(null)
                .commit();
    }


    private void pickImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            if (null != imageUri) {
                imageUri = data.getData();
                try {
                    // Setting image on image view using Bitmap
                    Bitmap bitmap = MediaStore
                            .Images
                            .Media
                            .getBitmap(requireActivity().getContentResolver(), imageUri);
                    binding.settingsProfileImage.setImageBitmap(bitmap);
                    viewModel.uploadImage(imageUri);

                } catch (IOException e) {
                    // Log the exception
                    e.printStackTrace();
                }

            }

        }
    }

    @Override
    public void navigateToSplash() {
        Intent intent = new Intent(requireActivity(), SplashActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
