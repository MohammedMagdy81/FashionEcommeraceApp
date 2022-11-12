package ddd.magdy.fashione_commerace.network;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.model.CartItem;
import ddd.magdy.fashione_commerace.model.ProductResponseItem;
import ddd.magdy.fashione_commerace.utils.Constant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET(Constant.END_POINT_PRODUCT)
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


}
