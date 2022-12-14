package ddd.magdy.fashione_commerace.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ProductResponseItem implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private double price;

	@SerializedName("rating")
	private Rating rating;

	@SerializedName("description")
	private String description;

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setImage(String image) {
		this.image = image;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCategory(String category) {
		this.category = category;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductResponseItem)) return false;
		ProductResponseItem item = (ProductResponseItem) o;
		return Double.compare(item.price, price) == 0 && id == item.id && Objects.equals(image, item.image) && Objects.equals(rating, item.rating) && Objects.equals(description, item.description) && Objects.equals(title, item.title) && Objects.equals(category, item.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(image, price, rating, description, id, title, category);
	}
}