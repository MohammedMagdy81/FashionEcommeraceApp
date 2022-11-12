package ddd.magdy.fashione_commerace.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CartItem{

	@SerializedName("date")
	private String date;

	@SerializedName("__v")
	private int V;

	@SerializedName("id")
	private int id;

	@SerializedName("userId")
	private int userId;

	@SerializedName("products")
	private List<ProductsItem> products;

	public String getDate(){
		return date;
	}

	public int getV(){
		return V;
	}

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}