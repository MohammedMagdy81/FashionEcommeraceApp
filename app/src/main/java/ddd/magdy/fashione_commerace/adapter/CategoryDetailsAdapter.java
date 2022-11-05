package ddd.magdy.fashione_commerace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder>{

    List<ProductResponseItem> productResponseItems;
    Context context;

    public CategoryDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setData( List<ProductResponseItem> productResponseItems){
        this.productResponseItems=productResponseItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_details,parent,false);
        return new CategoryDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsViewHolder holder, int position) {

        ProductResponseItem product= productResponseItems.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productResponseItems.size();
    }

    class CategoryDetailsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageItem;
        TextView mItemName,mItemPrice,mItemDesc;


        public CategoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.item_image_product_detail);
            mItemName = itemView.findViewById(R.id.item__product_name_detail);
            mItemPrice = itemView.findViewById(R.id.item__product_price_detail);
            mItemDesc = itemView.findViewById(R.id.item__product_desc_detail);
        }


        public void bind(ProductResponseItem product) {
                Glide.with(itemView)
                        .load(product.getImage())
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(400))
                        .into(imageItem);
                mItemName.setText(product.getTitle());
                mItemDesc.setText(product.getDescription());
                mItemPrice.setText("$ "+product.getPrice());

        }
    }
}
