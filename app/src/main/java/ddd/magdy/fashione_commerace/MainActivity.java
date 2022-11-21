package ddd.magdy.fashione_commerace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import ddd.magdy.fashione_commerace.databinding.ActivityMainBinding;
import ddd.magdy.fashione_commerace.fragments.CartShoppingFragment;
import ddd.magdy.fashione_commerace.fragments.HomeFragment;
import ddd.magdy.fashione_commerace.fragments.NotificationFragment;
import ddd.magdy.fashione_commerace.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //set dark theme
            setTheme(R.style.Theme_Dark);
        } else {
            setTheme(R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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