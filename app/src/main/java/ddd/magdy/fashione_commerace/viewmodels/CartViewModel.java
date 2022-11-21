package ddd.magdy.fashione_commerace.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import ddd.magdy.fashione_commerace.database.ProductDatabase;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;

public class CartViewModel extends ViewModel {

    public void addToDB(Context context, ProductResponseItem item) {
        ProductDatabase.getInstance(context)
                .productDao()
                .addProductItem(item);
    }

}
