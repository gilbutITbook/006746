/**
 * 
 */
package com.apress.isf.spring.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.isf.spring.views.ResourceLoaderMenu;

/**
 * @author Felipe Gutierrez
 *
 */
public class MyDocumentsWithResourceLoaderInjectionTest {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithResourceLoaderInjectionTest.class);
	private ClassPathXmlApplicationContext context;
	
	@Before
	public void setup(){
		context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-resourceloader-injection-context.xml");
	}
	
	@Test
	public void testMenu() {	
		log.debug("Calling the Menu as Resourceloader Injection:");
		ResourceLoaderMenu menu = context.getBean(ResourceLoaderMenu.class);
		assertNotNull(menu);
		menu.printMenu("classpath:META-INF/data/menu.txt");
	}
}
