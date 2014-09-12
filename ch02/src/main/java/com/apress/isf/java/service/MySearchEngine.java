package com.apress.isf.java.service;

import java.util.ArrayList;
import java.util.List;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

public class MySearchEngine implements SearchEngine {
	
    @Override
    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<Document>();
        for (Document document : storage()) {
            if (document.getType().getName().equals(documentType.getName()))
                result.add(document);
        }
		return result;
	}
	
    @Override
    public List<Document> listAll() {		
        return storage();
    }

    private List<Document> storage() {
        List<Document> result = new ArrayList<Document>();
		
        Type type = new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");
		
        Document document = new Document();
        document.setName("Book Template");
        document.setType(type);
        document.setLocation("/Users/felipeg/Documents/Random/Book Template.pdf");
		
        result.add(document);
		
        document = new Document();
        document.setName("Sample Contract");
        document.setType(type);
        document.setLocation(
            "/Users/felipeg/Documents/Contracts/Sample Contract.pdf");
		
        result.add(document);
		
        type = new Type();
        type.setName("NOTE");
        type.setDesc("Text Notes");
        type.setExtension(".txt");
		
        document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(type);
        document.setLocation(
            "/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt");
		
        result.add(document);
		
        type = new Type();
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");
		
        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type);
        document.setLocation("http://www.apress.com/9781430248187");
		
        result.add(document);
        
        return result;
    }
}
