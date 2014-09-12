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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;

/**
 * @author Felipe Gutierrez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-profiles-context.xml")
@ActiveProfiles("dev")
public class MyDocumentsWithProfilesTest {
	private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithProfilesTest.class);
	
	@Autowired
	private SearchEngine engine;
	@Autowired
	private Type webType;
	
	@Test
	public void testUsingSpringTestWithProfiles() {	
		try{
		log.debug("Using Spring Test fixtures:");
				
			List<Document> documents = engine.findByType(webType);
			assertNotNull(documents);
			assertTrue(documents.size() == 1);
			assertEquals(webType.getName(),documents.get(0).getType().getName());
			assertEquals(webType.getDesc(),documents.get(0).getType().getDesc());
			assertEquals(webType.getExtension(),documents.get(0).getType().getExtension());
			
			documents = engine.listAll();
			assertNotNull(documents);
			assertTrue(documents.size() == 4);
		}catch(Exception ex){
			log.error(ex.getMessage());
		}
	}
}
