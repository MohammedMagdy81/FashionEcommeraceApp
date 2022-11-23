package ddd.magdy.fashione_commerace.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageListViewModel extends ViewModel {

    private MutableLiveData<List<ResponseUserItem>> usersList = new MutableLiveData<>();

    public void getUsers() {
        RetrofitClient.getRetrofitClientInstance()
                .getApi()
                .getAllUsers().enqueue(new Callback<List<ResponseUserItem>>() {
                    @Override
                    public void onResponse(Call<List<ResponseUserItem>> call, Response<List<ResponseUserItem>> response) {
                        if (response.isSuccessful()) {
                            usersList.postValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<ResponseUserItem>> call, Throwable t) {
                    }
                });
    }

    public MutableLiveData<List<ResponseUserItem>> getUsersList() {
        return usersList;
    }


}
