package ddd.magdy.fashione_commerace.viewmodels;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.database.ProductDatabase;
import ddd.magdy.fashione_commerace.database.ProductItem;
import ddd.magdy.fashione_commerace.model.CartItem;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    private MutableLiveData<List<ProductResponseItem>> items = new MutableLiveData<>();
    List<ProductResponseItem> itemList = new ArrayList<>();

    public void getCartItems() {
        RetrofitClient.getRetrofitClientInstance()
                .getApi()
                .getProductsInCarts()
                .enqueue(new Callback<List<CartItem>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                        response.body().forEach(cartItem -> {
                            RetrofitClient
                                    .getRetrofitClientInstance().getApi()
                                    .getProductById(cartItem.getProducts().get(0).getProductId())
                                    .enqueue(new Callback<ProductResponseItem>() {
                                        @Override
                                        public void onResponse(Call<ProductResponseItem> call, Response<ProductResponseItem> response) {
                                            itemList.add(response.body());
                                            items.setValue(itemList);
                                        }

                                        @Override
                                        public void onFailure(Call<ProductResponseItem> call, Throwable t) {

                                        }
                                    });

                        });

                    }

                    @Override
                    public void onFailure(Call<List<CartItem>> call, Throwable t) {

                    }
                });
    }



    public void addProduct(ProductItem productItem, Context context){
        showLoading.setValue(true);
        ProductDatabase.getInstance(context).productDao().addProductItem(productItem);
        showLoading.setValue(false);
        Toast.makeText(context, "Product Added To Cart Successfully", Toast.LENGTH_SHORT).show();

    }

    public LiveData<List<ProductItem>> getAllProduct(Context context){
       return ProductDatabase.getInstance(context).productDao().getAllProductItem();
    }



    public MutableLiveData<List<ProductResponseItem>> getItems() {
        return items;
    }
}
