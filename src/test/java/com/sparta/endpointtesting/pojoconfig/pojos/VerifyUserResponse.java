package com.sparta.endpointtesting.pojoconfig.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyUserResponse{

	@JsonProperty("message")
	private String message;

	@JsonProperty("responseCode")
	private int responseCode;

	public String getMessage(){
		return message;
	}

	public int getResponseCode(){
		return responseCode;
	}
}