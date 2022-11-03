package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ItemOnBoarding;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {

    private List<ItemOnBoarding> itemOnBoardingList;

    public OnBoardingAdapter(List<ItemOnBoarding> itemOnBoardingList) {
        this.itemOnBoardingList = itemOnBoardingList;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contianer_boarding, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(itemOnBoardingList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemOnBoardingList.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextTitle, mTextDesc;

        public OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.onboarding_image);
            mTextTitle = itemView.findViewById(R.id.onboardingTitle);
            mTextDesc = itemView.findViewById(R.id.onboardingDesc);
        }

        void setOnBoardingData(ItemOnBoarding itemOnBoarding) {
            mImageView.setImageResource(itemOnBoarding.getImage());
            mTextTitle.setText(itemOnBoarding.getTitle());
            mTextDesc.setText(itemOnBoarding.getDesc());
        }
    }
}
