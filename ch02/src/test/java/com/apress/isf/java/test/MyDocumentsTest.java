package com.apress.isf.java.test;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.MySearchEngine;
import com.apress.isf.java.service.SearchEngine;

public class MyDocumentsTest {
	
    private SearchEngine engine = new MySearchEngine();

    @Test
    public void testFindByType() {
        Type documentType = new Type();
        documentType.setName("WEB");
        documentType.setDesc("Web Link");
        documentType.setExtension(".url");
		
        List<Document> documents = engine.findByType(documentType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(documentType.getName(), documents.get(0).getType().getName());
        assertEquals(documentType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(documentType.getExtension(),
            documents.get(0).getType().getExtension());
	}
	
    @Test
    public void testListAll() {		
        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }

}
