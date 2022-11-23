package ddd.magdy.fashione_commerace.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseUserItem implements Serializable {

	@SerializedName("password")
	private String password;

	@SerializedName("address")
	private Address address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private Name name;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public String getPassword(){
		return password;
	}

	public Address getAddress(){
		return address;
	}

	public String getPhone(){
		return phone;
	}

	public int getV(){
		return V;
	}

	public Name getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}
}