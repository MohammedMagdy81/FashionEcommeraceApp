package ddd.magdy.fashione_commerace.adapter;

import android.content.Context;
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
import ddd.magdy.fashione_commerace.database.ProductItem;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.model.ProductsItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    List<ProductResponseItem> items;
    Context context;

    public CartAdapter(List<ProductResponseItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public void setData(List<ProductResponseItem> items){
        this.items= items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemTitle,itemDesc,itemPrice,itemNumberAdded;
        ImageButton btnAdded,btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_cart_image);
            itemTitle = itemView.findViewById(R.id.item_cart_item_name);
            itemDesc = itemView.findViewById(R.id.item_cart_item_desc);
            itemPrice = itemView.findViewById(R.id.item_cart_item_price);
            itemNumberAdded = itemView.findViewById(R.id.item_cart_number_added);
            btnAdded = itemView.findViewById(R.id.item_cart_btn_add);
            btnRemove = itemView.findViewById(R.id.item_cart_btn_remove);
        }

        public void bind(ProductResponseItem item) {
            Glide.with(itemView)
                    .load(item.getImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(400))
                    .into(itemImage);
            itemTitle.setText(item.getTitle());
            itemDesc.setText(item.getDescription());
            itemPrice.setText("$ "+item.getPrice());
            itemNumberAdded.setText(String.valueOf(1));

        }
    }
}
