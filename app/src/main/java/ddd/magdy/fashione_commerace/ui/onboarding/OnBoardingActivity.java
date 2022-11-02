package ddd.magdy.fashione_commerace.ui.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.ui.splash.SplashActivity2;

public class OnBoardingActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layout;
    View view;
    ViewPager2 pager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        layout = findViewById(R.id.layout_indicator);

        setOnBoardingItems();

        pager2 = findViewById(R.id.onboarding_pager);
        view = findViewById(R.id.view);
        pager2.setAdapter(onBoardingAdapter);
        setUpClickListener();
        setLayoutIndicator();


        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setCurrentIndicator(position);
            }
        });
    }

    private void setUpClickListener() {
        view.setOnClickListener(v -> {
            if (pager2.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                pager2.setCurrentItem(pager2.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(OnBoardingActivity.this, SplashActivity2.class));
                finish();
            }
        });
    }

    private void setOnBoardingItems() {
        List<ItemOnBoarding> itemOnBoardingList = new ArrayList<>();
        ItemOnBoarding item1 = new ItemOnBoarding(
                R.drawable.onboarding_image1,
                getResources().getString(R.string.onboarding_title),
                getResources().getString(R.string.onboarding_desc)
        );
        ItemOnBoarding item2 = new ItemOnBoarding(
                R.drawable.onboarding_image2,
                getResources().getString(R.string.onboarding_titlee),
                getResources().getString(R.string.onboarding_desc)
        );
        ItemOnBoarding item3 = new ItemOnBoarding(
                R.drawable.onboarding_image3,
                getResources().getString(R.string.onboarding_titleee),
                getResources().getString(R.string.onboarding_desc)
        );
        itemOnBoardingList.add(item1);
        itemOnBoardingList.add(item2);
        itemOnBoardingList.add(item3);

        onBoardingAdapter = new OnBoardingAdapter(itemOnBoardingList);

    }

    private void setLayoutIndicator() {
        // count of images that display in layout
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(16, 0, 16, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicatior_inactive
            ));
            indicators[i].setLayoutParams(params);
            layout.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int index) {
        int childCount = layout.getChildCount(); // 3

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layout.getChildAt(i);
            if (index == i) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.onboarding_indicatior_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.onboarding_indicatior_inactive
                ));
            }
        }
    }
}





