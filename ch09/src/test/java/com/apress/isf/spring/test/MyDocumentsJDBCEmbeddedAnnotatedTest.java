package com.apress.isf.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-jdbc-embedded-context.xml")
public class MyDocumentsJDBCEmbeddedAnnotatedTest {

	@Autowired
	private SearchEngine engine;
	private Type webType = new Type("WEB",".url");

	@Test
	public void testJDBCEmbedded() {

		List<Document> documents = engine.findByType(webType);
		assertNotNull(documents);
		assertTrue(documents.size() == 1);
		assertEquals(webType.getName(), documents.get(0).getType().getName());
		assertEquals(webType.getExtension(),
		    documents.get(0).getType().getExtension());

		documents = engine.listAll();
		assertNotNull(documents);
		assertTrue(documents.size() == 4);
	}

}
