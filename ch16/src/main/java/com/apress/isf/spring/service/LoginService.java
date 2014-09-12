/**
 * 
 */
package com.apress.isf.spring.service;

import com.apress.isf.java.service.Login;

/**
 * @author Felipe Gutierrez
 *
 */
public class LoginService implements Login {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAuthorized(String email, String pass){
		if(username.equals(email) && password.equals(pass))
			return true;
		return false;
	}

}
