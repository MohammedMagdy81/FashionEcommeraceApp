package ddd.magdy.fashione_commerace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import ddd.magdy.fashione_commerace.R;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<ProductResponseItem> productResponseItemList;


    public CategoryAdapter(Context context, List<ProductResponseItem> productResponseItemList) {
        this.context = context;
        this.productResponseItemList = productResponseItemList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ProductResponseItem product = productResponseItemList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productResponseItemList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageProduct;
        TextView categoryNameProduct,categoryNumProduct;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct= itemView.findViewById(R.id.image_view);
            categoryNameProduct= itemView.findViewById(R.id.item_category_name);
            categoryNumProduct= itemView.findViewById(R.id.item_category_number);
        }

        public void bind(ProductResponseItem product) {
            Glide.with(itemView)
                    .load(product.getImage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade(400))
                    .into(imageProduct);
            categoryNameProduct.setText(product.getCategory());
            categoryNumProduct.setText(product.getRating().getCount()+" Product");
        }
    }
}
