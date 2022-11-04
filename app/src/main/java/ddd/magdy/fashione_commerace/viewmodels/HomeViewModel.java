package ddd.magdy.fashione_commerace.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ProductResponseItem>> productResponseItemMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>(false);
    public MutableLiveData<String> messageError = new MutableLiveData<>();

    public void getProducts() {
        showLoading.postValue(true);
        RetrofitClient.getRetrofitClientInstance().getApi().getProduct().enqueue(new Callback<List<ProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ProductResponseItem>> call, Response<List<ProductResponseItem>> response) {
                showLoading.setValue(false);
                if (response.isSuccessful()) {
                    productResponseItemMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseItem>> call, Throwable t) {
                messageError.setValue(t.getLocalizedMessage());
            }
        });


    }

    public MutableLiveData<List<ProductResponseItem>> getProductResponseItemMutableLiveData() {
        return productResponseItemMutableLiveData;
    }
}