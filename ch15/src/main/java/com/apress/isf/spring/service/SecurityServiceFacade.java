/**
 * 
 */
package com.apress.isf.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.isf.java.service.Login;

/**
 * @author Felipe Gutierrez
 *
 */
public class SecurityServiceFacade {
	private Logger log = LoggerFactory.getLogger(SecurityServiceFacade.class);

	private Login login;

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public boolean areCredentialsValid(String email, String pass){
		log.debug("Validating Credentials > email:" + email + ", pass:" + pass);
		return this.login.isAuthorized(email, pass);
	}
}
