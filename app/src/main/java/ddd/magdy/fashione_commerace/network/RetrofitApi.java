package ddd.magdy.fashione_commerace.network;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.model.CartItem;
import ddd.magdy.fashione_commerace.model.LoginAuthUser;
import ddd.magdy.fashione_commerace.model.LoginResponse;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.model.ResponseUserItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("products")
    Call<List<ProductResponseItem>> getProduct();

    @GET("products/category/{category}")
    Call<List<ProductResponseItem>> getItemByCategory(@Path("category") String categoryName);

    @GET("products/{id}")
    Call<ProductResponseItem> getProductById(@Path("id") int id);

    @GET("products/categories")
    Call<ArrayList<String>> getCategories();


    @GET("carts")
    Call<List<CartItem>> getProductsInCarts();

    @GET("carts/{id}")
    Call<CartItem> getProductsInCartsById(@Path("id") int id);

    @POST("carts")
    Call<ProductResponseItem> addToCart(@Body ProductResponseItem item);


    @GET("users")
    Call<List<ResponseUserItem>> getAllUsers();

    @GET("users/{id}")
    Response<ResponseUserItem> getUserById(@Path("id") int id);


    @POST("auth/login")
    Response<LoginResponse> loginUser(@Body LoginAuthUser user);


}



