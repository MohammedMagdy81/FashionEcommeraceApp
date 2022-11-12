package ddd.magdy.fashione_commerace.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Cart{

	@SerializedName("Cart")
	private List<CartItem> cart;

	public List<CartItem> getCart(){
		return cart;
	}
}