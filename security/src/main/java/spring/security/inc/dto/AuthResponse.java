package spring.security.inc.dto;

import lombok.Builder;

@Builder
public class AuthResponse
{
	private String Token;

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token) {
		super();
		Token = token;
	}



}
