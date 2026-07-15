package com.sparta.endpointtesting.pojoconfig.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category{

	@JsonProperty("usertype")
	private Usertype usertype;

	@JsonProperty("category")
	private String category;

	public Usertype getUsertype(){
		return usertype;
	}

	public String getCategory(){
		return category;
	}
}