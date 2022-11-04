package ddd.magdy.fashione_commerace.network;

import ddd.magdy.fashione_commerace.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static RetrofitClient retrofitClient = null;
    private RetrofitApi api;
    private Retrofit retrofitInstance;

    private RetrofitClient() {
        retrofitInstance = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofitInstance.create(RetrofitApi.class);
    }

    public static RetrofitClient getRetrofitClientInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public RetrofitApi getApi() {
        return api;
    }


}
