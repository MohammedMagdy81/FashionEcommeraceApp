package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ReviewModel;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    List<ReviewModel> reviewModelList = new ArrayList<>();

    public ReviewAdapter(List<ReviewModel> reviewModelList) {
        this.reviewModelList = reviewModelList;
    }

    public void setData(List<ReviewModel> reviewModelList){
        this.reviewModelList=reviewModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.bind(reviewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView imageReview;
        TextView personName, personReview, time;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageReview = itemView.findViewById(R.id.image_review);
            personName = itemView.findViewById(R.id.person_name_review);
            personReview = itemView.findViewById(R.id.person_review);
            time = itemView.findViewById(R.id.text_time_review);
        }

        public void bind(ReviewModel reviewModel) {
            imageReview.setImageResource(reviewModel.getImage());
            personName.setText(reviewModel.getPersonName());
            personReview.setText(reviewModel.getPersonReview());
            time.setText(reviewModel.getTime());
        }
    }
}










