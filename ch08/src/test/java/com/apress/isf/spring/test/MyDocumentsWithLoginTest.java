/**
 * 
 */
package com.apress.isf.spring.test;

import static java.lang.System.out;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.service.Login;

/**
 * @author Felipe Gutierrez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-login-context.xml")
public class MyDocumentsWithLoginTest {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithLoginTest.class);
	private static final String EMAIL = "test@mydocuments.com";
	private static final String PASS = "test123";
	private static final String SUCCESS = "This user is authorized";
	private static final String FAILURE = "WARNING! This user is not authorized!";
	
	@Autowired
	private ApplicationContext context;
	
	
	
	@Test
	public void testLogin() {	
		log.debug("Login test.");
		Login login = context.getBean(Login.class);
		assertNotNull(login);
		if(login.isAuthorized(EMAIL, PASS))
			out.println(SUCCESS);
		else
			out.println(FAILURE);
	}
}
