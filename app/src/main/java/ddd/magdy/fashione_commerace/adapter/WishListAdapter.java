package ddd.magdy.fashione_commerace.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {

    List<ProductResponseItem> productResponseItems;

    public WishListAdapter(List<ProductResponseItem> productResponseItems) {
        this.productResponseItems = productResponseItems;
    }

    public void setData(List<ProductResponseItem> productResponseItems) {
        this.productResponseItems = productResponseItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WishListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {

        holder.bind(productResponseItems.get(position));
    }

    @Override
    public int getItemCount() {
        return productResponseItems.size();
    }

    class WishListViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemTitle, itemDesc, itemPrice, itemAddToCart;

        public WishListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_cart_image);
            itemTitle = itemView.findViewById(R.id.item_cart_item_name);
            itemDesc = itemView.findViewById(R.id.item_cart_item_desc);
            itemPrice = itemView.findViewById(R.id.item_cart_item_price);
            itemAddToCart = itemView.findViewById(R.id.item_favorite_add_tocart_text);
        }

        public void bind(ProductResponseItem item) {
            Glide.with(itemView)
                    .load(item.getImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(200))
                    .into(itemImage);
        }
    }
}
