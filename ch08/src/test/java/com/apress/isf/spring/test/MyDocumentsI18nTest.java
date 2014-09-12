/**
 * 
 */
package com.apress.isf.spring.test;

import static java.lang.System.out;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Felipe Gutierrez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-i18n-context.xml")
public class MyDocumentsI18nTest {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsI18nTest.class);
	
	@Autowired
	private ApplicationContext context;
		
	@Test
	public void testMenu() {	
		log.debug("About to Translate...");
		
		String english = context.getMessage("main.title",null, Locale.ENGLISH);
		String spanish = context.getMessage("main.title",null, new Locale("es"));
		out.println("English: " + english);
		out.println("Spanish: " + spanish);
	}
}
