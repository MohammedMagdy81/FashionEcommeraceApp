package ddd.magdy.fashione_commerace.network;

import java.util.ArrayList;
import java.util.List;

import ddd.magdy.fashione_commerace.model.CartItem;
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
    Call<ResponseUserItem> getUserById(@Path("id") int id);



}






/*
*   @GET("carts/user/{id}")
    suspend fun getCart(@Path(value = "id") id : Int) : Response<Cart>

    @GET("carts/user/{id}")
    suspend fun getCartProducts(@Path(value = "id") id : Int) : Response<CartItem>

    @POST("carts")
    suspend fun addToCart(@Body cartItem : CartItem) : Response<CartItem>

    @PATCH("carts/{id}")
    suspend fun updateCart(@Path(value = "id") id : Int, @Body cartItem: CartItem) : Response<CartItem>

    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path(value = "id") id : Int) : Response<CartItem>

    @GET("users/{id}")
    suspend fun getUser(@Path(value = "id") id : Int) : Response<User>

    @PUT("users/{id}")
    suspend fun updateUser(@Path(value = "id") id : Int, @Body user: User) : Response<User>

    //Login user
    @POST("auth/login")
    suspend fun loginUser(@Body login : Login): Response<LoginResponse>

    //Register user
    @POST("users")
    suspend fun registerUser(@Body user : User): Response<User>
*
*
* */