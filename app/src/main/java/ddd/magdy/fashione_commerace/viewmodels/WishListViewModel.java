package ddd.magdy.fashione_commerace.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ddd.magdy.fashione_commerace.database.ProductDatabase;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;

public class WishListViewModel extends ViewModel {


   public LiveData<List<ProductResponseItem>> getAllFavoriteList(Context context){
       return ProductDatabase.getInstance(context)
               .productDao()
               .getAllProductItem();
   }
}
