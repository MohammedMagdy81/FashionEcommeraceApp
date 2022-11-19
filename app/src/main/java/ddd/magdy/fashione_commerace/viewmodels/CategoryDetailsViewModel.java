package ddd.magdy.fashione_commerace.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ddd.magdy.fashione_commerace.database.ProductDatabase;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetailsViewModel extends ViewModel {

    private MutableLiveData<List<ProductResponseItem>> productResponseItemMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    public MutableLiveData<String> messageError = new MutableLiveData<>();

    public void getItemsByCategory(String categoryName) {
        showLoading.setValue(true);
        RetrofitClient.getRetrofitClientInstance().getApi()
                .getItemByCategory(categoryName)
                .enqueue(new Callback<List<ProductResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<ProductResponseItem>> call, Response<List<ProductResponseItem>> response) {
                        showLoading.setValue(false);
                        if (response.isSuccessful()) {
                            productResponseItemMutableLiveData.setValue(response.body());
                        } else {
                            messageError.setValue(response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ProductResponseItem>> call, Throwable t) {
                        messageError.setValue(t.getMessage());
                    }
                });
    }

    public MutableLiveData<List<ProductResponseItem>> getProductResponseItemMutableLiveData() {
        return productResponseItemMutableLiveData;
    }

    public void addItemToDB(ProductResponseItem item, Context context) {
        ProductDatabase.getInstance(context).productDao()
                .addProductItem(item);
    }
}


