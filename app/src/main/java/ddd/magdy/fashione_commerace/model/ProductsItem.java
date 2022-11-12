package ddd.magdy.fashione_commerace.model;

import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private int productId;

	public int getQuantity(){
		return quantity;
	}

	public int getProductId(){
		return productId;
	}
}