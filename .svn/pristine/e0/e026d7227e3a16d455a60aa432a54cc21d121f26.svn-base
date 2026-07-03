package com.stl.ngms.config;


/**
 * @author Sanatnu Sritam Panda
 *
 */
public class JwtTokenResponse extends AbstractResponse{
	
	private String access_token;
	private String token_type;
	private String statusDesc;
	private Integer status;
	private Boolean firstLogin;
	public JwtTokenResponse(String access_token, String token_type) {
		this.access_token = access_token;
		this.token_type = token_type;
	}
	
	public JwtTokenResponse(String access_token, String token_type, String statusDesc, Integer status) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.statusDesc = statusDesc;
		this.status = status;
	}

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}
	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}



	public String getStatusDesc() {
		return statusDesc;
	}



	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public JwtTokenResponse(String access_token, String token_type, String statusDesc, Integer status,
			Boolean firstLogin) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.statusDesc = statusDesc;
		this.status = status;
		this.firstLogin = firstLogin;
	}



	
	
	
	

}

