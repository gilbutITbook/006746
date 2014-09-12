package com.apress.isf.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;

public class MyDocumentsAnnotatedTest {
	
    private ApplicationContext context;
    private SearchEngine engine;
    private Type webType;
	
    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext(
            "META-INF/spring/mydocuments-annotations-context.xml");	
    }
	
    @Test
    public void testWithAnnotationsAll() {	
        engine = context.getBean(SearchEngine.class);
        webType = context.getBean(Type.class);
		
        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(),
            documents.get(0).getType().getExtension());
        
        engine = context.getBean(SearchEngine.class);
        
        documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }
    
}
