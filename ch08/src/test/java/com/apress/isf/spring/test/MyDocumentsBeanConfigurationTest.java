/**
 * 
 */
package com.apress.isf.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.config.MyDocumentsContext;

/**
 * @author Felipe Gutierrez
 * gradle -Dtest.single=MyDocumentsBeanConfigurationTest test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MyDocumentsContext.class)
public class MyDocumentsBeanConfigurationTest {

	@Autowired
	private ApplicationContext context;
	private SearchEngine engine;
	private Type webType;
	
	@Test
	public void testWithBeanConfigurationAll() {	
		engine = context.getBean(SearchEngine.class);
		webType = context.getBean(Type.class);
		
		List<Document> documents = engine.findByType(webType);
		assertNotNull(documents);
		assertTrue(documents.size() == 1);
		assertEquals(webType.getName(),documents.get(0).getType().getName());
		assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
		assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());
	
		engine = context.getBean(SearchEngine.class);
		
		documents = engine.listAll();
		assertNotNull(documents);
		assertTrue(documents.size() == 4);
	}

}
