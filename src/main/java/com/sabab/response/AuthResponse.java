package com.sabab.response;

public class AuthResponse {
	
	private String token;
	private String messageg;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String token, String messageg) {
		super();
		this.token = token;
		this.messageg = messageg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessageg() {
		return messageg;
	}

	public void setMessageg(String messageg) {
		this.messageg = messageg;
	}
	
}
