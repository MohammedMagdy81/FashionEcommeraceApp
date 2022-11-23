package ddd.magdy.fashione_commerace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import ddd.magdy.fashione_commerace.databinding.ActivityMainBinding;
import ddd.magdy.fashione_commerace.fragments.CartShoppingFragment;
import ddd.magdy.fashione_commerace.fragments.HomeFragment;
import ddd.magdy.fashione_commerace.fragments.NotificationFragment;
import ddd.magdy.fashione_commerace.fragments.ProfileFragment;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.viewmodels.SignupViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SignupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setItemSelected(R.id.home_item, true);
        binding.bottomNavigation.setOnItemSelectedListener(id -> {
            switch (id) {
                case R.id.home_item:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.cart_item:
                    replaceFragment(new CartShoppingFragment());
                    break;
                case R.id.notification_item:
                    replaceFragment(new NotificationFragment());
                    break;
                case R.id.person_item:
                    replaceFragment(new ProfileFragment());
                    break;

            }
        });




    }

    void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
    }
}