package ddd.magdy.fashione_commerace.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductResponseItem implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private double price;

	@SerializedName("rating")
	private Rating rating;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public ProductResponseItem(String category) {
		this.category = category;
	}

	@SerializedName("category")
	private String category;

	public String getImage(){
		return image;
	}

	public double getPrice(){
		return price;
	}

	public Rating getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getCategory(){
		return category;
	}
}