package com.example.demo.infrastructure;

public class RestResponseError {

	private String error;
	
	public String getError() {
		return error;
	}
	
	public static RestResponseError fromMessage(String message) {
		RestResponseError resp = new RestResponseError();
		resp.error = message;
		return resp;
	}
}
